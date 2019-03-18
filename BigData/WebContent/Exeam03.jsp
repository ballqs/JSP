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
		String grade = "3";
	%>
<select>
	<option value="">== 선택 ==</option>
	<option value="1" <%if(grade.equals("1")) out.print("selected='selected'"); %>>1학년</option>
	<option value="2" <%if(grade.equals("2")) out.print("selected='selected'"); %>>2학년</option>
	<option value="3" <%=grade.equals("3") ? "selected='selected'" : "" %>>3학년</option>
	<option value="4" <%=grade.equals("4") ? "selected='selected'" : "" %>>4학년</option>
</select>
</body>
</html>