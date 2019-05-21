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
    
    <title>My JSP 'regist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath%>/css/style.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <div id="wrapper" class="login-page">
		<div id="login_form" class="form">
		<form class="registerform" action="userRegist" method="post">
		<s:fielderror name="error"></s:fielderror>
				<input required="true" type="text" name="uid" placeholder="用户名" id="r_user_name"/>
				<input required="true" type="password" name="upass" placeholder="密码" id="r_password" />
				<input required="true" type="password" name="upass1" placeholder="确认密码" id="r_password" />
				<input required="true" type="number" name="phone" placeholder="电话" id="r_emial"/>
				<input required="true" type="text" name="email" id="r_emial" placeholder="邮箱" >
				<button typde="submit" id="create">创建账户</button>
				<p class="message">已经有了一个账户? <a href="<%=basePath%>user/login.jsp">立刻登录</a></p>
			</form>
		
	</div>
	</div>
  
    <%-- <center>
    <table align="center">

        <tbody><tr>
        
            <td><label>用户名：</label></td>
            <td><input name="uid" type="text" class="username" onblur="checkusername()" ></td>
            <td><span class="y_span" id="y_username">用户名由4-10位字符组成，不能包含数字</span></td>
        </tr>
        <tr>
            <td><label>密码：</label></td>
            <td><input  name="upass" class="upass" type="password" id="password" onblur="checkpassword()"></td>
            <td><span class="y_span" id="y_password">密码由4-10位字符组成</span></td>
        </tr>
        <tr>
            <td><label>确认密码：</label></td>
            <td><input type="password" id="q_password" onblur="yzpassword()"></td>
            <td><span class="y_span" id="y_q_password"></span></td>
        </tr>
        <tr>
            <td><label>手机号码：</label></td>
            <td><input name="phone" class="phone" type="text" id="phone" onblur="checkphone()"></td>
            <td><span class="y_span" id="y_phone"></span></td>
        </tr>
        <tr>

            <td></td>
            <td></td>
            <td><input type="button" value="注册" onclick="zhuce()"></td>
        </tr>
    </tbody>
  
    </table>
</center> --%>
<script type="text/javascript" src="<%=basePath%>/scripts/jquery-3.4.1.js"></script>
<script>
//页面加载使用
window.onload = function onld() {
    window.status = "正在注册...";
}

//验证用户名
var username;
var cn;

function checkusername() {

	 $.ajax({
           type:"post",
           url:"checkusername",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
           data:{//设置数据源
           	uid:$(".username").val()
           },
           dataType:"json",//设置需要返回的数据类型
           success:function(data){
               var d = eval("("+data+")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
               //得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
                
               $("#y_username").text(""+d.idtext+"");
               return true;
                
           },
           error:function(){
               alert("系统异常，请稍后重试！");
               return false;
           }//这里不要加","
       });
   
}
//验证密码
var password;

function checkpassword() {
    password = document.getElementById("password").value;
    cn = document.getElementById('y_password');
    var reg = /^.{4,10}$/; //密码长度可以为非换行符的任意字符，长度必须在4-10位以内;
    if (reg.test(password) == false) {
        y_password.innerHTML = "密码长度必须为4-10位以内";
        cn.className = 'r_span';
        return false;
    } else {
        y_password.innerHTML = "*";
        cn.className = 'g_span';
        return true;
    }

}
//确认密码验证
var yzpwd;

function yzpassword() {
    yzpwd = document.getElementById("q_password").value;
    cn = document.getElementById('y_q_password');
    if (yzpwd != "") {
        if (yzpwd == password) {
            y_q_password.innerHTML = "*";
            cn.className = 'g_span';
            return true;
        } else {
            y_q_password.innerHTML = "两次输入密码不一致，请重新输入";
            cn.className = 'r_span';
            return false;
        }
    } else {
        y_q_password.innerHTML = "必填";
        cn.className = 'r_span';
    }

}


//验证手机号码
var phone;

function checkphone() {
    phone = document.getElementById("phone").value;
    cn = document.getElementById('y_phone');
    var reg = /^1\d{10}$/;
    if (reg.test(phone) == false) {
        y_phone.innerHTML = "请输入正确的手机号";
        cn.className = 'r_span';
        return false;
    } else {
        y_phone.innerHTML = "*";
        cn.className = 'g_span';
        return true;
    }
}


//注册验证
function zhuce() {
    
    
        $.ajax({
           type:"post",
           url:"userRegist",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
           data:{//设置数据源
           	uid:$(".username").val(),
           	upass:$(".upass").val(),
           	phone:$(".phone").val()
           },
           dataType:"json",//设置需要返回的数据类型
           success:function(data){
               var d = eval("("+data+")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
               //得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
                alert("注册成功");
                window.location.href="index.jsp";
               return true;
           },
           error:function(){
               alert("系统异常，请稍后重试！");
               return false;
           }//这里不要加","
       });

   
}
</script>

  </body>
</html>
