<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2017-08-15
  Time: 下午 5:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<form id="pagerForm" method="post" action="demo_page1.html">
    <input type="hidden" name="status" value="${param.status}">
    <input type="hidden" name="keywords" value="${param.keywords}"/>
    <input type="hidden" name="pageNum" value="1"/>
    <input type="hidden" name="numPerPage" value="${model.numPerPage}"/>
    <input type="hidden" name="orderField" value="${param.orderField}"/>
</form>
<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="demo_page1.html" method="post">
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        名称：<input type="text" name="keyword"/>
                    </td>
                </tr>
            </table>
            <div class="subBar">
                <ul>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">检索</button>
                            </div>
                        </div>
                    </li>
                    <li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
                </ul>
            </div>
        </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="add" href="demo_page4.html" target="navTab"><span>添加</span></a></li>
            <li><a class="delete" href="demo/common/ajaxDone.html?uid={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
            <li><a class="edit" href="demo_page4.html?uid={sid_user}" target="navTab"><span>修改</span></a></li>
            <li class="line">line</li>
            <li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
            <tr>
                <th width="80">ID</th>
                <th width="120">名称</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list.pageList}" var="test">
                <tr target="sid_user" rel="1">
                    <td>${test.testId}</td>
                    <td>${test.testName}</td>
                </tr>
            </c:forEach>
        <%--<tr target="sid_user" rel="1">
            <td>天津农信社</td>
            <td>A120113196309052434</td>
            <td>天津市华建装饰工程有限公司</td>
            <td>联社营业部</td>
            <td>29385739203816293</td>
            <td>5级</td>
            <td>政府机构</td>
            <td>2009-05-21</td>
        </tr>
        <tr target="sid_user" rel="2">
            <td>天津农信社</td>
            <td>A120113196309052434</td>
            <td>天津市华建装饰工程有限公司</td>
            <td>联社营业部</td>
            <td>29385739203816293</td>
            <td>5级</td>
            <td>政府机构</td>
            <td>2009-05-21</td>
        </tr>--%>
        </tbody>
    </table>
    <div class="panelBar">
        <div class="pages">
            <span>显示</span>
            <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
                <option value="20">20</option>
                <option value="50">50</option>
                <option value="100">100</option>
                <option value="200">200</option>
            </select>
            <span>条，共${list.totalNum}条</span>
        </div>
        <div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="1"></div>
    </div>
</div>