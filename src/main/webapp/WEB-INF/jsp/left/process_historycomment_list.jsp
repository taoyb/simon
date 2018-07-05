<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2018-01-22
  Time: 下午 9:45
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
                <th>批注时间</th>
                <th>批注人</th>
                <th>批注信息</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${comment}" var="c">
                <tr>
                    <td><fmt:formatDate value="${c.time}" type="both"/> </td>
                    <td>${c.userId}</td>
                    <td>${c.fullMessage}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>