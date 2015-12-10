<%@page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@taglib uri="http://www.springframework.org/security/tags"  prefix="sec" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- header login -->
<div style="text-align:right;font-size:13px">
	Logueado como: 
	<sec:authorize access="isAuthenticated()">
	    <span style="color:blue;font-weight:bold;">
	    	<%=SecurityContextHolder.getContext().getAuthentication().getName()%>
	    </span> 
	    |<a href="#">Mi Perfil</a>
	</sec:authorize>|
	<span><a href="<c:url value='j_spring_security_logout'/>">Salir</a></span>
</div>
<!-- header login -->

<!-- Menu       -->
<div style="background-color: orange">
<sec:authorize access="hasRole('ROLE_ADMIN')">
<a href="mainProfesional">Administracion de Profesionales</a>
</sec:authorize>
|
<sec:authorize access="hasRole('ROLE_ADMIN')">
<a href="mainEspecialidad">Administracion de Especialidades</a>
</sec:authorize>
|
<sec:authorize access="hasRole('ROLE_ADMIN')">
<a href="mainEspecialidad">Administracion de Pacientes</a>
</sec:authorize>
</div>

<sec:authorize access="hasRole('ROLE_USER')">
USER
</sec:authorize>
<sec:authorize access="hasRole('ROLE_RECEPTION')">
RECEPCION
</sec:authorize>
<!-- Menu       -->