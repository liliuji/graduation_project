<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <meta http-equiv="X-UA-Compatible" content="IE=edge">
	 <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.8&key=cc17678642f59e975f39bb06fcb83665&plugin=AMap.Autocomplete,AMap.PlaceSearch,AMap.Geolocation"></script>
    
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
<title>报名信息</title>
</head>
<body>
	<div class="col-sm-10 col-sm-offset-1">
		<table class="table table-bordered table-hover">
			<thead>
			<tr>
				<th>序号</th>
				<th>志愿者姓名</th>
				<th>活动名称</th>
				<th>报名原因</th>
				<th>报名日期</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${enlists}" var= "enlist"  varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${enlist.volunteername}</td>
					<td>${enlist.activityname}</td>
					<td>${enlist.enlistreason}</td>
					<td>${enlist.enlistdate}</td>
					<td>${enlist.enliststatus}</td>
					<td>
						<c:if test="${enlist.enliststatus=='审核中'}">
							<button id="approved" onclick="examine(this);">通过</button>
							<button id="auditFailed" onclick="examine(this);">不通过</button>
							<a class="tooltip-test" data-toggle="tooltip" title="删除">
								<span class="glyphicon glyphicon-trash delete"></span>
							</a>
						</c:if>
						<c:if test="${enlist.enliststatus=='不通过'}">
							<a class="tooltip-test" data-toggle="tooltip" title="删除">
								<span class="glyphicon glyphicon-trash delete"></span>
							</a>
						</c:if>
					</td>
					<td style="display: none">${enlist.enlistid}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	    </div>
	</div>
</div>
<script src="/graduation_project/js/myEnlist.js"></script>
</body>
</html>