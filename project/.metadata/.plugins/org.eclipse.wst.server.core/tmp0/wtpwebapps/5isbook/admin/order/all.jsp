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
<link rel="shortcut icon" type="image/png" href="admin/images/favicon.png">
<title>订单管理</title>
</head>
<body>
	<jsp:include page="/admin/header.jsp" />
	<h1></h1>
	<div class="container">
		<ul class="nav nav-tabs">
			<li class="<s:if test="%{status.equals('committed')}">active</s:if>"><a
				href="admin/order/selectByStatus?status=committed">已提交的订单</a>
			</li>
			<li class="<s:if test="%{status.equals('disposed')}">active</s:if>"><a
				href="admin/order/selectByStatus?status=disposed">已处理的订单</a>
			</li>
			<li
				class="<s:if test="%{status.equals('out_storage')}">active</s:if>"><a
				href="admin/order/selectByStatus?status=out_storage">已出库的订单</a>
			</li>
			<li class="<s:if test="%{status.equals('received')}">active</s:if>"><a
				href="admin/order/selectByStatus?status=received">已收货的订单</a>
			</li>
			<li class="<s:if test="%{status.equals('cancel')}">active</s:if>"><a
				href="admin/order/selectByStatus?status=cancel">已取消的订单</a></li>
		</ul>
		<div class="order-area">
			<!-- 分页条 -->
			<div class="page-bar">
				<div class="jump-page">
					<div class="jump-left">
						<span> <s:property value="pageNumberList.size()" /> 页
						</span>
					</div>
					<div class="jump-right">
						<input type="text" name="jump" size="3" value="${pageIndex }"
							onkeydown="" id="jump" title="输入页码，按回车快速跳转" /> <a class="btn"
							type="button" onClick="go('${status}')">Go</a>
					</div>
				</div>
				<ul class="cms-pagination">
					<s:iterator value="pageNumberList" var="page">
						<li><a
							href="admin/order/selectByStatus?status=${status }&pageIndex=${page}"
							class="<s:if test="%{pageIndex==#page}">current-page</s:if>">${page
								}</a></li>
					</s:iterator>
				</ul>
			</div>
			<div class="table-above">
				<table class="order-table">
					<tr>
						<th>标号</th>
						<th>订单号</th>
						<th>下单账号</th>
						<th>下单时间</th>
						<th>配送详细地址</th>
						<th>订单详情</th>
						<th>更改状态</th>
						<th>取消订单</th>
					</tr>
					<s:iterator value="list" var="order" status="st">
						<tr>
							<td><s:property value="#st.index+1" /></td>
							<td><s:property value="getId()" /></td>
							<td><a
								href="admin/user/detail/getInfo?roleName=ROLE_USER&userAccount=${user.account}"
								 title="查看该用户信息">
									<s:property value="getUser().getAccount()" />
							</a></td>
							<td><s:property value="showCreateTime()" /></td>
							<td><s:property value="getAddress()" /></td>
							<td><a href="admin/order/detail?orderId=${id}" title="查看订单详情"
								class="btn btn-small btn-info">查看</a></td>
							<td><s:if
									test="%{status.equals('received')||status.equals('cancel')}">
									<a class="btn btn-small" title="该状态下已经不可以更改了">不可更改</a>
								</s:if> <s:else>
									<a class="btn btn-small btn-warning" title="更改到[已处理]状态"
										href="admin/order/processToNextStatus?orderId=${Id}&status=${status}&pageIndex=${pageIndex}&back=select">
										更改 </a>
								</s:else></td>
							<td><s:if
									test="%{status.equals('received')||status.equals('cancel')}">
									<a class="btn btn-small btn-danger" title="该状态下不可取消了">不可取消</a>
								</s:if> <s:else>
									<a
										href="admin/order/cancel?orderId=${id}&status=${status}&pageIndex=${pageIndex}&back=select"
										class="btn btn-small btn-danger" title="取消该订单">取消</a>
								</s:else></td>
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
	<script src="admin/js/jquery-1.9.1.min.js"
		type="text/javascript"></script>
	<script src="admin/js/bootstrap.js" type="text/javascript"></script>
	<script type="text/javascript">
		function go(status) {
			var pageIndex = document.getElementById("jump").value;
			if(pageIndex>0)
			window.location.href = "admin/order/selectByStatus?status="
					+ status + "&pageIndex=" + pageIndex;
		}
	</script>
</body>
</html>
