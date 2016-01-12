<%@page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@taglib uri="http://www.springframework.org/security/tags"  prefix="sec" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- header login -->
<div class="navbar navbar-inverse nav">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="">NUOVA</a>
    		
          	<div class="nav-collapse collapse">
              <div class="pull-right">
                <ul class="nav pull-right">
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-user"></i>&nbsp;&nbsp;Logueado como, <sec:authorize access="isAuthenticated()">
	    <span>
	    	<%=SecurityContextHolder.getContext().getAuthentication().getName()%>
	    </span> 
	</sec:authorize> <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href=""><i class="icon-cog"></i> Preferencias</a></li>
                            <li><a href=""><i class="icon-envelope"></i> Contacto Soporte</a></li>
                            <li class="divider"></li>
                            <li><a href="<c:url value='j_spring_security_logout'/>"><i class="icon-off"></i> Salir</a></li>
                        </ul>
                    </li>
                </ul>
              </div>
            </div>
        </div>
    </div>
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
		<li>
		<a href="/nuova/mainOrden" id="mainOrden">Administracion de Practicas</a>
		</li>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
		<li><a href="/nuova/mainPaciente" id="mainPaciente">Administracion de Pacientes</a></li>
		</sec:authorize>
		<li>
		<a href="" id="reportes">Reportes</a>
		</li>
		<li class="dropdown" style="width:30%;">
		            <a href="#" id="configuracion" class="dropdown-toggle" data-toggle="dropdown"><img style="height:7%;width:7%;" src="<c:url value="/resources/img/others/conf.ico"/>">&nbsp;&nbsp;&nbsp;&nbsp;Configuracion</a>
		            <ul class="dropdown-menu">
		            	<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="mainProfesional" id="mainProfesional">Administracion de Profesionales</a></li>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="mainEspecialidad" id="mainEspecialidad">Administracion de Especialidades</a></li>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="/nuova/mainObraSocial" id="mainObraSocial">Administracion de Obras Sociales</a></li>
						</sec:authorize>
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
