<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="avatars">
    <c:forEach var="avatar" items="${avatars}">
        <img class="clickable" src="<c:url value="/data/avatars/${avatar}" />" onclick="selectAvatar('${avatar}');" />
    </c:forEach>
</div>
<script>
    function selectAvatar(avatar) {
        $('*[name=\'${field}\']').val(avatar);
        $('.${field}-img').attr('src', "<c:url value="/data/avatars/" />" + avatar);
        
        $('.avatars-modal').modal('hide');
    }
</script>