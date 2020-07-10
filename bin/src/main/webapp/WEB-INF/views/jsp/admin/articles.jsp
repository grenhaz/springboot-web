<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<spring:message code="title.admin" var="title" />
<ui:layout title="${title}" tag="admin" flash="${flash}">
    <div class="section">
        <div class="header"><spring:message code="label.admin.articles" /></div>
        <table id="records" class="table table-striped table-bordered" cellspacing="0">
            <thead>
                <tr>
                    <th><spring:message code="column.id" /></th>
                    <th><spring:message code="column.title" /></th>
                    <th><spring:message code="column.description" /></th>
                    <th><spring:message code="column.type" /></th>
                    <th><spring:message code="column.tags" /></th>
                    <th><spring:message code="column.publish" /></th>
                    <th style="width:1px;"><spring:message code="column.active" /></th>
                    <th style="width:1px;"></th>
                </tr>
            </thead>
        </table>
    </div>
    <script>
        $(function() {
            $('.btn-refresh')
            $('#records').DataTable({
                "language": {
                    "url": "<c:url value="/resources/datatables/i18n/es.json" />"
                },
                "dom": "<'row'<'col-sm-3'l><'col-sm-6'B><'col-sm-3'f>>rt<'row'<'col-sm-6'i><'col-sm-6'p>>",//'lBfrtip',
                "buttons": [
                    {
                        "text": "<spring:message code="label.admin.articles.new" />",
                        "className": 'btn btn-primary',
                        "action": function (e, dt, node, config) {
                            document.location = "<c:url value="/admin/article/0" />";
                        }
                    },
                    {
                        "text": "<spring:message code="label.admin.refresh" />",
                        "className": 'btn btn-primary',
                        "action": function (e, dt, node, config) {
                            dt.ajax.reload();
                        }
                    }
                ],
                "order": [[ 5, "desc" ]],
                "processing": true,
                "serverSide": true,
                "ajax": "<c:url value="/admin/ajax/articles" />",
                "columns": [
                    {
                        "data": "id"
                    }, {
                        "data": "title"
                    }, {
                        "data": "description"
                    }, {
                        "data": "type",
                        "render": function ( data, type, row, meta ) {
                            switch(data) {
                                case "new": return "<spring:message code="articles.type.new" />";
                                case "review": return "<spring:message code="articles.type.review" />";
                                case "video": return "<spring:message code="articles.type.video" />";
                                case "special": return "<spring:message code="articles.type.special" />";
                                case "guide": return "<spring:message code="articles.type.guide" />";
                            }
                            return "";
                        }
                    }, {
                        "data": "formattedTags",
                        "name": "tags",
                        "orderable": false
                    }, {
                        "data": "formattedPublish",
                        "name": "publish",
                        "className": "text-center"
                    }, {
                        "data": "active",
                        "className": "text-center",
                        "render": function ( data, type, row, meta ) {
                            return "<i data-toggle='tooltip' data-placement='top' title='<spring:message code="label.admin.tooltip.active" />' data-id='" + row.id + "' data-value='" + data+ "' class='btn-active fa " + (data ? 'fa-check-circle-o text-success' : 'fa-times-circle-o text-danger') + "'></i>";
                        }
                    }, {
                        "orderable": false,
                        "render": function ( data, type, row, meta ) {
                            return "<a data-toggle='tooltip' data-placement='top' title='<spring:message code="label.admin.tooltip.edit" />' href='<c:url value="/admin/article/" />" + row.id + "'><i class='fa fa-pencil'></i></a>";
                        }
                    }
                ]
            });
            $('#records').on('click', '.btn-active', function(e) {
                $activate(this, '<c:url value="/admin/article/" />' + $(this).data('id') + "/active", {
                    '<c:out value="${_csrf.parameterName}" />': '<c:out value="${_csrf.token}" />'
                }, $(this).data('value'), 'fa-check-circle-o text-success', 'fa-times-circle-o text-danger');
            });
        });
    </script>
</ui:layout>