<%@page import="java.util.Enumeration"%>
<% 
/*  
*	@author:TQ
*	购物车页
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="../basic/basic-header.jsp" />
<!-- 正文 -->
<div class="content">
	<div class="buycar-title">
		<img src="statics/images/step1.png" alt="第一步：购物车" />	
	</div>
	<article class="mygoods">
		<div class="title">购物车的商品</div>
		<div class="goodslist">
			<s:if test="!cart.isEmpty()">
			<s:iterator id="cartItem" value="cart.iterator()">
			<div class="item">
				<img src='<s:property value='#cartItem.entity.getEntityValue("large_image")'/>' alt="商品图" />
				<div class="item-info">
						<s:a action="detail" namespace="/" title='ISBN:%{#cartItem.getEntity().getEntityValue("isbn")}'>
						<s:param name="id" value="#cartItem.entityId"/>
							<s:property value="#cartItem.entity.getEntityValue('title')"/>
						</s:a>
						<s:a action="delete" namespace="/cart" cssClass="del">
						<s:param name="id" value="#cartItem.id"/>
							删除
						</s:a>
					<div class="goods-detail">
						<ul>
							<li>作者：<s:property value='#cartItem.entity.getEntityValue("author")'/></li>
							<li>出版社：<s:property value='#cartItem.entity.getEntityValue("publisher")'/></li>
						</ul>
					</div>
					<div class="buy-detail">
						<ul>
							<div class="label">数量及单价</div>
							<li>
								<span class="copy"><s:property value='#cartItem.entityType.entityTypePK.goodsType.displayName'/>
								(<span class="price"><s:property value='#cartItem.entity.getSinglePriceByEntityType(#cartItem.entityTypeName)'/></span>元)
									<a class="minus" href="javascript:void(0)">-</a>
									<input type="text" data-id='<s:property value="#cartItem.id"/>' data-max='<s:property value="#cartItem.entityType.storageCount"/>' data-now='<s:property value='#cartItem.buyNum'/>' value='<s:property value='#cartItem.buyNum'/>' class="inputbox" disabled /> 
									<a class="plus" href="javascript:void(0)">+</a>
								</span> 
							</li>
							<li>小计：<span class="sum"><s:property value="#cartItem.totalPrice"/></span>元</li>
						</ul>
					</div>
				</div>
				</div>
				</s:iterator>
				</s:if>
			</div>
		<s:if test="!cart.isEmpty()">
		<div class="allsubmit"> 
			<span class="allsum">订单总价格：<span class="red allsumnum"><s:property value="cart.totalPrice"/></span>元
			</span>
			<button class="btn" onclick="window.location='index.action'">继续购物</button>
			<button class="btn btn-info" onclick="window.location='cart/clear.action'"><i class="icon-white icon-warning-sign"></i>清空订单</button>
			<button class="btn btn-danger" onclick="window.location='cart/checkout.action'">
				<i class="icon-fire icon-white"></i> 确认订单
			</button>
		</div>
		</s:if>
	</article>
</div>
 <jsp:include page="../basic/basic-footer.jsp" />
 <script type="text/javascript">
 window.onload = function loadFreshSum(){
     $list = $(".goodslist");
     $sumlist = $list.find(".item");
     if($sumlist.length<=0)
     {
       var $text = $("<p>购物车空空如也，还不赶紧去[<a href=''>淘货</a>]~</p>");
       $text.css({"font-size":"18px","margin":"30px","text-align":"center"});
       $list.append($text);
     }
	}
 </script>