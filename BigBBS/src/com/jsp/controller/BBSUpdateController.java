package com.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dao.BBSDao;
import com.jsp.dao.TransactionDao;
import com.jsp.dto.BBSDto;

public class BBSUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BBSUpdateController doGet - 접속");
		String BBSID = req.getParameter("id");
		BBSDao bbsDao = BBSDao.getInstance();
		BBSDto bbsDto = bbsDao.selectById(BBSID);
		//bbsDao.selectById(bbsID)으로 bbsID에 맞는 정보를 뽑아올린다.
		req.setAttribute("bbsUpdate", bbsDto);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/bbsupdate.jsp");
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BBSUpdateController doPost - 접속");
		HttpSession session = req.getSession();
		String sessionID = null;
		if(session.getAttribute("id") != null) {
			sessionID = (String)session.getAttribute("id");
		}
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String category = req.getParameter("category");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String bbsid = req.getParameter("bbsId");
		
		BBSDto bbsDto = new BBSDto();
		
		bbsDto.setBbsTitle(title);
		bbsDto.setBbsCategory(category);
		bbsDto.setBbsContent(content);
		bbsDto.setBbsId(bbsid);
		
		TransactionDao tDao = TransactionDao.getInstance();
		
		int resultTrans = tDao.updateAll(bbsDto);
		
		req.setAttribute("resultTrans", resultTrans);
		
		RequestDispatcher rd = req.getRequestDispatcher("BBS.do");
		
		rd.forward(req, resp);
	}
}
