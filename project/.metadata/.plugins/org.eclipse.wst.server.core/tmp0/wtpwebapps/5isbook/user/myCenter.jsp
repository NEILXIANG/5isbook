
<%
	/*  
	 *	@author:TQ
	 *	个人中心
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
			<li class="active"><a href="javascript:void(0)">我的首页</a></li>
			<li class=""><s:a action="order" namespace="/user">我的订单</s:a></li>
			<li class=""><s:a action="index" namespace="/user/address">地址管理</s:a></li>
			<li class=""><s:a action="nomalinfo" namespace="/user/info">个人信息</s:a></li>
			<li class=""><s:a action="index" namespace="/cart">购物车</s:a></li>
			<li class="backHome"><a href="">返回首页</a></li>
		</ul>
	</div>
	<div class="span7">
		<ul class="nav nav-tabs myul">
			<li class="active"><a href="javascript:void(0)">个人首页</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active">
				<Script Language="JavaScript">
				<!--
					var text = "";
					day = new Date();
					time = day.getHours();
					if ((time >= 0) && (time < 7))
						text = "夜猫子，要注意身体哦！ ";
					if ((time >= 7) && (time < 12))
						text = "今天的阳光真灿烂啊，你那个朋友呢？";
					if ((time >= 12) && (time < 14))
						text = "午休时间。您要保持睡眠哦！";
					if ((time >= 14) && (time < 18))
						text = "祝您下午工作愉快！ ";
					if ((time >= 18) && (time <= 22))
						text = "您又来了，可别和MM聊太久哦！";
					if ((time >= 22) && (time < 24))
						text = "您应该休息了！";
					document.write("<h3>" + text + "</h3>");
				//--->
				</Script>
				<p>欢迎来到用户中心。</p>
				<hr />
				<p>
					<strong>通告栏：</strong><br /> 今天优惠：暂无记录
				</p>
			</div>
		</div>
	</div>
	<div class="span3">
		<div class="user-summary">
			<div class="stitle">
				<i class="icon-user icon-white"></i>
				<s:a action="index" namespace="/user">用户信息</s:a>
			</div>
			<div class="scontent">
				<ul>
					<li><img src="statics/images/userlogo.jpg" alt="" /> <s:a
							action="nomalinfo" cssClass="edit" namespace="/user/info">个人信息</s:a></li>
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
					--------------------------------------- 
					<li>本次登陆ip:<s:property value="#session['login_info'].ip" /></li>
					<li>本次登录时间：<s:property value="#session['login_info'].formatLoginTime()" /></li>
					---------------------------------------
					<li>上次登陆ip:<s:property value="#session['last_login_info'].ip" /></li>
					<li>上次登录时间：<s:property value="#session['last_login_info'].formatLoginTime()" /></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="clear"></div>
<jsp:include page="../basic/basic-footer.jsp" />
