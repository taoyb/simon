<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 18-7-5
  Time: 上午11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
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
    <link rel="stylesheet" href="../../plugIn_web/blog/uikit/css/docs.css"/>
    <script src="../../plugIn_web/blog/uikit/jquery-3.3.1.js"></script>
    <script src="../../plugIn_web/blog/uikit/js/uikit.js"></script>
</head>
<body>
    <nav class="uk-navbar">
        <ul class="uk-navbar-nav uk-hidden-small uk-navbar-center">
            <c:forEach items="${list}" var="m" varStatus="l">
                <li class="uk-active"><a href="">${m.menuName}${l.index}</a></li>
            </c:forEach>
            <%--<li class="uk-parent" data-uk-dropdown="" aria-haspopup="true" aria-expanded="false">
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
            </li>--%>
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
        <div class="uk-navbar-brand uk-navbar-center uk-visible-small">首页</div>
    </nav>

    <div class="tm-middle">
        <div class="uk-container tm-container">
            <div class="uk-grid" data-uk-grid-margin>
                <div class="tm-sidebar uk-width-medium-1-4 uk-hidden-small">
                    <ul class="tm-nav uk-nav" data-uk-nav>
                        <li class="uk-nav-header">初学者</li>
                        <li class="uk-active"><a href="documentation_get-started.html">开始使用</a></li>
                        <li><a href="documentation_how-to-customize.html">如何定制</a></li>
                        <li><a href="documentation_layouts.html">布局示例</a></li>
                        <li class="uk-nav-header">开发者</li>
                        <li><a href="documentation_project-structure.html">项目结构</a></li>
                        <li><a href="documentation_less-sass.html">Less &amp; Sass 文件</a></li>
                        <li><a href="documentation_create-a-theme.html">创建主题</a></li>
                        <li><a href="documentation_create-a-style.html">创建样式</a></li>
                        <li><a href="documentation_customizer-json.html">Customizer.json</a></li>
                        <li><a href="documentation_javascript.html">JavaScript</a></li>
                        <li><a href="documentation_custom-prefix.html">自定义前缀</a></li>
                    </ul>
                </div>
                <div class="tm-main uk-width-medium-3-4">
                    <article class="uk-article">
                        <h1>开始使用</h1>
                        <p class="uk-article-lead">熟悉 UIkit 的基本组织结构。</p>
                        <p><span class="uk-badge uk-badge-danger">注意</span> 文档中的示例代码不具备可操作性，请随时右键查看源代码，以参考实例真实代码。</p>
                        <div class="uk-alert uk-alert-danger">
                            UIkit 兼容 IE9 以上现代浏览器。在 IE8 及其以下版本中，所有JavaScript 都会失效。
                        </div>
                        <p>首先，你需要下载 UIkit。在 <a href="https://github.com/uikit/uikit" target="_blank" rel="nofollow">GitHub</a> 上，你可以找到整个项目的所有源文件。</p>

                        <p><a class="uk-button uk-button-large uk-button-primary" href="../download/uikit-2.25.0.zip">下载 UIkit</a> <a class="uk-button uk-button-large" href="../download/uikit-doc-2.25.0.zip">下载离线文档</a></p>

                        <hr class="uk-article-divider">

                        <h2 id="file-structure"><a href="#file-structure" class="uk-link-reset">文件结构</a></h2>

                        <p>在你下载的 ZIP 压缩包中，你可以找到准备在你的项目中使用的所有的CSS、JavaScript和字体文件。UIKit 的核心框架<code>uikit.css</code> 或 <code>uikit.min.css</code>几乎没有任何样式，这样做是为了保持它的苗条。因此我们提供两个额外的风格样式，一个渐变样式和一个扁平化样式。每个风格样式提供单独的CSS文件以及压缩后的版本。</p>

                        <hr class="uk-article-divider">
                        <ul class="uk-list">
                            <li><a href="https://sublime.wbond.net/packages/UIkit%20autocomplete">Sublime 上的自动完成</a> - 通过 Sublime 的 Package Control 来安装。</li>
                            <li><a href="https://plugins.jetbrains.com/plugin/7791?pr=">PHPstorm 上的自动完成</a> - 通过 PHPsorm 的插件设置进行安装。</li>
                            <li><a href="https://atom.io/packages/atom-uikit">Atom 上的自动完成</a> - 在 Atom 的 package manager 里寻找它。</li>
                        </ul>
                    </article>
                </div>
            </div>
        </div>
    </div>





    <!-- 抽屉式边栏 -->
    <div id="my-id" class="uk-offcanvas">
        <div class="uk-offcanvas-bar">
            <ul class="uk-nav uk-nav-offcanvas uk-nav-parent-icon" data-uk-nav="{multiple:true}">
                <li class="uk-active"><a href="documentation_get-started.html">开始使用</a></li>
                <li><a href="core.html">核心组件</a></li>
                <li><a href="components.html">附加组件</a></li>
                <li><a href="customizer.html">定制工具</a></li>
                <li><a href="../showcase/index.html">案例展示</a></li>
                <li><a href="tutorials.html">视频教程</a></li>

                <li class="uk-parent"><a href="#">UIkit 中文文档</a>
                    <ul class="uk-nav-sub">
                        <li><a href="documentation_get-started.html">开始使用</a></li>
                        <li><a href="documentation_how-to-customize.html">如何定制</a></li>
                        <li><a href="documentation_layouts.html">布局示例</a></li>
                        <li><a href="core.html">核心组件</a></li>
                        <li><a href="components.html">附加组件</a></li>
                        <li><a href="documentation_project-structure.html">项目结构</a></li>
                        <li><a href="documentation_less-sass.html">Less &amp; Sass 文件</a></li>
                        <li><a href="documentation_create-a-theme.html">创建主题</a></li>
                        <li><a href="documentation_create-a-style.html">创建样式</a></li>
                        <li><a href="documentation_customizer-json.html">Customizer.json</a></li>
                        <li><a href="documentation_javascript.html">JavaScript</a></li>
                        <li><a href="documentation_custom-prefix.html">自定义前缀</a></li>
                    </ul>
                </li>
                <li class="uk-nav-header">Core</li>
                <li class="uk-parent"><a href="#"><i class="uk-icon-wrench"></i> 默认</a>
                    <ul class="uk-nav-sub">
                        <li><a href="base.html">基础</a></li>
                        <li><a href="print.html">打印</a></li>
                    </ul>
                </li>
                <li class="uk-nav-header">附加组件</li>
                <li class="uk-nav-divider"></li>
            </ul>
        </div>
    </div>
</body>
</html>
