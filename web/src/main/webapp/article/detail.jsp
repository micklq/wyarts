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
<title>新增文章 - 资讯管理</title>
<%  
  List<ArticlesCategory>  categoryList = (List<ArticlesCategory>)request.getAttribute("categoryList"); 
  Articles p = (Articles) request.getAttribute("article");
  if(p==null){ p = new Articles();} 
%>
</head>
<body>
<article class="cl pd-20">
<form method="post" class="form form-horizontal" id="form-article">
   <input type="hidden" id="id" name="id" value="<%=p.getArticleId()%>">
    <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>文章标题：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" id="title" name="title" value="<%=(p.getTitle()!=null?p.getTitle():"")%>" placeholder="" >
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>分类栏目：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <span class="select-box">
                        <select id="categoryId" name="categoryId" class="select">  
                         <option value="0"  <%=(p.getCategoryId() == 0 ? "selected" : "")%>>-无所属栏目-</option>                          
                         <%if (categoryList != null && categoryList.size() > 0){ 
                         	for(ArticlesCategory co :categoryList){
                         %>
                         <option value="<%=co.getCategoryId()%>" <%=(p.getCategoryId()== co.getCategoryId()? "selected" : "")%>><%=co.getName()%></option> 
                         <% } }%>		
                        </select>
                    </span>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">文章摘要：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <textarea id="description" name="description" class="textarea" placeholder="说点什么...最少输入10个字符" datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" onkeyup="$.Huitextarealength(this,200)"><%=(p.getDescription()!=null?p.getDescription():"")%></textarea>
                    <p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">文章作者：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" id="editor" name="editor" value="<%=(p.getEditor()!=null?p.getEditor():"")%>" placeholder="" >
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">文章来源：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <input type="text" class="input-text" id="media" name="media" value="<%=(p.getMedia()!=null?p.getMedia():"")%>" placeholder="" >
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-4 col-sm-2">文章内容：</label>
                <div class="formControls col-xs-8 col-sm-9">
                    <script id="editor1" type="text/plain" style="width:100%;height:400px;"></script>
                   <div style=" display:none;">
                       <textarea id="contents" name="contents"><%=(p.getContents()!=null?p.getContents():"")%></textarea>
                   </div>
                </div>
            </div>
            <div class="row cl">
                <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                    <button class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
                    <button id="cancle" name="cancle" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
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
<script type="text/javascript" src="<%=basePath%>/lib/My97DatePicker/4.8/WdatePicker.js"></script>   
<script type="text/javascript" src="<%=basePath%>/lib/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="<%=basePath%>/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" src="<%=basePath%>/ueditor/lang/zh-cn/zh-cn.js"></script>  
<script type="text/javascript">
    $(function () {
        var ue = UE.getEditor('editor1');
        ue.ready(function () { //设置编辑器的内容           
            ue.setContent($("#contents").val());           
        });

        $("#cancle").click(function () {
            var index = parent.layer.getFrameIndex(window.name);           
            parent.layer.close(index);
        });
        //表单验证
        $("#form-article").validate({
            rules: {
                title: {
                    required: true,
                },               
                description: {
                    required: true,
                },
                //contents:{required: true,},
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                var htmls = ue.getContent();                
                $("#contents").val(htmls);
                $(form).ajaxSubmit({
                    type: 'post',
                    url: "<%=basePath%>/article/saveAction",
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
