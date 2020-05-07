
$(document).ready(function(){
    $("tbody").on("click",".look",function(){
        var presencename = $(this).parents("tr").children("td").eq(1).html();
        var presencecontent = $(this).parents("tr").children("td").eq(2).html();
        var activename = $(this).parents("tr").children("td").eq(3).html();
        var volunteername = $(this).parents("tr").children("td").eq(4).html();
        var presenceid = $(this).parents("tr").children("td").eq(6).html();
        $(".modal-title").text(presencename);
        var $str="<table class='table table-bordered table-hover'>"+
            "<tbody id='presenceInfo'>"+
            "<tr>"+
            "<td>风采名称</td>"+
            "<td><input type='text' class='form-control' name='presenceName' value= '"+presencename+"'></td>"+
            "</tr><tr>"+
            "<td>风采内容</td>"+
            "<td><textarea class='form-control' name='presencecontent' value= '"+presencecontent+"'>"+presencecontent+"</textarea></td>"+
            "</tr><tr>"+
            "<td>活动名称</td>"+
            "<td><input type='text' class='form-control' name='activename' value= '"+activename+"'></td>"+
            "</tr><tr>"+
            "<td>志愿者姓名</td>"+
            "<td><input type='text' class='form-control' name='volunteername' value= '"+volunteername+"'></td>"+
            "</tr><tr>"+
            "<td style='display: none'>"+presenceid+"</td>"+
            "</tr></tbody>"+
            " </table>";
        $(".modal-body").html($str);
        $("#lookActivity").modal({backdrop: 'static', keyboard: false});
    });

    $("tbody").on("click",".delete",function(){
        var $tr=$(this).parents("tr");
        $.ajax({
            url:"/graduation_project/deletePresence",
            type:"post",
            cache:false,
            data:{
                'presenceId': $(this).parents("tr").children("td").eq(6).html()
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
        var presenceName = $("#presenceInfo").children("tr").eq(0).children("td").eq(1).find("input").val();
        var presencecontent = $("#presenceInfo").children("tr").eq(1).children("td").eq(1).find("textarea").val();
        var activename = $("#presenceInfo").children("tr").eq(2).children("td").eq(1).find("input").val();
        var volunteername = $("#presenceInfo").children("tr").eq(3).children("td").eq(1).find("input").val();
        var presenceid = $("#presenceInfo").children("tr").eq(4).children("td").eq(0).html();
        $.ajax({
            url:"/graduation_project/updatePresence",
            type:"post",
            cache:false,
            data:{
                'presencename':presenceName,
                'presencecontent':presencecontent,
                'activename':activename,
                "volunteername":volunteername,
                "presenceid":presenceid
            },
            success:function(data){
                if(data=="0"){
                    alert("修改失败");
                }else{
                    alert("修改成功");
                    $("#close").click();
                    window.location.href="/graduation_project/showPresence";
                }
            }
        });
    });
});