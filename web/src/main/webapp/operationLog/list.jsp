<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  style="background: white">
 <head>
  <%
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
    <title>标题管理</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <link href="<%=basePath%>/css/pagecss.css" rel="stylesheet" type="text/css" />  
    <script type="text/javascript" src="<%=basePath%>/js/page/operationLog.js" ></script>   
  </head>
<body>
	<!-- 列表 -->
    <table id="tt" data-options="border:false,toolbar:'#tb'">
    </table>
    
    <!-- 列表上面的按钮和搜索条件  -->
     <div id="tb" style="padding:5px;height:auto">
		<!--  
		<div style="margin-bottom:5px">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="javascript:newUser()" plain="true"></a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick="javascript:editUser()" plain="true"></a> 
		</div>
		
		<form action="" id="searchForm">
		<div>
			用户名: <input name="filters['usernameEq']" style="width:80px">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="javascript:doSearch()">查询</a>
		</div>
		</form>-->
	</div>
	
</body>
</html>
