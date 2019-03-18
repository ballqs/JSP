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
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		// Exeam03-1에서 가져온 값을 String 변수에 담는다.
		out.print("Exeam03-1의 ID : "+id+"<br>")  ;
		out.print("Exeam03-1의 PW : "+pw);
	%>
</body>
</html>