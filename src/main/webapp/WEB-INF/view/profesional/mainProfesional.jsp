<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Nuova</title>
</head>
<body>
	<jsp:include page="../sec_menu.jsp"></jsp:include>
	   
	<h3>Administracion de Profesionales</h3>
	<a href="formAddProfesional" >Nuevo Profesional</a>
	</br> 
	<c:if  test="${!empty profesionalList}">
	<table class="data" border="1">
	<tr>
	    <th>Apellido y Nombre</th>
	    <th>Matricula</th>
	    <th>Telefono</th>
	    <th>Registro Nacional</th>
	    <th>Habilitacion Siprosa</th>
	    <th>Fecha Vencimiento</th>
	    <th>&nbsp;</th>
	</tr>
	<c:forEach items="${profesionalList}" var="p">
	    <tr>
	        <td>${p.apellido}, ${p.nombre} </td>
	        <td>${p.matricula}</td>
	        <td>${p.telefono}</td>
	        <td>${p.registroNacional}</td>
	        <td>${p.habilitacionSiprosa}</td>
	        <td>${p.fechaVencimientoHabilitacion}</td>
	        <td>
	        	<a href="formEditProfesional/${p.profesionalId}">editar</a>|
	        	<a href="formDeleteProfesional/${p.profesionalId}">eliminar</a>
	       	</td>
	    </tr>
	</c:forEach>
	</table>
	</c:if>
	 
</body>
</html>