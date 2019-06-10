<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<c:set value="?t=${search}" var="urlGet" />
<div class="header"><spring:message code="title.results" />: <c:out value="${search}" /> <span class="badge"><c:out value="${articles.total}" /></span></div>
<c:if test="${not empty articles.records}">
    <c:forEach var="article" items="${articles.records}">
        <ui:article position="left" article="${article}" />
    </c:forEach>

    <ui:pagination urlGet="${urlGet}" destination=".articles" pre="search_${articles.tag}" current="${articles.current}" pages="${articles.pages}" />
</c:if>
<c:if test="${empty articles.records}">
    <div class="articles-empty"><spring:message code="articles.empty" /></div>
</c:if>