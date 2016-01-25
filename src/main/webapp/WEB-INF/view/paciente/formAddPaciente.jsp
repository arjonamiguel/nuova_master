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
	var index = 0;
	var liberado=0;
	var titular=0;
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
		
		function updateDate(){
			document.getElementById("fechaNacimiento").value=document.getElementById("registration-date").value;
		}

       </SCRIPT>
</head>
<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<jsp:include page="../breadcrumb.jsp"></jsp:include>

<div class="mainContainer"> 
<div class="panelContainer">
<form:form method="post" action="addPaciente" commandName="paciente">
	<div class="panel panel-info">
	<div class="panel-heading">
		<div class="panel-title">Nuevo Paciente</div>
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
								<form:select path="obrasocial" style="width:88%; margin-bottom:0px">
							   		<form:option value="NONE" label="Seleccione Obra Social ..."/>
							   		<form:options items="${obrasocialList}" itemLabel="nombre" itemValue="obrasocialId" />			    
								</form:select>
							</div>
							<div class="span1">
								<div style="float:right;"><INPUT type="button" value="Agregar" onclick="addRow('dataTable')" class="btn btn-info"/></div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="span12">
									<TABLE id="dataTable" class="table" style="width: 100%; margin-top:0px;">
								        <TR>
								            <TD>ID</TD>
								            <TD>Obra Social</TD>        
								            <TD style="width: 20%">Nro Credencial</TD>
								            <TD style="width: 15%">Original/Provisoria</TD>
								            <td></td>
								        </TR>
					    			</TABLE>
							</div>
						</div>
			</div>		
</div>
<div class="panel panel-info">
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