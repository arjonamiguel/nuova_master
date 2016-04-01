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
		<script>
			function updateDateFefchaStart() {
				document.getElementById("start").value=document.getElementById("fecha-start").value;
			}
			function updateDateFechaEnd() {
				document.getElementById("end").value=document.getElementById("fecha-end").value;
			}
		</script>

</head>
<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<div class="mainContainer"> 
<div class="panelContainer">
<form:form method="post" action="/nuova/deleteCalendario" commandName="calendario">
<div class="panel panel-info">
		<div class="panel-heading">
			 <div class="panel-title">Eliminar Calendario</div>
		</div>
		<div class="panel-body" >
				<div class="container-fluid">
						<div class="row-fluid">
							<div class="span3">
								<div class="formLabel"><form:label path="resourceId">Consultorio:</form:label></div>
        						<div class="formInput">
        						<form:input path="resourceId" type="text"/>
        						</div>
							</div>
							<div class="span3">
								<div class="formLabel"><form:label path="start">Inicio:</form:label></div>
        						<div style="visibility:hidden;height:0px;"><form:input path="start" class="date"/></div>
        						<div class="formInput">
									<form:input path="start" type="text"/>
								</div>
							</div>
							<div class="span3">
								<div class="formLabel"><form:label path="end">Final:</form:label></div>
        						<div style="visibility:hidden;height:0px;"><form:input path="end" type="text"/></div>
        						<div class="formInput">
									<form:input path="end" type="text"/>
								</div>
        						
							</div>
							<div class="span3">
								<div class="formLabel"><form:label path="title">Profesional:</form:label></div>
        						
        						<div class="formInput">
        						<form:input path="title" type="text"/>
								</div>
							</div>
						</div>
				</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div style="float:right;padding-bottom:1%;padding-right:1%;">
	         			<input class="btn btn-danger" type="submit" value="Eliminar"/> 
	         			<input class="btn" type="button" value="Cancelar" onclick="location.href='mainCalendario';"/>
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
	$("#calendario").validate({
    
		        // Specify the validation rules
		        rules: {
		            nombre: "required",
		        },
		        
		        // Specify the validation error messages
		        messages: {
		            nombre: "Ingrese Cal",
		        },
		                submitHandler: function(form) {
		            form.submit();
		        }
		    });
</script>
