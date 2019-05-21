<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'regist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <center>
    <s:form action="userUpdate" method="post" >
    	<s:textfield name="uid" label="用户名" disabled="true"/>
    	<s:hidden name="uid" value="%{uid}"/>
    	<s:password name="upass" label="密码"/>
    	<s:password name="upass1" label="确认密码"/>
    	<s:textfield name="phone" label="手机号"/>
    	<s:submit value="修改"/>
    </s:form>
</center>
<script type="text/javascript" src="<%=basePath%>/scripts/jquery-3.4.1.js"></script>

  </body>
</html>
