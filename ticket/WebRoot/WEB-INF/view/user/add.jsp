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
					loginName:{
						required:true,
						remote: {
						    url: "${base}/user/checkLoginName",     //后台处理程序
						    type: "post",               //数据发送方式
						    dataType: "json",           //接受数据格式   
						    data: {                     //要传递的数据
						        username: function() {
						            return $("#loginName").val();
						        }
						    }
						}
					},
					realName:{
						required: true
					},
					password:{
						required: true,
						rangelength:[6,20]
					}
				},
				messages:{
					loginName:{
						required:"请输入登录名",
						remote:"登录名已存在"
					},
					realName:{
						required: "请输入真实姓名"
					},
					password:{
						required: "请输入登录密码",
						rangelength:"密码长度为6-20位"
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
					url: "${base}/user/save",
					type:"POST",
					data:$("#addForm").serialize(),
					success:function(date){
						if(date == "success"){
							alert("添加成功");
							window.location = "${base}/user/list";
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
  	<div class="panel-heading h3" style="margin:0;">添加操作员</div>
  	<div class="panel-body">
		<form class="form-horizontal" id="addForm" method="post">
		  <div class="form-group">
		    <label for="loginName" class="col-md-2 control-label">登录名</label>
		    <div class="col-md-5">
		      <input type="text" class="form-control" id="loginName" name="loginName" placeholder="登录名..">
		    </div>
		    <div class="col-md-5 msg"></div>
		  </div>
		  <div class="form-group">
		    <label for="realName" class="col-md-2 control-label">真实姓名</label>
		    <div class="col-md-5">
		      <input type="text" class="form-control" id="realName" name="realName" placeholder="真实姓名..">
		    </div>
		    <div class="col-md-5 msg"></div>
		  </div>
		  <div class="form-group">
		    <label for="sex" class="col-md-2 control-label">性别</label>
		    <div class="col-md-5">
			    <label class="radio-inline">
				  <input type="radio" name="sex" id="sex1" value="1" checked> 男
				</label>
				<label class="radio-inline">
				  <input type="radio" name="sex" id="sex2" value="2"> 女
				</label>
			</div>
		  </div>
		  <div class="form-group">
		    <label for="password" class="col-md-2 control-label">密码</label>
		    <div class="col-md-5">
		      <input type="text" class="form-control" id="password" name="password" value="123456" placeholder="密码..">
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
