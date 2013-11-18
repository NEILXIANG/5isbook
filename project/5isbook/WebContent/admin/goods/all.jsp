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
<title>商品管理</title>
<link rel="shortcut icon" type="image/png"
	href="admin/images/favicon.png">
<link href="admin/css/bootstrap.css" type="text/css" rel="stylesheet">
<link href="admin/css/cms.css" type="text/css" rel="stylesheet">
</head>
<body>
	<!-- 标头 -->
	<s:include value="/admin/header.jsp"></s:include>
	<div class="container">
		<div class="top-bar">
			<div class="current-site">
				<span>当前位置：</span> <a href="admin">首页</a> <span>></span> <a
					href="admin/goods/allCategory">商品管理</a>
			</div>

			<div class="input-append">
				<form action="admin/goods/search" method="post">
					<select name="attributeName" class="input-select">
						<option value="isbn">ISBN</option>
						<option value="title">书名</option>
						<option value="author">作者</option>
						<option value="publisher">出版社</option>
					</select> <input type="text" class="span2" name="content"
						placeholder="搜索你喜欢的" /> <input type="submit" class="btn"
						type="button" value="搜索" />
				</form>
			</div>
		</div>
		<br />
		<div class="goods-nav">
			<ul class="nav nav-pills">
				<s:iterator id="top" value="#session.categoryList">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <s:property value="#top.displayName" />
							<!-- 向下箭头 --> <b class="caret"></b>
					</a>
						<ul id="" class="dropdown-menu">
							<s:iterator id="second" value="top.children">
								<li class="dropdown-submenu"><a  ><s:property
											value="#second.displayName" /></a>
									<ul class="dropdown-menu">
										<s:iterator id="item" value="#second.children">
											<li><a
												href="admin/goods/showEntities?id=${item.id}"><s:property
														value="#item.displayName" /></a></li>
										</s:iterator>
									</ul></li>
							</s:iterator>
						</ul></li>
				</s:iterator>
			</ul>
		</div>
		<s:if test="entitys.getList()!=null">
			<div class="current-side">
				当前目录： <a ><s:property
						value="category.getParent().getParent().getDisplayName()" /></a> <span>></span>
				<a ><s:property
						value="category.getParent().getDisplayName()" /></a> <span>></span> <a
					><s:property value="category.getDisplayName()" /></a>
			</div>
		</s:if>
		<div class="goods-content">
			<div class="add-goods">
				<a class="btn btn-success" href="admin/goods/add.jsp">新增商品</a>
			</div>
		</div>
		<s:if test="entitys.getList()!=null">

			<div class="page-bar">
				<div class="jump-page">
					<div class="jump-left">
						<span>共<s:property value="entitys.totalPage" />页
						</span>
					</div>
					<div class="jump-right">
						<input type="text" name="jump" size="3" value="${pageIndex }"
							onkeydown="" id="jump" title="输入页码，按回车快速跳转" /> <a class="btn"
							type="button" onClick="go(${id})">Go</a>
					</div>
				</div>
				<ul class="cms-pagination">
					<s:iterator value="entitys.getPageNumberList()" var="page">
						<li><a
							href="admin/goods/showEntities?id=${category.id}&pageIndex=${page}"
							class="<s:if test="%{pageIndex==#page}">current-page</s:if>">${page
								}</a></li>
					</s:iterator>
				</ul>
			</div>
			<div class="clear"></div>
			<div>
				<!-- 列表元素 -->
				<table class="goods-table table-condensed">
					<tr>
						<th>商品 ID</th>
						<th>ISBN</th>
						<th>书名</th>
						<th>作者</th>
						<th>出版社</th>
						<th>价格/元</th>
						<th>库存数量/本(正版，盗版，复印版)</th>
					</tr>
					<s:iterator value="entitys.getList()" var="entity">
						<tr>
							<td><s:property value="getId()" /></td>
							<td><s:property value="getEntityValue('isbn')" /></td>
							<td><a title="点击查看详情"
								href="admin/goods/info_detail?entityId=${id }&show=true"><s:property
										value="getEntityValue('title')" /></a></td>
							<td><s:property value="getEntityValue('author')" /></td>
							<td><s:property value="getEntityValue('publisher')" /></td>
							<td>
								<ul class="goods-price">
									<li><span>正版：</span> <em><s:property 
												value="getSinglePriceByEntityType('authorised_edition')" /></em></li>
									<li><span>盗版：</span> <em><s:property
												value="getSinglePriceByEntityType('pirate_edition')" /></em></li>
									<li><span>复印：</span> <em><s:property
												value="getSinglePriceByEntityType('copy_edition')" /></em></li>
								</ul>
							</td>
							<td>
								<!-- 库存，修改库存 -->
								<ul class="goods-num">
									<li><input type="text"
											id="authorised_edition"
											value="<s:property
													value="getEntityType('authorised_edition').getStorageCount()" />"
											class="num-input"> <a class="btn btn-mini btn-info"
											onClick="changeStoreCount(${id},'authorised_edition')">保存</a></li>
									<li><input type="text" id="pirate_edition"
										value="<s:property
													value=" getEntityType('pirate_edition').getStorageCount()" />"
										class="num-input"> <a  
										onClick="changeStoreCount(${id},'pirate_edition')"
										class="btn btn-mini btn-info">保存</a></li>
									<li><input type="text" id="copy_edition"
										value="<s:property
													value=" getEntityType('copy_edition').getStorageCount()" />"
										class="num-input"> <a  
										onClick="changeStoreCount(${id},'copy_edition')"
										class="btn btn-mini btn-info">保存</a></li>
								</ul>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</s:if>
	</div>

	<div class="footer">
		<p>
			版权所有 © 2013 BY <a  >工作组</a>
		</p>
	</div>
	<script src="admin/js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="admin/js/bootstrap.js" type="text/javascript"></script>
	<script src="admin/js/goodsManager.js" type="text/javascript"></script>
</body>
</html>