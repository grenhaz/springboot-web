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
            <div class="col-sm-offset-3 col-xs-12 col-sm-6">
                <div class="header"><spring:message code="label.user.profile.password" /></div>
                <form:form commandName="form" method="POST">
                    <form:hidden path="id" />
                    <div class="form-group">
                        <form:label path="password"><spring:message code="label.password" /></form:label>
                        <form:password class="form-control" path="password" maxlength="32" />
                        <form:errors path="password" cssClass="help-block help-error" />
                    </div>
                    <div class="form-group">
                        <form:label path="passwordRepeat"><spring:message code="label.passwordRepeat" /></form:label>
                        <form:password class="form-control" path="passwordRepeat" maxlength="32" />
                        <form:errors path="passwordRepeat" cssClass="help-block help-error" />
                    </div>
                    <div class="form-group">
                        <form:button class="btn btn-primary btn-full"><spring:message code="button.profile.password" /></form:button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</ui:layout>
