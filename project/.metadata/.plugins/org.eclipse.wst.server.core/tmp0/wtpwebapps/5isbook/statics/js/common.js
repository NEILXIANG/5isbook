/*
*
*	@author:TQ 	
*    公共js
*
*/
//---------------------全局----------------------
//兼容HTML5
/*
 HTML5 Shiv v3.6.2pre | @afarkas @jdalton @jon_neal @rem | MIT/GPL2 Licensed
 Uncompressed source: https://github.com/aFarkas/html5shiv
*/
(function(l,f){function m(){var a=e.elements;return"string"==typeof a?a.split(" "):a}function i(a){var b=n[a[o]];b||(b={},h++,a[o]=h,n[h]=b);return b}function p(a,b,c){b||(b=f);if(g)return b.createElement(a);c||(c=i(b));b=c.cache[a]?c.cache[a].cloneNode():r.test(a)?(c.cache[a]=c.createElem(a)).cloneNode():c.createElem(a);return b.canHaveChildren&&!s.test(a)?c.frag.appendChild(b):b}function t(a,b){if(!b.cache)b.cache={},b.createElem=a.createElement,b.createFrag=a.createDocumentFragment,b.frag=b.createFrag();
a.createElement=function(c){return!e.shivMethods?b.createElem(c):p(c,a,b)};a.createDocumentFragment=Function("h,f","return function(){var n=f.cloneNode(),c=n.createElement;h.shivMethods&&("+m().join().replace(/\w+/g,function(a){b.createElem(a);b.frag.createElement(a);return'c("'+a+'")'})+");return n}")(e,b.frag)}function q(a){a||(a=f);var b=i(a);if(e.shivCSS&&!j&&!b.hasCSS){var c,d=a;c=d.createElement("p");d=d.getElementsByTagName("head")[0]||d.documentElement;c.innerHTML="x<style>article,aside,figcaption,figure,footer,header,hgroup,main,nav,section{display:block}mark{background:#FF0;color:#000}</style>";
c=d.insertBefore(c.lastChild,d.firstChild);b.hasCSS=!!c}g||t(a,b);return a}var k=l.html5||{},s=/^<|^(?:button|map|select|textarea|object|iframe|option|optgroup)$/i,r=/^(?:a|b|code|div|fieldset|h1|h2|h3|h4|h5|h6|i|label|li|ol|p|q|span|strong|style|table|tbody|td|th|tr|ul)$/i,j,o="_html5shiv",h=0,n={},g;(function(){try{var a=f.createElement("a");a.innerHTML="<xyz></xyz>";j="hidden"in a;var b;if(!(b=1==a.childNodes.length)){f.createElement("a");var c=f.createDocumentFragment();b="undefined"==typeof c.cloneNode||
"undefined"==typeof c.createDocumentFragment||"undefined"==typeof c.createElement}g=b}catch(d){g=j=!0}})();var e={elements:k.elements||"abbr article aside audio bdi canvas data datalist details figcaption figure footer header hgroup main mark meter nav output progress section summary time video",version:"3.6.2pre",shivCSS:!1!==k.shivCSS,supportsUnknownElements:g,shivMethods:!1!==k.shivMethods,type:"default",shivDocument:q,createElement:p,createDocumentFragment:function(a,b){a||(a=f);if(g)return a.createDocumentFragment();
for(var b=b||i(a),c=b.frag.cloneNode(),d=0,e=m(),h=e.length;d<h;d++)c.createElement(e[d]);return c}};l.html5=e;q(f)})(this,document);

(function() {
     if (!0) return;
     var e = "abbr, article, aside, audio, canvas, datalist, details, dialog, eventsource, figure, footer, header, hgroup, mark, menu, meter, nav, output, progress, section, time, video".split(', ');
     var i= e.length;
     while (i--){
         document.createElement(e[i]);
     } 
})();
//兼容CSS3部分属性
    $(function() {
        if (window.PIE) {
            $('.rounded').each(function() {
                PIE.attach(this);
            });
        }
    });
//修正FIREFOX不支持innerText
function isIE(){ //ie
if (window.navigator.userAgent.toLowerCase().indexOf("msie")>=1) 
    return true; 
else 
    return false; 
}
if(!isIE()){ //firefox innerText define
    HTMLElement.prototype.__defineGetter__("innerText", 
    function(){
        var anyString = "";
        var childS = this.childNodes;
        for(var i=0; i<childS.length; i++) { 
            if(childS[i].nodeType==1)
                anyString += childS[i].innerText;
            else if(childS[i].nodeType==3)
                anyString += childS[i].nodeValue;
        }
        return anyString;
    } 
    ); 
    HTMLElement.prototype.__defineSetter__("innerText", 
    function(sText){
        this.textContent=sText; Webjx.Com 
    } 
    ); 
}
//说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。   
//调用：accAdd(arg1,arg2)   
//返回值：arg1加上arg2的精确结果   
function accAdd(arg1,arg2){   
    var r1,r2,m;   
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}   
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}   
    m=Math.pow(10,Math.max(r1,r2))   
    return (arg1*m+arg2*m)/m   
}   

//给Number类型增加一个add方法，调用起来更加方便。   
Number.prototype.add = function (arg){   
    return accAdd(arg,this);   
}

//说明：javascript的减法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的减法结果。   
//调用：accSub(arg1,arg2)   
//返回值：arg1减上arg2的精确结果   
function accSub(arg1,arg2){       
    return accAdd(arg1,-arg2);   
}   

//给Number类型增加一个sub方法，调用起来更加方便。   
Number.prototype.sub = function (arg){   
    return accSub(this,arg);   
}

//说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。   
//调用：accMul(arg1,arg2)   
//返回值：arg1乘以arg2的精确结果   
function accMul(arg1,arg2)   
{   
    var m=0,s1=arg1.toString(),s2=arg2.toString();   
    try{m+=s1.split(".")[1].length}catch(e){}   
    try{m+=s2.split(".")[1].length}catch(e){}   
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)   
}   

//给Number类型增加一个mul方法，调用起来更加方便。   
Number.prototype.mul = function (arg){   
    return accMul(arg, this);   
}


//说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。   
//调用：accDiv(arg1,arg2)   
//返回值：arg1除以arg2的精确结果   
function accDiv(arg1,arg2){   
    var t1=0,t2=0,r1,r2;   
    try{t1=arg1.toString().split(".")[1].length}catch(e){}   
    try{t2=arg2.toString().split(".")[1].length}catch(e){}   
    with(Math){   
        r1=Number(arg1.toString().replace(".",""))   
        r2=Number(arg2.toString().replace(".",""))   
        return (r1/r2)*pow(10,t2-t1);   
    }   
}   
//给Number类型增加一个div方法，调用起来更加方便。   
Number.prototype.div = function (arg){   
    return accDiv(this, arg);   
}
//判断数字
function IsNum(s)
{
    if (s!=null && s!="")
    {
        return !isNaN(s);
    }
    return false;
}
//----------------------公共部分--------------
$(".logined-state").hover(function() {
		var $opt = $(this).find(".logined-opt");
		var $a = $(this).find(".atitle");
		if(!$opt.is(":animated"))
		{
		$opt.stop(true).show(0);
		$a.css({"background-color":"white","color":"#888"});
		}
	},function() {
		var $opt = $(this).find(".logined-opt");
		var $a = $(this).find(".atitle");
		if(!$opt.is(":animated"))
		{
		$opt.stop(true).hide(0);
		$a.css({"background-color":"#eee","color":"#8BC53F"});
		}
	});
$("#buycar").hover(
  function(){
      $("#buycar").attr("src","statics/images/buycar-select.png");
  },
  function(){
      $("#buycar").attr("src","statics/images/buycar.png");
  }
  );
$("#collect").hover(
  function(){
      $("#collect").attr("src","statics/images/collect-select.png");
  },
  function(){
      $("#collect").attr("src","statics/images/collect.png");
  }
  );

//----------------------Home-----------------
//滑动条登陆框显示
  $loginOpt = $(".logined-opt");
  $loginOpt.hide();
  $(".log").hover(function(){
    $(this).css({"background-color":"#484848"});
    $loginOpt.show();
  },function(){
    $(this).css({"background-color":"#117788"});
    $loginOpt.hide();
  });
//滑动条FIX
  $(".sidebar-title").toggle(function(){
      var $list = $(this).siblings(".sidebar-list");
      if(!$list.is(":animated"))
        $list.stop(true).hide(150);
  },
  function(){
      var $list = $(this).siblings(".sidebar-list");
      if(!$list.is(":animated"))
        $list.stop(true).show(150);
  });
  $(".item-detail").hover(function(){
    var $second = $(this).find(".item-detail-second");
    if(!$second.is(":animated"))//在动画中则不开始
    $second.stop(true).show(100);
  },function(){
    var $second = $(this).find(".item-detail-second");
    $second.stop(true).hide(100);
  });
  //搜索栏FIX
  $(window).bind("scroll",function(){   
  		t=$(document).scrollTop();
        if(t>90.5){
        	$(".front-bar").addClass("fixedfrontbar");
        }
        else{
        	$(".front-bar").removeClass("fixedfrontbar");
        }
  } );
//分类推介图片轮播
        var page = 1;
        var pageTotal = 7;
        $(".next").click(function(){
            var $parent = $(this).parents("div.bookcontainer");
            var $booklist = $parent.find("div.booklist");
            var pageNow = $parent.attr("data-page");
            if(!$booklist.is(":animated")){
                if( pageNow == pageTotal){
                    $booklist.animate({'margin-left' : '0px'}, 100);
                    $parent.attr("data-page",1);
                }
                else{
                    $booklist.animate({'margin-left' : '-='+ '140px' }, 100);
                     pageNow++;
                     $parent.attr("data-page",pageNow);
               }
            }
        });
         $(".prev").click(function(){
            var $parent = $(this).parents("div.bookcontainer");
            var $booklist = $parent.find("div.booklist");
            var pageNow = $parent.attr("data-page");
            if( !$booklist.is(":animated")){
                if( pageNow == 1){
                    $booklist.animate({'margin-left' : '-=' + '840px'}, 100);
                    $parent.attr("data-page",pageTotal);
                }
                else{
                    $booklist.animate({'margin-left' : '+='+ '140px' }, 100);
                    pageNow--;
                    $parent.attr("data-page",pageNow);
                }
            }
        });
//----------------------carBuyer-----------------
//付款流程提示框
  $("button[rel=popover]").
  hover(function(e) {
    $(this).stop(true).popover('show');
  },function(e) {
    if(!$(this).is(":animated"))
   $(this).stop(true).popover('hide');
  });
  window.onload = function loadFreshSum(){
      $list = $(".goodslist");
      $sumlist = $list.find(".item");
      if($sumlist.length<=0)
      {
        var $text = $("<p>购物车空空如也，还不赶紧去[<a href=''>淘货</a>]~</p>");
        $text.css({"font-size":"18px","margin":"30px","text-align":"center"});
        $list.append($text);
      }
      $.each($sumlist,function(){
        var $plus = $(this).find(".plus");
        freshSum($plus);
      });
 	}
  $(function() {
     //删除商品
    $(".del").click(function(){
      var $item = $(this).parents(".item");
      var $result = confirm("是否删除该商品?");
      if($result==false){
        return false;
      }
    });
    //添加、删除商品数量
    $(".minus").click(function() {
      var $inputbox = $(this).siblings(".inputbox");
      var now = parseInt($inputbox.attr("data-now"));
      var nowId = $inputbox.attr("data-id");
      var $tmp = $(this);
      if (now > 1) {
    	$.ajax({
 			   type:'get',
 			   url:'/5isbook/cart/decrease.action',
 			   data:{
 				   id:nowId,
 				   num:1
 			   },
 			   success:function(msg){
 				   if(String($.trim(msg))=='true'){
 					 $inputbox.attr("data-now", --now);
 			         $inputbox.attr("value", now);
 					 freshSum($tmp);
 				   }else{
 					  alert("添加失败请重试");
 				   }
 			   },
 			   error:function(){
 				  alert("添加失败请重试");
 			   }
 			  });
	 		}
      return false;
    });
    $(".plus").click(function() {
      var $inputbox = $(this).siblings(".inputbox");
      var now = parseInt($inputbox.attr("data-now"));
      var max = parseInt($inputbox.attr("data-max"));
      var nowId = $inputbox.attr("data-id");
      var $tmp = $(this);
      if (now < max) {
        $.ajax({
			   type:'get',
			   url:'/5isbook/cart/increase.action',
			   data:{
				   id:nowId,
				   num:1
			   },
			   success:function(msg){
				   if(String($.trim(msg))=='true'){
					   $inputbox.attr("data-now", ++now);
				        $inputbox.attr("value", now);
					   freshSum($tmp);
				   }else{
					   alert("添加失败请重试");
				   }
			   },
			   error:function(){
				   alert("添加失败请重试");
			   }
			  });
      } else {
        alert("超过了最大数量");
      }
      return false;
    });
   
    //自动生成总价
    function freshSum(obj) {
      var $parent = obj.parents(".buy-detail");
      var $sum = $parent.find(".sum");
      var $price = $parent.find(".price");
      var $inputbox = $parent.find(".copy").find(".inputbox");
      var total = parseFloat($price.text()).mul($inputbox.attr("data-now"));
      if (total <= 0) {
        alert("数量不能为0哦。");
        obj.siblings(".inputbox").attr("data-now", 1).attr("value", 1);
        //obj为放减号按钮的span，与input兄弟关系
      } else {
        $sum.text(total);
      }
      freshAllSum();
    }
  });
  //刷新总订单价格
  function freshAllSum() {
    var sum = 0;
    $(".sum").each(function() {
      sum += parseFloat($(this).text());
    });
    $(".allsumnum").text(sum);
  }
  //------------------bookDetail--------------
  var $num = $(".num");
  var $numInput = $num.find(".inputbox");
  var $restNum = $num.find("#restNum");
  var $opt = $num.find(".opt");
  var $typeBox = $(".typeBox");
  var $typeBoxInput = $('input[name="goodsType"]');
  var $pricenow = $("#pricenow");
  //刷新余量
  window.onload=function freshRestNum(){
    $inputnow = $('input[name="goodsType"]:checked');
    $targetInput = $('input[name="buyNum"]');
    rest = parseInt($inputnow.attr("data-rest"));
    $restNum.text(rest);
	$targetInput.attr("data-max",rest);
	isRestZero(rest);
  }
  //修改input内容改变数量
  $(".inputbox").blur(function(){
    var tmp = $(this).val().trim();
    var nowId = $this.attr("data-id");
    if(!IsNum(tmp)||tmp==null||tmp==""){
      $(this).attr("data-now","1");
      $(this).attr("value","1");
      return false;
    }
    else{
      var value = parseInt(tmp);
       if(value<=0){
        $(this).attr("data-now","1");
        $(this).attr("value","1");
        return false;
      }
      var max = parseInt($(this).attr("data-max"));
      if(value > max){
        value = max;
      }
      $(this).attr("data-now",value.toString());
      $(this).attr("value",value.toString());
      freshSum($(this)); 
    }
  });
   
  //若余量为0则隐藏输入框
  function isRestZero(rest){
	  if(rest==0){
		  $opt.hide();
	  }
	  else{
		  $opt.show();
	  }
  }
  //添加、删除商品数量
  $(".dminus").click(function() {
    var $inputbox = $(this).siblings(".inputbox");
    var now = parseInt($inputbox.attr("data-now"));
    if (now > 1) {
      now--;
      $inputbox.attr("data-now", now);
      $inputbox.attr("value", now);
    }
  });
  $(".dplus").click(function() {
    var $inputbox = $(this).siblings(".inputbox");
    var now = parseInt($inputbox.attr("data-now"));
    var max = parseInt($inputbox.attr("data-max"));
    if (now < max) {
      now++;
      $inputbox.attr("data-now", now).attr("value", now);
      
    } else {
      alert("超过了最大数量");
    }
  });
  $(".typeBox").click(function(){
    var $type = $(this).siblings("input");
    var rest = parseInt($type.attr("data-rest"));
    var price = $type.attr("data-price");
    //取消选择
    $.each($typeBox,function(){
      $(this).removeClass("selected");      
    });
    //取消隐藏框选择
    $.each($typeBoxInput,function(){        
      $(this).attr("checked","");
    });
    //选择当前选择项的样式框以及对应隐藏框
    $(this).addClass("selected");
    $type.attr("checked","checked");
    $pricenow.text(price);
    //更新余量,初始化数值
    $numInput.attr("data-now",1);
    $numInput.attr("data-value",1);
    $numInput.attr("data-max",rest);
    $restNum.text(rest);
    isRestZero(rest);
   
  });
  //加入购物车页面，未完成。
  $("#addCartBtn").click(function(){
	if(parseInt($restNum.text())==0){
		return false;
	}
	else{
		this.submit();
	}
  });
  //修改数量大小
  $(".num>.inputbox").blur(function(){
      var tmp = $(this).val().trim();
      if(!IsNum(tmp)||tmp==null||tmp==""){
        $(this).attr("data-now","1");
        $(this).attr("value","1");
        return false;
      }
      else{
        var value = parseInt(tmp);
        if(value<=0){
          $(this).attr("data-now","1");
          $(this).attr("value","1");
          return false;
        }
        var max = parseInt($(this).attr("data-max"));
        if(value > max){
          value = max;
        }
        $(this).attr("data-now",value.toString());
        $(this).attr("value",value.toString());
      }
    });
 //-------------------orderConfirm------------

    var $addressAry = $('input[name="addressId"]');
    var $dateAry = $('input[name="deliveryTimeId"]');
  $(function(){
	
    $("#cancelBtn").click(function(){
      var result = confirm("真的要取消订单？"); 
      //todo
      if(result){
        alert("订单已取消！");//稍后推送式提示 
        window.location="Home.jsp";
      }
      else
      {
        return false;
      }
    });
	  //选择地址
	  function addressboxControll(obj,i){
	    if(i==0){//selected
	      obj.addClass("redborder").find(".ok").stop(true).show(0);
	    }
	    else{//unselected
	      obj.removeClass("redborder").find(".ok").stop(true).hide(0);
	    }
	  }
    //清空订单
    function cleanAllOrder(){
    	$.each($addressAry,function(){
    		$(this).attr("checked","");});
    }
    //更新订单信息
    function freshOrder(obj){
      var $input = obj.find('input[name="addressId"]');
      $input.attr("checked","checked");
    }
	  //只能选择一个地址框
	  $(".addressbox").click(function(){
	    if(!$(this).hasClass("redborder"))
	    {
	      var $otherbox = new Array();
	      $otherbox = $(".addressbox");
	      $.each($otherbox,function(){addressboxControll($(this),1);});
	      cleanAllOrder();
	      addressboxControll($(this),0);
          freshOrder($(this));
	    }
	    else{
	      return false;
	    }
	  });
	});

//-------------------个人中心-------------
  $(function(){
      $("#message").fadeIn("slow").fadeTo(3E3,0.99).fadeOut("slow");
        $(".checked").blur(function(){
          if ($(this).val() ==''||$(this).val().trim()=='') 
          {
            $(this).siblings("#nametip").removeClass("hide");
          } 
          else {
            $(this).siblings("#nametip").addClass("hide");
          }
        });
        //检验两次密码正确性
       var $confirmPassword = $("#confirmPassword");
       var $newPassword = $("#newPassword");
       $confirmPassword.blur(function(){
          if($(this).val()!=$newPassword.val())
          {
            $(this).siblings("#notmatchtip").removeClass("hide");
            return false;
          }
          else{
            $(this).siblings("#notmatchtip").addClass("hide");
          }
       });
      //密码还要确认是否两次输入相同
       $("#passwordEditFormBtn").click(function()
       {
          var $error = $confirmPassword.siblings("#notmatchtip");
          if($error.hasClass("hide")){
            $obj.submit();
          }
          else{
            alert("请清除错误再提交!");
            return false;
          }
          return false;
          });

  });
  