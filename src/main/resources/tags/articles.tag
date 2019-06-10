<%@tag description="Articles list" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<%@attribute name="menu" required="false" type="java.lang.Boolean" rtexprvalue="true" %>
<%@attribute name="articles" required="true" type="org.obarcia.demo.models.ListPagination" rtexprvalue="true" %>
<c:if test="${empty menu or menu == true}">
    <div class="row">
        <div class="col-xs-12">
            <ul class="list-sections">
                <li ${articles.type == "all" ? "class='active'" : "" }>
                    <a class="go" href="#${articles.tag}_all"><spring:message code="articles.type.all" /></a>
                </li>
                <spring:eval expression="@configProperties.getProperty('sections.types')" var="types" />
                <c:forEach items="${types}" var="t">
                    <li ${articles.type == t ? "class='active'" : "" }>
                        <a class="go" href="#${articles.tag}_${t}"><spring:message code="articles.type.${t}" /></a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</c:if>
<c:if test="${not empty articles.records}">
    <c:forEach var="article" items="${articles.records}">
        <ui:article position="left" article="${article}" />
    </c:forEach>
    <ui:pagination destination=".articles" 
                   menu="${menu}"
                   pre="${articles.tag}_${articles.type}" 
                   current="${articles.current}" 
                   pages="${articles.pages}" />
</c:if>
<c:if test="${empty articles.records}">
    <div class="articles-empty"><spring:message code="articles.empty" /></div>
</c:if>