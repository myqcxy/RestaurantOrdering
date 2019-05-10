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
            
            <s:form >
            	<s:textfield name="totle" label="总金额" class="totle"/>
            	<s:textfield name="discount" label="折扣" class="discount"/>
            	<s:textfield name="price" label="应付" class="price"/>
            	<s:textfield name="phone" label="电话" class="phone"/>
            	<s:textfield name="number" label="人数" class="number"/>
            	<s:textarea name="note" label="备注" class="note"/>
            	 <% String isok=(String)session.getAttribute("placeAnOrderRes");
             	if(isok.equalsIgnoreCase("ok")){
             		%>
             		<input type="button" onclick="topay(1)" value="去付款">
             		<% 
             	}else {
             	%>
             		<input type="button"  onclick="topay(2)" value="余额不足，到店付款">
             		<% 
             	}
			  %>
            	
           </s:form>
          </div>
 
          
        </div>
      </div>
    </div>
</center>
  </body>
  <script type="text/javascript" src="<%=basePath%>/scripts/jquery-3.4.1.js"></script>
  <script type="text/javascript">
          function topay(aa){
          $.ajax({
              type:"post",
              url:"toPay",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
              data:{//设置数据源
              	totle:$(".totle").val(),
              	discount:$(".discount").val(),
              	price:$(".price").val(),
              	phone:$(".phone").val(),
              	number:$(".number").val(),
              	payState:aa,
              	note:$(".note").val()
              },
              dataType:"json",//设置需要返回的数据类型
              success:function(data){
                  alert("下单成功！");
                  window.location.href="myOrder";
              },
              error:function(){
                  alert("系统异常，请稍后重试！");
              }//这里不要加","
          });
      };
           

  </script>
</html>