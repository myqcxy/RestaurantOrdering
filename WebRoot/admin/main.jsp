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
*{font-family:'微软雅黑';font-size:12px;color:#626262;}
dl,dt,dd{display:block;margin:0;}
a{text-decoration:none;}

#bg{background-image:url(<%=basePath%>admin/images/content/dotted.png);}
.container{width:100%;height:100%;margin:auto;}

/*left*/
.leftsidebar_box{width:160px;height:auto !important;overflow:visible !important;position:fixed;height:100% !important;background-color:#3992d0;}
.line{height:2px;width:100%;background-image:url(<%=basePath%>admin/images/left/line_bg.png);background-repeat:repeat-x;}
.leftsidebar_box dt{padding-left:40px;padding-right:10px;background-repeat:no-repeat;background-position:10px center;color:#f5f5f5;font-size:14px;position:relative;line-height:48px;cursor:pointer;}
.leftsidebar_box dd{background-color:#317eb4;padding-left:40px;}
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
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">用户管理员</a>s
			<s:debug></s:debug>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a>您好！admin</a></li>
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
		
			<div class="col-sm-3 col-md-2 sidebar">
			<div class="container">

			<div class="leftsidebar_box">
				<div class="line"></div>
				<dl class="system_log">
					<dt onClick="changeImage()">餐品管理<img src="<%=basePath%>admin/images/left/select_xl01.png"></dt>
					<dd class="first_dd"><a href="meal/addMeal.jsp">添加餐品</a></dd>
					<dd><a href="getAllMeals">修改餐品信息</a></dd>
					<dd><a href="getAllMeals">浏览餐品信息</a></dd>
					<dd><a href="#">操作记录</a></dd>
				</dl>
			
				<dl class="custom">
					<dt onClick="changeImage()">餐桌管理<img src="<%=basePath%>admin/images/left/select_xl01.png"></dt>
					<dd class="first_dd"><a href="table/addTable.jsp">添加餐桌</a></dd>
					<dd><a href="showAllTables">显示餐桌<span class="sr-only">(current)</span></a></dd>
					<dd><a href="showAllTables">修改餐桌<span class="sr-only">(current)</span></a></dd>
					<dd><a href="#">即将到期客户管理</a></dd>
				</dl>
				<dl class="statistics">
					<dt>统计分析<img src="<%=basePath%>admin/images/left/select_xl01.png"></dt>
					<dd class="first_dd"><a href="salesStatistics">统计销量<span class="sr-only">(current)</span></a></dd>
				</dl>
			
				
			
			</div>
		
		</div>
				
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">统计结果</h1>

				 <div class="row placeholders">
           <s:iterator value="list">
            <div class="col-xs-6 col-sm-3 placeholder">
              
              <s:property value="mname"/>
		   		&nbsp;&nbsp;&nbsp;￥:<s:property value="price"/><br>
		   		类别：<s:property value="category"/><br>
		   		销量：<s:property value="sales"/><br>
		   		<img alt="图片走丢了" src="${ photo }" width="200px" height="200px" ><br>
		   	<s:url var="updateUrl" action="toUpdateMeal">
         <s:param name="mid" value="mid"/>
      </s:url>
   		<a href="${updateUrl }">修改</a>
   		
   		 <s:url var="delUrl" action="delMeal">
         <s:param name="mid" value="mid"/>
      	</s:url>
      <a href="${delUrl}" onClick="return readyDel();">删除</a>
	      <s:url var="setDiscountUrl" action="setDiscount">
	         <s:param name="mid" value="mid"/>
	      	</s:url>
      	<a href="${setDiscountUrl }">设置此餐品的优惠信息</a>
   	 
   		<br><br>
		       
		   		
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
