package org.apache.jsp.basic;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class basic_002dfooter_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;


/*  @author:TQ
*	前台公共页尾
*
*/

      out.write("\r\n");
      out.write("\r\n");

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("<footer class=\"text-center\">\r\n");
      out.write("\t<div class=\"footer-slogan\">我爱搜书,我爱阅读</div>\r\n");
      out.write("\t<ul class=\"footer-info inline\">\r\n");
      out.write("\t\t<li><a href=\"javascript:void(0)\">关于网站</a></li>\r\n");
      out.write("\t\t<li><a href=\"javascript:void(0)\">帮助中心</a></li>\r\n");
      out.write("\t\t<li><a href=\"javascript:void(0)\">联系我们</a></li>\r\n");
      out.write("\t\t<li><a href=\"javascript:void(0)\">兄弟连接</a></li>\r\n");
      out.write("\t</ul>\r\n");
      out.write("\t<div class=\"copyright\">Copyright&nbsp;三边传媒&nbsp;粤ICP备13038384号</div>\r\n");
      out.write("</footer>\r\n");
      out.write("<a title=\"欢迎留下您宝贵的意见!\" class=\"feedback\" href=\"#\"><i class=\"icon-comment icon-white\"></i></a>\r\n");
      out.write("<a title=\"返回顶部\" class=\"totop\" href=\"javascript:void(0);\" id=\"back-to-top\"><i class=\"icon-arrow-up icon-white\"></i></a>\r\n");
      out.write("<script src=\"");
      out.print(basePath);
      out.write("statics/js/jquery-1.9.1.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath);
      out.write("statics/js/bootstrap.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath);
      out.write("statics/js/jquery-migrate-1.0.0.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath);
      out.write("statics/js/common.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("if($(document).width()>768){//768px以下宽度的分辨率下不显示\r\n");
      out.write("        \r\n");
      out.write("  $(window).scroll(function(){\r\n");
      out.write("    if ($(window).scrollTop()>100){\r\n");
      out.write("      $(\"#back-to-top\").fadeIn(400);\r\n");
      out.write("    }\r\n");
      out.write("    else {\r\n");
      out.write("      $(\"#back-to-top\").fadeOut(400);\r\n");
      out.write("    }\r\n");
      out.write("  });\r\n");
      out.write("  \r\n");
      out.write("  $(\"#back-to-top\").click(function(){\r\n");
      out.write("    $('body,html').animate({scrollTop:0},500);\r\n");
      out.write("    return false;\r\n");
      out.write("  });\r\n");
      out.write("}\r\n");
      out.write("</script>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
