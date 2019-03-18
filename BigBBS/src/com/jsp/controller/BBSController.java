package com.jsp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dao.BBSDao;
import com.jsp.dto.BBSDto;

public class BBSController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BBS컨트롤러 접속");
		String page = req.getParameter("page");
		String bbsColum = req.getParameter("id");
		
		if(bbsColum==null || bbsColum.trim().equals("")) {
			page = "1";
		}
//		int nowPage = Integer.parseInt(page);
		
		int startNum = 0;
//		int endNum = 0;
		int perpage = 10;//페이지 항목 수
//		endNum = nowPage * 10;//
//		startNum = endNum - 9;
		
		BBSDao bbsDao = BBSDao.getInstance();
		List<BBSDto> list = bbsDao.selectAll(startNum, perpage);
		
		req.setAttribute("list", list);
		int startPage = 1;
		int endPage = 0;
//		startPage = nowPage / 10 * 10 + 1;
//		if(nowPage % 10 == 0) {
//			startPage -= 10;
//		}
//		endPage = startPage + 9;
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/bbs.jsp");
		rd.forward(req, resp);
	}
}
