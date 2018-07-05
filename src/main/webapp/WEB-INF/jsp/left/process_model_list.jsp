<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2017-08-15
  Time: 下午 5:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<form id="pagerForm" method="post" action="/workflow/modelList">
    <input type="hidden" name="pageNum" value="${pager.pageNum}"/>
    <input type="hidden" name="numPerPage" value="${pager.pageSize}"/>
</form>
<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="/workflow/modelList" method="post">
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        流程模型名称：<input type="text" name="m_name"/>
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
            <li><a class="add" href="/workflow/create" ><span>模型设计</span></a></li>
            <li><a class="edit" href="/workflow/deploy/{modelId}"  target="ajaxTodo"  title="确定要部署此流程模型吗?"><span>模型部署</span></a></li>
            <li><a class="delete" href="/workflow/delete/{modelId}" target="ajaxTodo" title="确定要删除吗?"><span>删除模型</span></a></li>
            <li><a class="icon" href="/workflow/exportModelByXML?modelId={modelId}" target="dwzExportId" targetType="navTab" title="确实要导出这些记录吗?"><span>导出XML</span></a></li>
        </ul>
    </div>
    <table class="table" width="101%" layoutH="138">
        <thead>
            <tr>
                <th width="10%">编号</th>
                <th width="25%">流程名称</th>
                <th width="20%">模型版本</th>
                <th width="20%">模型类型</th>
                <th width="20%">模型创建时间</th>
                <th width="5%">操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${pager.pageList}" var="m">
                <tr target="modelId" rel="${m.id}">
                    <td>${m.id}</td>
                    <td>${m.name}</td>
                    <td>${m.version}</td>
                    <td>${m.category}</td>
                    <td><fmt:formatDate value="${m.createTime}" type="both"/> </td>
                    <td>
                        <a target="dialog" max="false" width="800"  height="480" href="/workflow/toShowViewProcess?modelId=${m.id}" title="查看流程图" class="btnView">查看流程图</a>
                        <a title="编辑模型" href="/workflow/updateModel?modelId=${m.id}" class="btnEdit">编辑模型</a>
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