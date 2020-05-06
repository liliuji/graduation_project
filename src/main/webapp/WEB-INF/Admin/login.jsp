<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="${pageContext.request.contextPath }/">
<title>携志同行</title>
<link rel="shortcout icon" href="<%=request.getContextPath()%>/backimg/11.ico.jpg" />
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
#window {
	border: 1px solid gray;
	border-radius: 10px;
	background-color: white;
	opacity: 0.9;
}

body {
	background-image: url("<%=request.getContextPath()%>/backimg/1.jpg");
}

.errorMessage{
	padding:0;
	text-align:center;
}
.errorMessage li{
	list-style-type:none;
	text-align:center;
}
</style>


</head>
<body>
<s:actionerror/>
	<div class="col-sm-4 col-sm-offset-4" id="window">
		<div id="myAlert" class="alert alert-danger col-sm-offset-1 col-sm-10"
			style="margin-top: 30px;">
			<s:fielderror/>
			<p class="text-center"></p>
		</div>
		<form role="form" class="form-horizontal" action="<%=request.getContextPath()%>/checkLogin" method="post">
			<div class="form-group" style="margin-top: 30px;">
				<div class="col-sm-offset-1 col-sm-10">
					<input type="text" class="form-control input-lg" id="userNo"
						name="userName" placeholder="请输入账号">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-10">
					<input type="password" class="form-control input-lg" id="password"
						name="password" placeholder="请输入密码">
				</div>
			</div>
			<div class="form-group" style="margin-bottom: 30px;">
				<div class="col-sm-offset-1 col-sm-10">
					<input type="submit" class="btn btn-info btn-block btn-lg"
						id="login" value="登陆" />
				</div>
			</div>
		</form>
	</div>
	<script>
		var windowHeight = $(window).height();
		$("#window").css("margin-top", windowHeight * 0.25 + 'px')
		$("#window").height(windowHeight * 0.5);
		if ($(".errorMessage").length>0) {
			$("p").text($(".errorMessage").text());
			$(".errorMessage").remove();
		} else { 
			$("p").text("欢迎登陆\"携志同行\"志愿者管理平台");
		}
		$(document).ready(function() {
			$("#login").click(function () {
				var username = $("#userNo").val();
				var password = $("#password").val();
				if (username == "") {
					$("p").text("用户名不能为空");
					return false;
				}
				if (password == "") {
					$("p").text("密码不能为空");
					return false;
				}
				return true;
			});

			$("input").on("focus", function() {
				$(".errorMessage").remove();
				$("p").text("欢迎登陆\"携志同行\"志愿者管理平台");
			});
		});
	</script>
</body>
</html>