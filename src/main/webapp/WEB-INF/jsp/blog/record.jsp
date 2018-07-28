<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2017-08-16
  Time: 下午 2:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="../../plugIn_web/zTree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="../../plugIn_web/zTree/js/jquery.ztree.exedit.js"></script>
<SCRIPT type="text/javascript">
    <!--
    var setting = {
        async: {
            enable: true,
            url:"/blog/record_left",
            autoParam:["id=id", "pId=pid"]
        },
        view: {
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        edit: {
            editNameSelectAll: true,
            showRemoveBtn: showRemoveBtn
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeRename: beforeRename,
            onRemove: onRemove,
            onRename: onRename,
            onAsyncSuccess: zTreeRegisterTarget
        }
    };

    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
        var addStr = "";
        if (treeNode.pId == null || treeNode.pId == '0'){
            addStr = "<span class='button addroot' id='addPBtn_" + treeNode.tId
                + "' title='添加根节点' onfocus='this.blur();'></span>"
                + "<span class='button add' id='addBtn_" + treeNode.tId
                + "' title='添加子节点' onfocus='this.blur();'></span>"
        }else{
            addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                + "' title='添加子节点' onfocus='this.blur();'></span>"
            ;
        }
        addStr += "<span class='button edit' id='editBtn_" + treeNode.tId
            + "' title='添加子节点' onfocus='this.blur();'></span>"
            + "<span class='button remove' id='removeBtn_" + treeNode.tId
            + "' title='添加子节点' onfocus='this.blur();'></span>";
        sObj.after(addStr);
        var editbtn = $("#editBtn_"+treeNode.tId);
        if (editbtn) editbtn.bind("click", function(){
            beforeEditName(treeId, treeNode);
            return false;
        });
        var removeBtn_ = $("#removeBtn_"+treeNode.tId);
        if (removeBtn_) removeBtn_.bind("click", function(){
            onRemove(null,treeId, treeNode);
            return false;
        });
        var btnP = $("#addPBtn_"+treeNode.tId);
        if (btnP) btnP.bind("click", function(){
            saveNodes(0,null);
            return false;
        });
        var btn = $("#addBtn_"+treeNode.tId);
        if (btn) btn.bind("click", function(){
            saveNodes(treeNode.id,treeNode);
            return false;
        });
    }

    function removeHoverDom(treeId, treeNode) {
        if (treeNode.pId == null || treeNode.pId == '0'){
            $("#addBtn_"+treeNode.tId).unbind().remove();
            $("#addPBtn_"+treeNode.tId).unbind().remove();
        }else{
            $("#addBtn_"+treeNode.tId).unbind().remove();
        }
        $("#editBtn_"+treeNode.tId).unbind().remove();
        $("#removeBtn_"+treeNode.tId).unbind().remove();
    }

    var newCount = 1;
    function saveNodes(pid,treeNode) {
        var name = "new node" + (newCount++)
        $.ajax({
            type: "POST",
            url:"/record/saveLeftNote",
            data:{recordParentId:pid,recordName:name},
            async: false,
            success: function(data) {
                var zTree = $.fn.zTree.getZTreeObj("recordZtree");
                zTree.addNodes(treeNode, data);
                initUI($('#recordZtree'));
            }
        });
    }

    function beforeRename(treeId, treeNode, newName, isCancel) {
        className = (className === "dark" ? "":"dark");
        showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
        if (newName.length == 0) {
            setTimeout(function() {
                var zTree = $.fn.zTree.getZTreeObj("recordZtree");
                zTree.cancelEditName();
                alert("Node name can not be empty.");
            }, 0);
            return false;
        }
        return true;
    }

    function beforeEditName(treeId, treeNode) {
        className = (className === "dark" ? "":"dark");
        showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
        var zTree = $.fn.zTree.getZTreeObj("recordZtree");
        zTree.selectNode(treeNode);
        setTimeout(function() {
                setTimeout(function() {
                    zTree.editName(treeNode);
                }, 0);
        }, 0);
        return false;
    }

    function onRemove(e, treeId, treeNode) {
        $.ajax({
            type: "POST",
            url:"/record/deleteLeftNote",
            data:{recordId:treeNode.id},
            async: false,
            error: function(request) {
                alert("Connection error");
            },success: function(data) {
                var zTree = $.fn.zTree.getZTreeObj("recordZtree");
                setTimeout(function() {
                    setTimeout(function() {
                        zTree.removeNode(treeNode, false);
                    }, 0);
                }, 0);
            }
        });
    }

    function onRename(e, treeId, treeNode, isCancel) {
        $.ajax({
            type: "POST",
            url:"/record/updateLeftNoteName",
            data:{recordId:treeNode.id,recordName:treeNode.name},
            async: false,
            error: function(request) {
                alert("Connection error");
            }
        });
        showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
    }


    function showLog(str) {
        if (!log) log = $("#log");
        log.append("<li class='"+className+"'>"+str+"</li>");
        if(log.children("li").length > 8) {
            log.get(0).removeChild(log.children("li")[0]);
        }
    }
    function getTime() {
        var now= new Date(),
            h=now.getHours(),
            m=now.getMinutes(),
            s=now.getSeconds(),
            ms=now.getMilliseconds();
        return (h+":"+m+":"+s+ " " +ms);
    }

    function showRemoveBtn(treeId, treeNode) {
        return !treeNode.isParent;
    }

    function zTreeRegisterTarget(event, treeId, treeNode) {
        initUI($('#'+treeId));
    }

    $(document).ready(function(){
        $.fn.zTree.init($("#recordZtree"), setting);
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
                    <ul id="recordZtree" class="ztree"></ul>
                </div>
                <div id="jbsxBox1" class="unitBox" style="margin-left:246px;"></div>
            </div>
        </div>
        <div class="tabsFooter">
            <div class="tabsFooterContent"></div>
        </div>
    </div>
</div>