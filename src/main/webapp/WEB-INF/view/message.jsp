<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
${message}
<c:if test="${message==1}">
<div class="alert alert-success">
<button type="button" class="close" data-dismiss="alert">&times;</button>
<b>Perfecto!</b> Los datos fueron guardados correctamente.
</div>
</c:if>