<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.register" var="title" />
<ui:layout title="${title}" tag="user" flash="${flash}">
    <div class="login">
        <h1>${title}</h1>

        <div class="section">
            <div class="header"><spring:message code="title.register.info" /></div>
            <div class="row">
                <div class="col-xs-4 left">
                    <form:form commandName="model" method="POST">
                        <div class="form-group">
                            <form:label path="nickname"><spring:message code="label.nickname" /></form:label>
                            <form:input class="form-control" path="nickname" maxlength="32" />
                            <form:errors path="nickname" cssClass="help-block help-error" />
                        </div>
                        <div class="form-group">
                            <form:label path="email"><spring:message code="label.email" /></form:label>
                            <form:input class="form-control" path="email" maxlength="128" />
                            <form:errors path="email" cssClass="help-block help-error" />
                        </div>
                        <div class="form-group">
                            <form:label path="emailRepeat"><spring:message code="label.emailRepeat" /></form:label>
                            <form:input class="form-control" path="emailRepeat" maxlength="128" />
                            <form:errors path="emailRepeat" cssClass="help-block help-error" />
                        </div>
                        <div class="form-group">
                            <form:button class="btn btn-primary btn-full"><spring:message code="button.register" /></form:button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</ui:layout>