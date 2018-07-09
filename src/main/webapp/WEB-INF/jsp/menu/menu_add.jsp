<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2017-08-23
  Time: 下午 4:41
  To change this template use File | Settings | File Templates
  菜单添加
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<div class="pageContent">
    <form method="post" action="/menus/menuAdd" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
        <input type="hidden" name="menuParentid" value="${pid}">
        <input type="hidden" name="menuBelong" value="${menu.menuBelong}">
        <input type="hidden" name="menuId" value="${menu.menuId}">
        <div class="pageFormContent" layoutH="58">
            <div class="unit">
                <label>菜单名称：</label>
                <input type="text" name="menuName" size="30" value="${menu.menuName}" class="required" />
            </div>
            <div class="unit">
                <label>菜单路径：</label>
                <input type="text" name="menuUrl" size="30" value="${menu.menuUrl}" />
            </div>
            <div class="unit">
                <label>菜单路径：</label>
                <select name="menuType" class="required combox" >
                    <option value="backstage" <c:if test="${menu.menuType eq 'backstage'}">selected</c:if>> 后台 </option>
                    <option value="blog" <c:if test="${menu.menuType eq 'blog'}">selected</c:if>> 博客 </option>
                </select>
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

