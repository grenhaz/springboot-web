<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<div class="browser browser-${type}">
    <div class="row">
        <c:forEach items="${files}" var="file">
            <div class="col-xs-12 col-sm-6 col-md-4">
                <c:if test="${not file.isFile}">
                    <div class="file" onclick="$browserChange('<c:url value="/admin/ajax/browser" />', '${file.filename}', '${field}', '${type}');">
                        <div class="image folder"><img src="<c:url value="/resources/images/directory.png" />" /></div>
                        <div class="filename"><c:out value="${file.name}" /></div>
                    </div>
                </c:if>
                <c:if test="${file.isFile}">
                    <div class="file" onclick="$browserSelect('${field}', '${type}', '${file.filename}', '<c:url value="${file.url}" />');">
                        <c:if test="${file.isImage}">
                            <div class="image"><img src="<c:url value="${file.url}" />" /></div>
                        </c:if>
                        <c:if test="${!file.isImage}">
                            <div class="image"><img src="<c:url value="/resources/images/file.png" />" /></div>
                        </c:if>
                        <div class="filename"><c:out value="${file.name}" /></div>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>