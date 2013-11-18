<% 
/*  
*	@author:TQ
*	搜索显示页面
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../basic/basic-header.jsp" />
<div class="content">
	<div class="detail-index">
		<div class="title">
			<s:property value="root.name"/>
		</div>
		<s:iterator id="son" value="root.children">
			<ul class="list">
				<li><s:property value="#son.displayName"/>:</li>
				<s:iterator id="node" value="#son.children">
					<s:if test="#node.id==cid">
					<li style="color: red"><s:property value="#node.displayName"/></li><!-- 当前选中项 -->
					</s:if>
					<s:else>
					<li>
						<s:a action="category" namespace="/">
						<s:param name="id" value="#node.id"/>
						<s:property value="#node.displayName"/>
						</s:a>
					</li>					
					</s:else>
				</s:iterator>
			</ul>
		</s:iterator>
	</div>
	<div class="item-list">
		<s:iterator id="item" value="entitys.list">
		<figure>
			<s:a action="detail" namespace="/"><s:param name="id" value="#item.id"/>
				<img src='<s:property value="#item.getEntityValue('large_image')"/>' />
				<p><s:property value='#item.getEntityValue("title")'/></p>
			</s:a>
		</figure>
		</s:iterator>
	</div>
	<div class="pagination pagination-centered">
	  <ul>
	  	<li>
	  		<s:if test="entitys.currentPage > 1">
	  		<s:a action="category" namespace="/">
	  			<s:param name="id" value="id"/>
	  			<s:param name="pageIndex" value="entitys.currentPage-1"/>
	  			<s:param name="pageSize" value="entitys.pageSize"/>
	  			&lt;&lt;
	  		</s:a>
	  		</s:if>
	  		<s:else>
	  			<a>&lt;&lt;</a>
	  		</s:else>
	  	</li>
		<s:iterator id="number" value="entitys.pageNumberList">
	  		<s:if test="entitys.currentPage != #number">
	  			<li>
	  				<s:a action="category" namespace="/">
	  					<s:param name="id" value="id"/>
	  					<s:param name="pageIndex" value="#number"/>
	  					<s:param name="pageSize" value="entitys.pageSize"/>
	  					<s:property value="#number"/>
	  				</s:a>
	  			</li>
	  		</s:if>
	  		<s:else>
	  			<li><a><s:property value="#number"/></a></li>
	  		</s:else>
	  	</s:iterator>
	  	<li>
	  		<s:if test="entitys.currentPage < entitys.totalPage">
	  		<s:a action="category" namespace="/">
	  			<s:param name="id" value="id"/>
	  			<s:param name="pageIndex" value="entitys.currentPage+1"/>
	  			<s:param name="pageSize" value="entitys.pageSize"/>
	  			&gt;&gt;
	  		</s:a>
	  		</s:if>
	  		<s:else>
	  			<a>&gt;&gt;</a>
	  		</s:else>
	  	</li>
	  </ul>
	</div>
</div>
<jsp:include page="../basic/basic-footer.jsp" />