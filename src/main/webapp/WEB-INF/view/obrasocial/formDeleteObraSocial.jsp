<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Nuova</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap-responsive.css" rel="stylesheet"/>
	<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery/bootstrap-collapse.js" />"></script>
	<link href="${pageContext.request.contextPath}/resources/css/nuova.css" rel="stylesheet"/>
	
</head>
<body style="background-color:#eee;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<div class="mainContainer"> 
		<div style="padding-bottom:0%;">
			<h3>Eliminar Obra Social</h3>
		</div>
		<form:form method="post" action="/nuova/deleteObraSocial" commandName="obrasocial">
		 	<div>
		    <table class="table" style="background-color:white;width:30%;">
		    <tr>
		        <td><form:label path="nombre">Obra Social:</form:label></td>
		        <td><form:input path="nombre" disabled="true" /></td>
		    </tr>
		   
		    <tr>
		        <td>
		        </td>
		        <td>
		            <div style="float:right;">
	         			<input class="btn btn-info" type="submit" value="Eliminar"/> 
	         			<input class="btn" type="button" value="Cancelar" onclick="location.href='/nuova/mainObraSocial';"/>
	        		</div>
		        </td>
		    </tr>
			</table>
			</div> 
		</form:form>
 
</div>
</body>
</html>