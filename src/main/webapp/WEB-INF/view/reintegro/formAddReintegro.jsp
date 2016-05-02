<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Nuova</title>
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/resources/img/favicon/favicon.ico">

<link
	href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap.min.css"
	rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery/bootstrap-collapse.js" /></script>
<link href="<%=request.getContextPath()%>/resources/css/nuova.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/resources/css/panel.css"
	rel="stylesheet" />
<link
	href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap-responsive.css"
	rel="stylesheet" />
<script
	src="<c:url value="/resources/js/jquery/jquery.validate.min.js" />"></script>
<link
	href="<%=request.getContextPath()%>/resources/montrezorro-bootstrap-checkbox-fa865ff/css/bootstrap-checkbox.css"
	rel="stylesheet" />
<script
	src="<%=request.getContextPath()%>/resources/montrezorro-bootstrap-checkbox-fa865ff/js/bootstrap-checkbox.js" /></script>

<style>
label.error {
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
	padding: 1px 20px 1px 20px;
	width: 58%;
}
</style>
<script type="text/javascript">
function updateFechaInicio(){
	document.getElementById("fechaDesde").value=document.getElementById("fecha_inicio").value;
}

function updateFechaReintegro(){
	document.getElementById("fechaReintegro").value=document.getElementById("fecha_reintegro").value;
}
</script>
</head>
<body style="background-color: #e5e5e5;">
	<jsp:include page="../sec_menu.jsp"></jsp:include>

	<div class="mainContainer">
		<div class="panelContainer">
			<form:form method="post" action="/nuova/addReintegro"
				commandName="reintegro">
				<form:hidden path="pacienteId"/>
				<div class="panel panel-info">
					<div class="panel-heading">
						<div class="panel-title">Nuevo Reintegro</div>
					</div>
					<div class="panel-body">
						<div class="container-fluid">
							<div class="row-fluid">
								<div class="span3">									
									<div class="formLabel"><form:label path="fechaDesde">Inicio:</form:label></div>
									<div style="visibility:hidden;height:0px;"><form:input class="date" path="fechaDesde" /></div>
									<div class="formInput">
										<div id="calendar">
											<div class="input-group registration-date-time" style="padding-top:0%;">
												<input class="form-control" name="fecha_inicio" id="fecha_inicio" type="date"  onchange="javascript:updateFechaInicio();" />
							            	</div>
							        	</div>  
							        </div>
								</div>
								<div class="span3">
									<div class="formLabel"><form:label path="fechaReintegro">Reintegro:</form:label></div>
									<div style="visibility:hidden;height:0px;"><form:input class="date" path="fechaReintegro" /></div>
									<div class="formInput">
										<div id="calendar">
											<div class="input-group registration-date-time" style="padding-top:0%;">
												<input class="form-control" name="fecha_reintegro" id="fecha_reintegro" type="date"  onchange="javascript:updateFechaReintegro();" />
							            	</div>
							        	</div>  
							        </div>
								</div>
								<div class="span4">
									<div class="formLabel">
										<form:label path="profesional">Profesional:</form:label>
									</div>
									<div class="formInput">
										<form:input path="profesional" type="text" cssStyle="width:150%"/>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span3">
									<div class="formLabel">
										<form:label path="monto">Monto $:</form:label>
									</div>
									<div class="formInput">
										<form:input path="monto" type="text" />
									</div>
								</div>
								<div class="span3">
								</div>
								<div class="span4">
									<div class="formLabel">
										<form:label path="observaciones">Observaciones:</form:label>
									</div>
									<div class="formInput">
										<form:input path="observaciones" type="text" cssStyle="width:150%"/>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>


				<div class="panel panel-info">
					<div class="panel-body">
						<div class="row-fluid">
							<div class="span12">
								<div style="float: right;">
									<input type="button" value="Cancelar"
										onclick="location.href='/nuova/mainPaciente';" class="btn" />
								</div>
								<div style="float: right; padding-right: 2%;">
									<input type="submit" value="Guardar" class="btn btn-info" />
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
document.getElementById("mainPaciente").parentNode.classList.add("active");
</script>