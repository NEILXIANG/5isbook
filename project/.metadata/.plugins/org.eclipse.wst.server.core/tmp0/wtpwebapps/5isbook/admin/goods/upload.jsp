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
<title>商品上传记录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<link rel="shortcut icon" type="image/png" href="admin/images/favicon.png">
</head>
<body>
	<!-- 标头 -->
	<s:include value="/admin/header.jsp"></s:include>
	<div class="container">
		<div class="current-side">
			当前位置： <a href="admin/goods/allCategory">商品管理</a> <span>></span>
			<a href="admin/goods/add.jsp">新增商品</a> <span>></span> <a  >商品上传</a>
		</div>
		<div class="info-content">
			<h1 class="title-bar">【上传成功的记录】</h1>
			<div class="all-uploads">
				总数：<span><s:property value="entrys.size()" /></span>
			</div>
			<!-- 清除浮动 -->
			<div class="clear"></div>
			<div class="table-above">
				<table class="upload-info-table">
					<tr>
						<th>序号</th>
						<th>ISBN</th>
						<th>是否已经在仓库</th>
						<th>商品库正版存码</th>
						<th>商品库盗版存码</th>
						<th>商品库复印版存码</th>
					</tr>
					<s:iterator value="entrys.entrySet()" id="entry" status="st">
						<tr>
							<td>${st.count }</td>
							<td><s:property value='#entry.key' /></td>
							<td><s:if test="#entry.value.existedBefore">是</s:if> <s:else>否</s:else></td>
							<td><s:property value="#entry.value.authorisedStoreCode" /></td>
							<td><s:property value="#entry.value.prirateStoreCode" /></td>
							<td><s:property value="#entry.value.copyStoreCode" /></td>
						</tr>
					</s:iterator>

				</table>
			</div>
			<s:if test="hasError">
				<h1 class="title-bar">【上传失败的记录】---EXCEL的内容有错</h1>
				<div class="all-uploads">
					错误的ISBN总数：<span><s:property value="errorIsbnList.size()" /></span>
				</div>
				<!-- 清除浮动 -->
				<div class="clear"></div>
				<div class="table-above">
					<table class="upload-info-table">
						<tr>
							<th>序号</th>
							<th>错误的商品 ISBN</th>
						</tr>
						<s:iterator value="errorIsbnList" status="st" var="error">
							<tr>
								<td>${st.count }</td>
								<td><s:property value="toString()" /></td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<div class="all-uploads">
					错误信息：<span><s:property value="errorMessage.size()" /></span>
				</div>
				<table class="upload-info-table">
					<tr>
						<th>序号</th>
						<th>错误信息</th>
					</tr>
					<s:iterator value="errorMessage" status="st" var="error">
						<tr>
							<td>${st.count }</td>
							<td><s:property value="toString()" /></td>
						</tr>
					</s:iterator>
				</table>
			</s:if>
		</div>
	</div>
	<div class="footer">
		<p>
			版权所有 © 2013 BY <a  >工作组</a>
		</p>
	</div>
</body>
</html>