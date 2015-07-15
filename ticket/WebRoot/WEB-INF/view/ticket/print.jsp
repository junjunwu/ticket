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
    
    <style type="text/css">
<!--
body{
	font-family: "FangSong_GB2312";
	font-size:12pt;
}
.firstTr {
	margin: 0px;
	padding: 0px;
	padding-top:20px;
	height:20px;
	font-size:10.5pt;
	font-family: "SimSun";
	border-bottom: 1px solid #000000;
	text-align:center;
	vertical-align:bottom;
}
.secondTr {
	margin: 0px;
	padding: 0px;
	width: 100%;
	height:20px;
	padding-top:32px;
	font-family: "宋体";
	font-size: 14pt;
	font-weight: bold;	
}
.thirdTr {
	padding: 0px;
	width: 100%;
	height:20px;
	padding-top:8px;
	font-size: 10.5pt;
}
.fifthTr {
	height:20px;
	padding-top:40px;
	font-size:15pt;
	font-weight: bold;	
}
-->
</style>
  </head>

  <body>
    <div style="width:660px;border:1px solid #000;">
	<div style="width:329px;float:right;">
		<div style="width:94%;margin:0 auto;text-align:center;">
			<div class="firstTr">东莞市黄江镇长途客运站乘车票（邮政代售版）</div>
			<div class="secondTr">存 根 联</div></td>
			<div class="thirdTr">凭证号码 <span style="color:#FF0000;">${sale.validNum }</span></div>
			<div class="fourthTr"></div>
			<div class="fifthTr">黄江&#8212;&#8212;<span style="color:#FF0000;">${ticket.terminus }</span></div>
			<div style="font-size:15pt;padding-top:20px;color:red;">￥<fmt:formatNumber value="${ticket.price }" pattern="0.00"/>元</div>
			<div style="padding-top:8px;text-align:left;font-weight: bold;">班次：<span style="color:#FF0000;">${ticket.coachNum }（${ticket.terminus }班）</span></div>
			<div style="padding-top:0;text-align:left;font-weight: bold;">车型：<span style="color:#FF0000;">${ticket.coachType }</span></div>
			<div style="padding-top:0;text-align:left;font-weight: bold;">日期：<span style="color:#FF0000;"><fmt:formatDate type="both" value="${ticket.departureTime}" pattern="yyyy-MM-dd" /></span></div>
			<div style="padding-top:0;text-align:left;font-weight: bold;margin-left:50px;"><span style="color:#FF0000;"><fmt:formatDate type="both" value="${ticket.departureTime}" pattern="HH:mm" /></span></div>
			<div style="padding-top:0;text-align:right;font-size:12pt;">收款单位：黄江邮政代售</div>
			<div style="padding-top:0;text-align:left;font-size:10.5pt;margin-left:0;">&nbsp;</div>
			<div style="padding-top:0;text-align:right;font-size:10.5pt;margin-left:0;font-family: "SimSun";">(存根联交邮政网点和客运站各一联）&nbsp;</div>
		</div>
	</div>
	<div style="width:329px;border-right:1px solid #000;">
		<div style="width:94%;margin:0 auto;text-align:center;">
			<div class="firstTr">东莞市黄江镇长途客运站乘车票（邮政代售版）</div>
			<div class="secondTr">乘车凭证联</div></td>
			<div class="thirdTr">凭证号码 <span style="color:#FF0000;">${sale.validNum }</span></div>
			<div class="fourthTr">（凭票乘车当次有效）</div>
			<div class="fifthTr">黄江&#8212;&#8212;<span style="color:#FF0000;">${ticket.terminus }</span></div>
			<div style="font-size:15pt;padding-top:20px;color:red;">￥<fmt:formatNumber value="${ticket.price }" pattern="0.00"/>元</div>
			<div style="padding-top:8px;text-align:left;font-weight: bold;">班次：<span style="color:#FF0000;">${ticket.coachNum }（${ticket.terminus }班）</span></div>
			<div style="padding-top:0;text-align:left;font-weight: bold;">车型：<span style="color:#FF0000;">${ticket.coachType }</span></div>
			<div style="padding-top:0;text-align:left;font-weight: bold;">日期：<span style="color:#FF0000;"><fmt:formatDate type="both" value="${ticket.departureTime}" pattern="yyyy-MM-dd" /></span></span></div>
			<div style="padding-top:0;text-align:left;font-weight: bold;margin-left:50px;"><span style="color:#FF0000;"><fmt:formatDate type="both" value="${ticket.departureTime}" pattern="HH:mm" /></span></div>
			<div style="padding-top:0;text-align:left;font-size:10.5pt;margin-left:120px;">收款单位（盖章）：</div>
			<div style="padding-top:0;text-align:left;font-size:10.5pt;margin-left:0;">此票手写及超过千元无效</div>
			<div style="padding-top:0;text-align:left;font-size:10.5pt;margin-left:0;padding-bottom:5px;">退票请于发车前两小时办理，需收取10元手续费</div>
		</div>
	
	</div>
    
 </div>
    
    <script src="${base }/res/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
    	window.print();
    </script>
  </body>
</html>

