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
        text-align:center;
        background:#f1f1f1;
    }
    p{
        padding:8% 0 3% 0;
        color:#579f23;
        font-size:25px;

    }
    textarea{
        width:400px;
        height:250px;
        border-radius:8px;
        border:2px solid #999;
    }
    input{
        display:inline-block;
        padding:5px 10px;
        background:#579f23;
        color:#fff;
        border:0;
        border-radius:5px;
        margin-top:20px;
        line-height:30px;
        font-size:14px;    
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
<form action="<%=request.getContextPath()%>/volunteer/savePublish" method="post" onsubmit="return checkPublish();">
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
        <div class="content">
            <p>请输入你想要反馈的言论：</p>
            <textarea id="feedbackcontent" name="feedbackcontent"></textarea>
            <br/>
            <input class="submit" type="submit" value="提交"/>
            <input class="none" type="button" value="清空"/>
        </div>
        <div class="footer">
            <img src="<%=request.getContextPath()%>/volunteer/img/bottom.png">
        </div>
    </div>
</form>
</body>

<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        var volunteerAccount = '${account}';
        if(volunteerAccount!=null&&volunteerAccount!=''){
            $("#loginOrRegister").css("display","none");
            $(".navtext").append("<li>"+volunteerAccount+",你好</li>");
        }

        var savePublishFlag = '${savePublishFlag}';
        if(savePublishFlag==1){
            alert("发表反馈成功!");
        }
    });

    $(".none").click(function(){
        $("textarea").val("");
    });

    function checkPublish(){
        if($("textarea").val()==""){
            alert("内容不能为空！");
            return false;
        }
    }
</script>
</html>