<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="authz" uri="http://acegisecurity.org/authz"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>用户管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<link rel="shortcut icon" type="image/png" href="admin/images/favicon.png">
</head>
<body>
	<!-- 标头 -->
	<s:include value="/admin/header.jsp"></s:include>
	<div class="container">
		<ul class="nav nav-tabs">
			<li
				class="<s:if test="%{roleName.equals('ROLE_SUPER_ADMIN')}">active</s:if>"><a
				href="admin/user/gotoPage?roleName=ROLE_SUPER_ADMIN">超级管理员</a></li>
			<li
				class="<s:if test="%{roleName.equals('ROLE_ADMIN')}">active</s:if>"><a
				href="admin/user/gotoPage?roleName=ROLE_ADMIN">管理员</a></li>
			<li
				class="<s:if test="%{roleName.equals('ROLE_USER')}">active</s:if>"><a
				href="admin/user/gotoPage?roleName=ROLE_USER">普通用户</a></li>
		</ul>
		<div class="order-area">
			<ul>
				<div class="other-admin">
					<s:if test="%{roleName.equals('ROLE_ADMIN')}">
						<authz:authorize ifAllGranted="ROLE_SUPER_ADMIN">
							<div class="add-admin">
								<a href="admin/user/addAdmin.jsp" class="btn btn-success"> 新增一个管理员</a>
							</div>
						</authz:authorize>
					</s:if>
				</div>
			</ul>
			<!-- 分页条 -->
			<div class="page-bar">
				<div class="jump-page">
					<div class="jump-left">
						<span>共<s:if test="%{pageBean.getTotalPage()!=0}">
								<s:property value="%{pageBean.getTotalPage()}" />
							</s:if> <s:else>0</s:else>页
						</span>
					</div>
					<div class="jump-right">
						<input type="text" name="jump" size="3" value="${pageIndex }"
							onkeydown="" id="jump" title="输入页码，按回车快速跳转" /> <a class="btn"
							type="button" onClick="go('${roleName}')">Go</a>
					</div>
				</div>
				<ul class="cms-pagination">
					<s:iterator value="pageList" var="page">
						<li><a href="admin/user/gotoPage?roleName=${roleName}&pageIndex=${page}"
							class="<s:if test="%{pageIndex==#page}">current-page</s:if>">${page
								}</a></li>
					</s:iterator>
				</ul>
			</div>
			<div class="table-above">
				<table class="account-table">
					<tr>
						<th>序号</th>
						<th>账号</th>
						<th>当前状态</th>
						<th>登录信息</th>
						<th>个人信息</th>
						<s:if
							test="%{roleName.equals('ROLE_SUPER_ADMIN')||roleName.equals('ROLE_ADMIN')}">
							<authz:authorize ifAllGranted="ROLE_SUPER_ADMIN">
								<th>操作账号</th>
							</authz:authorize>
						</s:if>
						<s:else>
							<th>操作账号</th>
						</s:else>
					</tr>
					<s:iterator value="list" var="user" status="st">
						<tr>
							<td><s:property value="#st.index+1" /></td>
							<td><s:property value="account" /></td>
							<td><s:if test="enabled">
									<div id="enabled_${account}">已激活</div>
								</s:if> <s:else>
									<div id="enabled_${account}">未激活</div>
								</s:else></td>
							<td><a href="admin/user/detail/getLoginInfo?userAccount='${account}'"
								class="btn btn-small btn-info">点击查看</a></td>
							<td><a
								href="admin/user/detail/getInfo?roleName=${roleName}&userAccount=${account}"
								class="btn btn-small btn-info">点击查看</a></td>
							<s:if
								test="%{roleName.equals('ROLE_SUPER_ADMIN')||roleName.equals('ROLE_ADMIN')}">
								<authz:authorize ifAllGranted="ROLE_SUPER_ADMIN">
									<td><button onClick="change('${account}')"
											class="btn btn-small btn-inverse">更改账号状态</button></td>
								</authz:authorize>
							</s:if>
							<s:else>
								<td>
									<button onClick="change('${account}')"
										class="btn btn-small btn-inverse">更改账号状态</button>
								</td>
							</s:else>
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
	<script src="admin/js/enabledUser.js" type="text/javascript"></script>
	<script type="text/javascript">
		function go(roleName) {
			var pageIndex = document.getElementById("jump").value;
			if(pageIndex>0)
			window.location.href = "admin/user/gotoPage?roleName="
					+ roleName + "&pageIndex=" + pageIndex;
		}
	</script>
</body>
</html>
