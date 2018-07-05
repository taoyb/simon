<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2018-01-25
  Time: 下午 5:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<div class="pageContent">
    <form method="post" action="/record/sendMail" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
        <input type="hidden" name="" value="">
        <div class="pageFormContent" layoutH="58">
            <div class="unit">
                <label>收件人：</label>
                <input type="text" name="to" size="30" value="" class="required" />
            </div>
            <div class="unit">
                <label>主题：</label>
                <input type="text" name="subject" size="30" value="" class="required"/>
            </div>
            <div class="unit">
                <label>内容：</label>
                <textarea name="html" rows="4" style="width: 70%;"></textarea>
            </div>
        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>
    </form>
</div>