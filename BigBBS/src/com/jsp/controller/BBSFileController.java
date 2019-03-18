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
			is = new FileInputStream(bbsFile);//�б�(bbsFDao.selectById(fileId)�� ��)
			os = resp.getOutputStream();//���� -> ������ resp�� ���� ���������� ����ϴ� �ڿ��� ������ ��
			int data = 0;
			while((data = is.read(b,0,b.length))!= -1) {//�д´�, b�� 1024�� �ִ� ũ�⸦ ������ �ְ� ����Ʈ ������ �д´�.
				os.write(b,0,data);//����, ���� ���� �״�� ����(���ٴ� �ǹ�)�� ���� �� ����.
			}
			os.flush();//�������� �����ֱ�
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
