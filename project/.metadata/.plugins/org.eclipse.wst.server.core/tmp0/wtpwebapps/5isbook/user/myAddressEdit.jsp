<%
/*  
*	@author:TQ
*	个人地址修改
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
			<li class=""><s:a action="order" namespace="/user">我的订单</s:a></li>
			<li class="active"><s:a action="index" namespace="/user/address">地址管理</s:a></li>
			<li class=""><s:a action="nomalinfo" namespace="/user/info">个人信息</s:a></li>
			<li class=""><s:a action="index" namespace="/cart">购物车</s:a></li>
			<li class="backHome"><a href="">返回首页</a></li>
		</ul>
	</div>
	<div class="span7">
		<ul class="nav nav-tabs myul">
			<li class="active"><a href="javascript:void(0)">地址修改</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active">
				<s:form cssClass="form-horizontal" action="modify" namespace="/user/address" method="post" id="addressEditForm">
					<!-- 隐藏地址id -->
					<input type="hidden" name="id" value="<s:property value='id'/>" />
					<input type="hidden" name="formFlag" value="1"/>
					<div class="control-group">
						<label class="control-label" for="name"><span class="red">*</span>姓名</label>
						<div class="controls">
							<input type="text" class="input-xlarge checked" 
								name="realName" id="name" placeholder="为方便联系，请用真实姓名" value='<s:property value="realName"/>' /><s:fielderror fieldName="realName"/>
							<span id="nametip" class="label label-important hide">姓名不能为空</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="address"><span class="red">*</span>地址</label>
						<div class="controls">
							<textarea type="text" class="input-xlarge checked" 
								name="address" id="address" placeholder="eg:华南农业大学华山区xx栋xxx"><s:property value="address"/></textarea><s:fielderror fieldName="address"/>
							<span id="nametip" class="label label-important hide">地址不能为空</span>
						</div>
					</div>
					<div class="control-group"> 
						    <label class="control-label" for="phoneNum">长号/短号</label>
						    <div class="controls">
						      <input type="text" class="input" name="longTel" id="phoneNum" placeholder="长号" 
						      value='<s:property value="longTel"/>' />
						      <span class="add-on">/</span>
						      <input type="text" class="input" name="shortTel" id="phoneNum" placeholder="短号" 
						      value='<s:property value="shortTel"/>' />
						    </div>
						    <s:fielderror fieldName="longTel"/><s:fielderror fieldName="shortTel"/>
					 </div>
					 <div class="btnGroup">
						<button type="submit" id="addressEditFormBtn" class="btn btn-primary">
							保存</button>
						<button type="reset" class="btn">重置</button>
					</div>
				</s:form>
			</div>
		</div>
	</div>
	<div class="span3">
		<div class="user-summary">
			<div class="stitle"><i class="icon-user icon-white"></i><s:a action="index" namespace="/user">用户信息</s:a></div>
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
					<li>本次登陆ip:<s:property value="#session['login_info'].ip"/></li>
					<li>登录时间：<s:property value="#session['login_info'].formatLoginTime()"/></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="clear"></div>
 <jsp:include page="../basic/basic-footer.jsp" />
