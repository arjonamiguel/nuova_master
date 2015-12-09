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
	   
	<h3>Administracion de Especialidades</h3>
	<a href="formAddEspecialidad" >Nueva Especialidad</a>
	</br> 
	<c:if  test="${!empty especialidadList}">
	<table class="data" border="1">
	<tr>
	    <th>Nombre</th>	    
	    <th>&nbsp;</th>
	</tr>
	<c:forEach items="${especialidadList}" var="e">
	    <tr>
	        <td>${e.nombre}</td>        
	        <td>
	        	<a href="formEditEspecialidad/${e.especialidadId}">editar</a>|
	        	<a href="delete/${e.especialidadId}">eliminar</a>
	       	</td>
	    </tr>
	</c:forEach>
	</table>
	</c:if>
	 
</body>
</html>