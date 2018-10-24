package kr.co.happy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardReg")
public class BoardRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardRegServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("content", "boardReg");
		RequestDispatcher dispatcher = request.getRequestDispatcher("template.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bid = request.getParameter("bid");
		int intBid = Integer.parseInt(bid);
		String pw = request.getParameter("pw");
		String btype = request.getParameter("btype");
		
		BoardDAO dao = BoardDAO.getInstance();
		if(dao.checkPw(intBid, pw)) {
			BoardDTO dto = dao.getBoard(intBid);
			
			request.setAttribute("content", "boardReg");
			request.setAttribute("dto", dto);
			RequestDispatcher dispatcher = request.getRequestDispatcher("template.jsp");
			dispatcher.forward(request, response);
			
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호가 다릅니다'); history.back();</script>");
			out.flush();
		}
	}
}
