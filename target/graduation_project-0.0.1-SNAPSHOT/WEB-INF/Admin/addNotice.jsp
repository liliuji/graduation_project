<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <script type="text/javascript">
        $(document).ready(function(){
            var resultMsg = '${resultMsg}';
            if(resultMsg!=''&&resultMsg!=null){
                alert(resultMsg);
            }
        });
    </script>
    <title>添加公告</title>
</head>
<body>
<form action="<%=request.getContextPath() %>/saveNotice" id="form" class="form-horizontal" role="form" method="post">
    <div class="form-group">
        <label for="noticeTitle" class="col-sm-2 control-label">标题</label>
        <div class="col-sm-7">
            <input type="text" class="form-control" id="noticetitle" name="noticetitle" placeholder="请输入公告标题">
        </div>
    </div>
    <div class="form-group">
        <label for="noticeContent" class="col-sm-2 control-label">内容</label>
        <div class="col-sm-7">
            <textarea class="form-control" id="noticecontent" name="noticecontent" placeholder="请输入公告内容" ></textarea>
        </div>
    </div>
    <div class="col-sm-3 col-sm-offset-2">
        <button class="btn btn-info btn-lg btn-block " type="submit">提交</button>
    </div>
    <div class="col-sm-2 col-sm-offset-2">
        <button class="btn btn-default btn-block btn-lg" id="clear" type="reset" >清空</button>
    </div>
</form>
</body>
</html>
