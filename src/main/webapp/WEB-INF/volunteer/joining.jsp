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
<form action="<%=request.getContextPath()%>/volunteer/saveEnlist" method="post" onsubmit="return checkReason();">
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
                    ${activity.activitycontent}
                    </pre>
                </p>
                <p>
                    <pre>
                    活动要求：
                    ${activity.activityrequirement}
                    </pre>
                </p>
            </div>
            <hr/>
            <input type="hidden" value="${activity.activityid}" name="activityid">
            <input type="hidden" value="${volunteer.userId}" name="volunteerid">
            <label for="enlistreason" class="col-sm-2 control-label" id="enlistLable">报名原因：</label>
            <textarea rows="3" placeholder="请输入报名原因：" name="enlistreason" id="enlistreason"></textarea>
            <input type="submit" value="报名" id="enlist">
        </div>
        <div class="footer">
            <img src="<%=request.getContextPath()%>/volunteer/img/bottom.png">
        </div>
    </div>
</form>
</body>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
<script>
    function checkReason(){
        var volunteerAccount = '${account}';
        if(volunteerAccount==null||volunteerAccount==""){
            alert("请先登录系统！");
            window.location.href="<%=request.getContextPath()%>/volunteer/login";
            return false;
        }
        var enlistreason = $("#enlistreason").val();
        if(enlistreason==""||enlistreason==null){
            alert("请输入报名原因!");
            return false;
        }
    }

    $(document).ready(function(){
        var volunteerAccount = '${account}';
        if(volunteerAccount!=null&&volunteerAccount!=''){
            $("#loginOrRegister").css("display","none");
            $(".navtext").append("<li>"+volunteerAccount+",你好</li>");
        }
        var enliststatus = '${enliststatus}';
        if(enliststatus!=""&&enliststatus!=null){
            $('#enlistLable').css("display","none");
            $('#enlistreason').css("display","none");
            $('#enlist').css("display","none");
            $('.content').append("报名成功，状态："+enliststatus);
        }
        var enlistsFlag = '${enlistsFlag}';
        if(enlistsFlag!=null&&enlistsFlag==1){
            alert("报名成功！");
        }
    });
</script>
</html>