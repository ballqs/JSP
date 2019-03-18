<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Random" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Random ran = new Random();
		int number = ran.nextInt(45) + 1;
		out.print("<h1> 랜덤 : "+number+"</h1>");
		//html에 보내주는 명령어 : out
	%>
</body>
</html>