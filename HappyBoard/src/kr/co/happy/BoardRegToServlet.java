package kr.co.happy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardRegTo")
public class BoardRegToServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardRegToServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String bid = request.getParameter("bid");
		int intBid = Integer.parseInt(bid);
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String btype = request.getParameter("btype");
		int intBtype = Integer.parseInt(btype);
		
		BoardDAO dao = BoardDAO.getInstance();
		
		if(bid.equals("0")) {
			String pw = request.getParameter("pw");
			dao.insertBoard(intBtype, btitle, bcontent, pw);
			response.sendRedirect("boardList?btype=" + intBtype);
		} else {
			dao.updateBoard(intBid, btitle, bcontent);
			response.sendRedirect("boardDetail?bid=" + intBid + "&btype=" + intBtype);
		}
	}
}
