package com.jsp.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dao.TransactionDao;
import com.jsp.dto.BBSDto;
import com.jsp.dto.BBSFileDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BBSuploadController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/bbsupload.jsp");
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BBSuploadController�� ���� �Ϸ�");
		String path = "C:/workspace/utils/Test/";//������ ��
		
		MultipartRequest mRequest = new MultipartRequest(req, path, 1024 * 1024 * 50, 
				"utf-8", new DefaultFileRenamePolicy());
		//���� ũ�� 50MB���� ����Ѵ�.
		//DefaultFileRenamePolicy : �����ҷ��� �ߴ��� �Ȱ��� ���ϸ��� ���� �� �ٸ� �̸����� �ٲ㼭 ����
		HttpSession session = req.getSession();//�α��� �� ������� Ȯ���ϱ� ���� ����.
		String sessionID = null;
		if(session.getAttribute("id") != null) {//�α��� ����
			sessionID = (String) session.getAttribute("id");
			//�α��� �� ����� ��� sessionID�� �Ӽ����ν� ����
	}
	
		String category = mRequest.getParameter("category");
		String title = mRequest.getParameter("title");
		String content = mRequest.getParameter("content");
		BBSDto bbsDto = new BBSDto();
		bbsDto.setId(sessionID);
		bbsDto.setBbsTitle(title);
		bbsDto.setBbsCategory(category);
		bbsDto.setBbsContent(content);
		
		List<BBSFileDto> bbsfDtoList = new ArrayList<BBSFileDto>();
		String name[] = new String[100];
		String changeName[] = new String[100];
		File file[] = new File[100];
		
		for(int i=1; i<50; i++) {
			name[i] = mRequest.getOriginalFileName("bbs_file" + i);
			//bbsupload.jsp ���� �޾ƿ� ������ �̸��� ���°� bbs_file�����̴�.
			//�׷��� ������ for���� ���·� �ҷ��� ������ �̸��� () �ȿ� �ִ� ���̸�
			//mRequest���� �ִ� 50MB�� ũ���� ������ �޾ƿ´�.
			System.out.println(name[i]);
			if(name[i] == null) {
				if(i==1) {
					bbsfDtoList = null;				
				}
				break;
			}
			
			file[i] = mRequest.getFile("bbs_file"+i);//���� ������ Ÿ�����·� �̸� ����
			changeName[i] = file[i].getName();//�ٲ� �̸��� ���¸� changeName�� �����Ѵ�.
			BBSFileDto bbsfDto = new BBSFileDto();
			bbsfDto.setOrgn_file_nm(path+name[i]);//�����̸�
			bbsfDto.setSave_file_nm(path+changeName[i]);//������̸�
			
			bbsfDtoList.add(bbsfDto);
			System.out.println("bbsfDtoList�� ����Ϸ�"+i);
		}
		TransactionDao tDao = TransactionDao.getInstance();
		
		int resultTrans = tDao.insertAll(bbsDto,bbsfDtoList);
		
		RequestDispatcher rd = req.getRequestDispatcher("BBS.do");
		rd.forward(req, resp);
		
	}

}
