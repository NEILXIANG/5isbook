<%
/*  
*	@author:TQ
*	登陆页面
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
<html>
<head>
<title>我爱搜书 - 寻找你的知识海洋</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<base href="<%=basePath%>">
<link rel="shortcut icon" type="image/png" href="statics/images/favicon.png">
<link rel="stylesheet" media="screen" href="statics/css/bootstrap.min.css">
<link rel="stylesheet" media="screen" href="statics/css/common.css">
<script src="statics/js/bootstrap.min.js" type="text/javascript"></script>
<script src="statics/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="statics/js/common.js" type="text/javascript"></script>
</head>
<body>
	<header class="login-header">
		<s:a action="index" namespace="/" title="回到主页"><img class="logo" src="statics/images/logo.png" /></s:a>
		<a href="pass/login.jsp" class="register-to-Log">登陆</a>
	</header>
	<div class="register-content">
		<h1 class="green">我爱搜书注册</h1>
		<form class="form-horizontal" method="post" action="pass/register">
			<div class="control-group">
				<label class="control-label" for="account">账号：</label>
				<div class="controls">
					<input type="text" class="input-xlarge" name="account" id="account" placeholder="新的账号" required value='<s:property value="account"/>'/>
					<s:fielderror fieldName="account"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="password">密码：</label>
				<div class="controls">
					<input type="password" class="input-xlarge" name="password" id="password" placeholder="输入您的密码" required />
					<s:fielderror fieldName="password" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="repassword">密码：</label>
				<div class="controls">
					<input type="password" class="input-xlarge" name="rePassword" id="repassword" placeholder="再次输入密码" required />
					<s:fielderror fieldName="rePassword"/>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">注册</button>
			<button type="reset" class="btn">清空</button>
		</form>
	</div>
	<footer class="text-center">
		<div class="footer-slogan">我爱搜书,我爱阅读</div>
		<ul class="footer-info inline">
			<li><a href="">关于网站</a></li>
			<li><a href="">帮助中心</a></li>
			<li><a href="">联系我们</a></li>
			<li><a href="">兄弟连接</a></li>
		</ul>
		<a class="copyright" href="">Copyright 2003-2013, 版权所有三边传媒</a>
	</footer>
</body>
</html>
