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
<style type="text/css">
body{margin:0;padding:0;overflow-x:hidden;}
html, body{height:100%;}
img{border:none;}
dl,dt,dd{display:block;margin:0;}
a{text-decoration:none;}

#bg{background-image:url(<%=basePath%>admin/images/content/dotted.png);}
.container{width:100%;height:100%;margin:auto;}

/*left*/
.leftsidebar_box{width:160px;height:auto !important;overflow:visible !important;position:fixed;height:100% !important;background-color:#3992d0;}
.line{height:2px;width:100%;background-image:url(<%=basePath%>admin/images/left/line_bg.png);background-repeat:repeat-x;}
.leftsidebar_box dt{padding-left:55px;padding-right:10px;background-repeat:no-repeat;background-position:10px center;color:#f5f5f5;font-size:14px;position:relative;line-height:48px;cursor:pointer;}
.leftsidebar_box dd{background-color:#317eb4;padding-left:58px;}
.leftsidebar_box dd a{color:#f5f5f5;line-height:20px;}
.leftsidebar_box dt img{position:absolute;right:10px;top:20px;}
.system_log dt{background-image:url(<%=basePath%>admin/images/left/system.png)}
.custom dt{background-image:url(<%=basePath%>admin/images/left/custom.png)}
.channel dt{background-image:url(<%=basePath%>admin/images/left/channel.png)}
.app dt{background-image:url(<%=basePath%>admin/images/left/app.png)}
.cloud dt{background-image:url(<%=basePath%>admin/images/left/cloud.png)}
.syetem_management dt{background-image:url(<%=basePath%>admin/images/left/syetem_management.png)}
.source dt{background-image:url(<%=basePath%>admin/images/left/source.png)}
.statistics dt{background-image:url(<%=basePath%>admin/images/left/statistics.png)}
.leftsidebar_box dl dd:last-child{padding-bottom:10px;}
</style>
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> 
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="admin/main.jsp">用户管理员</a>s
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a>您好！admin</a></li>
				<li><a href="#">帮助</a></li>
				<li><a href="admin/AdminLogin.jsp">注销</a></li>
			</ul>
			<form action="adminSearch" class="navbar-form navbar-right">
				<input name="searchText" type="text" class="form-control" placeholder="搜索...">
				<select class="btn" name="searchCondition" >
					<option value="mname">餐品名</option>
					<option value="mid">餐品编号</option>
				</select>
				<input class="btn btn-default" type="submit" value="搜索">
			</form>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
		
			<div class="col-sm-3 col-md-2 sidebar" style="padding-left:0;padding-top:0px;">
			<div class="container" style="padding-left:0;">

			<div class="leftsidebar_box" style="width:210px">
				<!-- <div class="line">
				
				
				</div> -->
				
				<dl class="syetem_management">
					<dt onClick="changeImage()">店铺管理<img src="<%=basePath%>admin/images/left/select_xl01.png"></dt>
					<dd class="first_dd"><a href="manageShop">管理店铺信息</a></dd>
				</dl>
				
				<dl class="system_log">
					<dt onClick="changeImage()">餐品管理<img src="<%=basePath%>admin/images/left/select_xl01.png"></dt>
					<dd class="first_dd"><a href="meal/addMeal.jsp">添加餐品</a></dd>
					<dd><a href="getAllMeals">修改餐品信息</a></dd>
					<dd><a href="getAllMeals">浏览餐品信息</a></dd>
				</dl>
				
				
			
				<dl class="custom">
					<dt onClick="changeImage()">餐桌管理<img src="<%=basePath%>admin/images/left/select_xl01.png"></dt>
					<dd class="first_dd"><a href="table/addTable.jsp">添加餐桌</a></dd>
					<dd><a href="showAllTables">显示餐桌<span class="sr-only">(current)</span></a></dd>
					<dd><a href="showAllTables">修改餐桌<span class="sr-only">(current)</span></a></dd>
				</dl>
				<dl class="statistics">
					<dt>统计分析<img src="<%=basePath%>admin/images/left/select_xl01.png"></dt>
					<dd class="first_dd"><a href="salesStatistics">按照销量降序</a></dd>
					<dd class="first_dd"><a href="salesStatistics?statisticMethod=1">按照销量升序</a></dd>
					<dd class="first_dd"><a href="salesStatistics?statisticMethod=2">按照好评升序</a></dd>
					<dd class="first_dd"><a href="salesStatistics?statisticMethod=3">按照好评降序</a></dd>
				</dl>
			
				
			
			</div>
		
		</div>
				
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="margin-top:50px;">
				<h1 class="page-header">统计结果</h1>

				 <div class="row placeholders">
				 <s:if test="sname!=null">
				 <form action="updateShop" type="post">
				 	<table class="table">
				 		<tr><td>店铺名<td><input value="${sname} " name="sname" type="text" required="true">
				 		<tr><td>地址<td><input value="${address }" name="address" type="text" required="true">
				 		<tr><td>联系电话<td><input value="${phone }" name="phone" type="text" required="true">
				 		<tr><td>QQ<td><input value="${qq }" name="qq" type="text" required="true">
				 		<tr><td>微信<td><input value="${wechat }" name="wechat" type="text" required="true">
				 		<tr><td><td><input type="submit" class="btn" value="更改">
				 		
				 	</table>
				 </form></s:if>
           <s:iterator value="list">
            <div class="col-xs-6 col-sm-3 placeholder">
              <b style="color:red">优惠：<s:property value="discount"/></b><br>
              <s:property value="mname"/>
              <%-- <table class="table">
              <tr><td>￥:<td><s:property value="price"/>
              </table> --%>
		   		&nbsp;&nbsp;&nbsp;￥:<s:property value="price"/><br>
		   		类别：<s:property value="category"/><br>
		   		销量：<s:property value="sales"/><br>
		   		<img alt="图片走丢了" src="${ photo }" width="200px" height="200px" ><br>
		   	<s:url var="updateUrl" action="toUpdateMeal">
         <s:param name="mid" value="mid"/>
      </s:url>
   		<a  class="a a-default" href="${updateUrl }">修改</a>
   		
   		 <s:url var="delUrl" action="delMeal">
         <s:param name="mid" value="mid"/>
      	</s:url>
      <a  class="a a-default" href="${delUrl}" onClick="return readyDel();">删除</a>
	      <s:url var="setDiscountUrl" action="setDiscount">
	         <s:param name="mid" value="mid"/>
	      	</s:url>
      	<a class="a a-default" href="${setDiscountUrl }">设置此餐品的优惠信息</a>
   	 
   		<br><br>
		       
		   		
            </div></s:iterator>
            <s:iterator value="orders">
          
          <div class="col-xs-6 col-sm-4 placeholder">
            <s:form style="background-color:gray;color:black;height: 420px;">
            
         	
              <s:textfield name="oid" value="%{ oid }" label="订单编号" class="choiceTableoid%{oid} sss" disabled="true"/>
              <s:textfield name="uid" value="%{ uid }" label="下单人"  disabled="true"/>
              
          	
          		<s:textfield name="tid"  label="餐桌编号"  readonly="true"/>
          		
          	
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
               <s:textfield name="note" value="%{ note }" label="批注"  disabled="true"/>
               <input type="hidden" id="order${oid}" value="${grade}">
               <tr><td>
             
             	评价等级 <td><select disabled="true" id="select<s:property value="oid"/>" value="%{grade}" class="select<s:property value="oid"/>" name="evaluationLevel">
					<option value="0">选择评价等级</option>
					<option value="1">很不满意 ★</option>
					<option value="2">较不满意 ★★</option>
					<option value="3">基本满意 ★★★</option>
					<option value="4">满意 ★★★★</option>
					<option value="5">非常满意 ★★★★★</option>
				</select>
             <s:textfield name="payState" value="%{ payStateString }" label="付款状态"  disabled="true"/>
             
             
     			
		     	</s:form>
             </div></s:iterator> 
          </div>
		
				
				
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script>window.jQuery || document.write('<script src="<%=basePath%>bootstrap/assets/js/vendor/jquery-2.1.1.min.js"><\/script>')
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
<script type="text/javascript">
	
</script>
<script type="text/javascript">
$(function(){
  	$(".sss").each(function(){
  		var id=$(this).val();
  		var order=$("#order"+id).val();
  		
  		$(".select"+id).val(order);
  	});
  });
$(".leftsidebar_box dt").css({"background-color":"#3992d0"});
$(".leftsidebar_box dt img").attr("src","<%=basePath%>admin/images/left/select_xl01.png");
$(function(){
	$(".leftsidebar_box dd").hide();
	$(".leftsidebar_box dt").click(function(){
		$(".leftsidebar_box dt").css({"background-color":"#3992d0"})
		$(this).css({"background-color": "#317eb4"});
		$(this).parent().find('dd').removeClass("menu_chioce");
		$(".leftsidebar_box dt img").attr("src","<%=basePath%>admin/images/left/select_xl01.png");
		$(this).parent().find('img').attr("src","<%=basePath%>admin/images/left/select_xl.png");
		$(".menu_chioce").slideUp(); 
		$(this).parent().find('dd').slideToggle();
		$(this).parent().find('dd').addClass("menu_chioce");
	});
})
</script>
 <script>
      function readyDel(){
        return confirm("是否真的删除?");
      }
    </script>
</html>
