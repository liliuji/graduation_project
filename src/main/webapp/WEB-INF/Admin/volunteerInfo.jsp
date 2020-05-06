<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
 
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
 
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<style>
		body{
			padding-top:50px;
		}
	</style>
<title>志愿者信息</title>
</head>
<body>
	<div class="col-sm-10 col-sm-offset-1">
		<%--<form class="form-inline" role="form">
		  <div class="form-group col-sm-5">
		    <label  for="No" style="margin-bottom:10px;">学号</label>
		    <input type="text" class="form-control" id="No" placeholder="请输入学号" style="margin-bottom:10px;">
		  </div>
		</form>
		<button class="btn btn-info col-sm-1" style="margin-bottom:10px;">查询</button> --%>
		<table class="table table-bordered table-hover">
		  <thead>
		    <tr>
			  <th>序号</th>
			  <th>账号</th>
		      <th>姓名</th>
		      <th>性别</th>
		      <th>注册时间</th>
			  <th>操作</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:forEach items="${volunteerInfo}" var= "volunteer"  varStatus="status">
			  <tr>
				  <td>${status.index + 1}</td>
				  <td>${volunteer.account}</td>
				  <td>${volunteer.userName}</td>
				  <td>${volunteer.sex}</td>
				  <td>${volunteer.createDate}</td>
				  <td>
					  <a class="tooltip-test" data-toggle="tooltip" title="详细信息">
						  <span class="glyphicon glyphicon-search look"></span>
					  </a>
					  <a class="tooltip-test" data-toggle="tooltip" title="删除">
						  <span class="glyphicon glyphicon-trash delete"></span>
					  </a>
				  </td>
				  <td style="display: none">${volunteer.userId}</td>
			  </tr>
		  </c:forEach>
		  </tbody>
	</div>
	<div class="modal fade" id="lookActivity" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<div class="row">
						<div class="col-sm-2"></div>
						<div class="col-sm-4 text-center">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
						<%--<div class="col-sm-4 text-center">
							<button type="button" class="btn btn-primary" id="export">导出名单</button>
						</div>--%>
						<div class="col-sm-2"></div>
					</div>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<script src="<%=request.getContextPath()%>/js/volunteerInfo.js"></script>
</body>
</html>