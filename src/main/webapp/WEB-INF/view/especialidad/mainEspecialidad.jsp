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
	.table{
    border: 1px solid #ccc;
    padding: 5px;
    margin: 5% 0;
    box-shadow: 3px 3px 2px #ccc;
    transition: 0.5s;
    }
.table:hover{
    box-shadow: 3px 3px 0px transparent;
    transition: 0.5s;
    }
.mainContainer {
  position: relative; /* or absolute */
  top: 0%;
  left: 2%;
  right: 2%;
  bottom:2%;
  float:left;
  width:95%;
  margin-top:0%;
  margin-bottom:5%;
  background-color:#f5f5f5;
  border-radius: 10px;
}
	</style>
</head>
<body style="background-color:#eee;">
	<jsp:include page="../sec_menu.jsp"></jsp:include>
<div class="mainContainer">     
	<div style="padding-left:5%;padding-bottom:2%;">   
		<h3>Administracion de Especialidades</h3>
	</div>
	
	<div style="width:32%;padding-left:5%;">
		<div style="float:left;padding-left:65%;">
			<a href="formAddEspecialidad" class="btn btn-info btn-xs pull-right"><b>+</b>&nbsp;&nbsp;Nueva Especialidad</a>
		</div> 
	<c:if  test="${!empty especialidadList}">
	
	<table class="table" style="background-color:white;">
	<tr>
	    <th style="background-color:#f9f9f9;">Nombre</th>	    
	    <th style="background-color:#f9f9f9;"s>&nbsp;</th>
	</tr>
	<c:forEach items="${especialidadList}" var="e">
	    <tr>
	        <td>${e.nombre}</td>        
	        <td>
	        <div style="float:right;">
	        	<a class="btn btn-info btn-xs" href="formEditEspecialidad/${e.especialidadId}"><span class="icon icon-edit"></span>editar</a>
	        	<a class="btn btn-danger btn-xs" href="formDeleteEspecialidad/${e.especialidadId}"><span class="icon icon-remove"></span>eliminar</a>
	       	</td>
	       	</div>
	    </tr>
	</c:forEach>
	</table>
	</div>
	</c:if>

</div>	 
</body>
</html>
<script>
document.getElementById("mainEspecialidad").parentNode.classList.add("active")
</script>