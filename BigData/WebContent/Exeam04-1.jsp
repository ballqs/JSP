<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="Exeam04-2.jsp" method="get">
		<input type="checkbox" id="java" name= "hobby" value="java"/>
		<label for="java">자바</label>
		<input type="checkbox" id="html" name= "hobby" value="html"/>
		<label for="html">HTML</label>
		<input type="checkbox" id="jsp" name= "hobby" value="jsp"/>
		<label for="jsp">JSP</label>
		<input type="checkbox" id="android" name= "hobby" value="android"/>
		<label for="android">안드로이드</label>
		<input type="checkbox" id="CSS" name= "hobby" value="CSS"/>
		<label for="CSS">CSS</label>
		<input type="checkbox" id="JQuery" name= "hobby" value="JQuery"/>
		<label for="JQuery">JQuery</label>
		<input type="checkbox" id="Linux" name= "hobby" value="Linux"/>
		<label for="Linux">리눅스</label>
		<br>
		<input type="submit" value="전송">
	</form>
</body>
</html>