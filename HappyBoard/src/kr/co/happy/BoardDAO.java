package kr.co.happy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {
	
	private BoardDAO() {}
	
	public static BoardDAO instance = null;
	
	public static BoardDAO getInstance() {
		if(instance==null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	public ArrayList<BoardDTO> getBoardList(int page, int btype) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = " select * from ( "
				+ " select h.*, row_number() over(order by seq desc) as rnum from h_board h where h.btype = ? "
				+ " ) " 
				+ " where rnum between ? and ? ";
		int start = (page-1)*10 +1;
		int end = page*10;
		
		try {
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(query);
			ps.setInt(1, btype);
			ps.setInt(2, start);
			ps.setInt(3, end);
	
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBid(rs.getInt("bid"));
				dto.setSeq(rs.getInt("seq"));
				dto.setBtype(rs.getInt("btype"));
				dto.setBtitle(rs.getString("btitle"));
				dto.setBregdate(rs.getString("bregdate"));
				list.add(dto);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps, rs);
		}
		return list;
	}
	
	public BoardDTO getBoard(int bid) {
		BoardDTO dto = new BoardDTO();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = " select * from h_board where bid = ?";
		
		try {
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(query);
			ps.setInt(1, bid);	
	
			rs = ps.executeQuery();
			
			if(rs.next()) {
				dto.setSeq(rs.getInt("seq"));
				dto.setBtitle(rs.getString("btitle"));
				dto.setBcontent(rs.getString("bcontent"));
			}		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps, rs);
		}
		return dto;
	}
	
	public void insertBoard(int btype, String title, String content) {
		
	
	}
	
	
}
	
