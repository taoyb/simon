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
    <form method="post" action="/test/formAdd" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
        <input type="hidden" name="m" value="${m}">
        <input type="hidden" name="testId" value="${test.testId}">
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
                <label>用户名：</label>
                <input name="testName" class="required" type="text" value="${test.testName}" size="20" alt="请输入"/>
            </p>
            <p>
                <label>文本输入：</label>
                <input name="testWbsr" class="required" type="text" value="${test.testWbsr}" size="20" alt="请输入"/>
            </p>
            <p>
                <label>查找带回：</label>
                <input type="hidden" name="orgLookup.id" value="${orgLookup.id}"/>
                <input type="text" class="required" name="testCzdh" value="${test.testCzdh}" suggestFields="orgNum,orgName" suggestUrl="demo/database/db_lookupSuggest.html" lookupGroup="orgLookup" />
                <a class="btnLook" href="demo/database/dwzOrgLookup.html" lookupGroup="orgLookup">查找带回</a>
            </p>
            <p>
                <label>数字输入：</label>
                <input name="testSzsr" class="digits" type="text" value="${test.testSzsr}" size="30" alt="请输入数字"/>
            </p>
            <p>
                <label>下拉菜单：</label>
                <select name="testXlcd" class="required combox">
                    <option value="">请选择</option>
                    <option value="个人" <c:if test="${test.testXlcd eq '个人'}">selected</c:if> >个人</option>
                    <option value="公司" <c:if test="${test.testXlcd eq '公司'}">selected</c:if>>公司</option>
                </select>
            </p>
            <p>
                <label>执照签发日期：</label>
                <input type="text" name="testRqgs" class="date" value="<fmt:formatDate value="${test.testRqgs}" type="date"/>" size="20" /><a class="inputDateButton" href="javascript:;">选择</a>
            </p>
            <div class="divider"></div>
        </div>
    </form>
</div>
