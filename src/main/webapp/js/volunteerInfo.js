
$(document).ready(function(){
	$("tbody").on("click",".look",function(){
		$.ajax({
			url:"/graduation_project/showVolunteer",
			type:"post",
			cache:false,
			data:{
				'volunteerId':$(this).parents("tr").children("td").eq(6).html()
			},
			success:function(data){
				var info=data.volunteer;
				$(".modal-title").text(info.userName);
				var $str="<table class='table table-bordered table-hover'>"+
					"<tbody>"+
					"<tr>"+
					"<td>账号</td>"+
					"<td>"+info.account+"</td>"+
					"</tr><tr>"+
					"<td>密码</td>"+
					"<td>"+info.password+"</td>"+
					"</tr><tr>"+
					"<td>性别</td>"+
					"<td> "+info.sex+"</td>"+
					"</tr><tr>"+
					"<td>年龄</td>"+
					"<td>"+info.age+"</td>"+
					"</tr><tr><td>身份证号</td>"+
					"<td>"+info.card+"</td></tr>"+
					"<tr><td>联系方式</td>"+
					"<td>"+info.userTel+"</td></tr>"+
					"<tr><td>注册时间</td>"+
					"<td>"+info.createDate+"</td></tr>"+
					"</tbody>"+
					" </table>";
				$(".modal-body").html($str);
				$("#lookActivity").modal({backdrop: 'static', keyboard: false});
			}
		});
	});

	$("tbody").on("click",".delete",function(){
		var $tr=$(this).parents("tr");
		$.ajax({
			url:"/graduation_project/deleteVolunteer",
			type:"post",
			cache:false,
			data:{
				'volunteerId':$(this).parents("tr").children("td").eq(6).html()
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