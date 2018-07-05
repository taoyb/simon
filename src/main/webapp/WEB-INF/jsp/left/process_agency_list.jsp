<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2018-01-15
  Time: 下午 3:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<form id="pagerForm" method="post" action="/workflow/taskPage">
    <input type="hidden" name="pageNum" value="${pager.pageNum}"/>
    <input type="hidden" name="numPerPage" value="${pager.pageSize}"/>
</form>
<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="/workflow/taskPage" method="post">
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        流程定义名称1：<input type="text" name="p_name"/>
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
            <th width="120">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pager.pageList}" var="t">
            <tr target="tid" rel="${t.id}">
                <td>${t.id}</td>
                <td>${t.name}</td>
                <td><fmt:formatDate value="${t.createTime}" type="both"/></td>
                <td>
                    <a href="/leave/toLeaveSp?taskId=${t.id}" target="dialog" mask="true" max="false" width="800"  height="480">办理任务</a>
                    <a href="/workflow/toShowViewProcess?taskId=${t.id}" target="dialog" max="false" width="800"  height="480">查看流程图</a>
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
