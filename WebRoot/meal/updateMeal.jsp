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
  	  请修改餐品信息<s:debug></s:debug>
    	<s:form action="updateMeal" method="post" enctype="multipart/form-data">
    		<s:hidden name="mid"></s:hidden>
	    	<s:textfield name="mname" label="餐品名称" />
	    	<s:textfield name="price" label="价格"/>
	    	<s:select list="{'主食','甜点','晚餐'}" name="Category" label="类别"></s:select>
	    	<img alt="图片走丢了" src="${photo }" width="100px" height="80px"><br>
			 <s:file name="mphoto" label="餐品图片"></s:file>
	    	<s:submit value="提交" ></s:submit>
    	</s:form>
    </center>
  </body>
</html>
