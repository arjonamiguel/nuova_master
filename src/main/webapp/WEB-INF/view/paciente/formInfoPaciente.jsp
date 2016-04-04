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

function callMovimientosCaja(tipo) {
	var retorno;
	$.ajax({
		url : "ajaxGetMovimientoCaja?tipoMovimiento="+tipo,
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

function getConceptos(tipo) {
	var conceptos = callMovimientosCaja(tipo.value);
	$('#concepto')
	.empty()
    .append('<option selected="selected" value="-1">Seleccione Concepto ...</option>')
;
	$.each(conceptos, function(key, value) {   
	     $('#concepto')
	          .append($('<option>', { value : value.id })
	          .text(value.value)); 
	});
}

function updateDate(){
	document.getElementById("fechaMovimiento").value=document.getElementById("fecha_Movimiento").value;
}

function nuevoAdherente() {
	var titularId = document.getElementById("pacienteId").value;
	window.location.href = "/nuova/formAddAdherente/"+titularId;
}

</script>

</head>
<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<div class="mainContainer"> 	
	<div class="panelContainer">
		<div class="panel panel-info">
		<input type="hidden" value = "${paciente.pacienteId}" id="pacienteId">
		
			<div class="panel-heading">
          			<div class="panel-title">
	          			Informacion del Paciente
          			</div>
    		</div>
    		
			<div  class="panel-body" >
				<div class="container-fluid" >
				<table class="table" border = 1>
				<tr>
					<td rowspan="4" width="130">
						<img alt="" src="/nuova/resources/img/user_128x128.png">
					</td>
					<td colspan="4">
						<div class="panel-title">${paciente.apellido} ${paciente.nombre}</div>
					</td>					
					
				</tr>
				<tr >
					<td width="150">D.N.I.</td>
					<td width="400">${paciente.dni}</td>
					<td width="100">Tipo Afiliado</td>
					<td>${paciente.parentescoDescription}</td>
				</tr>
				<tr >
					<td>Fecha Nacimiento</td>
					<td>${paciente.fechaNacimiento}</td>
					<td>Obra Social</td>
					<td>${paciente.obrasocial.nombre}</td>
				</tr>
								<tr >
					<td>Domicilio</td>
					<td>${paciente.domicilio}</td>
					<td>Nro. Credencial</td>
					<td>${paciente.obrasocial.credencial}</td>
				</tr>
				</table>
				
				<!-- Declaracion de tabs -->
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#tb_adherentes" ><b>Adherentes</b></a></li>
								<li><a data-toggle="tab" href="#tb_consultas" ><b>Consultas</b></a></li>
								<li><a data-toggle="tab" href="#tb_practicas" ><b>Practicas</b></a></li>								
								<li><a data-toggle="tab" href="#tb_reintegros" ><b>Reintegros</b></a></li>
							</ul>
							<!-- Fin Declaracion de tabs -->							
							
							<!-- Contenedor de Tabs -->
							<div class="tab-content">
								<!-- ** Tab Adhenrentes -->
								<div id="tb_adherentes" class="tab-pane fade in active">							
								
<c:if test="${paciente.parentesco == 0}">
<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Adherentes</div>
				</div>
				<div class="panel-body">
					<div class="row-fluid">
						<div class="span12">
						<div style="text-align: right;">
		    <INPUT type="button" value="Nuevo Adherente" onclick="nuevoAdherente()" class="btn btn-success"/>
			</div>
		     <TABLE id="dataTableAdherente" class="table table-striped custab" style="width: 100%; margin: 1% 0">
		        <TR>
		        	
		            <TD>ID</TD>
		            <TD>DNI</TD>
		            <TD>Apellido</TD>        
		            <TD>Nombre</TD>
		            <TD>Credencial</TD>
		            <TD>Parentesco</TD>
		            <TD></TD>
		        </TR>
		         <% int index2 = 0;%>
		        <c:forEach items="${paciente.adherentes}" var="adh" varStatus="loop" >
		    	<tr>
			    	
			        <td>${adh.pacienteId}<input type="hidden" name = "adherentesEditList[<%=index2%>].pacienteId" value = "${adh.pacienteId}" /> </td>
			        <td>${adh.dni}</td>        
			        <td>${adh.apellido}</td>
			        <td>${adh.nombre}</td>
			        <td>${adh.crdencial}</td>
			        <td>${adh.parentescoDescription}</td>
			        <td>
			        	<a class="btn btn-info btn-xs" href="/nuova/formEditPaciente/${adh.pacienteId}" title="Editar Adherente">
			        	<span class="icon icon-edit" title="Editar Adherente"></span></a>		        
			    		<a class="btn btn-danger btn-xs" href="/nuova/formDeletePaciente/${adh.pacienteId}" title="Eliminar Adherente">
			    		<span class="icon icon-remove" title="Eliminar Adherente"></span></a>
			    	</td>
			        <%index2++;%>
		    	</tr>
				</c:forEach>
		    </TABLE> 
						</div>
					</div>
				</div>
</div>
</c:if>
								
								</div>
								<!-- ** Tab Consultas -->
								<div id="tb_consultas" class="tab-pane fade">							
								asdsa 3
								</div>
								<!-- ** Tab Practicas -->
								<div id="tb_practicas" class="tab-pane fade">							
								asdsa 2
								</div>								
								<!-- ** Tab Reintegros -->
								<div id="tb_reintegros" class="tab-pane fade">							
								asdsa 4
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
									<div style="float:right;"><input type="button" value="Cancelar" onclick="location.href='/nuova/mainCaja/null';" class="btn"/></div>
										<div style="float:right;padding-right:2%;"><input type="submit" value="Guardar" class="btn btn-info"/></div> 
							 			
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