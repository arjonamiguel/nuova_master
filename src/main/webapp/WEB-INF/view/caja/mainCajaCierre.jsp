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
	
		<!-- 	Configuracion del paginador -->
<link href="<%=request.getContextPath()%>/resources/simplepaginggrid/css/simplePagingGrid-0.4.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/simplepaginggrid/examples/pageNumbers/script/handlebars-1.0.rc.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/simplepaginggrid/script/simplePagingGrid-0.5.0.2.js"></script>
	<style>
	.label-error {
		  color: #a94442;
		  background-color: #f2dede;
		  border-color: #ebccd1;
		  padding:1px 20px 1px 20px;
		  width:22%;
		}
	</style>
	
	
<script>

	$(document)
			.ready(
					function() {

						var rows = [];

						rows = callCierreCaja();

						$("#cierreCajaGrid").simplePagingGrid(
								{
									columnNames : [ "ID", "FECHA", "MONTO", "" ],
									columnKeys : [ "cajaCierreId", "fechaCierre", "montoView",""],
									columnWidths : [ "5%", "20%", "10%", "70%"],
									sortable : [ false, true ],									
									data : rows
								});

						$('input#search')
								.bind(
										'keypress',
										function(event) {
											var keycode = (event.keyCode ? event.keyCode
													: event.which);
											if (keycode == '13') {
												var search = document
														.getElementById("search").value;
												// rows = callSearchPaciente(search);
												rows.length = [];
												$("#cierreCajaGrid")
														.simplePagingGrid(
																"refresh");
												// rows.push(callSearchPaciente(search));
												// rows = rows[0];
												var arr = callSearchPaciente(search);
												$.each(arr, function(index,
														value) {
													rows.splice(0, 0, value);

												});
												$("#cierreCajaGrid")
														.simplePagingGrid(
																"refresh");

											}
										});

					});

	function hideMessage() {
		$("#message").css("visibility", "hidden");
	}
</script>

</head>
<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<jsp:include page="../breadcrumb.jsp"></jsp:include>
<div class="mainContainer"> 	
	<div class="panelContainer">		
		<div class="panel panel-info">
			<div class="panel-heading">
          			<div class="panel-title">
	          			<b>Administracion de Cierres de Cajas</b>
          			</div>
    		</div>     
			<div  class="panel-body" >
				<div class="container-fluid" >
	  				<div class="row-fluid" >
						<div class="span4">
				   			<div id="labelDate" class="formLabel"><label>Fecha:</label></div>
							<div style="visibility:hidden;height:0px;"><input id="fecha" class="date"/></div>						
							<div class="formInput" >
							<table>
									<tr>
									<td style="width:80%">		
									
										<div id="calendar">
											<div class="input-group registration-date-time">
												<input class="form-control" name="search" 
												id="search" type="date" onchange="hideMessage()"/>											
				            				</div>
				            			</div>	            	
									</td>
									<td>			
									<input type="button" value="Buscar" class="btn btn-info" style="margin-bottom:8px" 
									onclick="javascript:submitSearch()" />
									
									</td>
									</tr>
								</table>
								
										
		            			
	            			</div>
	            				
			   			</div>		
			   			<div class="label-error" id="message" style="float:left;margin-left:8%;visibility:hidden;">Debe ingresar una fecha valida de busqueda</div>
		    		</div>
		    		
		    		<div class="row-fluid" >
		    			<section id="main">
						<div id="cierreCajaGrid"></div>
						</section>
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
function callCierreCaja() {
	var retorno;
	$.ajax({
		url : "/nuova/ajaxGetCajaCierrePaginados",
		type : "GET",
		contentType : "application/json; charset=utf-8",
		//    data: jsonString, //Stringified Json Object
		async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
		cache : false, //This will force requested pages not to be cached by the browser          
		processData : false, //To avoid making query String instead of JSON
		success : function(page) {
			// Success Message Handler
			retorno = page.content;
		}
	});

	return retorno;
}

function callSearchCierreCaja(search) {
	var retorno;
	$.ajax({
		
		url : "/nuova/ajaxGetSearshCajaCierrePaginados?search=" + search,
		type : "GET",
		contentType : "application/json; charset=utf-8",
		//    data: jsonString, //Stringified Json Object
		async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
		cache : false, //This will force requested pages not to be cached by the browser          
		processData : false, //To avoid making query String instead of JSON
		 beforeSend: loadStart,
		 complete: loadStop,		
		success : function(pagesearch) {
			retorno = pagesearch.content;
		}
	});

	return retorno;
}
function loadStart() {
	$('#wait').css("visibility","visible");
}
function loadStop() {
	$('#wait').css("visibility","hidden");
}
</script>