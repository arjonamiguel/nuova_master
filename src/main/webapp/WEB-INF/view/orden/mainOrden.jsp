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
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap-responsive.css" rel="stylesheet"/>
		<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
		<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
		<script src="<%=request.getContextPath()%>/resources/js/jquery/bootstrap-collapse.js" />"></script>
		<link href="<%=request.getContextPath()%>/resources/css/nuova.css" rel="stylesheet"/>

</head>
<body style="background-color:#eee;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<jsp:include page="../breadcrumb.jsp"></jsp:include>
<div class="mainContainer">   
	
	<div>
	<h3>Administracion de Practicas</h3>
	</div>
	<div>
		<div style="float:right;">
		<a href="formAddOrden" class="btn btn-info btn-xs pull-right"><b>+</b>&nbsp;&nbsp;Nueva Practica</a>
		</div>	
		<c:if  test="${!empty ordenList}">
		<table class="table" style="">
		<tr>
		    <th class="tableHeader"">Paciente</th>
		    <th class="tableHeader"">Nro Orden</th>
		    <th class="tableHeader"">Fecha</th>
		    <th class="tableHeader"">Orden Medica</th>
		    <th class="tableHeader"">Credencial</th>
		    <th class="tableHeader"">Monotributista</th>
		    <th class="tableHeader"">Recibo Sueldo</th>
		    <th class="tableHeader"">Estado</th>
		    <th class="tableHeader""></th>
		    <th class="tableHeader"" class="text-center"></th>
		</tr>
		<c:forEach items="${ordenList}" var="o">
		    <tr>
		        <td> 
			        <a class="btn btn-success btn-xs" href="formViewPaciente/${o.paciente.pacienteId}">
			        <span class="icon icon-user"></span>Ver...</a>
		        </td>
		        <td>${o.ordenId}</td>
		        <td>${o.fecha}</td>
		        <td>${o.reqOrdenMedico}</td>
		   	    <td>${o.reqCredecial}</td>
		        <td>${o.reqMonotributista}</td>
		        <td>${o.reqReciboSueldo}</td>
		        <td>${o.estado}</td>
		        <td class="text-center">
		        	<div style="float:right;">
		        	<a class="btn btn-info btn-xs" href="formEditOrden/${o.ordenId}"><span class="icon icon-edit"></span>editar</a>
		        	<a class="btn btn-danger btn-xs" href="formDeleteOrden/${o.ordenId}"><span class="icon icon-remove"></span>eliminar</a>
		        	</div>
		       	</td>
		    </tr>
		</c:forEach>
		</table>
	</div>
	</c:if>
</div>
</body>
</html>
<script>
document.getElementById("mainProfesional").parentNode.classList.add("active")
</script>