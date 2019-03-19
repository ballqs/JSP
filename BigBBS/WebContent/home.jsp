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
	<link rel="stylesheet" href="css/animate.css"/>
	<link rel="stylesheet" href="css/style.css"/>


	<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->

</head>
<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Header section -->
	<header class="header-section">
		<a href="Home.do" class="site-logo"><img src="./img/logo.png" alt=""></a>
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
				<%--<c:choose>
					<c:when test='${loginResult == 1}'>
						<a href="#">
							 <c:if test='${loginResult == 1}'>
								${sessionScope.id}님 접속
							</c:if>
						</a> /
						<a href="#">
                			<c:if test='${joinResult == 1}'>
								${sessionScope.id}님 가입.접속
							</c:if>
						</a>
					</c:when>
					<c:otherwise>
						<a href="login.co">Sign in</a>
					</c:otherwise>
				</c:choose>--%>
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


	<!-- Hero section -->
	<section class="hero-section set-bg" data-setbg="img/bg.jpg">
		<div class="container h-100">
			<div class="hero-content text-white">
				<div class="row">
					<div class="col-lg-6 pr-0">
						<h2>Unbeatable Offers</h2>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada lorem maximus mauris scelerisque, at rutrum nulla dictum. Ut ac ligula sapien. </p>
						<a href="#" class="site-btn">Get your plan</a>
					</div>
				</div>
				<div class="hero-rocket">
					<img src="./img/rocket.png" alt="">
				</div>
			</div>
		</div>
	</section>
	<!-- Hero section end -->


	<!-- Features section -->
	<section class="features-section spad">
		<div class="container">
			<div class="section-title">
				<img src="./img/section-title-icon.png" alt="#">
				<p>The best out there</p>
				<h2>포트 폴리오</h2>
			</div>
			<div class="row">
				<div class="col-lg-6 col-md-6 feature-item">
					<div class="ft-icon"></div>
					<h4>프로필</h4>
					<p>이름 : 황호진 <br/>
					나이 : 26살 (1994.04.13) <br/>
					성격 : 꼼꼼하고 집중력이 좋음</p>
				</div>
				<div class="col-lg-6 col-md-6 feature-item">
					<div class="ft-icon"></div>
					<h4>교육 이수 과정</h4>
					<p>경남정보대학교 2013.03 ~ 2018.02<br/>
					부산IT직업전문학교 2018.12 ~ 2019.07</p>
				</div>
				<div class="col-lg-4 col-md-6 feature-item">
					<div class="ft-icon"></div>
					<h4>Blog</h4>
					<p>배운 내용을 Blog에 기록을 하며 남겼습니다.</p>
				</div>
				<div class="col-lg-4 col-md-6 feature-item">
					<div class="ft-icon"></div>
					<h4>Skill</h4>
					<p>HTML,CSS,Java,Linux,DataBase,JSP,PHP<br/> 등 많은 기술을 배워왔습니다.</p>
				</div>
				<div class="col-lg-4 col-md-6 feature-item">
					<div class="ft-icon"></div>
					<h4>Project</h4>
					<p>지금까지 만든 프로젝트</p>
				</div>
			</div>
		</div>
	</section>
	<!-- Features section end -->


	<!-- Skills & testimonials section -->
	<section class="skills-and-testimonials-section spad">
		<div class="container">
			<div class="row">
				<!-- Skills -->
				<div class="col-lg-6">
					<div class="single-progress-item">
						<p>HTML</p>
						<div class="progress-bar-style" data-progress="90"  data-bgcolor="#eb2b63"></div>
					</div>
					<div class="single-progress-item">
						<p>CSS</p>
						<div class="progress-bar-style" data-progress="100"  data-bgcolor="#eb2bae"></div>
					</div>
					<div class="single-progress-item">
						<p>Java</p>
						<div class="progress-bar-style" data-progress="80"  data-bgcolor="#d22beb"></div>
					</div>
					<div class="single-progress-item">
						<p>JSP</p>
						<div class="progress-bar-style" data-progress="60"  data-bgcolor="#972beb"></div>
					</div>
					<div class="single-progress-item">
						<p>DataBase</p>
						<div class="progress-bar-style" data-progress="80"  data-bgcolor="#732beb"></div>
					</div>
				</div>
				<!-- Testimonials -->
				<div class="col-lg-6">
					<div class="testimonials-slider owl-carousel">
						<div class="testimonial-item">
							<div class="ti-content">
								<p>작은 것을 크게 받아들이는 자에겐 큰 것이 찾아 든다 라는 말이 있습니다. 작은 실수 하나 하나를 책임있게 맡아 해결하며 꼼꼼한 면을 빛내겠습니다.</p>
							</div>
							<div class="ti-author-pic set-bg" data-setbg="img/mine_img.jpg"></div>
							<div class="ti-author">
								<h6>황호진</h6>
								<span>Ho Jin Hwang</span>
							</div>
						</div>
						<div class="testimonial-item">
							<div class="ti-content">
								<p>JSP를 통한 HTML 뼈대 구축 및 CSS 디자인을 통해 홈페이지를 만들며 거기에 Java를 추가해 기본적인 홈페이지 기능을 가진 웹 페이지를 제작할 수 있습니다.</p>
							</div>
							<div class="ti-author-pic set-bg" data-setbg="img/mine_img.jpg"></div>
							<div class="ti-author">
								<h6>황호진</h6>
								<span>Ho Jin Hwang</span>
							</div>
						</div>
						<div class="testimonial-item">
							<div class="ti-content">
								<p>전문대학교를 졸업했지만 다니는 내내 2년 동안 Java라는 과목이 빠진적이 한번도 없습니다. 그에 따른 이 언어에 대한 이해도는 다른 언어에 비해 높다고 자부할 수 있습니다.</p>
							</div>
							<div class="ti-author-pic set-bg" data-setbg="img/mine_img.jpg"></div>
							<div class="ti-author">
								<h6>황호진</h6>
								<span>Ho Jin Hwang</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Skills & testimonials section end -->


	<!-- Pricing section -->
	<section class="pricing-section spad pt-0">
		<div class="container">
			<div class="section-title">
				<p>코딩의 흔적을 찾아서...</p>
				<h2>GIT</h2>
			</div>
			<div class="row">
				<div class="col-lg-4 col-md-8 offset-md-2 offset-lg-0">
					<div class="pricing-plan">
						<div class="pricing-title">
							<h4>WEP GIT</h4>
						</div>
						<div class="pricing-body">
							<ul>
								<li>HTML,CSS</li>
								<li>화면 구현</li>
								<li>프로젝트</li>
							</ul>
							<a href="https://github.com/ballqs/study-web" class="site-btn">Get Plan</a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-8 offset-md-2 offset-lg-0">
					<div class="pricing-plan">
						<div class="pricing-title">
							<h4>Java GIT</h4>
						</div>
						<div class="pricing-body">
							<ul>
								<li>Java</li>
								<li>Event,Swing,Component</li>
								<li>Thread,Socket</li>
							</ul>
							<a href="https://github.com/ballqs/Java-project" class="site-btn">Get Plan</a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-8 offset-md-2 offset-lg-0">
					<div class="pricing-plan">
						<div class="pricing-title">
							<h4>JSP GIT</h4>
						</div>
						<div class="pricing-body">
							<ul>
								<li>JSP</li>
								<li>HTML,CSS,Java,Javascript,DB</li>
								<li>회원가입 폼,로그인 폼,게시판</li>
							</ul>
							<a href="https://github.com/ballqs/JSP" class="site-btn">Get Plan</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Pricing section end -->

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
