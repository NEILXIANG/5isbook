<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>用户登录信息</title>
<link rel="shortcut icon" type="image/png" href="admin/images/favicon.png">
<script src="../../js/page-bar.js" type="text/javascript"></script>
</head>
<body>
	<s:include value="/admin/header.jsp"></s:include>
	<div class="container">
		<div class="current-side">
			当前位置： <a href="admin/user/gotoPage?roleName=ROLE_ADMIN">会员管理</a>
			<span>></span> <a>登录信息</a>
		</div>
		<div class="info-content">
			<h1 class="title-bar">【用户登录信息 - 记录】</h1>
			<div class="current-search">
				当前查询账号： <span><s:property value="userAccount" /></span>
			</div>
			<div class="page-bar">
				<div class="jump-page">
					<div class="jump-left">
						<span>共<s:if test="%{pageBean.getTotalPage()!=0}">
								<s:property value="%{pageBean.getTotalPage()}" />
							</s:if> <s:else>0</s:else>页
						</span>
					</div>
					<div class="jump-right">
						<input type="text" name="jump" size="3" value="${pageIndex }" onkeydown=""
							id="jump" title="输入页码，按回车快速跳转" /> <a class="btn" type="button"
							onClick="go(${userAccount})">Go</a>
					</div>
				</div>
				<ul class="cms-pagination">
					<s:iterator value="pageList" var="page">
						<li><a
							href="admin/user/detail/getLoginInfo?userAccount=${userAccount}&pageIndex=${page}"
							class="<s:if test="%{pageIndex==#page}">current-page</s:if>">${page
								}</a></li>
					</s:iterator>
				</ul>
			</div>
			<!-- 清除浮动 -->
			<div class="clear"></div>
			<div class="table-above">
			<!-- 下面为分页的内容，其中每一个 li 存放一页的内容，注意下 -->
			<table class="login-info-table">
				<tr>
					<th>序号</th>
					<th>登录 IP</th>
					<th>登录时间</th>
				</tr>
				<s:iterator value="list" var="loginInfo" status="st">
					<tr>
						<td><s:property value="#st.count" /></td>
						<td><s:property value="getIp()" /></td>
						<td><s:property value="formatLoginTime()" /></td>
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
	<script type="text/javascript">
		function go(userAccount) {
			var pageIndex = document.getElementById("jump").value;
			if(pageIndex>0)
			window.location.href = "admin/user/detail/getLoginInfo?userAccount='"+userAccount+"'&pageIndex="+pageIndex;
		}
	</script>
</body>
</html>
