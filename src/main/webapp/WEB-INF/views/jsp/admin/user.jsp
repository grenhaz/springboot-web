<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.admin" var="title" />
<ui:layout title="${title}" tag="admin" flash="${flash}">
    <div class="section">
        <div class="header"><spring:message code="label.admin.user" />: <c:out value="${id}" /></div>
            <form:form commandName="form" method="POST">
                <form:hidden path="id" />
                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-group">
                            <form:label path="email"><spring:message code="label.email" /></form:label>
                            <form:input class="form-control" path="email" maxlength="128" />
                            <form:errors path="email" cssClass="help-block help-error" />
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <form:label path="nickname"><spring:message code="label.nickname" /></form:label>
                            <form:input class="form-control" path="nickname" maxlength="32" />
                            <form:errors path="nickname" cssClass="help-block help-error" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <form:button class="btn btn-primary"><spring:message code="button.save" /></form:button>
                </div>
            </form:form>
        </div>
    </div>
</ui:layout>