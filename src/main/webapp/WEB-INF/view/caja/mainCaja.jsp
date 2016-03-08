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
	
<script>
function submitSearch() {
	fechaCaja = document.getElementById("search").value;
	location.href="/nuova/mainCaja/"+fechaCaja;
}

function submitCierreCaja() {
	fechaCierre = document.getElementById("fechaCierre").value;
	location.href="/nuova/formCierreCaja?fecha="+fechaCierre;
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
	          			Administracion de Caja
	           			<a href="/nuova/formUpdateCaja" class="pull-right"><b>+</b>&nbsp;&nbsp;Nuevo Movimiento de Caja</a>
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
												id="search" type="date" />											
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
			   			<div class="span4" style="float: right;text-align: right;">
			   			<input type="button" value="${fechaBoton}" class="btn btn-success" style="margin-bottom:8px" 
									onclick="submitCierreCaja()" />
						<input type="hidden" name="fechaCierre" id="fechaCierre" value="${fecha}" />			
			   			</div>
		    		</div>
		    		
		    		<div class="row-fluid" >
						<div class="span12">
						<table class="table table-striped custab">
						<tr>
						    <th>FECHA</th>	    
						    <th>CONCEPTO</th>
						    <th style="text-align: right;">INGRESO</th>
						    <th style="text-align: right;">EGRESO</th>

						</tr>
						<c:forEach items="${cajaList}" var="c">
						    <tr>
						        <td>${c.fecha}</td>        
						        <td>${c.conceptoDesc}</td>
						        <td style="text-align: right;">${c.ingreso}</td>
						        <td style="text-align: right;">${c.egreso}</td>
						    </tr>
						</c:forEach>
							<tr>
						        <td colspan="4" style="text-align: right; background: #d9edf7;"></td>						      
						    </tr>
						  	<tr>
						        <td colspan="3" style="text-align: right;"><b> TOTAL $</b></td>        
						        <td style="color: #31708f;background: #d9edf7; text-align: center">
						        <b>${total}</b>
						        <input type="hidden" name="totalCierre" id="totalCierre" value="${total}" />
						        </td>
						    </tr>
						</table>
						
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
document.getElementById("caja").parentNode.classList.add("active");
</script>