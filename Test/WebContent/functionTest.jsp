<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="str" value="동해물과 백두산이 마르고"/>
	Substrung : ${fn:substring(str,1,10)}<br/>
	trim : ${fn:trim(str)}<br/>
	replace : ${fn:replace(str," ",'3')}<br/>
	indexOf : ${fn:indexOf(str,"과")}<br/>
	length : ${fn:length(str)} <br/>
	split : <c:forEach var="item" items="${fn:split(str,'')}">
		${item}
	</c:forEach>
</body>
</html>