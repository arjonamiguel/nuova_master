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
	
<script type="text/javascript">
$(document).ready(function() {
	var map = new Object();
	var objects = [];

	$('#searchPaciente').keypress(function(event){
	    var keycode = (event.keyCode ? event.keyCode : event.which);
	    if(keycode == '13'){
	    	goInfoPaciente();  
	    }
	});
	
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
			// setInterval(function(){ goInfoPaciente(); }, 1500);
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
<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<div class="mainContainer"> 	
	<div class="panelContainer">
			
		<div class="panel panel-info">
			<div class="panel-heading">
          			<div class="panel-title">
	          			<b>Busqueda de Paciente</b>
	          			<a href="formAddPaciente" class="pull-right" target="_blank"><b>+</b>&nbsp;&nbsp;Nuevo Paciente</a>	          			
          			</div>
    		</div>
    		
			<div  class="panel-body" >
				<div class="container-fluid" >
				  	<div class="row-fluid">
				  	<div style="margin: auto;width: 100%; padding-left: 20%;">
										<input type="hidden" name="pacienteId" id="pacienteId" value="">										
											<input
												data-provide="typeahead" 
												class="typeahead"
												name="searchPaciente"
												type="text"
												id="searchPaciente"
												placeholder="Ingresar DNI o el Apellido ..."
												autocomplete="off"
												style="height: 40px; width: 50%;margin-top: 10px">

										<button id="breadButton" href="" 
										class="btn btn-info" 
										style="margin-top: 0px; height: 50px;"
										onclick="goInfoPaciente()">
										<i class="ico icon-user">
										</i>&nbsp;Buscar Paciente
										</button>				
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
document.getElementById("mainPaciente").parentNode.classList.add("active");



</script>