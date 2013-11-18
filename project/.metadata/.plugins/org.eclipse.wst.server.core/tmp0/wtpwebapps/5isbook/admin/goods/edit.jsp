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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<link href="admin/js/xheditor-1.2.1/demos/common.css" rel="stylesheet"
	type="text/css" media="screen" />
<title>修改商品信息</title>
<link rel="shortcut icon" type="image/png" href="admin/images/favicon.png">
</head>
<body>
	<s:include value="/admin/header.jsp"></s:include>
	<div class="container">
		<div class="current-side">
			当前位置： <a href="admin/goods/showEntities?id=${entity.category.id }">商品管理</a> <span> ></span> <a href="admin/goods/info_detail?entityId=${entityId}&show=true">商品详情</a> <span>></span>
			<a  >详情修改</a>
		</div>
		<div class="details-content">
			<form action="admin/goods/info_edit" method="post" name="details-form"
				onsubmit="#" class="details-form">
				<input type="hidden" name="entityId"
					value="<s:property value="entityId" />">
				<dl>
					<dt>商品 ISBN：</dt>
					<dd>
						<s:textfield type="text" name="isbn"
							value="%{entity.getEntityValue('isbn')}" />
					</dd>
				</dl>
				<dl>
					<dt>书名：</dt>
					<dd>
						<s:textfield type="text" name="title"
							value="%{entity.getEntityValue('title')}" />
					</dd>
				</dl>
				<dl>
					<dt>作者：</dt>
					<dd>
						<s:textfield type="text" name="author"
							value="%{entity.getEntityValue('author')}" />
					</dd>
				</dl>
				<dl>
					<dt>出版社：</dt>
					<dd>
						<s:textfield type="text" name="publisher"
							value="%{entity.getEntityValue('publisher')}" />
					</dd>
				</dl>
				<dl>
					<dt>出版时间：</dt>
					<dd>
						<s:textfield type="text" name="pubdate"
							value="%{entity.getEntityValue('pubdate')}" />
					</dd>
				</dl>
				<dl>
					<dt>页数：</dt>
					<dd>
						<s:textfield type="text" name="pages"
							value="%{entity.getEntityValue('pages')}" />
					</dd>
				</dl>
				<dl>
					<dt>作者简介：</dt>
					<dd>
						<div class="editor">
							<textarea class="xheditor" rows="20" cols="80" name="authorIntro"
								id="authorIntro" style="width: 780px;">
								<s:property value="%{entity.getEntityValue('author-intro')}" escape="false"/>
							</textarea>
						</div>
					</dd>
				</dl>
				<dl>
					<dt>书本简介：</dt>
					<dd>
						<div class="editor">
							<textarea class="xheditor" rows="20" cols="80" name="summary"
								id="summary" style="width: 780px;">
								<s:property value="%{entity.getEntityValue('summary')}" escape="false"/>
							</textarea>
						</div>
					</dd>
				</dl>
				<dl>
					<dt></dt>
					<dt>
						<s:submit value="保存" cssClass="btn btn-success"></s:submit>
					</dt>
				</dl>
			</form>
		</div>
		<div style="clear: both;"></div>
		<div class="operate">
			<%-- <a
				href="admin/goods/admin/goods/edit<s:property value="entityId" />"
				class="btn btn-success">保存</a> --%>
			<!--无需用到删除 
			 <a  
				class="btn btn-danger">删除</a> -->
		</div>
	</div>
	<div class="footer">
		<p>
			版权所有 © 2013 BY <a  >工作组</a>
		</p>
	</div>
	<script src="admin/js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="admin/js/bootstrap.js" type="text/javascript"></script>
	<script src="admin/js/xheditor-1.2.1/jquery/jquery-1.4.4.min.js"
		type="text/javascript"></script>
	<script src="admin/js/xheditor-1.2.1/xheditor-1.2.1.min.js"
		type="text/javascript"></script>
	<script src="admin/js/xheditor-1.2.1/xheditor_lang/zh-cn.js"
		type="text/javascript"></script>
</body>
</html>