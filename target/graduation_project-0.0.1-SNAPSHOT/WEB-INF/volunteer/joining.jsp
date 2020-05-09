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
    .content h1{
        color:#579f23;
        line-height:70px;
    }
    .content p{
        font-size:14px;
        line-height:35px;
        display:inline-block;
        margin-left:20px;
    }
    .content img{
        width:400px;
        height:200px;
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
    input{
        display:inline-block;
        padding:7px 30px;
        background:#579f23;
        color:#fff;
        border:0;
        border-radius:5px;
        float:right;
    }
</style>

<body>
<form action="">
    <div class="document">
        <div class="header">
            <img src="img/timg.jpg">
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
        <div class="content" >
            <h1 style="text-align:center;">${activity.activityname}</h1>
            <hr/>
            <div>
                <p>活动时间：${activity.activitydate}&nbsp;&nbsp;${activity.activitystartdate}</p><br/>
                <p>活动地点：${activity.activitylocation}</p><br/>
                <p>需求人数：${activity.demand}人</p><br/>
                <p>
                    <pre>
                    活动内容：
                    ${activity.activityrequirement}
                    </pre>
                </p>
                <p>
                    <pre>
                    活动要求：
                    1.喜欢小学生
                    2.成绩优异，可以讲课
                    3.了解小学生的爱好习惯
                    4.服从安排
                    </pre>
                </p>
            </div>
            <hr/>
            <input type="button" value="参加">
        </div>
        <div class="footer">
            <img src="<%=request.getContextPath()%>/volunteer/img/bottom.png">
        </div>
    </div>
</form>
</body>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script>
    $("input").click(function(){
        alert("报名提交成功！！！");
        $("input").css('display','none');
    })
</script>
</html>