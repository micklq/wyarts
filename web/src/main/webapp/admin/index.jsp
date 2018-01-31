<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  style="background: white">
 <head>
 <%
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<title>管理员列表 - 管理员列表</title>
</head>
<body>
	<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i>首页<span class="c-gray en">&gt;</span>
	管理员管理<span class="c-gray en">&gt;</span>管理员列表 
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a> 
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<div class="text-c"> 
				<input type="text" class="input-text" style="width:250px" placeholder="输入管理员名称" id="" name="">
				<button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
			</div>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l"><a href="javascript:;" onclick="admin_detail('添加管理员','${pageContext.request.contextPath}/admin/detail','800','500')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加管理员</a> </span>
			    <span class="r">共有数据：<strong>${requestScope.dataCount}</strong> 条</span>
			</div>
			<table class="table table-border table-bordered table-bg">
				<thead>
					<tr>
						<th scope="col" colspan="9">管理员列表</th>
					</tr>
					<tr class="text-c">						
						<th width="40">ID</th>
						<th width="150">登录名</th>
						<th width="90">手机</th>
						<th width="150">邮箱</th>
						<th>角色</th>
						<th width="130">加入时间</th>
						<th width="100">是否已启用</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${list}" var="p">                
                <tr class="text-c">                   
                    <td>${p.id}</td>
                    <td>${p.userName}</td>                               
                    <td>${p.mobile}</td>
                     <td>${p.email}</td>                   
                     <td>${p.roleName}</td>
                     <td>${p.createDateStr}</td>
                     <td class="td-status"><span class="label label-success radius">${p.passportStatusText}</span></td>
                     <td class="td-manage">                            
                       <a title="编辑" href="javascript:;" onclick="admin_detail('管理员编辑','${pageContext.request.contextPath}/admin/detail?id=${p.id}' , '800' , '500' )" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> 
                     </td>
                </tr>
                </c:forEach> 					
					
				</tbody>
			</table>
		</article>
	</div>
</section>
 <!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript" src="<%=basePath%>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>/lib/laypage/1.2/laypage.js"></script> 
 <script type="text/javascript">
        /*参数解释： 
        title=>标题
        url=>请求的url 
        w=>弹出层宽度（缺省调默认值）
        h=>弹出层高度（缺省调默认值）
       */
        /*管理员-增加-编辑*/
        function admin_detail(title, url, w, h) {
            layer_show(title, url, w, h);
        }  
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
