<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2017-08-29
  Time: 下午 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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