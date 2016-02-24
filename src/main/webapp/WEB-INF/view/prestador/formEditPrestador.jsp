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
	label.error {
		  color: #a94442;
		  background-color: #f2dede;
		  border-color: #ebccd1;
		  padding:1px 20px 1px 20px;
		  width:24%;
		}
	</style>
	
</head>
<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<jsp:include page="../breadcrumb.jsp"></jsp:include>
<div class="mainContainer"> 
<div class="panelContainer">
	<form:form method="post" action="/nuova/editPrestador" commandName="prestador">
	<form:hidden path="prestadorId" />
	<div class="panel panel-info">
			<div class="panel-heading">
				<div class="panel-title">Editar Prestador</div>
			</div>
			<div class="panel-body" >
				<div class="container-fluid">
						<div class="row-fluid">
							<div class="span6">
								<div class="formLabel"><form:label path="nombre">Nombre:</form:label></div>
        						<div class="formInput"><form:input path="nombre" class="input-block-level" type="text"/></div>
							</div>
							<div class="span6">
								<div class="formLabel"><form:label path="provincia">Provincia:</form:label></div>
								<div class="formInput">
								<form:select path="provincia">
								   <form:option value="NONE" label="Seleccione Provincia ..."/>
								   <form:options items="${provinciasList}" />			    
								</form:select>
								</div>
							</div>
							
						</div>
						
						<div class="row-fluid">		
							<div class="span6">
								<div class="formLabel"><form:label path="domicilio">Domicilio:</form:label></div>
        						<div class="formInput"><form:textarea path="domicilio" class="input-block-level" type="text"/></div>
							</div>				
							<div class="span6">
								<div class="formLabel"><form:label path="telefono">Telefono:</form:label></div>
        						<div class="formInput"><form:input path="telefono" class="input-block-level" type="text" cssStyle="width:53%"/></div>
							</div>
							
							
						</div>
						<br>
						<div class="row-fluid">
							<div class="span12">
									        <div style="float:right;">
	         									<input class="btn btn-info" type="submit" value="Guardar"/> 
	         									<input class="btn" type="button" value="Cancelar" onclick="location.href='/nuova/mainPrestador';"/>
	        								</div>
							</div>
						</div>
				</div>
			</div>
	</div>
	</form:form>
</div>
</div>
</body>
</html>
<script>
document.getElementById("configuracion").parentNode.classList.add("active");
			$("#prestador").validate({
    
		        // Specify the validation rules
		        rules: {
		            nombre: "required",
		            provincia: "required",
		            domicilio: "required",
		            telefono: {
		                required: true,
		                minlength: 5
		            }

		        },
		        
		        // Specify the validation error messages
		        messages: {
		            nombre: "Ingrese nombre",
		            telefono: {
		                required: "Ingrese telefono",
		                minlength: "Telefono debe tener al menos 5 caracteres de largo"
		            },
		            provincia: "Ingrese Provincia",
		            domicilio: "Ingrese domicilio"

		        },
		                submitHandler: function(form) {
		            form.submit();
		        }
		    });
</script>