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
<div class="mainContainer"> 	
	<div class="panelContainer">			
		<div class="panel panel-info">
			<div class="panel-heading">
          			<div class="panel-title">
	          			Informacion del Paciente
          			</div>
    		</div>
    		
			<div  class="panel-body" >
				<div class="container-fluid" >
				<table class="table">
				<tr>
					<td>s</td>
					<td>s</td>
					<td>s</td>
				</tr>
				</table>			
	  	
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
</div>
</div>
</body>
</html>
<script>
document.getElementById("caja").parentNode.classList.add("active");

</script>