<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.arts.org.model.entity.*"%>
<%@page import="com.arts.org.model.entity.view.*"%>
<%@page import="com.arts.org.model.enums.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background: white">
<head>
<%
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); 
  ArticlesCategory p = (ArticlesCategory) request.getAttribute("category");
  if(p==null){ p = new ArticlesCategory();} 
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
<title>权限信息维护</title>
</head>
<body>
<article class="cl pd-20">
<form method="post" class="form form-horizontal" id="form-submit">
   <input type="hidden" id="id" name="id" value="<%=p.getCategoryId()%>">
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">
                    <span class="c-red">*</span>名称：
                </label>
                <div class="formControls col-xs-6 col-sm-6">
                    <input type="text" class="input-text" id="name" name="name" value="<%=(p.getName()!=null?p.getName():"")%>" placeholder="">
                </div>
            </div>            
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">
                    <span class="c-red">*</span>描述：
                </label>
                <div class="formControls col-xs-6 col-sm-6">
                    <input type="text" class="input-text" id="description" name="description" value="<%=(p.getDescription()!=null?p.getDescription():"")%>" placeholder="">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">
                    <span class="c-red">*</span>排序：
                </label>
                <div class="formControls col-xs-6 col-sm-6">
                    <input type="text" class="input-text" id="sort" name="sort" value="<%=(p.getSort()>0?p.getSort():"0")%>" placeholder="">
                </div>
            </div>
            <div class="row cl">
                <div class="col-9 col-offset-2">
                    <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
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
            $("#form-submit").validate({
                rules: {
                    name: {
                        required: true,
                        minlength: 2,
                        maxlength: 16
                    },                    
                },
                onkeyup: false,
                focusCleanup: true,
                success: "valid",
                submitHandler: function (form) {
                    $(form).ajaxSubmit({
                        type: 'post',
                        url: "<%=basePath%>/articleCategory/saveAction",
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
