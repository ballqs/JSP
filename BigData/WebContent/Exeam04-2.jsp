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
		String []hobbi = request.getParameterValues("hobby");
		if(hobbi != null){			
			for(int i=0; i<hobbi.length; i++){
				out.print("체크가 된 목록은 : "+hobbi[i]+"<br>");			
			}
		}
		/*
		for(String hobby : hobbi){
			out.print(hobby);
		}
		*/
	%>
</body>
</html>