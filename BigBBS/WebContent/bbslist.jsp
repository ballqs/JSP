<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	*{
        padding: 0;
        margin: 0;
        box-sizing: border-box;
    }
	
	#bbstable,th,td{
		border: 1px solid black;
		border-collapse: collapse;
	}
</style>
</head>
<body>
	<%
		String sessionID = null;
		if(session.getAttribute("sessionID") != null){
			sessionID = (String) session.getAttribute("sessionID");
		}
	%>
	<div>
	<table id="bbstable">
		<thead>
			<tr>
				<th width="10%">글번호</th>
				<th width="10%">카테고리</th>
				<th width="10%">작성자</th>
				<th width="20%">제목</th>
				<th width="30%">내용</th>
				<th width="10%">날짜</th>
				<th width="10%" align="left">조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="bbs">
				<tr>
					<td width="10%" align="center">${bbs.bbsId }</td>
					<td width="10%" align="left">
						<c:choose>
							<c:when test="${bbs.bbsCategory=='math'}">수학</c:when>
							<c:when test="${bbs.bbsCategory=='enjoy'}">여행</c:when>
							<c:when test="${bbs.bbsCategory=='pic'}">사진</c:when>
							<c:when test="${bbs.bbsCategory=='java'}">Java</c:when>
							<c:when test="${bbs.bbsCategory=='web'}">웹프로그래밍</c:when>
							<c:when test="${bbs.bbsCategory=='estate'}">부동산</c:when>
							<c:when test="${bbs.bbsCategory=='food'}">음식</c:when>
							<c:when test="${bbs.bbsCategory=='common'}">상식</c:when>
						</c:choose>
						</td>
					<td width="10%" align="center">${bbs.id}</td>
					<td width="20%" align="left"><a href='bbsview.do?id=${bbs.bbsId}'>
						<b>${bbs.bbsTitle}</b></a></td>
					<td width="30%" align="left"><a href='bbsview.do?id=${bbs.bbsId}'>
						<b>${bbs.bbsContent}</b></a></td>
					<td width="10%" align="left">${bbs.bbsDate}</td>
					<td width="10%" align="center">${bbs.bbsHit}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<% if(sessionID != null){%>
		<div>
			<button>
				<a href="#">글쓰기</a>
			</button>
		</div>
	<%} %>
	</div>
</body>
</html>