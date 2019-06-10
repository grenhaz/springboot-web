<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<!-- ADD COMMENTS -->
<sec:authorize access="isAuthenticated()">
    <form:form commandName="comment" id="form-comment">
        <div class="row">
            <div class="col-xs-12">
                <div class="form-group">
                    <form:textarea class="form-control" path="content" rows="8" />
                    <form:errors path="content" cssClass="help-block help-error" />
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="form-group">
                    <form:button class="btn btn-primary"><spring:message code="button.comment" /></form:button>
                </div>
            </div>
        </div>
    </form:form>
</sec:authorize>