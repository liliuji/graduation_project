
$(document).ready(function(){
    $("tbody").on("click",".look",function(){
        var noticetitle = $(this).parents("tr").children("td").eq(1).html();
        var noticecontent = $(this).parents("tr").children("td").eq(2).html();
        var noticeId = $(this).parents("tr").children("td").eq(4).html();
        $(".modal-title").text(noticetitle);
        var $str="<table class='table table-bordered table-hover'>"+
            "<tbody id='noticeInfo'>"+
            "<tr>"+
            "<td>公告标题</td>"+
            "<td><input type='text' class='form-control' name='noticeTitle' value= '"+noticetitle+"'></td>"+
            "</tr><tr>"+
            "<td>公告内容</td>"+
            "<td><textarea class='form-control' name='noticecontent' value= '"+noticecontent+"'>"+noticecontent+"</textarea></td>"+
            "</tr><tr>"+
            "<td style='display: none'>"+noticeId+"</td>"+
            "</tr></tbody>"+
            " </table>";
        $(".modal-body").html($str);
        $("#lookActivity").modal({backdrop: 'static', keyboard: false});
    });

    $("tbody").on("click",".delete",function(){
        var $tr=$(this).parents("tr");
        $.ajax({
            url:"/graduation_project/deleteNotice",
            type:"post",
            cache:false,
            data:{
                'noticeId':$(this).parents("tr").children("td").eq(4).html()
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
        var noticetitle = $("#noticeInfo").children("tr").eq(0).children("td").eq(1).find("input").val();
        var noticecontent = $("#noticeInfo").children("tr").eq(1).children("td").eq(1).find("textarea").val();
        var noticeId = $("#noticeInfo").children("tr").eq(2).children("td").eq(0).html();
        $.ajax({
            url:"/graduation_project/updateNotice",
            type:"post",
            cache:false,
            data:{
                'noticeTitle':noticetitle,
                'noticeContent':noticecontent,
                'noticeId':noticeId
            },
            success:function(data){
                if(data=="0"){
                    alert("修改失败");
                }else{
                    alert("修改成功");
                    $("#close").click();
                    window.location.href="/graduation_project/showNotice";
                }
            }
        });
    });
});