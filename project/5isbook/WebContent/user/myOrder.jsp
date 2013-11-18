
<%
	/*  
	 *	@author:TQ
	 *	个人订单
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
			<!-- 这里没办法更换 -->
			<li class="<s:if test='req.equals("all")'>active</s:if>">
			<a href="user/order.action?req=all">全部订单</a>
			</li>
			<li class="<s:if test='req.equals("process")'>active</s:if>">
			<a href="user/order.action?req=process">未收货订单</a></li>
			<li class="<s:if test='req.equals("done")'>active</s:if>">
			<a href="user/order.action?req=done">已完成订单</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active">
				<div class="myOrder-list">
					<div class="list">
						<s:iterator id="order" value="orders.list">
							<table class="table table-bordered">
								<thead>
									<th width="300">订单名称</th>
									<th width="100">总价</th>
									<th width="110">状态</th>
									<th width="120">操作</th>
								</thead>
								<tr>
									<td>
										<!--  img class="orderImg" src="" alt=""/-->
										<ul>
											<li>订单号：<s:property value="#order.id" /></li>
											<li>交易时间：<s:property value="#order.showCreateTime()" /></li>
										</ul>
									</td>
									<td><s:property value="#order.totalPrice()" /></td>
									<td><s:property value="#order.orderState" /></td>
									<td><s:a action="orderview" namespace="/user">
										<s:param name="id" value="#order.id" />订单详情</s:a>
										<s:if test="#order.orderState.canBeCancelByUser()"><br>
										<s:a action="ordercancel" namespace="/user"><s:param name="id" value="#order.id"/>取消订单</s:a>
										</s:if>
									</td>
								</tr>
							</table>
						</s:iterator>
						<!--  -->
						<div class="pagination pagination-centered">
							<ul>
								<li><s:if test="orders.currentPage > 1">
										<s:a action="order" namespace="/user">
											<s:param name="index" value="orders.currentPage-1" />
											<s:param name="size" value="orders.pageSize" />
	  			&lt;&lt;
	  		</s:a>
									</s:if> <s:else>
										<a>&lt;&lt;</a>
									</s:else></li>
								<s:iterator id="number" value="orders.pageNumberList">
									<s:if test="orders.currentPage != #number">
										<li><s:a action="order" namespace="/user">
												<s:param name="index" value="#number" />
												<s:param name="size" value="orders.pageSize" />
												<s:property value="#number" />
											</s:a></li>
									</s:if>
									<s:else>
										<li><a><s:property value="#number" /></a></li>
									</s:else>
								</s:iterator>
								<li><s:if test="orders.currentPage < orders.totalPage">
										<s:a action="order" namespace="/user">
											<s:param name="index" value="orders.currentPage+1" />
											<s:param name="size" value="orders.pageSize" />
	  			&gt;&gt;
	  		</s:a>
									</s:if> <s:else>
										<a>&gt;&gt;</a>
									</s:else></li>
							</ul>
						</div>
						<!--  -->
					</div>
				</div>
			</div>

		</div>
	</div>
	<div class="span3">
		<div class="user-summary">
			<div class="stitle">
				<i class="icon-user icon-white"></i> <s:a action="index" namespace="/user">用户信息</s:a>
			</div>
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
					<li>登录时间：<s:property
							value="#session['login_info'].formatLoginTime()" /></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="clear"></div>
<jsp:include page="../basic/basic-footer.jsp" />
