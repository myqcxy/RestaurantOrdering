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
			<a class="navbar-brand" href="#">用户管理员</a>
			<s:debug></s:debug>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li>您好！admin
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
				<ul class="nav nav-sidebar">
					<li><a href="">餐品管理<span class="sr-only">(current)</span></a></li>
					<li><a href="meal/addMeal.jsp">添加餐品<span class="sr-only">(current)</span></a></li>
					<li><a href="getAllMeals">修改餐品信息</a></li>
					<li><a href="getAllMeals">浏览餐品信息</a></li>
					<li><a href="table/addTable.jsp">添加餐桌<span class="sr-only">(current)</span></a></li>
					<li><a href="showAllTables">修改餐桌<span class="sr-only">(current)</span></a></li>
					<li><a href="showAllTables">显示餐桌<span class="sr-only">(current)</span></a></li>
					<li><a href="salesStatistics">统计销量<span class="sr-only">(current)</span></a></li>




				</ul>

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
		   	
		       <a class="btn" onclick="add(<s:property value="mid"/>)"><font color="red" size="10">+</font></a>
		      <input name="mid" class="mid<s:property value="mid"/>" id="%{mid}" type="hidden" value='<s:property value="mid"/>'>
		   		 <span class="mid<s:property value="mid"/>" id="number<s:property value="mid"/>"><s:property value="addToCacheNumber"/> </span>
		      <a class="btn" onclick="del(<s:property value="mid"/>)"><font color="red" size="10">-</font></a>
		   		
            </div></s:iterator>
            
          </div>

				<h2 class="sub-header">餐品</h2>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>餐饮名称</th>
								<th>价格</th>
								<th>售出</th>
								<th>Header</th>
								<th>Header</th>
							</tr>
						</thead>

						<tbody>

							<tr>
								<td>1,001</td>
								<td>Lorem</td>
								<td>ipsum</td>
								<td>dolor</td>
								<td>sit</td>
							</tr>




						</tbody>
					</table>
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
<script type="text/javascript">
	
</script>
</html>
