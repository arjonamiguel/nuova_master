<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>   
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
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
	<div style="padding-left:5%;padding-bottom:0%;"> 
		<h3>Nueva Especialidad</h3>
	</div>
	<form:form method="post" action="addEspecialidad" commandName="especialidad">
	 	<div style="padding-left:5%;">
	    <table class="table" style="background-color:white;width:30%;">
	    <tr>
	        <td><form:label path="nombre">Especialidad:</form:label></td>
	        <td><form:input path="nombre" class="input-block-level"/></td>
	    </tr>
	    <tr>
	        <td>   
	        </td>
	        <td>
	        <div style="float:right;">
	         	<input class="btn btn-info" type="submit" value="Guardar"/> 
	         	<input class="btn" type="button" value="Cancelar" onclick="location.href='mainEspecialidad';"/>
	        </div>
	        </td>
	    </tr>
		</table>
		</div> 
	</form:form>
</div>
</body>
</html>