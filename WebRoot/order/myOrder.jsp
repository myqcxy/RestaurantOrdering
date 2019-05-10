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
	<a href="index.jsp">返回主页</a>
    <div class="container-fluid">
      <div class="row">
        <s:debug></s:debug>
       <!--  <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> -->
         <!--  <div class="row placeholders"> -->
          
          <s:iterator value="orders">
          
           <!--  <div class="col-xs-6 col-sm-3 placeholder"> -->
            <s:form>
           
              <s:textfield name="oid" value="%{ oid }" label="订单编号"  disabled="true"/>
              <s:textfield name="uid" value="%{ uid }" label="下单人"  disabled="true"/>
              <s:textfield name="tid" value="%{ tid }" label="餐桌"  disabled="true"/>
              <s:textfield name="number" value="%{ number }" label="就餐人数"  disabled="true"/>
              <s:textfield name="totle" value="%{ totle }" label="总共金额"  disabled="true"/>
              <s:textfield name="discount" value="%{ discount }" label="折扣"  disabled="true"/>
              <s:textfield name="price" value="%{ price }" label="支付金额（元）"  disabled="true"/>
              <s:date name="date" var="vf" format="yyyy年MM月dd日 HH:mm:ss"/> 
              <s:textfield name="date" value="%{vf}" label="下单时间"  disabled="true"/> 
              <s:textfield name="state" value="%{ stateString }" label="订单状态"  disabled="true"/>
              <s:textfield name="mid" value="%{ midString }" label="购买餐品"  disabled="true"/>
              <s:textfield name="phone" value="%{ phone }" label="联系电话"  disabled="true"/>
             <%--  <s:textfield name="method" value="%{ method }" label="下单方式"  disabled="true"/>
              <s:textfield name="payMethod" value="%{ payMethodString }" label="付款方式"  disabled="true"/> --%>
              <s:textfield name="payState" value="%{ payStateString }" label="付款状态"  disabled="true"/>
              <s:textfield name="note" value="%{ note }" label="批注"  disabled="true"/>
     
		     	</s:form><br>
            <!-- </div> --></s:iterator>
            
           
        <!--   </div> -->
 
          
       <!--  </div> -->
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
                  window.location.href="index.jsp";
              },
              error:function(){
                  alert("系统异常，请稍后重试！");
              }//这里不要加","
          });
      };
           

  </script>
</html>
