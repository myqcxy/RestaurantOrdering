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
    <center> <h4><a href="<%=basePath%>/admin/main.jsp">返回主页</a></h4>
 	
    <h3>餐桌信息为：</h3>
 	<table border="1">
 <tr style="color:red"><th width="100px">编号<th width="100px">可入座人数<th width="100px">状态<th width="100px">特色<th width="100px">操作</tr>
 <s:if test="tables==null">
 	暂无餐桌信息
 </s:if>
   <s:iterator value="tables">
   	<tr>
   		<td><s:property value="tid"/>
   		<td><s:property value="number"/>
   		<td><s:if test="used==0">可用</s:if>
   		<s:else>使用中</s:else>
   		
   		<td><s:property value="describe"/>
   		<s:url var="updateUrl" action="toUpdateTable">
         <s:param name="tid" value="tid"/>
      </s:url>
   		<td><a href="${updateUrl }">修改</a>
   		
   		 <s:url var="delUrl" action="delMeal">
         <s:param name="mid" value="mid"/>
      </s:url>
     <a href="${delUrl}" onClick="return readyDel();">删除</a>
   	  <script>
      function readyDel(){
        return confirm("是否真的删除?");
      }
    </script>
   </s:iterator>
   </table>
    	
    </center>
  </body>
</html>
