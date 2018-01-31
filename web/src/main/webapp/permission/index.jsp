<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.arts.org.model.entity.*"%>
<%@page import="com.arts.org.model.entity.view.*"%>
<%@page import="com.arts.org.model.enums.*"%>
<%@page import="com.arts.org.webcomn.util.*"%>
<%@page import="com.arts.org.base.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  style="background: white">
 <head>
 <%
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
  List<Permission> plist = (List<Permission>) request.getAttribute("permissionList"); 
  List<Permission> roots =  WebUtil.filterPermissionList(plist,0); 
    
%>
<title>管理员列表 - 权限管理</title>
</head>
<body>
<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 
	<span class="c-gray en">&gt;</span>  管理员管理 
	<span class="c-gray en">&gt;</span> 权限管理 
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
	<i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">	
			<div class="cl pd-5 bg-1 bk-gray mt-20"> 
			<span class="l"><a href="javascript:;" onclick="permission_detail('添加权限节点','<%=basePath%>/permission/detail?id=0','','310')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加权限节点</a></span> 
			</div>
			<table class="table table-border table-bordered table-bg">
				<thead>
					<tr>
					  <th scope="col" colspan="7">权限节点</th>
					</tr>
					<tr class="text-c">									
					<th width="200">权限名称</th>
                    <th>访问地址</th> 
                    <th width="60">ID</th>		
                    <th width="60">ParentID</th>
                    <th width="80">排序</th>
                    <th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
				 <%if (roots != null && roots.size() > 0){ 
				  for(Permission o :roots){
				 %>
				 <tr class="text-c">
                  <td class="text-l"><%=o.getName()%></td>
                  <td><%=(Util.isNullOrEmpty(o.getUrl())?"--":o.getUrl())%></td>
                  <td><%=o.getPermissionId()%></td>
                  <td><%=o.getParentId()%></td>
                  <td><%=o.getSort()%></td>
                  <td>
                   <a title="编辑" href="javascript:;" onclick="permission_detail('编辑权限节点', '<%=basePath%>/permission/detail?id=<%=o.getPermissionId()%>', '', '310')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>                                
                  </td>
                  </tr>
				  <% 
				     List<Permission>  slist = WebUtil.filterPermissionList(plist,o.getPermissionId()); 				                 
                     if(slist.size()>0){
                        for(Permission so : slist) {%>
				          <tr class="text-c">
                                <td class="text-l">&nbsp;&nbsp;&nbsp;├ <%=so.getName()%></td>
                                <td><%=(Util.isNullOrEmpty(so.getUrl())?"--":so.getUrl())%></td>
                                <td><%=so.getPermissionId()%></td>
                                <td><%=so.getParentId()%></td>
                                <td><%=so.getSort()%></td>
                                <td>
                                    <a title="编辑" href="javascript:;" onclick="permission_detail('角色编辑', '<%=basePath%>/permission/detail?id=<%=so.getPermissionId()%>', '', '310')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
                                    <a title="删除" href="javascript:;" onclick="permission_del(this, '<%=so.getPermissionId()%>" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
                                </td>
                            </tr>				          
				      <% }}%> 
                 <% } }%>
				
				</tbody>
			</table>
		</article>
	</div>
</section>

 <!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript" src="<%=basePath%>/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=basePath%>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript">
/*管理员-权限-添加-编辑*/
function permission_detail(title, url, w, h) {
    layer_show(title, url, w, h);
}
/*管理员-权限-删除*/
function permission_del(obj, id) {
   layer.confirm('确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '<%=basePath%>/permission/remove?id=' + id,
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
                    console.log(data.msg);
                },
            });
        });
 }
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>
