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
				  width:73%;
				}
		</style>

</head>
<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<div class="mainContainer"> 
<div class="panelContainer">
<form:form method="post" action="addObraSocial" commandName="obrasocial">
<div class="panel panel-info">
		<div class="panel-heading">
			 <div class="panel-title">Nueva Obra Social</div>
		</div>
		<div class="panel-body" >
				<div class="container-fluid">
						<div class="row-fluid">
							<div class="span3">
								<div class="formLabel"><form:label path="nombre">Obra Social:</form:label></div>
        						<div class="formInput"><form:input path="nombre" /></div>
							</div>
							<div class="span3">
								<div class="formLabel"><form:label path="cuit">CUIT:</form:label></div>
        						<div class="formInput"><form:input path="cuit" /></div>
							</div>
							<div class="span3">
								<div class="formLabel"><form:label path="direccion">Direccion:</form:label></div>
        						<div class="formInput"><form:input path="direccion" /></div>
							</div>
							<div class="span3">
								<div class="formLabel"><form:label path="telefono">Telefono:</form:label></div>
        						<div class="formInput"><form:input path="telefono" /></div>
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
						<input class="btn" type="button" value="Cancelar" onclick="location.href='/nuova/mainObraSocial';"/>	
					</div>
					<div style="float:right;padding-right:2%;">
						<input type="submit" value="Guardar" class="btn btn-info"/>
					</div>								 			
				</div>
				</div>
			</div>
		</div>
		<!-- Fin Botoneras -->
</form:form> 
</div>
</div>
</body>
</html>
<script>
	$("#obrasocial").validate({
    
		        // Specify the validation rules
		        rules: {
		            nombre: "required",
		        },
		        
		        // Specify the validation error messages
		        messages: {
		            nombre: "Ingrese Obra Social",
		        },
		                submitHandler: function(form) {
		            form.submit();
		        }
		    });
</script>
