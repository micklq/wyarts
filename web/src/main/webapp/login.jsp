<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<title>登陆</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
 <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5shiv.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <![endif]-->
    <link href="<%=basePath%>/static/css/H-ui.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>/static/css/H-ui.login.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>/static/css/style.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>后台登陆</title>  
    <style type="text/css">
     label.error { right:300px; top:10px;}
    </style>
<meta name="keywords" content="网站后台">
<meta name="description" content="后台系统。">
</head>
<body>
<div class="header"></div>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form class="form form-horizontal" id="form-login" name="form-login" method="post">
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input id="username" name="username" type="text" placeholder="账户" class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L">
                </div>
            </div>       
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input name="btnLogin" type="submit" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                    <input name="btnReset" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
                </div>
            </div>
        </form>
    </div>
</div>
<div class="footer">Copyright XXX by xxx2018 v1.0</div>
 <script type="text/javascript" src="<%=basePath%>/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/static/js/H-ui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=basePath%>/lib/jquery.validation/1.14.0/validate-methods.js"></script>
    <script type="text/javascript" src="<%=basePath%>/lib/jquery.validation/1.14.0/messages_zh.js"></script> 
    <script type="text/javascript">
    $(function () {        

        $("#form-login").validate({
            rules: {
                username: {
                    required: true,
                    minlength: 4,
                    maxlength: 16
                },
                password: {
                    required: true,
                },                
            },
            onkeyup: false,
            focusCleanup: true,
            success: "valid",
            submitHandler: function (form) {
                $("#btnLogin").attr("disabled", true);
                $(form).ajaxSubmit({
                    type: 'post',
                    url: "login/loginAjax",
                    success: function (result) {
                     if (result.success) { 
					 location.href = "index/welcome";
				     } else {
				      $("#btnLogin").attr("disabled", false);                            
                      $.Huimodalalert(result.message, 3000);					 
				     }                      
                    },
                    error: function (XmlHttpRequest, textStatus, errorThrown) {
                        $("#btnLogin").attr("disabled", false);                       
                        $.Huimodalalert('网络超时,请检查网络连接！', 2000);
                    }
                });
            }
        });
    });
    </script> 
</body>
</html>
