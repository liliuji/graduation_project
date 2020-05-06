<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>计忆志协</title>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=2.0"/><title>主页</title>
    
	
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
 
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
 
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
	<input type="file" id="1">
	<button class="btn btn-info">提交</button>
	
	<script>
		$(document).ready(function(){
			//获取文件名称
			function getFileName(path) {
			    var pos1 = path.lastIndexOf('/');
			    var pos2 = path.lastIndexOf('\\');
			    var pos = Math.max(pos1, pos2);
			    if (pos < 0) {
			        return path;
			    }
			    else {
			        return path.substring(pos + 1);
			    }
			}
			
			$("button").on("click",function(){
				 var str = $("#1").val();
			        var file = getFileName(str);
			        var fileName=file.substring(0,file.lastIndexOf('.'));
			        alert(fileName);
			        var name=new Array();
			        name=fileName.split(" ");
			        var date=name[1]
			        alert(date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6));
			        var fileExt = str.substring(str.lastIndexOf('.') + 1); 
			        alert(fileName + "\r\n" + fileExt);
			})
		});
	</script>
</body>
</html>