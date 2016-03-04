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

</script>

</head>
<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<jsp:include page="../breadcrumb.jsp"></jsp:include>
<div class="mainContainer"> 	
	<div class="panelContainer">
	<form:form method="post" action="/nuova/addProfesional" commandName="caja">
			
		<div class="panel panel-info">
			<div class="panel-heading">
          			<div class="panel-title">
	          			Administracion de Caja
          			</div>
    		</div>
    		
			<div  class="panel-body" >
				<div class="container-fluid" >
				  	<div class="row-fluid">
				  	<div class="span4">	
						<div>
							<div class="formLabel"><form:label path="fechaMovimiento">Fecha:</form:label></div>
							<div style="visibility:hidden;height:0px;"><form:input class="date" path="fechaMovimiento" /></div>
							<div class="formInput">
								<div id="calendar">
									<div class="input-group registration-date-time" style="padding-top:0%;">
										<input class="form-control" name="fecha_Movimiento" id="fecha_Movimiento" type="date"  onchange="javascript:updateDateValidoHasta();">
					            	</div>
					        	</div>  
					        </div>
						</div>
				    </div>
				
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