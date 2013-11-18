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
<title>商品搜索</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<link rel="shortcut icon" type="image/png"
	href="admin/images/favicon.png">
<link href="admin/css/bootstrap.css" type="text/css" rel="stylesheet">
<link href="admin/css/cms.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" media="screen"
	href="/statics/css/bootstrap.min.css">
<link rel="stylesheet" media="screen"
	href="/statics/css/bootstrap-responsive.min.css">
<link rel="stylesheet" media="screen" href="/statics/css/common.css">
</head>
<body>
	<!-- 标头 -->
	<s:include value="/admin/header.jsp"></s:include>
	<div class="container">
		<div class="current-site">
			<div class="top-bar">
				<span>当前位置：</span> <a href="admin/index.jsp">首页</a> <span>></span><span>
					<a href="admin/goods/allCategory">商品管理</a> <span> >
				</span> <a  >商品搜索</a>
					<div class="input-append">
						<form action="search" method="post">
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
			<div class="content">
				<div class="search-title">
					<h1 class="title-bar">你的搜索结果 ${results }</h1>
					<div class="relative-result">
						<s:property value="message" />
					</div>
				</div>
				<%-- 	<s:if test="results!=null"> --%>
				<div class="page-bar">
					<!-- 分页 -->
					<ul class="cms-pagination">
						<s:iterator value="results.pageNumberList" var="page">
							<s:if test="results.currentPage!= #page">
								<li><s:a action="search">
										<s:param name="content" value="content" />
										<s:param name="attributeName" value="attributeName" />
										<s:param name="index" value="#page" />
										<s:param name="size" value="results.pageSize" />
										<s:property value="#page" />
									</s:a></li>
							</s:if>
							<s:else>
								<li><a class="current-page"><s:property value="#page" /></a></li>
							</s:else>
						</s:iterator>
					</ul>
					<div class="jump-page">
						<div class="jump-left">
							<span>共<s:property value="results.pageNumberList.size()" />页
							</span>
						</div>
						<div class="jump-right">
							<input type="text" name="jump" size="3" value="${index }"
								onkeydown="" id="jump" title="输入页码，按回车快速跳转" /> <a class="btn"
								type="button" onClick="go(${content},'${attributeName }')">Go</a>
						</div>
					</div>
					<ul class="cms-pagination">
						<s:iterator value="entitys.getPageNumberList()" var="page">
							<li><a
								href="admin/goods/showEntities?id=${item.id}&pageIndex=${page}"
								class="<s:if test="%{pageIndex==#page}">current-page</s:if>">${page
									}</a></li>
						</s:iterator>
					</ul>
				</div>
				<!-- 清除浮动 -->
				<div class="clear"></div>
				<div>
					<!-- 列表元素 -->
					<table class="search-table">
						<tr>
							<th>ISBN</th>
							<th>书名</th>
							<th>作者</th>
							<th>出版社</th>
							<th>详情</th>
						</tr>
						<s:iterator id="item" value="results.list">
							<tr>
								<td><s:property value='#item.getEntityValue("isbn")' /></td>
								<td><a
									href="admin/goods/info_detail?entityId=${id }&show=true"><s:property
											value='#item.getEntityValue("title")' /></a></td>
								<td><s:property value='#item.getEntityValue("author")' /></td>
								<td><s:property value='#item.getEntityValue("publisher")' /></td>
								<td><a
									href="admin/goods/info_detail?entityId=${id }&show=true">查看</a></td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<%-- </s:if> --%>
			</div>
		</div>
	</div>


	<div class="footer">
		<p>
			版权所有 © 2013 BY <a  >工作组</a>
		</p>
	</div>
	<script src="admin/js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="admin/js/bootstrap.js" type="text/javascript"></script>
	<script type="text/javascript">
		function go(content,attributeName) {
			var pageIndex = document.getElementById("jump").value;
			if(pageIndex>0)
			window.location.href = "admin/goods/search?content="+content+"&attributeName="+attributeName+"&size=10" +"&index=" + pageIndex;
		}
	</script>
</body>

</html>


