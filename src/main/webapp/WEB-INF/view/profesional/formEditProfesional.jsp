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

<style>
.btn-info {
	color: #fff;
	background-color: #5bc0de;
	border-color: #46b8da;
}

label.error {
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
	padding: 1px 20px 1px 20px;
	width: 70%;
}
</style>
<SCRIPT language="javascript">
	var siprosa = 0;

	function Eliminar(i) {
		document.getElementById("dataTable").deleteRow(i);
	}

	function addRow(tableID) {

		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		
		if(document.getElementById("especialidad").value=="NONE"){
            	return;
        } 

		var cell2 = row.insertCell(0);
		cell2.innerHTML = document.getElementById("especialidad").value
				+ "<input type='hidden' name='especialidadList' value='"
				+ document.getElementById("especialidad").value + "'>";
		var cell3 = row.insertCell(1);
		cell3.innerHTML = document.getElementById('especialidad').options[document
				.getElementById('especialidad').selectedIndex].text;

		var cell4 = row.insertCell(2);
		cell4.innerHTML = "<button type='button' class='btn btn-danger btn-xs' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'><span class='icon icon-remove' title='Eliminar'></span></button>";

	}

	function deleteRow(tableID) {
		try {
			var table = document.getElementById(tableID);
			var rowCount = table.rows.length;

			for (var i = 0; i < rowCount; i++) {
				var row = table.rows[i];
				var chkbox = row.cells[0].childNodes[0];
				if (null != chkbox && true == chkbox.checked) {
					table.deleteRow(i);
					rowCount--;
					i--;
				}

			}
		} catch (e) {
			alert(e);
		}
	}

	function updateSiprosa() {
		if (siprosa == 0) {
			siprosa = 1;
			document.getElementById("habilitacionSiprosa").value = 1;
			document.getElementById("labelDate").style.visibility = 'visible';
			document.getElementById("calendar").style.visibility = 'visible';
		} else {
			siprosa = 0;
			document.getElementById("habilitacionSiprosa").value = 0;
			document.getElementById("labelDate").style.visibility = 'hidden';
			document.getElementById("calendar").style.visibility = 'hidden';
		}
	}
	function updateDate() {
		document.getElementById("fechaVencimientoHabilitacion").value = document.getElementById("registration-date").value;
	}

	function updateDateValidoHasta() {
		document.getElementById("validoHasta").value=document.getElementById("valido-hasta").value;
	}

	function updateDateFechaMatricula() {
		document.getElementById("fechaEmisionMatricula").value=document.getElementById("fecha-matricula").value;
	}

	function updateDateFechaDesde() {
		document.getElementById("vigenciaDesde").value=document.getElementById("fecha-desde").value;
	}

	function updateDateFechaHasta() {
		document.getElementById("vigenciaHasta").value=document.getElementById("fecha-hasta").value;
	}
	
	function isNumberKey(evt){
	    var charCode = (evt.which) ? evt.which : evt.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57))
	        return false;
	    return true;
	}

</SCRIPT>
</head>
<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<jsp:include page="../breadcrumb.jsp"></jsp:include>

<div class="mainContainer"> 	
<div class="panelContainer">
<form:form method="post" action="/nuova/editProfesional" commandName="profesional">
<div class="panel panel-info">
	<div class="panel-heading">
          <div class="panel-title">Editar Datos del Profesional</div>
    </div>     
	<div style="padding-top:30px" class="panel-body" >
	
<div class="container-fluid">
  <div class="row-fluid">
	    <div class="span4">
	      <div><form:hidden path="profesionalId" /></div>
        	<div class="formLabel"><form:label path="apellido">Apellido:</form:label></div>
        	<div class="formInput"><form:input path="apellido" placeholder="Apellido" /></div>
	    </div>
	    <div class="span4">
			<div class="formLabel"><form:label path="nombre">Nombre:</form:label></div>
        	<div class="formInput"><form:input path="nombre" placeholder="Nombre"/></div>
	    </div>
	    <div class="span4">
			<div class="formLabel"><form:label path="telefono">Telefono:</form:label></div>
	        <div class="formInput"><form:input path="telefono" placeholder="Telefono"/></div>
	    </div>
  </div>
    <div class="row-fluid">
	    <div class="span4">
			<div class="formLabel"><form:label path="matricula">Matricula:</form:label></div>
	        <div class="formInput"><form:input path="matricula" placeholder="Matricula" onkeypress='return isNumberKey(event)'/></div>
	    </div>
	    
	    <div class="span4">
	     	<div class="formLabel"><form:label path="tituloProfesional">Titulo Profesional:</form:label></div>
	        <div class="formInput"><form:input path="tituloProfesional" placeholder="Titulo Profesional"/></div>
	    </div>
  </div>

</div>        
        
     </div>   
    
        
</div>   



<div class="panel panel-info">
	<div class="panel-heading">
          <div class="panel-title">Editar Registro Nacional de Prestadores</div>
    </div>     
	<div  class="panel-body">
	
<div class="container-fluid">
  <div class="row-fluid">
	    <div class="span4">
	      <div><form:hidden path="profesionalId" /></div>
        	<div class="formLabel"><form:label path="apellido">Nro Registro:</form:label></div>
        	<div class="formInput"><form:input path="nroRegistro" type="text" onkeypress='return isNumberKey(event)'/></div>
	    </div>
	    <div class="span4">	
			<div style="visibility:hidden;height:0px;"><form:label path="habilitacionSiprosa">Habilitacion del Siprosa:</form:label></div>
			<div style="visibility:hidden;height:0px;"><form:input path="habilitacionSiprosa" /></div>
			<div>
				<div class="formLabel"><form:label path="validoHasta">Valido Hasta:</form:label></div>
				<div style="visibility:hidden;height:0px;"><form:input class="date" path="validoHasta" /></div>
				<div class="formInput">
				<div id="calendar">
					<div class="input-group registration-date-time" style="padding-top:0%;">
						<input class="form-control" name="registration_date" id="valido-hasta" type="date"  onchange="javascript:updateDateValidoHasta();">
	            	</div>
	        	</div>  
	        	</div>
			</div>
	    </div>
	    <div class="span4">
	    </div>
  	</div>
	</div>        
        
    </div>       
</div>  


<div class="panel panel-info">
	<div class="panel-heading">
          <div class="panel-title">Editar Certificado de Cobertura</div>
    </div>     
	<div  class="panel-body">
	
<div class="container-fluid">
  <div class="row-fluid">
	    <div class="span4">
	      <div><form:hidden path="profesionalId" /></div>
        	<div class="formLabel"><form:label path="nroPoliza">Nro. Poliza:</form:label></div>
        	<div class="formInput"><form:input path="nroPoliza" type="text" onkeypress='return isNumberKey(event)'/></div>
	    </div>
	     <div class="span4">	
			
			<div>
				<div class="formLabel"><form:label path="vigenciaDesde">Vigencia Desde:</form:label></div>
				<div style="visibility:hidden;height:0px;"><form:input class="date" path="vigenciaDesde" /></div>
				<div class="formInput">
				<div id="calendar">
					<div class="input-group registration-date-time" style="padding-top:0%;">
						<input class="form-control" name="fecha_desde" id="fecha-desde" type="date"  onblur="javascript:updateDateFechaDesde();">
	            	</div>
	        	</div>  
	        	</div>
			</div>
	    </div>
	     <div class="span4">	
			
			<div>
				<div class="formLabel"><form:label path="vigenciaHasta">Vigencia Hasta:</form:label></div>
				<div style="visibility:hidden;height:0px;"><form:input class="date" path="vigenciaHasta" /></div>
				<div class="formInput">
				<div id="calendar">
					<div class="input-group registration-date-time" style="padding-top:0%;">
						<input class="form-control" name="fecha_hasta" id="fecha-hasta" type="date"  onblur="javascript:updateDateFechaHasta();">
	            	</div>
	        	</div>  
	        	</div>
			</div>
	    </div>
  	</div>
	</div>        
        
    </div>       
</div>  




<div class="panel panel-info">
	<div class="panel-heading">
	          <div class="panel-title">Editar Especialidades</div>
	</div>  
<div style="padding-top:30px" class="panel-body" >

	<div class="row-fluid">
			<div class="span6">
			</div>
			<div class="span6">
				<div style="float:right;padding-right:2%;">
						<INPUT type="button" value="Agregar" onclick="addRow('dataTable')" class="btn btn-info"/>
				</div>
				<div style="float:right;padding-right:2%;">
					
								 	<form:select path="especialidad">
				   <form:option value="NONE" label="Seleccione Especialidad ..."/>
				   <form:options items="${especialidadList}" itemLabel="nombre" itemValue="especialidadId" />			    
				</form:select>
				</div>
			</div>
    
    </div>
    <div class="tableContainer"> 

	    <TABLE id="dataTable" class="table"  style="margin-top:0px;">
	        <TR>
	        <TR>      	
	            <TD>ID</TD>
	            <TD>Especialidad</TD> 
	            <TD></TD>       
	        </TR>       
	        </TR>
	       	<c:forEach items="${especialidadListEdit}" var="esp">
		    <tr>

		        <td>${esp.key} <input type="hidden" name="especialidadList" value="${esp.key}"></td>
		        <td>${esp.value}</td>       
		        <td>
		        <button type='button' class='btn btn-danger btn-xs' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'><span class='icon icon-remove' title='Eliminar'></span></button>
		        </td> 
		    </tr>
	</c:forEach>
	    </TABLE>
	   	
 	</div>
	<div class="row-fluid">
		<div class="span8">
		</div>
		<div class="span4">
		<div style="float:right;"><input type="button" value="Cancelar" onclick="location.href='/nuova/mainProfesional';" class="btn"/></div>
			<div style="float:right;padding-right:2%;"><input type="submit" value="Guardar" class="btn btn-info"/></div> 
	 		
		</div>
	</div>	        

</div>
</div>  

</form:form>
</div>
</div>
</div>


</body>
</html>

<script language="javascript">

	document.getElementById("valido-hasta").value= document.getElementById("validoHasta").value;
	document.getElementById("fecha-desde").value= document.getElementById("vigenciaDesde").value;
	document.getElementById("fecha-hasta").value= document.getElementById("vigenciaHasta").value;
        
     $("#profesional").validate({
    
		        // Specify the validation rules
		        rules: {
		            apellido: "required",
		            nombre: "required",
		            matricula: "required",
		            tituloProfesional: "required",
		            nroRegistro: "required",
		            nroLibro: "required",
		            nroFolio: "required",
		            nroPoliza: "required"
		        },
		        
		        // Specify the validation error messages
		        messages: {
		            apellido: "Ingrese apellido",
		            nombre: "Ingrese nombre",
		        	matricula: "Ingrese Matrícula",
		        	tituloProfesional: "Ingrese Título Profesional",
		        	nroRegistro: "Ingrese Número de Registro",
		        	nroLibro: "Ingrese Número de Libro",
		        	nroFolio: "Ingrese Número de Folio",
		        	nroPoliza: "Ingrese Número de Poliza"
		        },
		                submitHandler: function(form) {
		            form.submit();
		        }
		    });
</script>