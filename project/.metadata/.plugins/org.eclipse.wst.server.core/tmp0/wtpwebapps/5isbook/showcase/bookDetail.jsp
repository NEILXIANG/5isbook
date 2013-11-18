<% 
/*  
*	@author:TQ
*	图书详请页
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../basic/basic-header.jsp" />
        <div class="content">
            <div class="book-summary">
                <div class="book-summary-left">
                  <div class="imgblock">
                    <img title="<s:property value='book.getEntityValue("title")'/>" src="<s:property value="book.getEntityValue('large_image')"/>" />
                  </div>
                </div>
                <div class="book-summary-right">
                  <div class="summary-head">
                    <h3><s:property value='book.getEntityValue("title")'/></h3>
                  </div>
                  <ul class="summary-meta">
                    <li>
                      <span>价格</span>
                      <strong class="red" id="pricenow"><s:property value='book.getSinglePriceByEntityType("authorised_edition")'/></strong><span>元</span>
                      &nbsp;(原价：<s:property value='book.price'/>元)
                    </li>
                    <li>
                      <span>作者</span>
                      <span><s:property value='book.getEntityValue("author")'/></span>
                    </li>
                    <li>
                      <span>出版社</span>
                      <span><s:property value='book.getEntityValue("publisher")'/></span>
                    </li>
                  </ul>
                  <s:form class="summary-key" method="get" action="add" id="bookDetailForm" namespace="/cart">
                    <input type="hidden" name="id" value='<s:property value="book.id"/>'/>
                    <div class="chooseType">类型
                      <span>
                        <a class="typeBox selected" href="javascript:void(0)">正版</a>
                        <input type="radio" name="goodsType" value="authorised_edition" 
                        data-rest="<s:property value='book.getEntityType("authorised_edition").getStorageCount()'/>"
                        data-price="<s:property value='book.getSinglePriceByEntityType("authorised_edition")'/>" 
                        id="typebox1" class="hide" checked="checked" />
                      </span>
                      <span>
                        <a class="typeBox" href="javascript:void(0)">非正版</a>
                        <input type="radio" name="goodsType" value="pirate_edition" 
                        data-rest="<s:property value='book.getEntityType("pirate_edition").getStorageCount()'/>"  
                        data-price="<s:property value='book.getSinglePriceByEntityType("pirate_edition")'/>"
                        id="typebox2" class="hide" />
                      </span>
                      <span>
                        <a class="typeBox" href="javascript:void(0)">复印</a>
                        <input type="radio" name="goodsType" value="copy_edition" 
                        data-rest="<s:property value='book.getEntityType("copy_edition").getStorageCount()'/>" 
                        data-price="<s:property value='book.getSinglePriceByEntityType("copy_edition")'/>"
                        id="typebox3" class="hide" />
                      </span>
                    </div>
                    <div class="num">数量
                    	<span class="opt">
                      		<span onselectstart="return false" class="dminus">-</span> 
                      		<input type="text" name="buyNum" data-max="20" data-now='1' value='1' class="inputbox"/> 
                      		<span onselectstart="return false" class="dplus">+</span>
                      	</span>
                      	&nbsp;(剩余：<span id="restNum"></span>件)
                    </div>
                  <div class="btn-group">
                    <button id="addCartBtn" class="btn btn-danger"><i class="icon-fire icon-white"></i> 立即购买</button>
                    <!--  <button class="btn btn-primary">加入购物车</button>-->
                  </div>
                  </s:form>
                </div>
            </div>
            <div class="book-info">
              <div class="accordion">
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle">
                    	  图书详情
                    </a>
                  </div>
                  <div class="accordion-body">
                    <table class="table table-hover table-condensed table-bordered">
                      <thead><tr><td cospan="2">基本信息</td></tr></thead>
                      <tr><td class="ttitle">书名</td><td class="tcontent"><s:property value='book.getEntityValue("title")'/></td></tr>
                      <tr><td class="ttitle">ISBN编号</td><td class="tcontent"><s:property value='book.getEntityValue("isbn")'/></td></tr>
                      <tr><td class="ttitle">作者</td><td class="tcontent"><s:property value='book.getEntityValue("author")'/></td></tr>
                      <tr><td class="ttitle">出版社</td><td class="tcontent"><s:property value='book.getEntityValue("publisher")'/></td></tr>
                      <tr><td class="ttitle">页数</td><td class="tcontent"><s:property value='book.getEntityValue("pages")'/></td></tr>
                      <tr><td class="ttitle">出版时间</td><td class="tcontent"><s:property value='book.getEntityValue("pubdate")'/></td></tr>
                      <tr><td class="ttitle">作者简介</td><td class="tcontent"><s:property value='book.getEntityValue("author-intro")' escape="false"/></td></tr>
                    </table>
                  </div>
                </div>
                <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle">
                      	图书简介
                    </a>
                  </div>
                  <div class="accordion-body">
                      <s:property value='book.getEntityValue("summary")' escape="false"/>
                  </div>
                </div>
                 <div class="accordion-group">
                  <div class="accordion-heading">
                    <a class="accordion-toggle">相关图书</a>
                  </div>
                  <div class="accordion-body">
                       <div class="item-list">
                         <s:iterator id="recommendEntity" value="recommendEntitys">
                          <figure>
                          	<s:a action="detail" namespace="/">
                          	<s:param name="id" value="#recommendEntity.id"/>
                          		<img class="bookimg" title="推荐图书:<s:property value='#recommendEntity.getEntityValue("title")'/>" src="<s:property value='#recommendEntity.getEntityValue("large_image")'/>" />
                              <p class="bookname"><s:property value='#recommendEntity.getEntityValue("title")'/></p>
                          	</s:a>
                          </figure>
                         </s:iterator>
                      </div>
                  </div>
                </div>
              </div>
            </div>
        </div>
 <jsp:include page="../basic/basic-footer.jsp" />
