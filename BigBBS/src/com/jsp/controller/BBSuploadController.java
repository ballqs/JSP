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
		System.out.println("BBSuploadController에 접속 완료");
		String path = "C:/workspace/utils/Test/";//저장할 곳
		
		MultipartRequest mRequest = new MultipartRequest(req, path, 1024 * 1024 * 50, 
				"utf-8", new DefaultFileRenamePolicy());
		//파일 크기 50MB까지 허용한다.
		//DefaultFileRenamePolicy : 저장할려고 했더니 똑같은 파일명이 있을 때 다른 이름으로 바꿔서 저장
		HttpSession session = req.getSession();//로그인 한 사람인지 확인하기 위해 쓴다.
		String sessionID = null;
		if(session.getAttribute("id") != null) {//로그인 여부
			sessionID = (String) session.getAttribute("id");
			//로그인 한 사람일 경우 sessionID에 속성으로써 저장
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
			//bbsupload.jsp 에서 받아온 파일의 이름의 형태가 bbs_file숫자이다.
			//그렇기 때문에 for문의 형태로 불러올 파일의 이름이 () 안에 있는 것이며
			//mRequest에서 최대 50MB의 크기인 파일을 받아온다.
			System.out.println(name[i]);
			if(name[i] == null) {
				if(i==1) {
					bbsfDtoList = null;				
				}
				break;
			}
			
			file[i] = mRequest.getFile("bbs_file"+i);//파일 데이터 타입형태로 이름 저장
			changeName[i] = file[i].getName();//바뀐 이름의 형태를 changeName에 저장한다.
			BBSFileDto bbsfDto = new BBSFileDto();
			bbsfDto.setOrgn_file_nm(path+name[i]);//원래이름
			bbsfDto.setSave_file_nm(path+changeName[i]);//변경된이름
			
			bbsfDtoList.add(bbsfDto);
			System.out.println("bbsfDtoList에 저장완료"+i);
		}
		TransactionDao tDao = TransactionDao.getInstance();
		
		int resultTrans = tDao.insertAll(bbsDto,bbsfDtoList);
		
		RequestDispatcher rd = req.getRequestDispatcher("BBS.do");
		rd.forward(req, resp);
		
	}

}
