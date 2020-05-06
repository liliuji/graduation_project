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
<title>活动信息</title>
</head>
<body>
	<div class="col-sm-10 col-sm-offset-1">
	<div id="navbar-example">
		<ul class="nav nav-tabs">
		    <li class="active"><a data-toggle="tab" href="#ing">正在进行</a></li>
		    <li><a data-toggle="tab" href="#no">未开始</a></li>
		    <li><a data-toggle="tab" href="#done">已结束</a></li>
	  	</ul>
	</div>
		<div class="tab-content"  data-spy="scroll" data-target="#navbar-example"data-offset="10" style="overflow:auto; position: relative;margin-top:15px;">
	    <div id="ing" class="tab-pane fade in active">
	   <table class="table table-bordered table-hover">
	  <thead>
	    <tr>
		  <th>序号</th>
	      <th>活动名称</th>
	      <th>发布人</th>
	      <th>发布时间</th>
	      <th>操作</th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${underwayList}" var= "activity"  varStatus="status">
		  <tr>
			  <td>${status.index + 1}</td>
			  <td>${activity.activityname}</td>
			  <td>${activity.byUserName}</td>
			  <td>${activity.releasetime}</td>
			  <td>
				  <a class="tooltip-test" data-toggle="tooltip" title="查看活动">
					  <span class="glyphicon glyphicon-search look"></span>
				  </a>
				  <a class="tooltip-test" data-toggle="tooltip" title="删除活动">
					  <span class="glyphicon glyphicon-trash delete"></span>
				  </a>
			  </td>
			  <td style="display: none">${activity.activityid}</td>
		  </tr>
	  </c:forEach>
	</tbody>
	</table>
	    </div>
	    <div id="no" class="tab-pane fade">
	    <table class="table table-bordered table-hover">
	  <thead>
	    <tr>
		  <th>序号</th>
	      <th>活动名称</th>
	      <th>发布人</th>
	      <th>发布时间</th>
	      <th>操作</th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${noStartList}" var= "activity"  varStatus="status">
		     <tr>
				 <td>${status.index + 1}</td>
				 <td>${activity.activityname}</td>
		   	     <td>${activity.byUserName}</td>
		 		 <td>${activity.releasetime}</td>
				 <td>
					 <a class="tooltip-test" data-toggle="tooltip" title="查看活动">
						 <span class="glyphicon glyphicon-search look"></span>
					 </a>
					 <a class="tooltip-test" data-toggle="tooltip" title="删除活动">
						 <span class="glyphicon glyphicon-trash delete"></span>
					 </a>
				 </td>
				 <td style="display: none">${activity.activityid}</td>
	 		 </tr>
	  </c:forEach>
	  </tbody>
	</table>
	    </div>
	      <div id="done" class="tab-pane fade">
	    <table class="table table-bordered table-hover">
	  <thead>
	    <tr>
		  <th>序号</th>
	      <th>活动名称</th>
	      <th>发布人</th>
	      <th>发布时间</th>
	      <th>操作</th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach items="${finishList}" var= "activity"  varStatus="status">
		  <tr>
			  <td>${status.index + 1}</td>
			  <td>${activity.activityname}</td>
			  <td>${activity.byUserName}</td>
			  <td>${activity.releasetime}</td>
			  <td>
				  <a class="tooltip-test" data-toggle="tooltip" title="查看活动">
					  <span class="glyphicon glyphicon-search look"></span>
				  </a>
				  <a class="tooltip-test" data-toggle="tooltip" title="删除活动">
					  <span class="glyphicon glyphicon-trash delete"></span>
				  </a>
			  </td>
			  <td style="display: none">${activity.activityid}</td>
		  </tr>
	  </c:forEach>
	  </tbody>
	</table>
	    </div>
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
				<div class="col-sm-4 text-center">
                	<button type="button" class="btn btn-primary" id="export">导出名单</button>
                </div>
				<div class="col-sm-2"></div>
				</div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script src="/graduation_project/js/myActivity.js"></script>
</body>
</html>