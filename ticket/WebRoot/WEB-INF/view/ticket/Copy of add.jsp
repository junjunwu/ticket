<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加车票信息</title>
    <link rel="stylesheet" type="text/css" href="${base }/res/js/DateTimePicket/jquery.datetimepicker.css"/ >
    <script src="${base }/res/js/DateTimePicket/jquery.datetimepicker.js"></script>
	<script>
		$(function(){
		
			//日期控件 	
			$("#departureTime").datetimepicker({
				lang:'ch',
				timepicker:true,
				format:'Y-m-d H:i',
				onChangeDateTime:function(dp,$input){
				    if($("#beginDate").val()!='' && $("#endDate").val()!=''){
						$("#timeFilter a").removeClass("on");
						$("#timeInterval").val("");
					}
				},
				allowBlank:true
			});
			
			$("#subBtn").click(function(){
        		$.ajax({
					url: "${base}/ticket/save",
					type:"POST",
					data:$("#addNoticeForm").serialize(),
					success:function(date){
						if(date == "success"){
							alert("添加成功");
							window.location = "${base}/ticket/list";
						}else{
							alert("添加失败");
						}
					}
				});	
        	});
		
		});
	</script>
  </head>
<body>
  <div class="panel panel-success">
  	<div class="panel-heading h3" style="margin:0;">添加车票信息</div>
  	<div class="panel-body">
		<form class="form-horizontal" id="addNoticeForm">
		  <div class="form-group">
		    <label for="coachNum" class="col-sm-2 control-label">车次</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="coachNum" name="coachNum" placeholder="车次..">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="terminus" class="col-sm-2 control-label">终点站</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="terminus" name="terminus" placeholder="终点站..">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="departureTime" class="col-sm-2 control-label">发车时间</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="departureTime" name="departureTime" placeholder="发车时间..">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="price" class="col-sm-2 control-label">票价</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="price" name="price" placeholder="票价..">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="totalNum" class="col-sm-2 control-label">可售</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="totalNum" name="totalNum" placeholder="可售..">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="saleNum" class="col-sm-2 control-label">已售</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="saleNum" name="saleNum" placeholder="已售..">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="coachType" class="col-sm-2 control-label">车型</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="coachType" name="coachType" placeholder="车型..">
		    </div>
		  </div>
		  
		  
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="button" class="btn btn-default" id="subBtn">确定</button>
		    </div>
		  </div>
		</form>
	</div>
  </div>
  </body>
</html>
