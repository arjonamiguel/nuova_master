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
		<script src="<c:url value="/resources/js/jquery/jquery.validate.min.js" />"></script>
		<style>
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
<jsp:include page="../breadcrumb.jsp"></jsp:include>
<div class="mainContainer"> 
<div class="panelContainer">
<form:form method="post" action="/nuova/redirectOrden" commandName="ordenTipo">
<form:hidden path="pacienteId"/>
<div class="panel panel-info">
		<div class="panel-heading">
			 <div class="panel-title">			 
			 <b>DNI: ${paciente.dni}<br>Paciente: ${paciente.apellido}, ${paciente.nombre} <br>
			 Parentesco: ${paciente.parentescoDescription}</b>			 
			 </div>
		</div>
		<div class="panel-body" >
				<div class="container-fluid">
						<div class="row-fluid">
							<div class="span6">
								<div class="formLabel"><form:label path="ordenTipoId">Orden a Emitir:</form:label></div>
        						<div class="formInput">
        						<form:select path="codigo">
								   <form:option value="NONE" label="Seleccione Tipo de Orden ..."/>
								   <form:options items="${ordenestipo}" itemLabel="nombre" itemValue="codigo" />			    
								</form:select>
        						</div>
							</div>
						</div>
				</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div style="float:right;padding-bottom:1%;padding-right:1%;">
	         			<input class="btn btn-info" type="submit" value="Siguiente >>"/> 
	         			<input class="btn" type="button" value="Cancelar" onclick="location.href='/nuova/mainPaciente';"/>
	        	</div>
			</div>
		</div>
</div>
</form:form> 
</div>
</div>
</body>
</html>