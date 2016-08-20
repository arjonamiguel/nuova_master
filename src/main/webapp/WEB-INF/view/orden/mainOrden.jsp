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

<style type="text/css">
.custom-height-modal {
  height: 400px;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
												
						var rows = [];

						rows = callPaciente();

						$("#ordenGrid").simplePagingGrid(
								{
									columnNames : [ "NRO.ORDEN","FECHA","SOLICITANTE","ESPECIALIDAD","PRACTICAS" ,"" ],
									columnKeys : [ "nroOrden", "fecha", "apellidoNombreProfesional"
													, "especialidadView", "practicas","acciones"],
									columnWidths : [ "10%","8%","15%", "10%" ],
									sortable : [ true, false, true,true,true ],
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
												$("#ordenGrid")
														.simplePagingGrid(
																"refresh");
												// rows.push(callSearchPaciente(search));
												// rows = rows[0];
												var arr = callSearchPaciente(search);
												$.each(arr, function(index,
														value) {
													rows.splice(0, 0, value);

												});
												$("#ordenGrid")
														.simplePagingGrid(
																"refresh");

											}
										});
						
					});
</script>
<!-- 	Fin Configuracion del paginador -->

<script>

function editColumnEstado() {
	var text="";
    //run through each row
    $('.table tr').each(function (i, row) {
    	if(i>0){
    		    // reference all the stuff you need first
		        var $row = $(row);
		        $row.find('.label');
		        text=$row.find('.label')[0].innerHTML;
		        if(text=='RECHAZADA'){
		        	$row.find('.label')[0].className="label-important";
		        }else if(text=='INCOMPLETA'){
		        	$row.find('.label')[0].className="label-warning";
		        }else if(text=='PENDIENTE'){
		        	$row.find('.label')[0].className="label-warning";
		        }else if(text=='AUTORIZADA'){
		        	$row.find('.label')[0].className="label-success";
		  		}     
    	}
    });
}

function editColumnsChecked() {
	var text="";
	
    //run through each row
    $('.table tr').each(function (i, row) {
    	if(i>0){
    		    // reference all the stuff you need first
		        var $row = $(row);
				var group= $row.find('.icon-ok-sign')
				for(var j=0;j<group.length;j++){
					if(group[j].innerHTML==0){
						group[j].className="icon-remove-sign";
					}
					group[j].innerHTML="";
				}
    	}

    });
}

					$(function() {
			    		$('.btn-info').tooltip({
			        	placement: 'bottom',
			        	title: 'Editar Orden de Práctica'
			        	});
					});
					$(function() {
			    		$('.btn-default').tooltip({
			        	placement: 'bottom',
			        	title: 'Imprimir Orden de Práctica'
			        	});
					});
					$(function() {
			    		$('.btn-danger').tooltip({
			        	placement: 'bottom',
			        	title: 'Eliminar Orden de Práctica'
			        	});
					});
					
function showReport(id){
	$("#myModal").css("visibility", "visible");
	var iframe = "<iframe src='/nuova/reporteOrdenEmitida/"+id+"' width='100%' height='150%' >";
	document.getElementById("iframeReport").innerHTML = iframe;
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
	          			<b>Administraci&oacute;n Orden de Pr&aacute;cticas</b>
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
										    	placeholder="USTED PUEDE FILTRAR POR EL NRO. DE ORDEN ..."										    	
										   	/>
										   	<span id = "wait" style="visibility: hidden; padding-left: 5px">Buscando ...</span>			
											</div>
											<div class="row-fluid">
												<div id="ordenGrid"></div>
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


<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog" style="visibility: hidden;">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Comprobante de Orden</h4>
      </div>
      <div class="modal-body custom-height-modal">
        <div id="iframeReport" style="height: 800px">	</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div>

  </div>
</div>
<!-- Fin Modal -->

</html>
<script>
document.getElementById("mainOrden").parentNode.classList.add("active");
editColumnEstado();
editColumnsChecked();

function callPaciente() {
	var retorno;
	$.ajax({
		url : "ajaxGetOrdenesPaginados/102",
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
		url : "ajaxGetSearchOrdenesPaginados/102/?search=" + search,
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


$("#cerrarReportOrden").click()


</script>
