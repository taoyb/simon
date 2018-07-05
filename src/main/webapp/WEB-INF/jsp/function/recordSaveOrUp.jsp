<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2017-08-29
  Time: 下午 11:09
  To change this template use File | Settings | File Templates.
  记录-添加与修改页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>

<div class="pageContent">
    <form method="post" action="/record/recordAdd" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
        <input type="hidden" name="m" value="${m}">
        <input type="hidden" name="recordId" value="${record.recordId}">
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
                <li>
                    <div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
                </li>
            </ul>
        </div>
        <div class="pageFormContent" layoutH="56">
            <p>
                <label>记录名称：</label>
                <input name="recordName" class="required" type="text" value="${record.recordName}" size="20" alt="请输入"/>
            </p>
            <div class="divider"></div>
            <div>
                <textarea id="recordDesc" name="recordDesc" style="height: 300px;width: 100%;">${record.recordDesc}</textarea>
            </div>
        </div>
        <script type="text/javascript">
            var editor = new UE.ui.Editor();
            editor.render('recordDesc');
        </script>
    </form>
</div>