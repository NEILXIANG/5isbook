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
<title>管理员用户的信息</title>
<link rel="shortcut icon" type="image/png"
	href="admin/images/favicon.png">
<script src="admin/js/enabledUser.js" type="text/javascript"></script>
</head>
<body>
	<!-- 标头 -->
	<s:include value="/admin/header.jsp"></s:include>
	<div class="container">
		<div class="current-side">
			当前位置： <a href="admin/user/gotoPage?roleName=ROLE_ADMIN">会员管理</a>
			<span>></span> <a>管理员个人信息</a>
		</div>
		<div class="info-content">
			<h1 class="title-bar">【管理员个人信息】</h1>
			<dl class="user-info-list">
				<dt>账号：</dt>
				<dd>
					<s:property value="userAccount" />
				</dd>
				<dt>姓名：</dt>
				<dd>
					<s:property value="userInfo.getNickName()" />
				</dd>
				<dt>性别：</dt>
				<dd>
					<s:if test="%{userInfo.gender=='man'}">
					男
					</s:if>
					<s:if test="%{userInfo.gender=='women'}">女</s:if>
				</dd>
				<dt>生日：</dt>
				<dd>
					<s:property value="userInfo.getBirthday()" />
				</dd>
				<dt>QQ：</dt>
				<dd>
					<s:property value="userInfo.getQq()" />
				</dd>
				<dt>处理过的订单：</dt>
				<dd>
					<a title="暂时没有实现" class="btn btn-small btn-info">点击查看</a>
				</dd>
			</dl>
		</div>
	</div>
	<div class="footer">
		<p>
			版权所有 © 2013 BY <a  >工作组</a>
		</p>
	</div>
</body>
</html>