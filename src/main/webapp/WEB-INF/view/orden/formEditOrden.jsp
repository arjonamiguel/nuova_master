<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Nuova</title>
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/resources/img/favicon/favicon.ico">

<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
<!-- Scripts Toaster ------------------------------------------- -->
<link
	href="<%=request.getContextPath()%>/resources/css/bootstrap-combined.min.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/toastr.css"
	rel="stylesheet" />
<style>
.row {
	margin-left: 0;
}
</style>

<script src="<%=request.getContextPath()%>/resources/js/toastr.js" /></script>
<script type="text/javascript">
toastr.options = {
  "closeButton": false,
  "debug": false,
  "newestOnTop": false,
  "progressBar": false,
  "rtl": false,
  "positionClass": "toast-bottom-right",
  "preventDuplicates": false,
  "onclick": null,
  "showDuration": 300,
  "hideDuration": 1000,
  "timeOut": 3000,
  "extendedTimeOut": 1000,
  "showEasing": "swing",
  "hideEasing": "linear",
  "showMethod": "fadeIn",
  "hideMethod": "fadeOut"
}
</script>
<!-- Fin Scripts Toaster ---------------------------------------- -->

<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
<link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/resources/js/jquery/bootstrap-collapse.js" /></script>
<link href="<%=request.getContextPath()%>/resources/css/nuova.css" 	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/panel.css" 	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/montrezorro-bootstrap-checkbox-fa865ff/css/bootstrap-checkbox.css" 	rel="stylesheet" />
<script src="<%=request.getContextPath()%>/resources/montrezorro-bootstrap-checkbox-fa865ff/js/bootstrap-checkbox.js" /></script>
<script src="<%=request.getContextPath()%>/resources/js/validateForm.js" /></script>

<script type="text/javascript">
var usr = "<%=SecurityContextHolder.getContext().getAuthentication().getName()%>";
var observacionCount = 0;

 
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
				+ ' <option value="AUTORIZADA POR AFILIACIONES">AUTORIZADA POR AFILIACIONES</option>'
				+ ' <option value="AUTORIZADA POR AUDITORIA">AUTORIZADA POR AUDITORIA</option>'
				+ ' <option value="PENDIENTE AFILIACIONES">PENDIENTE AFILIACIONES</option>'
				+ ' <option value="PENDIENTE AUDITORIA">PENDIENTE AUDITORIA</option>'
				+ ' <option value="RECHAZADA POR AFILIACIONES">RECHAZADA POR AFILIACIONES</option>'
				+ ' <option value="RECHAZADA POR AUDITORIA">RECHAZADA POR AUDITORIA</option>'
				+ ' <option value="RECHAZADA">RECHAZADA</option><option value="ANULADO">ANULADO</option>'
				+ ' <option value="VENCIDA">VENCIDA</option>'
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
		var especialidadPrestadorTH = $('#especialidadPrestadorString.typeahead');
		var especialidadProfTH = $('#especialidadStringProf.typeahead');

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
	  	
	  	var mapEsp = new Object();
		var objectsEsp = [];

		especialidadProfTH.typeahead({
			source : function(query, process) {
				$.ajax({
					url : '/nuova/ajaxGetAutoCompleteEspecialidades',
					type : 'POST',
					dataType : 'JSON',
					data : 'query=' + query,
					success : function(data) {
						console.log(data);
						$.each(data, function(i, object) {
							mapEsp[object.value] = object;
							if (objectsEsp[i] == null) {
								objectsEsp.push(object.value);
							}
						});
						process(objectsEsp);
						objectsEsp = [];
					}
				});
			},
			updater : function(item) {
				$('#especialidadProf').val(mapEsp[item].id);				
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

	
	function getJsonEspecialidad(especialidad, tipo) {
		return '{"nombre": "'+especialidad+'", "tipo": '+tipo+'}';	
	}

	function getJsonProfesional(apellidoRazonSocial, tipo, profesional, especialidadProf) {
		return '{"apellido": "'+apellidoRazonSocial+'", "tipo": '+tipo+', "profesionalId": '+profesional+', "especialidadId" :'+especialidadProf +'}';	
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
	
	function callCantidadSesiones(nomencladorId, pacienteId) {
		var retorno;
		$.ajax({
			url : "/nuova/ajaxGetCantidadSesiones?nomencladorId="
					+ nomencladorId+"&pacienteId="+pacienteId,
			type : "GET",
			contentType : "application/json; charset=utf-8",
			//    data: jsonString, //Stringified Json Object
			async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
			cache : false, //This will force requested pages not to be cached by the browser          
			processData : false, //To avoid making query String instead of JSON
			success : function(data) {
				// Success Message Handler
				retorno = data;
			}
		});

		return retorno;
		
	}

	function addRow(tableID) {
		if (document
				.getElementById("nomencladorString").value == "") {
			return;
		}
		
		var ordenId = document.getElementById("ordenId").value;
		var msjSesion = callCantidadSesiones(document.getElementById("nomencladorId").value
				, document.getElementById("paciente.pacienteId").value);
		var arrMsjSesion = msjSesion.split(";;");
		//	alert(arrMsjSesion[1]);
		document.getElementById("valida_sesion").innerHTML = "";
		if (arrMsjSesion[0] == 2) {
			document.getElementById("valida_sesion").innerHTML = createInfo("Información de Práctica Agregada:",arrMsjSesion[2]);
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
		cell1.innerHTML = "<input type='text' name='ordenpracticaListEdit[" + index + "].cantidad' placeholder='Cantidad' value='1' style='width:60px'>";

		
		var cell2 = row.insertCell(2);
		var str1 = document.getElementById("nomencladorString").value;
		var str2 = "ODON";
		var str3 = "odon";
		var str4 = "Odon";
		
		if(str1.indexOf(str2) != -1 || str1.indexOf(str3) != -1 || str1.indexOf(str4) != -1){
			cell2.innerHTML = "<input type='text' name='ordenpracticaListEdit[" + index + "].piezaDental' placeholder='pieza dental'>";
		}

		if (usr == "mesa.entrada@nuovamed.com") {			

			var cell3 = row.insertCell(3);
			row.valign = "BASELINE";
			cell3.innerHTML = "<button type='button' class='btn btn-link' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'>Eliminar</button>";				

		} else {
			
			var cell3 = row.insertCell(3);
			cell3.innerHTML = createSelectEstados("ordenpracticaListEdit[" + index
					+ "].estado");
	
			var cell4 = row.insertCell(4);
			cell4.innerHTML = createDatePicker(index);

			var cell5 = row.insertCell(5);
			row.valign = "BASELINE";
			cell5.innerHTML = "<button type='button' class='btn btn-link' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'>Eliminar</button>";

		}			
		
	
		index++;
		document
				.getElementById("nomencladorString").value = "";
		document
				.getElementById("nomencladorString")
				.focus();
	}
	
	function bindElement(){
		$('.myFile').bind('change', function() {
		  if(this.files[0].size>4000000){
			alert("El archivo es demasiado grande -  " + this.files[0].size+ " bytes");
			eliminarHC(this.parentNode.parentNode.rowIndex)
			return;}
		});
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
				+ "<input class='myFile' type='file' name='historiasclinicas[" + index + "].fileData'>";

		var cell1 = row.insertCell(1);
		cell1.innerHTML = "";

		var cell2 = row.insertCell(2);
		row.valign = "BASELINE";
		cell2.innerHTML = "<a href='#' onclick='eliminarHC(this.parentNode.parentNode.rowIndex)'>Eliminar</a>";

		index++;
		bindElement();
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
				$(".largerCheckbox")[2].checked=true;
				$(".largerCheckbox")[3].checked=true;
				$(".largerCheckbox")[4].checked=true;
				$(".largerCheckbox")[5].checked=true;
				//$(".largerCheckbox")[5].checked=true;
			}else
			{
				$(".largerCheckbox")[2].checked=false;
				$(".largerCheckbox")[3].checked=false;
				$(".largerCheckbox")[4].checked=false;
				$(".largerCheckbox")[5].checked=false;
				// $(".largerCheckbox")[5].checked=true;
			}
		}
		
		function callRemoveObservaciones(observacionId) {
			var retorno;
			$.ajax({
				url : "/nuova/ajaxPostRemoveObservacion/"+observacionId,
				type : "POST",
				contentType : "application/json; charset=utf-8",
				//    data: jsonString, //Stringified Json Object
				async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
				cache : false, //This will force requested pages not to be cached by the browser          
				processData : false, //To avoid making query String instead of JSON
				success : function(data) {
					// Success Message Handler
					retorno = data;
				}
			});
			if (retorno = "OK") {
				var tbl = document.getElementById("observacion_"+observacionId);
		        if(tbl) tbl.parentNode.removeChild(tbl);
		        observacionCount = observacionCount - 1;
		        document.getElementById("observacionCount").innerHTML=observacionCount+"";
			}
			return retorno;
		}

		function updateDateCaja(){
			document.getElementById("fechaCaja").value=document.getElementById("fechaCajaId").value;
		}

		function checkOrdenEntregada(){	
			var mensaje = "";
			var resp;		
			if ($("#ordenEntregada").is(':checked')) {
				resp = callOrdenEntregada(1);
				mensaje = "El comprobante se marco\n[ENTREGADO]";
			}else {
				resp = callOrdenEntregada(0);
				mensaje = "El comprobante \n se marco [NO_ENTREGADO]";		
			}	

			if (resp == "OK") {
				toastr["success"](mensaje);				
			}
		}
		
		function callOrdenEntregada(ordenEntregada) {
			var retorno;
			var ordenId = document.getElementById("ordenId").value;
			$.ajax({
				url : "/nuova/ajaxPosOrdenEntregada?entregada="
						+ ordenEntregada + "&ordenId=" + ordenId,
				type : "POST",
				contentType : "application/json; charset=utf-8",
				//    data: jsonString, //Stringified Json Object
				async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
				cache : false, //This will force requested pages not to be cached by the browser          
				processData : false, //To avoid making query String instead of JSON
				success : function(data) {
					// Success Message Handler
					retorno = data;
				}
			});

			return retorno;
		}
		

		$(function() {
			  $('#btnEspecialidad').click(function() {
				  document.getElementById("especialidadNombre").value="";
				$('#modalEspecialidad').css("visibility", "visible");  
			    $('#modalEspecialidad').modal('show');
			  });
			  
			  $('#btnSaveEspecialidad').click(function() {
			    var especialidad = document.getElementById("especialidadNombre").value;
			    var resp = callNuevaEspecialidad(getJsonEspecialidad(especialidad, 0));
			    $('#modalEspecialidad').modal('hide');
			    if (resp > 0) {
					toastr["success"]("Se agrego una nueva especialidad");				
				}
			    
			  });
			});
			
		$(function() {
			  $('#btnProfesional').click(function() {
				 document.getElementById("apellidoRazonSocial").value="";
				 document.getElementById("profesional").value="";
				 document.getElementById("tipo").value="-1";
				 document.getElementById("especialidadProf").value="";
				 document.getElementById("especialidadStringProf").value="";		 
				    
				$('#modalProfesional').css("visibility", "visible");  
			    $('#modalProfesional').modal('show');
			  });
			  
			  $('#btnSaveProfesional').click(function() {
			    var apellidoRazonSocial = document.getElementById("apellidoRazonSocial").value;
				var profesional = document.getElementById("profesional").value;
			    var tipo = document.getElementById("tipo").value;
			    var especialidadProf = document.getElementById("especialidadProf").value;
			    if (profesional == "") {
			    	profesional = 0;
			    }
			    if (especialidadProf == "") {
			    	especialidadProf = 0;
			    }
			    
			    var resp = callNuevoProfesional(getJsonProfesional(apellidoRazonSocial, tipo, profesional, especialidadProf));
			    $('#modalProfesional').modal('hide');
			    if (resp > 0) {
					toastr["success"]("Se agrego un nuevo Profesional / Institucion");				
				}
			  });
			});
			
		function callNuevaEspecialidad(jsonEspecialidad) {
			var retorno;
			$.ajax({
				url : "/nuova/ajaxPostSaveEspecialidad",
				type : "POST",
				contentType : "application/json; charset=utf-8",
				data: jsonEspecialidad, //Stringified Json Object
				async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
				cache : false, //This will force requested pages not to be cached by the browser          
				processData : false, //To avoid making query String instead of JSON
				success : function(result) {
					retorno = result;
				}
			});

			return retorno;
		}

		function callNuevoProfesional(jsonProfesional) {
			var retorno;
			$.ajax({
				url : "/nuova/ajaxPostSaveProfesional",
				type : "POST",
				contentType : "application/json; charset=utf-8",
				data: jsonProfesional, //Stringified Json Object
				async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
				cache : false, //This will force requested pages not to be cached by the browser          
				processData : false, //To avoid making query String instead of JSON
				success : function(result) {
					retorno = result;
				}
			});

			return retorno;
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

	<form:form method="post" action="/nuova/editOrden"
		commandName="ordenDto" enctype="multipart/form-data"
		onsubmit="return disabledSubmit()">
		<form:hidden path="ordenId" />
		<div class="mainContainer">

			<div class="panelContainer">
				<div class="panel panel-info">
				<div id="validacion_requeridos"></div>
					<!-- Cabecera y Titulo -->
					<div class="panel-heading" style = "background-color: #fdf293">
						<div class="panel-title" >
							<b>Editar Prácticas</b> <a class="btn btn-default btn-xs"
								data-toggle="modal" data-target="#myModal"
								style="float: right; padding: 1px 3px 1px 4px"
								onclick="showReport(${ordenDto.ordenId})" data-original-title=""
								title=""> <span class="icon icon-print"></span> Imprimir

							</a>
							<div style="float: right; padding-right: 5%">
								<span style="font-size: 14px;">Entregado</span>
								<form:checkbox path="ordenEntregada" id="ordenEntregada"
									cssClass="largerCheckbox"
									onchange="javascript:checkOrdenEntregada()" />

							</div>
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
											<li><a data-toggle="tab" href="#tb_profesional">Medico
													Solicitante</a></li>
												<li><a data-toggle="tab" href="#tb_autorizacion">Autorizaci&oacute;n</a></li>												
											
											<li><a data-toggle="tab" href="#tb_prestador">Prestador
														Derivado</a></li>
											<li><a data-toggle="tab" href="#tb_observacion">
													Observaciones <c:if test="${observacionCount > 0}">
														<span class="badge" id="observacionCount">${observacionCount}</span>
													</c:if>
											</a></li>
											<sec:authorize access="hasRole('ROLE_ADMIN')">
												<li><a data-toggle="tab" href="#tb_historiaclinica">Historia
														Cl&iacute;nica</a></li>
											</sec:authorize>
											<li><a data-toggle="tab" href="#tb_coseguro">Coseguro</a></li>

										</ul>
										<!-- Fin Declaracion de tabs -->

										<!-- Contenedor de Tabs -->
										<div class="tab-content">
											<!-- ** Tab Paciente -->
											<div id="tb_paciente" class="tab-pane fade in active">
												<jsp:include page="formEditOrdenTabPaciente.jsp"></jsp:include>
											</div>

											<!-- ** Tab Requisitos -->
											<div id="tb_requisitos" class="tab-pane fade">
												<jsp:include page="formEditOrdenTabRequisitos.jsp"></jsp:include>
											</div>

											<!-- ** Tab Profesional -->
											<div id="tb_profesional" class="tab-pane fade">
												<jsp:include page="formEditOrdenTabProfesional.jsp"></jsp:include>
											</div>
											<!-- ** Tab Autorizaciones -->
												<div id="tb_autorizacion" class="tab-pane fade">
													<jsp:include page="formEditOrdenTabAutorizacion.jsp"></jsp:include>
												</div>												

											<!-- ** Tab Prestador -->
											<div id="tb_prestador" class="tab-pane fade">
												<jsp:include page="formEditOrdenTabPrestador.jsp"></jsp:include>
											</div>
											<!-- ** Tab Observaciones -->
											<div id="tb_observacion" class="tab-pane fade" style="">
												<jsp:include page="formEditOrdenTabObservaciones.jsp"></jsp:include>
											</div>
											<sec:authorize access="hasRole('ROLE_ADMIN')">
												<!-- ** Tab Historia Clinica -->
												<div id="tb_historiaclinica" class="tab-pane fade">
													<jsp:include page="formEditOrdenTabHistoriaClinica.jsp"></jsp:include>
												</div>
											</sec:authorize>
											<!-- ** Tab Coseguro -->
											<div id="tb_coseguro" class="tab-pane fade">
												<jsp:include page="formEditOrdenTabCoseguro.jsp"></jsp:include>
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
										onclick="location.href = '/nuova/mainOrdenPractica'"
										class="btn" />
								</div>
								<div style="float: right; padding-right: 2%;">
									<input class="btn btn-lg btn-primary btn-block btn-info"
										type="submit" value="  Guardar  " id="btn_submit" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Fin Botoneras -->

			</div>
		</div>

		<script>
		observacionCount = ${observacionCount};
		</script>
	</form:form>
</body>

<!-- Modal Nuevo Especialidad -->
<div id="modalEspecialidad" class="modal fade" role="dialog" style="visibility: hidden;">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Nueva Especialidad</h4>
      </div>
      <div class="modal-body custom-height-modal">
        <p>Nombre Especialidad: </p>
        <input type="text" id="especialidadNombre" name="especialidadNombre">
      </div>
      <div class="modal-footer">
      	<button type="button" id="btnSaveEspecialidad" class="btn btn-info">Guardar</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div>

  </div>
</div>
<!-- Fin Modal -->

<!-- Modal Nuevo Profesional -->
<div id="modalProfesional" class="modal fade" role="dialog" style="visibility: hidden;">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Nuevo Profesional</h4>
      </div>
      <div class="modal-body custom-height-modal">
      <div class="panel-body">
		<div class="container-fluid">
		  		<div class="row-fluid">
			   		<div class="span12">
				   		<div class="formLabel">Tipo:</div>
	        			<div class="formInput">
	        			<select id="tipo" name="tipo" style="width: 100%">
							<option value="-1">Seleccione Tipo ...</option>
							<option value="0">PROFESIONAL</option>
							<option value="1">INSTITUCION</option>
						</select>
	        			</div>
	  				</div>
	  			</div>
	  			<div class="row-fluid">
			   		<div class="span12">
			   			<div class="formLabel">Apellido o Intitucion:</div>
	        			<div class="formInput">
							<input type="hidden" name="profesional" id="profesional">	
							<input data-provide="typeahead" class="typeahead"
								name="apellidoRazonSocial" id="apellidoRazonSocial" type="text"
								placeholder="Ingrese Profesional ..." autocomplete="off"
								style="width: 96%">
	        			</div>
	  				</div>
	  			</div>
	  			<div class="row-fluid">
			   		<div class="span12">
			   			<div class="formLabel">Especialidad:</div>
	        			<div class="formInput">
		        			<input
							type="hidden" name="especialidadProf" id="especialidadProf"	> 
							<input
							data-provide="typeahead" class="typeahead"
							name="especialidadStringProf" id="especialidadStringProf"
							type="text" placeholder="Ingrese Especialidad ..."
							autocomplete="off" style="width: 96%">
	        			</div>
	  				</div>
	  			</div>
	  			<br><br><br><br><br><br><br><br>
	  	</div>
	  </div>		   		
      
      </div>
      <div class="modal-footer">
      	<button type="button" id="btnSaveProfesional" class="btn btn-info">Guardar</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div>

  </div>
</div>
<!-- Fin Modal -->


<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog"
	style="visibility: hidden;">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Comprobante de Orden</h4>
			</div>
			<div class="modal-body custom-height-modal">
				<div id="iframeReport" style="height: 800px"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
			</div>
		</div>

	</div>
</div>
<!-- Fin Modal -->
</html>
<script>

document.getElementById("mainOrden").parentNode.classList.add("active");
$(".checkbox").checkbox();

function showReport(id){
	$("#myModal").css("visibility", "visible");
	var iframe = "<iframe src='/nuova/reporteOrdenEmitida/"+id+"' width='100%' height='150%' >";
	document.getElementById("iframeReport").innerHTML = iframe;
}	

function validaRequerido() {
	window.scrollTo(0,0);
	var requireds = ['reqOrdenMedico: Presentó la orden Práctica del médico solicitante?'
					, 'reqCredecial: Presentó la credencial de la prestadora OSPSIP?'
					, 'reqMonotributista: Presentó fotocopia de los 3 último recibos como Monotributista o Ama de Casa?'
					];
	
	if ($("#fueraCartilla").is(':checked')) {
		requireds.push('entidad:Entidad o Procedencia');

	}else {
		requireds.push('especialidad:Especialidad','profesionalId: Profesional');	
	}	

	requireds.push('especialidadPrestadorString: Prestador Derivado/Especialidad', 'prestadorId: Prestador Derivado/Profesional');
	
	return isValidForm("validacion_requeridos", requireds);			
}

function disabledSubmit() {
	var retorno = true;
	if ( !validaRequerido() ) {
		retorno = false;
	} else {
		document.getElementById("btn_submit").disabled = true;				

	}
	
	return retorno;
}
</script>