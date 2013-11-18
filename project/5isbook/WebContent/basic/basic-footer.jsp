<%
/*  @author:TQ
*	前台公共页尾
*
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<footer class="text-center">
	<div class="footer-slogan">我爱搜书,我爱阅读</div>
	<ul class="footer-info inline">
		<li><a href="javascript:void(0)">关于网站</a></li>
		<li><a href="javascript:void(0)">帮助中心</a></li>
		<li><a href="javascript:void(0)">联系我们</a></li>
		<li><a href="javascript:void(0)">兄弟连接</a></li>
	</ul>
	<div class="copyright">Copyright&nbsp;三边传媒&nbsp;粤ICP备13038384号</div>
</footer>
<a title="欢迎留下您宝贵的意见!" class="feedback" href="#"><i class="icon-comment icon-white"></i></a>
<a title="返回顶部" class="totop" href="javascript:void(0);" id="back-to-top"><i class="icon-arrow-up icon-white"></i></a>
<script src="<%=basePath%>statics/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="<%=basePath%>statics/js/bootstrap.min.js" type="text/javascript"></script>
<script src="<%=basePath%>statics/js/jquery-migrate-1.0.0.js" type="text/javascript"></script>
<script src="<%=basePath%>statics/js/common.min.js" type="text/javascript"></script>
</body>
</html>
<script type="text/javascript">
if($(document).width()>768){//768px以下宽度的分辨率下不显示
        
  $(window).scroll(function(){
    if ($(window).scrollTop()>100){
      $("#back-to-top").fadeIn(400);
    }
    else {
      $("#back-to-top").fadeOut(400);
    }
  });
  
  $("#back-to-top").click(function(){
    $('body,html').animate({scrollTop:0},500);
    return false;
  });
}
</script>