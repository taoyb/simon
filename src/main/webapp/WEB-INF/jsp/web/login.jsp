<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="keywords" content="JAVA开发实践"/>
    <meta name="description" content="记录JAVA开发过程中遇到的问题，记录以及测试"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TYB后台管理系统</title>
    <link rel="shortcut icon" href="../../plugIn_web/tyb/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="../../plugIn_web/login/prompt/bootstrap.css" >
    <link rel="stylesheet" href="../../plugIn_web/login/prompt/message.css" >
    <link rel="stylesheet" type="text/css" href="../../plugIn_web/login/normalize.css" />
    <link rel="stylesheet" type="text/css" href="../../plugIn_web/login/demo.css" />
    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="../../plugIn_web/login/component.css"  />
    <!--[if IE]>
    <script src="../../plugIn_web/login/html5.js" ></script>
    <![endif]-->
</head>
<body onkeydown="if(event.keyCode==13){login()}">
<div class="container demo-1">
    <div class="content">
        <div id="large-header" class="large-header">
            <canvas id="demo-canvas"></canvas>
            <div class="logo_box">
                <h3>欢迎你</h3>
                <form action="/login/admin" name="f" id="login" method="post">
                    <div class="input_outer">
                        <span class="u_user"></span>
                        <input name="userName" id="userName" value="admin" class="text" style="color: #FFFFFF !important" autofocus type="text" placeholder="请输入账户">
                    </div>
                    <div class="input_outer">
                        <span class="us_uer"></span>
                        <input name="password" id="password" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="123456" type="password" placeholder="请输入密码">
                    </div>
                    <div class="mb2"><a class="act-but submit" href="javascript:;" onclick="login()" style="color: #FFFFFF">登录</a></div>
                </form><%--onclick="document.getElementById('login').submit();"--%>
            </div>
        </div>
    </div>
</div><!-- /container -->
<script src="../../plugIn_web/login/TweenLite.min.js"></script>
<script src="../../plugIn_web/login/EasePack.min.js" ></script>
<script src="../../plugIn_web/login/rAF.js"></script>
<script src="../../plugIn_web/login/demo-1.js"></script>
<script src="../../plugIn_web/login/prompt/jquery.min.js"></script>
<script src="../../plugIn_web/login/prompt/message.js"></script>
<script>
    function validate(){
        if ($("#userName").val() == ""){
            $.message({
                message:'请输入用户名',
                type:'info'
            });return false;
        }else if ($("#password").val() == ""){
            $.message({
                message:'请输入密码',
                type:'info'
            });return false;
        }else{
            return true;
        }
    }
    function login() {
        if(!validate()){
            return;
        }
        $.ajax({
            cache: false,
            async: false,
            type: "POST",
            dataType: "json",
            url: "/login/login" ,
            data: $('#login').serialize(),
            success: function (data) {
                if(data.statusCode == '200'){
                    $.message('正在登录');
                    location.href="/login/admin";
                }else{
                    $.message({
                        message:data.message,
                        type:'error'
                    });
                }
            },
            error : function() {
                $.message({
                    message:'登录异常',
                    type:'warning'
                });
            }
        });
    }

</script>
</body>
</html>