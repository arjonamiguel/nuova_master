<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" session="true"
	contentType="text/html;charset=latin1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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

<!-- 	Configuracion del paginador -->
<link
	href="<%=request.getContextPath()%>/resources/simplepaginggrid/css/simplePagingGrid-0.4.css"
	rel="stylesheet">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/simplepaginggrid/examples/pageNumbers/script/handlebars-1.0.rc.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/simplepaginggrid/script/simplePagingGrid-0.5.0.2.js"></script>

</head>
<body style="background-color: #e5e5e5;">
	<jsp:include page="../sec_menu.jsp"></jsp:include>

	<div class="mainContainer">
		<div class="panelContainer">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">
						<b>Monitor de Reportes</b>

					</div>
				</div>
				<div class="panel-body">
					<div class="container-fluid">
						<div class="row-fluid">
							<!-- Reporte de Pacientes -->
							<div class="span4">
								<div class="mainContainer">
									<div class="panelContainer">
										<div class="panel panel-info">
											<div class="panel-heading">
												<div class="panel-title">Paciente Afiliado de O.S.P.S.I.P</div>
											</div>
											<div class="panel-body">
											<ul>
												<li><a href="#" onclick="">Cantidad de Afiliados Atendidos.</a> </li>
												<li><a href="#" onclick="">Total de Pacientes Registrados.</a></li>
												<li><a href="#" onclick="">Total de Pacientes Sin Coseguro.</a></li>												
												<li><a href="#" onclick="">Pacientes Fuera de covertura (Mayores de 21 años).</a></li>
												<li><a href="#" onclick="">Total de Pacientes Filtrados por Nomenclador.</a></li>
												
											</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<!-- Reporte de Ordenes -->
							<div class="span4">
								<div class="mainContainer">
									<div class="panelContainer">
										<div class="panel panel-info">
											<div class="panel-heading">
												<div class="panel-title">ORDENES (Consultas y Practicas)</div>
											</div>
											<div class="panel-body">
											<ul>
												<li><a href="#" onclick="">Listado de Ordenes.</a></li>
												<li><a href="#" onclick="">Listado de Practicas.</a></li>
												
											</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						
							<!-- Reporte de Caja -->
							<div class="span4">
								<div class="mainContainer">
									<div class="panelContainer">
										<div class="panel panel-info">
											<div class="panel-heading">
												<div class="panel-title">Movimiento de Caja</div>
											</div>
											<div class="panel-body">
											<ul>
												<li><a href="#" onclick="">Listado de Caja.</a></li>
												<li><a href="#" onclick="">Listado de Cierre de Caja.</a></li>
												
											</ul>
											</div>
										</div>
									</div>
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

<script type="text/javascript">
	document.getElementById("mainReporte").parentNode.classList.add("active");
</script>