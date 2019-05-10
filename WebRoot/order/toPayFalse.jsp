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


    

  </head>
  
  <body>
	<center>
<s:debug></s:debug>
    <div class="container-fluid">
      <div class="row">
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <div class="row placeholders">
           <s:iterator value="list">
            <div class="col-xs-6 col-sm-3 placeholder">
             
              <s:property value="mname"/>
		   		&nbsp;&nbsp;&nbsp;￥:<s:property value="price"/><br>
		   		类别：<s:property value="category"/><br>
		   		<img alt="图片走丢了" src="${ photo }" width="100px" height="100px" ><br>
		   		
		   		
            </div></s:iterator>
            
            <s:form action="toPay" method="post">
            	<s:textfield name="totle" label="总金额" style=""/>
            	<s:textfield name="discount" label="折扣"/>
            	<s:textfield name="price" label="应付"/>
            	<s:textfield name="phone" label="电话"/>
            	<s:textfield name="number" label="人数"/>
            	<s:textarea name="note" label="备注"/>
            	 <% String isok=(String)session.getAttribute("placeAnOrderRes");
             	if(isok.equalsIgnoreCase("ok")){
             	
             		%>
             		<s:submit value="去付款"></s:submit>
             		<% 
             	}else {
             	%>
             		<p>余额不足
             		<% 
             	}
			  %>
            	<h1>付款失败</h1>
            </s:form>
          </div>
 
          
        </div>
      </div>
    </div>
</center>
  </body>
</html>
