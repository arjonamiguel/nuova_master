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
		
		
<!-- 	Configuracion del paginador -->
<link href="<%=request.getContextPath()%>/resources/simplepaginggrid/css/simplePagingGrid-0.4.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/simplepaginggrid/examples/pageNumbers/script/handlebars-1.0.rc.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/simplepaginggrid/script/simplePagingGrid-0.5.0.2.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
												
						var rows = [];

						rows = callPaciente();

						$("#profesionalGrid").simplePagingGrid(
								{
									columnNames : [ "ID", "APELLIDO", "NOMBRE",
											"TELEFONO", "MATRIULA", "REGISTRO",
											 "TITULO", "VENCIMIENTO", ""],
									columnKeys : [ "profesionalId", "nombre",
											"apellido", "telefono", "matricula",
											"registroNacional", "tituloProfesional", 
											"fechaVencimientoHabilitacion", "acciones" ],
									columnWidths : [ "5%", "20%", "20%", "11%", "11%" ],
									sortable : [ false, true, true, true ],
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
												$("#profesionalGrid")
														.simplePagingGrid(
																"refresh");
												// rows.push(callSearchPaciente(search));
												// rows = rows[0];
												var arr = callSearchPaciente(search);
												$.each(arr, function(index,
														value) {
													rows.splice(0, 0, value);

												});
												$("#profesionalGrid")
														.simplePagingGrid(
																"refresh");

											}
										});
						
					});
</script>
<!-- 	Fin Configuracion del paginador -->
</head>
<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<jsp:include page="../breadcrumb.jsp"></jsp:include>
<div class="mainContainer">
		
<div class="panelContainer">		
		<div class="panel panel-info">
			<div class="panel-heading">
          			<div class="panel-title">
	          			Administración de Profesionales
	           			<a href="formAddProfesional" class="pull-right"><b>+</b>&nbsp;&nbsp;Nuevo Profesional</a>
          			</div>
    		</div>     
			<div  class="panel-body" >
				<div class="container-fluid" >
	  				<div class="row-fluid" >
		    				<div class="span12">
		    						<section id="main">        
										<div class="container-fluid">	
											<div>		
										    <input type="text" 
										    	style="width: 50%"
										    	name="search" 
										    	id="search"			    	 
										    	class="form-control input-lg"  
										    	placeholder="USTED PUEDE FILTRAR POR APELLIDO O NOMBRE ..."										    	
										   	/>
										   	<span id = "wait" style="visibility: hidden; padding-left: 5px">Buscando ...</span>			
											</div>
											<div class="row-fluid">
												<div id="profesionalGrid"></div>
											</div>
										</div>
							        </section>								
		    				</div>
		    		</div>
		    	</div>
	    	</div>
	    </div>
</div>
	
</div>

</body>
</html>
<script>
document.getElementById("configuracion").parentNode.classList.add("active");

	function callPaciente() {
		var retorno;
		$.ajax({
			url : "ajaxGetProfesionalesPaginados",
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

	function callSearchPaciente(search) {
		var retorno;
		$.ajax({
			url : "ajaxGetSearchProfesionalesPaginados?search=" + search,
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