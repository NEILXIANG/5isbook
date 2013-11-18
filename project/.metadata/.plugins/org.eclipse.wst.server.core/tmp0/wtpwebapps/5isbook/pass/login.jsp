
<%
	/*  
	 *	@author:TQ
	 *	登陆页面
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page import="org.acegisecurity.AuthenticationException"%>
<%@ page import="org.acegisecurity.ui.AbstractProcessingFilter"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<link rel="stylesheet" media="screen" href="statics/css/bootstrap-responsive.min.css">
<link rel="stylesheet" media="screen" href="statics/css/common.css">
<!--[if lt IE 9]>
	<script type="text/javascript" src="statics/js/html5.js"></script>
	<script type="text/javascript" src="statics/js/PIE_IE678.js"></script>
<![endif]-->
</head>
<body>
	<header class="login-header">
		<s:a action="index" namespace="/" title="回到主页">
			<img class="logo" src="statics/images/logo.png" />
		</s:a>
	</header>
	<div class="login-content">
		<div class="big-img">
			<img src="statics/images/login_bg.jpg" alt="登录" />
		</div>
		<div class="login-box">
			<h3 class="green">我爱搜书登陆</h3>
			<s:if test="#parameters.login_error != null">
				<div class="alert">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					Login unsuccessful, try again.<BR> Reason:
					<%=((AuthenticationException) session
						.getAttribute(AbstractProcessingFilter.ACEGI_SECURITY_LAST_EXCEPTION_KEY))
						.getMessage()%>
				</div>
			</s:if>
			<s:elseif test="#parameters.message != null">
				<div class="alert">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<s:property value="#parameters.message" />
				</div>
			</s:elseif>
			<form action="j_acegi_security_check" method="POST">
				<input type="text" name="j_username" class="input" placeholder="输入您的账号" value='<s:property value="#attr.ACEGI_SECURITY_LAST_USERNAME"/>' /> <br /> 
					<input type="password" name="j_password" class="input" placeholder="输入您的密码" /> <br /> 
					<input type="radio" name="j_userkind" id="user" value="1" <%if(request.getParameter("login_error") == null || "1".equals(request.getParameter("login_error"))){%>checked="checked"<%}%> /> 
					<label for="user">用户</label> 
					<input type="radio" name="j_userkind" id="admin" value="0" <%if("0".equals(request.getParameter("login_error"))){%>checked="checked"<%}%>/> 
					<label for="admin">管理员</label> <br />
					<button class="login-submit btn btn-primary" type="submit">登陆</button>
				<br />
			</form>
			<a href="pass/register.jsp" class="btn">免费注册</a>
		</div>
	</div>
	<footer class="text-center">
		<div class="footer-slogan">我爱搜书,我爱阅读</div>
		<ul class="footer-info inline">
			<li><a href="javascript:void(0)">关于网站</a></li>
			<li><a href="javascript:void(0)">帮助中心</a></li>
			<li><a href="javascript:void(0)">联系我们</a></li>
			<li><a href="javascript:void(0)">兄弟连接</a></li>
		</ul>
		<a class="copyright" href="javascript:void(0)">Copyright 2003-2013, 版权所有三边传媒</a>
	</footer>
</body>
</html>
<script src="statics/js/bootstrap.min.js" type="text/javascript"></script>
<script src="statics/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="statics/js/common.js" type="text/javascript"></script>