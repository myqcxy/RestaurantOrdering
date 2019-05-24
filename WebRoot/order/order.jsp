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
<link href="./bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="./bootstrap/assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">
<link href="./bootstrap/dashboard.css" rel="stylesheet">
<script src="./bootstrap/assets/js/ie-emulation-modes-warning.js"></script>

    

  </head>
  
  <body>

    <div class="container">
      <div class="row">
        <div class="col-xs-3"></div>
        <div class="col-xs-5">
        <div class="page-header">
		  <h1>下单 <small>请确认下单信息</small></h1>
		  
		</div>
        <table class="table">
        	<tr><th>餐品图片<th>餐品名<th>价格<th>折扣<th>类别
        	<s:iterator value="list">
        	<tr><td><img alt="图片走丢了" src="${ photo }" width="40px" height="30px">
            <td>  <s:property value="mname"/>
		   		<td>￥:<s:property value="price"/>
		   		<td><s:property value="discount"/>
		   		<td><s:property value="category"/>
            </s:iterator>
        </table>
   		<table class="table">
   			<s:form theme="simple">
            	<tr><th>总金额<th><s:textfield name="totle" value="%{totle}" readonly="true" class="totle"/>
            	<tr><th>可用积分<th><s:textfield name="integral"  label="积分可抵"  readonly="true" class="integral"/>
            	<tr><th>积分可抵<th><s:textfield style="width:50px;" name="integral" value="%{integral/10}"   readonly="true" />
            	<label><input type="radio"  onclick="usedIntegral()" value="1" name="useIntegral" id="Integraluser" checked="checked">使用积分</label>
            	<label><input type="radio" onclick="nouseIntegral()" value="0" name="useIntegral" id="Integralnext">下次再用</label>
            	<tr><th>折扣<th><s:textfield name="discount" label="折扣"  readonly="true" class="discount"/>
            	<tr><th>应付<th><s:textfield name="price" id="price1" label="应付" value="%{price-integral/10}"  readonly="true" class="price"/>
            	<tr><th>电话<th><s:textfield type="number" name="user.phone" label="电话" class="phone"/>
           <tr>
           <th>人数:
           <th>
            	<select name="searchCondition" class="number">
					<option value="1">1人</option>
					<option value="2">2人</option>
					<option value="3">3人</option>
					<option value="4">4人</option>
					<option value="5">5人</option>
					<option value="6">6人</option>
					<option value="7">7人</option>
					<option value="8">8人</option>
					<option value="9">9人</option>
					<option value="10">10人以上</option>
					
				</select>
            	<tr><th>备注<th><s:textarea name="note" label="备注" class="note"/>
            	 <% String isok=(String)session.getAttribute("placeAnOrderRes");
             	if(isok.equalsIgnoreCase("ok")){
             		%>
             		<tr>
             		<td>
             		<input class="btn" type="button" onclick="topay(1)" value="去付款"><td>
             		<input class="btn" type="button"  onclick="topay(2)" value="预约，到店付款">
             		<% 
             	}else {
             	%><tr><td><td>
             		<input type="button" class="btn" onclick="topay(2)" value="余额不足，先预约，到店付款">
             		<% 
             	}
			  %>
            	
           </s:form>
   		</table>
          
 
          
        </div>
      </div>
    </div>
  </body>
  <script type="text/javascript" src="<%=basePath%>/scripts/jquery-3.4.1.js"></script>
  
  <script type="text/javascript">
  	function usedIntegral(){
  		
  		document.getElementById("price1").value = '<s:property value="%{price-integral/10}"/>'; 
  	
  	};
  	function nouseIntegral(){
  	
  		document.getElementById("price1").value = '<s:property value="%{price}"/>'; 
  	
  	};
          function topay(aa){
          $.ajax({
              type:"post",
              url:"toPay",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
              data:{//设置数据源
              	totle:$(".totle").val(),
              	discount:$(".discount").val(),
              	integral:$(".integral").val(),
              	useIntegral:$('input[type=radio][name=useIntegral]:checked').val(),
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
