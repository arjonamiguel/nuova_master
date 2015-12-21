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
<div>
<div class="navbar">
<div class="navbar-inner">
<div class="container">
<div class="nav-collapse">
 <ul class="nav">
<sec:authorize access="hasRole('ROLE_ADMIN')">
<li><a href="mainProfesional" id="mainProfesional">Administracion de Profesionales</a></li>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<li><a href="mainEspecialidad" id="mainEspecialidad">Administracion de Especialidades</a></li>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<li>
<a href="/nuova/mainObraSocial" id="mainObraSocial">Administracion de Obras Sociales</a>
</li>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<li>
<a href="/nuova/mainPaciente">Administracion de Pacientes</a>
</li>
</sec:authorize>

<li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><img style="height:7%;width:7%;" src="<c:url value="/resources/img/others/conf.ico"/>"><b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="#">Action</a></li>
              <li><a href="#">Another action</a></li>
              <li><a href="#">Something else here</a></li>
              <li class="divider"></li>
              <li class="nav-header">Nav header</li>
              <li><a href="#">Separated link</a></li>
              <li><a href="#">One more separated link</a></li>
            </ul>
          </li>
</ul>
</div>
</div>
</div>
</div>
</div>




<sec:authorize access="hasRole('ROLE_USER')">
USER
</sec:authorize>
<sec:authorize access="hasRole('ROLE_RECEPTION')">
RECEPCION
</sec:authorize>
<!-- Menu       -->
