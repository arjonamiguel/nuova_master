<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Nuova</title>
</head>
<body>
<jsp:include page="../sec_menu.jsp"></jsp:include>
<h3>Eliminar Profesional</h3>
<form:form method="post" action="/nuova/deleteProfesional" commandName="profesional">
 
    <table>
    <tr>
    	<td colspan="2"><form:hidden path="profesionalId" /></td>
    </tr>
    <tr>
        <td><form:label path="apellido">Apellido:</form:label></td>
        <td><form:input path="apellido" disabled="true" /></td>
    </tr>
    <tr>
        <td><form:label path="nombre">Nombre:</form:label></td>
        <td><form:input path="nombre" disabled="true" /></td>
    </tr>
    
    <tr>
        <td><form:label path="telefono">Telefono:</form:label></td>
        <td><form:input path="telefono" disabled="true" /></td>
    </tr>
    
    <tr>
        <td><form:label path="matricula">Matricula:</form:label></td>
        <td><form:input path="matricula" disabled="true" /></td>
    </tr>
    
    <tr>
        <td><form:label path="registroNacional">Registro Nacional:</form:label></td>
        <td><form:input path="registroNacional" disabled="true" /></td>
    </tr>
    
    <tr>
        <td><form:label path="tituloProfesional">Titulo Profesional:</form:label></td>
        <td><form:input path="tituloProfesional" disabled="true" /></td>
    </tr>
    
    <tr>
        <td><form:label path="habilitacionSiprosa">Habilitacion del Siprosa:</form:label></td>
        <td><form:input path="habilitacionSiprosa" disabled="true"/></td>
    </tr>
    
    <tr>
        <td><form:label path="fechaVencimientoHabilitacion">Fecha Vencimiento Habilitacion:</form:label></td>
        <td><form:input path="fechaVencimientoHabilitacion" disabled="true" /></td>
    </tr>
    
    <tr>
        <td><form:label path="fechaVencimientoHabilitacion">Especialidad:</form:label></td>
        
    </tr>
    
        <tr>
    <td colspan="2">
    <TABLE id="dataTable" width="350px" border="1">
        <TR>

            <TD>Id</TD>
            <TD>Especialidad</TD>
            
        </TR>
        
        <c:forEach items="${especialidadListEdit}" var="esp">
	    <tr>

	        <td>${esp.key} </td>
	        <td>${esp.value}</td>        
	    </tr>
	</c:forEach>
    
    </TABLE>
    </td>
    </tr>
    
   
    <tr>
        <td>
            <input type="submit" value="Eliminar"/>
        </td>
        <td>
            <input type="button" value="Cancelar" onclick="location.href='/nuova/mainProfesional';"/>
        </td>
    </tr>
    
</table> 
</form:form>
 

</body>
</html>