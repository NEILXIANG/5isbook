<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>更改密码</title>
</head>
<body>
	<!-- 标头 -->
	<s:include value="/admin/header.jsp"></s:include>
	<div class="container">
		<div class="current-side">
			当前位置： <a href="admin/user/myInfo">我的账号</a> <span>></span> <a
				 >密码管理</a>
		</div>
		<div class="info-content">
			<h1 class="title-bar">【管理员密码修改】 ${message }</h1>
			<form action="admin/changePsw" method="post"
				name="cmsChangePsw" onsubmit="">
				<dl class="input-list">
					<dt>旧密码：</dt>
					<dd>
						<input type="password" value="" name="oldPassword"
							id="oldPassword" required /> <span><s:fielderror
								fieldName="oldPassword" /></span>
					</dd>
					<dt>新密码：</dt>
					<dd>
						<input type="password" value="" name="newPassword"
							id="newPassword" required /> <span><s:fielderror
								fieldName="newPassword" /></span>
					</dd>
					<dt>重复新密码：</dt>
					<dd>
						<input type="password" value="" name="newPasswordRepeat"
							id="newPasswordRepeat" required> <span><s:fielderror
								fieldName="newPasswordRepeat" /></span>
					</dd>
				</dl>
				<input type="hidden" name="adminAccount"
					value="<s:property value="#session['ACEGI_SECURITY_LAST_USERNAME']"/>" />
				<input type="submit" value="提交修改" class="btn btn-success add-btn" />
			</form>
		</div>
	</div>
	<%
		session.removeAttribute("ok");
	%>

</body>
</html>