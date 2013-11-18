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
<title>普通用户的信息</title>
<link rel="shortcut icon" type="image/png" href="admin/images/favicon.png">
<script src="admin/js/enabledUser.js" type="text/javascript"></script>
</head>
<body>
	<!-- 标头 -->
	<s:include value="/admin/header.jsp"></s:include>

	<div class="container">
		<div class="current-side">
			当前位置： <a href="admin/user/gotoPage?roleName=ROLE_ADMIN">会员管理</a>
			<span>></span> <a  >普通用户个人信息</a>
		</div>
		<div class="info-content">
			<h1 class="title-bar">【普通用户个人信息】</h1>
			<dl class="user-info-list">
				<dt>账号：</dt>
				<dd>
					<s:property value="userAccount" />
					<a
						href="admin/order/getUserOrders?userAccount=${userAccount}"
						class="btn btn-small btn-primary">查看账号历史订单</a>
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
					<s:else>女</s:else>
				</dd>
				<dt>生日：</dt>
				<dd>
					<s:property value="userInfo.getBirthday()" />
				</dd>
				<dt>QQ：</dt>
				<dd>
					<s:property value="userInfo.getQq()" />
				</dd>
			</dl>
			<br /> <br />
			<div class="user-address">
				<p>可配送地址列表：</p>
				<table>
					<tr>
						<th>标号</th>
						<th>姓名</th>
						<th>长号</th>
						<th>短号</th>
						<th>配送地址</th>
					</tr>
					<s:iterator value="userAddress" id="address" status="st">
						<tr>
							<td>${st.count }</td>
							<td><s:property value="getRealName()" /></td>
							<td><s:property value="getLongTel()" /></td>
							<td><s:property value="getShortTel()" /></td>
							<td><s:property value="getAddress()" /></td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
	</div>
	<div class="footer">
		<p>
			版权所有 © 2013 BY <a  >工作组</a>
		</p>
	</div>
</body>
</html>
