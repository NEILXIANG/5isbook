<% 
/*  
*	@author:TQ
*	订单确认页
	主要问题有几个:
		1.商品数量传递的问题（与购物车同步）
		2.地址以及配送时间传递问题
		2.价格计算问题
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../basic/basic-header.jsp" />
        <article class="content">
            <div class="orderresult">
             <div class="buycar-title">
				<img src="statics/images/step3.png" alt="第一步：购物车" />	
			</div>
              <hr />
              <div class="title">订单信息</div>
              <div class="summary" >
                  <table class="table table-bordered table-hover">
                    <tbody>
                    <tr>
                      <td class="tdname">订单号：</td>
                      <td><s:property value="order.id"/></td>
                    </tr>
                    <tr>
                      <td class="tdname">总价：</td>
                      <td><s:property value="order.totalPrice()"/>元</td>
                    </tr>
                    <tr>
                      <td class="tdname">姓名</td>
                      <td><s:property value="order.realName"/></td>
                    </tr>
                    <tr>
                      <td class="tdname">长号</td>
                      <td><s:property value="order.longTel"/></td>
                    </tr>
                    <tr>
                      <td class="tdname">短号</td>
                      <td>
                        <s:property value="order.shortTel"/>
                      </td>
                    </tr>
                    <tr>
                      <td class="tdname">地址</td>
                      <td>
                        <s:property value="order.address"/>
                      </td>
                    </tr>
                    <tr>
                      <td class="tdname">配送日期</td>
                      <td>
                        <s:property value="order.deliveryTime.time"/>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
              <div class="title">你现在可以做的事</div>
              <div class="okcontrol modal-footer">
                <s:a action="index" namespace="/" cssClass="btn">继续淘书</s:a>
                <s:a action="order" namespace="/user" cssClass="btn btn-success">查看订单</s:a>
              </div>
            </div>
        </article>
<jsp:include page="../basic/basic-footer.jsp" />