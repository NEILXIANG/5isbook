<%
/*  
*	@author:TQ
*	前台公共页头
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
<html>
<head>
<title>我爱搜书 - 寻找你的知识海洋</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="我爱搜书" />
<meta name="keywords" content="我爱搜书,二手书,三边传媒,5isbook,旧书交易" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" >
<base href="<%=basePath%>">
<link rel="shortcut icon" type="image/png" href="statics/images/favicon.png">
<link rel="stylesheet" media="screen" href="statics/css/bootstrap.min.css">
<link rel="stylesheet" media="screen" href="statics/css/bootstrap-responsive.min.css">
<link rel="stylesheet" media="screen" href="statics/css/common.min.css">
<!--[if lt IE 9]>
	<script type="text/javascript" src="statics/js/PIE_IE678.js"></script>
<![endif]-->
</head>
<body>
	<header>
		<div class="header-wrap">
			<s:a action="index" namespace="/" title="回到主页"> <img class="logo" src="statics/images/logo.png" /></s:a>
			<ul class="inline">
				<li class="header-tab"><s:a action="index" namespace="/"> <span>我爱搜书</span> <br />
						<small>search for my books</small>
				</s:a></li>
				
				<li class="header-tab"><a href="javascript:void(0)"> <span>慈善捐书</span> <br />
						<small>express my helps</small>
				</a></li>
				<li class="header-tab"><a href="javascript:void(0)"> <span>公益活动</span> <br />
						<small>donate some good ideas</small>
				</a></li>

			</ul>
		</div>
	</header>
	<div class="front-bar">
        <authz:authorize ifAllGranted="ROLE_ANONYMOUS">
  			<div class="log"><a href="pass/login.jsp"><i class="icon-user icon-white"></i>登陆</a></div>
        </authz:authorize>
		<authz:authorize ifNotGranted="ROLE_ANONYMOUS">		
		<div class="log">
			<s:a action="index" namespace="/user"><i class="icon-user icon-white"></i>个人中心</s:a>
		    <div class="logined-opt">
	    		<s:a action="index" namespace="/user"><img src="statics/images/userlogo.jpg" alt=""/></s:a>
	    		<p><s:property value="#session['NICK_NAME']" />，你好！</p>
	    		<table class="table table-condensed">
	    			<tr><td><s:a action="order" namespace="/user">我的订单</s:a></td><td><s:a action="index" namespace="/cart">购物车</s:a></td></tr>
	    			<tr><td><s:a action="nomalinfo" namespace="/user/info">修改信息</s:a></td><td><a href="j_acegi_logout">退出登录</a></td></tr>
	    		</table>
		    </div>
		</div>
	    </authz:authorize>
	    <span class="front-bar-opt">
			<s:a action="index" namespace="/cart"><img	title="进入购物车" id="buycar" src="statics/images/buycar.png" /></s:a> 
		</span>
		<s:form cssClass="searchForm" cssClass="navbar-search" method="post" action="search" namespace="/">
			<select name="attributeName">
				<option value="isbn">ISBN</option>
				<option value="title">书名</option>
				<option value="author">作者</option>
				<option value="publisher">出版社</option>
			</select>
			<input type="text" class="input" name="content" placeholder="搜索你喜欢的" />
			<button class="btn btn-primary search-btn">
				<i class="icon-search icon-white"></i>
			</button>
		</s:form>
	</div>
