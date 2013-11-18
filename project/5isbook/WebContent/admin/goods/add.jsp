<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增商品</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" type="image/png" href="admin/images/favicon.png">
</head>
<body>
	<s:include value="/admin/header.jsp"></s:include>
	<div class="container">
		<div class="current-side">
			当前位置： <a href="admin/goods/allCategory">商品管理</a> <span>></span>
			<a  >新增商品</a>
		</div>
		<div class="info-content">
			<h1 class="title-bar">【新增商品 - 上传】</h1>
			<s:form enctype="multipart/form-data" method="post" action="add" namespace="/admin/goods"
				name="fileUp">
				<input type="file" name="file" />
				<s:fielderror fieldName="file" />
				<br />
				<s:submit value="submit" class="btn btn-info file-btn"></s:submit>
			</s:form>

			<h2 class="title-bar">注意：只能上传EXCEL文件的格式，EXCEL内容格式如下 :
				ISBN格式需要为文本格式，分类编号需按照规定填写<br>
			<s:a action="nodes" namespace="/nodeinfo" target="_blank">查看分类编号</s:a></h2>
			<img alt="事例" title="样例" src="admin/images/excelFormat.jpg">
		</div>
	</div>
	<div class="footer">
		<p>
			版权所有 © 2013 BY <a  >工作组</a>
		</p>
	</div>
	<script src="admin/js/uploadfile.js" type="text/javascript"></script>
</body>
</html>