<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" session="true" contentType="text/html;charset=latin1"%>    
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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

<!-- 	Configuracion del paginador -->
<link href="<%=request.getContextPath()%>/resources/simplepaginggrid/css/simplePagingGrid-0.4.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/simplepaginggrid/examples/pageNumbers/script/handlebars-1.0.rc.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/simplepaginggrid/script/simplePagingGrid-0.5.0.2.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
												
						var rows = [];

						rows = callPaciente();

						$("#pacienteGrid").simplePagingGrid(
								{
									columnNames : [ "ID", "DNI", "APELLIDO", "NOMBRE","ESTADO",  "" ],
									columnKeys : [ "pacienteId", "dni",
											"apellido", "nombre", "eliminadoView" ,"acciones" ],
									columnWidths : [ "5%", "10%", "20%", "20%","20%"],
									sortable : [ false, true, true, true ],
									data : rows
								});

						$('input#search')
								.bind(
										'keypress',
										function(event) {
											var keycode = (event.keyCode ? event.keyCode
													: event.which);
											if (keycode == '13') {
												var search = document
														.getElementById("search").value;
												// rows = callSearchPaciente(search);
												rows.length = [];
												$("#pacienteGrid")
														.simplePagingGrid(
																"refresh");
												// rows.push(callSearchPaciente(search));
												// rows = rows[0];
												var arr = callSearchPaciente(search);
												$.each(arr, function(index,
														value) {
													rows.splice(0, 0, value);

												});
												$("#pacienteGrid")
														.simplePagingGrid(
																"refresh");

											}
										});
						
					});
					
					$(function() {
			    		$('.btn-info').tooltip({
			        	placement: 'bottom',
			        	title: 'Editar Datos del Paciente'
			        	});
					});
					$(function() {
			    		$('.btn-success').tooltip({
			        	placement: 'bottom',
			        	title: 'Generar una Orden para este Paciente'
			        	});
					});
					$(function() {
			    		$('.btn-danger').tooltip({
			        	placement: 'bottom',
			        	title: 'Eliminar Paciente'
			        	});
					});
					
function activarPaciente(id) {
	var resp = callActivarPaciente(id);
	if (resp == "1") {
		alert("Activacion procesada con exito.\nSe procedera a recargar la p�gina...");
		window.location.href = "/nuova/mainPaciente";
	}else {
		alert("ERROR: No se pudo activar el paciente");
	}
}					
</script>
<!-- 	Fin Configuracion del paginador -->

<style type="text/css">
.custab {
	border: 1px solid #ccc;
	padding: 5px;
	margin: 5% 0;
	box-shadow: 3px 3px 2px #ccc;
	transition: 0.5s;
}

.custab:hover {
	box-shadow: 3px 3px 0px transparent;
	transition: 0.5s;
}

</style>

</head>
<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>

<div class="mainContainer"> 	  
	<div class="panelContainer">		
		<div class="panel panel-info">
			<div class="panel-heading">
          			<div class="panel-title">
	          			<b>Administracion de Pacientes</b>
	           			<a href="formAddPaciente" class="pull-right"><b>+</b>&nbsp;&nbsp;Nuevo Paciente</a>
          			</div>
    		</div>     
			<div  class="panel-body" >
				<div class="container-fluid" >
	  				<div class="row-fluid" >
		    				<div class="span12">
		    						
										<section id="main">        
										<div class="container-fluid">	
											<div>		
										    <input type="text" 
										    	style="width: 50%"
										    	name="search" 
										    	id="search"			    	 
										    	class="form-control input-lg"  
										    	placeholder="USTED PUEDE FILTRAR POR APELLIDO O NOMBRE ..."										    	
										   	/>
										   	<span id = "wait" style="visibility: hidden; padding-left: 5px">Buscando ...</span>			
											</div>
											<div class="row-fluid">
												<div id="pacienteGrid"></div>
											</div>
										</div>
							        </section>
								
		    				</div>
		    		</div>
		    	</div>
	    	</div>
	    </div>
</div>
	
</div>	 

</body>
</html>

<script type="text/javascript">

	document.getElementById("mainPaciente").parentNode.classList.add("active");
	 
	function callPaciente() {
		var retorno;
		$.ajax({
			url : "ajaxGetPacientesPaginados",
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

	function callSearchPaciente(search) {
		var retorno;
		$.ajax({
			url : "ajaxGetSearchPacientesPaginados?search=" + search,
			type : "GET",
			contentType : "application/json; ;charset=ISO-8859-15",
			//    data: jsonString, //Stringified Json Object
			async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
			cache : false, //This will force requested pages not to be cached by the browser          
			processData : false, //To avoid making query String instead of JSON
			 beforeSend: loadStart,
			 complete: loadStop,		
			success : function(pagesearch) {
				retorno = pagesearch.content;
			}
		});

		return retorno;
	}
	
	function callActivarPaciente(id) {
		var retorno;
		$.ajax({
			url : "/nuova/ajaxPostActivarPaciente/"+id,
			type : "POST",
			contentType : "application/json; charset=utf-8",
			// data: jsonEmpresa, //Stringified Json Object
			async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
			cache : false, //This will force requested pages not to be cached by the browser          
			processData : false, //To avoid making query String instead of JSON
			success : function(result) {
				retorno = result;
			}
		});

		return retorno;
	}
	
	function loadStart() {
		$('#wait').css("visibility","visible");
	}
	function loadStop() {
		$('#wait').css("visibility","hidden");
	}
</script>