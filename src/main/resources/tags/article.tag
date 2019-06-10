<%@tag description="Article" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="article" required="true" type="org.obarcia.demo.models.article.ArticleSimple" rtexprvalue="true" %>
<%@attribute name="position" required="true" %>
<div class="article ${position}">
    <!-- IMAGE -->
    <div class="image">
        <a href="<c:url value="/article/${article.id}" />">
            <img src="<c:url value="/data/articles/${article.image}" />" />
            <c:if test="${article.type == 'review'}">
                <div class="over score">
                    <div class="line"></div>
                    <div class="number"><c:out value="${article.score}" /></div>
                </div>
            </c:if>
            <c:if test="${article.type == 'video'}">
                <div class="over over-play"><i class="fa fa-play-circle-o"></i></div>
            </c:if>
            <c:if test="${article.type != 'review'}">
                <div class="over over-title"><c:out value="${article.title}" /></div>
            </c:if>
            <div class="comments"><c:out value="${article.commentsCount}" /></div>
        </a>
    </div>
    <!-- TEXT -->
    <div class="text">
        <div class="type">
            <spring:message code="article.type.${article.type}" />
        </div>
        <div class="title">
            <a href="<c:url value="/article/${article.id}" />">
                <c:out value="${article.title}" />
            </a>
        </div>
        <div class="content">
            <c:out value="${article.description}" />
        </div>
        <div class="info"><i class="fa fa-comment"></i><c:out value="${article.commentsCount}" /> | <c:out value="${article.formattedPublish}" /> | <c:out value="${article.formattedTags}" /></div>
    </div>
</div>