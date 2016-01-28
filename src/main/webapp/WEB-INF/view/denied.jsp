<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Nuova</title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/img/favicon/favicon.ico">
</head>	
	<body>
	
		<h1 id="banner">Acceso no autorizado !!</h1>
	
		<hr />
	
		<c:if test="${not empty error}">
			<div style="color:red">
				Los datos ingresados son invalidos, intente de nuevo !!<br /> 
				Causa : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
		</c:if>
	
		<p class="message">Acceso denegado !</p>
		<a href="/nuova/login">Ir a la pagina de login</a> 
	</body>
</html>