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
		int start = (page-1)*5 +1;
		int end = page*5;
		
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
	
	public void insertBoard(int btype, String title, String content, String pw) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		String query = " insert into h_board ( bid, btype, seq, btitle, bcontent, pw ) "
				+ " values ( (select nvl(max(bid), 0)+1 from h_board), "
				+ " ? , (select nvl(max(seq), 0)+1 from h_board where btype=?), ?, ?, ? ) ";
		
		try {
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(query);
			ps.setInt(1, btype);
			ps.setInt(2, btype);
			ps.setString(3, title);
			ps.setString(4, content);
			ps.setString(5, pw);
			
			ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps);
		}
	}
	
	public void updateBoard(int bid, String title, String content) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		String query = " update h_board set btitle = ?, bcontent = ? where bid = ? ";
		
		try {
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(query);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setInt(3, bid);
			
			ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps);
		}
	}
	
	public boolean checkPw(int bid, String pw) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = " select * from h_board where bid = ? and pw = ? "; 
		
		try {
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(query);
			ps.setInt(1, bid);
			ps.setString(2, pw);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = true;
			}
			
		} catch (Exception e) {
			
		} finally {
			DBConnector.close(conn, ps, rs);
		}
		return result;
	}
	
	public void deleteBoard(int bid) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		String query = " delete from h_board where bid = ? ";
		
		try {
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(query);
			ps.setInt(1, bid);
			
			ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps);
		}
	}
	
	public void insertComment(int bid, String content) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = " insert into h_comment (cid, bid, ccontent) values "
				+ "	((select nvl(max(cid), 0)+1 from h_comment), ?, ?) ";
		
		try {
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(query);
			ps.setInt(1, bid);
			ps.setString(2, content);
			
			ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps);
		}
	}
	
	public ArrayList<CommentDTO> getComment(int bid) {
		ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = " select * from h_comment where bid = ? order by cid ";
		
		try {
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(query);
			ps.setInt(1, bid);	
	
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setBid(rs.getInt("bid"));
				dto.setCid(rs.getInt("cid"));
				dto.setContent(rs.getString("ccontent"));
				dto.setPw(rs.getString("cpw"));
				dto.setRegdate(rs.getString("cregdate"));
				list.add(dto);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps, rs);
		}
		return list;
	}
	
	
	public int getPage(int btype) {
		int result = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = " select count(*) from h_board where btype = ? ";
		
		try {
			conn = DBConnector.getConn();
			ps = conn.prepareStatement(query);
			ps.setInt(1, btype);	
	
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int count = rs.getInt("count(*)");
				if(count%5==0) {
					result = count/5;
				} else {
					result = count/5 + 1;
				}			
			}		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps, rs);
		}
		return result;
	}
}
