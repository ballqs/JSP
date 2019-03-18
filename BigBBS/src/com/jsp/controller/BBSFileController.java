package com.jsp.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dao.BBSFileDao;

public class BBSFileController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.process(req, resp);
	}
	private void process(HttpServletRequest req, HttpServletResponse resp) {
		String fileId = req.getParameter("id");
		BBSFileDao bbsFDao = BBSFileDao.getInstance();
		String bbsFile = bbsFDao.selectById(fileId);
		
		InputStream is = null;
		OutputStream os = null;
		
		try {
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attatchment; filename="+bbsFile);
			
			byte b[] = new byte[1024];
			is = new FileInputStream(bbsFile);//읽기(bbsFDao.selectById(fileId)의 값)
			os = resp.getOutputStream();//쓰기 -> 서버가 resp를 통해 웹페이지를 사용하는 자에게 보낼때 씀
			int data = 0;
			while((data = is.read(b,0,b.length))!= -1) {//읽는다, b는 1024의 최대 크기를 가지고 있고 바이트 단위로 읽는다.
				os.write(b,0,data);//쓴다, 읽은 량을 그대로 축적(쓴다는 의미)후 다음 껄 쓴다.
			}
			os.flush();//브라우저로 보내주기
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(os != null) {
				try {
					os.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(is != null) {
				try {
					is.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
