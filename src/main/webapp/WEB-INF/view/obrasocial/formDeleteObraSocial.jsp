<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Nuova</title>
</head>
<body>
<jsp:include page="../sec_menu.jsp"></jsp:include>
<h3>Eliminar Obra Social</h3>
<form:form method="POST" action="/nuova/deleteObraSocial" commandName="obrasocial">
 
    <table>
    <tr>
        <td><form:label path="nombre">Obra Social:</form:label></td>
        <td><form:input path="nombre" /></td>
    </tr>
    
    <tr>
        <td></td>
        <td><form:hidden path="obrasocialId" /></td>
    </tr>
   
    <tr>
        <td>
            <input type="submit" value="Eliminar"/>
        </td>
        <td>
            <input type="button" value="Cancelar" onclick="location.href='/nuova/mainObraSocial';"/>
        </td>
    </tr>
</table> 
</form:form>
 

</body>
</html>