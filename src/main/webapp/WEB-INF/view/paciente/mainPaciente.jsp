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
	<style>
	.custab{
    border: 1px solid #ccc;
    padding: 5px;
    margin: 5% 0;
    box-shadow: 3px 3px 2px #ccc;
    transition: 0.5s;
    }
.custab:hover{
    box-shadow: 3px 3px 0px transparent;
    transition: 0.5s;
    }
.table{
	width: 30%;
	}
.row{
	margin-left: 10%;
}
	</style>
</head>
<body>
	<jsp:include page="../sec_menu.jsp"></jsp:include>
	   
	<h3>Administracion de Pacientes</h3>
	<div style="width: 37%;">
	<a href="formAddPaciente" class="btn btn-primary btn-xs pull-right">Nuevo Paciente</a>
	</div> 
	<c:if  test="${!empty pacienteList}">
	<div class="row col-md-6 col-md-offset-2 custyle">
	<table class="table table-striped custab">
	<tr>
	    <th>Apellido</th>	    
	    <th>Nombre</th>
	    <th>DNI</th>
	    <th>Telefono</th>
	    <th>Fecha Nacimiento</th>
	    <th>Domicilio</th>
	    <th>&nbsp;</th>
	</tr>
	<c:forEach items="${pacienteList}" var="pa">
	    <tr>
	        <td>${pa.apellido}</td>        
	        <td>${pa.nombre}</td>
	        <td>${pa.dni}</td>
	        <td>${pa.telefono}</td>
	        <td>${pa.fechaNacimiento}</td>
	        <td>${pa.domicilio}</td>
	        <td>
	        <div style="float:right;">
	        	<a class="btn btn-info btn-xs" href="formEditPaciente/${pa.pacienteId}"><span class="icon icon-edit"></span>editar</a>
	        	<a class="btn btn-danger btn-xs" href="formDeletePaciente/${pa.pacienteId}"><span class="icon icon-remove"></span>eliminar</a>
	       	</td>
	       	</div>
	    </tr>
	</c:forEach>
	</table>
	</div>
	</c:if>
	 
</body>
</html>