<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Nuova</title>
		<link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/img/favicon/favicon.ico">
		
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>   
		<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
		<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
		<script src="<%=request.getContextPath()%>/resources/js/jquery/bootstrap-collapse.js" />"></script>
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
	          		Editar Practicas 
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
											  <li><a data-toggle="tab" href="#tb_autorizacion" onclick="setObservacionInvisible()">Autorizacion</a></li>
											  <li>
											  	<a data-toggle="tab" href="#tb_observacion" onclick="setObservacionVisible()">
											  	Observaciones 
											  	<c:if test="${observacionCount > 0}">
										     		<span class="badge">${observacionCount}</span>
										     	</c:if>
											  	</a>
											  </li>
											  <li><a data-toggle="tab" href="#tb_flujo" onclick="setObservacionInvisible()">Flujo de Estados</a></li>
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
															<b>Presentó fotocopia del último recibo Monotributista?</b>
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
										  		<div id="tb_autorizacion" class="tab-pane fade">
										  		<table class="table"  style="width: 100%">			
													<tr>
														<td style="width: 10%">Estado</td>
										        		<td>
										        			<form:select path="estado" style="width:30%; margin-bottom:0px">
															   <form:option value="NONE" label="Seleccione Estado ..."/>
															   <form:options items="${estadosList}" itemLabel="value" itemValue="id" />			    
															</form:select>
														</td>
										        	</tr>	
										  		</table>
										    	<table class="table"  style="width: 100%" height="100%">			
													<tr>
										        	<td colspan="2">
										           	<div>
														<div class="inputProf">
														<form:select path="practica" style="width:70%; margin-bottom:0px">
															   <form:option value="NONE" label="Seleccione Práctica ..."/>
															   <form:options items="${practicasList}" itemLabel="value" itemValue="id" />			    
															</form:select>
															<INPUT type="button" value="Agregar" onclick="addRow('tb_practicas')" class="btn btn-info"/>
																    	
															
														</div>	
													</div>
										    		<div>		    
													    <TABLE id="tb_practicas" class="table" style="width: 100%; margin: 2% 0;" >
													    <thead>
													        <TR>
													        	
													            <TD style="width: 10%">ID</TD>			                    
													            <TD style="width: 100%">Practica</TD>
													            <td></td>
													        </TR>
													        <% int index = 0;%>
													        <c:forEach items="${ordenDto.practicasListEdit}" var="pa" varStatus="loop" >
													    	<tr>
														        <td>${pa.practicaId}
														        <input type="hidden" name = "ordenpracticaListEdit[<%=index%>].orddenPracticaId" value = "${pa.orddenPracticaId}" />
														        <input type="hidden" name = "ordenpracticaListEdit[<%=index%>].practicaId" value = "${pa.practicaId}" /> 
														        </td>
														        <td>${pa.nombre}</td>				        
														        <td><button type='button' class='btn btn-link' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'>eliminar</button></td>
														        <%index++;%>
													    	</tr>
															</c:forEach>
													    </thead>
													   
													    </TABLE>
											   	
										 			</div>    
										 			
										        	</td>
										    	</tr>
													
												</table>
										  		</div>
										  		
										  		<div id="tb_observacion" class="tab-pane fade" style="">  					
													<c:forEach items="${ordenDto.observacioneses}" var="obs" varStatus="loop" >
													<table class="table"  style="width: 100%;">	
													<tr style="background-color:#f5f5f5;">
														<td></td><td></td><td></td><td></td>
													</tr>
													<tr>
														<td style="background-color:#f5f5f5;border-top:none;"></td>
														<td style="width: 80%; padding: 1px 1px;border-left: 2px solid orange;">
											    			<b>${obs.userName}</b> <span style="font-size: 12px">${obs.fecha}</span>
											    		</td>
											    		<td style="padding: 1px 1px; text-align: right;">
												    		<a class="btn btn-link" href="#">
																<i class="icon-trash"></i>
															</a>
															<a class="btn btn-link" href="#">
																<i class="icon-pencil"></i>
															</a>
														</td>
														<td style="background-color:#f5f5f5;border-top:none;"></td>
														
											    	</tr>
											    	<tr>	
											    		<td style="background-color:#f5f5f5;border-top:none;"></td>    		
												        <td align="justify" colspan="2" style="border-left: 2px solid orange;border-bottom: 1px solid orange;">${obs.observacion}</td>		  
												        <td style="background-color:#f5f5f5;border-top:none;"></td>    
											    	</tr>
											    	<tr style="background-color:#f5f5f5;">
														<td style="border-top:none;"></td><td></td><td></td><td style="border-top:none;"></td>
													</tr>
											
											    	</table>
											    	</c:forEach>	

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
												    		<c:if test="${loop.index < 1}">
													        	<span style="color: orange; font-weight: bold">${ow.estado}</span>
													        </c:if>
													        <c:if test="${loop.index  > 0}">
													        	${ow.estado}
													        </c:if>
												        </td>
											    	</tr>
											    	</table>
											    	</c:forEach>   		
										  		</div>
										  		
											</div>
												<div id="addObservacion" style="visibility:hidden;">
													
										    		<table class="table"  style="width: 100%">    					
													<tr>		
														<td style="width: 15%"><form:label path="observacion">Observacion</form:label></td>
														<td  style="text-align:left" colspan="5">			
														    <form:textarea path="observacion" cssStyle="width:90%"/>
														</td>
													</tr>		
													</table>
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