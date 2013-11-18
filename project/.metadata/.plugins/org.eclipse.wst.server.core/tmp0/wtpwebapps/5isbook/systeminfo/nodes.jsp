<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>all end nodes</title>
</head>
<body>
<h3>节点比较多，请用【ctrl+f】键来查找。</h3>
<ul> 
	<s:iterator id="node" value="endNodes">
	<li><s:property value="#node.displayName"/>&nbsp;-&gt;&nbsp;<s:property value="#node.basicCode"/></li>
	</s:iterator>
</ul>
</body>
</html>