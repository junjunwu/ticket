<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户列表</title>
    <script type="text/javascript">
    	$(function(){
    		$(".resetPwd").click(function(){
    			var userId = $(this).attr("userId");
    			$.ajax({
					url: "${base}/user/resetPwd",
					type:"POST",
					data:{userId:userId},
					success:function(date){
						if(date == "success"){
							alert("重置密码成功");
						}else{
							alert("失败");
						}
					}
				});	
    		});
    	
    	});
    </script>
  </head>
<body>
  <div class="panel panel-success">
  <div class="panel-heading h3" style="margin:0;">用户列表</div>
  <div class="panel-body">
	  <div class="well search-well well-sm">
	        <form class="form-inline text-center" id="searchForm" action="${base }/user/list" method="post">
			  <div class="form-group">
			    <label for="realName">用户名</label>
			    <input type="text" value="${query.user.realName }" class="form-control" id="realName" name="user.realName" placeholder="真实姓名..">
			  </div>
			  
			  
			  <button type="submit" class="btn btn btn-primary">查找</button>
			</form>
	    </div>
	    <table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>ID</th>
					<th>登录名</th>
					<th>真实姓名</th>
					<th>性别</th>
					<th>注册时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pagination.objLists }" var="user" varStatus="vs">
					<tr>
						<td>${vs.count+pagination.pageSize*(pagination.pageNo-1) }</td>
						<td>${user.id}</td>
						<td>${user.loginName}</td>
						<td>${user.realName}</td>
						<td>${user.sex==1?'男':'女'}</td>
						<td><fmt:formatDate type="both" value="${user.createTime}"/></td>
						<td><button type="button" class="btn btn-default btn-xs resetPwd" userId="${user.id }">重置密码</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:import url="../common/page.jsp"></c:import>
		</div>
	</div>
  </body>
</html>
