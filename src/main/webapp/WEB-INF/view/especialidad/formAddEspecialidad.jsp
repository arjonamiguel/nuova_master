<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Nuova</title>
</head>
<body>
<jsp:include page="../sec_menu.jsp"></jsp:include>
<h3>Nueva Especialidad</h3>
<form:form method="post" action="addEspecialidad" commandName="especialidad">
 
    <table>
    <tr>
        <td><form:label path="nombre">Especialidad:</form:label></td>
        <td><form:input path="nombre" /></td>
    </tr>
   
    <tr>
        <td>
            <input type="submit" value="Guardar"/>
        </td>
        <td>
            <input type="button" value="Cancelar" onclick="location.href='mainEspecialidad';"/>
        </td>
    </tr>
</table> 
</form:form>
 

</body>
</html>