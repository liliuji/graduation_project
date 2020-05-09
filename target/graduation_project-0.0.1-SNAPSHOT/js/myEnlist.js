
$(document).ready(function(){
    $("tbody").on("click",".delete",function(){
        var $tr=$(this).parents("tr");
        $.ajax({
            url:"/graduation_project/deleteEnlist",
            type:"post",
            cache:false,
            data:{
                'enlistid': $(this).parents("tr").children("td").eq(7).html()
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
});

function examine(object){
    var status = "";
    if(object.id=='approved'){
        status = "通过";
    }else if(object.id=='auditFailed'){
        status = "不通过";
    }
    $.ajax({
        url:"/graduation_project/updateEnlistStatus",
        type:"post",
        cache:false,
        data:{
            "enliststatus":status,
            'enlistid': $(object).parents("tr").children("td").eq(7).html()
        },
        success:function(data){
            if(data=="0"){
                alert("审核失败");
            }else{
                alert("审核成功");
            }
            window.location.href="/graduation_project/showEnlist";
        }
    });
}