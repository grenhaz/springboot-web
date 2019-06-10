<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title" var="title" />
<ui:layout title="${title}" tag="user" flash="${flash}">
    <div class="section profile">
        <div class="header"><spring:message code="label.user.profile" /></div>
        <div class="row">
            <div class="col-xs-6 col-sm-3">
                <form:form commandName="form" method="POST">
                    <form:hidden path="id" />
                    <div class="form-group">
                        <div class="avatar">
                            <img class="clickable avatar-img" src="<c:url value="/data/avatars/${form.avatar}" />" onclick="openWindowAvatars();" />
                            <form:hidden path="avatar" />
                            <form:errors path="avatar" cssClass="help-block help-error" />
                        </div>
                    </div>
                    <div class="form-group">
                        <form:label path="nickname"><spring:message code="label.nickname" /></form:label>
                        <form:input class="form-control" path="nickname" maxlength="32" />
                        <form:errors path="nickname" cssClass="help-block help-error" />
                    </div>
                    <div class="form-group">
                        <form:button class="btn btn-primary btn-full"><spring:message code="button.profile.update" /></form:button>
                    </div>
                </form:form>
            </div>
            <div class="col-xs-12 col-sm-9">
                <div class="header"><spring:message code="label.user.last_comments" /></div>
                <c:if test="${not empty comments}">
                    <div class="row">
                        <c:forEach var="comment" items="${comments}">
                            <div class="col-xs-12">
                                <ui:mcomment comment="${comment}" />
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${empty comments}">
                    <spring:message code="comments.empty" />
                </c:if>
            </div>
        </div>
    </div>
    <script>
        function openWindowAvatars() {
            openWindow('<c:url value="/user/avatars" />', {'field': 'avatar'}, {'title': '<spring:message code="title.avatar.select" />', 'className': 'avatars-modal'});
        }
    </script>
</ui:layout>
