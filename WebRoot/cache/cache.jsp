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


    <link href="./bootstrap/dashboard.css" rel="stylesheet">


  </head>
  
  <body>


    <div class="container-fluid">
      <div class="row">
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">购物车</h1>
          <h1 class="page-header"><a href="index.jsp">返回主页</a></h1>
          
			<h1 class="page-header">
			<s:if test="list!=null">
			<a href="placeAnOrder">下单</a></s:if>
			</h1>
          <div class="row placeholders">
          <s:if test="list!=null">
           <s:iterator value="list" >
            <div class="col-xs-6 col-sm-3 placeholder">
             <b style="color:red">优惠：<s:property value="discount"/></b><br>
              <s:property value="mname"/>
		   		&nbsp;&nbsp;&nbsp;￥:<s:property value="price"/><br>
		   		类别：<s:property value="category"/><br>
		   		<img alt="图片走丢了" src="${ photo }" width="200px" height="200px" ><br>
		      <a class="btn" onclick="add(<s:property value="mid"/>)"><font color="red" size="10">+</font></a>
		      <input name="mid" class="mid<s:property value="mid"/>" id="%{mid}" type="hidden" value='<s:property value="mid"/>'>
		   		 <span class="mid<s:property value="mid"/>" id="number<s:property value="mid"/>"><s:property value="sales"/> </span>
		      <a class="btn" onclick="del(<s:property value="mid"/>)"><font color="red" size="10">-</font></a>
            </div></s:iterator>
            </s:if>
            <s:else>
            	<h1 class="page-header">
				购物车中暂无餐品
			</h1>
            </s:else>
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
  <s:debug></s:debug>
 
  </body>
  <script type="text/javascript" src="<%=basePath%>/scripts/jquery-3.4.1.js"></script>
  <script type="text/javascript">
          function add(aa){
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
            if(d.number==0)
            	window.location.href="settlement";
        },
        error:function(){
            alert("系统异常，请稍后重试！");
        }
    });
   }
};

  </script>
</html>
