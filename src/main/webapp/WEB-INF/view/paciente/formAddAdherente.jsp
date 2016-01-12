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
        var liberado=0;
		
        
        function addRow(tableID) {
        	var index = document.getElementById('tb_paciente_obrasocial').getElementsByTagName('tr').length;
        	index ++;	
            var table = document.getElementById(tableID);
 
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
 
            var cell1 = row.insertCell(0);
            var element1 = document.createElement("input");
            element1.type = "checkbox";
            element1.name="chkbox[]";
            cell1.appendChild(element1);
 
            var cell2 = row.insertCell(1);                      
            cell2.innerHTML = document.getElementById("obrasocial.nombre").value+"<input type='hidden' name='obrasocialListEdit["+index+"].obrasocialId' value='"+document.getElementById("obrasocial.nombre").value+"'>";
            var cell3 = row.insertCell(2);
            cell3.innerHTML = document.getElementById('obrasocial.nombre').options[document.getElementById('obrasocial.nombre').selectedIndex].text; 
 
            var cell3 = row.insertCell(3);
            var element2 = document.createElement("input");
            element2.type = "text";
            element2.name="obrasocialListEdit["+index+"].credencial";
            cell3.appendChild(element2);

            var cell4 = row.insertCell(4);
            var element3 = document.createElement("input");
            element3.type = "checkbox";
            element3.name="obrasocialListEdit["+index+"].original";
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

		function nuevoAdherente() {
			var titularId = document.getElementById("pacienteId").value;
			window.location.href = "/nuova/formAddAdherente/"+titularId;
		}
function updateDate(){
			document.getElementById("fechaNacimiento").value=document.getElementById("registration-date").value;
}
		
		function updateLiberado(){
			if(liberado==0){
				liberado=1;
				document.getElementById("liberado").value=1;

			}else{
				liberado=0;
				document.getElementById("liberado").value=0;

			}
		}
        </SCRIPT>
</head>
<body style="background-color:#eee;"class="bodyStyle">
<jsp:include page="../sec_menu.jsp"></jsp:include>

<div class="mainContainer" style="padding-top:1%;"> 
<form:form method="post" action="/nuova/addAdherente" commandName="paciente">
	<div class="panel panel-info">
	 	<div class="panel-heading">
	 	 	<div class="panel-title">Nuevo Adherente - Titular: ${datosTitular}</div>
	 	</div>
	 	<div style="padding-top:30px" class="panel-body" >
		 	<div class="container-fluid">
		 		<div class="row-fluid">
		 			<div class="span4">
		 				<form:hidden path="pacienteId"/>
    					<form:hidden path="titularId"/>
    					<div class="formLabel"><form:label path="dni">DNI:</form:label></div>
    					<div class="formInput"><form:input path="dni" /><form:input path="dni" /></div>
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
		 				<div id="labelDate" class="formLabel"><form:label path="fechaNacimiento">Fecha de Nacimiento:</form:label></div>
							<div style="visibility:hidden;height:0px;"><form:input path="fechaNacimiento" class="date"/></div>
							<div id="calendar">
							<div class="input-group registration-date-time" style="padding-top:0%;">
							<input class="form-control" name="registration_date" id="registration-date" type="date"  onchange="javascript:updateDate();">
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
		 				<div class="formInput">	<form:select path="provincia" style="width:70%; margin-bottom:0px">
							<form:option value="NONE" label="Seleccione Provincia ..."/>
								<form:options items="${provinciaList}"  />			    
							</form:select>
						</div>
		 			</div>
		 			<div class="span4">
		 				<div class="formLabel"><form:label path="domicilio">Domicilio:</form:label></div>
		 				<div class="formInput"><form:textarea path="domicilio" cssStyle="width:65%"/></div>
		 			</div>
		 			<div class="span4">
		 				<div class="formLabel"><form:label path="liberado">Liberado:</form:label></div>
						<div style="visibility:hidden;height:0px;"><form:input path="liberado" /></div>
						<label for="info" style="padding-left:28%;"><input type="checkbox" id="info" class="badgebox" onchange="javascript:updateLiberado();"><span class="badge">&check;</span></label>
		 				
		 			</div>
		 		</div>
		 	</div>
	 	</div>
	 </div>
	 
	 <div class="panel panel-info">
	 	<div class="panel-heading">
	 		<div class="panel-title">Obra Social</div>
	 	</div>
	 	<div style="padding-top:3%;" class="panel-body">
	 		<div class="row-fluid">
	 			<div class="span12">
	 					    <TABLE id="tb_paciente_obrasocial" class="table table-striped custab" style="width: 100%; margin: 1% 0">
	        				<TR>
					            <TD>Id</TD>
					            <TD>Obra Social</TD>        
					            <TD>Nro Credencial</TD>
					            <TD>Original/Provisoria</TD>
					        </TR>
					        
					        <% int index = 0;%>
					        <c:forEach items="${paciente.obrasocialList}" var="po" varStatus="loop" >
					    	<tr>
						    	
						        <td>${po.obrasocialId}<input type="hidden" name = "obrasocialListEdit[<%=index%>].obrasocialId" value = "${po.obrasocialId}" /> </td>
						        <td>${po.nombre}</td>        
						        <td><input type="text" value="${po.credencial}" name = "obrasocialListEdit[<%=index%>].credencial"></td>
						        <td> <input type="checkbox" name="obrasocialListEdit[<%=index%>].original" ${po.original} /></td>
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
				<div style="float:right;padding-right:2%;"><input type="submit" value="Guardar" class="btn btn-info"/></div> 
	 			<div style="float:right;"><input type="button" value="Cancelar" onclick="location.href = document.referrer; return false;" class="btn"/></div>
			</div>
		</div>
	 </div>
</div>
</form:form>
 </div>
 

</body>
</html>