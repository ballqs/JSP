<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

	<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	<style type="text/css">
		.select-me{
			margin: 0 0 20px 0;
		}
/* 		.files input {
		    outline: 2px dashed #92b0b3;
		    outline-offset: -10px;
		    -webkit-transition: outline-offset .15s ease-in-out, background-color .15s linear;
		    transition: outline-offset .15s ease-in-out, background-color .15s linear;
		    padding: 120px 0px 85px 35%;
		    text-align: center !important;
		    margin: 0;
		    width: 100% !important;
		}
		.files input:focus{     outline: 2px dashed #92b0b3;  outline-offset: -10px;
		    -webkit-transition: outline-offset .15s ease-in-out, background-color .15s linear;
		    transition: outline-offset .15s ease-in-out, background-color .15s linear; border:1px solid #92b0b3;
		 }
		.files{ position:relative}
		.files:after {  pointer-events: none;
		    position: absolute;
		    top: 60px;
		    left: 0;
		    width: 50px;
		    right: 0;
		    height: 56px;
		    content: "";
		    background-image: url(https://image.flaticon.com/icons/png/128/109/109612.png);
		    display: block;
		    margin: 0 auto;
		    background-size: 100%;
		    background-repeat: no-repeat;
		}
		.color input{ background-color:#f1f1f1;}
		.files:before {
		    position: absolute;
		    bottom: 10px;
		    left: 0;  pointer-events: none;
		    width: 100%;
		    right: 0;
		    height: 57px;
		    display: block;
		    margin: 0 auto;
		    color: #2ea591;
		    font-weight: 600;
		    text-transform: capitalize;
		    text-align: center;
		} */
		
		#imgdiv{
			border: 1px solid black;
			width: 700px;
			height: 200px;
			border-radius: 5px;
			margin: 5px 0;
			background-image: url("img/blog/1.jpg");
			background-size: 100% 100%;
			background-repeat: no-repeat;
		}
		.mine{
			display: inline-block;
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
			<h2>Contact</h2>
			<div class="site-breadcrumb">
				<a href="home">Home</a> / <span>Contact</span>
			</div>
		</div>
	</section>
	<!-- Page Top section end -->


	<!-- Contact section -->
	<section class="contact-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 contact-text">
					<h4>Contact Info</h4>
					<p>Nullam lacinia ex eleifend orci portt-itor, suscipit interdum augue condi-mentum. Etiam pretium turpis eget nibh laoreet iaculis. Vivamus auctor mi eget odio feugiat.</p>
					<div class="cont-info">
						<div class="ci-icon">
							<img src="img/icons/1.png" alt="">
						</div>
						<span>Main Str, no 23, New York</span>
					</div>
					<div class="cont-info">
						<div class="ci-icon">
							<img src="img/icons/2.png" alt="">
						</div>
						<span>+546 990221 123</span>
					</div>
					<div class="cont-info">
						<div class="ci-icon">
							<img src="img/icons/2.png" alt="">
						</div>
						<span>hosting@contact.com</span>
					</div>
				</div>
				<div class="col-lg-9">
					<form class="contact-form" method="post" action="BBSupload.do" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-12">
								<div class="mine" id="imgdiv"></div>
								<div class="mine" id="radio-mine">
									<input type="radio" class="mine_img" name="check" value="1" checked="checked" onclick="imgchange(1)">
									<input type="radio" class="mine_img" name="check" value="2" onclick="imgchange(2)">
									<input type="radio" class="mine_img" name="check" value="3" onclick="imgchange(3)">
								</div>
						        <div class="b-select-wrap select-me">
						          <select name="category" class="form-control b-select">
						            <option selected>-- 카테고리 선택 --</option>
						            <option value="math">수학</option>
									<option value="enjoy">여행</option>
									<option value="pic">사진</option>
									<option value="java">java</option>
									<option value="web">웹프로그래밍</option>
									<option value="estate">부동산</option>
									<option value="food">음식</option>
									<option value="common">상식</option>
						          </select>
						        </div>
							</div>
							
							
							
							<div class="col-md-12">
								<input type="text" name="title" placeholder="Title">
								<textarea name="content" placeholder="Content"></textarea>
								<!-- <div class="form-group files">
					              <label>Upload Your File </label>
					              <input type="file" name='bbs_file1' class="form-control" multiple="">
					            </div> -->
							<script type="text/javascript">
								var uf = "";
								function file_add(size, ext) {
									var filecountTemp = parseInt(document
											.getElementById("file_cnt").value);
									//<input type="hidden" id="file_cnt"name="file_cnt" value="1" style="">
									//의 value의 값을 가져와서 나중에 생길 파일의 name명에 붙이는 용도로 사용
									
									var parents = document
											.getElementById("file_add_form");
									//parents 라는 변수명에 file_add_form라는 아이디를 가진 태그를 불러옴
									var br = document.createElement("br");
									//br만드는 태그
									br.setAttribute("id", "br" + (filecountTemp + 1));
									parents.appendChild(br);
									if (filecountTemp == 30) {
										alert("파일업로드는 최대 30개까지 가능합니다"); //< --- 이 부분 지우면 무한대로 추가됨.
										return;
										//30개 초과시 경고창을 나타낸다.
									} else {
										var obj = document.createElement("input");
										//화면에는 존재하지 않는 input 태그를 새로 만들고
										obj.setAttribute("type", "file");
										//그 input의 태그의 타입은 file이라는 지정하는 명령어
										obj.setAttribute("size", size);
										//또한 size는 50이라고 밑에 지정해뒀음
										obj.setAttribute("name", "bbs_file"
												+ (filecountTemp + 1));
										//그리고 이 태그의 이름은 bbs_file뒤에 붙는 숫자의 +1 형태로
										obj.setAttribute("id", "file"
												+ (filecountTemp + 1));
										//아이디는 file로! 나중에 불러들일때 배열형태로 불러들이면 된다.
										parents.appendChild(obj);
										//parents 밑에 있던 자식들 중 제일 밑에 만들어준다.
									}
									document.getElementById("file_cnt").value = filecountTemp + 1;
								}
								function file_delete() {
									var filecountTemp = parseInt(document
											.getElementById("file_cnt").value);
									var parents = document
											.getElementById("file_add_form");
									var obj = document.getElementById('file'
											+ filecountTemp);
									var br = document.getElementById('br'
											+ filecountTemp);
									parents.removeChild(obj);
									parents.removeChild(br);
									document.getElementById("file_cnt").value = filecountTemp - 1;
								}
							</script>
								<div class="w3-button w3-block w3-button w3-black" style="width: 30%;">
								<input type="hidden" id="file_cnt"
									name="file_cnt" value="1" style=""> 
									<a href="javascript:file_add(50, 'class=input_write');"><b>첨부파일추가</b></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="javascript:file_delete();"><b>첨부파일삭제</b></a>
								</div>
								<div id='file_add_form'
									class="w3-button w3-block w3-button w3-indigo"
									style="width: 80%;">
									<input type=file name='bbs_file1' size='50' class='input_write'>
								</div>
								<button type="submit" class="site-btn">Send</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<br/>
		<br/>
		<br/>
	</section>
	<!-- Contact section end -->


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
							<div class="lp-item">
								<div class="lp-thumb set-bg" data-setbg="img/footer-thumb/1.jpg"></div>
								<div class="lp-content">
									<h6>Web Hosting for everyone</h6>
									<span>Oct 21, 2018</span>
								</div>
							</div>
							<div class="lp-item">
								<div class="lp-thumb set-bg" data-setbg="img/footer-thumb/2.jpg"></div>
								<div class="lp-content">
									<h6>Web Hosting for everyone</h6>
									<span>Oct 21, 2018</span>
								</div>
							</div>
							<div class="lp-item">
								<div class="lp-thumb set-bg" data-setbg="img/footer-thumb/3.jpg"></div>
								<div class="lp-content">
									<h6>Web Hosting for everyone</h6>
									<span>Oct 21, 2018</span>
								</div>
							</div>
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

	<!-- load for map -->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB0YyDTa0qqOjIerob2VTIwo_XVMhrruxo"></script>
	<script src="js/map.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type='text/javascript'>
		function imgchange(i) {
			
			if(i == 1){
				document.getElementById('imgdiv').setAttribute('style', 'background:url(img/blog/1.jpg) 80% 80% no-repeat');
			}else if(i == 2){
				document.getElementById('imgdiv').setAttribute('style', 'background:url(img/blog/2.jpg) 80% 80% no-repeat');
			}else if(i == 3){
				document.getElementById('imgdiv').setAttribute('style', 'background:url(img/blog/3.jpg) 80% 80% no-repeat');
			}
		}
	</script>
	</body>
</html>
