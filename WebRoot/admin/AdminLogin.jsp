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
	<link rel="stylesheet" href="<%=basePath%>/css/style.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%-- <center>
    	<s:form action="adminLog" method="post">
    	<s:textfield name="aid" label="用户名"/>
    	<s:password name="apass" label="密码"></s:password>
    	
    	<s:submit value="登录" ></s:submit>
    </s:form>
    </center> --%>
    <center><h1><b>管理员登录</b></h1></center>
		<div id="wrapper" class="login-page">
		<div id="login_form" class="form">
		<s:fielderror name="aid"></s:fielderror>
		<form class="login-form" action="adminLog" method="post">
				<input name="aid" type="text" placeholder="用户名" id="user_name"/>
				<input name="apass" type="password" placeholder="密码" id="password"/>
				<input type="submit" id="login" value="登录"/>
			</form>
	</div>
	</div>
  </body>
</html>
