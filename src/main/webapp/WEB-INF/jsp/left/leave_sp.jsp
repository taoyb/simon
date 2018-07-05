<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2018-01-18
  Time: 下午 8:30
  To change this template use File | Settings | File Templates.
  请假审批办理页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp" %>
<div class="pageContent">
    <form method="post" action="/leave/submitTask" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
        <input type="hidden" name="id" value="${leave.leaveId}">
        <input type="hidden" name="taskId" value="${wb.taskId}">
        <input type="hidden" name="outcome" id="outcome" value="">
        <div class="pageFormContent" layoutH="210">
            <div class="unit">
                <label>请假天数：</label>
                <input type="text" name="" size="30" value="${leave.leaveDays}" readonly/>
            </div>
            <div class="unit">
                <label>请假原因：</label>
                <textarea style="width: 80%;" rows="5" readonly>${leave.leaveReason}</textarea>
            </div>
            <div class="unit">
                <label>批注：</label>
                <textarea style="width: 80%;" rows="5" name="comment" class="required"></textarea>
            </div>
        </div>
        <div class="panel" defH="110">
            <h1>审批意见</h1>
            <div>
                <table class="list" width="98%">
                    <thead>
                    <tr>
                        <th width="200">时间</th>
                        <th>批注人</th>
                        <th>批注信息</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${comments}" var="c">
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
        <div class="formBar">
            <ul>
                <c:choose>
                    <c:when test="${btns != null}">
                        <c:forEach items="${btns}" var="b">
                            <li>
                                <div class="button">
                                    <div class="buttonContent">
                                        <button type="submit" onclick="$('#outcome').val('${b}');">${b}</button>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <div class="buttonActive">
                                <div class="buttonContent">
                                    <button type="submit">提交</button>
                                </div>
                            </div>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">取消</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>

</div>
