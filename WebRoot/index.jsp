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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">


    <link href="./bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <link href="./bootstrap/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <link href="./bootstrap/dashboard.css" rel="stylesheet">

    <script src="./bootstrap/assets/js/ie-emulation-modes-warning.js"></script>
	<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		.navbar {
  		min-height: 60px;
}
	</style>
  </head>
 
  <body>
  <nav class="navbar navbar-inverse navbar-fixed-top navbar-right">
      <div class="container-fluid navbar-fixed-top">
        <div class="navbar-header menu">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">餐厅点餐</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse navbar-right">
          <ul class="menu" >
          <li> <form action="userSearch" method="post" class="navbar-form">
            <input name="searchContent" type="text" class="form-control" placeholder="搜索餐品...">
            <input type="submit" class="btn btn-defalut" value="搜索">
          </form></li>
          <% String uname=(String)session.getAttribute("uid");
             	if(uname==null){
             	uname="请登录！";%>
             	 <li><a href="user/login.jsp"><%= uname %></a></li>
             	  <li><a href="user/regist.jsp">注册</a></li>
             	<% 
             	
             	}else {
             	uname="你好！" + uname;%>
             	
		        <li width=100><a ><%= uname %></a>
					<ul class="submenu">
					 <li><a href="userEdit">个人信息</a>
						 <li><a href="settlement">购物车</a></li>
						<li><a href="myOrder">我的订单</a></li>
						<li><a href="logout">注销</a></li>
					</ul>
				</li>
             	<% 
             	}
			  %>
            <li><a  href="#">帮助</a></li>
            <li><a href="admin/AdminLogin.jsp">管理员</a></li>
            <li><a href="waiter/waiterLogin.jsp">服务员</a></li>
            <li><a href="cashier/cashierLogin.jsp">收银员</a></li>
            
          </ul>
         
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar" style="font-size:25px;background: black;">
          <ul class="nav nav-sidebar" >
          	<li class="active"><a href="specialOffer">今日特惠</a></li>
          	<li ><a href="specialOffer">按销量降序</a></li>
          	<li ><a href="specialOffer">按价格降序</a></li>
          	
            <li >
		            <s:url var="getByCategory1Url" action="getByCategory">
		            <s:param name="category" value="1"/>
			      	</s:url>
            <a href="${ getByCategory1Url }">主食 <span class="sr-only">(current)</span></a></li>
            <li>
		            <s:url var="getByCategory2Url" action="getByCategory">
		            <s:param name="category" value="2"/>
			      	</s:url>
            <a href="${ getByCategory2Url }">晚餐<span class="sr-only">(current)</span></a></li>
            		<s:url var="getByCategory3Url" action="getByCategory">
		            <s:param name="category" value="3"/>
			      	</s:url>
            <li><a href="${ getByCategory3Url }">饮料</a></li>
            		
            		<s:url var="getByCategory4Url" action="getByCategory">
		            <s:param name="category" value="4"/>
			      	</s:url>
            
            
            <li><a href="${ getByCategory4Url }">小吃</a></li>
          </ul>
       
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="padding-left: 0px;">
          <h1 class="page-header" style="background: red;line-height: 60px">今日热卖</h1>
			
			
          <div class="row placeholders" >
          <s:if test="list==null">
          	<script language="javascript" type="text/javascript">
           window.location.href="initRecommend"; 
    </script>
          </s:if>
           <s:iterator value="list">
            <div class="col-xs-6 col-sm-3 placeholder" style="background: #C2D0DF;margin-right:10px;">
              	<b style="color:red">优惠：<s:property value="discount"/></b><br>
              <s:property value="mname"/>
		   		&nbsp;&nbsp;&nbsp;￥:<s:property value="price"/><br>
		   		类别：<s:property value="category"/><br>
		   		销量：<s:property value="sales"/><br>
		   		<img alt="图片走丢了" src="${ photo }" width="200px" height="200px" ><br>
		   	
		       <a class="btn" onclick="add(<s:property value="mid"/>)"><font color="red" size="10">+</font></a>
		      <input name="mid" class="mid<s:property value="mid"/>" id="%{mid}" type="hidden" value='<s:property value="mid"/>'>
		   		 <span class="mid<s:property value="mid"/>" id="number<s:property value="mid"/>"><s:property value="addToCacheNumber"/> </span>
		   	
		   		 <a class="btn" onclick="del(<s:property value="mid"/>)"><font color="red" size="10">-</font></a>
		   		
		      
		   		
            </div></s:iterator>
            
          </div>
 
          
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script>window.jQuery || document.write('<script src="./bootstrap/assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="./bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="./bootstrap/assets/js/vendor/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./bootstrap/assets/js/ie10-viewport-bug-workaround.js"></script>
  
 
  </body>
  <script type="text/javascript" src="<%=basePath%>/scripts/jquery-3.4.1.js"></script>
  <script type="text/javascript">
                function add(aa){
                var a='<%=session.getAttribute("uid")%>';
                if(a == "null"){
                	window.location.href="<%=basePath%>/user/login.jsp";
                }else{
                $.ajax({
                    type:"post",
                    url:"addToCache",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
                    data:{//设置数据源
                    	mid:$(".mid"+aa).val()
                    },
                    dataType:"json",//设置需要返回的数据类型
                    success:function(data){
                        var d = eval("("+data+")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
                        //得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
                         
                        $("#number"+aa).text(""+d.number+"");
                         
                    },
                    error:function(){
                        alert("系统异常，请稍后重试！");
                    }//这里不要加","
                });
                }
                
            };
            function del(aa){
            
            var a='<%=session.getAttribute("uid")%>';
                if(a == "null"){
                	window.location.href="<%=basePath%>/user/login.jsp";
                }else{
                $.ajax({
                    type:"post",
                    url:"delFromCache",//需要用来处理ajax请求的action,excuteAjax为处理的方法名，JsonAction为action名
                    data:{//设置数据源
                    	mid:$(".mid"+aa).val()
                    },
                    dataType:"json",//设置需要返回的数据类型
                    success:function(data){
                        var d = eval("("+data+")");//将数据转换成json类型，可以把data用alert()输出出来看看到底是什么样的结构
                        //得到的d是一个形如{"key":"value","key1":"value1"}的数据类型，然后取值出来
                         
                        $("#number"+aa).text(""+d.number+"");
                         if($("#number"+aa).val()==0){
            				$("#number"+aa).style.visibility="hidden";
            }
                    },
                    error:function(){
                        alert("系统异常，请稍后重试！");
                    }//这里不要加","
                });
                }
                
            };

  </script>
</html>
