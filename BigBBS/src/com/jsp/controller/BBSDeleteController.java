package com.jsp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dao.TransactionDao;
import com.jsp.dto.BBSDto;
import com.jsp.dto.BBSFileDto;

public class BBSDeleteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.process(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.process(req, resp);
	}
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BBSDeleteController - Á¢¼Ó");
		HttpSession session = req.getSession();
		String sessionID = null;
		if(session.getAttribute("id")!= null) {
			sessionID = (String)session.getAttribute("id");
		}
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String bbsid = req.getParameter("id");
		
		BBSDto bbsDto = new BBSDto();
		bbsDto.setBbsId(bbsid);
		
		BBSFileDto bbsfDto = new BBSFileDto();
		bbsfDto.setBbsId(bbsid);
		
		TransactionDao tDao = TransactionDao.getInstance();
		
		int resultTrans = tDao.Delete(bbsDto,bbsfDto);
		
		req.setAttribute("resultTrans", resultTrans);
		
		RequestDispatcher rd = req.getRequestDispatcher("BBS.do");
		
		rd.forward(req, resp);
	}
}
