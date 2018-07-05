<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2018-01-22
  Time: 下午 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<div class="panel" defH="400">
    <h1></h1>
    <div>
        <table class="list" width="98%">
            <thead>
            <tr>
                <th>任务节点ID</th>
                <th>任务节点名称</th>
                <th>开始时间</th>
                <th>结束时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${haiList}" var="h">
                <tr>
                    <td>${h.activityId}</td>
                    <td>${h.activityName}</td>
                    <td><fmt:formatDate value="${h.startTime}" type="both"/> </td>
                    <td><fmt:formatDate value="${h.endTime}" type="both"/> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>