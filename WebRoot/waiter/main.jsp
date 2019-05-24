<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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


<link href="./bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

<link href="./bootstrap/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<link href="./bootstrap/dashboard.css" rel="stylesheet">

<script src="./bootstrap/assets/js/ie-emulation-modes-warning.js"></script>

</head>

<body>
	
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">服务员</a>
			
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li> <a>您好！waiter</a>
				<li><a onclick="getResult()" type="button" class="a a-default" aria-label="Left Align">
						  <span style="font-size:25px;" id="newOrder" class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
						</a></li>
				<li><a href="obtainNewOrders" class="navbar-brand" id="newOrderText"></a></li>
				<li><a href="#">帮助</a></li>
				<li><a href="admin/AdminLogin.jsp">注销</a></li>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="搜索...">
				<input type="submit" value="搜索">
			</form>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">统计结果</h1>

				 <div class="row placeholders">
              
              <s:iterator value="orders">
          
          <div class="col-xs-6 col-sm-4 placeholder">
            <s:form style="background-color:gray;color:black;">
           
            <font style="color:yellow">订单状态：<span id="state<s:property value="oid"/>"><s:property value="stateString" />&nbsp;&nbsp;&nbsp;</span></font>
      <s:if test="state==0">
              		<input class="btn btn-default" type="button" onclick="sendToChef(<s:property value="oid"/>,1)" value="送至厨师">
              </s:if>
              <s:elseif test="state==1">
              		<input class="btn btn-default" type="button" onclick="sendToChef(<s:property value="oid"/>,2)" value="完成就餐">
              </s:elseif>
              <s:elseif test="state==3">
              	<input class="btn btn-default" type="button" onclick="sendToChef(<s:property value="oid"/>,0)" value="确认接单">
              </s:elseif>
              <s:textfield name="oid" value="%{ oid }" label="订单编号" class="sendToChefoid%{oid}" disabled="true"/>
              <s:textfield name="uid" value="%{ uid }" label="下单人"  disabled="true"/>
              <s:textfield name="tid" id="tid%{oid}" value="%{ tid }" label="餐桌"   disabled="true"/>
              <s:textfield name="number" label="就餐人数"  class="choiceTablenumber%{oid}" disabled="true"/>
              <s:textfield name="totle" value="%{ totle }" label="总共金额"  disabled="true"/>
              <s:textfield name="discount" value="%{ discount }" label="折扣"  disabled="true"/>
               <s:textfield label="使用积分" value="%{integral}" disabled="true"/>
              <s:textfield label="积分折扣" value="%{integral/10}" disabled="true"/>
              <s:textfield name="price" value="%{ price }" label="支付金额（元）"  disabled="true"/>
              <s:date name="date" var="vf" format="yyyy年MM月dd日 HH:mm:ss"/> 
              <s:textfield name="date" value="%{vf}" label="下单时间"  disabled="true"/> 
              <s:textfield name="state" value="%{ stateString }" label="订单状态"  disabled="true"/>
              
              
              <s:textfield name="mid" value="%{ midString }" label="购买餐品"  disabled="true"/>
              <s:textfield name="phone" value="%{ phone }" label="联系电话"  disabled="true"/>
              <s:if test="%{payStateString==\"已付款\"}"><s:textfield name="payState" value="%{ payStateString }" label="付款状态"  disabled="true"/></s:if>
              <s:else><s:textfield name="payState" value="%{ payStateString }" label="付款状态"  disabled="true"/>
          
           
              </s:else>
              <s:textfield name="note" value="%{ note }" label="批注"  disabled="true"/>
     			
		     	</s:form>
             </div></s:iterator> 
            
          </div>

			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script>window.jQuery || document.write('<script src="./bootstrap/assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="./bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="./bootstrap/assets/js/vendor/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="./bootstrap/assets/js/ie10-viewport-bug-workaround.js"></script>


</body>

<script type="text/javascript"
	src="<%=basePath%>/scripts/jquery-3.4.1.js"></script>
<script type="text/javascript">
   $("#newOrder").ready(function(){  
 	 c = window.setInterval("getResult()",1000); //间隔多少秒去触发ajax
 	 }); 
 
  function getResult(){
	$.ajax({
         type:"post",
         url:"newOrder",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
         data:{//设置数据源
         },
         dataType:"json",//设置需要返回的数据类型
         success:function(data){
             var d = eval("("+data+")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
             /* alert("" + d.hadpay+""); */
             if(d.hadpay==1001){
             document.getElementById('newOrder').style.color="red";
              document.getElementById('newOrderText').textContent="有新的订单";
              document.getElementById('newOrderText').style="color:red;front:30px"
             }
             
         },
         error:function(){
             alert("系统异常，请稍后重试！");
         }//这里不要加","
     });	
  };

	function sendToChef(aa,statenum){
	$.ajax({
         type:"post",
         url:"changeOrderState",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
         data:{//设置数据源
         	oid:$(".sendToChefoid"+aa).val(),
         	state:statenum
         },
         dataType:"json",//设置需要返回的数据类型
         success:function(data){
             var d = eval("("+data+")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
             //得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
              //
          //   $("#state"+aa).text(""+d.stateString+"");
          	window.location="obtainUnservedOrders";
         },
         error:function(){
             alert("系统异常，请稍后重试！");
         }//这里不要加","
     });	
	
	}
</script>
</html>
