package com.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dao.TestMemberDao;
import com.jsp.dto.TestMemberDto;

/**
 * Servlet implementation class JoinController
 */
@WebServlet("/Join.do")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("»Æ¿Œ");
		String name = request.getParameter("name");
 		String password = request.getParameter("password");
		String confirm = request.getParameter("confirm");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		
		TestMemberDao mDao = TestMemberDao.getInstance();
		TestMemberDto mDto = new TestMemberDto();
		mDto.setName(name);
		if(password.equals(confirm)) {
			mDto.setPassword(password);
		}
		mDto.setUsername(username);
		mDto.setEmail(email);
		
		int result = mDao.join(mDto);
		
		if(result == 1) {
			request.setAttribute("result", result);
			HttpSession session = request.getSession();
			session.setAttribute("id", name);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("result", 0);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
			rd.forward(request, response);
		}
	}

}
