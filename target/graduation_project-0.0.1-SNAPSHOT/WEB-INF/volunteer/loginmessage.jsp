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
<form action="<%=request.getContextPath()%>/volunteer/loginmessageInfo" method="post" onsubmit="return checkInfo();">
    <div class="document">
        <div class="nav">
            <ul>
                <a href="<%=request.getContextPath()%>/volunteer/index"><li>网页首页</li></a>&nbsp;&nbsp;|
                <a href="<%=request.getContextPath()%>/volunteer/presenceList"><li>风采展览</li></a>&nbsp;&nbsp;|
                <a href="<%=request.getContextPath()%>/volunteer/join"><li>参加报名</li></a>&nbsp;&nbsp;|
                <a href="<%=request.getContextPath()%>/volunteer/publish"><li>发表反馈</li></a>
            </ul>
            <div class="navtext">
                <a href="<%=request.getContextPath()%>/volunteer/login"><li>登录/注册</li></a>
            </div>
        </div>
        <div class="login">
            <div class="input" style="margin-top:40px;"><label>账号：</label><input class="text" type="text" name="account"/></div>
            <div class="input"><label>密码：</label><input class="password" type="text" name="password"/></div>
            <div class="input"><label>姓名：</label><input class="text" type="text" name="userName"/></div>
            <div class="input"><label>性别：</label><input class="text" type="text" name="sex"/></div>
            <div class="input"><label>年龄：</label><input class="text" type="text" name="age"/></div>
            <div class="input"><label>身份证号：</label><input class="text" type="text" name="card"/></div>
            <div class="input"><label>手机号：</label><input class="text" name="userTel"/></div>
            <div class="button">
                <input class="submit" type="submit" value="提交">
            </div>
        </div>
    </div>
</form>
</body>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
    function checkInfo(){
        var account = $("input[name='account']").val();
        var password = $("input[name='password']").val();
        var userName = $("input[name='userName']").val();
        var sex = $("input[name='sex']").val();
        var age = $("input[name='age']").val();
        var card = $("input[name='card']").val();
        var userTel = $("input[name='userTel']").val();
        if(account==""||password==""){
            alert("用户名或密码不能为空！");
            return false;
        }
        if(userName==""){
            alert("姓名不能为空！");
            return false;
        }
        if(sex==""){
            alert("性别不能为空！");
            return false;
        }
        if(age==""){
            alert("年龄不能为空！");
            return false;
        }
        if(card==""){
            alert("身份证号不能为空！");
            return false;
        }
        if(userTel==""){
            alert("手机号不能为空！");
            return false;
        }
    }
    
</script>
</html>