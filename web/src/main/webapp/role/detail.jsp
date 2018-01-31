<%@page import="org.bouncycastle.asn1.cmp.OOBCertHash"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.arts.org.model.entity.*"%>
<%@page import="com.arts.org.model.entity.view.*"%>
<%@page import="com.arts.org.model.enums.*"%>
<%@page import="com.arts.org.webcomn.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background: white">
<head>
<%
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
  Role p = (Role) request.getAttribute("role");
  if(p==null){ p = new Role();} 
  List<Permission> plist = (List<Permission>) request.getAttribute("plist"); 
  List<RolePermission> rlist = (List<RolePermission>)request.getAttribute("rlist"); 
  List<Permission> roots = WebUtil.filterPermissionList(plist,0); 
%>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="favicon.ico" >
<link rel="Shortcut Icon" href="favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script><![endif]-->
<title>角色信息维护</title>
</head>
<body>
<article class="cl pd-20">
 <form method="post" class="form form-horizontal" id="form-admin-role-action">
            <input type="hidden" id="id" name="id" value="<%=p.getRoleId()%>">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名称：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" value="<%=(p.getName()!=null?p.getName():"")%>" placeholder="" id="name" name="name">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3">描述：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" value="<%=(p.getDescription()!=null?p.getDescription():"")%>" placeholder="" id="description" name="description">
                </div>
            </div>  
             <div class="row cl">
                <label class="form-label col-xs-4 col-sm-3">网站角色：</label>
                <div class="formControls col-xs-8 col-sm-9">
                 <%if (roots != null && roots.size() > 0){ 
				    for(Permission o :roots){
				 %>
				  <dl class="permission-list">
                   <dt><label><%=o.getName()%></label></dt>
                    <dd>
                    <% 
				       List<Permission> slist =WebUtil.filterPermissionList(plist,o.getPermissionId()); 				                 
                       if(slist.size()>0){
                         for(Permission so : slist) {  
                         boolean ck0 = WebUtil.checkPermissionValue(rlist,so.getPermissionId(),0);
                     %>
                           <dl class="cl permission-list2">
                           <dt>
                            <label class="">
                            <input type="checkbox" <%=(ck0 ? "checked" : "")%> value="" name="permissionId" id="permissionId-<%=so.getPermissionId()%>">
                            <%=so.getName()%>
                            </label>
                            </dt>
                            <dd>
                              <% for( PermissionAction type : PermissionAction.values() ){ 
                               boolean ck =  WebUtil.checkPermissionValue(rlist,so.getPermissionId(),type.getValue());;%>                                                 
                               <label><input type="checkbox" @(ck ? "checked" : "") value="<%=o.getPermissionId()%>-<%=so.getPermissionId()%>-<%=type.getValue() %>" name="permissionValue" id="permissionValue-<%=so.getPermissionId()%>"><%= type.getName() %></label>
                             <%}%>                                                                                           
                            </dd>
                           </dl>
                        <% }%>                                   
                    </dd></dl>
				  <% } }}%>                   
                </div>
            </div>         
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                    <button type="submit" class="btn btn-success radius" id="admin-role-save" name="admin-role-save"><i class="icon-ok"></i> 确定</button>
                </div>
            </div>
 </form>
</article>
<script type="text/javascript" src="<%=basePath%>/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>/lib/layer/2.4/layer.js"></script> 
<script type="text/javascript" src="<%=basePath%>/static/js/H-ui.js"></script> 
<script type="text/javascript" src="<%=basePath%>/static/js/H-ui.admin.page.js"></script> 
<script type="text/javascript" src="<%=basePath%>/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="<%=basePath%>/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="<%=basePath%>/lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript">
    $(function () {
        $(".permission-list dt input:checkbox").click(function () {
            $(this).closest("dl").find("dd input:checkbox").prop("checked", $(this).prop("checked"));
        });
        $(".permission-list2 dd input:checkbox").click(function () {
            var l = $(this).parent().parent().find("input:checked").length;
            var l2 = $(this).parents(".permission-list").find(".permission-list2 dd").find("input:checked").length;
            if ($(this).prop("checked")) {
                $(this).closest("dl").find("dt input:checkbox").prop("checked", true);
                $(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked", true);
            }
            else {
                if (l == 0) {
                    $(this).closest("dl").find("dt input:checkbox").prop("checked", false);
                }
                if (l2 == 0) {
                    $(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked", false);
                }
            }
        });
        $("#form-admin-role-action").validate({
            rules: {
                Name: {
                    required: true,
                },
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                $(form).ajaxSubmit({
                    type: 'post',
                    url: "<%=basePath%>/role/updateAction",
                    success: function (data) {
                        if (data.success) {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.location.reload();
                            parent.layer.close(index);
                        }
                        else {
                            $.Huimodalalert(data.message, 3000);
                        }
                    },
                    error: function (XmlHttpRequest, textStatus, errorThrown) {
                        $.Huimodalalert('网络超时,请检查网络连接！', 2000);
                    }
                });                
            }
        });
    });
 </script>   
    
</body>
</html>
