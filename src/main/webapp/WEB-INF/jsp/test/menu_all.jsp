<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2017-08-16
  Time: 下午 2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<link rel="stylesheet" href="../../plugIn_web/zTree/css/demo.css" type="text/css">--%>
<link rel="stylesheet" href="../../plugIn_web/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="../../plugIn_web/zTree/js/jquery.ztree.core.js"></script>
<SCRIPT type="text/javascript">
    <!--
    var setting = {
        async: {
            enable: true,
            url:"/menus/treeList",
            autoParam:["id=id", "pId=pid"],
            otherParam:{"otherParam":"zTreeAsyncTest"},
            dataFilter: filter
        }
    };
    function filter(treeId, parentNode, childNodes) {
        if (!childNodes) return null;
        for (var i=0, l=childNodes.length; i<l; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
        }
        return childNodes;
    }
    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo"), setting);
    });
    //-->
</SCRIPT>
<div class="pageContent" style="padding:2px">
    <div class="tabs">
        <div class="tabsHeader">
            <div class="tabsHeaderContent">
                <ul>
                </ul>
            </div>
        </div>
        <div class="tabsContent">
            <div>
                <div layoutH="45" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
                    <ul id="treeDemo" class="ztree"></ul>
                    <ul class="tree treeFolder">
                        <li><a href="javascript">实验室检测</a>
                            <ul>
                                <li><a href="/test/get" target="ajax" rel="jbsxBox">尿检</a></li>
                                <li><a href="demo/pagination/list1.html" target="ajax" rel="jbsxBox">HIV检测</a></li>
                                <li><a href="demo/pagination/list1.html" target="ajax" rel="jbsxBox">HCV检测</a></li>
                                <li><a href="demo/pagination/list1.html" target="ajax" rel="jbsxBox">TB检测</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div id="jbsxBox" class="unitBox" style="margin-left:246px;">
                    <!--#include virtual="list1.html" -->
                </div>
            </div>
        </div>
        <div class="tabsFooter">
            <div class="tabsFooterContent"></div>
        </div>
    </div>
</div>