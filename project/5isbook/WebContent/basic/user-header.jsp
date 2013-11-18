<%
/*  
*	@author:TQ
*	个人中心公共页头
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
<meta name="description" content="我爱搜书" />
<meta name="keywords" content="我爱搜书,二手书,三边传媒,5isbook,旧书交易" />
<base href="<%=basePath%>">
<link rel="shortcut icon" type="image/png" href="statics/images/favicon.png">
<link rel="stylesheet" media="screen" href="statics/css/bootstrap.min.css">
<link rel="stylesheet" media="screen" href="statics/css/bootstrap-responsive.min.css">
<link rel="stylesheet" media="screen" href="statics/css/common.min.css">
</head>
<body>
	<header>
		<div class="header-wrap">
			<s:a action="index" namespace="/" title="回到主页"> <img class="logo" src="statics/images/logo.png" /></s:a>
		</div>
		<hr />
	</header>
	
