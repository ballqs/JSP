<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		ArrayList list = new ArrayList();
		list.add("ABC");
		list.add("ZYX");
		request.setAttribute("list", list);
	%>
	<c:forEach var="item" items="${list}">
		${item}<br/>
	</c:forEach>
</body>
</html>