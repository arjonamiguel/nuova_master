<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Nuova</title>
    	<link href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
	<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
	<style>
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
	
	  <SCRIPT language="javascript">
        var siprosa=0;
		var index = 0;
        
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
            cell2.innerHTML = document.getElementById("obrasocial").value+"<input type='hidden' name='obrasocialList["+index+"].obrasocialId' value='"+document.getElementById("obrasocial").value+"'>";
            var cell3 = row.insertCell(2);
            cell3.innerHTML = document.getElementById('obrasocial').options[document.getElementById('obrasocial').selectedIndex].text; 
 
            var cell3 = row.insertCell(3);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name="obrasocialList["+index+"].credencial";
            cell3.appendChild(element2);

            var cell4 = row.insertCell(4);
            var element3 = document.createElement("input");
            element3.type = "checkbox";
            element3.name="obrasocialList["+index+"].original";
            cell4.appendChild(element3);

            index ++;
 
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


<form:form method="post" action="addPaciente" commandName="paciente">
 	<div class="row col-md-6 col-md-offset-2 custyle">
    <table class="table table-striped custab">
    <tr>
    <td colspan="6"><h4>Nuevo Paciente</h4></td>
    </tr>
    <tr>
    	<td><form:label path="dni">DNI:</form:label></td>
        <td><form:input path="dni" /></td>
        <td><form:label path="apellido">Apellido:</form:label></td>
        <td><form:input path="apellido" /></td>
        <td><form:label path="nombre">Nombre:</form:label></td>
        <td><form:input path="nombre" /></td>
    </tr>
   	<tr>
        <td><form:label path="fechaNacimiento">Fecha de Nacimiento:</form:label></td>
        <td><form:input path="fechaNacimiento" /></td>
             <td><form:label path="telefono">Telefono:</form:label></td>
        <td><form:input path="telefono" /></td>
        <td><form:label path="mail">E-Mail:</form:label></td>
        <td><form:input path="mail" /></td>
    </tr>
    <tr>
    	
    	<td><form:label path="provincia">Provincia:</form:label></td>
    	<td>
    	<form:select path="provincia" style="width:100%; margin-bottom:0px">
			<form:option value="NONE" label="Seleccione Provincia ..."/>
			<form:options items="${provinciaList}"  />			    
		</form:select>
		</td>
        <td><form:label path="domicilio">Domicilio:</form:label></td>
        <td colspan=""><form:textarea path="domicilio" cssStyle="width:100%"/></td>
        <td><form:label path="liberado">Liberado:</form:label></td>
        <td><form:input path="liberado" /></td>
    </tr>
    
    <tr>
         <td colspan="6"><h5>Obra Social</h5></td>         
    </tr>
    <tr>
        <td colspan="6">
           <div>   

			<div class="inputProf">
			<form:select path="obrasocial" style="width:70%; margin-bottom:0px">
				   <form:option value="NONE" label="Seleccione Obra Social ..."/>
				   <form:options items="${obrasocialList}" itemLabel="nombre" itemValue="obrasocialId" />			    
				</form:select>
				<INPUT type="button" value="Agregar" onclick="addRow('dataTable')" class="btn btn-primary"/>
					    	<INPUT type="button" value="Eliminar" onclick="deleteRow('dataTable')" class="btn"/>
				
			</div>	
			</div>
    <div>  
    
	    <TABLE id="dataTable" class="table table-striped custab" style="width: 100%; margin: 2% 0">
	        <TR>
	        	<TD></TD>
	            <TD>Id</TD>
	            <TD>Obra Social</TD>        
	            <TD>Nro Credencial</TD>
	            <TD>Original/Provisoria</TD>
	        </TR>
	    </TABLE>
	   	
 	</div>
        
        </td>
    </tr>
      <tr>
         <td colspan="6"><h5>Adherentes</h5></td>         
    </tr>
    <tr>
	    <td colspan="6">
	    <div style="text-align: right;">
	    <INPUT type="button" value="Add Row" onclick="addRowAdherente('dataTableAdherente')" class="btn btn-primary"/>
		<INPUT type="button" value="Delete Row" onclick="deleteRowAdherente('dataTableAdherente')" class="btn"/>
		</div>
	     <TABLE id="dataTableAdherente" class="table table-striped custab" style="width: 100%; margin: 2% 0">
	        <TR>
	        	<TD></TD>
	            <TD>Id</TD>
	            <TD>DNI</TD>
	            <TD>Apellido</TD>        
	            <TD>Nombre</TD>
	            <TD>Credencial</TD>
	        </TR>
	    </TABLE> 
	    </td>
    </tr>
    
    <tr>
        <td>
            <input class="btn btn-lg btn-primary btn-block btn-signin" type="submit" value="Guardar"/>
        </td>
        <td>
            <input type="button" value="Cancelar" onclick="location.href='mainPaciente';" class="btn"/>
        </td>
        <td colspan="4">
        </td>
    </tr>
	</table>
	</div> 
</form:form>
 

</body>
</html>