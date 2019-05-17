<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
<link rel="stylesheet" href="<%=basePath%>/css/style.css">
    <title>user登录</title>


  </head>
   <body>

  
	<center>
	<div id="wrapper" class="login-page">
		<div id="login_form" class="form">
		
		<form class="login-form" action="userLogin" method="post">
		<s:fielderror name="error"></s:fielderror>
				<input type="text" placeholder="用户名" name="uid" id="user_name"/>
				<input type="password" placeholder="密码"  name="upass" id="password"/>
				<button type="submit" id="login">登　录</button>
				<p class="message">还没有账户? <a href="<%=basePath%>user/regist.jsp">立刻创建</a></p>
			</form>
	</div>
	</div>
	
     </center>
  </body>
</html>
