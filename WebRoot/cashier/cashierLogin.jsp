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
	

  </head>
  
<body>
<center><h1><b>收银员登录</b></h1></center>
<div id="wrapper" class="login-page">
		<div id="login_form" class="form">
		<s:fielderror name="cid"></s:fielderror>
		<form class="login-form" action="cashierLogin" method="post">
				<input name="cid" type="text" placeholder="用户名" id="user_name"/>
				<input name="cpass" type="password" placeholder="密码" id="password"/>
				<input type="submit" id="login" value="登录"/>
			</form>
	</div>
	</div>
</body>
</html>
