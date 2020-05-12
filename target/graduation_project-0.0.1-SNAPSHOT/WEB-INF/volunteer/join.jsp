<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>志愿者服务平台</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
   <%-- <link rel='stylesheet' type='text/css' media='screen' href='main.css'>
    <script src='main.js'></script>--%>
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
    .content div{
        width: 20%;
        height: 300px;
        border-radius: 5px;
        display: inline-block;
        float: left;
        background: #fff;
        margin: 10px 0 0 20px;
        border: 1px solid #000;
    }
    .content img{
        width: 85%;
        height: 62%;
        border: 1px solid #000;
        margin: 10px auto;
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
    <div class="document">
        <div class="header">
            <img src="<%=request.getContextPath()%>/volunteer/img/timg.jpg">
        </div>
        <div class="nav">
            <ul>
                <a href="<%=request.getContextPath()%>/volunteer/index"><li>网页首页</li></a>&nbsp;&nbsp;|
                <a href="<%=request.getContextPath()%>/volunteer/presenceList"><li>风采展览</li></a>&nbsp;&nbsp;|
                <a href="<%=request.getContextPath()%>/volunteer/join"><li>参加报名</li></a>&nbsp;&nbsp;|
                <a href="<%=request.getContextPath()%>/volunteer/publish"><li>发表反馈</li></a>
            </ul>
            <div class="navtext">
                <a href="<%=request.getContextPath()%>/volunteer/login" id="loginOrRegister"><li>登录/注册</li></a>
            </div>
        </div>
        <div style="width:1000px;height:40px;margin:0 auto;background:#f1f1f1;">
            <p style="margin-left:20px;line-height:40px;">欢迎参加志愿者服务平台的活动</p>
        </div>
        <div class="content" style="text-align:center;">
            <c:forEach items="${activitys}" var= "activity"  varStatus="status">
                <a href="<%=request.getContextPath()%>/volunteer/joining?activityId=${activity.activityid}">
                    <div>
                        <p style="color: #fff;background: #999;border-top-left-radius: 5px;border-top-right-radius: 5px;">${activity.activitydate}</p>
                        <img src="<%=request.getContextPath()%>/${activity.activityimgpath}" />
                        <p style="color: #999;text-align: left;margin: 0 15px 10px 15px;font-size: 13px;">${activity.activityname}</p>
                        <p style="color: #999;font-size: 11px;text-align: left;margin: 0 15px  10px 15px;">目标：${activity.demand}人  报名人数：${activity.volunteerCount}人</p>
                    </div>
                </a>
            </c:forEach>
        </div>
        <div class="footer">
            <img src="<%=request.getContextPath()%>/volunteer/img/bottom.png">
        </div>
    </div>
</body>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        var volunteerAccount = '${account}';
        if(volunteerAccount!=null&&volunteerAccount!=''){
            $("#loginOrRegister").css("display","none");
            $(".navtext").append("<li>"+volunteerAccount+",你好</li>");
        }
    });
</script>
</html>