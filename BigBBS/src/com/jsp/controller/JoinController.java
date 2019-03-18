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
  //������ submit�ϸ� GET�̳� POST�� ���� �ؿ� ����� Ŭ������ ������.
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.getWriter().append("Served at: ").append(req.getContextPath());
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/Join.jsp");
		//req.getRequestDispatcher()���� ��ο� ���� �޶�����.
		rd.forward(req, resp);
	}//�ּ�â���� Join.do�� ������ Get�� ���ؼ� Join.jsp�� ����ȴ�.
	//�ּ�â�� �Է����� ������ �����ü�� GET���� ����

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		result = 0;
		try {
//			doGet(req, resp);
			req.setCharacterEncoding("utf-8");//�ѱ� ���� ������
			System.out.println("Ȯ��");
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
				req.setAttribute("result", result);//���尴ü ����ϴ� ���
				//setAttribute����(�̸�,��) ���� �� �Ӽ��� �ҷ����� ������ ��
//				req.getAttribute("result"); ã�ƿö� �̷��� ����ϸ� �θ����� �� �̸� �ۼ�
				HttpSession session = req.getSession();
				//HttpSession�� req�� ���尴ü�� ������ �����ش�.
				session.setAttribute("id", name);
				//�׷��� �Ǹ� HttpSession�� req�� ���尴ü�� ������ ����Ҽ� �ִ�.
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/LoginForm.jsp");
				//�����ϸ� Ŭ���̾�Ʈ���� "/WEB-INF/home.jsp"�� ������ ������.
				rd.forward(req, resp);
				//Ŭ���̾�Ʈ���� ���� �� â�� �ٿ���� �ϴ� ��ɾ�
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
