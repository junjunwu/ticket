<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加车票信息</title>
    <link rel="stylesheet" type="text/css" href="${base }/res/js/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css"/ >
    <script src="${base }/res/js/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <script src="${base }/res/js/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="${base }/res/js/jquery.validate.min.js"></script>
	<script>
		$(function(){
		
			//日期控件 	
			$('.form_time').datetimepicker({
		        language:  'zh-CN',
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 1,
				minView: 0,
				maxView: 1,
				forceParse: 0,
				minuteStep:1
		    });
		    
		    $('.form_date').datetimepicker({
		        language:  'zh-CN',
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0,
				initialDate:new Date()
		    });
		    
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
					coachNum:{
						required:true
					},
					terminus:{
						required: true
					},
					price:{
						required: true,
						number:true
					},
					totalNum:{
						required: true,
						digits:true
					},
					departureDate:{
						required: true
					},
					departureTime:{
						required: true
					}
				},
				messages:{
					coachNum:{
						required:"请输入车次"
					},
					terminus:{
						required: "请输入终点站"
					},
					price:{
						required: "请输入票价",
						number:"请输入正确格式的价格"
					},
					totalNum:{
						required: "请输入可售票数",
						digits:"请输入整数型数据"
					},
					departureDate:{
						required: "请输入发车日期"
					},
					departureTime:{
						required: "请输入发车时间"
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
					url: "${base}/ticket/save",
					type:"POST",
					data:$("#addForm").serialize(),
					success:function(date){
						if(date == "success"){
							alert("保存车票信息成功");
							window.location = "${base}/ticket/list";
						}else{
							alert("保存车票信息失败");
						}
					}
				});	
        	}
			
		});
	</script>
  </head>
<body>
  <div class="panel panel-success">
  	<div class="panel-heading h3" style="margin:0;">添加车票信息</div>
  	<div class="panel-body">
		<form class="form-horizontal" id="addForm">
		  <input type="hidden" value="${ticket.id }" name="id">
		  <div class="form-group">
		    <label for="coachNum" class="col-md-2 control-label">车次</label>
		    <div class="col-md-5">
		      <input type="text" class="form-control" id="coachNum" name="coachNum" value="${ticket.coachNum }" placeholder="车次..">
		    </div>
		    <div class="col-md-5 msg"></div>
		  </div>
		  <div class="form-group">
		    <label for="terminus" class="col-md-2 control-label">终点站</label>
		    <div class="col-md-5">
		      <input type="text" class="form-control" id="terminus" name="terminus" value="${ticket.terminus }" placeholder="终点站..">
		    </div>
		    <div class="col-md-5 msg"></div>
		  </div>
		  <div class="form-group">
                <label for="departureDate" class="col-md-2 control-label">发车日期</label>
                <div style="float:left;padding-left:15px;padding-right:15px;" class="input-group date form_date col-md-5" data-date="" data-date-format="yyyy/mm/dd" data-link-field="departureDate" data-link-format="yyyy/mm/dd">
                    <input class="form-control" size="16" type="text" value="${empty ticket.date?today:ticket.date }" readonly id="departureDate" name="departureDate">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
                <div class="col-md-5 msg"></div>
            </div>
		  <div class="form-group">
                <label for="departureTime" class="col-md-2 control-label">发车时间</label>
                <div style="float:left;padding-left:15px;padding-right:15px;" class="input-group date form_time col-md-5" data-date="" data-date-format="hh:ii" data-link-field="departureTime" data-link-format="hh:ii">
                    <input class="form-control" size="16" type="text" value="${ticket.time }" readonly id="departureTime" name="departureTime">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                </div>
                <div class="col-md-5 msg"></div>
            </div>
		  <div class="form-group">
		    <label for="price" class="col-md-2 control-label">票价</label>
		    <div class="col-md-5">
		      <input type="number" class="form-control" id="price" name="price" value="${ticket.price }" placeholder="票价..">
		    </div>
		    <div class="col-md-5 msg"></div>
		  </div>
		  <div class="form-group">
		    <label for="totalNum" class="col-md-2 control-label">可售</label>
		    <div class="col-md-5">
		      <input type="text" class="form-control" id="totalNum" name="totalNum"  value="${ticket.totalNum }" placeholder="可售..">
		    </div>
		    <div class="col-md-5 msg"></div>
		  </div>
		  <div class="form-group">
		    <label for="coachType" class="col-md-2 control-label">车型</label>
		    <div class="col-md-5">
		      <c:if test="${empty ticket.coachType }">
			      	<label class="radio-inline">
					  <input type="radio" name="coachType" id="coachType1" value="大型卧铺" checked> 大型卧铺
					</label>
					<label class="radio-inline">
					  <input type="radio" name="coachType" id="coachType2" value="大型座席"> 大型座席
					</label>
				</c:if>
				<c:if test="${not empty ticket.coachType }">
			      	<label class="radio-inline">
					  <input type="radio" name="coachType" id="coachType1" value="大型卧铺" <c:if test="${ticket.coachType=='大型卧铺' }">checked</c:if>> 大型卧铺
					</label>
					<label class="radio-inline">
					  <input type="radio" name="coachType" id="coachType2" value="大型座席" <c:if test="${ticket.coachType=='大型座席' }">checked</c:if>> 大型座席
					</label>
				</c:if>
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
