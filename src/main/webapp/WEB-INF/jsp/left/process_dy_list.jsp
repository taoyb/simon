<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2017-08-15
  Time: 下午 5:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<form id="pagerForm" method="post" action="/test/formAll">
    <input type="hidden" name="pageNum" value="${pager.pageNum}"/>
    <input type="hidden" name="numPerPage" value="${pager.pageSize}"/>
</form>
<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="/workflow/processDefinitionPage" method="post">
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        流程定义名称：<input type="text" name="p_name"/>
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
                <th width="80">编号</th>
                <th width="120">流程名称</th>
                <th width="120">流程定义的Key</th>
                <th width="120">流程版本</th>
                <th width="120">流程定义的规则文件名称</th>
                <th width="120">流程定义的规则图片名称</th>
                <th width="120">流程部署ID</th>
                <th width="120">操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${pager.pageList}" var="p">
                <tr target="tid" rel="${p.id}">
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.key}</td>
                    <td>${p.version}</td>
                    <td>${p.resourceName}</td>
                    <td>${p.diagramResourceName}</td>
                    <td>${p.deploymentId}</td>
                    <td><a href="/workflow/toShowViewProcess?deploymentId=${p.deploymentId}&diagramResourceName=${p.diagramResourceName}" target="dialog" max="false" width="800"  height="480">查看流程图</a></td><%--dialog--%>
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