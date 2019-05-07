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
    显示所有Meal<s:debug></s:debug>
    <h1>餐品信息为：</h1><br>
 	
 
   <s:iterator value="list">
   	
   		<s:property value="mname"/>
   		&nbsp;&nbsp;&nbsp;￥:<s:property value="price"/><br>
   		类别：<s:property value="category"/><br>
   		销量：<s:property value="sales"/><br>
   		<img alt="图片走丢了" src="${ photo }" width="100px" height="80px"><br>
   		<s:url var="updateUrl" action="toUpdateMeal">
         <s:param name="mid" value="mid"/>
      </s:url>
   		<a href="${updateUrl }">修改</a>
   		
   		 <s:url var="delUrl" action="delMeal">
         <s:param name="mid" value="mid"/>
      </s:url>
      <a href="${delUrl}" onClick="return readyDel();">删除</a>
   	  <script>
      function readyDel(){
        return confirm("是否真的删除?");
      }
    </script>
   		<br><br>
   </s:iterator>
    	<br>
    	
    </center>
  </body>
</html>
