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
    显示所有Table<s:debug></s:debug>
    <h1>餐桌信息为：</h1><br>
 	
 
   <s:iterator value="tables">
   	
   		编号：<s:property value="tid"/>
   		&nbsp;&nbsp;&nbsp;可入座人数：<s:property value="number"/>
   		状态：<s:if test="used==0">可用</s:if>
   		<s:else>不可用</s:else>
   		<br>
   		特色：<s:property value="describe"/>
   		<br>
   		<s:url var="updateUrl" action="toUpdateTable">
         <s:param name="tid" value="tid"/>
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
