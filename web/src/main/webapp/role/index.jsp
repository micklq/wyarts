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
<body><section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 
	<span class="c-gray en">&gt;</span> 角色管理 
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			<div class="cl pd-5 bg-1 bk-gray"> <span class="l"> 
			<a class="btn btn-primary radius" href="javascript:;" onclick="role_detail('添加角色', '${pageContext.request.contextPath}/role/detail', '800')"><i class="Hui-iconfont">&#xe600;</i> 添加角色</a> </span> 
			</div>
			<div class="mt-10">
			<table class="table table-border table-bordered table-hover table-bg">
				<thead>
					<tr>
						<th scope="col" colspan="6">角色管理</th>
					</tr>
					<tr class="text-c">					
					<th width="40">ID</th>
                    <th width="200">角色名</th>                   
                    <th width="300">描述</th>
                    <th width="70">操作</th>						
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${list}" var="p">                   
                    <tr class="text-c">
                        <td>${p.id}</td>
                        <td>${p.name}</td>
                        <td>${p.description}</td>
                        <td class="f-14">
                        <a title="编辑" href="javascript:;" onclick="role_detail('角色编辑', '${pageContext.request.contextPath}/role/detail?id=${p.id}', '800')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> 
                        <a title="删除" href="javascript:;" onclick="role_del(this, '${p.id}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
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
 /*管理员-角色-添加-编辑*/
        function role_detail(title, url, w, h) {
            layer_show(title, url, w, h);
        }       
        /*管理员-角色-删除*/
        function role_del(obj, id) {
            layer.confirm('角色删除须谨慎，确认要删除吗？', function (index) {
                $.ajax({
                    type: 'POST',
                    url: '<%=basePath%>/role/remove?id=' + id,
                    dataType: 'json',
                    success: function (data) {
                        if (data.success) {
                            $(obj).parents("tr").remove();
                            layer.msg(data.message, { icon: 1, time: 1000 });
                        }
                        else {
                            layer.msg(data.message, { icon: 1, time: 1000 });
                        }
                        
                    },
                    error: function (data) {
                        console.log(data.message);
                    },
                });
            });
}
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
