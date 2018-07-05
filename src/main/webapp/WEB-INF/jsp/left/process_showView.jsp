<%--
  Created by IntelliJ IDEA.
  User: taoyb
  Date: 2018-01-09
  Time: 下午 7:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/tag.jsp"%>
<div class="tabs" currentIndex="0" eventType="click">
    <div class="tabsHeader">
        <div class="tabsHeaderContent">
            <ul>
                <li><a href="javascript:;"><span>流程图</span></a></li>
                <c:if test="${modelId != null}">
                    <li><a href="javascript:;" onclick="loadXML();"><span>XML文件</span></a></li>
                </c:if>
            </ul>
        </div>
    </div>
    <div class="tabsContent">
        <div class="pageContent">
            <div class="pageFormContent" layoutH="56">
                <c:choose>
                    <c:when test="${modelId != null}">
                        <img src="/workflow/showViewProcess?modelId=${modelId}"/>
                    </c:when>
                    <c:when test="${taskId != null}">
                        <img src="/workflow/showViewProcess?taskId=${taskId}"/>
                    </c:when>
                    <c:otherwise>
                        <img src="/workflow/showViewProcess?deploymentId=${deploymentId}&diagramResourceName=${diagramResourceName}"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="pageContent">
            <div class="pageFormContent" layoutH="56">
                <textarea id="xml" style="width: 99%;height: 99%;"></textarea>
            </div>
        </div>
    </div>
    <div class="tabsFooter">
        <div class="tabsFooterContent"></div>
    </div>
</div>
<script type="text/javascript">
    function loadXML(){
        $.ajax({
            cache: false,
            async: false,
            type: "POST",
            dataType: "text",
            url: "/workflow/showProcessModelXML?modelId=${modelId}" ,
            success: function (data) {
                $("#xml").text(data);
            },
            error : function() {
                alert("加载XML文件失败");
            }
        });
    }
</script>
<%--<div class="pageContent">
    <div class="pageFormContent" layoutH="56">
        <c:choose>
            <c:when test="${modelId != null}">
                <img src="/workflow/showViewProcess?modelId=${modelId}"/>
            </c:when>
            <c:when test="${taskId != null}">
                <img src="/workflow/showViewProcess?taskId=${taskId}"/>
            </c:when>
            <c:otherwise>
                <img src="/workflow/showViewProcess?deploymentId=${deploymentId}&diagramResourceName=${diagramResourceName}"/>
            </c:otherwise>
        </c:choose>
    </div>
</div>--%>
