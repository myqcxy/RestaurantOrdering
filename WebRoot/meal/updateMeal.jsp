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
  		<div class="col-xs-8">
  		<div class="page-header">
		 	<small><h3><a href="admin/main.jsp">返回我的主页</a></h3></small>
		  
		</div>
  		 <div class="page-header">
		  <h1>管理员 <small>修改餐品信息</small></h1>
		  
		</div>
		<div class="media">
		  <div class="media-left">
		    <a >
		      <img width="200px" height="200px" class="media-object" src="${photo }" alt="${mname}">
		    </a>
		  </div>
		  
		  <s:fielderror id="mname" class="alert alert-warning" />
		<s:form class="media-body" theme="simple" action="updateMeal" method="post" enctype="multipart/form-data">
  	  <div class="input-group input-group-lg">
		  <span class="input-group-addon" id="basic-addon1">餐品名称</span>
		  <input name="mname" value="${mname}" required="true" type="text" class="form-control" placeholder="例如：黄焖鸡" aria-describedby="basic-addon1">
		  
		</div>
		<s:hidden name="mid"></s:hidden>
		<div class="input-group  input-group-lg">
		  <span class="input-group-addon" id="basic-addon1">餐品价格</span>
		  <input value="${price}"  name="price" required="true"  type="number" class="form-control" placeholder="大于0的小数" aria-describedby="basic-addon1">
		</div>
		<div class="input-group input-group-lg">
		  <span class="input-group-addon" id="basic-addon1">餐品类别</span>
		  <s:select name="Category" class="form-control" list="{'主食','饮料','晚餐','小吃'}" />
		</div>
		<div class="input-group input-group-lg">
		  <span class="input-group-addon" id="basic-addon1">餐品图片</span>
		  <input  name="mphoto" type="file" class="form-control"  aria-describedby="basic-addon1">
		</div>
    	<s:submit style="margin-left: 353px;" class="btn  btn-lg" value="提交" />
    	</s:form>
	</div>
		
  		
  		
  		
  		</div>
  		
  	</div>
  </div>
  	 
   
  </body>
</html>
