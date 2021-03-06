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
	label.error {
		  color: #a94442;
		  background-color: #f2dede;
		  border-color: #ebccd1;
		  padding:1px 20px 1px 20px;
		  width:24%;
		}
	</style>
	<script type="text/javascript">
	function addRow(tableID) {
		 
        var table = document.getElementById(tableID);

        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        if(document.getElementById("especialidad").value=="NONE"){
        	return;
        }  

        var cell2 = row.insertCell(0);      
          
        cell2.innerHTML = document.getElementById("especialidad").value+"<input type='hidden' name='especialidadList' value='"+document.getElementById("especialidad").value+"'>";
        var cell3 = row.insertCell(1);
        cell3.innerHTML = document.getElementById('especialidad').options[document.getElementById('especialidad').selectedIndex].text;
        
       	var cell4 = row.insertCell(2);
        cell4.innerHTML=  "<button type='button' class='btn btn-danger btn-xs' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'><span class='icon icon-remove' title='Eliminar'></span></button>";

    }

	function Eliminar (i) {    	 
	    document.getElementById("dataTable").deleteRow(i);
	}
	
	$(function(){
		
			$(document.body).on('click', '.changeType' ,function(){
				$(this).closest('.phone-input').find('.type-input').val($(this).data('type-value'));
			});
			
			$(document.body).on('click', '.btn-remove-phone' ,function(){
				$(this).closest('.phone-input').remove();
			});
			
			
			$('.btn-add-phone').click(function(){

				var index = $('.phone-input').length + 1;
				
				$('.phone-list').append(''+
						'<div class="input-group phone-input">'+
							'<input type="text" name="phone['+index+'][number]" class="form-control" placeholder="+1 (999) 999 9999 int: 123" style="width:20.5%;margin-bottom:0px;"/>'+
							'<input type="hidden" name="phone['+index+'][type]" class="type-input" value="" />'+
							'<span class="input-group-btn" style="padding-left:1%;">'+
								'<button class="btn btn-danger btn-remove-phone" type="button"><span class="icon icon-remove"></span></button>'+
							'</span>'+
						'</div>'
				);

			});
			
		});
	
function procesarSubmit()
	{
		var jsonPhones='';
		var line='';
		$( '.form-control' ).each(function( index ) {
			var trimStr=$( this ).val();
			trimStr=trimStr.replace(/\s/g, "");
  			line= '{"id":"'+index + '", "value":"' + trimStr+'"},';
  			jsonPhones=jsonPhones+line;
		});
		var str=jsonPhones;
		var newStr = str.substring(0, str.length-1);
		jsonPhones=newStr;
		jsonPhones='['+jsonPhones+']';
		$('#telefono').val(jsonPhones);
		$( '#prestador' ).submit();
	}
	
	
	</script>
</head>
<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>

<div class="mainContainer"> 
<div class="panelContainer">
	<form:form method="post" action="addPrestador" commandName="prestador">
	<div class="panel panel-info">
			<div class="panel-heading">
				<div class="panel-title">Nuevo Prestador</div>
			</div>
			<div class="panel-body" >
				<div class="container-fluid">
						<div class="row-fluid">
							<div class="span6">
								<div class="formLabel"><form:label path="nombre">Nombre:</form:label></div>
        						<div class="formInput"><form:input path="nombre" class="input-block-level" type="text"/></div>
							</div>
							<div class="span6">
								<div class="formLabel"><form:label path="provincia">Provincia:</form:label></div>
								<div class="formInput">
								<form:select path="provincia">
								   <form:option value="NONE" label="Seleccione Provincia ..."/>
								   <form:options items="${provinciasList}" />			    
								</form:select>
								</div>
							</div>
							
						</div>
						
						<div class="row-fluid">		
							<div class="span6">
								<div class="formLabel"><form:label path="domicilio">Domicilio:</form:label></div>
        						<div class="formInput"><form:textarea path="domicilio" class="input-block-level" type="text"/></div>
							</div>				
							<div class="span6" style="visibility:hidden;">
								<div class="formLabel"><form:label path="telefono">Telefono:</form:label></div>
        						<div class="formInput"><form:textarea path="telefono" class="input-block-level" type="text" cssStyle="width:53%" placeholder="Enter Choice#1 &#10;Enter Choice#2 &#10;Enter Choice#3"/></div>
							</div>
							
							
						</div>
						
				</div>
			</div>
	</div>


	<div class="panel panel-info">
		<div class="panel-heading">
		          <div class="panel-title">Agregar Telefonos</div>
		</div>  
		<div style="padding-top:30px" class="panel-body" >	
			<div class="row-fluid">		
	    		<div class="span12">
		    		<div style="float:right;padding-right:1%;">
		    			<button type="button" class="btn btn-success btn-sm btn-add-phone"><span class="icon icon-plus"></span> Agregar Telefono</button>
		    		</div>	
	    		</div>
    		</div>
    		<div class="phone-list" style="padding-left:14.5%;">
				<div class="input-group phone-input"></div>
			</div>	
		</div>
	</div>
	
	<div class="panel panel-info">
	<div class="panel-heading">
	          <div class="panel-title">Agregar Especialidades</div>
	</div>  
<div style="padding-top:30px" class="panel-body" >
   	
	<div class="row-fluid">		
    		<div class="span12">
	    		<div style="float:right;padding-right:1%;">
	    			<INPUT type="button" value="Agregar" onclick="addRow('dataTable')" class="btn btn-info"/>
	    		</div>
	    		<div style="float:right;">
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
	            <TD>ID</TD>
	            <TD>Especialidad</TD> 
	            <TD></TD>       
	        </TR>
	    </TABLE>
	   	
 	</div>

</div>
</div>   
	
		<!-- Botoneras -->
		<div class="panel panel-info">
			<div class="panel-body">
				<div class="row-fluid">
				<div class="span12">					
					<div style="float:right;">
						<input class="btn" type="button" value="Cancelar" onclick="location.href='/nuova/mainPrestador';"/>	
					</div>
					<div style="float:right;padding-right:2%;">
						<input type="button" value="Guardar" class="btn btn-info" onclick="procesarSubmit()"/>
					</div>								 			
				</div>
				</div>
			</div>
		</div>
		<!-- Fin Botoneras -->
	</form:form>
</div>
</div>
</body>
</html>
<script>
document.getElementById("configuracion").parentNode.classList.add("active");
			$("#prestador").validate({
    
		        // Specify the validation rules
		        rules: {
		            nombre: "required",
		            provincia: "required",
		            domicilio: "required",
		            telefono: {
		                required: true,
		                minlength: 5
		            }

		        },
		        
		        // Specify the validation error messages
		        messages: {
		            nombre: "Ingrese nombre",
		            telefono: {
		                required: "Ingrese telefono",
		                minlength: "Telefono debe tener al menos 5 caracteres de largo"
		            },
		            provincia: "Ingrese Provincia",
		            domicilio: "Ingrese domicilio"

		        },
		                submitHandler: function(form) {
		            form.submit();
		        }
		    });
</script>
