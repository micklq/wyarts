<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  style="background: white">
 <head>
 <%
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<title>组织管理 - 组织列表 </title>
</head>
<body><section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 组织管理
	<span class="c-gray en">&gt;</span> 组织列表
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<div class="cl pd-5 bg-1 bk-gray"> <span class="l"> 
			<a class="btn btn-primary radius" href="javascript:;" onclick="org_detail('添加组织', '${pageContext.request.contextPath}/organization/detail', '800')"><i class="Hui-iconfont">&#xe600;</i> 添加组织</a> </span> 
			</div>
			<div class="mt-10">
			<table class="table table-border table-bordered table-hover table-bg">
				<thead>
					<tr>
						<th scope="col" colspan="6">组织列表</th>
					</tr>
					<tr class="text-c">					
					<th width="40">ID</th>
                    <th width="200">名称</th>                   
                    <th width="300">描述</th>
                    <th width="300">地址</th>
                    <th width="300">状态</th>
                    <th width="70">操作</th>						
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${list}" var="p">                   
                    <tr class="text-c">
                        <td>${p.id}</td>
                        <td>${p.name}</td>
                        <td>${p.description}</td>
                         <td>${p.address}</td>
                        <td class="td-status"><span class="label label-success radius">${p.statusText}</span></td>
                        <td class="f-14">
                         <a title="组织部门" href="${pageContext.request.contextPath}/department/index?orgid=${p.id}" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe62b;</i></a>
                        <a title="编辑" href="javascript:;" onclick="org_detail('组织编辑', '${pageContext.request.contextPath}/organization/detail?id=${p.id}', '800')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>                        
                        </td>
                    </tr>
                 </c:forEach> 			
					
				</tbody>
			</table>
			</div>
		</article>
	</div>
</section>
 <!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript" src="<%=basePath%>/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=basePath%>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript">
 /*组织-添加-编辑*/
        function org_detail(title, url, w, h) {
            layer_show(title, url, w, h);
        } 
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
