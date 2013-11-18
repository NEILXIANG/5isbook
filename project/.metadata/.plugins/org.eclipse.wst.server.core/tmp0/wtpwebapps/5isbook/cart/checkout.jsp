<% 
/*  
*	@author:TQ
*	订单确认页
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../basic/basic-header.jsp" />
        <article class="content">
          <s:form action="buy" namespace="/cart" method="post" id="confirmForm">
            <div class="confirm-page">
                <div class="buycar-title">
					<img src="statics/images/step2.png" alt="第一步：购物车" />	
				</div>
                <hr />
                <div class="confirm-page-content">
                    <h4>收货人信息</h4>
                    <div id="addresslist">
					<s:property value="message"/>
					<s:iterator id="address_" value="addresses" status="st">
                      <div <s:if test="#st.Index == 0">class="addressbox redborder"</s:if><s:else>class="addressbox"</s:else> onselectstart="return false">
                          <input class="hide address" value='<s:property value='#address_.id'/>' <s:if test="#st.Index == 0">checked="checked"</s:if> type="radio" name="addressId" />
                          <ul>
                              <li id="name"><s:property value="#address_.readName"/></li>
                              <li id="address"><s:property value="#address_.address"/></li>
                              <li id="longtel"><s:property value="#address_.tel"/></li>
                              <li id="shorttel"><s:property value="#address_.tel"/></li>
                          </ul> 
                          <span class="ok hide"><i class="icon-ok icon-white"></i></span>
                      </div>  
 					</s:iterator>
                    </div>
      
                    <div class="addnew">
                      <s:a action="index" namespace="/user/address">添加新收货人信息</s:a>
                    </div>
                    
                    <hr />
                    <h4>配送日期选择</h4>
                    <div class="datebox">
                      <s:iterator id="dv" value="deliveryTimes" status="st">
                      <label class="radio"><input type="radio" name="deliveryTimeId" <s:if test="#st.Index == 0">checked="checked"</s:if> value='<s:property value='#dv.id'/>' /><s:property value='#dv.time'/></label>  
                   	  </s:iterator>
                    </div>
                    <hr />
                    <h4>订单详请</h4>
                    <div class="orderbox">
                      <table class="table table-borderd table-hover table-condensed table-bordered">
                        <thead><th>商品名称</th><th>单价</th><th>数量</th><th>小计</th></thead>
                        <s:iterator id="item" value="cart.iterator()">
                        <tr>
                        	<td><s:property value="#item.entity.getEntityValue('title')"/></td>
                        	<td><s:property value="#item.entity.getSinglePriceByEntityType(#item.entityTypeName)"/>元</td>
                        	<td><s:property value="#item.buyNum"/></td>
                        	<td><s:property value="#item.totalPrice"/>元</td>
                        </tr>
                      	</s:iterator>
                      </table>
                    </div>
                    <div class="modal-footer">
                        <button id="submitBtn" type="submit" class="btn btn-danger">确认订单</button>
                        <s:a id="cancelBtn" cssClass="btn" action="index" namespace="/cart">取消</s:a>
                    </div>
                </div>
            </div>
          </s:form>
        </article>
<jsp:include page="../basic/basic-footer.jsp" />