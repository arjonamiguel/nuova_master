<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Nuova</title>
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
		<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
		<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
		<script src="<%=request.getContextPath()%>/resources/js/jquery/bootstrap-collapse.js" />"></script>
		<link href="<%=request.getContextPath()%>/resources/css/nuova.css" rel="stylesheet"/>
		<link href="<%=request.getContextPath()%>/resources/css/panel.css" rel="stylesheet"/>
		<link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap-responsive.css" rel="stylesheet"/>

</head>
<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<jsp:include page="../breadcrumb.jsp"></jsp:include>
<div class="mainContainer">   

		
		
		
<div class="panelContainer">		
		<div class="panel panel-info">
			<div class="panel-heading">
          			<div class="panel-title">
	          			Administracion de Profesionales
	           			<a href="formAddProfesional" class="pull-right"><b>+</b>&nbsp;&nbsp;Nuevo Profesional</a>
          			</div>
    		</div>     
			<div  class="panel-body" >
				<div class="container-fluid" >
	  				<div class="row-fluid" >
		    				<div class="span12">
		    					<div class="tableContainer">	
									<c:if  test="${!empty profesionalList}">
									<table class="table">
									<tr>
									    <th class="tableHeader"">Apellido y Nombre</th>
									    <th class="tableHeader"">Matricula</th>
									    <th class="tableHeader"">Telefono</th>
									    <th class="tableHeader"">Registro Nacional</th>
									    <th class="tableHeader"">Habilitacion Siprosa</th>
									    <th class="tableHeader"">Fecha Vencimiento</th>
									    <th class="tableHeader"" class="text-center"></th>
									</tr>
									<c:forEach items="${profesionalList}" var="p">
									    <tr>
									        <td>${p.apellido}, ${p.nombre} </td>
									        <td>${p.matricula}</td>
									        <td>${p.telefono}</td>
									        <td>${p.registroNacional}</td>
									   	     <td>${p.habilitacionSiprosa}</td>
									        <td>${p.fechaVencimientoHabilitacion}</td>
									        <td class="text-center">
									        	<div style="float:right;">
									        	<a class="btn btn-info btn-xs" href="formEditProfesional/${p.profesionalId}"><span class="icon icon-edit"></span>editar</a>
									        	<a class="btn btn-danger btn-xs" href="formDeleteProfesional/${p.profesionalId}"><span class="icon icon-remove"></span>eliminar</a>
									        	</div>
									       	</td>
									    </tr>
									</c:forEach>
									</table>
									</c:if>
								</div>
		    				</div>
		    		</div>
		    	</div>
	    	</div>
	    </div>
</div>
	
</div>
</body>
</html>
<script>
document.getElementById("configuracion").parentNode.classList.add("active")
</script>