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
	
</head>

<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<form:form method="post" action="/nuova/addAdherente" commandName="paciente">

<div class="mainContainer"> 
	<div class="panelContainer">		
		<div class="panel panel-info">
			<div class="panel-heading">
          			<div class="panel-title">
	          			Nueva Practica
          			</div>
    		</div>     
			<div  class="panel-body" >
			  				<div class="row-fluid" >
			  						<div class="span9">
			  						</div>
			  						<div class="span2">
				  						<form:select path="pacienteSelected" style="width:100%; margin-bottom:0px">
																   <form:option value="NONE" label="Seleccione Paciente ..."/>
																   <form:options items="${pacienteList}" itemLabel="value" itemValue="id" />			    
										</form:select>
			  						</div>
			  						<div class="span1">
			  						<INPUT type="button" value="Agregar" onclick="addRow('dataTable')" class="btn btn-success"/>
			  						</div>
				    				
				    		</div>
			  				<div class="row-fluid" >	
					    		<div class="span12">
					    				
					    			<TABLE id="dataTable" class="table">
										<TR>
											<TD></TD>
											<TD>Id</TD>
											<TD>Obra Social</TD>        
											<TD>Nro Credencial</TD>
											<TD>Original/Provisoria</TD>
										</TR>
									</TABLE>				
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