<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" session="true"
	contentType="text/html;charset=latin1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Nuova</title>
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/resources/img/favicon/favicon.ico">

<link
	href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap.min.css"
	rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery/bootstrap-collapse.js" /></script>
<link href="<%=request.getContextPath()%>/resources/css/nuova.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/panel.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap-responsive.css"
	rel="stylesheet" />

<!-- 	Configuracion del paginador -->
<link
	href="<%=request.getContextPath()%>/resources/simplepaginggrid/css/simplePagingGrid-0.4.css"
	rel="stylesheet">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/simplepaginggrid/examples/pageNumbers/script/handlebars-1.0.rc.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/simplepaginggrid/script/simplePagingGrid-0.5.0.2.js"></script>
<style type="text/css">
#myModal 
{
    width: 700px; 
    margin-top: -300px !important;
    margin-left:  -350px !important; 
} 

#myModal .modal-body {
    height: 300px;
}
</style>

</head>
<body style="background-color: #e5e5e5;">
	<jsp:include page="../sec_menu.jsp"></jsp:include>

	<div class="mainContainer">
		<div class="panelContainer">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">
						<b>Monitor de Reportes</b>

					</div>
				</div>
				<div class="panel-body">
					<div class="container-fluid">
						<div class="row-fluid">
							<!-- Reporte de Pacientes -->
							<div class="span4">
								<div class="mainContainer">
									<div class="panelContainer">
										<div class="panel panel-info">
											<div class="panel-heading">
												<div class="panel-title">Paciente Afiliado de O.S.P.S.I.P</div>
											</div>
											<div class="panel-body">
											<ul>
												<li><a href="#" id="lnk_filtro_afiliados">Filtrar Afiliados.</a> </li>
												<li><a href="#" id="lnk_afiliados_atendidos">Cantidad de Afiliados Atendidos.</a> </li>
												<li><a href="#" id="lnk_pacientes_registrados">Total de Pacientes Registrados.</a></li>
												<li><a href="#" id="lnk_afiliados_sincoseguro">Total de Pacientes Sin Coseguro.</a></li>												
												<li><a href="#" id="lnk_paciente_fueracobertura">Pacientes Fuera de cobertura (Mayores de 21 años).</a></li>
												<li><a href="#" id="lnk_afiliados_filtrados_nomenclador">Total de Pacientes Filtrados por Nomenclador.</a></li>
												
											</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<!-- Reporte de Ordenes -->
							<div class="span4">
								<div class="mainContainer">
									<div class="panelContainer">
										<div class="panel panel-info">
											<div class="panel-heading">
												<div class="panel-title">ORDENES (Consultas y Practicas)</div>
											</div>
											<div class="panel-body">
											<ul>
												<li><a href="#" id="lnk_cantidad_ordenes_por_tipo_fecha">Cantidad de Ordenes por Tipo y Fecha.</a></li>
												
											</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						
							<!-- Reporte de Caja -->
							<div class="span4">
								<div class="mainContainer">
									<div class="panelContainer">
										<div class="panel panel-info">
											<div class="panel-heading">
												<div class="panel-title">Movimiento de Caja</div>
											</div>
											<div class="panel-body">
											<ul>
												<li><a href="#" onclick="">Listado de Caja.</a></li>
												<li><a href="#" onclick="">Listado de Cierre de Caja.</a></li>
												
											</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

</body>

<jsp:include page="modalOrdenesPorTipoYFecha.jsp"></jsp:include>
<jsp:include page="modalAfiliadosAtendidos.jsp"></jsp:include>
<jsp:include page="modalFiltroAfiliados.jsp"></jsp:include>
<jsp:include page="modalPacientesRegistrados.jsp"></jsp:include>

</html>

<script type="text/javascript">
document.getElementById("reportes").parentNode.classList.add("active");

function updatefechaDesdeAA(){
	document.getElementById("fechaDesdeAA").value=document.getElementById("fecha_desde_aa").value;
}

function updatefechaHastaAA(){
	document.getElementById("fechaHastaAA").value=document.getElementById("fecha_hasta_aa").value;
}

function updatefechaDesdePR(){
	document.getElementById("fechaDesdePR").value=document.getElementById("fecha_desde_pr").value;
}

function updatefechaHastaPR(){
	document.getElementById("fechaHastaPR").value=document.getElementById("fecha_hasta_pr").value;
}

function updatefechaDesdeAfiliado(){
	document.getElementById("fechaDesdeAfiliado").value=document.getElementById("fecha_desde_afiliado").value;
}

function updatefechaHastaAfiliado(){
	document.getElementById("fechaHastaAfiliado").value=document.getElementById("fecha_hasta_afiliado").value;
}

function updatefechaNacimiento(){
	document.getElementById("fechaNacimiento").value=document.getElementById("fecha_nacimiento").value;
}

function updatefechaNacimiento(){
	document.getElementById("fechaNacimiento").value=document.getElementById("fecha_nacimiento").value;
}

function updatefechaDesdeOrden(){
	document.getElementById("fechaDesdeOrden").value=document.getElementById("fecha_desde_orden").value;
}

function updatefechaHastaOrden(){
	document.getElementById("fechaHastaOrden").value=document.getElementById("fecha_hasta_orden").value;
}
//----------------------------------------------------------------------------

//Actions Modal

$(function() {
	  $('#lnk_cantidad_ordenes_por_tipo_fecha').click(function() {
		  $("#modal_ordenes_por_tipo_fecha").css("visibility", "visible");
	
		  document.getElementById("fecha_desde_orden").value="";
		  document.getElementById("fecha_hasta_orden").value="";
		
	    $('#modal_ordenes_por_tipo_fecha').modal('show');
	  });
	  
	  $('#btn_modal_ordenes_por_tipo_fecha_aceptar').click(function() {
		  var orden = document.getElementById("tipo_orden").value;
		  var fechaDesdeOrden = document.getElementById("fecha_desde_orden").value;
		  var fechaHastaOrden = document.getElementById("fecha_hasta_orden").value;	 
	    
		resp = callReportOrdenesPorTipoYFecha(orden, fechaDesdeOrden, fechaHastaOrden);
 	    $('#modal_ordenes_por_tipo_fecha').modal('hide');
	  });
	});



$(function() {
	  $('#lnk_filtro_afiliados').click(function() {
		  $("#filtroafiliados").css("visibility", "visible");
		  // $("#msj_afiliadoatendido").css("visibility","hidden");
		  document.getElementById("fechaDesdeAfiliado").value="";
		  document.getElementById("fecha_desde_afiliado").value="";
		  document.getElementById("fechaHastaAfiliado").value="";
		  document.getElementById("fecha_hasta_afiliado").value="";
		  document.getElementById("fechaNacimiento").value="";
		  document.getElementById("fecha_nacimiento").value="";
		  document.getElementById("localidadId").value="";
		  document.getElementById("localidadString").value="";
		  
	    $('#filtroafiliados').modal('show');
	  });
	  
	  $('#btnAceptarFiltro').click(function() {
		  var fechaDesdeAfiliado = document.getElementById("fechaDesdeAfiliado").value;
		  var fechaHastaAfiliado = document.getElementById("fechaHastaAfiliado").value;
		  var fechaNacimiento = document.getElementById("fechaNacimiento").value;
		  var localidadId = document.getElementById("localidadId").value;
		  var zonaAfiliacion = document.getElementById("zonaAfiliacion").value;
		  
	    
		resp = callReportFiltroAfiliado(fechaDesdeAfiliado, fechaHastaAfiliado, fechaNacimiento, localidadId, zonaAfiliacion );
 	    $('#filtroafiliados').modal('hide');
	  });
	});


$(function() {
	  $('#lnk_afiliados_atendidos').click(function() {
		  $('#afiliadosAtendidos').css("visibility", "visible");
		  $("#msj_afiliadoatendido").css("visibility","hidden");
		  document.getElementById("especialidad").value="";
		  document.getElementById("especialidadString").value="";
		  
	    $('#afiliadosAtendidos').modal('show');
	  });
	  
	  $('#btnSave').click(function() {
	    var fechaDesdeAA = document.getElementById("fechaDesdeAA").value;
	    var fechaHastaAA = document.getElementById("fechaHastaAA").value;
	    var especialidad = document.getElementById("especialidad").value;

	    if (especialidad == "") {
	    	especialidad = 0;
	    }
	    
	    if (fechaDesdeAA == "" || fechaHastaAA == "") {
            $("#msj_afiliadoatendido").css("visibility","visible");

	    	return false;
	    }
	    
	    callReportAfiliadosAtendidos(fechaDesdeAA, fechaHastaAA, especialidad);
	    $('#afiliadosAtendidos').modal('hide');
	  });
	});

$(function() {
	  $('#lnk_pacientes_registrados').click(function() {
		  $('#pacienteregistrado').css("visibility", "visible");
		  $("#msj_pacienteregistrado").css("visibility","hidden");
	    $('#pacienteregistrado').modal('show');
	  });
	  
	  $('#btnSavePR').click(function() {
	    var fechaDesdePR = document.getElementById("fechaDesdePR").value;
	    var fechaHastaPR = document.getElementById("fechaHastaPR").value;

	    if (fechaDesdePR == "" || fechaHastaPR == "") {
          $("#msj_pacienteregistrado").css("visibility","visible");
	    	return false;
	    }
	    
	    callReportPacienteRegistrado(fechaDesdePR, fechaHastaPR);
	    $('#pacienteregistrado').modal('hide');
	  });
	});

$(function() {
	  $('#lnk_afiliados_sincoseguro').click(function() {
		  callReportAfiliadosSinCoseguro();			    
	  });
	});

$(function() {
	  $('#lnk_paciente_fueracobertura').click(function() {
		  callReportAfiliadosSinCobertura();			    
	  });
	});


// ----------------------------------------------------------------------------
// Autocomplete Especialidades
$(document).ready(function() {
	var especialidadTH = $('#especialidadString.typeahead');
	var localidadTH = $('#localidadString.typeahead');
	
	var map = new Object();
	var mapLoc = new Object();

	var objects = [];
	var objectsLoc = [];

	especialidadTH.typeahead({
		source : function(query, process) {
			$.ajax({
				url : '/nuova/ajaxGetAutoCompleteEspecialidades',
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
				}
			});
		},
		updater : function(item) {
			$('#especialidad').val(map[item].id);
			return item;
		}
	});


	localidadTH.typeahead({
		source : function(query, process) {
			$.ajax({
				url : '/nuova/ajaxGetAutoCompleteLocalidades',
				type : 'POST',
				dataType : 'JSON',
				data : 'query=' + query,
				success : function(data) {
					console.log(data);
					$.each(data, function(i, object) {
						mapLoc[object.value] = object;
						if (objectsLoc[i] == null) {
							objectsLoc.push(object.value);
						}
					});
					process(objectsLoc);
					objectsLoc = [];
				}
			});
		},
		updater : function(item) {
			$('#localidadId').val(mapLoc[item].id);
			return item;
		}
	});
	
});

//----------------------------------------------------------------------------
//Call Ajax Reports

function callReportAfiliadosAtendidos(fd, fh, esp) {
	var url = "/nuova/ajaxGetReportAfiliadosAtendidos?fd="+fd+"&fh="+fh+"&esp="+esp;
	window.open(url, '_blank');
}

function callReportPacienteRegistrado(fd, fh) {
	var url = "/nuova/ajaxGetReportPacienteRegistrado?fd="+fd+"&fh="+fh;
	window.open(url, '_blank');
}

function callReportAfiliadosSinCoseguro() {
	var url = "/nuova/ajaxGetReportAfiliadosSinCoseguro";
	window.open(url, '_blank');
}

function callReportAfiliadosSinCobertura() {
	var url = "/nuova/ajaxGetReportAfiliadosSinCobertura";
	window.open(url, '_blank');
}

function callReportFiltroAfiliado(fechaDesdeAfiliado, fechaHastaAfiliado
		, fechaNacimiento, localidadId, zonaAfiliacion ) {
	var parameters = "fechaDesdeAfiliado=" + fechaDesdeAfiliado 
	+ "&fechaHastaAfiliado=" + fechaHastaAfiliado 
	+ "&fechaNacimiento=" + fechaNacimiento 
	+ "&localidadId=" + localidadId
	+ "&zonaAfiliacion="+zonaAfiliacion;
	
	var url = "/nuova/ajaxGetReportFiltroAfiliado?"+parameters;
	window.open(url, '_blank');
}

function callReportOrdenesPorTipoYFecha(tipoOrden, fechaDesde, fechaHasta) {
	var parameters = "tipoOrden="+tipoOrden+"&fechaDesdeOrden="+fechaDesde+"&fechaHastaOrden="+fechaHasta
	var url = "/nuova/ajaxGetReportOrdenesPorTipoYFecha?"+parameters;
	window.open(url, '_blank');
}

</script>