<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>导入车票信息</title>
	<script>
		$(function(){
			var msg = "${msg}";
			if(msg!=""){
				alert(msg);
				//window.location = "${base}/ticket/list";
			}
			if(msg.substring(0,4)=="成功导入"){
				alert(msg);
				window.location = "${base}/ticket/list";
			}
			
			$("#subBtn").click(function(){
				if($("#file").val()==""){
					alert("文件不能为空");
				}
				else{
					$('#myModal').modal({
					 	keyboard: false,
					 	backdrop:'static' 
					});
					$(".form-horizontal").submit();
				}
			});
		});
	</script>
  </head>
<body>
  <div class="panel panel-success">
  	<div class="panel-heading h3" style="margin:0;">导入车票信息</div>
  	<div class="panel-body">
		<form class="form-horizontal" method="post" enctype="multipart/form-data" action="${base }/ticket/upload">
		  <div class="form-group">
		    <label for="file" class="col-md-2 control-label">车次</label>
		    <div class="col-md-5">
		      <input type="file" id="file" name="file">
		    </div>
		    <div class="col-md-5">
		    <button type="button" class="btn btn-default" id="subBtn">导入</button>
		    </div>
		  </div>
		  
		</form>
	</div>
  </div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="margin-top:160px;">
    <div class="modal-content">
     <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">正在导入...</h4>
      </div>
      <div class="modal-body">
        <div class="progress">
  <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
    <span class="sr-only">100% Complete</span>
  </div>
</div>
      </div>
    </div>
  </div>
</div>
  </body>
</html>
