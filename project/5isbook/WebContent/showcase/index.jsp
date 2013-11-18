<% 
/*  
*	@author:TQ
*	前台主页
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %> 
<jsp:include page="../basic/basic-header.jsp" />
<div class="bg">
	<div class="content-show">
	</div>
	<div class="sidebar">
		<div class="sidebar-title" onselectstart="return false">
			<h3>图书导航</h3>
		</div>
		<ul class="sidebar-list">
			<li>
				<s:iterator id="top" value="categoryList">
				<div class="item">
					<div class="item-detail">
						<div>
							<div class="item-label">
								<s:property value="#top.displayName"/>
							</div>
						</div>
						<div class="hide item-detail-second">
							<ul>
								<s:iterator id="second" value="top.children">
									<li class="items"> 
									<a>
									<h4 title="<s:property value="#second.displayName"/>"><s:property value="#second.displayName"/></h4>
									</a>
									<ul class="itemscontent">
										<s:iterator id="item" value="#second.children">
											<li>
												<s:a action="category" namespace="/">
													<s:param name="id" value="#item.id"/>
													<s:property value="#item.displayName"/>
												</s:a>
											</li>
										</s:iterator>
									</ul>
									</li> 
								</s:iterator>
							</ul>
						</div>
					</div>
				</div>
				</s:iterator>
			</li>
		</ul>
	</div>
	<div class="fixMT30"></div>
	<article class="content">
		<article class="main-content">
			<ul>
			<s:iterator id="showItem" value="showCategoryList">		
				<li>
					<div class="fixMT10"></div>
					<div class="bookcontainer-head">
						<h3><s:property value="#showItem.displayName"/></h3>
					</div>
					<div class="bookcontainer" data-page="1">
						<div class="bookcontainer-opt">
							<a href="javascript:void(0)" class="prev" title="上一页"><span>&lt;</span></a>
							<a href="javascript:void(0)" class="next" title="下一页"><span>&gt;</span></a>
						</div>
						<div class="booklisthider">
							<div class="booklist">
								<ul>
								<s:iterator id="showEntity" value="recmEntitys.get(#showItem.id)">
									<li>
										<s:a action="detail" namespace="/">
										<s:param name="id" value="#showEntity.id"/>
											<img src='<s:property value="#showEntity.getEntityValue('large_image')"/>' />
											<p><s:property value="#showEntity.getEntityValue('title')"/><br />
											<s:property value="#showEntity.getEntityValue('author')"/><br />
											<s:property value="#showEntity.getEntityValue('publisher')"/></p>
										</s:a>
									</li>
								</s:iterator>
								</ul>
							</div>
						</div>
					</div>
				</li>
				</s:iterator>
			</ul>
		</article>
	</article>
</div>
<jsp:include page="../basic/basic-footer.jsp" />