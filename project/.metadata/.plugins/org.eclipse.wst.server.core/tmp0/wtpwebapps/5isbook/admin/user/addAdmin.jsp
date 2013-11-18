<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.acegisecurity.ui.AbstractProcessingFilter"%>
<%@ page
	import="org.acegisecurity.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page import="org.acegisecurity.AuthenticationException"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增管理员</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<link rel="shortcut icon" type="image/png" href="admin/images/favicon.png">
</head>
<body>
	<!-- 标头 -->
	<s:include value="/admin/header.jsp"></s:include>
	<div class="container">
		<div class="current-side">
			当前位置： <a href="admin/user/gotoPage?roleName=ROLE_ADMIN">会员管理</a>
			<span>></span> <a  >新增一个管理员</a>
		</div>
		<div class="info-content">
			<h1 class="title-bar">【新增管理员表单】</h1>
			<h2 class="title-bar">${message}</h2>
			<form action="admin/addAdmin" method="post"
				name="cmsAddAdmin" onsubmit="">
				<dl class="input-list">
					<dt>账号：</dt>
					<dd>
						<input type="text" value="${account }" name="account"
							required>
						<span class="error"><s:fielderror fieldName="account" /></span>
					</dd>
					<dt>密码：</dt>
					<dd>
						<input type="password" value="" name="password" required>
						<span class="error"><s:fielderror fieldName="password" /></span>
					<dt>密码确认：</dt>
					<dd>
						<input type="password" value="" name="rePassword" required>
						<span class="error"><s:fielderror fieldName="rePassword" /></span>
					</dd>
				</dl>
				<input type="hidden" name="adminAccount" value="<s:property value="#session['ACEGI_SECURITY_LAST_USERNAME']"/>" />
				<input type="submit" value="提交" class="btn btn-success add-btn">
			</form>
		</div>
	</div>
	<div class="footer">
		<p>
			版权所有 © 2013 BY <a  >工作组</a>
		</p>
	</div>
	<script src="../js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="../js/bootstrap.js" type="text/javascript"></script>
</body>
</html>