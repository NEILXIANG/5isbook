<!-- 管理员个人中心 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>我的信息</title>
<link rel="shortcut icon" type="image/png" href="admin/images/favicon.png">
<script src="admin/js/enabledUser.js" type="text/javascript"></script>
</head>
<body>
	<!-- 标头 -->
	<s:include value="/admin/header.jsp"></s:include>
	<div class="container">
		<div class="current-user">
			<h1 class="title-bar">【当前登录系统的管理员】</h1>
			<table>
				<tr>
					<th>账号</th>
					<th>当前状态</th>
					<th>登录信息</th>
					<th>个人信息</th>
					<th>密码管理</th>
				</tr>
				<tr>
					<td><s:property value="#session.adminUser.getAccount()"/></td>
					<td><s:if test="#session.adminUser.isEnabled()"> 
						 已激活</s:if><s:else>未激活</s:else></td>
					<td><a href="admin/user/detail/getLoginInfo?userAccount='${session.adminUser.account}'" class="btn btn-small btn-info">点击查看</a>
					</td>
					<td><a href="admin/user/detail/getInfo?roleName=ROLE_ADMIN&userAccount=${session.adminUser.account}"
						class="btn btn-small btn-info">点击查看</a></td>
					<td><a href="admin/user/changePassword.jsp"
						class="btn btn-small btn-danger">点击进入修改</a></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="footer">
		<p>
			版权所有 © 2013 BY <a  >工作组</a>
		</p>
	</div>
</body>
</html>