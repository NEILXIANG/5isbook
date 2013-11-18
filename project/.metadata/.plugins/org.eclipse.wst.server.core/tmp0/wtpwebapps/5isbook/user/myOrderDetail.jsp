<%
/*  
*	@author:TQ
*	个人订单修改
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<jsp:include page="../basic/user-header.jsp" />
<div class="content">
	<div class="span2 ml100">
		<ul class="user-center-nav">
			<li class=""><s:a action="index" namespace="/user">我的首页</s:a></li>
			<li class="active"><a href="javascript:void(0)">我的订单</a></li>
			<li class=""><s:a action="index" namespace="/user/address">地址管理</s:a></li>
			<li class=""><s:a action="nomalinfo" namespace="/user/info">个人信息</s:a></li>
			<li class=""><s:a action="index" namespace="/cart">购物车</s:a></li>
			<li class="backHome"><a href="">返回首页</a></li>
		</ul>
	</div>
	<div class="span7">
		<ul class="nav nav-tabs myul">
			<li class="active"><a href="javascript:void(0)">订单详情</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active">
				<ul class="detail-list">
					<li><h1>订单号：<s:property value="order.id"/>&nbsp;/&nbsp;<s:property value="order.orderState"/></h1></li>
					<li><h1>收货地址</h1>
						<ul>
							<li>姓名：<s:property value="order.realName"/></li>
							<li>地址：<s:property value="order.address"/></li>
							<li>电话：<s:property value="order.longTel"/>&nbsp;/&nbsp;<s:property value="order.shortTel"/></li>
						</ul>
					</li>
					<li>
						<h1>购买日期</h1>
						<s:property value="order.showCreateTime()"/>
					</li>
					<li>
						<h1>送货时间</h1>
					 	<s:property value="order.deliveryTime"/>
					 </li>
					<li><h1>订单项</h1>
						<table class="table table-hover table-bordered">
							<thead>
								<th col="3">商品名称</th>
								<th>单价</th>
								<th>数量</th>
								<th>小计</th>
							</thead>
							<s:iterator id="item" value="order.orderItems">
							<tr>
								<td><s:a action="detail" namespace="/"><s:param name="id" value="#item.orderItemPK.entity.id"/>
										<s:property value='#item.orderItemPK.entity.getEntityValue("title")'/>-<s:property value="#item.orderItemPK.entityType.entityTypePK.goodsType.displayName"/>
								</s:a></td>
								<td><s:property value="#item.orderItemPK.entity.getSinglePriceByEntityType(#item.orderItemPK.entityType.entityTypePK.goodsType.name)"/>元</td>
								<td><s:property value="#item.buyNum"/></td>
								<td><s:property value="#item.calculatePrice()"/>元</td>
							</tr>
							</s:iterator>
							<tr>
								<td class="red" colspan="6">合计：<s:property value="order.totalPrice()"/>元</td>
							</tr>
						</table>
					</li>
				</ul>
				<div class="opt">
					<s:a action="order" namespace="/user" cssClass="btn">返回</s:a>
				</div>
			</div>
		</div>
	</div>
	<div class="span3">
		<div class="user-summary">
			<div class="stitle"><i class="icon-user icon-white"></i> <s:a action="index" namespace="/user">用户信息</s:a></div>
			<div class="scontent">
				<ul>
					<li><img src="statics/images/userlogo.jpg" alt="" />
					<s:a action="nomalinfo" cssClass="edit" namespace="/user/info">个人信息</s:a></li>
					<li>
					<script type="text/javascript">
					<!--
						var now = (new Date()).getHours();
						if (now > 0 && now <= 6) {
							document.write("午夜好，");
						} else if (now > 6 && now <= 11) {
							document.write("早上好，");
						} else if (now > 11 && now <= 14) {
							document.write("中午好，");
						} else if (now > 14 && now <= 18) {
							document.write("下午好，");
						} else {
							document.write("晚上好，");
						}
					//--->
					</script><s:property value="#session['NICK_NAME']" />.
					</li>
					<li>本次登陆ip:<s:property value="#session['login_info'].ip" /></li>
					<li>登录时间：<s:property value="#session['login_info'].formatLoginTime()" /></li>
				</ul>
			</div>

		</div>
	</div>
</div>
<div class="clear"></div>
 <jsp:include page="../basic/basic-footer.jsp" />
