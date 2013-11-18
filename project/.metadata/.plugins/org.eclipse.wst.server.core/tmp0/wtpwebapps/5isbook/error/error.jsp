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
<title>hello-页面未找到 </title>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<base href="<%=basePath%>">
<link rel="shortcut icon" type="image/png" href="statics/images/favicon.png">
<link rel="stylesheet" type="text/css" href="statics/css/error.css" media="screen" />
</head>
<body>
<div id="container"><img class="png" src="statics/images/404.png" /> <img class="png msg" src="statics/images/404_msg.png" />
  <p><s:a action="index" namespace="/"><img class="png" src="statics/images/404_to_index.png" /></s:a> </p>
</div>
<div id="cloud" class="png"></div>
<pre style="DISPLAY: none"></pre>
</body>
</html>