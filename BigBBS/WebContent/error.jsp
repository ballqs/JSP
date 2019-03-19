<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	오류가 발생했습니다.
	<br />
	
	<%
		String type = exception.toString();
		String msg = exception.getMessage();
		out.print("오류의 종류 : "+type);
		out.print("<br/>메시지 : "+msg);
	%>
</body>
</html>