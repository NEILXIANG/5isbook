<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//tdD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.tdd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>商品详情</title>
<link rel="shortcut icon" type="image/png"
	href="admin/images/favicon.png">
</head>
<body>
	<!-- 标头 -->
	<s:include value="/admin/header.jsp"></s:include>
	<div class="container">
		<div class="current-side">
			当前位置： <a href="admin/goods/showEntities?id=${entity.category.id }">商品管理</a> <span>></span>
			<a  >商品详情</a>
		</div>
		<div class="goods-details">
			<table class="table table-hover table-condensed">
				<tr>
					<td width="80">商品 ISBN：</td>
					<td><s:property value="entity.getEntityValue('isbn')" /></td>
				</tr>
				<tr>
					<td>书名：</td>
					<td><s:property value=" entity.getEntityValue('title')" /></td>
				</tr>
				<tr>
					<td>作者：</td>
					<td><s:property value=" entity.getEntityValue('author')" /></td>
				</tr>
				<tr>
					<td>出版社：</td>
					<td><s:property value=" entity.getEntityValue('publisher')" /></td>
				</tr>
				<tr>
					<td>出版时间：</td>
					<td><s:property value=" entity.getEntityValue('pubdate')" /></td>
				</tr>
				<tr>
					<td>页数：</td>
					<td><s:property value=" entity.getEntityValue('pages')" /></td>
				</tr>
				<tr>
					<td>作者简介：</td>
					<td><s:property value=" entity.getEntityValue('author-intro')"
							escape="false" /></td>
				</tr>
				<tr>
					<td>书本简介：</td>
					<td><s:property value=" entity.getEntityValue('summary')"
							escape="false" /></td>
				</tr>
				<tr>
					<td>书本封面：</td>
					<td><img
						src='<s:property value="entity.getEntityValue('large_image')"/>' /></td>
				</tr>
				<tr>
					<td>价格和库存：</td>
					<td>
						<ul class="goods-price">
							<li><span>正版：</span> 价格：<s:property
									value="entity.getSinglePriceByEntityType('authorised_edition')" />
								库存： <input type="text" id="authorised_edition"
								value="<s:property
													value="entity.getEntityType('authorised_edition').getStorageCount()" />"
								class="num-input"> <a
								onClick="changeStoreCount(${entity.id},'authorised_edition')"
								class="btn btn-mini btn-info">保存</a></li>
							<li><span>盗版：</span> 价格：<s:property
									value="entity.getSinglePriceByEntityType('pirate_edition')" /> 库存：<input
								type="text" id="pirate_edition"
								value="<s:property value=" entity.getEntityType('pirate_edition').getStorageCount()" />"
								class="num-input"> <a
								onClick="changeStoreCount(${entity.id},'pirate_edition')"
								class="btn btn-mini btn-info">保存</a></li>
							<li><span>复印：</span> 价格：<s:property
									value="entity.getSinglePriceByEntityType('copy_edition')" /> 库存： <input
								type="text" id="copy_edition"
								value="<s:property
													value="entity.getEntityType('copy_edition').getStorageCount()" />"
								class="num-input"> <a
								onClick="changeStoreCount(${entity.id},'copy_edition')"
								class="btn btn-mini btn-info">保存</a></li>
						</ul>
					</td>
				</tr>
			</table>
		</div>
		<div style="clear: both;"></div>
		<div class="operate">
			<!-- 此处加个确认框吧，以防不小心清空了库存 -->
			<a
				href="admin/goods/info_detail?entityId=${entity.id}&show=false"
				class="btn btn-success">修改</a>
			<button onclick="clearStoreCount('${entity.id}')"
				class="btn btn-danger">清空库存</button>
		</div>
	</div>
	<div class="footer">
		<p>
			版权所有 © 2013 BY <a  >工作组</a>
		</p>
	</div>
	<script type="text/javascript" src="admin/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#test1").click(function() {
				$("#form1").show();
			});
		});
	</script>
	<script type="text/javascript" src="admin/js/goodsDetail.js"></script>
</body>
</html>