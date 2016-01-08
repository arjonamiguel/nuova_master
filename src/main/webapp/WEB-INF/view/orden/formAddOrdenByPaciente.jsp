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
	.chkbox {
   padding-left: 10px;
   font-weight: bold;
 }
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
<form:form method="post" action="/nuova/addOrden" commandName="ordenDto">
<div class="row col-md-6 col-md-offset-2 custyle">
<table class="table table-striped custab">
	<tr>
	<td colspan="6"><h4>Nueva Practica</h4></td>
	</tr>
		
	<tr>
		<td><form:label path="paciente.dni">DNI</form:label></td>
		<td><form:input path="paciente.dni"/></td>
		<td><form:label path="paciente.apellido">Apellido</form:label></td>
		<td>
			<form:hidden path="paciente.pacienteId"/>
			<form:input path="paciente.apellido"/>
		</td>
		<td><form:label path="paciente.nombre">Nombre</form:label></td>
		<td><form:input path="paciente.nombre"/></td>
	</tr>
	
	<tr>
		<td><form:label path="paciente.dni">Obra Social</form:label></td>
		<td><form:input path="paciente.dni"/></td>	
		<td><form:label path="paciente.nombre">Credencial</form:label></td>			
		<td><form:input path="paciente.nombre"/></td>
		<td><form:label path="paciente.nombre">Original</form:label></td>			
		<td><input type="checkbox" /> </td>
	</tr>
	<tr><td colspan="6"><h5>Requisitos</h5></td></tr>
	<tr>
		
		<td colspan="4">
			<b>Presentó la orden original del médico solicitante?</b>
		</td>
		<td  style="text-align:left" colspan="2">			
		    <input type="checkbox" id="reqOrdenMedico" name = "reqOrdenMedico"  />
		</td>
	</tr>
	<tr>
		
		<td colspan="4">
			<b>Presentó fotocopia de la credencial de la prestadora OSPSIP?</b>
		</td>
		<td  style="text-align:left" colspan="2">			
		    <input type="checkbox" id="reqCredecial" name="reqCredecial"  />
		</td>
	</tr>
	<tr>
		
		<td colspan="4">
			<b>Presentó fotocopia del último recibo Monotributista?</b>
		</td>
		<td  style="text-align:left" colspan="2">			
		    <input type="checkbox" id="reqMonotributista" name="reqMonotributista" />
		</td>
	</tr>	
	<tr>
		
		<td colspan="4">
			<b>Presentó fotocopia del último recibo de sueldo?</b>
		</td>
		<td  style="text-align:left" colspan="2">			
		    <input type="checkbox" id="reqReciboSueldo" name="reqReciboSueldo" />
		</td>
	</tr>
	<tr>		
		<td><form:label path="observacion">Observacion</form:label></td>
		<td  style="text-align:left" colspan="5">			
		    <form:textarea path="observacion" cssStyle="width:100%"/>
		</td>
	</tr>
	<tr>
	<td>
	<input class="btn btn-lg btn-primary btn-block btn-signin" type="submit" value="Guardar"/>
	</td>
	<td>
	<input type="button" value="Cancelar" onclick="location.href = document.referrer; return false;" class="btn"/>
	</td>
	<td colspan="4"></td>
	</tr>
</table>
</div> 
</form:form>
 
</body>
</html>