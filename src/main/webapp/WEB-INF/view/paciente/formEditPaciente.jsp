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
	<script src="<%=request.getContextPath()%>/resources/js/jquery/bootstrap-collapse.js"/></script>
	<link href="<%=request.getContextPath()%>/resources/css/nuova.css" rel="stylesheet"/>
	
 <SCRIPT language="javascript">
        var siprosa=0;
        var liberado=0;
		
        function Eliminar (i) {    	 
    	    document.getElementById("tb_paciente_obrasocial").deleteRow(i);
    	}
    	
        function addRow(tableID) {
        	var index = document.getElementById(tableID).getElementsByTagName('tr').length;
        	index ++;	
            var table = document.getElementById(tableID);
 
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
  
            var cell2 = row.insertCell(0);                      
            cell2.innerHTML = document.getElementById("obrasocial.nombre").value+"<input type='hidden' name='obrasocialListEdit["+index+"].obrasocialId' value='"+document.getElementById("obrasocial.nombre").value+"'>";

            var cell3 = row.insertCell(1);
            cell3.innerHTML = document.getElementById('obrasocial.nombre').options[document.getElementById('obrasocial.nombre').selectedIndex].text; 
 
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
        function updateLiberado(){
			if(liberado==0){
				liberado=1;
				document.getElementById("liberado").value=1;
				
			}else{
				siprosa=0;
				document.getElementById("liberado").value=0;
				
			}
		}

		function nuevoAdherente() {
			var titularId = document.getElementById("pacienteId").value;
			window.location.href = "/nuova/formAddAdherente/"+titularId;
		}
        </SCRIPT>
</head>
<body style="background-color:#eee;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<jsp:include page="../breadcrumb.jsp"></jsp:include>

<div class="mainContainer"> 
<form:form method="post" action="/nuova/editPaciente" commandName="paciente">

    	<table class="table" style="background-color:white;border-color:#bce8f1;margin-top:2%;margin-left:2%;;width:80%;">
    	<tr style="background-color:#d9edf7;color:#31708f;">
    		<td colspan="6">
 	    	<h4>Editar Paciente</h4>
    	 	<c:if test="${!isTitular}">
     			<h4>Titular: <a href="/nuova/formEditPaciente/${paciente.pacienteTitular.pacienteId}">${paciente.pacienteTitular.apellido}, ${paciente.pacienteTitular.nombre}</a></h4>
     		</c:if>
     </td>
    	</tr>
	    <tr>
	    	<td><form:label path="dni">DNI:</form:label></td>
	        <td><form:input path="dni" /><form:hidden path="pacienteId" /></td>
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
	        <td colspan=""><form:textarea path="domicilio" cssStyle="width:94%"/></td>
	        <td><form:label path="liberado">Liberado:</form:label></td>
	        <td>	        	
	        	<form:checkbox path="liberado" />	        	
	        </td>
	    </tr>	
	    <tr>
     	<td colspan="4"></td>	
 	    	<td>
 	    	 	<form:label path="titular">Titular</form:label>
 	    	</td>
 	    	<td>
 	    		<input type="checkbox" name="titular"  ${paciente.checkedTitular} />
 	    	</td>        	
     	</tr>       
	    <tr style="background-color:#d9edf7;color:#31708f;">
	         <td colspan="6"><h5>Obra Social</h5></td>         
	    </tr>
    	<tr>
        	<td colspan="6">
           	<div>
				<div class="inputProf">
				<form:select path="obrasocial.nombre" style="width:70%; margin-bottom:0px">
					   <form:option value="NONE" label="Seleccione Obra Social ..."/>
					   <form:options items="${obrasocialList}" itemLabel="nombre" itemValue="obrasocialId" />			    
					</form:select>
					<INPUT type="button" value="Agregar" onclick="addRow('tb_paciente_obrasocial')" class="btn btn-info"/>
						    	
					
				</div>	
			</div>
    		<div>		    
			    <TABLE id="tb_paciente_obrasocial" class="table table-striped custab" style="width: 100%; margin: 2% 0">
			        <TR>
			            <TD>Id</TD>
			            <TD>Obra Social</TD>        
			            <TD>Nro Credencial</TD>
			            <TD>Original/Provisoria</TD>
			            <td></td>
			        </TR>
			        <% int index = 0;%>
			        <c:forEach items="${paciente.obrasocialList}" var="po" varStatus="loop" >
			    	<tr>
				        <td>${po.obrasocialId}<input type="hidden" name = "obrasocialListEdit[<%=index%>].obrasocialId" value = "${po.obrasocialId}" /> </td>
				        <td>${po.nombre}</td>        
				        <td><input type="text" value="${po.credencial}" name = "obrasocialListEdit[<%=index%>].credencial"></td>
				        <td> <input type="checkbox" name="obrasocialListEdit[<%=index%>].original" ${po.original} /></td>
				        <td><button type='button' class='btn btn-link' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'>eliminar</button></td>
				        <%index++;%>
			    	</tr>
					</c:forEach>
			    </TABLE>
	   	
 	</div>
        
        </td>
    </tr>
    
     <c:if test="${isTitular}">
	   	<tr style="background-color:#d9edf7;color:#31708f;">
        	<td colspan="6"><h5>Adherentes</h5></td>         
    	</tr>
	    <tr>
		    <td colspan="6">
		    <div style="text-align: right;">
		    <INPUT type="button" value="Nuevo Adherente" onclick="nuevoAdherente()" class="btn btn-success"/>
			</div>
		     <TABLE id="dataTableAdherente" class="table table-striped custab" style="width: 100%; margin: 1% 0">
		        <TR>
		        	
		            <TD>ID</TD>
		            <TD>DNI</TD>
		            <TD>Apellido</TD>        
		            <TD>Nombre</TD>
		            <TD>Credencial</TD>
		            <TD></TD>
		        </TR>
		         <% int index2 = 0;%>
		        <c:forEach items="${paciente.adherentes}" var="adh" varStatus="loop" >
		    	<tr>
			    	
			        <td>${adh.pacienteId}<input type="hidden" name = "adherentesEditList[<%=index2%>].pacienteId" value = "${adh.pacienteId}" /> </td>
			        <td>${adh.dni}</td>        
			        <td>${adh.apellido}</td>
			        <td>${adh.nombre}</td>
			        <td>${adh.crdencial}</td>
			        <td>
			        	<a class="btn btn-info btn-xs" href="/nuova/formEditPaciente/${adh.pacienteId}" title="Editar Adherente">
			        	<span class="icon icon-edit" title="Editar Adherente"></span></a>		        
			    		<a class="btn btn-danger btn-xs" href="/nuova/formDeletePaciente/${adh.pacienteId}" title="Eliminar Adherente">
			    		<span class="icon icon-remove" title="Eliminar Adherente"></span></a>
			    	</td>
			        <%index2++;%>
		    	</tr>
				</c:forEach>
		    </TABLE> 
		    </td>
	    </tr>
</c:if>
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
</form:form>
 
</div>
</body>
</html>