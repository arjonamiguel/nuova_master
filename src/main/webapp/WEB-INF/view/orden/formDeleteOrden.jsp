<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<link
	href="<%=request.getContextPath()%>/resources/montrezorro-bootstrap-checkbox-fa865ff/css/bootstrap-checkbox.css"
	rel="stylesheet" />
<script
	src="<%=request.getContextPath()%>/resources/montrezorro-bootstrap-checkbox-fa865ff/js/bootstrap-checkbox.js" /></script>


<script type="text/javascript">
	function createDatePicker(idx) {
		return '	<div style="visibility:hidden;height:0px;"> '
				+ ' <input type="text"' + 
	' name = "ordenpracticaListEdit['+idx+'].autorizarAutomatico" '  +
	' id = "ordenpracticaListEdit['+idx+'].autorizarAutomatico"' +
	' class="date" />'
				+ ' </div> '
				+ ' <div id="calendar">'
				+ ' <div class="input-group registration-date-time" style="padding-top:0%;">'
				+ ' <input class="form-control"'
				+ ' <input class="form-control"'
				+ ' name="autorizar_automatico_' + idx + '"'
				+ ' id="autorizar_automatico_' + idx + '"' + ' type="date"'
				+ ' onchange="javascript:updateDate(' + idx + ');">'
				+ ' </div>' + ' </div>';

	}
	function createSelectEstados(id) {
		var selectEstado = '<select name="'
				+ id
				+ '" id="'
				+ id
				+ '" style="width:70%; margin-bottom:0px">'
				+ ' <option value="NONE">Seleccione Estado ...</option>'
				+ ' <option value="AUTORIZACION DIRECTA">AUTORIZACION DIRECTA</option>'
				+ ' <option value="PENDIENTE AFILIACIONES">PENDIENTE AFILIACIONES</option>'
				+ ' <option value="AUTORIZADA POR AFILIACIONES">AUTORIZADA POR AFILIACIONES</option>'
				+ ' <option value="RECHAZADA POR AFILIACIONES">RECHAZADA POR AFILIACIONES</option>'
				+ ' <option value="PENDIENTE AUDITORIA">PENDIENTE AUDITORIA</option>'
				+ ' <option value="AUTORIZADA POR AUDITORIA">AUTORIZADA POR AUDITORIA</option>'
				+ ' <option value="RECHAZADA POR AUDITORIA">RECHAZADA POR AUDITORIA</option>'
				+ ' <option value="RECHAZADA">RECHAZADA</option><option value="ANULADO">ANULADO</option>'
				+ ' </select>';

		return selectEstado;
	}


	function enabledFueraCartilla(){
	
		if ($("#fueraCartilla").is(':checked')) {
			document.getElementById("entidad").disabled = "";
			document.getElementById("observacionFueraCartilla").disabled = "";

			document.getElementById("especialidadString").value = "";
			document.getElementById("especialidad").value = "";
			document.getElementById("profesionalId").value = "";
			document.getElementById("especialidadString").disabled = "false";
			document.getElementById("profesionalId").disabled = "false";			
	
			document.getElementById("entidad").focus();

		}else {
			document.getElementById("entidad").disabled = "false";
			document.getElementById("observacionFueraCartilla").disabled = "false";
			document.getElementById("entidad").value = "";
			document.getElementById("observacionFueraCartilla").value = "";

			document.getElementById("especialidadString").disabled = "";
			document.getElementById("profesionalId").disabled = "";
			document.getElementById("especialidadString").focus();
			
	
		}	

	}
	
	function sinCosto(){
		if ($("#coseguroSinCosto").is(':checked')) {
			document.getElementById("monto").value = "0.00";
			document.getElementById("monto").disabled = "false";
		}else {
			document.getElementById("monto").value = "";
			document.getElementById("monto").disabled = "";
		}	

	}

	$(document).ready(function() {
		var especialidadTH = $('#especialidadString.typeahead');
		var nomencladorTH = $('#nomencladorString.typeahead');
		var especialidadPrestadorTH = $('#especialidadPrestadorString.typeahead')

		var map = new Object();
		var objects = [];

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
		
		  var mapNom = new Object();
	  	  var objectsNom = [];
	  	  
	  	nomencladorTH.typeahead({
	        source: function (query, process) {
	          $.ajax({
	            url: '/nuova/ajaxGetAutoCompleteNomenclador',
	            type: 'POST',             
	            dataType: 'JSON',
	            minLength: 3,                           
	            data: 'query=' + query,
	            success: function(data) { 
	              console.log(data);
	              $.each(data, function(i, object) {
	                  mapNom[object.value] = object;
	                  if (objectsNom[i] == null) {
	                  	objectsNom.push(object.value);
	                  }
	              });
	              process(objectsNom);
	              objectsNom = [];
	            }
	          });
	        },
	        updater: function(item) {
	            $('#nomencladorId').val(mapNom[item].id);
	            return item;
	        }
	      });

		  var mapesppres = new Object();
	  	  var objectsesppres = [];
	  	  
	  	especialidadPrestadorTH.typeahead({
	        source: function (query, process) {
	          $.ajax({
	            url: '/nuova/ajaxGetAutoCompleteEspecialidadesPrestador',
	            type: 'POST',             
	            dataType: 'JSON',
	            minLength: 3,                           
	            data: 'query=' + query,
	            success: function(data) { 
	              console.log(data);
	              $.each(data, function(i, object) {
	                  mapesppres[object.value] = object;
	                  if (objectsesppres[i] == null) {
	                  	objectsesppres.push(object.value);
	                  }
	              });
	              process(objectsesppres);
	              objectsesppres = [];
	            }
	          });
	        },
	        updater: function(item) {
	            $('#especialidadPrestador').val(mapesppres[item].id);
				findPrestadores($('#especialidadPrestador'));	            
	            return item;
	        }
	      });
	  	
	});

	function findPrestadores(especialidadPrestador) {
		var especialidades = callPrestadoresByEspecialidad(especialidadPrestador
				.attr('value'));
		$('#prestadorId')
				.empty()
				.append(
						'<option selected="selected" value="-1">Seleccione Prestador ...</option>');
		$.each(especialidades, function(key, value) {
			$('#prestadorId').append($('<option>', {
				value : value.id
			}).text(value.value));
		});

		hideMessage();
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

		hideMessage();
	}
	function hideMessage() {
		$("#message").css("visibility", "hidden");
	}

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

	function callPrestadoresByEspecialidad(especialidadId) {
		var retorno;
		$.ajax({
			url : "/nuova/ajaxGetPrestadorByEspecialidad?especialidadId="
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

	function setObservacionVisible() {
		$("#addObservacion").css("visibility", "visible");
	}
	function setObservacionInvisible() {
		$("#addObservacion").css("visibility", "hidden");
	}

	function Eliminar(i) {
		document.getElementById("tb_practicas").deleteRow(i);
	}

	function addRow(tableID) {
		if (document
				.getElementById("nomencladorString").value == "") {
			return;
		}

		var index = document.getElementById(tableID).getElementsByTagName('tr').length;
		index++;
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		row.style.background = '#f5f5f5';

		var cell0 = row.insertCell(0);
		cell0.innerHTML = document
				.getElementById("nomencladorString").value
				+ " <input type='hidden' name='ordenpracticaListEdit[" + index + "].orddenPracticaId'> "
				+ "<input type='hidden' name='ordenpracticaListEdit["
				+ index
				+ "].practicaId' value='"
				+ document.getElementById("nomencladorId").value + "'>";
				
		var cell1 = row.insertCell(1);
		var str1 = document.getElementById("nomencladorString").value;
		var str2 = "ODON";
		var str3 = "odon";
		var str4 = "Odon";
		
		if(str1.indexOf(str2) != -1 || str1.indexOf(str3) != -1 || str1.indexOf(str4) != -1){
			cell1.innerHTML = "<input type='text' name='ordenpracticaListEdit[" + index + "].piezaDental' placeholder='pieza dental'>";
		}
		var cell2 = row.insertCell(2);
		cell2.innerHTML = "<input type='hidden' name='ordenpracticaListEdit[" + index + "].valor' value='0.00'>";


		var cell3 = row.insertCell(3);
		cell3.innerHTML = createSelectEstados("ordenpracticaListEdit[" + index
				+ "].estado");

		var cell4 = row.insertCell(4);
		cell4.innerHTML = createDatePicker(index);

		var cell5 = row.insertCell(5);
		row.valign = "BASELINE";
		cell5.innerHTML = "<button type='button' class='btn btn-link' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'>Eliminar</button>";

		var cell6 = row.insertCell(6);
		cell6.innerHTML = "";

		index++;
		document
				.getElementById("nomencladorString").value = "";
		document
				.getElementById("nomencladorString")
				.focus();
	}

	function addRowHistoriaClinica(tableID) {

		var index = document.getElementById(tableID).getElementsByTagName('tr').length;
		index++;
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		row.style.background = '#f5f5f5';

		var cell0 = row.insertCell(0);
		cell0.innerHTML = " <input type='hidden' name='historiasclinicas[" + index + "].documentId'> "
				+ "<input type='file' name='historiasclinicas[" + index + "].fileData'>";

		var cell1 = row.insertCell(1);
		cell1.innerHTML = "";

		var cell2 = row.insertCell(2);
		row.valign = "BASELINE";
		cell2.innerHTML = "<a href='#' onclick='eliminarHC(this.parentNode.parentNode.rowIndex)'>Eliminar</a>";

		index++;

	}
	function eliminarHC(i) {
		document.getElementById("tabla_historiaclinica").deleteRow(i);
	}

	function findEspecialidades(profesional) {
		var especialidades = callEspecialidadesByProfesional(profesional.value);
		$('#especialidad')
				.empty()
				.append(
						'<option selected="selected" value="-1">Seleccione Especialidad ...</option>');
		$.each(especialidades, function(key, value) {
			$('#especialidad').append($('<option>', {
				value : value.id
			}).text(value.value));
		});

	}

	function callEspecialidadesByProfesional(profesionalId) {
		var retorno;
		$.ajax({
			url : "/nuova/ajaxGetEspecialidadesByProfesional?profesionalId="
					+ profesionalId,
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
	
		function marcarTodos(){
			if(document.getElementById("selectAll").checked==true)
			{
				$(".largerCheckbox")[1].checked=true;
				$(".largerCheckbox")[2].checked=true;
				$(".largerCheckbox")[3].checked=true;
				$(".largerCheckbox")[4].checked=true;
			}else
			{
				$(".largerCheckbox")[1].checked=false;
				$(".largerCheckbox")[2].checked=false;
				$(".largerCheckbox")[3].checked=false;
				$(".largerCheckbox")[4].checked=false;
			}
		}
</script>

<style>
.chkbox {
	padding-left: 10px;
	font-weight: bold;
}
</style>
</head>

<body style="background-color: #e5e5e5;">
	<jsp:include page="../sec_menu.jsp"></jsp:include>

	<form:form method="post" action="/nuova/deleteOrden"
		commandName="ordenDto" enctype="multipart/form-data">
		<form:hidden path="ordenId" />
		<form:hidden path="ordenTipo.ordenTipoId" />
		<div class="mainContainer">

			<div class="panelContainer">
				<div class="panel panel-info">
					<!-- Cabecera y Titulo -->
					<div class="panel-heading" style = "background-color: #fdf293">
						<div class="panel-title">
							<b>Elimiar Prácticas</b>
						</div>
					</div>
					<!-- Fin Cabecera y Titulo -->

					<div class="panel-body">
						<div class="container-fluid">
							<div class="row-fluid">
								<div class="span12">
									<div class="tableContainer">
										<jsp:include page="../message.jsp"></jsp:include>
																				<div class="tab-content">
											<table class="table" style="width: 100%;" >
												<tr>
													<td style="width: 15%"><span class="badge"
														style="padding: 5px 5px 5px 5px"><b>Nro de
																Orden: ${ordenDto.nroOrden}</b></span></td>
													<td style="text-align: right"><b>Fecha de
															Creación:</b> ${ordenDto.fecha}</td>


												</tr>

											</table>
										</div>
										<!-- Declaracion de tabs -->
										<ul class="nav nav-tabs">
											<li class="active"><a data-toggle="tab"
												href="#tb_paciente" onclick="setObservacionInvisible()">Paciente</a></li>
											<li><a data-toggle="tab" href="#tb_requisitos">Requisitos</a></li>
											<li><a data-toggle="tab" href="#tb_profesional">Medico Solicitante</a></li>
											<li><a data-toggle="tab" href="#tb_autorizacion">Autorizaci&oacute;n</a></li>
											<li><a data-toggle="tab" href="#tb_prestador">Prestador Derivado</a></li>
											<li><a data-toggle="tab" href="#tb_observacion">
													Observaciones <c:if test="${observacionCount > 0}">
														<span class="badge">${observacionCount}</span>
													</c:if>
											</a></li>
											<li><a data-toggle="tab" href="#tb_historiaclinica">Historia
													Cl&iacute;nica</a></li>
											<li><a data-toggle="tab" href="#tb_coseguro">Coseguro</a></li>													
											
										</ul>
										<!-- Fin Declaracion de tabs -->

										<!-- Contenedor de Tabs -->
										<div class="tab-content">
											<!-- ** Tab Paciente -->
											<div id="tb_paciente" class="tab-pane fade in active">
												<jsp:include page="formDeleteOrdenTabPaciente.jsp"></jsp:include>
											</div>

											<!-- ** Tab Requisitos -->
											<div id="tb_requisitos" class="tab-pane fade">
												<jsp:include page="formDeleteOrdenTabRequisitos.jsp"></jsp:include>
											</div>

											<!-- ** Tab Profesional -->
											<div id="tb_profesional" class="tab-pane fade">
												<jsp:include page="formDeleteOrdenTabProfesional.jsp"></jsp:include>
											</div>

											<!-- ** Tab Autorizaciones -->
											<div id="tb_autorizacion" class="tab-pane fade">
												<jsp:include page="formDeleteOrdenTabAutorizacion.jsp"></jsp:include>
											</div>
											
											<!-- ** Tab Prestador -->
											<div id="tb_prestador" class="tab-pane fade">
												<jsp:include page="formDeleteOrdenTabPrestador.jsp"></jsp:include>
											</div>
											

											<!-- ** Tab Observaciones -->
											<div id="tb_observacion" class="tab-pane fade" style="">
												<jsp:include page="formDeleteOrdenTabObservaciones.jsp"></jsp:include>
											</div>

											<!-- ** Tab Historia Clinica -->
											<div id="tb_historiaclinica" class="tab-pane fade">
												<jsp:include page="formDeleteOrdenTabHistoriaClinica.jsp"></jsp:include>
											</div>

											<!-- ** Tab Coseguro -->
											<div id="tb_coseguro" class="tab-pane fade">
												<jsp:include page="formDeleteOrdenTabCoseguro.jsp"></jsp:include>
											</div>
											

										</div>
										<!-- Fin Contenedor de Tabs -->

										<div style="float: right;"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Botoneras -->
				<div class="panel panel-info">
					<div class="panel-body">
						<div class="row-fluid">
							<div class="span12">
								<div style="float: right;">
									<input type="button" value="Cancelar"
										onclick="location.href = '/nuova/mainOrdenPractica'; return false;"
										class="btn" />
								</div>
								<div style="float: right; padding-right: 2%;">
									<input type="submit" value="Eliminar" class="btn btn-danger"/>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Fin Botoneras -->

			</div>
		</div>
	</form:form>
</body>
</html>
<script>
	document.getElementById("mainOrden").parentNode.classList.add("active");
	$(".checkbox").checkbox();
</script>