<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@	taglib prefix="authz" uri="http://acegisecurity.org/authz" %>
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
</head>
<body>
	<div class="header">
		<div>
			<s:a action="index" namespace="/admin" title="首页">
			<img src="admin/images/cms-logo.png" alt="cms-logo" class="cms-logo">
			</s:a>
			<div class="login-out">
				<authz:authentication operation="username"/>&nbsp; <a href="j_acegi_logout"> 退出登录</a>  
			</div>
		</div>
		<div class="cms-nav">
			<ul> 
				<li><a
					href="admin/order/selectByStatus?status=committed">订单管理
				</a></li>
				<li><a href="admin/goods/allCategory"> 商品管理</a></li>
				<li><a href="admin/user/gotoPage?roleName=ROLE_ADMIN">
						会员管理 </a></li>
				<li><a href="admin/user/myInfo">
						我的账户  </a></li>
			</ul>
		</div>
	</div>
</body>
</html>
