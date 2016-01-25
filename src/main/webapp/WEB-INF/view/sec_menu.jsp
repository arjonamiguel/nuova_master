<%@page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@taglib uri="http://www.springframework.org/security/tags"  prefix="sec" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- header login -->
<div class="navbar navbar-inverse nav">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target="#collapse1">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="">NUOVA</a>
    		
          	<div class="nav-collapse collapse" id="collapse1">S
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
		 		<li class="dropdown">
		            <a href="#" id="configuracion" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-cog"></i>&nbsp;&nbsp;Configuracion<span class="caret"></a>
		            <ul class="dropdown-menu">
		            	<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="/nuova/mainProfesional" id="mainProfesional">Administrar Profesionales</a></li>
						<li><a href="/nuova/formAddProfesional" id="formAddProfesional">Agregar Profesional</a></li>
						<li><a href="/nuova/showReporteProfesionales" id="showReporteProfesionales">Reporte de Profesionales</a></li>
						
						<li class="divider"></li>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="/nuova/mainEspecialidad" id="mainEspecialidad">Administrar Especialidades</a></li>
						<li><a href="/nuova/formAddEspecialidad" id="formAddEspecialidad">Agregar Especialidades</a></li>
						<li><a href="/nuova/showReporteEspecialidades" id="showReporteEspecialidades">Reporte de Especialidades</a></li>
						<li class="divider"></li>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="/nuova/mainObraSocial" id="mainObraSocial">Administrar Obras Sociales</a></li>
						<li><a href="/nuova/formAddObraSocial" id="formAddObraSocial">Agregar Obras Sociales</a></li>
						</sec:authorize>
		            </ul>
				</li>

				
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="dropdown">
				<a href="#" id="mainOrden" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-briefcase"></i>&nbsp;&nbsp;Administracion de Practicas<span class="caret"></span></a>			
			  		<ul class="dropdown-menu" role="menu">
			  			<li><a href="/nuova/mainOrden">Administrar Practicas</a></li>
		                <li><a href="/nuova/formAddOrden">Agregar Practicas</a></li>
              		</ul>                
				</li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="dropdown" >
					<a href="#" id="mainPaciente"  class="dropdown-toggle" data-toggle="dropdown"><i class="icon-file"></i>&nbsp;&nbsp;Administracion de Pacientes</a>
					<ul class="dropdown-menu" role="menu">
		                <li><a href="/nuova/mainPaciente">Administrar Pacientes</a></li>
		                <li><a href="/nuova/formAddPaciente">Agregar Pacientes</a></li>
              		</ul>  
				</li>
				</sec:authorize>
				<li class="dropdown" >
				<a href="#" id="reportes"  class="dropdown-toggle" data-toggle="dropdown"><i class="icon-th-list"></i>&nbsp;&nbsp;Reportes</a>
				<ul class="dropdown-menu" role="menu">
		                <li><a href="#">Action</a></li>
		                <li><a href="#">Another action</a></li>
		                <li><a href="#">Something else here</a></li>
		                <li class="divider"></li>
		                <li><a href="#">Separated link</a></li>
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
<script>
$(document).ready(function(){
    $(".dropdown").hover(            
        function() {
            $('.dropdown-menu', this).not('.in .dropdown-menu').stop( true, false ).slideDown("slow");
            $(this).toggleClass('open');        
        },
        function() {
            $('.dropdown-menu', this).not('.in .dropdown-menu').stop( true, true ).slideUp("slow");
            $(this).toggleClass('open');       
        }
    );
});
</script>