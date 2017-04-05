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


<script
	src="<%=request.getContextPath()%>/resources/js/amcharts/custom.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/amcharts/amcharts.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/amcharts/serial.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/amcharts/pie.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/amcharts/export.min.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/export.css"
	rel="stylesheet">
<script
	src="<%=request.getContextPath()%>/resources/js/amcharts/light.js"></script>






<style type="text/css">
#chartdiv {
	width: 100%;
	height: 500px;
}

#chartdiv2 {
	width: 100%;
	height: 500px;
}

#myModal {
	width: 700px;
	margin-top: -300px !important;
	margin-left: -350px !important;
}

#myModal .modal-body {
	height: 300px;
}

.amcharts-pie-slice {
	transform: scale(1);
	transform-origin: 50% 50%;
	transition-duration: 0.3s;
	transition: all .3s ease-out;
	-webkit-transition: all .3s ease-out;
	-moz-transition: all .3s ease-out;
	-o-transition: all .3s ease-out;
	cursor: pointer;
	box-shadow: 0 0 30px 0 #000;
}

.amcharts-pie-slice:hover {
	transform: scale(1.1);
	filter: url(#shadow);
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
							<div class="span3">
								<div class="mainContainer">
									<div class="panelContainer">
										<div class="panel panel-info">
											<div class="panel-heading">
												<div class="panel-title">Paciente Afiliado de
													O.S.P.S.I.P</div>
											</div>
											<div class="panel-body">
												<ul>
													<li><a href="#" id="lnk_filtro_afiliados">Filtrar
															Afiliados.</a></li>
													<li><a href="#" id="lnk_afiliados_atendidos">Cantidad
															de Afiliados Atendidos por Especialidad y Profesional.</a></li>
													<li><a href="#" id="lnk_afiliados_atendidos">Cantidad
															de Afiliados Atendidos por Especialidad y Prestador.</a></li>		
													<li><a href="#" id="lnk_pacientes_registrados">Total
															de Pacientes Registrados.</a></li>
													<li><a href="#" id="lnk_afiliados_sincoseguro">Total
															de Pacientes Sin Coseguro.</a></li>
													<li><a href="#" id="lnk_paciente_fueracobertura">Pacientes
															Fuera de cobertura (Mayores de 21 a�os).</a></li>
													<li><a href="#"
														id="lnk_afiliados_filtrados_nomenclador">Total de
															Pacientes Filtrados por Nomenclador.</a></li>

												</ul>
											</div>
										</div>
									</div>
								</div>

								<div class="mainContainer">
									<div class="panelContainer">
										<div class="panel panel-info">
											<div class="panel-heading">
												<div class="panel-title">ORDENES (Consultas y
													Practicas)</div>
											</div>
											<div class="panel-body">
												<ul>
													<li><a href="#"
														id="lnk_cantidad_ordenes_por_tipo_fecha">Cantidad de
															Ordenes por Tipo y Fecha.</a></li>

												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="span9">
								<div class="mainContainer">
									<div class="panelContainer">
										<div class="panel panel-info">
											<div class="panel-heading">
												<div class="panel-title">Estadisticas</div>
											</div>
											<div class="panel-body">
												<div>
													<div class="row-fluid" style="color: rgb(132, 183, 97)">
														<div class="span4">
															<div class="formLabel">
																<label>Grafico</label>
															</div>
															<div class="formInput">
																<select id="grafico" style="width: 150px">
																	<option value="1">Circular</option>
																	<option value="2">Lineal</option>
																</select>
															</div>
														</div>
														<div class="span4">
															<div class="formLabel">
																<label>Tipo</label>
															</div>
															<div class="formInput">
																<select id="tipo" style="width: 150px">
																	<option value="1">Ordenes</option>
																	<option value="2">Pacientes</option>
																	<option value="3">Nomenclador</option>
																	<option value="2">Caja</option>
																</select>
															</div>
														</div>
														<div class="span3">
															<div class="formLabel">
																<label>Filtro</label>
															</div>
															<div class="formInput">
																<select id="tipo_filtro" style="width: 200px">
																	<option value="-1">Seleccione ...</option>

																</select>
															</div>
														</div>

													</div>
													<div class="row-fluid">
														<div class="span4">
															<div class="formLabel">
																<label style="color: #31708f">Desde</label>
															</div>
															<div class="formInput">
																<input id="fechaDesdeFiltro" class="date" type="hidden" />
																<div id="calendar">
																	<div class="input-group registration-date-time"
																		style="padding-top: 0%; width: 101%;">
																		<input class="form-control" name="fecha_desde_filtro"
																			id="fecha_desde_filtro" type="date" 
																			onchange="javascript:updateFechaDesdeFiltro();">
																	</div>
																</div>
															</div>
														</div>
														<div class="span4">
															<div class="formLabel">
																<label style="color: #31708f">Hasta</label>
															</div>
															<div class="formInput">
																<input id="fechaHastaFiltro" class="date" type="hidden" />
																<div id="calendar">
																	<div class="input-group registration-date-time"
																		style="padding-top: 0%; width: 101%;">
																		<input class="form-control" name="fecha-hasta-filtro"
																			id="fecha-hasta-filtro" type="date"
																			onchange="javascript:updateFechaHastaFiltro();">
																	</div>
																</div>
															</div>
														</div>
														
														<div class="span3">															
															<div class="formInput">
																<button type="button" id="btn_filtrar" class="btn btn-success">Filtrar</button>
															</div>
														</div>
														
													</div>

												</div>
												<div id="chartdiv"></div>
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

	function updatefechaDesdeAA() {
		document.getElementById("fechaDesdeAA").value = document
				.getElementById("fecha_desde_aa").value;
	}

	function updatefechaHastaAA() {
		document.getElementById("fechaHastaAA").value = document
				.getElementById("fecha_hasta_aa").value;
	}

	function updatefechaDesdePR() {
		document.getElementById("fechaDesdePR").value = document
				.getElementById("fecha_desde_pr").value;
	}

	function updatefechaHastaPR() {
		document.getElementById("fechaHastaPR").value = document
				.getElementById("fecha_hasta_pr").value;
	}

	function updatefechaDesdeAfiliado() {
		document.getElementById("fechaDesdeAfiliado").value = document
				.getElementById("fecha_desde_afiliado").value;
	}

	function updatefechaHastaAfiliado() {
		document.getElementById("fechaHastaAfiliado").value = document
				.getElementById("fecha_hasta_afiliado").value;
	}

	function updatefechaNacimiento() {
		document.getElementById("fechaNacimiento").value = document
				.getElementById("fecha_nacimiento").value;
	}

	function updatefechaNacimiento() {
		document.getElementById("fechaNacimiento").value = document
				.getElementById("fecha_nacimiento").value;
	}

	function updatefechaDesdeOrden() {
		document.getElementById("fechaDesdeOrden").value = document
				.getElementById("fecha_desde_orden").value;
	}

	function updatefechaHastaOrden() {
		document.getElementById("fechaHastaOrden").value = document
				.getElementById("fecha_hasta_orden").value;
	}
			 
	function updateFechaDesdeFiltro() {
		document.getElementById("fechaDesdeFiltro").value = document
				.getElementById("fecha_desde_filtro").value;
	}

	function updateFechaHastaFiltro() {
		document.getElementById("fechaHastaFiltro").value = document
				.getElementById("fecha-hasta-filtro").value;
	}
	
	
	//----------------------------------------------------------------------------
	
	function showCircular(jsonData){
	
	}
	function showLineal(jsonData){
		
	}
	function getJsonFiltro(grafico, tipo, tipoFiltro, fechaDesdeFiltro, fechaHastaFiltro) { 
		return '{"grafico":'+grafico+', "tipo":'+tipo+', "tipoFiltro":'+tipoFiltro+', "fechaDesdeFiltro": "'+fechaDesdeFiltro+'", "fechaHastaFiltro": "'+fechaHastaFiltro+'"}';
	}
	
	$(function() {
		$('#btn_filtrar').click(function() {

			var grafico = document.getElementById("grafico").value;
			var tipo = document.getElementById("tipo").value;
			var tipoFiltro = document.getElementById("tipo_filtro").value;
			var fechaDesdeFiltro = document.getElementById("fechaDesdeFiltro").value;
			var fechaHastaFiltro = document.getElementById("fechaHastaFiltro").value;
			var jsonData = callJsonDataByFiltro(getJsonFiltro(grafico, tipo, tipoFiltro, fechaDesdeFiltro, fechaHastaFiltro));
			if (grafico == 1) { // Circular
				showCircular(jsonData);
			} else if (grafico == 2) { // Lineal
				showLineal(jsonData);
			}
			

		});

	
	});

	//Actions Modal

	$(function() {
		$('#lnk_cantidad_ordenes_por_tipo_fecha').click(function() {
			$("#modal_ordenes_por_tipo_fecha").css("visibility", "visible");

			document.getElementById("fecha_desde_orden").value = "";
			document.getElementById("fecha_hasta_orden").value = "";

			$('#modal_ordenes_por_tipo_fecha').modal('show');
		});

		$('#btn_modal_ordenes_por_tipo_fecha_aceptar').click(
				function() {
					var orden = document.getElementById("tipo_orden").value;
					var fechaDesdeOrden = document
							.getElementById("fecha_desde_orden").value;
					var fechaHastaOrden = document
							.getElementById("fecha_hasta_orden").value;

					resp = callReportOrdenesPorTipoYFecha(orden,
							fechaDesdeOrden, fechaHastaOrden);
					$('#modal_ordenes_por_tipo_fecha').modal('hide');
				});
	});

	$(function() {
		$('#lnk_filtro_afiliados').click(function() {
			$("#filtroafiliados").css("visibility", "visible");
			// $("#msj_afiliadoatendido").css("visibility","hidden");
			document.getElementById("fechaDesdeAfiliado").value = "";
			document.getElementById("fecha_desde_afiliado").value = "";
			document.getElementById("fechaHastaAfiliado").value = "";
			document.getElementById("fecha_hasta_afiliado").value = "";
			document.getElementById("fechaNacimiento").value = "";
			document.getElementById("fecha_nacimiento").value = "";
			document.getElementById("localidadId").value = "";
			document.getElementById("localidadString").value = "";

			$('#filtroafiliados').modal('show');
		});

		$('#btnAceptarFiltro')
				.click(
						function() {
							var fechaDesdeAfiliado = document
									.getElementById("fechaDesdeAfiliado").value;
							var fechaHastaAfiliado = document
									.getElementById("fechaHastaAfiliado").value;
							var fechaNacimiento = document
									.getElementById("fechaNacimiento").value;
							var localidadId = document
									.getElementById("localidadId").value;
							var zonaAfiliacion = document
									.getElementById("zonaAfiliacion").value;

							resp = callReportFiltroAfiliado(fechaDesdeAfiliado,
									fechaHastaAfiliado, fechaNacimiento,
									localidadId, zonaAfiliacion);
							$('#filtroafiliados').modal('hide');
						});
	});

	$(function() {
		$('#lnk_afiliados_atendidos').click(function() {
			$('#afiliadosAtendidos').css("visibility", "visible");
			$("#msj_afiliadoatendido").css("visibility", "hidden");
			document.getElementById("especialidad").value = "";
			document.getElementById("especialidadString").value = "";

			$('#afiliadosAtendidos').modal('show');
		});

		$('#btnSave')
				.click(
						function() {
							var fechaDesdeAA = document
									.getElementById("fechaDesdeAA").value;
							var fechaHastaAA = document
									.getElementById("fechaHastaAA").value;
							var especialidad = document
									.getElementById("especialidad").value;

							var profesionalId = document
							.getElementById("profesionalId").value;

							if (especialidad == "") {
								especialidad = 0;
							}

							if (fechaDesdeAA == "" || fechaHastaAA == "") {
								$("#msj_afiliadoatendido").css("visibility",
										"visible");

								return false;
							}

							callReportAfiliadosAtendidos(fechaDesdeAA,
									fechaHastaAA, especialidad, profesionalId);
							$('#afiliadosAtendidos').modal('hide');
						});
	});

	$(function() {
		$('#lnk_pacientes_registrados').click(function() {
			$('#pacienteregistrado').css("visibility", "visible");
			$("#msj_pacienteregistrado").css("visibility", "hidden");
			$('#pacienteregistrado').modal('show');
		});

		$('#btnSavePR').click(function() {
			var fechaDesdePR = document.getElementById("fechaDesdePR").value;
			var fechaHastaPR = document.getElementById("fechaHastaPR").value;

			if (fechaDesdePR == "" || fechaHastaPR == "") {
				$("#msj_pacienteregistrado").css("visibility", "visible");
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
				findProfesinoales($('#especialidad'));
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

	function callProfesionalByEspecialidad(especialidadId) {
		var retorno;
		$.ajax({
			url : "/nuova/ajaxGetProfesionalByEspecialidad?especialidadId="
					+ especialidadId,
			type : "GET",
			contentType : "application/json; charset=utf-8",
			//    data: jsonString, //Stringified Json Object
			async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
			cache : false, //This will force requested pages not to be cached by the browser          
			processData : false, //To avoid making query String instead of JSON
			success : function(page) {
				// Success Message Handler
				retorno = page;
			}
		});

		return retorno;
	}

	function findProfesinoales(especialidad) {
		var especialidades = callProfesionalByEspecialidad(especialidad
				.attr('value'));
		$('#profesionalId')
				.empty()
				.append(
						'<option selected="selected" value="-1">Seleccione Profesional ...</option>');
		$.each(especialidades, function(key, value) {
			$('#profesionalId').append($('<option>', {
				value : value.id
			}).text(value.value));
		});

		
	}

	//----------------------------------------------------------------------------
	//Call Ajax Reports

	function callReportAfiliadosAtendidos(fd, fh, esp, profesionalId) {
		var url = "/nuova/ajaxGetReportAfiliadosAtendidos?fd=" + fd + "&fh="
				+ fh + "&esp=" + esp + "&profesionalId=" + profesionalId;
		window.open(url, '_blank');
	}

	function callReportPacienteRegistrado(fd, fh) {
		var url = "/nuova/ajaxGetReportPacienteRegistrado?fd=" + fd + "&fh="
				+ fh;
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

	function callReportFiltroAfiliado(fechaDesdeAfiliado, fechaHastaAfiliado,
			fechaNacimiento, localidadId, zonaAfiliacion) {
		var parameters = "fechaDesdeAfiliado=" + fechaDesdeAfiliado
				+ "&fechaHastaAfiliado=" + fechaHastaAfiliado
				+ "&fechaNacimiento=" + fechaNacimiento + "&localidadId="
				+ localidadId + "&zonaAfiliacion=" + zonaAfiliacion;

		var url = "/nuova/ajaxGetReportFiltroAfiliado?" + parameters;
		window.open(url, '_blank');
	}

	function callReportOrdenesPorTipoYFecha(tipoOrden, fechaDesde, fechaHasta) {
		var parameters = "tipoOrden=" + tipoOrden + "&fechaDesdeOrden="
				+ fechaDesde + "&fechaHastaOrden=" + fechaHasta
		var url = "/nuova/ajaxGetReportOrdenesPorTipoYFecha?" + parameters;
		window.open(url, '_blank');
	}

	function callJsonDataByFiltro(jsonFiltro) {
		var retorno;
		$.ajax({
			url : "get-dataprovider",
			type : "POST",
			contentType : "application/json; charset=utf-8",
			data : jsonFiltro,
			async : false, 
			cache : false,           
			processData : false, 
			success : function(result) {
				retorno = result;
			}
		});

		return retorno;
	}
</script>

<script>
	var chart = AmCharts.makeChart("chartdiv", {
		"type" : "pie",
		"startDuration" : 0,
		"theme" : "light",
		"addClassNames" : true,
		"legend" : {
			"position" : "right",
			"marginRight" : 100,
			"autoMargins" : false
		},
		"innerRadius" : "30%",
		"defs" : {
			"filter" : [ {
				"id" : "shadow",
				"width" : "200%",
				"height" : "200%",
				"feOffset" : {
					"result" : "offOut",
					"in" : "SourceAlpha",
					"dx" : 0,
					"dy" : 0
				},
				"feGaussianBlur" : {
					"result" : "blurOut",
					"in" : "offOut",
					"stdDeviation" : 5
				},
				"feBlend" : {
					"in" : "SourceGraphic",
					"in2" : "blurOut",
					"mode" : "normal"
				}
			} ]
		},
		"dataProvider" : [ {
			"country" : "Lithuania",
			"litres" : 501.9
		}, {
			"country" : "Czech Republic",
			"litres" : 301.9
		}, {
			"country" : "Ireland",
			"litres" : 201.1
		} ],
		"valueField" : "litres",
		"titleField" : "country",
		"export" : {
			"enabled" : true
		}
	});

	chart.addListener("init", handleInit);

	chart.addListener("rollOverSlice", function(e) {
		handleRollOver(e);
	});

	function handleInit() {
		chart.legend.addListener("rollOverItem", handleRollOver);
	}

	function handleRollOver(e) {
		var wedge = e.dataItem.wedge.node;
		wedge.parentNode.appendChild(wedge);
	}
</script>