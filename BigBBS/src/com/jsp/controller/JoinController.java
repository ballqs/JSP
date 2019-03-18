package com.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dao.MemberDao;
import com.jsp.dto.MemberDto;

/**
 * Servlet implementation class JoinController2
 */
@WebServlet("/join.do")
public class JoinController extends HttpServlet {
	private int result;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinController() {
        super();
    }
  //유저가 submit하면 GET이냐 POST냐 따라에 밑에 실행될 클래스가 정해짐.
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.getWriter().append("Served at: ").append(req.getContextPath());
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/Join.jsp");
		//req.getRequestDispatcher()안의 경로에 따라 달라진다.
		rd.forward(req, resp);
	}//주소창으로 Join.do로 들어오면 Get에 인해서 Join.jsp가 실행된다.
	//주소창에 입력으로 들어오는 방식자체가 GET으로 추정

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		result = 0;
		try {
//			doGet(req, resp);
			req.setCharacterEncoding("utf-8");//한글 깨짐 방지용
			System.out.println("확인");
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			String confirm = req.getParameter("confirm");
			String username = req.getParameter("username");
			String email = req.getParameter("email");
			
			MemberDao mDao = MemberDao.getInstance();
			MemberDto mDto = new MemberDto();
			mDto.setName(name);
			if(password.equals(confirm)) {
				mDto.setPassword(password);			
			}
			mDto.setUsername(username);
			mDto.setEmail(email);
			
			result = mDao.join(mDto);
			
			if(result == 1) {
				req.setAttribute("result", result);//내장객체 사용하는 방법
				//setAttribute사용법(이름,값) 내가 그 속성을 불렀을때 제공할 값
//				req.getAttribute("result"); 찾아올때 이렇게 사용하며 부르고자 할 이름 작성
				HttpSession session = req.getSession();
				//HttpSession에 req의 내장객체인 세션을 던져준다.
				session.setAttribute("id", name);
				//그렇게 되면 HttpSession는 req의 내장객체인 세션을 사용할수 있다.
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/LoginForm.jsp");
				//성공하면 클라이언트에게 "/WEB-INF/home.jsp"의 파일을 보낸다.
				rd.forward(req, resp);
				//클라이언트에게 보낸 그 창을 뛰워라고 하는 명령어
			}else {
				req.setAttribute("result", 0);
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/Join.jsp");
				rd.forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
