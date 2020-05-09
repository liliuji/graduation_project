<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>志愿者服务平台</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
  <!--  <link rel='stylesheet' type='text/css' media='screen' href='main.css'>
    <script src='main.js'></script>-->
</head>
<style>
    *{
        margin:0;
        padding:0;
    }
    .header{
        width:100%;
        height:200px;
        background:lightblue;
    }
    .header img{
        width:100%;
        height:100%;
        display:block;
    }
    .nav{
        width:100%;
        height:40px;
        background:#579f23;
        color:#fff;
    }
    .nav ul{
        width:740px;
        height:40px;
        display:inline-block;
        margin-left:26%;
    }
    .nav ul li{
        display:inline-block;
        line-height:40px;
        width:150px;
        text-align:center;
        list-style:none;
    }
    .nav ul li:hover{
        background:lightgreen;
        color:#000;
    }
    a{
        text-decoration:none;
        color:#fff;
    }
    a:active{
        color:#000;
    }
    .navtext{
        width:150px;
        height:40px;
        line-height:40px;
        text-align:center;
        display:inline-block;
        margin-left:30px;
        color:#fff;
    }
    .navtext li{
        list-style:none;
    }
    .navtext ul{
        width:100%;
        height:100%;
    }
    .navtext ul li{
        display:none;
        list-style:none;
    }
    .content{
        width:1000px;
        height:540px;
        margin:0 auto;
    }
    .noticetitle{
        width:100%;
        height:40px;
        line-height:40px;
        border-bottom:1px solid #999;
        margin-top:20px;
    }
    .noticetitle span{
        font-size:20px;
        color:#438513;
    }
    .notice{
        width:100%;
        height:500px;
        
    }
    .notice ul{
        width:100%;
        height:100%;
    }
    .notice ul li{
        width:100%;
        height:40px;
        line-height:40px;
        border-bottom:1px dotted #999;
        color:#438513;
        list-style:none;
    }
    .notice ul li div{
        width:5px;
        height:5px;
        border-radius:5px;
        background:#438513;
        display:inline-block;
        margin-right:15px;
    }
    .footer{
        width:100%;
        height:210px;
    }
    .footer img{
        width:100%;
        height:100%;
        display:block;
    }
</style>

<body>
<form action="<%=request.getContextPath()%>/feedbackInfo" method="post" id="formId" lass="form-horizontal" role="form">
    <div class="document">
        <div class="header">
            <img src="<%=request.getContextPath()%>/volunteer/img/timg.jpg">
        </div>
        <div class="nav">
            <ul>
                <a href="<%=request.getContextPath()%>/index"><li>网页首页</li></a>&nbsp;&nbsp;|
                <a href="<%=request.getContextPath()%>/presenceList"><li>风采展览</li></a>&nbsp;&nbsp;|
                <a href="<%=request.getContextPath()%>/join"><li>参加报名</li></a>&nbsp;&nbsp;|
                <a href="<%=request.getContextPath()%>/publish"><li>发表反馈</li></a>
            </ul>
            <div class="navtext">
                <a href="<%=request.getContextPath()%>/volunteerLogin"><li>登录/注册</li></a>
            </div>
        </div>
       <div class="content">
            <div class="noticetitle"><span>风采</span>展览</div>
            <div class="notice">
                <ul>
                    <c:forEach items="${presenceList}" var= "presence"  varStatus="status">
                        <a href="<%=request.getContextPath()%>/presenceInfo?presenceId=${presence.presenceid}">
                            <li>${presence.presencename}</li>
                        </a>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="footer">
            <img src="<%=request.getContextPath()%>/volunteer/img/bottom.png">
        </div>
    </div>
</form>
</body>

<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script>
   /* var attr=['山区扶贫志愿活动展示','养老院照顾老人志愿活动展示','新春文化节志愿活动展示','新春文化节志愿者','年长志愿者关爱活动','志愿服务培训活动','平安希望小学支教行动志愿活动','帮助环卫工清洁志愿服务活动','共享单车文明停放志愿服务','志愿者征集活动','关爱流浪宠物志愿活动','“弘扬志愿精神 助建世界名城”志愿服务活动',];
    for(var i=0;i<attr.length;i++){
        $(".notice").find("ul").append('<a href="showing.jsp"><li><div></div>'+attr[i]+'</li></a>');
    }*/
</script>
</html>