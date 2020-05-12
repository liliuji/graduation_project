
$(document).ready(function(){
	$("#sub").on("click",function(){
		if($("#uploadImage").val()==""){
			alert("请选择图片");
			return;
		}
        if(typeof FileReader != 'undefined'){
            var file = $('#uploadImage')[0].files[0];
            if((file.type).indexOf("image/")==-1){
                alert("请上传图片!");
                file.delete();
                return false;
            }
        }else{
            var fileName=$('#uploadImage').value;
            var suffixIndex=fileName.lastIndexOf(".");
            var suffix=fileName.substring(suffixIndex+1).toUpperCase();
            if(suffix!="BMP"&&suffix!="JPG"&&suffix!="JPEG"&&suffix!="PNG"&&suffix!="GIF"){
                alert("请上传图片（格式BMP、JPG、JPEG、PNG、GIF等）!");
                fileName.delete();
                return false;
            }
        }
		var flag=0;
		var presenceName=$("#presenceName").val();
		var presenceContent=$("#presenceContent").val();
		var activityName=$("#activityName").val();
		var volunteeraccount=$("#volunteeraccount").val();
		if(presenceName==""||presenceContent==""||activityName==""||volunteeraccount==""){
			alert("输入框不能为空");
			return;
		}
		var formData = new FormData();
		formData.append('file', $('#uploadImage')[0].files[0]);
        $.ajax({
    		url:"/graduation_project/uploadImage",
    		type:"post",
    		data:formData,
    		contentType: false,
            processData: false,
            dataType:"text",
    		success:function(data){
    			var imgPath = JSON.parse(data).imgPath;
    			$.ajax({
    	    		url:"/graduation_project/savePresence",
    	    		type:"post",
    	    		cache:false,
    	    		data:{
    	    			'presencename':presenceName,
						'presencecontent':presenceContent,
						'activename':activityName,
						'volunteeraccount':volunteeraccount,
						"presenceimgpath":imgPath
    	    		},
    	            dataType:"text",
    	    		success:function(data){
    	    			if(data==1){
    	    				alert("风采添加成功！");
							window.location.href="/graduation_project/showPresence";
						}else{
							alert("风采添加失败！");
						}
    	    		},
    	    		error:function(xhr){
    	    			alert('出错。。\n'+xhr.responseText);
    	    		}
    	    	});
    		},
    		error:function(xhr){
    			alert('出错。。\n'+xhr.responseText);
    		}
    	});
  });
	

  $("#clear").on("click",function(){
	  $("input").val("");
	  $("textarea").val("");
  });
});