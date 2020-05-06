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
			padding-top:30px;
		}
	</style>
<title>Insert title here</title>
</head>
<body>
	<div class="col-sm-10 col-sm-offset-1">
		<table class="table table-bordered table-hover">
		<caption>市中医院志愿者(每3分钟刷新一次)</caption>
		  <thead>
		    <tr>
		      <th>姓名</th>
		      <th>学号</th>
		      <th>时间段</th>
		      <th>岗位</th>
		      <th>签到时间</th>
		      <th>签退时间</th>
		    </tr>
		  </thead>
		  <tbody>
		  <%-- ArrayList<User> list=(ArrayList<User>)session.getAttribute("signList");
		    for(int i=0;i<list.size();i++){
		  --%>
		  <!-- 	<tr>
		  		<td><%--=list.get(i).getName() %></td>
		  		<td><%=list.get(i).getNo() %></td>
		  		<td><%=list.get(i).getAtime() %></td>
		  		<td><%=list.get(i).getAjobstate() %></td>
		  		<%if(list.get(i).getSignIn().equals("")){ %>
		  		<td>未签到</td>
		  		<%}else{ %>
		  		<td><%=list.get(i).getSignIn() %></td>
		  		<%}if(list.get(i).getSignOut().equals("")){ %>
		  		<td>未签退</td>
		  		<%}else{ %>
		  		<td><%=list.get(i).getSignOut() %></td>
		  		<%}} --%>
		  	</tr> -->
		  	<tr>
		  		<td>吴宇丁</td>
		  		<td>201626811025</td>
		  		<td>8:00-12:00</td>
		  		<td>无</td>
		  		<td>8:00</td>
		  		<td>未签退</td>
		  	</tr>
		  	<tr>
		  		<td>高铁器</td>
		  		<td>201626811123</td>
		  		<td>8:00-12:00</td>
		  		<td>无</td>
		  		<td>8:00</td>
		  		<td>未签退</td>
		  	</tr>
		  	<tr>
		  		<td>往里面</td>
		  		<td>201626810813</td>
		  		<td>8:00-12:00</td>
		  		<td>无</td>
		  		<td>未签到</td>
		  		<td>未签退</td>
		  	</tr>
		  </tbody>
		</table>
		<div id="container" style="width:100%;"></div>
	</div>
	<script src="../js/signInAct.js"></script>
</body>
</html>