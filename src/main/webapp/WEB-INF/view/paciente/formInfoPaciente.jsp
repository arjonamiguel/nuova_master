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


	<!-- 	Configuracion del paginador -->
	<link href="<%=request.getContextPath()%>/resources/simplepaginggrid/css/simplePagingGrid-0.4.css" rel="stylesheet">
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/simplepaginggrid/examples/pageNumbers/script/handlebars-1.0.rc.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/simplepaginggrid/script/simplePagingGrid-0.5.0.2.js"></script>

	
<script type="text/javascript">

$(document).ready(function() {
	
	var rowsConsultas = [];
	var rowsPracticas = [];

	rowsConsultas = callConsultas();
	rowsPracticas = callPracticas();

	$("#consultasGrid").simplePagingGrid(
			{
				columnNames : [ "Nro. Orden","Fecha" ,"Profesional", ""],
				columnKeys : [ "nroOrden", "fecha" ,"apellidoNombreProfesional","acciones" ],
				columnWidths : [ "10%", "10%", "30%"],
				sortable : [ true, true,],
				data : rowsConsultas,
				pageSize : 5,
				minimumVisibleRows: 5
			});


	$("#practicasGrid").simplePagingGrid(
			{
				columnNames : [ "Nro. Orden","Fecha" ,"Estado", ""],
				columnKeys : [ "nroOrden", "fecha" ,"estado","acciones" ],
				columnWidths : [ "10%", "10%", "30%"],
				sortable : [ true, true,],
				data : rowsPracticas,
				pageSize : 5,
				minimumVisibleRows: 5
			});
});


function nuevoAdherente() {
	var titularId = document.getElementById("pacienteId").value;
	window.location.href = "/nuova/formAddAdherente/"+titularId;
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
					<td colspan="2">
						<div class="panel-title">${paciente.apellido} ${paciente.nombre}</div>
					</td>
					
					<td>Estado</td>
					<td>
					<c:if test="${paciente.eliminado == 0}">
					<span style="background:green; padding: 2px 4px 2px 4px;color:white;">ACTIVO</span>
					</c:if>
					<c:if test="${paciente.eliminado == 1}">
					<span style="background:red; padding: 2px 4px 2px 4px;;">INACTIVO</span>
					</c:if>
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
				
				<div class="panel-body">
					<div class="row-fluid">
						<div class="span12">
						<div style="text-align: right;">
		    <INPUT type="button" value="Nuevo Adherente" onclick="nuevoAdherente()" class="btn btn-success"/>
			</div>
		     <TABLE id="dataTableAdherente" class="table" style="width: 100%; margin: 1% 0">
		        <TR>
		        	
		            <TD><b>DNI</b></TD>
		            <TD><b>Apellido</b></TD>        
		            <TD><b>Nombre</b></TD>
		            <TD><b>Credencial</b></TD>
		            <TD><b>Parentesco</b></TD>
		            <TD></TD>
		        </TR>
		         <% int index2 = 0;%>
		        <c:forEach items="${paciente.adherentes}" var="adh" varStatus="loop" >
		    	<tr>			         
			        <td>${adh.dni} <input type="hidden" name = "adherentesEditList[<%=index2%>].pacienteId" value = "${adh.pacienteId}" /></td>        
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
								<div class="panel panel-info">		
								<div class="panel-body">
									<div class="row-fluid">
										<div class="span12">
											<div style="text-align: right;">
										    	<INPUT type="button" 
										    		value="Nueva Consulta" 
										    		onclick="javascript:createOrden(1)" 
										    		class="btn btn-success"/>
											</div>
										<div id="consultasGrid" style= "margin: 1% 0"></div>
										</div>
									</div>
								</div>		
								</div>
								</div>
								<!-- ** Tab Practicas -->
								<div id="tb_practicas" class="tab-pane fade">							
								<div class="panel panel-info">		
								<div class="panel-body">
									<div class="row-fluid">
										<div class="span12">
											<div style="text-align: right;">
										    	<INPUT type="button" 
										    		value="Nueva Pr�ctica" 
										    		onclick="javascript:createOrden(3)" 
										    		class="btn btn-success"/>
											</div>
										<div id="practicasGrid" style= "margin: 1% 0"></div>
										</div>
									</div>
								</div>		
								</div>
								</div>								
								<!-- ** Tab Reintegros -->
								<div id="tb_reintegros" class="tab-pane fade">							
								
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
									<div style="float:right;"><input type="button" value="Cancelar" onclick="location.href='/nuova/formBuscarPaciente';" class="btn"/></div>
							 			
									</div>
								</div>
							</div>
				</div>		
</div>
</div>


<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog" style="width: 50%;">
  <div class="modal-dialog" style="height:100%">

    <!-- Modal content-->
    <div class="modal-content" style="height:80%">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Comprobante</h4>
      </div>
      <div class="modal-body" style="height:110%">
        <div id="iframeReport" style="height:110%">	</div>
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

</script>