<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="<%=basePath%>/css/style.css">
    <title>user登录</title>


  </head>
   <body>

  <center><h1><b>用户登录</b></h1></center>
	<center>
	<div id="wrapper" class="login-page">
		<div id="login_form" class="form">
		
		<form class="login-form" action="userLogin" method="post">
		<s:fielderror name="error"></s:fielderror>
				<input type="text" placeholder="用户名" name="uid" id="user_name"/>
				<input type="password" placeholder="密码"  name="upass" id="password"/>
				<button type="submit" id="login">登　录</button>
				<p class="message">还没有账户? <a href="<%=basePath%>user/regist.jsp">立刻创建</a></p>
				<p class="message">忘记密码? <input type="button" onclick="retrivePassword()" value="找回密码"></p>
			</form>
	</div>
	</div>
     </center>
  </body>
  <script type="text/javascript" src="<%=basePath%>/scripts/jquery-3.4.1.js"></script>
  <script type="text/javascript">
function retrivePassword(){
	var uu=prompt("请输入需要重置密码的账号");
    $.ajax({
        type:"post",
        url:"findPassword",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
        data:{//设置数据源
        	uid:uu
        },
        dataType:"json",//设置需要返回的数据类型
        success:function(data){
            var d = eval("("+data+")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
            //得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
             
           alert(""+d.tfpMessage+"");
             
        },
        error:function(){
            alert("系统异常，请稍后重试！");
        }//这里不要加","
    });
    
};
  
</script>
</html>
