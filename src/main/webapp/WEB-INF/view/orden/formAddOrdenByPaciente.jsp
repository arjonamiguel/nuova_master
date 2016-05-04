<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Nuova</title>
		<link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/img/favicon/favicon.ico">
		
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>   
		<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
		<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
		<script src="<%=request.getContextPath()%>/resources/js/jquery/bootstrap-collapse.js" /></script>
		<link href="<%=request.getContextPath()%>/resources/css/nuova.css" rel="stylesheet"/>
		<link href="<%=request.getContextPath()%>/resources/css/panel.css" rel="stylesheet"/>
		<link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap-responsive.css" rel="stylesheet"/>
		<script src="<c:url value="/resources/js/jquery/jquery.validate.min.js" />"></script>
		<link href="<%=request.getContextPath()%>/resources/montrezorro-bootstrap-checkbox-fa865ff/css/bootstrap-checkbox.css" rel="stylesheet"/>
		<script src="<%=request.getContextPath()%>/resources/montrezorro-bootstrap-checkbox-fa865ff/js/bootstrap-checkbox.js" /></script>
	
	<style>
	.chkbox {
   padding-left: 10px;
   font-weight: bold;
 }
 .label-error {
		  color: #a94442;
		  background-color: #f2dede;
		  border-color: #ebccd1;
		  padding:1px 20px 1px 20px;
		  width:22%;
		}
	
	</style>
<script>

	var checkBoxSelectedFlag="false";
	
	$(document).ready(function() {
		var especialidadTH = $('#especialidadString.typeahead');
		var nomencladorTH = $('#nomencladorString.typeahead');

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

	});

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

	
	function validatedSelects() {
		$("#message2").css("visibility", "hidden");

		if ($(".cb-icon-check")[0].style.display != "none"
			&& $(".cb-icon-check")[1].style.display != "none") {
			$("#message2").css("visibility", "hidden");
			if (checkBoxSelectedFlag == "false") {
				$("#message2").text(
						"Falta chequear el penultimo o ultimo requisito");
				$("#message2").css("visibility", "visible");
				return false;
			}
		} else {
			$("#message2").text(
			"Falta chequear los 2 requisitos principales");
			$("#message2").css("visibility", "visible");
			return false;
		}
		
		
		if ($("#especialidad").val() == '') {
				$("#message2").text("Debe Seleccionar una Especialidad");
				$("#message2").css("visibility", "visible");
				return false;	
		} 
		
		if ($("#profesionalId").val() == '-1') {
			$("#message2").text("Debe Seleccionar un Profesional");
			$("#message2").css("visibility", "visible");
			return false;
		}
		
// 		if ($("#monto").val() == '') {
// 			$("#message").css("visibility", "visible");
// 			return false;
// 		}else {
// 			return validaFloat($("#monto").val());
// 		}

		return true;
	}
	function hideMessage() {
		$("#message").css("visibility", "hidden");
	}
	function monotributistaSelected(){
		if(checkBoxSelectedFlag=="false")
		{
			checkBoxSelectedFlag="true";
		}else{
			checkBoxSelectedFlag="false";
		}
		
		if($(".cb-icon-check")[3].style.display!="none")
		{
			$("#reqReciboSueldo").click();
		}
		
	}
	
	function recibosueldoSelected(){
		if(checkBoxSelectedFlag=="false")
		{
			checkBoxSelectedFlag="true";
		}else{
			checkBoxSelectedFlag="false";
		}
		if($(".cb-icon-check")[2].style.display!="none")
		{
			$("#reqMonotributista").click();
		}
	}
	
	  function findEspecialidades(profesional) {
		  var especialidades = callEspecialidadesByProfesional(profesional.value);
			$('#especialidad')
			.empty()
		    .append('<option selected="selected" value="-1">Seleccione Especialidad ...</option>')
		;
			$.each(especialidades, function(key, value) {   
			     $('#especialidad')
			          .append($('<option>', { value : value.id })
			          .text(value.value)); 
			});
		  
	  } 
	  
	  function callEspecialidadesByProfesional(profesionalId) {
			var retorno;
			$.ajax({
				url : "/nuova/ajaxGetEspecialidadesByProfesional?profesionalId="+profesionalId,
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
	
</head>

<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<form:form method="post" action="/nuova/addOrden" commandName="ordenDto">
<form:hidden path="ordenTipo.ordenTipoId"/>
<div class="mainContainer">

<div class="panelContainer">		
		<div class="panel panel-info">
			<div class="panel-heading">
          			<div class="panel-title">
	          			Nueva Práctica
          			</div>
          			<div class="label-error" id="message2" style="float:left;margin-left:8%;visibility:hidden;">Faltan chequear los dos primeros requisitos</div>
    		</div>     
			<div  class="panel-body" >
				<div class="container-fluid" >
	  				<div class="row-fluid" >
		    				<div class="span12">
		    					<div class="tableContainer">	
		    						<div class="row col-md-6 col-md-offset-2 custyle">
										<ul class="nav nav-tabs">
										  <li class="active"><a data-toggle="tab" href="#tb_paciente">Paciente</a></li>
										  <li><a data-toggle="tab" href="#tb_requisitos">Requisitos</a></li>
										  <li><a data-toggle="tab" href="#tb_profesional">Profesional</a></li>										  	  
										  <li><a data-toggle="tab" href="#tb_observacion">Observaciones</a></li>
										</ul>
									
										<div class="tab-content" style="height: 350px">
									  		<div id="tb_paciente" class="tab-pane fade in active">
									  		<table class="table">
												
												<tr>
													<td><form:label path="paciente.dni">DNI</form:label></td>
													<td><form:input path="paciente.dni" disabled="true"/></td>
													<td><form:label path="paciente.apellido">Apellido</form:label></td>
													<td>
														<form:hidden path="paciente.pacienteId"/>
														<form:input path="paciente.apellido" disabled="true"/>
													</td>
													<td><form:label path="paciente.nombre">Nombre</form:label></td>
													<td><form:input path="paciente.nombre" disabled="true"/></td>
												</tr>
												
												<tr>
													<td><form:label path="paciente.obrasocial.nombre">Obra Social</form:label></td>
													<td><form:input path="paciente.obrasocial.nombre" disabled="true"/></td>	
													<td><form:label path="paciente.obrasocial.credencial">Credencial</form:label></td>			
													<td><form:input path="paciente.obrasocial.credencial" disabled="true"/></td>
													<td></td><td></td>
												</tr>
												
											</table>  		   
									 		</div>
									  
									  		<div id="tb_requisitos" class="tab-pane fade">
									  		<table class="table" style="width: 100%">
										  		<tr>
										  		<td style="width:60%;"><div style="float:right;">Seleccionar todos:</div></td>
										  		<td><div style="float:left;"><input id="selectAll" type="checkbox" class="largerCheckbox" onchange="javascript:marcarTodos()"></div></td>
										  		</tr>
										  		</table>
											<table class="table" style="width: 100%">
												
												<tr>
													
													<td colspan="4" style="width:60%">
														<b>Presentó la orden Práctica del médico solicitante?</b>
													</td>
													<td  style="text-align:left" colspan="2">			
													    <input type="checkbox" id="reqOrdenMedico" name = "reqOrdenMedico" type="checkbox" class="largerCheckbox"/>
													</td>
												</tr>
												<tr>
													
													<td colspan="4">
														<b>Presentó la credencial de la prestadora OSPSIP?</b>
													</td>
													<td  style="text-align:left" colspan="2">			
													    <input type="checkbox" id="reqCredecial" name="reqCredecial" type="checkbox" class="largerCheckbox"/>
													</td>
												</tr>
									
											</table>
											
											<table class="table" style="width: 100%">
												<tr>			
													<td colspan="4" style="width:60%">
														<b>Presentó fotocopia de los 3 último recibos como Monotributista o Ama de Casa?</b>
													</td>
													<td  style="text-align:left" colspan="2">			
													    <input type="checkbox" id="reqMonotributista" name="reqMonotributista" type="checkbox" class="largerCheckbox" onchange="monotributistaSelected()"/>
													</td>
												</tr>	
												<tr>
													
													<td colspan="4">
														<b>Presentó fotocopia del último recibo de sueldo?</b>
													</td>
													<td  style="text-align:left" colspan="2">			
													    <input type="checkbox" id="reqReciboSueldo" name="reqReciboSueldo" type="checkbox" class="largerCheckbox" onchange="recibosueldoSelected()"/>
													</td>
												</tr>
									
											</table>	
									  		</div>
									  		
									  		<div id="tb_profesional" class="tab-pane fade">
									    		<table class="table"  style="width: 100%">			
												<tr>		
													<td style="width: 15%"><form:label path="especialidad">Especialidad</form:label></td>
			<td style="text-align: left" colspan="5">
				<input type="hidden"
				name="especialidad" id="especialidad"
				value="${ordenDto.especialidad}"> 
				
				<input
				data-provide="typeahead" 
				class="typeahead" 
				name="especialidadString"
				id="especialidadString"
				type="text" 
				placeholder="Ingrese Especialidad ..." 
				autocomplete="off"
				value="${especialidadView}"> 
				
				<a
				href="/nuova/formAddEspecialidad" title="Nueva Especialidad" target="_blank"> <img
					src="/nuova/resources/img/list_add_16x16.png">
			</a></td>
			<td style="width: 15%"><form:label path="profesionalId">Profesional</form:label></td>
			<td style="text-align: left" colspan="5"><form:select
					path="profesionalId" style="width:80%; margin-bottom:0px">
					<form:option value="-1" label="Seleccione Profesional ..." />
					<form:options items="${profesionales}" itemLabel="value"
						itemValue="id" />
				</form:select></td>
													
												</tr>		
												</table>
									  		</div>
									  		 		
									  		<div id="tb_observacion" class="tab-pane fade">
									    		<table class="table"  style="width: 100%">			
												<tr>		
													<td style="width: 15%"><form:label path="observacion">Observación</form:label></td>
													<td  style="text-align:left" colspan="5">			
													    <form:textarea path="observacion" cssStyle="width:60%"/>
													</td>
												</tr>		
												</table>
									  		</div>
										</div>
										
									</div> 		    						
									
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
					<div style="float:right;">
						<input type="button" value="Cancelar" onclick="location.href = document.referrer; return false;" class="btn"/>	
					</div>
					<div style="float:right;padding-right:2%;">
						<input type="submit" value="Guardar" class="btn btn-info"/>
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
		document.getElementById("mainPaciente").parentNode.classList.add("active");
		$(".checkbox").checkbox();
			$("#ordenDto").validate({
    
		        // Specify the validation rules
		        rules: {
		        	dni: {
		                required: true,
		                minlength: 7
		            },
		            apellido: "required",
		            nombre: "required"
		        },
		        
		        // Specify the validation error messages
		        messages: {
		            apellido: "Ingrese apellido",
		            nombre: "Ingrese nombre",
		            dni: {
		                required: "Ingrese DNI",
		                minlength: "DNI debe tener al menos 7 caracteres de largo"
		            }
		        },
		                submitHandler: function(form) {
		             	if(validatedSelects()){
		            		form.submit();
		            	}
		        }
		    });
		
	
</script>