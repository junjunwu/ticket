<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${base }/res/js/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css"/ >
    <script src="${base }/res/js/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <script src="${base }/res/js/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <title>班次明细</title>
    <script type="text/javascript">
    	$(function(){
    		$(".form_datetime").datetimepicker({
    			language:  'zh-CN',
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0
    		});
    		
    		$(".saleTicket").click(function(){
    			if(window.confirm("是否出售并打印车票？")){
	    			var ticketId = $(this).attr("ticketId");
	    			$.ajax({
						url: "${base}/ticket/sale",
						type:"POST",
						data:{ticketId:ticketId},
						success:function(date){
							if(date.status == "success"){
								//alert("售票成功");
								//$("#saleNum").html(parseInt($("#saleNum").attr("value"))+1);
								var url = "${base}/ticket/print?saleId="+date.saleId;
	    						window.open(url);
	    						setTimeout("$('#searchForm').submit();", 1500);
								//$("#searchForm").submit();
							}else if(date.status=="param_error"){
								alert("参数错误，班次不存在");
							}else if(date.status=="ticket_not_exist"){
								alert("参数错误，班次不存在");
							}else if(date.status=="ticket_sold_out"){
								alert("票已售罄");
								$("#searchForm").submit();
							}else{
								alert("系统出错了");
							}
						}
					});	
				}
    		});
    		
    		$(".exportBtn").click(function(){
    			var url = "${base}/ticket/export?"+$("#searchForm").serialize();
   				window.location = url;
    		});
    	});
    	function toPage(toPage){
    		$("#pageNo").val(toPage);
    		$("#searchForm").submit();
    	}
    </script>
  </head>
<body>
  <div class="panel panel-success">
  <div class="panel-heading h3" style="margin:0;">班次明细</div>
  <div class="panel-body">
	  <div class="well search-well well-sm">
	        <form class="form-inline text-center" id="searchForm" action="${base }/ticket/list" method="post">
			  <input type="hidden" id="pageNo" value="${query.pageNo }" name="pageNo">
			  <div class="form-group">
			    <label for="title">班次</label>
			    <input type="text" value="${query.ticket.coachNum }" class="form-control" id="coachNum" name="ticket.coachNum">
			  </div>
			  <div class="form-group">
			    <label for="title">终点站</label>
			    <input type="text" value="${query.ticket.terminus }" class="form-control" id="terminus" name="ticket.terminus">
			  </div>
			  <div class="form-group">
			    <label for="realName">发车日期</label>
			    <input size="16" type="text" value="${query.beginDate }" name="beginDate" readonly class="form_datetime form-control" data-date-format="yyyy-mm-dd">-
			    <input size="16" type="text" value="${query.endDate }" name="endDate" readonly class="form_datetime form-control" data-date-format="yyyy-mm-dd">
			  </div>
			  <div class="checkbox">
			    <label>
			      <input type="checkbox" name="availableOnly" value="1" <c:if test="${query.availableOnly==1 }">checked</c:if>> 只显示未过期车票
			    </label>
			  </div>
			  <button type="submit" class="btn btn btn-primary">查找</button>
			  <button type="button" class="btn btn btn-primary exportBtn">导出</button>
			</form>
	    </div>
	    <table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>ID</th>
					<th>班次</th>
					<th>终点站</th>
					<th>发车时间</th>
					<th>票价</th>
					<th>可售</th>
					<th>已售</th>
					<th>车型</th>
					<th>录入时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pagination.objLists }" var="ticket" varStatus="vs">
					<tr>
						<td>${vs.count+pagination.pageSize*(pagination.pageNo-1) }</td>
						<td>${ticket.id}</td>
						<td>${ticket.coachNum}</td>
						<td>${ticket.terminus}</td>
						<td><fmt:formatDate type="both" value="${ticket.departureTime}" pattern="yyyy-MM-dd HH:mm" /></td>
						<td>${ticket.price}</td>
						<td>${ticket.totalNum}</td>
						<td><a href="${base }/ticket/saleList?sale.ticketId=${ticket.id}" title="查看销售详情" id="saleNum" value="${ticket.saleNum}">${ticket.saleNum}</a></td>
						<td>${ticket.coachType}</td>
						<td><fmt:formatDate type="both" value="${ticket.createTime}"/></td>
						<%-- <td><button type="button" class="btn btn-default btn-xs btn-info saleTicket" ticketId="${ticket.id }" disabled="disabled">售票</button></td> --%>
						<td>
							<button type="button" class="btn btn-default btn-xs btn-info saleTicket" ticketId="${ticket.id }">售票</button>
							<a role="button" class="btn btn-default btn-xs btn-info" href="${base }/ticket/add?ticketId=${ticket.id }">修改</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:import url="../common/page.jsp"></c:import>
		</div>
	</div>
  </body>
</html>
