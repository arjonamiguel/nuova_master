<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"  prefix="sec" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/img/favicon/favicon.ico">
    
	
	    <title>Nuova</title>
        <link href="<%=request.getContextPath()%>/resources/css/pcan43e1.css" rel="stylesheet"/>
    
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap-responsive.css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/resources/css/panel.css" rel="stylesheet"/>
        
		<script src="<%=request.getContextPath()%>/resources/js/jquery/jquery-2.0.3.min.js" /></script>
		<script src="<%=request.getContextPath()%>/resources/js/bootstrap/bootstrap.min.js" /></script>
		<script src="<%=request.getContextPath()%>/resources/js/jquery/bootstrap-collapse.js" /></script>
		<script src="<%=request.getContextPath()%>/resources/js/autocomplete/bootstrap-typeahead.js" /></script>
		<link href="<%=request.getContextPath()%>/resources/css/nuova.css" rel="stylesheet"/>
		
		<!-- 	Configuracion del paginador -->
		<link href="<%=request.getContextPath()%>/resources/simplepaginggrid/css/simplePagingGrid-0.4.css" rel="stylesheet">
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/simplepaginggrid/examples/pageNumbers/script/handlebars-1.0.rc.1.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/resources/simplepaginggrid/script/simplePagingGrid-0.5.0.2.js"></script>

<style type="text/css">
.navbar-brand-centered {
	position: absolute;
	left: 50%;
	display: block;
	width: 160px;
	text-align: center;
	background-color: transparent;
}

.navbar>.container .navbar-brand-centered, .navbar>.container-fluid .navbar-brand-centered {
	margin-left: -80px;
}

</style>

<script type="text/javascript">
	$(document).ready(function() {
		var map = new Object();
		var objects = [];

		$('input.typeahead').typeahead({
			source : function(query, process) {
				$.ajax({
					url : '/nuova/ajaxGetAutoCompletePacientes',
					type : 'POST',
					dataType : 'JSON',
					data : 'query=' + query,
					success : function(data) {
						console.log(data);
						$.each(data, function(i, object) {
							map[object.value] = object;
							if (objects[i] == null) {
								objects.push(object.value);
							}
						});
						process(objects);
						objects = [];
						$('#pacienteId').val("");
					}
				});
			},
			updater : function(item) {
				$('#pacienteId').val(map[item].id);
				return item;
			}
		});
	});
	
	
function goInfoPaciente() {
	var pacienteId = document.getElementById("pacienteId").value;
	if (pacienteId == "") {
		alert("Usted debe seleccionar un paciente.");
		document.getElementById("searchPaciente").focus();
		
	}else {
		window.location.href = "/nuova/formInfoPaciente/"+pacienteId;
	}
}	
</script>

</head>

<body style="background-color: #e5e5e5;">
	<jsp:include page="../sec_menu.jsp"></jsp:include>
	<div class="mainContainer">
		<div class="panelContainer">
			<div>
				<div id="ContainerGeneralOverWrite_MainContainer">
					<div id="MainContainer" >

						<div class="home_ac_v2">

							<div class="bg_tooltips"></div>
							

							<div class="block_cand_offers">
								<section class="user_info">
								<div>
									<div class="img">
										<input type="file"
											name="ctl00$ContainerGeneralOverWrite$ContainerGeneral$uploadpic"
											id="ContainerGeneralOverWrite_ContainerGeneral_uploadpic"
											style="display: none"> <a href="#" id="uploadImage">
											<div class="img_cand icon men" id="up-img">
											<img src="/nuova/resources/img/others/android-icon-72x72.png" height="20%" width="20%">											
											</div>
										</a>
										<div class="cv_completed box_blue">
											<div class="cv_100">
												<span class="cv_user_complete" style="width: 80px;"></span>
											</div>
											<span class="txt"></span>

										</div>
									</div>
									<div class="info">
										<h1>${usuario.username}</h1>
										<h2></h2>
										<a href="http://www.nuovamed.com">Visitanos en: www.nuovamed.com</a> <a href="http://www.nuovamed.com"
											class="block_link"> <span class="icon clip"></span> <span>Bienvenido
												a SYSNUOVA</span>
										</a>
									</div>
								</div>
								</section>
								<div>

									<div class="buscador buscador_mvl">
										<h2>B&uacute;squeda de Pacientes</h2>

										<div class="">										
										<input type="hidden" name="pacienteId" id="pacienteId" value="">										
											<input
												data-provide="typeahead" 
												class="typeahead"
												name="searchPaciente"
												type="text"
												id="searchPaciente"
												placeholder="Ingrese DNI o el Apellido ..."
												autocomplete="off"
												style="height: 40px; width: 50%">
										</div>

										<button id="breadButton" href="" 
										class="btn btn-info" 
										style="margin-top: 2px; height: 40px"
										onclick="goInfoPaciente()">
										<i class="ico icon-user">
										</i>&nbsp;Buscar Paciente
										</button>

									</div>

								</div>
								<sec:authorize access="hasRole('ROLE_ADMIN')">
								<div class="offers_insc">
									<h3>Monitor de Pr&aacute;cticas Realizadas</h3>
									<ul>
										<c:forEach items="${alarmas}" var="al">
											<li class="offers_apl"><span><b>(${al.cantidad})</b>
													${al.descripcion}</span></li>
										</c:forEach>
									</ul>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
									<a href="/nuova/mainOrdenPractica">Ir al Administrador Ordenes de Pr&aacute;cticas</a>
									</sec:authorize>
								</div>
								</sec:authorize>
							</div>

							<div class="block_companies_courses">
								<div>
<!-- 									onkeypress="javascript:return WebForm_FireDefaultButton(event, 'ContainerGeneralOverWrite_ContainerGeneral_btnSearchOffers1')"> -->

									<div class="buscador">
										<h2>B&uacute;squeda de Pacientes</h2>
										<div class="">
										<input type="hidden" name="pacienteId" id="pacienteId" value="">
											<span class="icon palabra_clave"></span> 
												<input
												data-provide="typeahead" 
												name="searchPaciente"
												type="text"
												id="searchPaciente"
												data-su="/ofertas-de-trabajo/"
												class="js-cargos-suggest typeahead"
												placeholder="Ingrese DNI o el Apellido ..."
												style="height: 40px; width: 50%"
												autocomplete="off">
										</div>
										
										<button id="breadButton" href="" 
										class="btn btn-info" 
										style="margin-top: 2px; height: 40px"
										onclick="goInfoPaciente()">
										<i class="ico icon-user">
										</i>&nbsp;Buscar Paciente
										</button>

									</div>

								</div>
								
								<sec:authorize access="hasRole('ROLE_ADMIN')">
								<div class="offers_suggest">
									<h3>Historial de Registros efectuados hasta el dia:</h3>

									<ul>
										<sec:authorize access="hasRole('ROLE_ADMIN')">										
										<li class="devclick cp"><a href="/nuova/mainPaciente">
												<b>(${cantPaciente})</b> - Pacientes Registrados en Nuova.
										</a> <span> <a href="/nuova/showReportePacientes">
										<img src="/nuova/resources/img/others/export_excel_32x32.png"></a>
										</span></li>
										</sec:authorize>
										
										<sec:authorize access="hasRole('ROLE_USER') OR hasRole('ROLE_USER_CONF')">										
										<li class="devclick cp">
												<b>(${cantPaciente})</b> - Pacientes Registrados en Nuova.
										</li>
										</sec:authorize>
										
										<sec:authorize access="hasRole('ROLE_ADMIN')">
										<li class="devclick cp"><a href="/nuova/mainProfesional">
												<b>(${cantProfesional})</b> - Profesionales Registrados.
										</a> <span> <a href="/nuova/showReporteProfesionales">
										<img src="/nuova/resources/img/others/export_excel_32x32.png"></a>
										</span></li>
										</sec:authorize>
										
										<sec:authorize access="hasRole('ROLE_ADMIN')">
										<li class="devclick cp"><a href="/nuova/mainEspecialidad">
												<b>(${cantEspecialidad})</b> - Especialidades en Curso.
										</a> <span> <a href="/nuova/showReporteEspecialidades">
										<img src="/nuova/resources/img/others/export_excel_32x32.png"></a>
										</span></li>
										</sec:authorize>
										
										<sec:authorize access="hasRole('ROLE_ADMIN')">
										<li class="devclick cp"><a href="/nuova/mainObraSocial">
												<b>(${cantObrasocial})</b> - Obras Sociales Registradas.
										</a> <span> <a href="/nuova/showReportesObraSociales">
										<img src="/nuova/resources/img/others/export_excel_32x32.png"></a>
										</span></li>
										</sec:authorize>

									</ul>
									
								</div>
								</sec:authorize>

							</div>
						</div>
					</div>

				</div>
			</div>
		</div>


	</div>
</body>
</html>
<script>
document.getElementById("home").parentNode.classList.add("active");
</script>
