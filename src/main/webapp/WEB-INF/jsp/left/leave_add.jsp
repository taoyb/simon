<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2017-08-25
  Time: 下午 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<div class="pageContent">
    <form method="post" action="/leave/leaveAdd" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <input type="hidden" name="leaveId" value="${leave.leaveId}">
        <div class="pageFormContent" layoutH="60">
            <div>
                <label>请假天数：</label>
                <input name="leaveDays" class="required" type="text" value="${leave.leaveDays}" size="20" alt="请输入"/>
            </div>
            <div class="unit">
                <label>请假事由：</label>
                <textarea rows="5" style="width: 70%;" name="leaveReason" class="required" alt="请输入">${leave.leaveReason}</textarea>
            </div>
        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
                <li>
                    <div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
                </li>
            </ul>
        </div>
    </form>
</div>
