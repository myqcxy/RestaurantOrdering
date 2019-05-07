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
    	登录成功！！！<a href="">我的购物车</a><br>
    	<form action="" method="post">
    		<input name="seach" type="text"/>&nbsp;&nbsp;<input value="搜索" type="submit">
    	</form>
    	<a href="">主食</a>
    	<a href="">饮料</a>
    	<a href="">配菜</a>
    	<a href="">小吃</a><br><br>
    	<s:iterator value="list">
   	
   		<s:property value="mname"/>
   		&nbsp;&nbsp;&nbsp;￥:<s:property value="price"/><br>
   		类别：<s:property value="category"/><br>
   		销量：<s:property value="sales"/><br>
   		<img alt="图片走丢了" src="${ photo }" width="100px" height="80px"><br>
   		<s:url var="updateUrl" action="toUpdateMeal">
         <s:param name="mid" value="mid"/>
      </s:url>
   		<a href="${updateUrl }">加入订单</a>
   		
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
    </center>
    <script type="text/javascript">
  	function del(id){
  		form.action="";
  		form.submit()
  	}
  	window.onload=function(){
  		window.location.href="getAllMeals";
  	}
  	
  
  </script>
  </body>
  
</html>
