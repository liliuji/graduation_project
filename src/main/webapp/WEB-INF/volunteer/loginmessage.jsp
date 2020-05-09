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
    .document{
        min-width: 1100px;
        min-height: 500px;
        background: url(img/bg.jpg) no-repeat center;
        position: fixed;
        left: 0;
        right: 0;
        bottom: 0;
        top: 0;
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
    .login{
        width:350px;
        height:400px;
        border:3px solid #999;
        margin:12% auto;
        border-radius:8px;
        background:#f1f1f1;
    }
    .input{
        width:100%;
        height:50px;
        text-align:center;
    }
    label{
        display:inline-block;
    }
    .text,.password{
        display:inline-block;
        line-height:45px;
        width:180px;
        height:30px;
        background:#fff;
        border:0.5px solid #999;
        border-radius:8px;
    }
    .button{
        width:100%;
        height:50px;
        text-align:center;
    }
    .button input{
        display:inline-block;
        padding:5px 10px;
        background:#579f23;
        color:#fff;
        border:0;
        border-radius:5px;
    }
</style>
<body>
    <div class="document">
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
        <div class="login">
            <div class="input" style="margin-top:40px;"><label>手机号：</label><input class="text" type="text"></input></div>
            <div class="input"><label>密码：</label><input class="password" type="password"></input></div>
            <div class="input"><label>姓名：</label><input class="text" type="text"></input></div>
            <div class="input"><label>性别：</label><input class="text" type="text"></input></div>
            <div class="input"><label>年龄：</label><input class="text" type="text"></input></div>
            <div class="input"><label>身份证号：</label><input class="text" type="text"></input></div>
            <div class="button">
                <a href="login.jsp"><input class="submit" type="button" value="提交"></a>
            </div>
        </div>
    </div>
</body>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script>
    $(".submit").click(function(){
        alert("注册成功！！！！");         
    })
    
</script>
</html>