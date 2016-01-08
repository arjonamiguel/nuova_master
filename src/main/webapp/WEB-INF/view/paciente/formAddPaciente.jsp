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
	
<SCRIPT language="javascript">
       var siprosa=0;
	var index = 0;
	function Eliminar (i) {
	    //document.getElementsByTagName("table")[0].setAttribute("id","tableid");
	    document.getElementById("dataTable").deleteRow(i);
	}
       
       function addRow(tableID) {
			
           var table = document.getElementById(tableID);

           var rowCount = table.rows.length;
           var row = table.insertRow(rowCount);

           var cell2 = row.insertCell(0);                      
           cell2.innerHTML = document.getElementById("obrasocial").value+"<input type='hidden' name='obrasocialListEdit["+index+"].obrasocialId' value='"+document.getElementById("obrasocial").value+"'>";

            var cell3 = row.insertCell(1);
           cell3.innerHTML = document.getElementById('obrasocial').options[document.getElementById('obrasocial').selectedIndex].text; 

           var cell3 = row.insertCell(2);
           var element2 = document.createElement("input");
           element2.type = "text";
           element2.name="obrasocialListEdit["+index+"].credencial";
           cell3.appendChild(element2);

           var cell4 = row.insertCell(3);
           var element3 = document.createElement("input");
           element3.type = "checkbox";
           element3.name="obrasocialListEdit["+index+"].original";
           cell4.appendChild(element3);

           var cell1 = row.insertCell(4);
           row.valign = "BASELINE";
           cell1.innerHTML = "<button type='button' class='btn btn-link' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'>eliminar</button>"

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
<body style="background-color:#eee;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<jsp:include page="../breadcrumb.jsp"></jsp:include>

<div class="mainContainer"> 
<form:form method="post" action="addPaciente" commandName="paciente">
	<div>
	   	<table class="table" style="background-color:white;border-color:#bce8f1;margin-top:2%;margin-left:2%;;width:80%;">
    	<tr style="background-color:#d9edf7;color:#31708f;">
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
	    		<form:select path="provincia" style="width:80%; margin-bottom:0px">
					<form:option value="NONE" label="Seleccione Provincia ..."/>
					<form:options items="${provinciaList}"  />			    
				</form:select>
			</td>
	        <td><form:label path="domicilio">Domicilio:</form:label></td>
	        <td colspan=""><form:textarea path="domicilio" cssStyle="width:75%"/></td>
	        <td><form:label path="liberado">Liberado:</form:label></td>
	        <td><form:checkbox path="liberado"/></td>
	    </tr>	  
 		<tr>
    		<td colspan="4"></td>	
	    	<td>
	    	 	<form:label path="titular">Titular</form:label>
	    	</td>
	    	<td>
	    		<form:checkbox path="titular"/>
	    	</td>
        	
    	</tr>     
	    <tr style="background-color:#d9edf7;color:#31708f;">
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
					<INPUT type="button" value="Agregar" onclick="addRow('dataTable')" class="btn btn-info"/>
						    	
					
				</div>	
			</div>
    		<div>		    
			    <TABLE id="dataTable" class="table table-striped custab" style="width: 100%; margin: 2% 0">
			        <TR>
			        	
			            <TD>ID</TD>
			            <TD>Obra Social</TD>        
			            <TD style="width: 20%">Nro Credencial</TD>
			            <TD style="width: 15%">Original/Provisoria</TD>
			            <td></td>
			        </TR>
			    </TABLE>
	   	
 			</div>    
 			
        	</td>
    	</tr>
      	    
    	<tr>
        <td>
            <input class="btn btn-info" type="submit" value="Guardar"/>     
        </td>
        <td>
            <input type="button" value="Cancelar" onclick="location.href='/nuova/mainPaciente';" class="btn"/>
        </td>
        <td colspan="4">
        </td>
    </tr>
	</table>
	</div> 
</form:form>
 
</div>
</body>
</html>