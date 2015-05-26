<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>售票详情</title>
  </head>
<body>
  <div class="panel panel-success">
  <div class="panel-heading h3" style="margin:0;">售票详情</div>
  <div class="panel-body">
	  
	    <table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>ID</th>
					<th>操作员</th>
					<th>销售时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pagination.objLists }" var="sale" varStatus="vs">
					<tr>
						<td>${vs.count+pagination.pageSize*(pagination.pageNo-1) }</td>
						<td>${sale.id}</td>
						<td>${sale.userName}(ID:${sale.userId })</td>
						<td><fmt:formatDate type="both" value="${sale.saleTime}" pattern="yyyy-MM-dd HH:mm" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:import url="../common/page.jsp"></c:import>
		</div>
	</div>
  </body>
</html>
