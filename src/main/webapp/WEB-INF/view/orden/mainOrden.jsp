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
	          			Administracion de Practicas
	           			<a href="formAddOrden" class="pull-right"><b>+</b>&nbsp;&nbsp;Nueva Practica</a>
          			</div>
    		</div>     
			<div  class="panel-body" >
				<div class="container-fluid" >
	  				<div class="row-fluid" >
		    				<div class="span12">
		    					<div class="tableContainer">
									<div class="addButton">
									
									</div>	
									<c:if  test="${!empty ordenList}">
									<table class="table">
									<tr>
									    <th class="tableHeader"">Paciente</th>
									    <th class="tableHeader"">Nro Orden</th>
									    <th class="tableHeader"">Fecha</th>
									    <th class="tableHeader"">Orden Medica</th>
									    <th class="tableHeader"">Credencial</th>
									    <th class="tableHeader"">Monotributista</th>
									    <th class="tableHeader"">Recibo Sueldo</th>
									    <th class="tableHeader"">Estado</th>
									    <th class="tableHeader""></th>
									    <th class="tableHeader"" class="text-center"></th>
									</tr>
									<c:forEach items="${ordenList}" var="o">
									    <tr>
									        <td> 
										        <a class="btn btn-success btn-xs" href="formViewPaciente/${o.paciente.pacienteId}">
										        <span class="icon icon-user"></span>Ver...</a>
									        </td>
									        <td>${o.ordenId}</td>
									        <td>${o.fecha}</td>
									        <td><span class="icon-ok-sign">${o.reqOrdenMedico}</span></td>
									   	    <td><span class="icon-ok-sign">${o.reqCredecial}</span></td>
									        <td><span class="icon-ok-sign">${o.reqMonotributista}</span></td>
									        <td><span class="icon-ok-sign">${o.reqReciboSueldo}</span></td>
									        <td><span class="label" style="color:white;">${o.estado}</span></td>
									        <td class="text-center">
									        	<div style="float:right;">
									        	<a class="btn btn-info btn-xs" href="formEditOrden/${o.ordenId}"><span class="icon icon-edit"></span>editar</a>
									        	<a class="btn btn-danger btn-xs" href="formDeleteOrden/${o.ordenId}"><span class="icon icon-remove"></span>eliminar</a>
									        	</div>
									       	</td>
									    </tr>
									</c:forEach>
									</table>
									</c:if>
								</div>
	
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
document.getElementById("mainOrden").parentNode.classList.add("active");
editColumnEstado();
editColumnsChecked();
</script>
