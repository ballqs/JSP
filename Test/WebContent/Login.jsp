<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		
		out.print("아이디 : "+id+" 비밀번호 : "+pw+" 이름 : "+name);
	%>
	<br>
	${paramValues.id[0]}<br>
	${paramValues.id[1]}<br>
	${param.pw}<br>
	${param.name}
</body>
</html>