package kr.co.happy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String btype = request.getParameter("btype");
		String page = request.getParameter("page");
		
		if(btype==null||page==null) {
			response.sendRedirect("boardList?btype=1&page=1");
		} else {
			int intBtype = Integer.parseInt(btype);
			int intPage = Integer.parseInt(page);
		
			BoardDAO dao = BoardDAO.getInstance();
			ArrayList<BoardDTO> list = dao.getBoardList(intPage, intBtype);
			request.setAttribute("list", list);
			request.setAttribute("title", "HappyBoardList");
			request.setAttribute("content", "boardList");
			RequestDispatcher dispatcher = request.getRequestDispatcher("template.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
