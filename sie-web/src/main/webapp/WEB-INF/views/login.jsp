<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>Sign In - PixelAdmin</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
	<link href="/statics/assets/stylesheets/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="/statics/assets/stylesheets/pixel-admin.min.css" rel="stylesheet" type="text/css">
	<link href="/statics/assets/stylesheets/pages.min.css" rel="stylesheet" type="text/css">
	<link href="/statics/assets/stylesheets/rtl.min.css" rel="stylesheet" type="text/css">
	<link href="/statics/assets/stylesheets/themes.min.css" rel="stylesheet" type="text/css">

<!-- $DEMO =========================================================================================

	Remove this section on production
-->
	<style>
		#signin-demo {
			position: fixed;
			right: 0;
			bottom: 0;
			z-index: 10000;
			background: rgba(0,0,0,.6);
			padding: 6px;
			border-radius: 3px;
		}
		#signin-demo img { cursor: pointer; height: 40px; }
		#signin-demo img:hover { opacity: .5; }
		#signin-demo div {
			color: #fff;
			font-size: 10px;
			font-weight: 600;
			padding-bottom: 6px;
		}
	</style>
<!-- / $DEMO -->

</head>


<!-- 1. $BODY ======================================================================================
	
	Body

	Classes:
	* 'theme-{THEME NAME}'
	* 'right-to-left'     - Sets text direction to right-to-left
-->
<body class="theme-default page-signin">



<script>
	var init = [];
	init.push(function () {
		var $div = $('<div id="signin-demo" class="hidden-xs"><div>PAGE BACKGROUND</div></div>'),
		    bgs  = [ '/statics/assets/demo/signin-bg-1.jpg', '/statics/assets/demo/signin-bg-2.jpg', '/statics/assets/demo/signin-bg-3.jpg',
		    		 '/statics/assets/demo/signin-bg-4.jpg', '/statics/assets/demo/signin-bg-5.jpg', '/statics/assets/demo/signin-bg-6.jpg',
					 '/statics/assets/demo/signin-bg-7.jpg', '/statics/assets/demo/signin-bg-8.jpg', '/statics/assets/demo/signin-bg-9.jpg' ];
		for (var i=0, l=bgs.length; i < l; i++) $div.append($('<img src="' + bgs[i] + '">'));
		$div.find('img').click(function () {
			var img = new Image();
			img.onload = function () {
				$('#page-signin-bg > img').attr('src', img.src);
				$(window).resize();
			}
			img.src = $(this).attr('src');
		});
		$('body').append($div);
	});
</script>
<!-- Demo script --> <script src="/statics/assets/demo/demo.js"></script> <!-- / Demo script -->

	<!-- Page background -->
	<div id="page-signin-bg">
		<!-- Background overlay -->
		<div class="overlay"></div>
		<!-- Replace this with your bg image -->
		<img src="/statics/assets/demo/signin-bg-1.jpg" alt="">
	</div>
	<!-- / Page background -->

	<!-- Container -->
	<div class="signin-container">

		<!-- Left side -->
		<div class="signin-info">
			<a href="index.html" class="logo">
				<img src="/statics/assets/demo/logo-big.png" alt="" style="margin-top: -5px;">&nbsp;
				PixelAdmin
			</a> <!-- / .logo -->
			<div class="slogan">
				Simple. Flexible. Powerful.
			</div> <!-- / .slogan -->
			<ul>
				<li><i class="fa fa-sitemap signin-icon"></i> Flexible modular structure</li>
				<li><i class="fa fa-file-text-o signin-icon"></i> LESS &amp; SCSS source files</li>
				<li><i class="fa fa-outdent signin-icon"></i> RTL direction support</li>
				<li><i class="fa fa-heart signin-icon"></i> Crafted with love</li>
			</ul> <!-- / Info list -->
		</div>
		<!-- / Left side -->

		<!-- Right side -->
		<div class="signin-form">

			<!-- Form -->
			<form action="index.html" id="data-form">
				<div class="signin-text">
					<span>登录信息</span>
				</div> <!-- / .signin-text -->

				<div class="form-group w-icon">
					<input type="text" name="userName" id="userName" class="form-control input-lg" placeholder="请输入用户名">
					<span class="fa fa-user signin-form-icon"></span>
				</div> <!-- / Username -->

				<div class="form-group w-icon">
					<input type="password" name="password" id="password" class="form-control input-lg" placeholder="请输入密码">
					<span class="fa fa-lock signin-form-icon"></span>
				</div> <!-- / Password -->

				<div class="form-group w-icon">
					<span id="errorMsg" style="color: red;"></span>
				</div>

				<div class="form-actions" style="margin-left: 30%;">
					<input type="button" id="submitBtn" value="登录" onclick="return false;" class="signin-btn bg-primary">
				</div> <!-- / .form-actions -->
			</form>
		</div>
		<!-- Right side -->
	</div>
	<!-- / Container -->

<script src="/statics/assets/javascripts/jquery-1.8.3.min.js"></script>

<!-- Pixel Admin's javascripts -->
<script src="/statics/assets/javascripts/bootstrap.min.js"></script>
<script src="/statics/assets/javascripts/pixel-admin.min.js"></script>

<script type="text/javascript">
	// Resize BG
	init.push(function () {
		var $ph  = $('#page-signin-bg'),
		    $img = $ph.find('> img');

		$(window).on('resize', function () {
			$img.attr('style', '');
			if ($img.height() < $ph.height()) {
				$img.css({
					height: '100%',
					width: 'auto'
				});
			}
		});
	});
	window.PixelAdmin.start(init);

	$(function(){
		$("#submitBtn").bind("click", function(){
			if($("#userName").val() == ""){
				$("#errorMsg").html("请输入用户名")
				return;
			}
			if($("#password").val() == ""){
				$("#errorMsg").html("请输入密码")
				return;
			}

			var data = {"userName":$("#userName").val(),"password":$("#password").val()}
			$.ajax({
				url: '/user/login.json',
				data: data,
				type: 'post',
				dataType: 'json',
				cache: false,
				success: function (data) {
					if (data.success) {
						window.location.href="/index";
					} else {
						$("#errorMsg").html("登录失败，用户名或密码错误")
					}
				},
				error: function () {
					$("#errorMsg").html("登录失败，请联系管理员")
				}
			});
		})
	})
</script>

</body>
</html>
