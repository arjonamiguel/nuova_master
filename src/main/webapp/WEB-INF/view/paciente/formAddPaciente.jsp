<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>  
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Nuova</title>
		<link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/img/favicon/favicon.ico">
		
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>       
		<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
		<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
		<script src="<%=request.getContextPath()%>/resources/js/jquery/bootstrap-collapse.js" /></script>
		<link href="<%=request.getContextPath()%>/resources/css/nuova.css" rel="stylesheet"/>
		<link href="<%=request.getContextPath()%>/resources/css/panel.css" rel="stylesheet"/>
		<link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap-responsive.css" rel="stylesheet"/>
		<script src="<c:url value="/resources/js/jquery/jquery.validate.min.js" />"></script>
		<link href="<%=request.getContextPath()%>/resources/montrezorro-bootstrap-checkbox-fa865ff/css/bootstrap-checkbox.css" rel="stylesheet"/>
		<script src="<%=request.getContextPath()%>/resources/montrezorro-bootstrap-checkbox-fa865ff/js/bootstrap-checkbox.js" /></script>
		
<style>
label.error {
  color: #a94442;
  background-color: #f2dede;
  border-color: #ebccd1;
  padding:1px 20px 1px 20px;
  width:58%;
}
</style>		
	
<SCRIPT language="javascript">
	var index = 0;
	var coseguro=0;
	var titular=0;
	function Eliminar (i) {
	    //document.getElementsByTagName("table")[0].setAttribute("id","tableid");
	    document.getElementById("dataTable").deleteRow(i);
	}
       
       function addRow(tableID) {
			
           var table = document.getElementById(tableID);

           var rowCount = table.rows.length;
           var row = table.insertRow(rowCount);
           
           	if(document.getElementById("obrasocial").value=="NONE"){
            	return;
        	} 

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
            var att = document.createAttribute("class");
            att.value = "checkbox";  
       		element3.setAttributeNode(att);
           cell4.appendChild(element3);

           var cell1 = row.insertCell(4);
           row.valign = "BASELINE";
           cell1.innerHTML = "<button type='button' class='btn btn-link' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'>eliminar</button>"

           index ++;
           $(".checkbox").checkbox();

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
       
	function updatecoseguro(){
			if(document.getElementById("coseguro").value=="true" && coseguroFlag==0){
			//validacion on load
				$("#infocoseguro").click();
				coseguroFlag=1;
			}else{
			//validacion on change
				if(coseguroFlag==1){
					document.getElementById("coseguro").value="false";
					coseguroFlag=0;
				}else{
					document.getElementById("coseguro").value="true";
					coseguroFlag=1;
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
        					<div class="formInput"><form:input path="dni" type="number" /></div>
			   		</div>
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="apellido">Apellido:</form:label></div>
        					<div class="formInput"><form:input path="apellido" type="text"/></div>
			   		</div>
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="nombre">Nombre:</form:label></div>
        					<div class="formInput"><form:input path="nombre" type="text"/></div>
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
        					<div class="formInput"><form:input path="telefono" type="number"/></div>
			   		</div>
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="mail">E-Mail:</form:label></div>
        					<div class="formInput"><form:input path="mail" type="email"/></div>
			   		</div>
			   	</div>
			   	<div class="row-fluid">
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="provincia">Provincia Origen:</form:label></div>
        					<div class="formInput">
        						<form:select path="provincia" style="width:83%; margin-bottom:0px">
									<form:option value="NONE" label="Seleccione Provincia ..."/>
									<form:options items="${provinciaList}"  />			    
								</form:select>
        					</div>
			   		</div>
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="domicilio">Domicilio:</form:label></div>
        					<div class="formInput"><form:textarea path="domicilio" cssStyle="width:78%"/></div>
			   		</div>
			   		<div class="span1" style="margin-top:2%;">
			   				<div class="formLabel"><form:label path="coseguro">Coseguro:</form:label></div>
			   				
							
			   		</div>
			   		<div class="span3" style="margin-top:3%;">
			   		
							<div class="material-switch pull-left">
								<input id="coseguro" name="coseguro" type="checkbox" value="true">
								<label for="coseguro" class="label-success"></label>
								<div style="padding-top:10%;">
									NO - SI
								</div>
							</div>
							
			   		</div>
	
			   	</div>
			   	<div class="row-fluid">
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="titular">Parentesco:</form:label></div>
							<div  class="formInput">
								<form:select path="parentesco" style="width:83%; margin-bottom:0px">
									<form:option value="-1" label="Seleccione Parentesco ..."/>
									<form:options items="${parentescosList}"  itemLabel="value" itemValue="id"/>			    
								</form:select>
							</div>
			   		</div>
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="zonaAfiliacion">Zona Afiliación:</form:label></div>
        					<div class="formInput">
        						<form:select path="zonaAfiliacion" style="width:83%; margin-bottom:0px">
									<form:option value="NONE" label="Seleccione Zona Afiliación ..."/>
									<form:options items="${provinciaList}"  />			    
								</form:select>
        					</div>
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
					<div style="float:right;"><input type="button" value="Cancelar" onclick="location.href='/nuova/mainPaciente';" class="btn"/></div>
						<div style="float:right;padding-right:2%;"><input type="submit" value="Guardar" class="btn btn-info"/></div> 
			 			
					</div>
				</div>
			</div>
</div>	
</div>
</form:form>

</body>
</html>
<script>
		document.getElementById("mainPaciente").parentNode.classList.add("active");

		function callExistDni(dni) {
			var retorno;
			$.ajax({
				url : "ajaxGetExistDni?dni=" + dni,
				type : "GET",
				contentType : "application/json; charset=utf-8",
				//    data: jsonString, //Stringified Json Object
				async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
				cache : false, //This will force requested pages not to be cached by the browser          
				processData : false, //To avoid making query String instead of JSON
					
				success : function(existDni) {
					retorno = existDni;
				}
			});

			return retorno;
		}
		
			$("#paciente").validate({
    
        // Specify the validation rules
        rules: {
        	 dni: {
                required: true,
                minlength: 7
            },
            apellido: "required",
            nombre: "required",

            email: {
                required: true,
                email: true
            },
            fechaNacimiento: "required"
        },
        
        // Specify the validation error messages
        messages: {
        	 dni: {
                required: "Ingrese DNI",
                minlength: "DNI debe tener al menos 7 caracteres de largo"
            },
            apellido: "Ingrese apellido",
            nombre: "Ingrese nombre",

			fechaNacimiento : "Ingrese fecha de nacimiento"
        },
        submitHandler: function(form) {
        	var dni = document.getElementById("dni");            
            if (callExistDni(dni.value)){
                alert("El DNI ingresado ya existe.");
                dni.focus();
            } else {
            	form.submit();
            }        
            
        }
    });

</script>