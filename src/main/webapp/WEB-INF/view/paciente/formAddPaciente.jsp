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
 .label-error {
		  color: #a94442;
		  background-color: #f2dede;
		  border-color: #ebccd1;
		  padding:1px 20px 1px 20px;
		  width:22%;
		}
</style>		
<script type="text/javascript">
	$(function() {
	  $('#btnLaunch').click(function() {
		  document.getElementById("empresaNombre").value="";
	    $('#myModal').modal('show');
	  });
	  
	  $('#btnSave').click(function() {
	    var nombreEmpresa = document.getElementById("empresaNombre").value;
	    var resp = callNuevaEmpresa(getJsonEmpresa(nombreEmpresa));
	    refreshEmpresas();
	    $('#myModal').modal('hide');
	  });
	});


	$(function() {
		  $('#btnLocalidad').click(function() {
			  document.getElementById("localidadNombre").value="";
		    $('#modalLocalidad').modal('show');
		  });
		  
		  $('#btnSaveLocalidad').click(function() {
		    var localidad = document.getElementById("localidadNombre").value;
		    var resp = callNuevaLocalidad(getJsonLocalidad(localidad));
		    $('#modalLocalidad').modal('hide');
		  });
		});
	
	  function refreshEmpresas() {
			var empresas = callEmpresas();
			$('#empresaId')
					.empty()
					.append(
							'<option selected="selected" value="-1">Seleccione Empresa...</option>');
			$.each(empresas, function(key, value) {
				$('#empresaId').append($('<option>', {
					value : value.id
				}).text(value.value));
			});

		}


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
		
		function updateDateVenc(){
			document.getElementById("vencCarnet").value=document.getElementById("registration-date-venc").value;
		}

		function showEmpresas() {						
			var trabajo = document.getElementById("trabajaEn").value;
			var t = parseInt(trabajo); 
			if( t == 0) {
				document.getElementById("empresaId").disabled = "";
				
			}else {
				document.getElementById("empresaId").value = "-1";
				document.getElementById("empresaId").disabled = "false";
			}

		}
		
		function showEmpresaTitular(){
			var titular = document.getElementById("parentesco").value;
			if (titular != 0) {
				document.getElementById("empresaId").value = "-1";
				document.getElementById("empresaId").disabled = "false";
				document.getElementById("trabajaEn").value = "-1";
				document.getElementById("trabajaEn").disabled = "false";
			}else {
				document.getElementById("empresaId").disabled = "";
				document.getElementById("trabajaEn").disabled = "";
			}
		}
		
		function razonEnable(){
				if($( "#coseguro").prop( "checked" )){
					 $("#razonCoseguro").prop("disabled", false);
				}else{
					$("#razonCoseguro").val("NONE");
					$("#razonCoseguro").prop("disabled", true);
					$("#vencCarnetDiv").css("visibility","hidden");
				}
		}
		
		function validateDniRepetido(){
			if($("#flagDni").val()=="repetido"){
				document.getElementById("messageAlert").innerHTML='<div class="alert alert-danger alert-dismissable"><button type="button" class="close" data-dismiss="alert" aria-hidden="true">�</button><strong>ERROR GRAVE!</strong> Se intent� ingresar un paciente con DNI repetido. Intente nuevamente</div>';
			}
		}
		
		function validateDiscapacitado(){
			if($('#razonCoseguro').val()=="Discapacitado"){
				$("#vencCarnetDiv").css("visibility","visible");
			}else{
				$("#vencCarnetDiv").css("visibility","hidden");
			}
		}

       </SCRIPT>
</head>
<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>

<div class="mainContainer"> 
<div class="panelContainer">
<form:form method="post" action="addPaciente" commandName="paciente">
	<div class="panel panel-info">
	<div id="messageAlert"></div>
	<div class="panel-heading">
		<input id="flagDni" type="hidden" value="${dniRepetido}">
		<div class="panel-title">Nuevo Paciente</div>
		<div class="label-error" id="message" style="float:left;margin-left:8%;visibility:hidden;">El DNI ingresado ya existe.</div>
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
			   				<div class="formLabel"><form:label path="localidadString">Localidad:</form:label></div>
        					<div class="formInput">
        					<input type="hidden" name="localidadId" id="localidadId" value="">										
							<input
								data-provide="typeahead" 
								class="typeahead"
								name="localidadString"
								type="text"
								id="localidadString"
								placeholder="Ingrese Localidad ..."
								autocomplete="off"
								>
        					<a href="#" title="Nueva Localidad" id="btnLocalidad">
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
								<form:select path="parentesco" style="width:83%; margin-bottom:0px" onchange="showEmpresaTitular()">
									<form:option value="-1" label="Seleccione Parentesco ..."/>
									<form:options items="${parentescosList}"  itemLabel="value" itemValue="id"/>			    
								</form:select>
							</div>
			   		</div>
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="zonaAfiliacion">Zona Afiliaci�n:</form:label></div>
        					<div class="formInput">
        						<form:select path="zonaAfiliacion" style="width:83%; margin-bottom:0px">
									<form:option value="NONE" label="Seleccione Zona Afiliaci�n ..."/>
									<form:options items="${provinciaList}"  />			    
								</form:select>
        					</div>
			   		</div>			   		
			   		
			   	</div>
			   	<br>
			   	<div class="row-fluid">
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="trabajaEn">Trabaja En:</form:label></div>
        					<div class="formInput">
        						<form:select path="trabajaEn" style="width:83%; margin-bottom:0px" onchange="showEmpresas()">
									<form:option value="-1" label="Seleccione donde Trabaja ..."/>
									<form:options items="${trabajaEnList}"  itemLabel="value" itemValue="id"/>
								</form:select>
        					</div>
			   		
			   		</div>
			   		<div class="span4">
			   				<div class="formLabel"><form:label path="empresaId">Empresa:</form:label></div>
        					<div class="formInput">
        						<form:select path="empresaId" style="width:83%; margin-bottom:0px">
									<form:option value="-1" label="Seleccione Empresa..."/>
									<form:options items="${empresas}"  itemLabel="nombre" itemValue="empresaId"/>
								</form:select>
								<a href="#" title="Nueva Empresa" id="btnLaunch">
								<img src="/nuova/resources/img/list_add_16x16.png">
								</a>
        					</div>       					
			   		
			   		</div>
			   		
			   	</div>	
			   				   	
		 </div>
	</div>	
</div>


<div class="panel panel-info">
	<div class="panel-heading">
		<div class="panel-title">Coseguro</div>
	</div>
	<div class="panel-body">
		<div class="row-fluid">
					<div class="span4">
			   				<div class="formLabel" style="padding-left:20px">
			   				<form:label path="coseguro">Seleccione:</form:label>
			   				</div>
			   				
			   				<div class="formInput">
			   				<div class="material-switch pull-left" style="margin-top:1%; padding-left:20px">
								<input id="coseguro" name="coseguro" type="checkbox" value="true">
								<label for="coseguro" class="label-success" onclick="razonEnable()"></label>
								<div style="padding-top:10%;">
									NO - SI
								</div>
							</div>
			   				</div>
							
			   		</div>
			   		
			   		<div class="span4">
			   			<div class="formLabel"><form:label path="razonCoseguro">Raz�n Coseguro:</form:label></div>
        					<div class="formInput">
        						<form:select path="razonCoseguro" style="width:83%; margin-bottom:0px" onchange="validateDiscapacitado()">
									<form:option value="NONE" label="Seleccione Raz�n Coseguro ..."/>
									<form:options items="${razonCoseguroList}"  />			    
								</form:select>
        					</div>
			   		</div>
			   		<div class="span4" id="vencCarnetDiv" style="visibility:hidden;">
			   		<div style="visibility:hidden;height:0px;">
						<form:input path="vencCarnet" class="date"/>
					</div>
			   		<div class="formLabel"><form:label path="vencCarnet">Vencimiento Carnet:</form:label></div>
			   			<div class="formInput">
							<div id="calendar">
								<div class="input-group registration-date-time" style="padding-top:0%;">
									<input class="form-control" name="registration-date-venc" id="registration-date-venc" type="date"  onchange="javascript:updateDateVenc();">
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
				<form:option value="NONE" label="Seleccione Obra Social ..."/>
				<form:options items="${obrasocialList}" itemLabel="nombre" itemValue="obrasocialId" />
				</form:select>
        		</div>
			 </div>
			 
			 <div class="span4">
			 	<div class="formLabel"><form:label path="obrasocial.credencial">Credencial:</form:label></div>
        	 	<div class="formInput">
        	 		<form:input path="obrasocial.credencial" cssStyle="width:25%"/><b>&nbsp;-&nbsp;</b>
        	 		<form:input path="obrasocial.credencialSufijo" cssStyle="width:10%"/>
        	 	</div>
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

<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">�</span><span class="sr-only"></span></button>
        <h4 class="modal-title">Nueva Empresa</h4>
      </div>
      <div class="modal-body">
        <p>Nombre de la Empresa: </p>
        <input type="text" id="empresaNombre" name="empresaNombre">
      </div>
      <div class="modal-footer">
        <button type="button" id="btnSave" class="btn btn-info">Guardar</button>      
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="modalLocalidad">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">�</span><span class="sr-only"></span></button>
        <h4 class="modal-title">Nueva Localidad</h4>
      </div>
      <div class="modal-body">
        <p>Nombre de Localidad: </p>
        <input type="text" id="localidadNombre" name="localidadNombre">
      </div>
      <div class="modal-footer">
        <button type="button" id="btnSaveLocalidad" class="btn btn-info">Guardar</button>      
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</html>
<script>
		document.getElementById("mainPaciente").parentNode.classList.add("active");
		showEmpresas();

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
		
		function callNuevaEmpresa(jsonEmpresa) {
			var retorno;
			$.ajax({
				url : "/nuova/ajaxPostNuevaEmpresa",
				type : "POST",
				contentType : "application/json; charset=utf-8",
				data: jsonEmpresa, //Stringified Json Object
				async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
				cache : false, //This will force requested pages not to be cached by the browser          
				processData : false, //To avoid making query String instead of JSON
				success : function(result) {
					retorno = result;
				}
			});

			return retorno;
		}

		
		function getJsonEmpresa(nombreEmpresa){
			return '{ "nombre":"'+nombreEmpresa+'"}';
		}

		function getJsonLocalidad(localidad){
			return '{ "nombre":"'+localidad+'"}';
		}


		function callNuevaLocalidad(jsonLocalidad) {
			var retorno;
			$.ajax({
				url : "/nuova/ajaxPostNuevaLocalidad",
				type : "POST",
				contentType : "application/json; charset=utf-8",
				data: jsonLocalidad, //Stringified Json Object
				async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
				cache : false, //This will force requested pages not to be cached by the browser          
				processData : false, //To avoid making query String instead of JSON
				success : function(result) {
					retorno = result;
				}
			});

			return retorno;
		}
				
		
		function callEmpresas() {
			var retorno;
			$.ajax({
				url : "/nuova/ajaxGetEmpresas",
				type : "GET",
				contentType : "application/json; charset=utf-8",
				//    data: jsonString, //Stringified Json Object
				async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
				cache : false, //This will force requested pages not to be cached by the browser          
				processData : false, //To avoid making query String instead of JSON
				success : function(page) {
					// Success Message Handler
					retorno = page;
				}
			});

			return retorno;
		}

		
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
            localidadString: "required",

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
                minlength: "DNI debe tener al menos 7 caracteres de largo",
                maxlength: "DNI deber ser menor a 10 caracteres de largo"
            },
            apellido: "Ingrese apellido",
            nombre: "Ingrese nombre",
            localidadString : "Seleccione Localidad",

			fechaNacimiento : "Ingrese fecha de nacimiento"
        },
        submitHandler: function(form) {
        	var dni = document.getElementById("dni");            
            if (callExistDni(dni.value)){
                $("#message").css("visibility","visible");
                dni.focus();
            } else {
            	form.submit();
            }        
            
        }
    });
	$("#coseguro").click();	
	$("#razonCoseguro").val("NONE");
	$("#razonCoseguro").prop("disabled", true);
	validateDniRepetido();
</script>