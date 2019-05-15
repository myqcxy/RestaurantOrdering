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
			<a class="navbar-brand" href="#">收银员</a>
			<s:debug></s:debug>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a >您好！<s:property value="#session.cid"/></a></li>
				<li><a href="cashierLogout">注销</a></li>
			</ul>
			
			<form action="CashierSearch" method="post" class="navbar-form navbar-right">
				<input name="searchText" type="text" class="form-control" placeholder="搜索...">
				<select name="searchCondition">
					<option value="uid">用户名</option>
					<option value="oid">订单号</option>
					<option value="phone">手机号</option>
				</select>
				<input type="submit" value="搜索">
			</form>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
				</ul>

			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				
					<h1 style="color:red"><div id="datetime" class="page-header">
					    <script>
					        setInterval("document.getElementById('datetime').innerHTML='系统时间：'+new Date().toLocaleString();", 500);
					    </script>
					</div></h1>
					<h1 class="page-header">统计结果</h1>
				 <div class="row placeholders"> <s:if test="%{user.uid!=null}">
				 <div class="col-xs-6 col-sm-4 placeholder">
				  
			<s:form action="recharge" method="post" style="background-color:gray;color:black;">
           
              <s:textfield class="rechargeUid" name="user.uid"  label="用户名"  disabled="true"/>
              <s:textfield type="hidden" name="user.uid"/>
              <s:textfield name="user.phone"  label="电话"  disabled="true"/>
              <s:textfield  name="user.balance" label="余额" id="balance" disabled="true"/>
              <s:textfield class="rechargeAmount" name="rechargeAmount" label="充值金额"/>
              
              <s:submit value="充值" />
		     	</s:form>
				 
				 </div></s:if>
				 
				  <s:iterator value="orders">
          
          <div class="col-xs-6 col-sm-4 placeholder">
            <s:form style="background-color:gray;color:black;">
            
         	
              <s:textfield name="oid" value="%{ oid }" label="订单编号" class="choiceTableoid%{oid}" disabled="true"/>
              <s:textfield name="uid" value="%{ uid }" label="下单人"  disabled="true"/>
              <s:if test="tid==0">
	           <tr><td> <font style="color:yellow"><span id="table<s:property value="oid"/>">
	         	餐桌尚未选择&nbsp;&nbsp;&nbsp;</span></font>
          		<td><input id="button<s:property value="oid"/>" type="button" onclick="choiceTable(<s:property value="oid"/>)" style="color:red"  value="选择餐桌">	
          		</s:if><s:else>
          		<s:textfield name="tid"  label="餐桌编号"  readonly="true"/>
          		
          		</s:else>
              <s:textfield name="number" label="就餐人数"  class="choiceTablenumber%{oid}" disabled="true"/>
              <s:textfield name="totle" value="%{ totle }" label="总共金额"  disabled="true"/>
              <s:textfield name="discount" value="%{ discount }" label="折扣"  disabled="true"/>
              <s:textfield name="price" value="%{ price }" label="支付金额（元）"  disabled="true"/>
              <s:date name="date" var="vf" format="yyyy年MM月dd日 HH:mm:ss"/> 
              <s:textfield name="date" value="%{vf}" label="下单时间"  disabled="true"/> 
              <s:textfield name="state" value="%{ stateString }" label="订单状态"  disabled="true"/>
              <s:textfield name="mid" value="%{ midString }" label="购买餐品"  disabled="true"/>
              <s:textfield name="phone" value="%{ phone }" label="联系电话"  disabled="true"/>
               <s:textfield name="note" value="%{ note }" label="批注"  disabled="true"/>
              <s:if test="%{payStateString==\"已付款\"}"><s:textfield name="payState" value="%{ payStateString }" label="付款状态"  disabled="true"/></s:if>
              <s:else><s:textfield name="payState" value="%{ payStateString }" label="付款状态"  readonly="true"/>
          <s:url action="cashRegister"  var="cashRegisterUrl">
          	<s:param name="oid" value="%{ oid }"></s:param>
          	<s:param name="user.uid" value="%{ user.uid }"></s:param>
          	
          </s:url><tr><td><td style="text-align:right;padding:10px;bgcolor:#FFFFFF">
            <a href="${ cashRegisterUrl}" style="background-color:red;color:#FFFFFF;padding:10px">收银</a> 
              </s:else>
             
     			
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
<script>
	function readyDel() {
		return confirm("是否真的删除?");
	}
</script>
<script type="text/javascript"
	src="<%=basePath%>/scripts/jquery-3.4.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>/scripts/jquery-3.4.1.js"></script>
<script type="text/javascript">
	function choiceTable(aa){
	$.ajax({
         type:"post",
         url:"choiceTable",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
         data:{//设置数据源
         	oid:$(".choiceTableoid"+aa).val(),
         	number:$(".choiceTablenumber"+aa).val()
         },
         dataType:"json",//设置需要返回的数据类型
         success:function(data){
             var d = eval("("+data+")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
             //得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
              //
             
             if(d.tid==-1)
              alert("请稍等，现在餐桌已经用完！！");
              else{
               alert("订单编号为"+aa + "的餐桌为" + d.tid);
               $("#table"+aa).text("餐桌为" + d.tid);
               $("#button"+aa).attr("style","display:none;");
               }
         },
         error:function(){
             alert("系统异常，请稍后重试！");
         }//这里不要加","
     });	
	
	}
</script>
</html>
