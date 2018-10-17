package kr.co.happy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/commentReg")
public class CommentRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentRegServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bid = request.getParameter("bid");
		int intBid = Integer.parseInt(bid);
		String btype = request.getParameter("btype");
		String content = request.getParameter("comment");
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.insertComment(intBid, content);
		
		response.sendRedirect("boardDetail?bid=" + bid + "&btype=" + btype);
	}
}
