<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title" var="title" />
<ui:layout title="${title}" tag="${tag}" flash="${flash}">
    <div class="row">
        <div class="col-sm-8">
            <div class="section">
                <a id="return-top"></a>
                <div class="header"><spring:message code="articles.type.${type}" /></div>
                <div class="articles"></div>
            </div>
        </div>
        <div class="col-sm-4">
            <c:if test="${not empty importants}">
                <div class="section">
                    <div class="header"><spring:message code="title.importants" /></div>
                    <c:forEach var="article" items="${importants}">
                        <ui:article position="top" article="${article}" />
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </div>
    <script>
        $(function() {
            refreshBlock('.articles', '<c:url value="/ajax/${tag}/${type}" />', {'m': false}, false);
        });
    </script>
</ui:layout>