<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.error" var="title" />
<ui:layout title="${title}" classCss="error" tag="games" flash="${flash}">
    <div class="title"><spring:message code="${message}" /></div>
    <c:if test="${not empty submessage}">
        <div class="message"><spring:message code="${submessage}" /></div>
    </c:if>
    <div>
        <a href="<c:url value="/" />"><spring:message code="error.link.home" /></a>
    </div>
</ui:layout>