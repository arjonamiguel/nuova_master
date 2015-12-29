<%@page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@taglib uri="http://www.springframework.org/security/tags"  prefix="sec" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- header login -->
<div style="text-align:right;font-size:13px;background-color: #353e4a; padding: 2px 2px; height: 53px; color: white">
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
<nav class="navbar">
<div class="navbar-inner">

<div class="container">

		  <!-- .btn-navbar is used as the toggle for collapsed navbar content -->
      		<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
        		<span class="icon-bar"></span>
       		 	<span class="icon-bar"></span>
        		<span class="icon-bar"></span>
      		</a>
 
	      <!-- Be sure to leave the brand out there if you want it shown -->
	
			
	<!-- Collect the nav links, forms, and other content for toggling -->
	 	<div class="nav-collapse collapse">
	

		 <ul class="nav navbar-nav">
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


		</div><!-- /.navbar-collapse -->
</div><!-- /.container-fluid -->
</div>
</nav>
<sec:authorize access="hasRole('ROLE_USER')">
USER
</sec:authorize>
<sec:authorize access="hasRole('ROLE_RECEPTION')">
RECEPCION
</sec:authorize>
<!-- Menu       -->
