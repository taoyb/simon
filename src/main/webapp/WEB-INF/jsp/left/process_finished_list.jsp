<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2018-01-22
  Time: 下午 2:05
  To change this template use File | Settings | File Templates.
  已办理任务管理
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<form id="pagerForm" method="post" action="/workflow/finishedList">
    <input type="hidden" name="pageNum" value="${pager.pageNum}"/>
    <input type="hidden" name="numPerPage" value="${pager.pageSize}"/>
</form>
<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="/workflow/finishedList" method="post">
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        任务名称：<input type="text" name="s_name"/>
                    </td>
                    <td>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">检索</button>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<div class="pageContent">
    <table class="table" width="100%" layoutH="85">
        <thead>
        <tr>
            <th width="80">任务ID</th>
            <th width="120">任务名称</th>
            <th width="120">创建时间</th>
            <th width="120">结束时间</th>
            <th width="120">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pager.pageList}" var="t">
            <tr target="tid" rel="${t.id}">
                <td>${t.id}</td>
                <td>${t.name}</td>
                <td><fmt:formatDate value="${t.createTime}" type="both"/></td>
                <td><fmt:formatDate value="${t.endTime}" type="both"/></td>
                <td>
                    <a href="/workflow/executionProcess?taskId=${t.id}" target="dialog"  style="color: #3399CC;" mask="true" max="false" width="800"  height="480">流程执行过程</a>
                    <a href="/workflow/historyCommentList?taskId=${t.id}" target="dialog"  style="color: #3399CC;" mask="true" max="false" width="800"  height="480">历史批注</a>
                    <a href="/workflow/toShowViewProcess?taskId=${t.id}" target="dialog"  style="color: #3399CC;" max="false" width="800"  height="480">查看流程图</a>
                </td><%--dialog--%>
            </tr>
        </c:forEach>
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
            <span>条，共${pager.totalNum}条</span>
        </div>
        <div class="pagination" targetType="navTab" totalCount="${pager.totalNum}" numPerPage="${pager.pageSize}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
    </div>
</div>
