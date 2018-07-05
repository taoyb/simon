<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2017-08-29
  Time: 下午 11:09
  To change this template use File | Settings | File Templates.
  记录-列表
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<form id="pagerForm" method="post" action="/record/recordAll">
    <%--<input type="hidden" name="status" value="${param.status}">--%>
    <%--<input type="hidden" name="keywords" value="${param.keywords}"/>--%>
    <input type="hidden" name="pageNum" value="${pager.pageNum}"/>
    <input type="hidden" name="numPerPage" value="${pager.pageSize}"/>
    <%--<input type="hidden" name="orderField" value="${param.orderField}"/>--%>
</form>
<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="/record/recordAll" method="post">
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        名称：<input type="text" name="recordName"/>
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
                    <%--<li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>--%>
                </ul>
            </div>
        </div>
    </form>
</div>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="add" href="/record/recordToSaveOrUp?m=${m}" target="navTab" rel="recordToSaveOrUp"><span>添加</span></a></li>
            <li><a class="delete" href="/record/recordDel?rid={id}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
            <li><a class="edit" href="/record/recordToSaveOrUp?m=${m}&recordId={id}" target="navTab"><span>修改</span></a></li>
            <li class="line">line</li>
            <li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
            <li><a class="add" href="/record/toSendMail" target="dialog" mask="true"><span>发送邮件</span></a></li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
        <tr>
            <th width="80">ID</th>
            <th width="120">名称</th>
            <th width="50">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pager.pageList}" var="record">
            <tr target="id" rel="${record.recordId}">
                <td>${record.recordId}</td>
                <td>${record.recordName}</td>
                <td>
                    <a title="详情" target="dialog" rel="info" href="/record/recordToInfo?recordId=${record.recordId}" class="btnView">详情</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%--<%@include file="../common/paging.jsp"%>--%>
    <div class="panelBar">
        <div class="pages">
            <span>显示</span>
            <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value},'jbsxBox')">
                <option value="20" <c:if test="${pager.pageSize == '20'}">selected</c:if>>20</option>
                <option value="50" <c:if test="${pager.pageSize == '50'}">selected</c:if>>50</option>
                <option value="100" <c:if test="${pager.pageSize == '100'}">selected</c:if>>100</option>
                <option value="200" <c:if test="${pager.pageSize == '200'}">selected</c:if>>200</option>
            </select>
            <span>条，共${pager.totalNum}条</span>
        </div>
        <div class="pagination" targetType="navTab" rel="jbsxBox" totalCount="${pager.totalNum}" numPerPage="${pager.pageSize}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
    </div>
</div>