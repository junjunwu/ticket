<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加操作员</title>
    <script src="${base }/res/js/jquery.validate.min.js"></script>
	<script>
		$(function(){
			$("#addForm").validate({
		 		errorElement : 'span',  
            	errorClass : 'help-block',  
            	focusInvalid : false, 
	            errorPlacement : function(error, element) {  
	                element.parent().parent().find(".msg").html(error);  
	            }, 
				submitHandler : function(form) {
					subForm();
				},
				rules:{
					prePwd:{
						required:true,
						rangelength:[6,20]
					},
					newPwd:{
						required: true,
						rangelength:[6,20]
					},
					surePwd:{
						required: true,
						rangelength:[6,20],
						equalTo: "#newPwd"
					}
				},
				messages:{
					prePwd:{
						required:"请输入旧密码",
						rangelength:"密码长度为6-20位"
					},
					newPwd:{
						required: "请输入新密码",
						rangelength:"密码长度为6-20位"
					},
					surePwd:{
						required: "请输入登录密码",
						rangelength:"密码长度为6-20位",
						equalTo: "必须与新密码一致"
					}
				},
				highlight : function(element) {  
	                $(element).closest('.form-group').addClass('has-error');  
	            },  
	  
	            success : function(label) {  
	                label.closest('.form-group').removeClass('has-error');  
	                label.remove();  
	            } 
				
		 	});
			
			function subForm(){
        		$.ajax({
					url: "${base}/user/updatePwd",
					type:"POST",
					data:$("#addForm").serialize(),
					success:function(date){
						if(date == "success"){
							alert("修改成功");
							window.location = "${base}/user/list";
						}else if(date == "prepwd_error"){
							alert("旧密码输入错误");
						}else{
							alert("添加失败");
						}
					}
				});	
        	}
		
		});
	</script>
  </head>
<body>
  <div class="panel panel-success">
  	<div class="panel-heading h3" style="margin:0;">修改密码</div>
  	<div class="panel-body">
		<form class="form-horizontal" method="post" id="addForm">
		  <input type="hidden" value="${user.id }" name="userId">
		  <div class="form-group">
		    <label for="prePwd" class="col-md-2 control-label">旧密码</label>
		    <div class="col-md-5">
		      <input type="password" class="form-control" id="prePwd" name="prePwd">
		    </div>
		    <div class="col-md-5 msg"></div>
		  </div>
		  <div class="form-group">
		    <label for="newPwd" class="col-md-2 control-label">新密码</label>
		    <div class="col-md-5">
		      <input type="password" class="form-control" id="newPwd" name="newPwd">
		    </div>
		    <div class="col-md-5 msg"></div>
		  </div>
		  <div class="form-group">
		    <label for="surePwd" class="col-md-2 control-label">确认密码</label>
		    <div class="col-md-5">
		      <input type="password" class="form-control" id="surePwd" name="surePwd">
		    </div>
		    <div class="col-md-5 msg"></div>
		  </div>
		  
		  <div class="form-group">
		    <div class="col-md-offset-2 col-md-5">
		      <button type="submit" class="btn btn-default" id="subBtn">确定</button>
		    </div>
		  </div>
		</form>
	</div>
  </div>
  </body>
</html>
