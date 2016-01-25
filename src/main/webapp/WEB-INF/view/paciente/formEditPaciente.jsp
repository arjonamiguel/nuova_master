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
		<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
		<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
		<script src="<%=request.getContextPath()%>/resources/js/jquery/bootstrap-collapse.js" />"></script>
		<link href="<%=request.getContextPath()%>/resources/css/nuova.css" rel="stylesheet"/>
		<link href="<%=request.getContextPath()%>/resources/css/panel.css" rel="stylesheet"/>
		<link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap-responsive.css" rel="stylesheet"/>
	
<SCRIPT language="javascript">
    
        var liberadoFlag=0;
        var titularFlag=0;
   
		
        function Eliminar (i) {    	 
    	    document.getElementById("tb_paciente_obrasocial").deleteRow(i);
    	}
    	
  function addRow(tableID) {
  			var table = document.getElementById(tableID);
        	var index = table.getElementsByTagName('tr').length;
        	index ++;	
 
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
 

 
            var cell1 = row.insertCell(0);                      
            cell1.innerHTML = document.getElementById("obrasocial.nombre").value+"<input type='hidden' name='obrasocialListEdit["+index+"].obrasocialId' value='"+document.getElementById("obrasocial.nombre").value+"'>";
            var cell2 = row.insertCell(1);
            cell2.innerHTML = document.getElementById('obrasocial.nombre').options[document.getElementById('obrasocial.nombre').selectedIndex].text; 
 
            var cell3 = row.insertCell(2);
            var element1 = document.createElement("input");
            element1.type = "text";
            element1.name="obrasocialListEdit["+index+"].credencial";
            cell3.appendChild(element1);

            var cell4 = row.insertCell(3);
            var element2 = document.createElement("input");
            element2.type = "checkbox";
            element2.name="obrasocialListEdit["+index+"].original";
            cell4.appendChild(element2);
            
            var cell5 = row.insertCell(4);
			cell5.innerHTML="<button type='button' class='btn btn-danger btn-xs' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'><span class='icon icon-remove' title='Eliminar'></span></button>";

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

		function nuevoAdherente() {
			var titularId = document.getElementById("pacienteId").value;
			window.location.href = "/nuova/formAddAdherente/"+titularId;
		}
		function updateDate(){
			document.getElementById("fechaNacimiento").value=document.getElementById("registration-date").value;
		}
		function updateLiberado(){
			if(document.getElementById("liberado").value=="true" && liberadoFlag==0){
			//validacion on load
				$("#infoLiberado").click();
				liberadoFlag=1;
			}else{
			//validacion on change
				if(liberadoFlag==1){
					document.getElementById("liberado").value="false";
					liberadoFlag=0;
				}else{
					document.getElementById("liberado").value="true";
					liberadoFlag=1;
				}
			}	
		}
		function updateTitular(){
			if(document.getElementById("titular").value=="true" && titularFlag==0){
			//validacion on load
				$("#infoTitular").click();
				titularFlag=1;
			}else{
			//validacion on change
				if(titularFlag==1){
					document.getElementById("titular").value="false";
					titularFlag=0;
				}else{
					document.getElementById("titular").value="true";
					titularFlag=1;
				}
			}	
		}
        </SCRIPT>
</head>
<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<jsp:include page="../breadcrumb.jsp"></jsp:include>

<div class="mainContainer"> 
<div class="panelContainer">
<form:form method="post" action="/nuova/editPaciente" commandName="paciente">
	<div class="panel panel-info">
	<div class="panel-heading">
		<div class="panel-title">
		Editar Paciente
    	 	<c:if test="${!isTitular}">
     			<h4>Titular: <a href="/nuova/formEditPaciente/${paciente.pacienteTitular.pacienteId}">${paciente.pacienteTitular.apellido}, ${paciente.pacienteTitular.nombre}</a></h4>
     		</c:if>
     	</div>
	</div>
	<div class="panel-body">
		<div class="container-fluid">
		  		<div class="row-fluid">
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="dni">DNI:</form:label></div>
        					<div class="formInput"><form:input path="dni" /></div>
			   		</div>
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="apellido">Apellido:</form:label></div>
        					<div class="formInput"><form:input path="apellido" /></div>
			   		</div>
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="nombre">Nombre:</form:label></div>
        					<div class="formInput"><form:input path="nombre" /></div>
			   		</div>
			   	</div>
			   	<div class="row-fluid">
			   		<div class="span4">
			   				<div id="labelDate" class="formLabel"><form:label path="fechaNacimiento">Nacimiento:</form:label></div>
							<div style="visibility:hidden;height:0px;"><form:input path="fechaNacimiento" class="date"/></div>
							<div class="formInput">
							<div id="calendar">
								<div class="input-group registration-date-time" style="padding-top:0%;">
									<input class="form-control" name="registration_date" id="registration-date" type="date"  onchange="javascript:updateDate();">
	            				</div>
	            			</div>	
	            			</div>	
			   		</div>
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="telefono">Telefono:</form:label></div>
        					<div class="formInput"><form:input path="telefono" /></div>
			   		</div>
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="mail">E-Mail:</form:label></div>
        					<div class="formInput"><form:input path="mail" /></div>
			   		</div>
			   	</div>
			   	<div class="row-fluid">
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="provincia">Provincia:</form:label></div>
        					<div class="formInput">
        						<form:select path="provincia" style="width:68%; margin-bottom:0px">
									<form:option value="NONE" label="Seleccione Provincia ..."/>
									<form:options items="${provinciaList}"  />			    
								</form:select>
        					</div>
			   		</div>
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="domicilio">Domicilio:</form:label></div>
        					<div class="formInput"><form:textarea path="domicilio" cssStyle="width:64%"/></div>
			   		</div>
			   		<div class="span2" style="margin-top:2%;">
			   				<div class="formLabel"><form:label path="liberado">Liberado:</form:label></div>
							<div style="visibility:hidden;height:0px;"><form:checkbox path="liberado" id="liberado"/></div>
							<label for="infoLiberado" style="padding-left:28%;"><input type="checkbox" id="infoLiberado" class="badgebox" onchange="javascript:updateLiberado();"><span class="badge">&check;</span></label>
			   		</div>
			   		<div class="span2" style="margin-top:2%;">
			   			<div class="formLabel"><form:label path="titular">Titular:</form:label></div>
							<div style="visibility:hidden;height:0px;"><form:checkbox path="titular" id="titular"/></div>
							<label for="infoTitular" style="padding-left:28%;"><input type="checkbox" id="infoTitular" class="badgebox" onchange="javascript:updateTitular();"><span class="badge">&check;</span></label>
			   		</div>
			   	</div>
		 </div>
	</div>	
</div>

<div class="panel panel-info">
			<div class="panel-heading">
				<div class="panel-title">Obra Social</div>
			</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="span9">
					</div>
					<div class="span2">
						<form:select path="obrasocial.nombre" style="width:88%; margin-bottom:0px">
					   		<form:option value="NONE" label="Seleccione Obra Social ..."/>
					   		<form:options items="${obrasocialList}" itemLabel="nombre" itemValue="obrasocialId" />			    
						</form:select>
					</div>
					<div class="span1">
						<div style="float:right;"><INPUT type="button" value="Agregar" onclick="addRow('tb_paciente_obrasocial')" class="btn btn-info"/></div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
							<TABLE id="tb_paciente_obrasocial"  class="table" style="width: 100%; margin-top:0px;">
						        <TR>
						        	
						            <TD>ID</TD>
						            <TD>Obra Social</TD>        
						            <TD style="width: 20%">Nro Credencial</TD>
						            <TD style="width: 15%">Original/Provisoria</TD>
						            <td></td>
						        </TR>
						        <% int index = 0;%>
						        <c:forEach items="${paciente.obrasocialList}" var="po" varStatus="loop" >
						    	<tr>
							        <td>${po.obrasocialId}<input type="hidden" name = "obrasocialListEdit[<%=index%>].obrasocialId" value = "${po.obrasocialId}" /> </td>
							        <td>${po.nombre}</td>        
							        <td><input type="text" value="${po.credencial}" name = "obrasocialListEdit[<%=index%>].credencial"></td>
							        <td> <input type="checkbox" name="obrasocialListEdit[<%=index%>].original" ${po.original} /></td>
							        <td>
							        <button type='button' class='btn btn-danger btn-xs' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'><span class="icon icon-remove" title="Eliminar"></span></button>
							        </td>
							        <%index++;%>
						    	</tr>
								</c:forEach>
			    			</TABLE>
					</div>
				</div>
				
				<div class="row-fluid">
					<div class="span8">
					</div>
					<div class="span4">
						
					</div>
				</div>
			</div>
			
</div>
<c:if test="${isTitular}">
<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Adherentes</div>
				</div>
				<div class="panel-body">
					<div class="row-fluid">
						<div class="span12">
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
						</div>
					</div>
				</div>
</div>
</c:if>
<div class="panel panel-info" style="padding-top:0px;">
				<div class="panel-body">
					<div class="row-fluid">
						<div class="span12">
							<div style="float:right;padding-right:2%;"><input type="submit" value="Guardar" class="btn btn-info"/></div> 
			 				<div style="float:right;"><input type="button" value="Cancelar" onclick="location.href='/nuova/mainPaciente';" class="btn"/></div>
						</div>
					</div>
				</div>
</div>

</div>
</form:form>

</body>
</html>
<script>
        	document.getElementById("registration-date").value=document.getElementById("fechaNacimiento").value;
        	updateLiberado();
			updateTitular();
</script>