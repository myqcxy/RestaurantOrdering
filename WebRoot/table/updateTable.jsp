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
<link href="./bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="./bootstrap/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">
<link href="./bootstrap/dashboard.css" rel="stylesheet">
<script src="./bootstrap/assets/js/ie-emulation-modes-warning.js"></script>
  </head>
  
  <body>
  <div class="container">
  	<div class="row">
  		<div class="col-xs-2"></div>
  		<div class="col-xs-5">
  		<div class="page-header">
		 	<small><h3><a href="admin/main.jsp">返回我的主页</a></h3></small>
		  
		</div>
  		 <div class="page-header">
		  <h1>管理员 <small>请编辑餐桌信息</small></h1>
		  
		</div>
		<s:fielderror id="mname" class="alert alert-warning" />
		<s:form theme="simple" action="updateTable" method="post" >
  	  <div class="input-group input-group-lg">
		  <span class="input-group-addon" id="basic-addon1">餐桌编号</span>
		  <input value="${tid}" readonly="true" name="tid" required="true" type="text" class="form-control"  aria-describedby="basic-addon1">
		  
		</div>
		<%-- <s:fielderror id="price" class="alert alert-warning" /> --%>
		<div class="input-group  input-group-lg">
		  <span class="input-group-addon" id="basic-addon1">可入座人数</span>
		  <input value="${number}"  name="number" required="true"  class="form-control" aria-describedby="basic-addon1">
		</div>
		<div class="input-group  input-group-lg">
		  <span class="input-group-addon" id="basic-addon1">描述</span>
		  <input  value="${describe}" name="describe" required="true"  type="text" class="form-control"  aria-describedby="basic-addon1">
		</div>
		 
    	<s:submit style="margin-left: 353px;" class="btn  btn-lg" value="提交" ></s:submit>
    	</s:form>
  		
  		
  		
  		</div>
  		
  	</div>
  </div>
  </body>
</html>
