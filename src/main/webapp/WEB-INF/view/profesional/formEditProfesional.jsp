<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Nuova</title>
      <SCRIPT language="javascript">
        function addRow(tableID) {
 
            var table = document.getElementById(tableID);
 
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
 
            var cell1 = row.insertCell(0);
            var element1 = document.createElement("input");
            element1.type = "checkbox";
            element1.name="chkbox[]";
            cell1.appendChild(element1);
 
            var cell2 = row.insertCell(1);                      
            cell2.innerHTML = document.getElementById("especialidad").value+"<input type='hidden' name='especialidadList' value='"+document.getElementById("especialidad").value+"'>";
          
            
 
            var cell3 = row.insertCell(2);
            cell3.innerHTML = document.getElementById('especialidad').options[document.getElementById('especialidad').selectedIndex].text; 
 
 
        }
 
        function deleteRow(tableID) {
            try {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
 
            for(var i=0; i<rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                if(null != chkbox && true == chkbox.checked) {
                    table.deleteRow(i);
                    rowCount--;
                    i--;
                }
 
 
            }
            }catch(e) {
                alert(e);
            }
        }
 
    </SCRIPT>
</head>
<body>
<jsp:include page="../sec_menu.jsp"></jsp:include>
<h3>Editar Profesional</h3>
<form:form method="post" action="/nuova/editProfesional" commandName="profesional">
 
    <table>
    <tr>
    	<td colspan="2"><form:hidden path="profesionalId" /></td>
    </tr>
    <tr>
        <td><form:label path="apellido">Apellido:</form:label></td>
        <td><form:input path="apellido" /></td>
    </tr>
    <tr>
        <td><form:label path="nombre">Nombre:</form:label></td>
        <td><form:input path="nombre" /></td>
    </tr>
    
    <tr>
        <td><form:label path="telefono">Telefono:</form:label></td>
        <td><form:input path="telefono" /></td>
    </tr>
    
    <tr>
        <td><form:label path="matricula">Matricula:</form:label></td>
        <td><form:input path="matricula" /></td>
    </tr>
    
    <tr>
        <td><form:label path="registroNacional">Registro Nacional:</form:label></td>
        <td><form:input path="registroNacional" /></td>
    </tr>
    
    <tr>
        <td><form:label path="tituloProfesional">Titulo Profesional:</form:label></td>
        <td><form:input path="tituloProfesional" /></td>
    </tr>
    
    <tr>
        <td><form:label path="habilitacionSiprosa">Habilitacion del Siprosa:</form:label></td>
        <td><form:input path="habilitacionSiprosa" /></td>
    </tr>
    
    <tr>
        <td><form:label path="fechaVencimientoHabilitacion">Fecha Vencimiento Habilitacion:</form:label></td>
        <td><form:input path="fechaVencimientoHabilitacion" /></td>
    </tr>
    
    <tr>
        <td><form:label path="fechaVencimientoHabilitacion">Especialidad:</form:label></td>
        
    </tr>
    
      <tr>
        <td><form:label path="especialidad">Especialidad:</form:label></td>
	    <td>    
	    	<form:select path="especialidad">
			   <form:option value="NONE" label="Seleccione Especialidad ..."/>
			   <form:options items="${especialidadList}" itemLabel="nombre" itemValue="especialidadId" />			    
			</form:select>
			<INPUT type="button" value="Add Row" onclick="addRow('dataTable')" />
 
    		<INPUT type="button" value="Delete Row" onclick="deleteRow('dataTable')" />
		</td>
    </tr>
    <tr>
    <td colspan="2">
    <TABLE id="dataTable" width="350px" border="1">
        <TR>
        	<TD></TD>
            <TD>Id</TD>
            <TD>Especialidad</TD>
            
        </TR>
    
    </TABLE>
    </td>
    </tr>
   
   
    <tr>
        <td>
            <input type="submit" value="Guardar"/>
        </td>
        <td>
            <input type="button" value="Cancelar" onclick="location.href='/nuova/mainProfesional';"/>
        </td>
    </tr>
    
</table> 
</form:form>
 

</body>
</html>