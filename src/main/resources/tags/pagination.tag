<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="destination" required="true" rtexprvalue="true"%>
<%@attribute name="pre" required="true" rtexprvalue="true"%>
<%@attribute name="current" required="true" rtexprvalue="true"%>
<%@attribute name="pages" required="true" rtexprvalue="true"%>
<%@attribute name="menu" required="false" type="java.lang.Boolean" rtexprvalue="true"%>
<%@attribute name="urlGet" required="false"  rtexprvalue="true"%>
<c:set value="${not empty urlGet ? urlGet : ''}" var="getvars" />  
<c:set value="${current - 3 >= 1 ? current - 3 : 1}" var="first" />
<c:set value="${current + 3 <= pages ? current + 3 : pages}" var="last" />
<c:if test="${pages > 1}">
    <ul class="pagination">
        <c:if test="${first > 1}">
            <li>
                <a class="go" 
                   data-menu="${menu}" 
                   data-destination="${destination}" 
                   data-vars="${getvars}"
                   href="#${pre}_1">
                    &lt;&lt;
                </a>
            </li>
        </c:if>
        <c:if test="${first < current}">
            <c:forEach var="i" begin="${first}" end="${current - 1}">
                <li>
                    <a class="go" 
                       data-menu="${menu}" 
                       data-destination="${destination}"
                       data-vars="${getvars}"
                       href="#${pre}_${i}">
                        <c:out value="${i}" />
                    </a>
                </li>
            </c:forEach>
        </c:if>
        <li class="active"><span><c:out value="${current}" /></span></li>
        <c:if test="${current + 1 <= last}">
            <c:forEach var="i" begin="${current + 1}" end="${last}">
                <li>
                    <a class="go" 
                       data-menu="${menu}" 
                       data-destination="${destination}" 
                       data-vars="${getvars}"
                       href="#${pre}_${i}">
                        <c:out value="${i}" />
                    </a>
                </li>
            </c:forEach>
        </c:if>
        <c:if test="${last <= pages && current + 3 < pages}">
            <li>
                <a class="go" 
                   data-menu="${menu}" 
                   data-destination="${destination}" 
                   data-vars="${getvars}"
                   href="#${pre}_${pages}">
                    &gt;&gt;
                </a>
            </li>
        </c:if>
    </ul>
</c:if>