<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 18-7-5
  Time: 上午11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="keywords" content="JAVA开发实践"/>
    <meta name="description" content="记录JAVA开发过程中遇到的问题，记录以及测试"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>TYB个人博客</title>
    <link rel="stylesheet" href="../../plugIn_web/blog/uikit/css/uikit.css"/>
    <script src="../../plugIn_web/blog/uikit/jquery-3.3.1.js"></script>
    <script src="../../plugIn_web/blog/uikit/js/uikit.js"></script>
</head>
<body>
    <nav class="uk-navbar">
        <ul class="uk-navbar-nav uk-hidden-small uk-navbar-center ">
            <li class="uk-active"><a href="">首页</a></li>
            <li><a href="">Item</a></li>
            <li class="uk-parent" data-uk-dropdown="" aria-haspopup="true" aria-expanded="false">
                <a href="">Parent</a>
                <div class="uk-dropdown uk-dropdown-navbar uk-dropdown-bottom" style="top: 40px; left: 0px;">
                    <ul class="uk-nav uk-nav-navbar">
                        <li><a href="#">Item</a></li>
                        <li><a href="#">Another item</a></li>
                        <li class="uk-nav-header">Header</li>
                        <li><a href="#">Item</a></li>
                        <li><a href="#">Another item</a></li>
                        <li class="uk-nav-divider"></li>
                        <li><a href="#">Separated item</a></li>
                    </ul>
                </div>

            </li>
        </ul>
        <div class="uk-navbar-flip">
            <ul class="uk-navbar-nav">
                <%--<li class="uk-parent x-display-if-signin" data-uk-dropdown="">
                    <a href="#0"><i class="uk-icon-user"></i><span class="x-hidden-tiny">&nbsp;</span><span class="x-user-name x-hidden-tiny"></span></a>
                    <div class="uk-dropdown uk-dropdown-navbar">
                        <ul class="uk-nav uk-nav-navbar">
                            <li><a target="_blank" href="/me/profile"><i class="uk-icon-cog"></i> 个人资料</a></li>
                            <li class="uk-nav-divider"></li>
                            <li><a href="/auth/signout"><i class="uk-icon-power-off"></i> 登出</a></li>
                        </ul>
                    </div>
                </li>--%>
                <li class="x-display-if-not-signin uk-hidden-small"><a href="javascript:window.location='/login/loginOut'"><i class="uk-icon-sign-in"></i> 登录</a></li>
                <li class="x-display-if-not-signin uk-visible-small"><a href="javascript:window.location='/login/loginOut'"><i class="uk-icon-sign-in"></i></a></li>
            </ul>
        </div>
        <a class="uk-navbar-toggle uk-visible-small" data-uk-offcanvas="{target:'#my-id'}"></a>
    </nav>
    <!-- 抽屉式边栏 -->
    <div id="my-id" class="uk-offcanvas">
        <div class="uk-offcanvas-bar">
            <ul class="uk-nav uk-nav-offcanvas uk-nav-parent-icon" data-uk-nav>
                <li><a href="">Item</a></li>
                <li class="uk-active"><a href="">Active</a></li>
                <li class="uk-parent">
                    <a href="#">Parent</a>
                    <ul class="uk-nav-sub">
                        <li><a href="">Sub item</a></li>
                        <li><a href="">Sub item</a>
                            <ul>
                                <li><a href="">Sub item</a></li>
                                <li><a href="">Sub item</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li class="uk-parent">
                    <a href="#">Parent</a>
                    <ul class="uk-nav-sub">
                        <li><a href="">Sub item</a></li>
                        <li><a href="">Sub item</a></li>
                    </ul>
                </li>
                <li><a href="">Item</a></li>
                <li class="uk-nav-header">Header</li>
                <li class="uk-parent"><a href=""><i class="uk-icon-star"></i> Parent</a></li>
                <li><a href=""><i class="uk-icon-twitter"></i> Item</a></li>
                <li class="uk-nav-divider"></li>
                <li><a href=""><i class="uk-icon-rss"></i> Item</a></li>
            </ul>
        </div>
    </div>
</body>
</html>
