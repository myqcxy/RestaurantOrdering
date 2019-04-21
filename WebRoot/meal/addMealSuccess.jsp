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
	<meta http-equiv="refresh" content="5;url=getAllMeals"> 
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <center>
  	  <h1>添加成功！！修改的餐品信息为：5s返回</h1><s:debug></s:debug>
    	<s:form action="" method="post">
    	<s:textfield name="mname" label="餐品名称"/>
    	<s:textfield name="price" label="价格"/>
		 
    	<s:submit value="提交" ></s:submit>
    	</s:form> 
    	<img alt="sdf" src="images/20190419172544432.jpg"/>
    </center>
  </body>
</html>
