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
	           			<a href="#" class="pull-right"><b>+</b>&nbsp;&nbsp;Nuevo Ingreso de Caja</a>
          			</div>
    		</div>     
			<div  class="panel-body" >
				<div class="container-fluid" >
	  				<div class="row-fluid" >
	  				<form:form action="" method="GET" commandName="caja">
						<div class="span4">
				   			<div id="labelDate" class="formLabel"><label>Fecha:</label></div>
							<div style="visibility:hidden;height:0px;"><input id="fecha" class="date"/></div>						
							<div class="formInput" >
							<table>
									<tr>
									<td style="width:80%">		
									
										<div id="calendar">
											<div class="input-group registration-date-time">
												<input class="form-control" name="registration_date" 
												id="registration-date" type="date"  onchange="javascript:updateDate();">		            					
				            				</div>
				            			</div>	            	
									</td>
									<td>			
									<input type="button" value="Buscar" class="btn btn-info" style="margin-bottom:8px"/>
									</td>
									</tr>
								</table>
								
										
		            			
	            			</div>
	            				
			   			</div>
			   			</form:form>
			   			<span class="pull-right">TOTAL $ </span>
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
						       	</div>
						    </tr>
						</c:forEach>
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