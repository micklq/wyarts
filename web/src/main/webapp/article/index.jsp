<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.springframework.data.domain.*"%>
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
  List<ArticlesCategory> clist = (List<ArticlesCategory>) request.getAttribute("categoryList"); 
  Page<Articles> list =  (Page<Articles>) request.getAttribute("plist");
  Long categoryId =  (Long) request.getAttribute("categoryId");
  String keyword =  (String) request.getAttribute("keyword");
  if(categoryId==null){
    categoryId=0L;
  }
%>
<title>资讯管理 - 资讯列表 </title>
</head>
<body>
	<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 
	<span class="c-gray en">&gt;</span> 资讯管理 
	<span class="c-gray en">&gt;</span> 资讯列表 
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20">
			
		<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l"><a class="btn btn-primary radius"   data-title="添加资讯" _href="<%=basePath%>/article/detail"  onclick="article_detail('添加资讯', '<%=basePath%>/article/detail')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加资讯</a></span> 
        <span class="l">&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <span class="l">
         <span class="select-box inline">
           <select id="categoryId" name="categoryId" class="select">
              <option value="0" <%=(categoryId == 0 ? "selected" : "")%>>-全部栏目-</option>
              <%if (clist != null && clist.size() > 0){ 
				  for(ArticlesCategory o :clist){
			  %>             
              <option value="<%=o.getCategoryId()%>" <%=(categoryId == o.getCategoryId() ? "selected" : "")%>><%=o.getName()%></option>  
              <%}} %>		 
          </select>
       </span>
       <input type="text" name="keyword" id="keyword" placeholder="资讯名称" value="<%=(Util.isNullOrEmpty(keyword)?"":keyword)%>" style="width:250px" class="input-text">
        <button name="btnSearch" id="btnSearch" class="btn btn-success" type="button"><i class="Hui-iconfont">&#xe665;</i> 搜资讯</button>
       </span>
		<span class="r">共有数据：<strong><%=list.getTotalElements()%></strong> 条</span>
	   </div>		
	   <table class="table table-border table-bordered table-bg table-hover table-responsive">
         <thead>
           <tr class="text-c">                        
             <th width="80">ID</th>
             <th>标题</th>
             <th width="80">分类</th>
             <th width="80">来源</th>
             <th width="120">更新时间</th>
             <th width="75">浏览次数</th>                        
             <th width="120">操作</th>
           </tr>
         </thead>
		<tbody>
		<%if (list != null && list.getNumberOfElements() > 0){ 
			for(Articles o :list.getContent()){
		%>
		<tr class="text-c">
           <td><%=o.getArticleId()%></td>
           <td class="text-l"><%=o.getTitle()%></td>
           <td><%=o.getCategoryName()%></td>
           <td><%=o.getMedia()%></td>
           <td><%=o.getModifyDateStr()%></td>
           <td><%=o.getBrowseTimes()%></td>
           <td class="f-14 td-manage">
           <a style="text-decoration:none" class="ml-5" onclick="article_detail('资讯编辑', '<%=basePath%>/article/detail?id=<%=o.getArticleId()%>')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> 
           <a style="text-decoration:none" class="ml-5" onclick="article_del(this, '<%=o.getArticleId()%>')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
        </tr> 					
		<% } }%>			
		</tbody>
		</table>
		</article>
	</div>
</section>
 <!--请在下方写此页面业务相关的脚本--> 
<script type="text/javascript" src="<%=basePath%>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>/lib/laypage/1.2/laypage.js"></script> 
 <script type="text/javascript">
        $(function () {
            $("#btnSearch").click(function () {                
                window.location.href = "<%=basePath%>/article/index?categoryId=" + $("#categoryId").val() + "&keyword=" + $("#keyword").val();
            })
        })
        /*资讯-添加编辑*/
        function article_detail(title, url, w, h) {          
            var index = layer.open({
            	type: 2,
            	title: title,
            	content: url
            });
            layer.full(index);
        }        
        /*资讯-删除*/
        function article_del(obj, id) {
            layer.confirm('确认要删除吗？', function (index) {
                $.ajax({
                    type: 'POST',
                    url: '<%=basePath%>/article/delete?id=' + id,
                    dataType: 'json',
                    success: function (data) {
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!', { icon: 1, time: 1000 });
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
