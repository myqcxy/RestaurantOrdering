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
<p class="center">登录用户名为：admin，密码为123456</p>
<p class="center">在用户名和密码错误时，点击登录按钮可以看到表单的摇晃效果。</p>
<div id="wrapper" class="login-page">
		<div id="login_form" class="form">
	
		<form class="login-form" action="waiterLogin" method="post">
				<input name="wid" type="text" placeholder="用户名" id="user_name"/>
				<input name="wpass" type="password" placeholder="密码" id="password"/>
				<input type="submit" id="login" value="登录"/>
			</form>
	</div>
	</div>
<%-- <script src="<%=basePath%>/js/jquery-2.1.1.min.js"></script> 
<script type="text/javascript">
	function check_login()
	{
	 var name=$("#user_name").val();
	 var pass=$("#password").val();
	 if(name=="admin" && pass=="admin")
	 {
	  alert("登录成功！");
	  $("#user_name").val("");
	  $("#password").val("");

	 }
	 else
	 {
	  $("#login_form").removeClass('shake_effect');  
	  setTimeout(function()
	  {
	   $("#login_form").addClass('shake_effect')
	  },1);  
	 }
	}
	function check_register(){
		var name = $("#r_user_name").val();
		var pass = $("#r_password").val();
		var email = $("r_email").val();
		if(name!="" && pass=="" && email != "")
		 {
		  alert("注册成功！");
		  $("#user_name").val("");
		  $("#password").val("");
		 }
		 else
		 {
		  $("#login_form").removeClass('shake_effect');  
		  setTimeout(function()
		  {
		   $("#login_form").addClass('shake_effect')
		  },1);  
		 }
	}
	$(function(){
		$("#create").click(function(){
			check_register();
			return false;
		})
		$("#login").click(function(){
			check_login();
			return false;
		})
		$('.message a').click(function () {
		    $('form').animate({
		        height: 'toggle',
		        opacity: 'toggle'
		    }, 'slow');
		});
	})
	</script> --%>
</body>
</html>
