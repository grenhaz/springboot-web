<%@tag description="Main Layout" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@attribute name="title" required="false" type="java.lang.String" %>
<%@attribute name="lang" required="false" type="java.lang.String" %>
<%@attribute name="classCss" required="false" type="java.lang.String" %>
<%@attribute name="tag" required="true" rtexprvalue="true" %>
<%@attribute name="flash" required="false" rtexprvalue="true" %>
<%@attribute name="search" required="false" rtexprvalue="true" %>
<c:set var="lang" value="${(empty lang) ? 'es' : lang}" />
<c:set var="title" value="${(empty title) ? 'No title' : title}" />
<!DOCTYPE html>
<html lang="${lang}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><c:out value="${title}" /></title>
        <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/favicon.ico" />" />
        <link href="https://fonts.googleapis.com/css?family=Roboto:700,700italic,500italic,500,400,400italic,300|Oxygen:400,700|Roboto+Condensed:400,700" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/fontawesome/css/font-awesome.min.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/css/site.css" />" rel="stylesheet" type="text/css">
 
        <script src="<c:url value="/resources/jquery/jquery.min.js" />"></script>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />"></script>
        <script src="<c:url value="/resources/bootbox/bootbox.min.js" />"></script>
        <script src="<c:url value="/resources/js/site.js" />"></script>
        
        <sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">
            <link href="<c:url value="/resources/datatables/datatables.min.css" />" rel="stylesheet" type="text/css" />
            <link href="<c:url value="/resources/datepicker/css/bootstrap-datepicker3.min.css" />" rel="stylesheet" type="text/css" />
            
            <script src="<c:url value="/resources/datatables/datatables.min.js" />"></script>
            <script src="<c:url value="/resources/datatables/datatables.buttons.min.js" />"></script>
            <script src="<c:url value="/resources/tinymce/tinymce.min.js" />" type="text/javascript"></script>
            <script src="<c:url value="/resources/tinymce/jquery.tinymce.min.js" />" type="text/javascript"></script>
            <script src="<c:url value="/resources/js/admin.js" />"></script>
            <script src="<c:url value="/resources/datepicker/js/bootstrap-datepicker.js" />"></script>
            <script src="<c:url value="/resources/datepicker/js/bootstrap-timepicker.js" />"></script>
            <script src="<c:url value="/resources/datepicker/js/bootstrap-datepicker.es.min.js" />"></script>
            <script src="<c:url value="/resources/datepicker/js/bootstrap-datepicker.en.min.js" />"></script>
        </sec:authorize>
        <script>
            $(function () {
                // Cambio de secciones
                $(document.body).on('click', 'a.go', function(e) {
                    var url = $(this).attr('href');
                    var index = url.indexOf('#');
                    if (index >= 0) {
                        var link = url.substring(index + 1);
                        var destination = $(this).data('destination');
                        if (destination === undefined) {
                            destination = '.articles';
                        }
                        var vars = $(this).data('vars');
                        var data = {};
                        var menu = $(this).data('menu');
                        if (menu === true || menu === false) {
                            data['m'] = menu;
                        }
                        refreshBlock(destination, '<c:url value="/ajax/" />' + link.replace(/\_/g, '/') + (vars !== undefined ? vars : ''), data, true);
                    }
                    return true;
                });
                <c:if test="${not empty flash}">
                    alertJs('<c:out value="${flash}" />');
                </c:if>
            });
        </script>
    </head>
    <body class="<c:out value="${classCss}" />">
        <ui:header tag="${tag}" search="${search}" />

        <div class="container">
            <div class="wrap">
                <div class="wrap-container">
                    <jsp:doBody />
                </div>
                    
                <ui:footer />
            </div>
        </div>
    </body>
</html>