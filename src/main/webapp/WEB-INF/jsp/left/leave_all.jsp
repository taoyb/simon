<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2017-08-15
  Time: 下午 5:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<form id="pagerForm" method="post" action="/leave/findAll">
    <%--<input type="hidden" name="status" value="${param.status}">--%>
    <%--<input type="hidden" name="keywords" value="${param.keywords}"/>--%>
    <input type="hidden" name="pageNum" value="${pager.pageNum}"/>
    <input type="hidden" name="numPerPage" value="${pager.pageSize}"/>
    <%--<input type="hidden" name="orderField" value="${param.orderField}"/>--%>
</form>
<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="/leave/findAll" method="post">
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                        名称：<input type="text" name="testName"/>
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
            <li><a class="add" href="/leave/toAdd?m=${m}" target="dialog" rel="formToAdd"><span>请假申请</span></a></li>
        </ul>
    </div>
    <table class="table" width="100%" layoutH="138">
        <thead>
            <tr>
                <th width="80">编号</th>
                <th width="80">请假日期</th>
                <th width="80">请假天数</th>
                <th width="80">请假原因</th>
                <th width="80">审核状态</th>
                <th width="120">操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${pager.pageList}" var="leave">
                <tr target="tid" rel="${leave.leaveId}">
                    <td>${leave.leaveId}</td>
                    <td><fmt:formatDate type="both" value="${leave.leaveDate}"/></td>
                    <td>${leave.leaveDays}</td>
                    <td>${leave.leaveReason}</td>
                    <td>${leave.leaveState}</td>
                    <td>
                        <c:choose>
                            <c:when test="${leave.leaveState == null or leave.leaveState == '未提交'}">
                                <a href="/leave/toAdd?mid=${leave.leaveId}" target="dialog" style="color: #3399CC;" rel="formToAdd"><span>修改</span></a>
                                <a href="/leave/deleteLeave?mid=${leave.leaveId}" target="ajaxTodo" style="color: #3399CC;" title="确认删除">删除</a>
                                <a href="/leave/startApply?id=${leave.leaveId}" target="ajaxTodo" style="color: #3399CC;" title="确认提交申请">提交申请</a>
                            </c:when>
                            <c:otherwise>
                                <a href="/workflow/historyCommentFormList?piId=${leave.processInstanceId}" target="dialog"  style="color: #3399CC;" >查看审核记录</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
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