<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>基于jsp的餐厅点餐系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">


    <link href="./../bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <link href="./../bootstrap/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <link href="./../bootstrap/dashboard.css" rel="stylesheet">

    <script src="./../bootstrap/assets/js/ie-emulation-modes-warning.js"></script>

  </head>
  
  <body>
  

    <div class="container-fluid">
      <div class="row">
       
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

          <div class="row placeholders">
           <s:iterator value="list">
            <div class="col-xs-6 col-sm-3 placeholder">
              <%-- <img src="${ list[0].photo }" width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span> --%>
              <s:property value="mname"/>
		   		&nbsp;&nbsp;&nbsp;￥:<s:property value="price"/><br>
		   		类别：<s:property value="category"/><br>
		   		销量：<s:property value="sales"/><br>
		   		<img alt="图片走丢了" src="${ photo }" width="200px" height="200px" ><br>
		   		
		   		<s:url var="addToCacheUrl" action="addToCache">
		         <s:param name="mid" value="mid"/>
		      </s:url>
		   		<a href="${ addToCacheUrl }"><font color="red" size="10">+</font></a>
		   		<%-- <%@ include file="从页面文件地址"%> --%>
		   		<input type="text" style="width:50px;text-align:center;" value="sales" >
		   		
		   		 <s:url var="delFromCacheUrl" action="delFromCache">
		         <s:param name="mid" value="mid"/>
		      </s:url>
		      <a href="${delFromCacheUrl}" onClick="return readyDel();"><font color="red" size="10">-</font></a>
            </div></s:iterator>
            
          </div>

        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script>window.jQuery || document.write('<script src="./../bootstrap/assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="./../bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="./../bootstrap/assets/js/vendor/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./../bootstrap/assets/js/ie10-viewport-bug-workaround.js"></script>
  
  </body>
</html>
