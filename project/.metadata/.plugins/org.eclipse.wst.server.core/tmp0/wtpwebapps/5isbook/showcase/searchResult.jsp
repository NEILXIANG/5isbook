<% 
/*  
*	@author:TQ
*	搜索结果页面
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<jsp:include page="../basic/basic-header.jsp" />
<div class="content">
	<div class="search-title">
		<h1>你的搜索结果</h1>
		 <div class="relative-result"><s:property value="message"/></div>
	</div>
	<div class="search-list">
		<s:iterator id="item" value="results.list">	
		<div class="item">
			<img src='<s:property value="#item.getEntityValue('large_image')"/>' />
			<div class="title">
				<s:a action="detail" namespace="/">
					<s:param name="id" value="#item.id"/>
					<s:property value='#item.getEntityValue("title")'/>
				</s:a>
			</div>
			<div class="summary">
				<ul>
					<li>作者：<s:property value='#item.getEntityValue("author")'/></li>
					<li>出版社：<s:property value='#item.getEntityValue("publisher")'/></li>
					<li>ISBN：<s:property value='#item.getEntityValue("isbn")'/></li>
				</ul>
			</div>
			<div class="price">
				<!-- 正版价 -->
				<span class="red">￥<s:property value='#item.getSinglePriceByEntityType("authorised_edition")'/></span><br />
				<s:a cssClass="btn btn-small btn-primary" action="detail" namespace="/"><s:param name="id" value="#item.id"/>查看详情</s:a>
			</div>
		</div>
		</s:iterator>
	</div>
	<s:if test="results != null">
	<div class="pagination pagination-centered">
	  <ul>
	  	<li>
	  		<s:if test="results.currentPage > 1">
	  		<s:a action="search" namespace="/">
	  			<s:param name="content" value="content"/>
	  			<s:param name="attributeName" value="attributeName"/>
	  			<s:param name="index" value="results.currentPage-1"/>
	  			<s:param name="size" value="results.pageSize"/>
	  			&lt;&lt;
	  		</s:a>
	  		</s:if>
	  		<s:else>
	  			<a>&lt;&lt;</a>
	  		</s:else>
	  	</li>
		<s:iterator id="number" value="results.pageNumberList">
	  		<s:if test="results.currentPage != #number">
	  			<li>
	  				<s:a action="search" namespace="/">
	  					<s:param name="content" value="content"/>
	  					<s:param name="attributeName" value="attributeName"/>
	  					<s:param name="index" value="#number"/>
	  					<s:param name="size" value="results.pageSize"/>
	  					<s:property value="#number"/>
	  				</s:a>
	  			</li>
	  		</s:if>
	  		<s:else>
	  			<li><a><s:property value="#number"/></a></li>
	  		</s:else>
	  	</s:iterator>
	  	<li>
	  		<s:if test="results.currentPage < results.totalPage">
	  		<s:a action="search" namespace="/">
	  			<s:param name="content" value="content"/>
	  			<s:param name="attributeName" value="attributeName"/>
	  			<s:param name="index" value="results.currentPage+1"/>
	  			<s:param name="size" value="results.pageSize"/>
	  			&gt;&gt;
	  		</s:a>
	  		</s:if>
	  		<s:else>
	  			<a>&gt;&gt;</a>
	  		</s:else>
	  	</li>
	  </ul>
	</div>
	</s:if>
</div>
<jsp:include page="../basic/basic-footer.jsp" />