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
    <link rel="stylesheet" href="../../plugIn_web/blog/uikit/all.css"/>
    <script src="../../plugIn_web/blog/uikit/jquery-3.3.1.js"></script>
    <script src="../../plugIn_web/blog/js/b_record.js"></script>
    <script src="../../plugIn_web/blog/uikit/js/uikit.js"></script>
    <script type="text/javascript">
        <!--
        $(function(){
            $.ajax({
                type: "POST",
                url:"/blog/record_left",
                async: false,
                error: function(request) {
                    alert("Connection error");
                },success: function(data) {
                    $("#x-wiki-index").html(appentLeft(data));
                    if(data.length>=1){
                        $("#blog_conent").load("/blog/record_info?id="+data[0].id);
                    }

                }
            });
        })
        //-->
    </script>
</head>
<body>
<nav class="uk-navbar">
    <ul class="uk-navbar-nav uk-hidden-small uk-navbar-center">
        <li><a href="/">首页</a></li>
        <li class="uk-active"><a href="/blog/to_record_list">我的记录</a></li>
    </ul>
    <div class="uk-navbar-flip">
        <ul class="uk-navbar-nav">
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
                <div class="x-sidebar-left-content">
                    <div class="uk-float-right" style="padding-top:5px">
                        <a href="#0" onclick="expandWikiNode($('#x-wiki-index>div>i').get(0), true)" style="margin-left:5px"><i class="uk-icon-expand"></i></a>
                        <a href="#0" onclick="collapseWikiNode($('#x-wiki-index>div>i').get(0), true);expandActiveNode()" style="margin-left:5px"><i class="uk-icon-dot-circle-o"></i></a>
                        <a href="#0" onclick="collapseWikiNode($('#x-wiki-index>div>i').get(0), true)" style="margin-left:5px"><i class="uk-icon-compress"></i></a>
                    </div>
                    <ul class="uk-nav uk-nav-side">
                        <li class="uk-nav-header">目录</li>
                    </ul>
                    <ul id="x-wiki-index" class="uk-nav uk-nav-side" >
                        <div id="0013739516305929606dd18361248578c67b8067c8c017b000" depth="0" style="position:relative;margin-left:15px;" class="uk-active" expand="true">
                            <i onclick="toggleWikiNode(this)" class="uk-icon-minus-square-o" style="position:absolute;margin-left:-1em;padding-top:8px;"></i>
                            <a href="" class="x-wiki-index-item">Git教程</a>
                            <div id="001373962845513aefd77a99f4145f0a2c7a7ca057e7570000" depth="1" style="position: relative; margin-left: 15px;">
                                <i onclick="toggleWikiNode(this)" class="uk-icon-plus-square-o" style="position:absolute;margin-left:-1em;padding-top:8px;"></i>
                                <a href="" class="x-wiki-index-item">Git简介</a>
                                <div id="00137402760310626208b4f695940a49e5348b689d095fc000" depth="2" style="display:none;position:relative;margin-left:15px;" class="uk-active">
                                    <a href="/wiki/0013739516305929606dd18361248578c67b8067c8c017b000/00137402760310626208b4f695940a49e5348b689d095fc000"  class="x-wiki-index-item">Git的诞生</a>
                                </div>
                                <div id="001374027586935cf69c53637d8458c9aec27dd546a6cd6000" depth="2" style="display:none;position:relative;margin-left:15px;">
                                    <a href="/wiki/0013739516305929606dd18361248578c67b8067c8c017b000/001374027586935cf69c53637d8458c9aec27dd546a6cd6000" class="x-wiki-index-item">集中式vs分布式</a>
                                </div>
                            </div>
                            <div id="00137396287703354d8c6c01c904c7d9ff056ae23da865a000" depth="1" style="position: relative; margin-left: 15px;">
                                <a href="/wiki/0013739516305929606dd18361248578c67b8067c8c017b000/00137396287703354d8c6c01c904c7d9ff056ae23da865a000" class="x-wiki-index-item">安装Git</a>
                            </div>
                            <div id="00137628548491051ccfaef0ccb470894c858999603fedf000" depth="1" style="position: relative; margin-left: 15px;">

                                <a href="/wiki/0013739516305929606dd18361248578c67b8067c8c017b000/00137628548491051ccfaef0ccb470894c858999603fedf000" class="x-wiki-index-item">使用GitHub</a>
                            </div>

                            <div id="00150154460073692d151e784de4d718c67ce836f72c7c4000" depth="1" style="position: relative; margin-left: 15px;">

                                <a href="/wiki/0013739516305929606dd18361248578c67b8067c8c017b000/00150154460073692d151e784de4d718c67ce836f72c7c4000" class="x-wiki-index-item">使用码云</a>
                            </div>
                        </div>
                    </ul>
                    <div class="x-placeholder"></div>

                </div>
            </div>
            <div class="uk-width-medium-3-4">
                <article id="blog_conent" class="uk-article">
                    <p class="uk-article-lead">熟悉 UIkit 的基本组织结构。</p>
                    <p><span class="uk-badge uk-badge-danger">注意</span> 文档中的示例代码不具备可操作性，请随时右键查看源代码，以参考实例真实代码。</p>
                </article>
            </div>
        </div>
    </div>
</div>
<!-- 抽屉式边栏 -->
<div id="my-id" class="uk-offcanvas">
    <div class="uk-offcanvas-bar">
        <ul class="uk-nav uk-nav-offcanvas uk-nav-parent-icon" data-uk-nav="{multiple:true}">
            <li class="uk-nav-header">附加组件</li>
            <li class="uk-nav-divider"></li>
        </ul>
    </div>
</div>
</body>
</html>
