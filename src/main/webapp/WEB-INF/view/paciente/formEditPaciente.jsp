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
<script type="text/javascript">
	$(document).ready(function() {
		var map = new Object();
		var objects = [];

		$('input.typeahead').typeahead({
			source : function(query, process) {
				$.ajax({
					url : '/nuova/ajaxGetAutoCompleteLocalidades',
					type : 'POST',
					dataType : 'JSON',
					data : 'query=' + query,
					success : function(data) {
						console.log(data);
						$.each(data, function(i, object) {
							map[object.value] = object;
							if (objects[i] == null) {
								objects.push(object.value);
							}
						});
						process(objects);
						objects = [];
					}
				});
			},
			updater : function(item) {
				$('#localidadId').val(map[item].id);
				return item;
			}
		});
	});
</script>	

	
<SCRIPT language="javascript">
    
        var coseguroFlag=0;
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
 
			if(document.getElementById("obrasocial.nombre").value=="-1"){
            	return;
        	}
 
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
            var att = document.createAttribute("class");
            att.value = "checkbox";  
       		element2.setAttributeNode(att);
       	
            cell4.appendChild(element2);
            
            var cell5 = row.insertCell(4);
			cell5.innerHTML="<button type='button' class='btn btn-danger btn-xs' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'><span class='icon icon-remove' title='Eliminar'></span></button>";

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

		function nuevoAdherente() {
			var titularId = document.getElementById("pacienteId").value;
			window.location.href = "/nuova/formAddAdherente/"+titularId;
		}
		function updateDate(){
			document.getElementById("fechaNacimiento").value=document.getElementById("registration-date").value;
		}
		function updatecoseguro(){
			if(document.getElementById("coseguro").value=="true" && coseguroFlag==0){
			//validacion on load
				coseguroFlag=1;
				if($( "#coseguro").prop( "checked" )){
					$("#coseguroAux").click();
				}
			}else{
			//validacion on change
				if($( "#coseguro").prop( "checked" )){
					 $("#coseguro").removeAttr("checked");
				}else{
					$("#coseguro").click();
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
<form:hidden path="pacienteId"/>
	<div class="panel panel-info">
	<div class="panel-heading">
		<div class="panel-title">
		<b>Editar Paciente</b>
    	 	<c:if test="${paciente.parentesco > 0}">
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
			   				<div class="formLabel"><form:label path="provincia">Provincia Origen:</form:label></div>
        					<div class="formInput">
        						<form:select path="provincia" style="width:83%; margin-bottom:0px">
									<form:option value="NONE" label="Seleccione Provincia ..."/>
									<form:options items="${provinciaList}"  />			    
								</form:select>
        					</div>
			   		</div>
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="domicilio">Localidad:</form:label></div>
        					<div class="formInput">
        					<form:hidden path="localidadId"/>
        					<form:input path="localidadString"
        						data-provide="typeahead" 
								class="typeahead"								
								type="text"								
								placeholder="Ingrese Localidad ..."
								autocomplete="off"
        					/> 
        					<a href="#" title="Nueva Localidad">
								<img src="/nuova/resources/img/list_add_16x16.png">
							</a>
							</div>
			   		</div>
			   	
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="domicilio">Domicilio:</form:label></div>
        					<div class="formInput"><form:textarea path="domicilio" cssStyle="width:78%"/></div>
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
			   		
			   		<div class="span1" style="">
			   				<div class="formLabel"><form:label path="coseguro">Coseguro:</form:label></div>
			   				
							
			   		</div>
			   		<div class="span3" style="margin-top:1%;">
			   		
							<div class="material-switch pull-left">
								<input id="coseguro" name="coseguro" type="checkbox" value="true">
								<label for="coseguro" class="label-success"></label>
								<div style="padding-top:10%;">
									NO - SI
								</div>
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
			<div class="span4">
			   <div class="formLabel"><form:label path="obrasocial">Obra Social:</form:label></div>
        		<div class="formInput">
        		<form:select path="obrasocial.obrasocialId" style="width:83%; margin-bottom:0px">
				<form:option value="-1" label="Seleccione Obra Social ..."/>
				<form:options items="${obrasocialList}" itemLabel="nombre" itemValue="obrasocialId" />
				</form:select>
        		</div>
			 </div>
			 
			 <div class="span4">
			 	<div class="formLabel"><form:label path="obrasocial.credencial">Credencial:</form:label></div>
        	 	<div class="formInput"><form:input path="obrasocial.credencial"/></div>
			 </div>
		</div>
						
	</div>		
</div>


<c:if test="${paciente.parentesco == 0}">
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
		            <TD>Parentesco</TD>
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
			        <td>${adh.parentescoDescription}</td>
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

function callExistDni(dni) {
	var retorno;
	$.ajax({
		url : "/nuova/ajaxGetExistDni?dni=" + dni,
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

			document.getElementById("mainPaciente").parentNode.classList.add("active");
        	document.getElementById("registration-date").value=document.getElementById("fechaNacimiento").value;
        	updatecoseguro();
			$(".checkbox").checkbox();
			
			
		$("#paciente").validate({
    
        // Specify the validation rules
        rules: {
        	 dni: {
                required: true,
                minlength: 7,
                maxlength:10
            },
            apellido: "required",
            nombre: "required",
            localidadString: "required"
        },
        
        // Specify the validation error messages
        messages: {
        	 dni: {
                required: "Ingrese DNI",
                minlength: "DNI debe tener al menos 7 caracteres de largo",
                maxlength: "DNI deber ser menor a 10 caracteres de largo"
            },
            apellido: "Ingrese apellido",
            nombre: "Ingrese nombre",
            localidadString : "Seleccione Localidad"

        },
                submitHandler: function(form) {
                	var dni = document.getElementById("dni");            
                    if (callExistDni(dni.value)){
                        if(!confirm("El DNI ingresado ya existe.\nPresione Cancelar si deséa Cambiarlo?")){
                        	dni.focus();
                        }else {
                        	form.submit();            	
                        }
                        
                    } else {
                    	form.submit();
                    }        
        }
    });
</script>