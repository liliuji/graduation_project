
$(document).ready(function(){
    $("tbody").on("click",".look",function(){
        var feedbackcontent = $(this).parents("tr").children("td").eq(1).html();
        var feedbackdate = $(this).parents("tr").children("td").eq(2).html();
        var feedbackreply = $(this).parents("tr").children("td").eq(3).html();
        var replydate = $(this).parents("tr").children("td").eq(4).html();
        var feedbackid = $(this).parents("tr").children("td").eq(6).html();
       // $(".modal-title").text(presencename);
        var $str="<table class='table table-bordered table-hover'>"+
            "<tbody id='feedbackInfo'>"+
            "<tr>"+
            "<td>反馈内容</td>"+
            "<td>"+feedbackcontent+"</td>"+
            "</tr><tr>"+
            "<td>反馈日期</td>"+
            "<td>"+feedbackdate+"</td>"+
            "</tr><tr>"+
            "<td>回复</td>"+
            "<td><input type='text' class='form-control' name='feedbackreply' value= '"+feedbackreply+"'></td>"+
            "</tr><tr>"+
            "<td>回复日期</td>"+
            "<td>"+replydate+"</td>"+
            "</tr><tr>"+
            "<td style='display: none'>"+feedbackid+"</td>"+
            "</tr></tbody>"+
            " </table>";
        $(".modal-body").html($str);
        $("#lookActivity").modal({backdrop: 'static', keyboard: false});
    });

    $("tbody").on("click",".delete",function(){
        var $tr=$(this).parents("tr");
        $.ajax({
            url:"/graduation_project/deleteFeeback",
            type:"post",
            cache:false,
            data:{
                'feedbackid': $(this).parents("tr").children("td").eq(6).html()
            },
            success:function(data){
                if(data=="0"){
                    alert("删除失败");
                }else{
                    alert("删除成功");
                    $tr.remove();
                }
            }
        });
    });
    $("#update").click(function(){
        var feedbackreply = $("#feedbackInfo").children("tr").eq(2).children("td").eq(1).find("input").val();
        var feedbackid = $("#feedbackInfo").children("tr").eq(4).children("td").eq(0).html();
        $.ajax({
            url:"/graduation_project/updateFeedback",
            type:"post",
            cache:false,
            data:{
                'feedbackreply':feedbackreply,
                'feedbackid':feedbackid
            },
            success:function(data){
                if(data=="0"){
                    alert("回复失败");
                }else{
                    alert("回复成功");
                    $("#close").click();
                    window.location.href="/graduation_project/showFeedback";
                }
            }
        });
    });
});