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
	  					if(document.getElementById("practica").value=="NONE"){
            	return;
        	}
	  
	      	var index = document.getElementById(tableID).getElementsByTagName('tr').length;
	      	index ++;	
	          var table = document.getElementById(tableID);
	          var rowCount = table.rows.length;
	          var row = table.insertRow(rowCount);
	          var cell2 = row.insertCell(0);                      
	          cell2.innerHTML = document.getElementById("practica").value+"<input type='hidden' name='ordenpracticaListEdit[" + index + "].orddenPracticaId'> "
	          + "<input type='hidden' name='ordenpracticaListEdit[" + index + "].practicaId' value='" + document.getElementById("practica").value + "'>";
	          var cell3 = row.insertCell(1);
	          cell3.innerHTML = document.getElementById('practica').options[document.getElementById('practica').selectedIndex].text; 
	          var cell1 = row.insertCell(2);
	          row.valign = "BASELINE";
	          cell1.innerHTML = "<button type='button' class='btn btn-link' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'>eliminar</button>"
	           
	          index ++;
	       }
	</script>
	
	<style>
	.chkbox {
   padding-left: 10px;
   font-weight: bold;
 }
	</style>
	
</head>

<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<form:form method="post" action="/nuova/editOrden" commandName="ordenDto">
<form:hidden path="ordenId"/>
<div class="mainContainer">
	
	<div class="panelContainer">		
		<div class="panel panel-info">
			<div class="panel-heading">
          			<div class="panel-title">
	          		Editar Consultas 
          			</div>
    		</div>     
			<div  class="panel-body" >
				<div class="container-fluid" >
	  				<div class="row-fluid" >
		    				<div class="span12">
		    					<div class="tableContainer">
		    					
		    								<ul class="nav nav-tabs">
											  <li class="active"><a data-toggle="tab" href="#tb_paciente" onclick="setObservacionInvisible()">Paciente</a></li>
											  <li><a data-toggle="tab" href="#tb_requisitos" onclick="setObservacionInvisible()">Requisitos</a></li>
											  <li><a data-toggle="tab" href="#tb_profesional">Profesional</a></li>											 
											  <li><a data-toggle="tab" href="#tb_flujo" >Usuario Creador</a></li>
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
														<td></td>			
														<td> </td>
													</tr>
													
												</table>  		   
										 		</div>
										  
										  		<div id="tb_requisitos" class="tab-pane fade">
												<table class="table" style="width: 100%">
													
													<tr>
														
														<td colspan="4" style="width:60%">
															<b>Presentó la orden original del médico solicitante?</b>
														</td>
														<td  style="text-align:left" colspan="2">			    
														    <form:checkbox path="reqOrdenMedico" />
														</td>
													</tr>
													<tr>
														
														<td colspan="4">
															<b>Presentó fotocopia de la credencial de la prestadora OSPSIP?</b>
														</td>
														<td  style="text-align:left" colspan="2">				    
														    <form:checkbox path="reqCredecial" />
														</td>
													</tr>
										
												</table>
												
												<table class="table" style="width: 100%">
													<tr>			
														<td colspan="4" style="width:60%">
															<b>Presentó fotocopia de los 3 último recibos como Monotributista o Ama de Casa?</b>
														</td>
														<td  style="text-align:left" colspan="2">				    
														    <form:checkbox path="reqMonotributista" />
														</td>
													</tr>	
													<tr>
														
														<td colspan="4">
															<b>Presentó fotocopia del último recibo de sueldo?</b>
														</td>
														<td  style="text-align:left" colspan="2">				    
														    <form:checkbox path="reqReciboSueldo" />
														</td>
													</tr>
										
												</table>  		   
												  		   
												
										  		</div>
										  		<div id="tb_profesional" class="tab-pane fade">
									    		<table class="table"  style="width: 100%">			
												<tr>		
													<td style="width: 15%"><form:label path="profesionalId">Profesional</form:label></td>
													<td  style="text-align:left" colspan="5">			
													    <form:select path="profesionalId" style="width:30%; margin-bottom:0px">
															   <form:option value="-1" label="Seleccione Profesional ..."/>
															   <form:options items="${profesionales}" itemLabel="value" itemValue="id" />			    
															</form:select>
													</td>
												</tr>		
												</table>
									  			</div>
										  		<div id="tb_flujo" class="tab-pane fade">  					
													<c:forEach items="${ordenDto.ordenWorkflows}" var="ow" varStatus="loop" >			
													<table class="table"  style="width: 100%">	
											    	<tr style="border-left: 2px solid orange;border-bottom: 1px solid orange;">
											    		<td style="width: 60%">
											    		<b>${ow.userName}</b><br>
											    		<span style="font-size: 12px">${ow.fecha}</span>
											    		</td>
											    		<td align="left">
												    		
												        </td>
											    	</tr>
											    	</table>
											    	</c:forEach>   		
										  		</div>
										  		
											</div>
												
											<div style="float:right;">
											<table>
										 		<tr>
													<td style="width: 50%">
													<input class="btn btn-lg btn-primary btn-block btn-info" type="submit" value="Guardar"/>
													</td>
													<td>
													<input type="button" value="Cancelar" onclick="location.href = document.referrer; return false;" class="btn"/>
													</td>
													<td colspan="4"></td>
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
</form:form>

</body>
</html>
<script>
document.getElementById("mainOrden").parentNode.classList.add("active");
</script>