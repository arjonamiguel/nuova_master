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


<!-- 	Configuracion del paginador -->
<link
	href="<%=request.getContextPath()%>/resources/simplepaginggrid/css/simplePagingGrid-0.4.css"
	rel="stylesheet">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/simplepaginggrid/examples/pageNumbers/script/handlebars-1.0.rc.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/simplepaginggrid/script/simplePagingGrid-0.5.0.2.js"></script>


<script type="text/javascript">
$(document).ready(function() {	
	var rowsConsultas = [];
	var rowsPracticas = [];
	var rowsReintegros = [];
	// var rowsObservaciones = [];
	rowsConsultas = callConsultas();
	rowsPracticas = callPracticas();
	rowsReintegros = callReintegros();
	// rowsObservaciones = callObservaciones();
	//rowsObservaciones= [{'observacionId':'1', 'observacion':'hola gus','fecha':'24/05/2016'}];

	$("#consultasGrid").simplePagingGrid(
			{
				columnNames : [ "NRO.ORDEN","PACIENTE","TIPO" , "FECHA", "MEDICO SOLICITANTE" ,"ESPECIALIDAD", "" ],
				columnKeys : [ "nroOrden", "botonpaciente","ordenTipoDesc" , "fecha","apellidoNombreProfesional"
								, "especialidadView", "acciones"],
				columnWidths : [ "10%", "20%"],
				sortable : [ true, true,],
				data : rowsConsultas,
				pageSize : 5,
				minimumVisibleRows: 5
			});

	$("#practicasGrid").simplePagingGrid(
			{
				columnNames : [ "NRO.ORDEN","FECHA","SOLICITANTE","PRACTICAS", ""],
				columnKeys : [ "nroOrden","fecha","apellidoNombreProfesional","practicas"
				 					, "acciones"],
				columnWidths : [ "12%", "10%", "20%"],

				sortable : [ true, true,],
				data : rowsPracticas,
				pageSize : 5,
				minimumVisibleRows: 5
			});

	$("#reintegrosGrid").simplePagingGrid(
			{
				columnNames : [ "INICIO", "REINTEGRO" ,"MEDICO SOLICITANTE" , "ESTADO", "MONTO", "" ],
				columnKeys : [ "fechaDesde", "fechaReintegro","profesional","estadoView" , "monto","acciones"],
				columnWidths : [ "10%", "10%"],
				sortable : [ true, true,],
				data : rowsReintegros,
				pageSize : 5,
				minimumVisibleRows: 5
			});
			

});

function nuevoAdherente() {
	var titularId = document.getElementById("pacienteId").value;
	url = "/nuova/formAddAdherente/"+titularId;
	window.open(url, '_blank');
}

function createOrden(ordenTipoId){
	var pacienteId = document.getElementById("pacienteId").value;
	var url = "/nuova/createOrden/"+ordenTipoId+"/"+pacienteId;
	window.open(url, '_blank');
}

function showReport(id){
	var iframe = "<iframe src='/nuova/reporteOrdenEmitida/"+id+"' width='100%' height='150%' >";
	document.getElementById("iframeReport").innerHTML = iframe;
	document.getElementById("myModal").style.height = '60%';
}

function createReintegro(){
	var pacienteId = document.getElementById("pacienteId").value;
	var url = "/nuova/formAddReintegro/"+pacienteId;
	window.open(url, '_blank');
}

$(function(){
		
			$(document.body).on('click', '.changeType' ,function(){
				$(this).closest('.phone-input').find('.type-input').val($(this).data('type-value'));
			});
			
			$(document.body).on('click', '.btn-remove-phone' ,function(){
				var id=$(this).closest('.phone-input').find('.form-control').attr('name');
				deleteObservaciones('delete',id);
				
			});
			
			
			$('.btn-add-phone').click(function(){
				$("#btnGuardarObs").attr("disabled",false);
				var index =$(".form-control").length;
				
				$('.phone-list').append(''+
						'<div class="input-group phone-input">'+
							'<textarea type="text" name="'+index+'" class="form-control" placeholder="Ingrese Observacion" style="width:60.5%;margin-bottom:3px;"/></textarea>'+
							'<input type="hidden" name="phone['+index+'][type]" class="type-input" value="" />'+
							'<span class="input-group-btn" style="padding-left:1%;">'+
								'<button class="btn btn-danger btn-remove-phone" type="button"><span class="icon icon-remove"></span>Eliminar</button>'+
							'</span>'+
						'</div>'
				);
				
				$('.btn-add-phone').attr("disabled","disabled");

			});
			
		});
		
		
	function updatePhones(){
	
		var jsonPhones='';
		jsonPhones=$("#jsonObservaciones").html();
		if(jsonPhones.length>2){
			if(jsonPhones[jsonPhones.length-2]==","){
				var str=jsonPhones;
				var newStr = str.substring(0, str.length-2);
				jsonPhones=newStr+"]";
			}
		}
	
		var obj = jQuery.parseJSON( jsonPhones);
		
		$(obj).each(function(index, element) {
    		var id=element.id;
    		var value=element.value;
    		
    		var trimStr=value;
			//trimStr=trimStr.replace(/\s/g, "&nbsp;");
    		
    		$('.phone-list').append(''+
						'<div class="input-group phone-input">'+
							'<textarea disabled="disabled" name="'+index+'" class="form-control" placeholder="Ingrese Observacion" style="width:60.5%;margin-bottom:3px;">'+trimStr+'</textarea>'+
							'<span class="input-group-btn" style="padding-left:1%;">'+
								'<button class="btn btn-danger btn-remove-phone" type="button"><span class="icon icon-remove"></span>Eliminar</button>'+
							'</span>'+
						'</div>'
				);
		});

	}
	
	function saveObservaciones(operacion,observaciones) {
		var retorno;
		var dni = $("#pacienteDni").html();
			$.ajax({
				url : '/nuova/ajaxObservaciones?operacion='+operacion+'&dni=' + dni + '&observaciones=' +observaciones,
				type : "GET",
				contentType : "application/json; charset=utf-8",
				//    data: jsonString, //Stringified Json Object
				async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
				cache : false, //This will force requested pages not to be cached by the browser          
				processData : false, //To avoid making query String instead of JSON
					
				success : function(success) {
					retorno = success;
					location.reload();
				}
		});
	
		return retorno;
}

	function deleteObservaciones(operacion,observaciones) {
		var retorno;
		var dni = $("#pacienteDni").html();
			$.ajax({
				url : '/nuova/ajaxObservaciones?operacion='+operacion+'&dni='+dni+'&observaciones=' + observaciones,
				type : "GET",
				contentType : "application/json; charset=utf-8",
				//    data: jsonString, //Stringified Json Object
				async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
				cache : false, //This will force requested pages not to be cached by the browser          
				processData : false, //To avoid making query String instead of JSON
					
				success : function(success) {
					retorno = success;
					location.reload();
				}
		});
	
		return retorno;
}


function procesarSubmit()
	{
		var jsonPhones='';
		var line='';
		var user = $("#usuario").html();
		var date = $("#fechaSistema").html();
		$( '.form-control' ).each(function( index ) {
			var trimStr=$( this ).val();
			if($( this ).attr("disabled")!="disabled"){
				line= '{"id":"'+index + '", "value":"' +user+'('+date+'):'+ trimStr+'"},';
			}
  			jsonPhones=jsonPhones+line;
		});
		var str=jsonPhones;
		var newStr = str.substring(0, str.length-1);
		jsonPhones=newStr;
		//jsonPhones='['+jsonPhones+']';
		saveObservaciones('edit',jsonPhones);
	}
</script>
</head>
<body style="background-color: #e5e5e5;">
	<jsp:include page="../sec_menu.jsp"></jsp:include>
	<div class="mainContainer">
		<div class="panelContainer">
			<div class="panel panel-info">
				<input type="hidden" value="${paciente.pacienteId}" id="pacienteId">
				<div id="jsonObservaciones" style="visibility:hidden;height:0px;">${paciente.observaciones}</div><div id="fechaSistema" style="visibility:hidden;height:0px;">${fechaSistema}</div><div id="usuario" style="visibility:hidden;height:0px;">${usuario}</div>
				<div class="panel-heading">
					<div class="panel-title">Informacion del Paciente</div>
				</div>

				<div class="panel-body">
					<div class="container-fluid">
						<table class="table" border=1>
							<tr>
								<td rowspan="4" width="130"><img alt=""
									src="/nuova/resources/img/user_128x128.png"></td>
								<td colspan="2">
									<div class="panel-title">${paciente.apellido}
										${paciente.nombre}
										<a class="btn btn-info btn-xs" href="/nuova/formEditPaciente/${paciente.pacienteId}" title="Editar" target="_blank"
										> 
											<span class="icon icon-edit" title="Editar"></span>
										</a>
										
										<c:if test="${paciente.parentescoVO > 0}">
							     			<h5>Titular: <a href="/nuova/formInfoPaciente/${paciente.titularId}" target="_blank">${paciente.infoTitular} </a></h5>
							     		</c:if>
										
										</div>
								</td>

								<td>Estado</td>
								<td><c:if test="${paciente.eliminado == 0}">
										<span
											style="background: green; padding: 2px 4px 2px 4px; color: white;">ACTIVO</span>
									</c:if> <c:if test="${paciente.eliminado == 1}">
										<span style="background: red; padding: 2px 4px 2px 4px;">INACTIVO</span>
									</c:if></td>

							</tr>
							<tr>
								<td width="150">D.N.I.</td>
								<td width="400" id="pacienteDni">${paciente.dni}</td>
								<td width="100">Tipo Afiliado</td>
								<td>${paciente.parentescoDescripcion}</td>
							</tr>
							<tr>
								<td>Fecha Nacimiento</td>
								<td>${paciente.fechaNacimientoFormateada}</td>
								<td>Obra Social</td>
								<td>${paciente.obrasocial}</td>
							</tr>
							<tr>
								<td>Domicilio</td>
								<td>${paciente.domicilio}</td>
								<td>Nro. Credencial</td>
								<td>${paciente.credencial}</td>
							</tr>
						</table>

						<!-- Declaracion de tabs -->
						<ul class="nav nav-tabs">
							<c:if test="${paciente.parentescoVO == 0}">
							<li class="active"><a data-toggle="tab"
								href="#tb_adherentes"><b>Adherentes</b></a></li>
								<li><a data-toggle="tab" href="#tb_consultas"><b>Consultas</b></a></li>
							</c:if>	
							<c:if test="${paciente.parentescoVO > 0}">
								<li class="active"><a data-toggle="tab" href="#tb_consultas"><b>Consultas</b></a></li>
							</c:if>	
							<li><a data-toggle="tab" href="#tb_practicas"><b>Practicas</b></a></li>
							<li><a data-toggle="tab" href="#tb_reintegros"><b>Reintegros</b></a></li>
							<li><a data-toggle="tab" href="#tb_observaciones"><b>Observaciones</b></a></li>
						</ul>
						<!-- Fin Declaracion de tabs -->

						<!-- Contenedor de Tabs -->
						<div class="tab-content">
							<c:if test="${paciente.parentescoVO == 0}">
							<!-- ** Tab Adhenrentes -->
							<div id="tb_adherentes" class="tab-pane fade in active">
								<jsp:include page="formInfoPacienteTabAdherente.jsp"></jsp:include>
							</div>
							<!-- ** Tab Consultas -->
							<div id="tb_consultas" class="tab-pane fade">
								<jsp:include page="formInfoPacienteTabConsultas.jsp"></jsp:include>
							</div>
							</c:if>
							
							<c:if test="${paciente.parentescoVO > 0}">

							<!-- ** Tab Consultas -->
							<div id="tb_consultas" class="tab-pane fade in active">
								<jsp:include page="formInfoPacienteTabConsultas.jsp"></jsp:include>
							</div>
							</c:if>

							<!-- ** Tab Practicas -->
							<div id="tb_practicas" class="tab-pane fade">
								<jsp:include page="formInfoPacienteTabPracticas.jsp"></jsp:include>
							</div>

							<!-- ** Tab Reintegros -->
							<div id="tb_reintegros" class="tab-pane fade">
								<jsp:include page="formInfoPacienteTabReintegros.jsp"></jsp:include>
							</div> 
							<!-- ** Tab Observaciones -->
 							<div id="tb_observaciones" class="tab-pane fade"> 
 								<jsp:include page="formInfoPacienteTabObservaciones.jsp"></jsp:include>
 							</div> 
						</div>
						<!-- Fin Contenedor de Tabs -->

					</div>
				</div>
			</div>
			<div class="panel panel-info">
				<div class="panel-body">
					<div class="row-fluid">
						<div class="span12">
							<div style="float: right;">
								<input type="button" value="Cancelar"
									onclick="location.href='/nuova/formBuscarPaciente';"
									class="btn" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog" style="width: 50%;">
		<div class="modal-dialog" style="height: 100%">
			<!-- Modal content-->
			<div class="modal-content" style="height: 80%">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Comprobante</h4>
				</div>
				<div class="modal-body" style="height: 110%">
					<div id="iframeReport" style="height: 110%"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Fin Modal -->

</body>
</html>
<script>
document.getElementById("mainPaciente").parentNode.classList.add("active");
function callConsultas() {
	var retorno;
	$.ajax({
		url : "/nuova/ajaxGetConsultasByPacientePaginados/${paciente.pacienteId}",
		type : "GET",
		contentType : "application/json; charset=utf-8",
		//    data: jsonString, //Stringified Json Object
		async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
		cache : false, //This will force requested pages not to be cached by the browser          
		processData : false, //To avoid making query String instead of JSON
		success : function(page) {
			// Success Message Handler
			retorno = page.content;
		}
	});

	return retorno;
}

function callPracticas() {
	var retorno;
	$.ajax({
		url : "/nuova/ajaxGetPracticasByPacientePaginados/${paciente.pacienteId}",
		type : "GET",
		contentType : "application/json; charset=utf-8",
		//    data: jsonString, //Stringified Json Object
		async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
		cache : false, //This will force requested pages not to be cached by the browser          
		processData : false, //To avoid making query String instead of JSON
		success : function(page) {
			// Success Message Handler
			retorno = page.content;
		}
	});

	return retorno;
}

function callReintegros() {
	var retorno;
	$.ajax({
		url : "/nuova/ /${paciente.pacienteId}",
		type : "GET",
		contentType : "application/json; charset=utf-8",
		//    data: jsonString, //Stringified Json Object
		async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
		cache : false, //This will force requested pages not to be cached by the browser          
		processData : false, //To avoid making query String instead of JSON
		success : function(page) {
			// Success Message Handler
			retorno = page.content;
		}
	});

	return retorno;
}
updatePhones();
</script>