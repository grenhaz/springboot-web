<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title" var="title" />
<ui:layout title="${title}" tag="${tag}" flash="${flash}" search="${search}">
    <script>
        $(function () {
            // Sección inicial
            var anchor = window.location.hash;
            if (anchor !== '' && anchor.length > 1) {
                var link = anchor.substring(1);
                refreshBlock('.articles', '<c:url value="/ajax/" />' + link.replace(/\_/g, '/'), {'t': '<c:out value="${search}" />'}, false);
            } else {
                refreshBlock('.articles', '<c:url value="/ajax/search/${tag}" />', {'t': '<c:out value="${search}" />'}, false);
            }
        });
    </script>
    <div class="section">
        <a id="return-top"></a>
        <div class="articles"></div>
    </div>
</ui:layout>