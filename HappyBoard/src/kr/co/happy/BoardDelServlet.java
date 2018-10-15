package kr.co.happy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardDel")
public class BoardDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDelServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bid = request.getParameter("bid");
		int intBid = Integer.parseInt(bid);
		String pw = request.getParameter("pw");
		String btype = request.getParameter("btype");
		
		BoardDAO dao = BoardDAO.getInstance();
		if(dao.checkPw(intBid, pw)) {
			
			dao.deleteBoard(intBid);
			response.sendRedirect("boardList?btype=" + btype);
		} else {
			response.sendRedirect("boardDetail?bid=" + intBid);
		}
	}
}
