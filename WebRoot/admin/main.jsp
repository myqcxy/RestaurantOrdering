<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
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
    LoginSuccess
    	<s:form action="adminLog" method="post">
    	<s:textfield name="aid" label="用户名"/>
    	<s:password name="apass" label="密码"></s:password>
    	
    	<s:submit value="登录" ></s:submit>
    </s:form>
    <a href="meal.jsp">餐品管理</a><br>
    <a href="table.jsp">餐桌管理</a><br>
    <a href="" >信息统计</a>
    </center>
  </body>
</html>
