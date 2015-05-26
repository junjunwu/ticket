<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content=""> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	
	<title>黄江邮政分公司代售长途汽车票系统-<decorator:title/></title>
	
	<link href="${base }/res/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="${base }/res/js/jquery-1.11.3.min.js"></script>
    <script src="${base }/res/bootstrap/js/bootstrap.min.js"></script>
	<style>
		.container-main{
			padding: 10px 20px 0 20px;
		}
        #main-nav {
            margin-left: 1px;
        }

        #main-nav.nav-tabs.nav-stacked > li > a {
             padding-right: 20px;
			 padding-left: 20px;
             border-radius: 4px;
         }

		#main-nav.nav-tabs.nav-stacked > li > a,
		#main-nav.nav-tabs.nav-stacked > li > a:hover,
		#main-nav.nav-tabs.nav-stacked > li > a:focus {
		  color: #fff;
		  background-color: #428bca;
		}

		#main-nav.nav-tabs.nav-stacked > li > a > span {
			color: #fff;
		}

         #main-nav.nav-tabs.nav-stacked > li {
             margin-bottom: 4px;
         }

        /*定义二级菜单样式*/
        .secondmenu a {
            font-size: 12px;
            color: #4A515B;
            text-align: left;
        }

        .navbar-static-top {
            background-color: #212121;
            margin-bottom: 5px;
        }

        .navbar-brand {
            background: url('') no-repeat 10px 8px;
            display: inline-block;
            vertical-align: middle;
            padding-left: 50px;
            color: #fff;
        }
    </style>
    
    <decorator:head/>
  </head>
  
  <body>
    <div class="navbar navbar-duomi navbar-static-top" role="navigation">
        <div class="container-fluid">
           <div class="navbar-header"> 
                <span class="navbar-brand" id="logo">黄江邮政分公司代售长途汽车票系统
                </span>
           </div>
           <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
           		<ul class="nav navbar-nav navbar-right">
        		<li><a href="${base }/logout"><i class="glyphicon glyphicon-user"></i>退出</a></li>
        		</ul>
				<!-- <span class="navbar-brand pull-right" style="cursor:pointer;"><i class="glyphicon glyphicon-user"></i>退出
                </span> -->
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row  container-main">
            <div class="col-md-2 well">
                <ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
                    <li>
                        <a href="#userMenu" class="nav-header collapsed" data-toggle="collapse" aria-expanded="false"  aria-controls="userMenu">
                            <i class="glyphicon glyphicon-user"></i>
                            	用户管理
                               <span class="pull-right glyphicon glyphicon-chevron-down"></span>
                        </a>
                        <ul id="userMenu" class="nav nav-list collapse in secondmenu">
                            <li><a href="${base }/user/add">添加操作员</a></li>
                            <li><a href="${base }/user/list">操作员列表</a></li>
                        </ul>
                    </li>
					<li>
                        <a href="#msgMenu" class="nav-header collapsed" data-toggle="collapse">
                            <i class="glyphicon glyphicon-list-alt"></i>
                           		售票管理
                               <span class="pull-right glyphicon glyphicon-chevron-down"></span>
                        </a>
                        <ul id="msgMenu" class="nav nav-list collapse in secondmenu">
                            <li><a href="${base }/ticket/add">手动录入</a></li>
                            <li><a href="${base }/ticket/add">导入数据</a></li>
                            <li><a href="${base }/ticket/list">查询</a></li>
                        </ul>
                    </li>
					
                </ul>
            </div>
            <div class="col-md-10">
                <decorator:body />
            </div>
        </div>
    </div>
    
</body>
</html>
