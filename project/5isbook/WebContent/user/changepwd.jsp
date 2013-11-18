<%
/*  
*	@author:TQ
*	个人密码修改
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="authz" uri="http://acegisecurity.org/authz" %>
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
			<li class=""><s:a action="index" namespace="/user/address">地址管理</s:a></li>
			<li class="active"><a href="javascript:void(0)">个人信息</a></li>
			<li class=""><s:a action="index" namespace="/cart">购物车</s:a></li>
			<li class="backHome"><a href="">返回首页</a></li>
		</ul>
	</div>
	<div class="span7"> 
		<ul class="nav nav-tabs myul">
			<li><s:a action="nomalinfo" namespace="/user/info">基本信息</s:a></li>
			<li class="active"><a href="#passwordInfo">修改密码</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="passwordInfo">
				<s:form cssClass="form-horizontal" action="changepassword" namespace="/user/info" method="post" id="passwordEditForm"> 
				   <s:if test="message != null"> 
				    <s:if test="validateResult">
				    	<div class="alert alert-error" id="message">
							<s:property value="message"/><a class="close" data-dismiss="alert">×</a> 
				    	</div>
				    </s:if>
				    <s:else>
				    	<div class="alert alert-success" id="message">
							<s:property value="message"/><a class="close" data-dismiss="alert">×</a> 
				    	</div>
				    </s:else>
			       </s:if>
			       <div class="control-group">
					   <label class="control-label" for="oldPassword"><span class="red">*</span>旧密码</label>
					   <div class="controls">
					     <input type="password" class="input-xlarge checked" name="originalPass" id="oldPassword" placeholder="输入旧密码" required />
						<span id="nametip" class="label label-important hide">旧密码不能为空</span>
					   </div> 
				   </div>
				   <div class="control-group"> 
				  	    <label class="control-label" for="newPassword"><span class="red">*</span>新密码</label>
					    <div class="controls">
					      <input type="password" class="input-xlarge checked" name="newPass" id="newPassword" placeholder="输入新密码"  required />
							<span id="nametip" class="label label-important hide">新密码不能为空</span>
							<span id="notmatchtip" class="label label-important hide">两次密码输入不一致</span>
							<s:fielderror name="newPass"/>&nbsp;
					    </div>
			     	</div>
					<div class="control-group">
						<label class="control-label" for="confirmPassword"><span class="red">*</span>确认密码</label>
							<div class="controls">
							<input type="password" class="input-xlarge checked" name="reNewPass" id="confirmPassword"  placeholder="重复输入密码"  required />
							<span id="nametip" class="label label-important hide">请再次输入密码以确认</span>
							<span id="notmatchtip" class="label label-important hide">两次密码输入不一致</span>
						</div>
					</div>
					<div class="btnGroup">
						<button id="passwordEditFormBtn" class="btn btn-primary" >保存</button>
						<button type="reset" class="btn">取消</button>
					</div>
				</s:form>
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
					<li>本次登陆ip:<s:property value="#session['login_info'].ip"/></li>
					<li>登录时间：<s:property value="#session['login_info'].formatLoginTime()"/></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="clear"></div>
 <jsp:include page="../basic/basic-footer.jsp" />
