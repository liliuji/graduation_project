var height=$(window).height();
var width=$(window).width();
$(document).ready(function(){
	$("tbody").on("click",".look",function(){
		$.ajax({
    		url:"/graduation_project/showActivity",
    		type:"post",
    		cache:false,
    		data:{
    			'activityId':$(this).parents("tr").children("td").eq(5).html()
    		},
    		success:function(data){
    			var jsonArr=new Array();
    			//$("html").html(data);
    			var info=data.activity;
    			$(".modal-title").text(info.activityname);
    			var $str="<table class='table table-bordered table-hover'>"+
    						"<tbody>"+
    							"<input type='hidden' id='hidAdate' value='"+info.activitydate+"'>"+
    							"<input type='hidden' id='hidAname' value='"+info.activityname+"'>"+
    							"<tr>"+
    								"<td>活动时间</td>"+
    								"<td>"+info.activitydate+"</td>"+
    							"</tr><tr>"+
    								"<td>活动地址</td>"+
    								"<td>"+info.activitylocation+"</td>"+
        						/*"</tr><tr>"+
									"<td>报名截止时间</td>"+
									"<td>"+info.deadline+"</td>"+*/
								"</tr><tr>"+
								    "<td>活动时间段</td>"+
									"<td> 开始时间："+info.activitystartdate+" 结束时间："+info.activityenddate+"</td>"+
								"</tr><tr>"+
									"<td>需求人数</td>"+
									"<td>"+info.demand+"</td>"+
    		        "</tr><tr  rowspan='4'><td>活动要求</td>"+
    				"<td style='overflow:auto'>"+info.activityrequirement+"</td></tr>"+
    				"<tr><td>发布人</td>"+
    				"<td>"+info.byUserName+"</td></tr>"+
    				"<tr><td>发布时间</td>"+
    				"<td>"+info.releasetime+"</td></tr>"+
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
    		url:"/graduation_project/deleteActivity",
    		type:"post",
    		cache:false,
    		data:{
				'activityId':$(this).parents("tr").children("td").eq(5).html()
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
	
	$("#lookActivity").on("click","#export",function(){
		var $str="<iframe src='/volunteer/excelJobState?info.Ano="+$(this).parents(".modal-content").find("#hidAno").val()+"&fileName="+$(this).parents(".modal-content").find("#hidAname").val()+"_"+$(this).parents(".modal-content").find("#hidAdate").val()+"' style='display:none;'></iframe>";
		$(".modal-body").append($str);
	});

});