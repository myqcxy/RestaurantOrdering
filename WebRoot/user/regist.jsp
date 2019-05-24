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
	<link rel="stylesheet" href="<%=basePath%>/css/style.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <div id="wrapper" class="login-page">
		<div id="login_form" class="form">
		<form class="registerform" action="userRegist" method="post">
		<s:fielderror name="error"></s:fielderror>
				<input required="true" type="text" name="uid" placeholder="用户名" id="r_user_name"/>
				<input required="true" type="password" name="upass" placeholder="密码" id="r_password" />
				<input required="true" type="password" name="upass1" placeholder="确认密码" id="r_password" />
				<input required="true" type="number" name="phone" placeholder="电话" id="r_emial"/>
				<input required="true" type="text" name="email" id="r_emial" placeholder="邮箱" >
				<button typde="submit" id="create">创建账户</button>
				<p class="message">已经有了一个账户? <a href="<%=basePath%>user/login.jsp">立刻登录</a></p>
			</form>
		
	</div>
	</div>
  
   
<script type="text/javascript" src="<%=basePath%>/scripts/jquery-3.4.1.js"></script>


  </body>
</html>
