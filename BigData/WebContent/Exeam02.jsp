<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>4-2</title>
</head>
<body>
	<%
		String id = "seorab";
		String pw = "pass";
	%>
	<div>
		ID : <input type="text" name="id" value="<%=id %>"/>
								<!-- 위에 만든 String id를 끌어다가 사용할려면 위와 같이 -->
	</div>
	<div>
		PW : <input type="password" name="id" value="<%=pw %>"/>
	</div>
</body>
</html>