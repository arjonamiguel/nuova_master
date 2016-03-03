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
            <a class="brand" href=""><b style="font-size: 30px">NUOVA</b></a>
    		
          	<div class="nav-collapse collapse" id="collapse1">
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
		 		<li class="dropdown">
		            <a href="#" id="configuracion" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-cog"></i>&nbsp;&nbsp;Configuracion<span class="caret"></a>
		            <ul class="dropdown-menu">
		            	
						<li><a href="/nuova/mainProfesional" id="mainProfesional">Administrar Profesionales</a></li>
						<li><a href="/nuova/formAddProfesional" id="formAddProfesional">Agregar Profesional</a></li>
						<li><a href="/nuova/showReporteProfesionales" id="showReporteProfesionales">Reporte de Profesionales</a></li>
						
						<li class="divider"></li>
						
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="/nuova/mainEspecialidad" id="mainEspecialidad">Administrar Especialidades</a></li>
						<li><a href="/nuova/formAddEspecialidad" id="formAddEspecialidad">Agregar Especialidades</a></li>
						<li><a href="/nuova/showReporteEspecialidades" id="showReporteEspecialidades">Reporte de Especialidades</a></li>
						<li class="divider"></li>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="/nuova/mainObraSocial" id="mainObraSocial">Administrar Obras Sociales</a></li>
						<li><a href="/nuova/formAddObraSocial" id="formAddObraSocial">Agregar Obras Sociales</a></li>
						<li class="divider"></li>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="/nuova/mainPrestador" id="mainPrestador">Administrar Prestadores</a></li>
						<li><a href="/nuova/formAddPrestador" id="formAddPrestador">Agregar Prestadores</a></li>
						<li><a href="/nuova/showReportePrestadores" id="showReportePrestador">Reporte de Prestadores</a></li>
						<li class="divider"></li>						
						</sec:authorize>						
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="/nuova/mainPractica" id="mainPractica">Administrar Nomenclador</a></li>
						<li><a href="/nuova/formAddPractica" id="formAddPractica">Agregar Practica</a></li>
						<li><a href="/nuova/showReportePractica" id="showReportePractica">Reporte Nomenclador</a></li>
						</sec:authorize>
		            </ul>
				</li>
				</sec:authorize>
				
				<sec:authorize access="hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')">
				<li class="dropdown">
				<a href="#" id="mainOrden" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-briefcase"></i>&nbsp;&nbsp;Administracion de Ordenes<span class="caret"></span></a>			
			  		<ul class="dropdown-menu" role="menu">
			  			<li><a href="/nuova/mainConsulta">Administrar Consultas</a></li>
			  			<li><a href="/nuova/mainOrdenPractica">Administrar Practicas</a></li>
						<li><a href="/nuova/mainConsultaOdontologica">Administrar Practicas Odontologicas</a></li>						
              		</ul>                
				</li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')">
				<li class="dropdown" >
					<a href="#" id="mainPaciente"  class="dropdown-toggle" data-toggle="dropdown"><i class="icon-file"></i>&nbsp;&nbsp;Administracion de Pacientes<span class="caret"></a>
					<ul class="dropdown-menu" role="menu">
		                <li><a href="/nuova/mainPaciente">Administrar Pacientes</a></li>
		                <li><a href="/nuova/formAddPaciente">Agregar Pacientes</a></li>
		                <li><a href="/nuova/showReportePacientes">Reporte de Pacientes</a></li>
              		</ul>  
				</li>
				</sec:authorize>
				
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="dropdown" >
				<a href="#" id="calendario"  class="dropdown-toggle" data-toggle="dropdown"><i class="icon-calendar"></i>&nbsp;&nbsp;Calendario<span class="caret"></a>
				<ul class="dropdown-menu" role="menu">
		                <li><a href="/nuova/mainCalendario">Visualizar Calendario</a></li>		               
              	</ul>
				</li>
				</sec:authorize>
				
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="dropdown" >
				<a href="#" id="caja"  class="dropdown-toggle" data-toggle="dropdown"><i class="icon-list-alt"></i>&nbsp;&nbsp;Caja<span class="caret"></a>
				<ul class="dropdown-menu" role="menu">
		                <li><a href="/nuova/mainCaja/null">Administracion de Caja</a></li>		               
              	</ul>
				</li>
				</sec:authorize>
				
				
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="dropdown" >
				<a href="#" id="reportes"  class="dropdown-toggle" data-toggle="dropdown"><i class="icon-th-list"></i>&nbsp;&nbsp;Reportes<span class="caret"></a>
				<ul class="dropdown-menu" role="menu">
		                <li><a href="#">Reporte 1</a></li>
		                <li><a href="#">Reporte 2</a></li>
		                <li><a href="#">Reporte 3</a></li>
		                <li class="divider"></li>
		                <li><a href="#">Reporte 4</a></li>
              	</ul>
				</li>
				</sec:authorize>
		</ul>


		</div><!-- /.navbar-collapse -->
</div><!-- /.container-fluid -->
</div>
</nav>
<%-- <sec:authorize access="hasRole('ROLE_USER')"> --%>
<!-- USER -->
<%-- </sec:authorize> --%>
<%-- <sec:authorize access="hasRole('ROLE_RECEPTION')"> --%>
<!-- RECEPCION -->
<%-- </sec:authorize> --%>
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