<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set value="${pagination.begin }" var="begin"></c:set>
<c:set value="${pagination.end }" var="end"></c:set>
<nav class="text-center">
	<%-- <label>第${pagination.pageNo}页/共${pagination.totalPage}页</label> --%>
	<c:if test="${pagination.pageNo > 0 }">
		<ul class="pagination  pagination-sm">
			<li <c:if test="${pagination.pageNo==1 }">class="disabled"</c:if>><a href="javaScript:void(0);" onClick="paginationQuery(1)">&laquo;</a></li>
			<c:forEach begin="${begin}" end="${end}" varStatus="status">
				<c:choose>
					<c:when test="${ status.index == pagination.pageNo }">
					<li class="active"><a href="javascript:void(0);" onclick="paginationQuery(${status.index})">${status.index}</a></li>
					</c:when>
					<c:otherwise>
					<li><a  href="javascript:void(0);" onclick="paginationQuery(${status.index})">${status.index}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li <c:if test="${pagination.pageNo==pagination.totalPage }">class="disabled"</c:if>><a href="javaScript:void(0)" onClick="paginationQuery(${pagination.totalPage})">&raquo;</a></li>
		</ul>
		<span class="label label-default pull-right">共${pagination.totalPage}页/共${pagination.totalRows}条记录</span>
		<script type="text/javascript">
			function paginationQuery(pageNo){
				$("#pageNo").val(pageNo);
				$("#searchForm").submit();
			}
		</script>
	</c:if>
</nav>