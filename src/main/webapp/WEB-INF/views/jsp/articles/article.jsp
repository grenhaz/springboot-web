<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="ui" uri="http://www.obarcia.com/tags" %>
<ui:layout title="${model.title}" tag="games" flash="${flash}">
    <div class="section">
        <div class="header"><c:out value="${model.formattedTags}" /></div>
        <div class="article-section">
            <h6 class="header-type"><spring:message code="article.type.${model.type}" /></h6>
            <h2><c:out value="${model.title}" /></h2>
            <h3><c:out value="${model.description}" /></h3>
            <div class="row">
                <div class="col-sm-8">
                    <div>${model.content}</div>
                </div>
                <div class="col-sm-4">
                    <div class="image">
                        <img src="<c:url value="/data/articles/${model.image}" />" />
                    </div>
                </div>
            </div>
        </div>
        
        <!-- ADD COMMENTS -->
        <sec:authorize access="isAuthenticated()">
            <hr />
            <div class="header"><spring:message code="title.addcomment" /></div>
            <div class="form-container">
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
            </div>
        </sec:authorize>
        
        <!-- COMMENTS -->
        <a id="return-top"></a>
        <div class="comments"></div>
    </div>
    <script>
        $(function() {
            $(document).on('submit', '#form-comment', function () {
                if ($.trim($('#form-comment textarea').val()) !== '') {
                    $('.form-container').append(getLoader());
                    $('#form-comment').hide();

                    var data = $(this).serialize();

                    $('#form-comment textarea').val('');

                    $.post('<c:url value="/article/${model.id}" />', data, function (ret) {
                        $('.form-container').html(ret);
                        refreshBlock('.comments', '<c:url value="/ajax" />/comments/${model.id}');
                    }).always(function () {
                        $('.form-container .progress').remove();
                        $('#form-comment').show();
                    });
                }
                
                return false;
            });
            refreshBlock('.comments', '<c:url value="/ajax" />/comments/${model.id}');
        });
    </script>
</ui:layout>