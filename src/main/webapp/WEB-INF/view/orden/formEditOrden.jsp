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
<link href="<%=request.getContextPath()%>/resources/montrezorro-bootstrap-checkbox-fa865ff/css/bootstrap-checkbox.css" rel="stylesheet"/>
<script src="<%=request.getContextPath()%>/resources/montrezorro-bootstrap-checkbox-fa865ff/js/bootstrap-checkbox.js" /></script>


<script type="text/javascript">
function createSelectEstados(id) {
	var selectEstado = '<select name="'+id+'" id="'+id+'" style="width:70%; margin-bottom:0px">' +
	   ' <option value="NONE">Seleccione Estado ...</option>' +
	   ' <option value="AUTORIZACION DIRECTA">AUTORIZACION DIRECTA</option>' + 
	   ' <option value="PENDIENTE AFILIACIONES">PENDIENTE AFILIACIONES</option>' +
	   ' <option value="AUTORIZADA POR AFILIACIONES">AUTORIZADA POR AFILIACIONES</option>' +
	   ' <option value="RECHAZADA POR AFILIACIONES">RECHAZADA POR AFILIACIONES</option>' +
	   ' <option value="PENDIENTE AUDITORIA">PENDIENTE AUDITORIA</option>' +
	   ' <option value="AUTORIZADA POR AUDITORIA">AUTORIZADA POR AUDITORIA</option>' +
	   ' <option value="RECHAZADA POR AUDITORIA">RECHAZADA POR AUDITORIA</option>' +
	   ' <option value="RECHAZADA">RECHAZADA</option><option value="ANULADO">ANULADO</option>' +			    
	   ' </select>';
	
	   return selectEstado;
}

 $(document).ready(function() {
  	  var map = new Object();
  	  var objects = [];
  	  
      $('input.typeahead').typeahead({
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
        updater: function(item) {
            $('#nomencladorId').val(map[item].id);
            return item;
        }
      });
    });

function setObservacionVisible(){
	$("#addObservacion").css("visibility","visible");
}
function setObservacionInvisible(){
	$("#addObservacion").css("visibility","hidden");
}

function Eliminar (i) {    	 
    document.getElementById("tb_practicas").deleteRow(i);
}
 	
function addRow(tableID) {
	if(document.getElementById("ContainerGeneralOverWrite_ContainerGeneral_sq").value==""){
       	return;
   	}
  
	var index = document.getElementById(tableID).getElementsByTagName('tr').length;
	index ++;	
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    row.style.background= '#f5f5f5';
          
    var cell0 = row.insertCell(0);
    cell0.innerHTML = document.getElementById("ContainerGeneralOverWrite_ContainerGeneral_sq").value+" <input type='hidden' name='ordenpracticaListEdit[" + index + "].orddenPracticaId'> "
    + "<input type='hidden' name='ordenpracticaListEdit[" + index + "].practicaId' value='" + document.getElementById("nomencladorId").value + "'>"; 

    var cell1 = row.insertCell(1);
    cell1.innerHTML = "<input type='text' name='ordenpracticaListEdit[" + index + "].valor' value='0.00'>"; 
    
    var cell2 = row.insertCell(2);
    cell2.innerHTML = createSelectEstados("ordenpracticaListEdit[" + index + "].estado");
    
    var cell3 = row.insertCell(3);
    row.valign = "BASELINE";
    cell3.innerHTML = "<button type='button' class='btn btn-link' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'>Eliminar</button>";
    
    var cell4 = row.insertCell(4);
    cell4.innerHTML = "";
     
    index ++;
    document.getElementById("ContainerGeneralOverWrite_ContainerGeneral_sq").value = "";
    document.getElementById("ContainerGeneralOverWrite_ContainerGeneral_sq").focus();
   }


function addRowHistoriaClinica(tableID) {
	
	var index = document.getElementById(tableID).getElementsByTagName('tr').length;
	index ++;	
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    row.style.background= '#f5f5f5';
          
    var cell0 = row.insertCell(0);    
    cell0.innerHTML = " <input type='hidden' name='historiasclinicas[" + index + "].documentId'> "
    + "<input type='file' name='historiasclinicas[" + index + "].fileData'>"; 
    
    var cell1 = row.insertCell(1);
    cell1.innerHTML = "";
    
    var cell2 = row.insertCell(2);
    row.valign = "BASELINE";    
    cell2.innerHTML = "<a href='#' onclick='eliminarHC(this.parentNode.parentNode.rowIndex)'>Eliminar</a>";
     
    index ++;
    
   }
function eliminarHC (i) {    	 
    document.getElementById("tabla_historiaclinica").deleteRow(i);
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
</script>

<style>
.chkbox {padding-left: 10px;font-weight: bold;}
</style>
</head>

<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>

<form:form method="post" action="/nuova/editOrden" commandName="ordenDto"
enctype="multipart/form-data">
<form:hidden path="ordenId"/>
<div class="mainContainer">	

	<div class="panelContainer">		
		<div class="panel panel-info">	
			<!-- Cabecera y Titulo -->
			<div class="panel-heading">
   				<div class="panel-title">
		       		<b>Editar Prácticas</b> 
		       	</div>
		 	</div>
		 	<!-- Fin Cabecera y Titulo -->

			<div  class="panel-body" >
				<div class="container-fluid" >
					<div class="row-fluid" >
		   				<div class="span12">
		   					<div class="tableContainer">	
		   					<jsp:include page="../message.jsp"></jsp:include>
							<!-- Declaracion de tabs -->
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#tb_paciente" onclick="setObservacionInvisible()">Paciente</a></li>
								<li><a data-toggle="tab" href="#tb_requisitos" >Requisitos</a></li>
								<li><a data-toggle="tab" href="#tb_profesional">Profesional</a></li>
								<li><a data-toggle="tab" href="#tb_autorizacion" >Autorizaci&oacute;n</a></li>
								<li>
									<a data-toggle="tab" href="#tb_observacion" >
									Observaciones 
									<c:if test="${observacionCount > 0}">
										<span class="badge">${observacionCount}</span>
									</c:if>
									</a>
								</li>
								<li><a data-toggle="tab" href="#tb_historiaclinica" >Historia Cl&iacute;nica</a></li>								
								<li><a data-toggle="tab" href="#tb_flujo" >Flujo de Estados</a></li>								
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
				
								<!-- ** Tab Observaciones -->
								<div id="tb_observacion" class="tab-pane fade" style="">  					
									<jsp:include page="formEditOrdenTabObservaciones.jsp"></jsp:include>			
								</div>
								
								<!-- ** Tab Historia Clinica -->
								<div id="tb_historiaclinica" class="tab-pane fade">  					
									<jsp:include page="formEditOrdenTabHistoriaClinica.jsp"></jsp:include>	
		 						</div>
		 						
								<!-- ** Tab Flujos -->
								<div id="tb_flujo" class="tab-pane fade">  					
									<jsp:include page="formEditOrdenTabFlujo.jsp"></jsp:include>	
		 						</div>							
		 		
							</div>
							<!-- Fin Contenedor de Tabs -->					
		
							<div style="float:right;"></div>						
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
						<input type="button" value="Cancelar" onclick="location.href = '/nuova/mainOrdenPractica'; return false;" class="btn"/>	
					</div>
					<div style="float:right;padding-right:2%;">
						<input class="btn btn-lg btn-primary btn-block btn-info" type="submit" value="  Guardar  "/>
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