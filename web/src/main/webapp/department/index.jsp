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
  List<Department> plist = (List<Department>) request.getAttribute("departmentList"); 
  List<Department> tree0 =  WebUtil.getDepartmentTree(plist); 
  Long orgId = (Long) request.getAttribute("organizationId");
  if(orgId==null){
    orgId=0L;
  } 
  
%>
<title>组织架构 - 部门维护</title>
</head>
<body>
<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 
	<span class="c-gray en">&gt;</span> 组织架构 
	<span class="c-gray en">&gt;</span> 部门维护
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">	
			<div class="cl pd-5 bg-1 bk-gray mt-20"> 
			<span class="l"><a href="javascript:;" onclick="department_detail('添加部门节点','<%=basePath%>/department/detail?id=0&orgid=<%=orgId%>','','310')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加部门节点</a></span> 
			</div>
			<table class="table table-border table-bordered table-bg">
				<thead>
					<tr>
					  <th scope="col" colspan="7">部门架构</th>
					</tr>
					<tr class="text-c">								
					<th width="200">部门名称</th>      
					<th width="300">描述</th>   
					<th width="60">组织ID</th>		            
                    <th width="60">ID</th>		
                    <th width="60">ParentID</th>
                    <th width="80">排序</th>
                    <th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
				 <%if(tree0 != null && tree0.size() > 0){ 
				  for(Department o :tree0){
				 %>
				 <tr class="text-c">
                  <td class="text-l">
                  <%if(o.getDepth()>1){ 
                  for(int i=1;i<o.getDepth();i++){%>                  
                     &nbsp;&nbsp;├
                  <%} }%>               
                  <%=o.getName()%>
                  </td>
                  <td><%=(Util.isNullOrEmpty(o.getDescription())?"--":o.getDescription())%></td>
                  <td><%=o.getOrganizationId()%></td>
                  <td><%=o.getDepartId()%></td>
                  <td><%=o.getParentId()%></td>
                  <td><%=o.getSort()%></td>
                  <td>
                   <a title="编辑" href="javascript:;" onclick="department_detail('编辑部门节点', '<%=basePath%>/department/detail?id=<%=o.getDepartId()%>&orgid=<%=o.getOrganizationId()%>', '', '310')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>                                
                  </td>
                  </tr>					   
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
/*部门-添加-编辑*/
function department_detail(title, url, w, h) {
    layer_show(title, url, w, h);
}
/*部门-删除*/
function department_del(obj, id) {
   layer.confirm('确认要删除吗？', function (index) {
            $.ajax({
                type: 'POST',
                url: '<%=basePath%>/department/delete?id=' + id,
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
