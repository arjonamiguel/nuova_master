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
	
<script type="text/javascript">

function callMovimientosCaja(tipo) {
	var retorno;
	$.ajax({
		url : "ajaxGetMovimientoCaja?tipoMovimiento="+tipo,
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

function getConceptos(tipo) {
	var conceptos = callMovimientosCaja(tipo.value);
	$('#concepto')
	.empty()
    .append('<option selected="selected" value="-1">Seleccione Concepto ...</option>')
;
	$.each(conceptos, function(key, value) {   
	     $('#concepto')
	          .append($('<option>', { value : value.id })
	          .text(value.value)); 
	});
}

function updateDate(){
	document.getElementById("fechaMovimiento").value=document.getElementById("fecha_Movimiento").value;
}

</script>

</head>
<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<jsp:include page="../breadcrumb.jsp"></jsp:include>
<div class="mainContainer"> 	
	<div class="panelContainer">
	<form:form method="post" action="/nuova/updateCaja" commandName="caja">
			
		<div class="panel panel-info">
			<div class="panel-heading">
          			<div class="panel-title">
	          			<b>Nuevo Movimiento de Caja</b>
          			</div>
    		</div>
    		
			<div  class="panel-body" >
				<div class="container-fluid" >
				  	<div class="row-fluid">
				  		<div class="span4">
			 				<div class="formLabel"><form:label path="tipoMovimiento">Movimiento:</form:label></div>
			 				<div class="formInput">	
				 				<form:select path="tipoMovimiento" style="width:78%; margin-bottom:0px" onchange="getConceptos(this);">
									<form:option value="-1" label="Seleccione Movimiento ..."/>
									<form:option value="1" label="Ingresos"/>
									<form:option value="2" label="Egresos"/>
								</form:select>
							</div>
		 				</div>
		 				
		 				<div class="span4">
			 				<div class="formLabel"><form:label path="concepto">Concepto:</form:label></div>
			 				<div class="formInput">	
				 				<form:select path="concepto" style="width:78%; margin-bottom:0px">
									<form:option value="-1" label="Seleccione Concepto ..."/>
		
								</form:select>
							</div>
		 				</div>
		 			
				  	<div class="span4">	
							<div class="formLabel"><form:label path="fechaMovimiento">Fecha:</form:label></div>
							<div style="visibility:hidden;height:0px;"><form:input class="date" path="fechaMovimiento" /></div>
							<div class="formInput">
								<div id="calendar">
									<div class="input-group registration-date-time" style="padding-top:0%;">
										<input class="form-control" name="fecha_Movimiento" id="fecha_Movimiento" type="date"  onchange="javascript:updateDate();" />
					            	</div>
					        	</div>  
					        </div>
				    </div>			
	  				</div>
	  				
	  				<div class="row-fluid">
	  				<div class="span4">
		 				<div class="formLabel"><form:label path="monto">Monto $:</form:label></div>
		 				<div class="formInput">
		 					<form:input path="monto" cssStyle="width:30%" />
		 					<spam style="color: #31708f">Ej: 1200.00</spam>
		 				</div>
		 			</div>
		 			<div class="span4">
		 			</div>
		 			<div class="span4">
		 			</div>
		 			</div>	 			
		
		 			
	  			</div>  			
	  	
	  		</div>	  		
	  		
	  	</div>
	  	
	  			<div class="panel panel-info">
							<div class="panel-body">
								<div class="row-fluid">
									<div class="span12">
									<div style="float:right;"><input type="button" value="Cancelar" onclick="location.href='/nuova/mainCaja/null';" class="btn"/></div>
										<div style="float:right;padding-right:2%;"><input type="submit" value="Guardar" class="btn btn-info"/></div> 
							 			
									</div>
								</div>
							</div>
				</div>	
	 </form:form> 	
	 </div>			
</div>
</body>
</html>
<script>
document.getElementById("caja").parentNode.classList.add("active");

</script>