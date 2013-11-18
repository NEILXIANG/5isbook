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
<meta http-equiv="C
ontent-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>订单详情</title>
<link rel="shortcut icon" type="image/png" href="admin/images/favicon.png">
<link rel="shortcut icon" type="image/png" href="admin/images/favicon.png">
</head>
<body>
	<!-- 标头 -->
	<s:include value="/admin/header.jsp"></s:include>
	<div class="container">
		<div class="order-title">
			<h1 class="title-bar">【订单详情】</h1>
		</div>
		<div class="order-list">
			<s:set var="nextState"
				value="order.getOrderState().getNextState().getState().toString()" />
			<ul>
				<li><span>订单序号：</span> <em> <s:property
							value="order.getId()" /></em></li>
				<li><span>当前状态：</span> <em><s:property
							value="order.getOrderState().getState()" /></em>
					<button class="btn btn-small btn-warning" data-toggle="modal"
						title="<s:if test="nextState!=null">更改到 ${nextState } 状态</s:if><s:else>不可更改</s:else>"
						onclick="changeStatus('${order.id}','${order.orderState.state}')">状态更改
					</button>
				<li><span>下单账号：</span> <em> <a
						href="admin/user/detail/getInfo?roleName=ROLE_USER&userAccount=${order.user.account}">
							<s:property value="order.getUser().getAccount()" />
					</a></em> <a
					href="admin/order/getUserOrders?userAccount=${order.user.account}"
					class="btn btn-small btn-inverse" data-toggle="modal">查看他的所有订单
				</a></li>
				<li><span>下单时间：</span> <em><s:property
							value="order.showCreateTime()" /></em></li>
				<li><span>配送时间：</span> <em><s:property
							value="order.getDeliveryTime().getTime()" /></em></li>
				<li><span>配送地址：</span> <em><s:property
							value="order.getAddress()" /></em></li>
				<li><span>买家姓名：</span> <em><s:property
							value="order.getRealName()" /></em></li>
				<li><span>买家长号：</span> <em><s:property
							value="order.getLongTel()" /></em></li>
				<li><span>买家短号：</span> <em><s:property
							value="order.getShortTel()" /></em></li>
				<li><span>订单总价：</span> <em><s:property
							value="order.totalPrice()" /></em> <span>元</span></li>
				<li><span>具体订单：</span>
					<table class="detail-table">
						<tr>
							<th>标号</th>
							<th>商品名称</th>
							<th>商品 ISBN</th>
							<th>商品类型</th>
							<th>商品库存码</th>
							<th>数量/件</th>
							<th>单价/元</th>
						</tr>
						<s:iterator value="order.getOrderItems()">
							<s:set var="type"
								value="getOrderItemPK().getEntityType().getEntityTypePK().getGoodsType()" />
							<tr>
								<td>1</td>
								<td><s:a action="info_detail" namespace="/admin/goods">
										<s:param name="entityId"
											value="getOrderItemPK().getEntity().getId()" />
										<s:param name="show" value="true" />
										<s:property
											value="getOrderItemPK().getEntity().getEntityValue('title')" />
									</s:a></td>
								<td><s:property
										value="getOrderItemPK().getEntity().getEntityValue('isbn')" /></td>
								<td><s:property value="%{#type.getDisplayName()}" /></td>
								<td><s:property value="getStoCodeList()" /></td>
								<td><s:property value="getBuyNum()" /></td>
								<td><s:property
										value="getOrderItemPK().getEntity().getSinglePriceByEntityType('authorised_edition')" /></td>
							</tr>
						</s:iterator>
					</table></li>
			</ul>
		</div>
	</div>
	<script src="admin/js/changeOrderStatus.js" type="text/javascript"></script>
</body>
</html>
