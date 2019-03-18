package com.jsp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dao.BBSDao;
import com.jsp.dao.BBSFileDao;
import com.jsp.dto.BBSDto;
import com.jsp.dto.BBSFileDto;

public class BBSViewController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BBSViewController - 접속 완료");
		String bbsId = req.getParameter("id");
		//bbs에 title를 눌렀을때 id값을 가져온다.
		BBSDao bbsDao = BBSDao.getInstance();
		BBSDto bbsDto = new BBSDto();
		int result = bbsDao.hitUpdate(bbsId);
		bbsDto = bbsDao.selectById(bbsId);
		
		req.setAttribute("bbsview", bbsDto);
		
		BBSFileDao bbsfDao = BBSFileDao.getInstance();
		
		List<BBSFileDto> files = bbsfDao.selectByBbsId(bbsId);
		req.setAttribute("files", files);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/bbsview.jsp");
		rd.forward(req, resp);
	}
}
