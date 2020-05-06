<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/dashboard.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
<style>
#madd {
	margin-top: 40px;
	margin-left: -170px;
}

#msearch {
	margin-top: 40px;
}

#mdel {
	margin-top: 40px;
	margin-left: -50px;
}
</style>
<title>携志同行</title>
<link rel="shortcout icon" href="<%=request.getContextPath()%>/backimg/11.ico.jpg" />
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">携志同行</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">${User.userName }</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">账号管理 <span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li id="changePassword"><a href="#">更改密码</a></li>
						<li id="exit"><a href="#">退出登录</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	</nav>
	<div class="col-sm-3 col-md-2 sidebar">
		<nav id="sidebar">
		<ul class="list-unstyled components">
			<p>功能导航栏</p>
			<li><a href="#activitySubmenu" data-toggle="collapse"
				aria-expanded="false" class="dropdown-toggle">活动管理</a>
				<ul class="nav nav-tabs nav-stacked collapse list-unstyled"
					role="tablist" id="activitySubmenu">
					<li role="presentation"><a href="" aria-controls="addActivity"
						role="tab" data-toggle="tab" id="addActivity">添加活动</a></li>
					<li role="presentation"><a href="" aria-controls="teacherinfo"
						role="tab" data-toggle="tab" id="myActivity">我的活动</a></li>
				</ul>
			</li>
			<li><a href="#volunteerSubmenu" data-toggle="collapse"
				   aria-expanded="false" class="dropdown-toggle">志愿者管理</a>
				<ul class="nav nav-tabs nav-stacked collapse list-unstyled"
					role="tablist" id="volunteerSubmenu">
					<li role="presentation"><a href="" id="volunteerInfo"
											   aria-controls="orderinfo" role="tab" data-toggle="tab">志愿者信息查询</a>
					</li>
				</ul>
			</li>
			<li><a href="#carSubmenu" data-toggle="collapse"
				aria-expanded="false" class="dropdown-toggle">公告管理</a>
				<ul class="nav nav-tabs nav-stacked collapse list-unstyled"
					role="tablist" id="carSubmenu">
					<li role="presentation"><a href="" aria-controls="uploadVtime"
						id="addNotice" role="tab" data-toggle="tab">添加公告</a></li>
					<li role="presentation"><a href="" aria-controls="uploadVtime"
						id="showNotice" role="tab" data-toggle="tab">查看公告</a></li>
				</ul>
			</li>
			<li><a href="#presenceSubmenu" data-toggle="collapse"
				   aria-expanded="false" class="dropdown-toggle">风采管理</a>
				<ul class="nav nav-tabs nav-stacked collapse list-unstyled"
					role="tablist" id="presenceSubmenu">
					<li role="presentation"><a href="" aria-controls="uploadVtime"
											   id="addPresence" role="tab" data-toggle="tab">添加风采</a></li>
					<li role="presentation"><a href="" aria-controls="uploadVtime"
											   id="showPresence" role="tab" data-toggle="tab">查看风采</a></li>
				</ul>
			</li>
			<li><a href="#feedbackSubmenu" data-toggle="collapse"
				   aria-expanded="false" class="dropdown-toggle">反馈管理</a>
				<ul class="nav nav-tabs nav-stacked collapse list-unstyled"
					role="tablist" id="feedbackSubmenu">
					<li role="presentation"><a href="" aria-controls="uploadVtime"
											   id="showFeedback" role="tab" data-toggle="tab">查看反馈</a></li>
				</ul>
			</li>
			<li><a href="#enlistSubmenu" data-toggle="collapse"
				   aria-expanded="false" class="dropdown-toggle">报名管理</a>
				<ul class="nav nav-tabs nav-stacked collapse list-unstyled"
					role="tablist" id="enlistSubmenu">
					<li role="presentation"><a href="" aria-controls="uploadVtime"
											   id="showEnlist" role="tab" data-toggle="tab">查看报名信息</a></li>
				</ul>
			</li>
		</ul>
		</nav>
	</div>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"
		id="admin">
		<iframe src="<%=request.getContextPath()%>/tip" width="100%" style="border: none;"></iframe>
	</div>
	<div class="modal fade" id="changePsw" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
				
				<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">更改密码</h4>
				</div>
				<div class="modal-body text-center">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="firstname" class="col-xs-2 control-label">用户名</label>
							<div class="col-xs-10">
								<input type="text" readOnly="readonly" class="form-control" id="userName" value="${user.userName }">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-xs-2 control-label">请输旧入密码</label>
							<div class="col-xs-10">
								<input type="password" class="form-control" id="oldPassword" placeholder="请输旧入密码">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-xs-2 control-label">请输入新密码</label>
							<div class="col-xs-10">
								<input type="password" class="form-control" id="newPassword"  placeholder="请输入新密码">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-xs-2 control-label">确认密码</label>
							<div class="col-xs-10">
								<input type="password" class="form-control" id="password" placeholder="请再次填写密码">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-offset-2 col-xs-8">
								<button type="button" class="btn btn-info btn-block" id="updatePassword">修改</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<script src="/graduation_project/js/main.js"></script>
</body>
</html>