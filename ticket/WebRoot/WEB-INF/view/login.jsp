<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
   <!--  <link rel="icon" href="../../favicon.ico"> -->
    <title>黄江邮政分公司代售长途汽车票系统</title>
    <!-- Bootstrap core CSS -->
    <link href="${base }/res/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${base }/res/css/login.css" rel="stylesheet">
  </head>

  <body>
  	<div class="navbar navbar-duomi navbar-static-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <span class="navbar-brand" id="logo">黄江邮政分公司代售长途汽车票系统
                </span>
				
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
        	<div class="modal show">
			  <div class="modal-dialog" style="margin:160px auto;width:400px;">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h4 class="modal-title">Sign in</h4>
			      </div>
			      <div class="modal-body">
			        <form class="form-horizontal" action="${base }/login" method="POST">
					  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-3 control-label">登录名</label>
					    <div class="col-sm-9">
					      <%-- <input type="text" class="form-control" id="loginName" name="loginName" value="${loginName }" placeholder="LoginName"> --%>
					      <input type="text" class="form-control" id="loginName" name="loginName" value="system" placeholder="LoginName">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="inputPassword3" class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;码</label>
					    <div class="col-sm-9">
					      <!-- <input type="password" class="form-control" id="password" name="password" placeholder="Password"> -->
					      <input type="password" class="form-control" id="password" value="dg11185" name="password" placeholder="Password">
					    </div>
					  </div>
					  
					  <div class="form-group">
					    
					    <div class="col-sm-offset-1 col-sm-11">
					    	<lable class="text-danger">${msg }</lable>
					      <button type="submit" class="btn btn-primary pull-right">登录</button>
					    </div>
					  </div>
					</form>
			      </div>
			      
			    </div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
		    
		</div>
	</div>
    
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${base }/res/js/jquery-1.11.3.min.js"></script>
    <script src="${base }/res/bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>

