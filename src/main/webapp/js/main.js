/**
 * 
 */
$("#admin").css("padding-top", "20px");
var height = $(window).height();
var iframetop = $("iframe").offset().top;
$("iframe").height(height - iframetop);
$(document).ready(function() {
  $("#addActivity").on("click", function() {
    $("iframe").attr("src", "/graduation_project/addActivity");
  });

  $("#myActivity").on("click", function() {
    $("iframe").attr("src", "/graduation_project/myActivity");
  });

  $("#addNotice").on("click", function() {
    $("iframe").attr("src", "/graduation_project/addNotice");
  });

  $("#showNotice").on("click", function() {
    $("iframe").attr("src", "/graduation_project/showNotice");
  });

  $("#volunteerInfo").on("click", function() {
    $("iframe").attr("src", "/graduation_project/volunteerInfo");
  });

  $("#addPresence").on("click", function() {
    $("iframe").attr("src", "/graduation_project/addPresence");
  });

  $("#showPresence").on("click", function() {
    $("iframe").attr("src", "/graduation_project/showPresence");
  });

  $("#showFeedback").on("click", function() {
    $("iframe").attr("src", "/graduation_project/showFeedback");
  });

  $("#showEnlist").on("click", function() {
    $("iframe").attr("src", "/graduation_project/showEnlist");
  });

  $("#changePassword").on("click", function() {
    $("#changePsw").modal({
      backdrop: 'static',
      keyboard: false
    });
  });

  $("#updatePassword").on("click", function() {
    var userName = $("#userName").val();
    var oldPassword = $("#oldPassword").val();
    var newPassword = $("#newPassword").val();
    var password = $("#password").val();

    if (!oldPassword || oldPassword.length == 0) {
      alert("旧密码不能为空!");
      return;
    }
    if (!newPassword || newPassword.length == 0) {
      alert("新密码不能为空!");
      return;
    }
    if (!password || password.length == 0) {
      alert("确认密码不能为空!");
      return;
    }
    if (newPassword != password) {
      alert("新密码和确认密码不一致!");
      return;
    }
    $.ajax({
      url: "/graduation_project/alterPsw",
      type: "post",
      cache: false,
      data: {
        'userName': userName,
        'oldPassword': oldPassword,
        'newPassword': newPassword
      },
      success: function(data) {
        var msg = data.msg;
        alert(msg);
        if(data.checkLoginFlag){
          $("#changePsw").modal("hide");
          $("#oldPassword").val('');
          $("#newPassword").val('');
          $("#password").val('');
          window.location.href = "/graduation_project"+data.url;
        }
      }
    });
  });

  $("#exit").on("click", function() {
    window.location.href = "/graduation_project/exit";
  });
});