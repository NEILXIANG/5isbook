<%
/*  
*	@author:TQ
*	个人地址
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
			<li class="active"><a href="javascript:void(0)">地址管理</a></li>
			<li class=""><s:a action="nomalinfo" namespace="/user/info">个人信息</s:a></li>
			<li class=""><s:a action="index" namespace="/cart">购物车</s:a></li>
			<li class="backHome"><a href="">返回首页</a></li>
		</ul>
	</div>
	<div class="span7">
		<ul class="nav nav-tabs myul">
			<li class="active"><a href="javascript:void(0)" data-toggle="tab">收货地址</a></li>
		</ul>
		<div class="tab-content">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th width="60">姓名</th>
						<th width="400">地址</th>
						<th width="100">联系电话</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="address" value="addresses">
					<tr>
						<td><s:property value="#address.realName"/></td>
						<td><s:property value="#address.address"/></td>
						<td><s:property value="#address.longTel"/></td>
						<td>
							<!-- 直接在url后绑定id? -->
							<s:a action="modify" namespace="/user/address"><s:param name="id" value="#address.id"/>修改</s:a>&nbsp;
							<s:a action="delete" namespace="/user/address" onclick="javascript:if(!confirm('确定删除吗')){return false;}">
							<s:param name="id" value="#address.id"/>	
								删除
							</s:a>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
			<h4>新增地址</h4>
			<hr />
			<s:form cssClass="form-horizontal" action="add" namespace="/user/address" method="post" id="addressBoxForm">
				<div class="control-group">
					<label class="control-label" for="name"><span class="red">*</span>姓名</label>
					<div class="controls">
						<input type="text" class="input-xlarge checked"
							name="realName" id="name" placeholder="请使用真实姓名，方便配送" value="" /> 
						<span id="nametip" class="label label-important hide">姓名不能为空 </span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="address"><span class="red">*</span>地址</label>
					<div class="controls">
						<textarea type="text" class="input-xlarge checked" 
							name="address" id="address" placeholder="eg:华南农业大学华山区xx栋xxx" 
							></textarea>
						<span id="nametip" class="label label-important hide">地址不能为空</span>
					</div>
				</div>
				<div class="control-group"> 
					    <label class="control-label" for="phoneNum">长号/短号</label>
					    <div class="controls">
					      <input type="text" class="input" name="longTel" id="phoneNum" placeholder="长号" 
					      value="" />
					      <span class="add-on">/</span>
					      <input type="text" class="input" name="shortTel" id="phoneNum" placeholder="短号" 
					      value="" />
					    </div>
				    </div>
				 <div class="btnGroup">
					<button type="submit" id="addressBoxFormBtn" class="btn btn-primary">保存</button>
					<button type="reset" class="btn">重置</button>
				</div>
			</s:form>
		</div>
		<br />
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
