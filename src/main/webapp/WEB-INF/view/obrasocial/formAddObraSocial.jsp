<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Nuova</title>
    	<link href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
	<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
	<style>
	.custab{
    border: 1px solid #ccc;
    padding: 5px;
    margin: 5% 0;
    box-shadow: 3px 3px 2px #ccc;
    transition: 0.5s;
    }
.custab:hover{
    box-shadow: 3px 3px 0px transparent;
    transition: 0.5s;
    }
.table{
	width: 30%;
	}
.row{
	margin-left: 10%;
}
	</style>
</head>
<body>
<jsp:include page="../sec_menu.jsp"></jsp:include>

<h3>Nueva Obra Social
<form:form method="post" action="addObraSocial" commandName="obrasocial">
 	<div class="row col-md-6 col-md-offset-2 custyle">
    <table class="table table-striped custab">
    <tr>
        <td><form:label path="nombre">Obra Social:</form:label></td>
        <td><form:input path="nombre" /></td>
    </tr>
   
    <tr>
        <td>
            <input class="btn btn-lg btn-primary btn-block btn-signin" type="submit" value="Guardar"/>
        </td>
        <td>
            <input type="button" value="Cancelar" onclick="location.href='mainObraSocial';"/>
        </td>
    </tr>
	</table>
	</div> 
</form:form>
 

</body>
</html>