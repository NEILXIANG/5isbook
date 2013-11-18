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
<title>用户历史订单</title>
<link rel="shortcut icon" type="image/png"
	href="admin/images/favicon.png">
</head>
<body>
	<s:include value="/admin/header.jsp"></s:include>

	<div class="container">
		<div class="order-area">
			<h1 class="title-bar">
				【 <a
					href="admin/user/detail/getInfo?roleName=ROLE_USER&userAccount=${userAccount}">${userAccount
					}</a> 的历史订单列表 】
			</h1>
			<ul class="nav nav-tabs">
				<li class="<s:if test="%{status==null}">active</s:if>"><a
					href="admin/order/getUserOrders?userAccount=${userAccount}">所有订单</a>
				</li>
				<li class="<s:if test="%{status.equals('committed')}">active</s:if>"><a
					href="admin/order/getUserOrdersByStatus?userAccount=${userAccount}&status=committed">已提交的订单</a>
				</li>
				<li class="<s:if test="%{status.equals('disposed')}">active</s:if>"><a
					href="admin/order/getUserOrdersByStatus?userAccount=${userAccount}&status=disposed">已处理的订单</a>
				</li>
				<li
					class="<s:if test="%{status.equals('out_storage')}">active</s:if>"><a
					href="admin/order/getUserOrdersByStatus?userAccount=${userAccount}&status=out_storage">已出库的订单</a>
				</li>
				<li class="<s:if test="%{status.equals('received')}">active</s:if>"><a
					href="admin/order/getUserOrdersByStatus?userAccount=${userAccount}&status=received">已收货的订单</a>
				</li>
				<li class="<s:if test="%{status.equals('cancel')}">active</s:if>"><a
					href="admin/order/getUserOrdersByStatus?userAccount=${userAccount}&status=cancel">已取消的订单</a></li>
			</ul>
			<!-- 分页条 -->
			<div class="page-bar">
				<div class="jump-page">
					<div class="jump-left">
						<span> 共<s:property value="pageNumberList.size()" /> 页
						</span>
					</div>
					<div class="jump-right">
						<input type="text" name="jump" size="3" value="${pageIndex }"
							onkeydown="" id="jump" title="输入页码，按回车快速跳转" /> <a class="btn"
							type="button" onClick="go('${userAccount }','${status}')">Go</a>
					</div>
				</div>
				<ul class="cms-pagination">
					<s:iterator value="pageNumberList" var="page">
						<li><s:if test="%{status==null}">
								<a
									href="admin/order/getUserOrders?userAccount=${userAccount}&pageIndex=${page}"
									class="<s:if test="%{pageIndex==#page}">current-page</s:if>">${page
									}</a>
							</s:if> <s:else>
								<a
									href="admin/order/getUserOrdersByStatus?userAccount=${userAccount}&status=${status }&pageIndex=${page}"
									class="<s:if test="%{pageIndex==#page}">current-page</s:if>">${page
									}</a>
							</s:else></li>
					</s:iterator>
				</ul>
			</div>
			<!-- 清除浮动 -->
			<div class="clear"></div>
			<!-- 下面是一页的内容，一个 div 块-->
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
						<s:set var="nextState"
							value="getOrderState().getNextState().getState().toString()" />
						<tr>
							<td><s:property value="#st.index+1" /></td>
							<td><s:property value="getId()" /></td>
							<td><s:property value="getUser().getAccount()" /></td>
							<td><s:property value="getCreateTime()" /></td>
							<td><s:property value="getAddress()" /></td>
							<td><a href="admin/order/detail?orderId=${id}"
								class="btn btn-small btn-info">查看</a></td>
							<td><s:if
									test="%{getOrderState().getState().toString().equals('已收货')||getOrderState().getState().toString().equals('已取消')}">
									<a class="btn btn-small">不可更改</a>
								</s:if> <s:else>
									<s:if test="%{status!=null}">
										<a class="btn btn-small btn-warning"
											title="更改到 ${nextState} 状态"
											href="admin/order/processToNextStatus?orderId=${id }&status=${status }&userAccount=${userAccount }&pageIndex=${pageIndex}&back=getUserOrdersByStatus">
											更改</a>
									</s:if>
									<s:else>
										<a class="btn btn-small btn-warning"
											title="更改到 ${nextState} 状态"
											href="admin/order/processToNextStatus?orderId=${id }&userAccount=${userAccount}&back=getUserOrders&pageIndex=${pageIndex}">
											更改</a>
									</s:else>
								</s:else></td>
							<td><s:if
									test="%{getOrderState().getState().toString().equals('已收货')||getOrderState().getState().toString().equals('已取消')}">
									<a class="btn btn-small">不可取消</a>
								</s:if> <s:elseif test="%{status!=null}">
									<a
										href="admin/order/cancel?orderId=${id}&status=${status }&back=getUserOrdersByStatus&pageIndex=${pageIndex}&userAccount=${userAccount}&back=getUserOrdersByStatus"
										class="btn btn-small btn-danger">取消</a>
								</s:elseif> <s:else>
									<a
										href="admin/order/cancel?orderId=${id}&pageIndex=${pageIndex}&userAccount=${userAccount}&back=getUserOrders"
										class="btn btn-small btn-danger">取消</a>
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
		function go(userAccount, status) {
			var pageIndex = document.getElementById("jump").value;
			if (pageIndex > 0) {
				if (status == '')
					window.location.href = "admin/order/getUserOrders?userAccount="
							+ userAccount + "&pageIndex=" + pageIndex;
				else
					window.location.href = "admin/order/getUserOrdersByStatus?userAccount="
							+ userAccount
							+ "&status="
							+ status
							+ "&pageIndex="
							+ pageIndex;
			}
		}
	</script>
</body>
</html>
