<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Nuova</title>
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
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
<form:form method="post" action="/nuova/addAdherente" commandName="paciente">
<div class="row col-md-6 col-md-offset-2 custyle">
<table class="table table-striped custab">
	<tr>
	<td colspan="6"><h4>Nueva Practica</h4></td>
	</tr>
	
	<tr>
        	<td colspan="6">
           	<div>
				<div class="inputProf">
				<form:select path="pacienteSelected" style="width:70%; margin-bottom:0px">
					   <form:option value="NONE" label="Seleccione Paciente ..."/>
					   <form:options items="${pacienteList}" itemLabel="value" itemValue="id" />			    
					</form:select>
					<INPUT type="button" value="Agregar" onclick="addRow('dataTable')" class="btn btn-success"/>
						    	<INPUT type="button" value="Eliminar" onclick="deleteRow('dataTable')" class="btn"/>
					
				</div>	
			</div>
    		<div>		    
			    <TABLE id="dataTable" class="table table-striped custab" style="width: 100%; margin: 2% 0">
			        <TR>
			        	<TD></TD>
			            <TD>Id</TD>
			            <TD>Obra Social</TD>        
			            <TD>Nro Credencial</TD>
			            <TD>Original/Provisoria</TD>
			        </TR>
			    </TABLE>
	   	
 			</div>        
        	</td>
    	</tr>
	
	<tr>
	<td>
	<input class="btn btn-lg btn-primary btn-block btn-signin" type="submit" value="Guardar"/>
	</td>
	<td>
	<input type="button" value="Cancelar" onclick="location.href = document.referrer; return false;" class="btn"/>
	</td>
	<td colspan="4"></td>
	</tr>
</table>
</div> 
</form:form>
 
</body>
</html>