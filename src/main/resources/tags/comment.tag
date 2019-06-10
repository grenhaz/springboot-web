<%@tag description="Comment" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="comment" required="true" type="org.obarcia.demo.models.article.Comment" rtexprvalue="true" %>
<div class="comment">
    <div class="comment-header">
        <div class="image"><img src="<c:url value="/data/avatars/${not empty comment.user.avatar ? comment.user.avatar : 'anonymous.png'}" />" /></div>
        <div class="nickname"><c:out value="${comment.user.nickname}" /></div>
        <div class="publish"><c:out value="${comment.formattedPublish}" /></div>
    </div>
    <div class="content">
        <c:if test="${!comment.erased}">
            <c:out value="${comment.content}" />
        </c:if>
        <c:if test="${comment.erased}">
            <span class="erased"><spring:message code="comment.erased" /></span>
        </c:if>
    </div>
</div>