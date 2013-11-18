<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>Admin</title>
<link rel="shortcut icon" type="image/png" href="admin/images/favicon.png">
<link href="admin/css/bootstrap.css" type="text/css" rel="stylesheet">
<link href="admin/css/cms.css" type="text/css" rel="stylesheet">
<script src="admin/js/jquery-1.9.1.min.js" type="text/javascript"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
		<div class="container">
	<h4>订单总数：<s:property value="all"/></h4> 
		<ul>
			<li>已提交订单数：<s:property value="committed"/>&nbsp;&nbsp;[<a href="admin/order/selectByStatus?status=committed">查看</a>]</li>
			<li>已处理订单数：<s:property value="disposed"/>&nbsp;&nbsp;[<a href="admin/order/selectByStatus?status=disposed">查看</a>]</li>
			<li>已出库订单数：<s:property value="outStorage"/>&nbsp;&nbsp;[<a href="admin/order/selectByStatus?status=out_storage">查看</a>]</li>
			<li>已收货订单数：<s:property value="received"/>&nbsp;&nbsp;[<a href="admin/order/selectByStatus?status=received">查看</a>]</li>
			<li>已取消订单数：<s:property value="cancel"/>&nbsp;&nbsp;[<a href="admin/order/selectByStatus?status=cancel">查看</a>]</li>
		</ul>
	</div>
	<div class="footer">
		<p>
			版权所有 © 2013 BY <a  >工作组</a>
		</p>
	</div>
</body>
</html>