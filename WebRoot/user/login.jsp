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

    <title>user登录</title>


  </head>
   <body>

  
	<center>
     <s:form action="userLogin" method="post">
    	<s:textfield name="uid" label="用户名"/>
    	<s:password name="upass" label="密码"></s:password>
    	<table>
    		<tr><s:reset value="重置" theme="simple" style="margin:5px,30px"></s:reset>
    			<s:submit value="登录" theme="simple" style="padding:5,30"/></tr>
    	</table>
    	
    	
    </s:form></center>
  </body>
</html>
