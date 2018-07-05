<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2017-08-16
  Time: 下午 2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<link rel="stylesheet" href="../../plugIn_web/zTree/css/demo.css" type="text/css">--%>

<SCRIPT type="text/javascript">
    <!--
    var setting = {
        async: {
            enable: true,
            url:"/menus/treeLeft",
            autoParam:["id=id", "pId=pid"]
        }, callback: {
            onAsyncSuccess: zTreeRegisterTarget
        }
    };
    function zTreeRegisterTarget(event, treeId, treeNode) {
        initUI($('#'+treeId));
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
                <ul></ul>
            </div>
        </div>
        <div class="tabsContent">
            <div>
                <div layoutH="45" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
                <div id="jbsxBox" class="unitBox" style="margin-left:246px;"></div>
            </div>
        </div>
        <div class="tabsFooter">
            <div class="tabsFooterContent"></div>
        </div>
    </div>
</div>