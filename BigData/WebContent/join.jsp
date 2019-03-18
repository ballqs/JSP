<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.co.testdto.MemberDto" %>
<%@ page import="kr.co.testdao.MemberDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		
		MemberDto mDto = new MemberDto();
		mDto.setID(id);
		mDto.setPW(pw);
		mDto.setNAME(name);
		
		MemberDao mDao = new MemberDao();
		int result = mDao.insertMemberDao(mDto);
		
		if(result > 0){
			out.print("회원 정보 입력 성공");
		}else{
			out.print("회원 정보 입력 실패");
		}
	%>
</body>
</html>