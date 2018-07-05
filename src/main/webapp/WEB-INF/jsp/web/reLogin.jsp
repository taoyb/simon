<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2018-01-18
  Time: 上午 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="pageContent">
    <form method="post" action="/login/login" class="pageForm" onsubmit="return validateCallback(this, dialogAjaxDone)">
        <div class="pageFormContent" layoutH="58">
            <div class="unit">
                <label>用户名：</label>
                <input type="text" name="userName" size="30" class="required"/>
            </div>
            <div class="unit">
                <label>密码：</label>
                <input type="password" name="password" size="30" class="required"/>
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
