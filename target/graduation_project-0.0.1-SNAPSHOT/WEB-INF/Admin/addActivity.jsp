<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
 
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" ></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <meta http-equiv="X-UA-Compatible" content="IE=edge">
	 <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.8&key=cc17678642f59e975f39bb06fcb83665&plugin=AMap.Autocomplete,AMap.PlaceSearch,AMap.Geolocation"></script>
    
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
 
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	 <link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-fileinput/bootstrap-fileinput.css">
    <script src="<%=request.getContextPath()%>/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style type="text/css">
		.btn-circle {
		  width: 30px;
		  height: 30px;
		  text-align: center;
		  padding: 6px 0;
		  font-size: 12px;
		  line-height: 1.428571429;
		  border-radius: 15px;
		}
		
		.button{
			display:none;
		}
		
		body{
			padding-top:50px;
		}
		
		 .modal-content {
            // 宽度自适应内容
            width: fit-content;
            // 流出间隙
            padding: 0.2rem 0.4rem;
        }
	</style>
<title>添加活动</title>
</head>
<body>
<div id="container" style="display:none;"></div>
<div id="11"></div>
	<form id="form" class="form-horizontal" role="form" >
		<input type="hidden" id="searchlng">
		<input type="hidden" id="searchlat">
    	 <div class="form-group">
    	 	<label class="col-sm-2 control-label">活动照片</label>
            <div class="col-sm-5">
                <div class="fileinput fileinput-new" data-provides="fileinput" id="uploadImageDiv">
                    <div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
                        <img src="${companyInfo.image}" alt="" />
                    </div>
                    <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;"></div>
                    <div class="pull-right"  style="margin-top:50px;margin-left:30px;">
		                <span>请选择1M以内图片</span>
		            </div>
                    <div>
                        <span class="btn default btn-file">
                        <button class="btn btn-success fileinput-new">选择</button>
                        <button class="btn btn-info fileinput-exists">更改</button>
                        <input type="file" name="file"  id="uploadImage" /></span>
                        <button class="btn btn-default fileinput-exists" data-dismiss="fileinput">移除</button>
                    </div>
                </div>
            </div>
        </div>
        </form>
        <form id="form" class="form-horizontal" role="form">
		<div class="form-group">
    		<label for="Aname" class="col-sm-2 control-label">活动名称</label>
    		<div class="col-sm-7">
      			<input type="text" class="form-control" id="Aname" placeholder="请输入活动名称">
    		</div>
    	</div>
		<div class="form-group">
    		<label for="Adate" class="col-sm-2 control-label">活动日期</label>
    		<div class="col-sm-7">
      			<input type="date" class="form-control" id="Adate" placeholder="请输入活动日期">
    		</div>
    	</div>
		<div class="form-group">
    		<label for="Address" class="col-sm-2 control-label">活动地址</label>
    		<div class="col-sm-7">
      				<input type="text" class="form-control" id="Address" placeholder="请输入活动地址">
    				<div class="panel panel-default" id="ddd" style="display:none;"></div>
    		</div>
    	</div>
    	<div class="form-group">
    		<label class="col-sm-2 control-label">报名截止时间</label>
    		<div class="col-sm-3">
      			<input type="date" class="form-control" id="AdeadDate" placeholder="">
    		</div>
    		<div class="col-sm-1"></div>
    		<div class="col-sm-3">
      			<input type="time" class="form-control" id="AdeadTime" placeholder="">
    		</div>
    	</div>
    	<div class="requestGroup">
			<div class="form-group Atime">
	    		<label  class="col-sm-2 control-label">活动时间段</label>
	    		<div class="col-sm-3">
	      			<input type="time" class="form-control Aintime" id="Aintime" placeholder="">
	    		</div>
	    		<div class="col-sm-1  text-center">
	    			<label class="control-label">至</label>
	    		</div>
	    		<div class="col-sm-3">
	      			<input type="time" class="form-control Aouttime" id="Aouttime" placeholder="">
	    		</div>
	    		<%--<div class="col-sm-2 text-center button">
	    			<button type="button" class="btn btn-primary btn-circle pull-left" id="addTime">
						<span class="glyphicon glyphicon-plus"></span>
					</button>
					<label class="control-label pull-left">&nbsp;增加时间段</label>
	    		</div>--%>
	    	</div>
	    	<div class="form-group Aneed">
	    		<label  class="col-sm-2 control-label">需求人数</label>
	    		<div class="col-sm-7">
	      			<input type="text" class="form-control Apcount" id="Apcount" placeholder="请输入需求人数">
	    		</div>
	    		<%--<div class="col-sm-2 text-center button">
	    			<button type="button" class="btn btn-primary btn-circle pull-left addJob">
						<span class="glyphicon glyphicon-plus"></span>
					</button>
					<label class="control-label pull-left">&nbsp;增加岗位</label>
	    		</div>--%>
	    	</div>
	    	<div class="addJobGroup">
	    	</div>
    	</div>
    	<div class="form-group">
    		<label for="Arequest" class="col-sm-2 control-label">活动要求</label>
    		<div class="col-sm-7">
      			<textarea class="form-control" id="Arequest" placeholder="此处将整则招募要求填入" rows="3"></textarea>
    		</div>
    	</div>
		<div class="form-group">
			<label for="Arequest" class="col-sm-2 control-label">活动内容</label>
			<div class="col-sm-7">
				<textarea class="form-control" id="Acontent" placeholder="此处将整则招募信息填入" rows="3"></textarea>
			</div>
		</div>
	</form>
	<div class="col-sm-3 col-sm-offset-2">
	<button class="btn btn-info btn-lg btn-block " id="sub">提交</button>
	</div>
	<div class="col-sm-2 col-sm-offset-2">
		<button class="btn btn-default btn-block btn-lg" id="clear">清空</button>
	</div>
	<div class="modal fade bd-example-modal-sm"  id="myModal" role="dialog" data-backdrop="false"  aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <p class="text-center mb-0">
                <i class="fa fa-check-circle text-success mr-1" aria-hidden="true"></i>
                <span id="result">提交成功</span>
            </p>
        </div>
    </div>
</div>
	<script src="<%=request.getContextPath()%>/js/addActivity.js"></script>
</body>
</html>