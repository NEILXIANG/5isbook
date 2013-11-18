<%
/*  
*	@author:TQ
*	个人详细信息修改
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
			<li class=""><s:a action="index" namespace="/user/address">地址管理</s:a></li>
			<li class="active"><a href="javascript:void(0)">个人信息</a></li>
			<li class=""><s:a action="index" namespace="/cart">购物车</s:a></li>
			<li class="backHome"><a href="">返回首页</a></li>
		</ul>
	</div>
	<div class="span7">
		<ul class="nav nav-tabs myul">
			<li class="active"><a href="#detailInfo">基本信息</a></li>
			<li><a href="user/changepwd.jsp">修改密码</a></li>
		</ul>
		<div class="tab-content"> 
			<div class="tab-pane active" id="detailInfo">
				<s:form cssClass="form-horizontal" action="nomalinfo" namespace="/user/info" id="detailEditForm" >
				<input type="hidden" name="formFlag" value="1"/>
				
				<!-- message 
					    <div class="alert alert-error" id="message">
							保存失败！<a class="close" data-dismiss="alert">×</a> 
					    </div>
				-->
				<s:if test="message != null">
				    <div class="alert alert-success" id="message">
						<s:property value="message"/><a class="close" data-dismiss="alert">×</a> 
					</div>
				</s:if>
					<!-- 账号,不能修改 -->
					<div class="control-group">
						<label class="control-label" for="account">邮箱</label>
						<div class="controls">
							<span class="input-xlarge uneditable-input" id="account">
								<s:property value="#session['ACEGI_SECURITY_LAST_USERNAME']"/>
							</span>
						</div>
					</div>
					<!-- 亲昵 -->
					<div class="control-group">
						<label class="control-label" for="nickname"><span class="red">*</span>昵称</label>
						<div class="controls">
							<input type="text" class="input-xlarge checked" 
								name="nickName" id="nickname" placeholder="字母，数字，汉字" value='<s:property value="nickName"/>' required/> 
							<span id="nametip"	class="label label-important hide">昵称不能为空</span>
						</div>
					</div>
					<!-- 性别 -->
					<div class="control-group">
						<label class="control-label" for="sex">姓别</label>
						<div class="controls">
							<select id="sex" name="gender">
								<s:if test="gender == null || gender.equals('man')">
									<option value="man" selected="selected">男</option>
									<option value="women">女</option>
								</s:if>
								<s:else>
									<option value="man">男</option>
									<option value="women" selected="selected">女</option>
								</s:else>
							</select>
						</div>
					</div>
					<!-- 生日 -->
				    <div class="control-group">
						<label class="control-label" for="birth">出生日期</label>
						<div class="controls">
							<input type="Date" class="input-xlarge" id="birth" name="birthday" value='<s:property value="birthday"/>'
								 placeholder="请输入生日[yyyy/mm/dd]" />
						</div>
				    </div>
				    <!-- QQ -->
				    <div class="control-group">
					    <label class="control-label"  for="QQ">QQ</label>
					    <div class="controls">
					      <input type="text"  name="qq" class="input-xlarge" id="QQ" placeholder="请输入你的Q号" 
					      value='<s:property value="qq"/>' />
					    </div>
				    </div>
				    <div class="btnGroup">
						<button type="submit" id="detailEditFormBtn" class="btn btn-primary">保存</button>
						<button type="reset" class="btn">取消</button>
					</div>
				</s:form>
			</div>
		</div>
	</div>
	<div class="span1">
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
