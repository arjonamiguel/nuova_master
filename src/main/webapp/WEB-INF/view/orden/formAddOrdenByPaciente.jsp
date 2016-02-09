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
	
	<style>
	.chkbox {
   padding-left: 10px;
   font-weight: bold;
 }
 label.error {
		  color: #a94442;
		  background-color: #f2dede;
		  border-color: #ebccd1;
		  padding:1px 20px 1px 20px;
		  width:58%;
		}
	
	</style>
	
</head>

<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<form:form method="post" action="/nuova/addOrden" commandName="ordenDto">
<form:hidden path="ordenTipo.ordenTipoId"/>
<div class="panelContainer">		
		<div class="panel panel-info">
			<div class="panel-heading">
          			<div class="panel-title">
	          			Nueva Pr�ctica
          			</div>
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
													
													<td colspan="4" style="width:60%">
														<b>Present� la orden original del m�dico solicitante?</b>
													</td>
													<td  style="text-align:left" colspan="2">			
													    <input type="checkbox" id="reqOrdenMedico" name = "reqOrdenMedico"  />
													</td>
												</tr>
												<tr>
													
													<td colspan="4">
														<b>Present� fotocopia de la credencial de la prestadora OSPSIP?</b>
													</td>
													<td  style="text-align:left" colspan="2">			
													    <input type="checkbox" id="reqCredecial" name="reqCredecial"  />
													</td>
												</tr>
									
											</table>
											
											<table class="table" style="width: 100%">
												<tr>			
													<td colspan="4" style="width:60%">
														<b>Present� fotocopia de los 3 �ltimo recibos como Monotributista o Ama de Casa?</b>
													</td>
													<td  style="text-align:left" colspan="2">			
													    <input type="checkbox" id="reqMonotributista" name="reqMonotributista" />
													</td>
												</tr>	
												<tr>
													
													<td colspan="4">
														<b>Present� fotocopia del �ltimo recibo de sueldo?</b>
													</td>
													<td  style="text-align:left" colspan="2">			
													    <input type="checkbox" id="reqReciboSueldo" name="reqReciboSueldo" />
													</td>
												</tr>
									
											</table>	
									  		</div>
									  		 		
									  		<div id="tb_observacion" class="tab-pane fade">
									    		<table class="table"  style="width: 100%">			
												<tr>		
													<td style="width: 15%"><form:label path="observacion">Observaci�n</form:label></td>
													<td  style="text-align:left" colspan="5">			
													    <form:textarea path="observacion" cssStyle="width:60%"/>
													</td>
												</tr>		
												</table>
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
		document.getElementById("mainPaciente").parentNode.classList.add("active");
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
		            form.submit();
		        }
		    });
		
	
</script>