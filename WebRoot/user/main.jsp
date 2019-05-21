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
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><s:property value="#session.uid"/>的个人中心</a>s
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li><a>您好！<s:property value="#session.uid"/></a></li>
				<li><a href="#">帮助</a></li>
				<li><a href="admin/AdminLogin.jsp">注销</a></li>
			</ul>
		
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
		
			<div class="col-sm-3 col-md-2 sidebar" style="padding-left:0;padding-top:0px;">
			<div class="container" style="padding-left:0;">

			<div class="leftsidebar_box" style="width:210px">
				<dl class="custom">
					<dt onClick="changeImage()">个人信息<img src="<%=basePath%>admin/images/left/select_xl01.png"></dt>
					<!-- <dd class="first_dd"><a  href="meal/addMeal.jsp">修改密码</a></dd> -->
					<dd class="first_dd"><a class="a" onclick="changeright('user')">个人信息</a></dd>
					<dd class="first_dd"><a class="a" onclick="changeright('changePass')">修改密码</a></dd>
					<dd><a onclick="changeright('changePhone')">更换手机号</a></dd>
				</dl>
				
			
			</div>
		
		</div>
				
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="margin-top:50px;">
					<div class="row placeholders">
					
						 <form action="" class="form user">
						 	<table class="table">
						 	<tr><th>用户名<th><s:property value="uid"/>
						 	
						 	<tr><th>电话<td><s:property value="phone"/>
						 	<tr><th>邮箱<td><s:property value="email"/>
						 	
						 	<tr><th>余额<td><s:property value="balance"/>
						 	<tr><th>积分<td><s:property value="integral"/>
						 	<tr><th>会员等级<td><s:property value="vipString"/>
						 	</table>
						 </form>
						 <form  class="form changePass" hidden="true">
						 	<s:fielderror id="updatemessage" name="mainerror"/>
						 	
						 	<table class="table" >
						 	<s:hidden name="uid"  value="%{uid}"/>
						 	<s:hidden name="phone"  value="%{phone}"/>
						 	<tr><th>新密码<th><input name="upass" type="password" class="upass">
						 	<tr><th>确认密码<th><input name="upass1" type="password" class="reupass">
						 	<tr><th><td><input onclick="modifyPassWord()" class="btn" type="button" value="确认更改">
						 	
						 	</table>
						 </form>
						 <form  class="form changePhone" hidden="true">
						 	<table class="table" >
						 	<tr><th>新的电话号码<th><input type="number" class="phone">
						 	<tr><th><td><input onclick="modifyPhone()" class="btn" type="button" value="确认更改">
						 	
						 	</table>
						 </form>
						<%--  <div class="redenvelopes" hidden="true">
							 <s:iterator>
								 <table class="table" >
								 	
								 	<tr><th>可抵消<th><s:property value="price"/>
								 	<tr><th>描述<th><s:property value="describe"/>
								 	
								 	</table>
							 	</s:iterator>
						 </div> --%>
	         		 </div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="./bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="./bootstrap/assets/js/vendor/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="./bootstrap/assets/js/ie10-viewport-bug-workaround.js"></script>


</body>

<script type="text/javascript"
	src="<%=basePath%>/scripts/jquery-3.4.1.js"></script>

<script type="text/javascript">
/* var uid;
var pass;
var pass1;
var phone; */

function modifyPhone(){
		var a='<%=session.getAttribute("uid")%>';
     if(a == "null"){
     	window.location.href="<%=basePath%>/user/login.jsp";
     }else{
		var uid='<s:property value="uid"/>';
		var uphone=$(".phone").val();

$.ajax({
    type:"post",
    url:"modifyPhone",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
    data:{//设置数据源
    	'uid':uid,
    	'phone':uphone
    },
    dataType:"json",//设置需要返回的数据类型
    success:function(data){
        var d = eval("("+data+")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
        //得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
         alert(""+d.message+"  ");
    },
    error:function(){
        alert("系统异常，请稍后重试！");
    }//这里不要加","
});
}
}

function modifyPassWord(){
var a='<%=session.getAttribute("uid")%>';
                if(a == "null"){
                	window.location.href="<%=basePath%>/user/login.jsp";
                }else{
var uid='<s:property value="uid"/>';
var pass=$(".upass").val();
var pass1=$(".reupass").val();

$.ajax({
    type:"post",
    url:"modifyPassWord",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
    data:{//设置数据源
    	'uid':uid,
    	'upass':pass,
    	'upass1':pass1
    },
    dataType:"json",//设置需要返回的数据类型
    success:function(data){
        var d = eval("("+data+")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
        //得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
        /*  alert(uid+pass+pass1+"111"); */
        /*  $("#updatemessage").text(""+d.message+"");  */
         alert(""+d.message+"  ");
       /*  changeright('changePass'); */
    },
    error:function(){
        alert("系统异常，请稍后重试！");
    }//这里不要加","
});
}
}


function changeright(show1){
	$(".user").hide();
	$(".changePass").hide();
	$(".changePhone").hide();
	$("."+show1).show();
	
};
$(".leftsidebar_box dt").css({"background-color":"#3992d0"});
$(".leftsidebar_box dt img").attr("src","<%=basePath%>admin/images/left/select_xl01.png");
<%-- $(function(){
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
}) --%>
</script>

</html>
