<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2017-08-15
  Time: 下午 5:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<form id="pagerForm" method="post" action="/menus/menuList">
        <input type="hidden" name="parentId" value="${parentId}">
    <input type="hidden" name="pageNum" value="${pager.pageNum}"/>
    <input type="hidden" name="numPerPage" value="${pager.pageSize}"/>
</form>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="add" href="/menus/menuToAdd?pid=${parentId}" target="dialog" mask="true"><span>添加</span></a></li>
            <li><a class="delete" href="/menus/menuDel?mid={sid_menus}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
            <li><a class="edit" href="/menus/menuToAdd?mid={sid_menus}&pid=${parentId}" target="dialog" mask="true"><span>修改</span></a></li>
        </ul>
    </div>
    <table class="list" width="100%" layoutH="138">
        <thead>
            <tr>
                <th width="80">ID</th>
                <th width="80">名称</th>
                <th width="80">类型</th>
                <th width="120">路径</th>
            </tr>
        </thead>
        <tbody class="dragsort">
            <c:forEach items="${pager.pageList}" var="menu">
                <tr target="sid_menus" rel="${menu.menuId}">
                    <td width="80">${menu.menuId}</td>
                    <td>${menu.menuName}</td>
                    <td>
                        <c:choose>
                            <c:when test="${menu.menuType eq 'blog'}">博客菜单</c:when>
                            <c:otherwise>后台菜单</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${menu.menuUrl}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
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
<%--
<script type="text/javascript">
    $(document).ready(function(){
        $(".gridtbody").dragsort({ itemSelector: "tr", dragSelector: "tr", dragBetween: true,dragEnd: saveOrder1, placeHolderTemplate: "<tr></tr>" });
//        swapItems
    });
    function saveOrder1() {
        //  var data = $("#gridtbody").map(function() { return $(this).children().html(); }).get();
        var data = $("[name='biaozhi']").map(function() { return $(this).html(); }).get();
        $("#listhaoSortOrder").val(data.join("|"));
    };
</script>--%>
