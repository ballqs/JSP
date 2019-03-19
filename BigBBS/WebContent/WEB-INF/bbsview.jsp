<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zxx">
<head>
	<title>IT Host - Hosting Template</title>
	<meta charset="UTF-8">
	<meta name="description" content="Cloud 83 - hosting template ">
	<meta name="keywords" content="cloud, hosting, creative, html">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Favicon -->
	<link href="img/favicon.ico" rel="shortcut icon"/>

	<!-- Google Font -->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,400i,500,500i,600,600i,700,700i" rel="stylesheet">


	<!-- Stylesheets -->
	<link rel="stylesheet" href="css/bootstrap.min.css"/>
	<link rel="stylesheet" href="css/font-awesome.min.css"/>
	<link rel="stylesheet" href="css/flaticon.css"/>
	<link rel="stylesheet" href="css/owl.carousel.min.css"/>
	<link rel="stylesheet" href="css/style.css"/>


	<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	<style type="text/css">
		.listings{
			display: inline-block;
			padding: 5px;
			font-family: font-family: Georgia, "Times New Roman", serif;
			size: 8px;
			text-shadow: 1px 1px 1px black;
		}
		.btnnn{
			margin: 15px;
			border: 1px solid orange;
			padding: 15px 25px 15px 25px;
			background-color:orange;
			border-radius: 5px;
			font-family :'나눔고딕', 'Malgun Gothic', sans-serif;
			color : white;
		}
		.btnnn:hover {
			background-color: yellow;
			border: 1px solid yellow;
			color : red;
		}
	</style>
</head>
<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Header section -->
	<header class="header-section">
		<a href="./index.html" class="site-logo"><img src="./img/logo.png" alt=""></a>
		<div class="nav-switch">
			<i class="fa fa-bars"></i>
		</div>
		<div class="nav-warp">
			<div class="user-panel">
				<%
					String sessionID = null;
					if(session.getAttribute("id") != null){
						sessionID = (String) session.getAttribute("id");
					}
					
					if(sessionID != null ){
				%>
					<a href="logout.do">
						로그아웃
					</a>
				<% }else if(sessionID == null){ %>
					<a href="login.co">Sign in</a>
				<% } %>
			</div>
			<ul class="main-menu">
				<li><a href="Home.do">Home</a></li>
				<li><a href="BBS.do">Blog</a></li>
				<li><a href="./service.html">Services</a></li>
				<li><a href="./blog.html">News</a></li>
				<li><a href="./contact.html">Contact</a></li>
			</ul>
		</div>
	</header>
	<!-- Header section end -->
	

	<!-- Page Top section -->
	<section class="page-top-section set-bg" data-setbg="img/page-top-bg.jpg">
		<div class="container">
			<h2>About us</h2>
			<div class="site-breadcrumb">
				<a href="home">Home</a> / <span>About us</span>
			</div>
		</div>
	</section>
	<!-- Page Top section end -->

	<br/><br/><br/>
	<!-- About section -->
	<section class="about-section spad pt-0">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 about-text">
					<h3>${bbsview.bbsTitle}</h3>
					<div class="listings">조회수: ${bbsview.bbsHit}</div>
					<div class="listings">카테고리	: ${bbsview.bbsCategory}</div>
					<div class="listings">작성자: ${bbsview.id}</div>
					<div class="listings">날짜: ${bbsview.bbsDate}</div>
					<p>${bbsview.bbsContent}</p>
					<div class="w3-panel">
						<div>파일 목록</div>
						<c:forEach var="item" items="${files}" >
							<a href="bbsFile.do?id=${item.fileId}" target="_blank">${item.orgn_file_nm}</a>
						</c:forEach>
						<br/>
						<br/>
				<%
					if(sessionID != null ){
				%>
						</div>
							<a href="BBS.do" class="btnnn">목록</a>
							<a href="bbsdelete.do?id=${bbsview.bbsId}" class="btnnn">삭제</a>
							<a href="bbsupdate.do?id=${bbsview.bbsId}" class="btnnn">수정</a>
						</div>
				<% }else if(sessionID == null){ %>
						</div>
							<a href="BBS.do" class="btnnn">목록</a>
						</div>
				<% } %>
				<div class="col-lg-6">
					<img src="./img/about.jpg" alt="">
				</div>
			</div>
		</div>
	</section>
	<!-- About section end -->


	<!-- Footer top section -->
	<section class="footer-top-section text-white spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-4">
					<div class="footer-widget about-widget">
						<img src="./img/logo.png" alt="logo">
						<p>Proin gravida nibh vel velit auctor aliquet, aenean sollicitudin lorem quis bibendum auctor, nisi elit consequat ipsum.Proin gravida nibh vel velit auctor aliquet, aenean sollicitudin lorem quis bibendum auctor, nisi elit consequat ipsum</p>
						<div class="fw-social social">
							<a href="#"><i class="fa fa-pinterest"></i></a>
							<a href="#"><i class="fa fa-facebook"></i></a>
							<a href="#"><i class="fa fa-twitter"></i></a>
							<a href="#"><i class="fa fa-dribbble"></i></a>
							<a href="#"><i class="fa fa-behance"></i></a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6">
					<div class="footer-widget">
						<h4 class="fw-title">Usefull Links</h4>
						<div class="row">
							<div class="col-sm-6">
								<ul>
									<li><a href="">Web Hosting</a></li>
									<li><a href="">WordPress Hosting</a></li>
									<li><a href="">VPS Hosting</a></li>
									<li><a href="">Cloud Server</a></li>
									<li><a href="">Reseller Package</a></li>
									<li><a href="">Dedicated Hosting</a></li>
								</ul>
							</div>
							<div class="col-sm-6">
								<ul>
									<li><a href="">Web Hosting</a></li>
									<li><a href="">WordPress Hosting</a></li>
									<li><a href="">VPS Hosting</a></li>
									<li><a href="">Cloud Server</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4  col-md-6">
					<div class="footer-widget">
						<h4 class="fw-title">Latest Posts</h4>
						<div class="fw-latest-post-widget">
							<c:forEach items="${hitlist}" var="hit" begin="0" end="2">
								<div class="lp-item">
									<div class="lp-thumb set-bg" data-setbg="${hit.img}"></div>
									<div class="lp-content">
										<h6>${fn:substring(hit.bbsTitle,0,10)}</h6>
										<span>${hit.bbsDate }</span>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Footer top section end -->


	<!-- Footer section -->
	<footer class="footer-section">
		<div class="container">
			<ul class="footer-menu">
				<li><a href="">Home</a></li>
				<li><a href="">About us</a></li>
				<li><a href="">Services</a></li>
				<li><a href="">News</a></li>
				<li><a href="">Contact</a></li>
			</ul>
			<div class="copyright"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></div>
		</div>
	</footer>
	<!-- Footer top section end -->


	<!--====== Javascripts & Jquery ======-->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/circle-progress.min.js"></script>
	<script src="js/main.js"></script>

	</body>
</html>
