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
	
	  <SCRIPT language="javascript">
        var siprosa=0;
      </SCRIPT>
</head>
<body>
<jsp:include page="../sec_menu.jsp"></jsp:include>
<jsp:include page="../breadcrumb.jsp"></jsp:include>


<form:form method="post" action="/nuova/deletePaciente" commandName="paciente">
 	<div class="row col-md-6 col-md-offset-2 custyle">
    <table class="table table-striped custab">
    <tr>
    <td colspan="6"><h4>Eliminar Paciente</h4></td>
    </tr>
    <tr>
    	<td>
    	<form:hidden path="pacienteId"/>
    	<form:label path="dni">DNI:</form:label>
    	</td>
        <td><form:input path="dni" disabled="true"/></td>
        <td><form:label path="apellido">Apellido:</form:label></td>
        <td><form:input path="apellido" disabled="true"/></td>
        <td><form:label path="nombre">Nombre:</form:label></td>
        <td><form:input path="nombre" disabled="true"/></td>
    </tr>
   	<tr>
        <td><form:label path="fechaNacimiento">Fecha de Nacimiento:</form:label></td>
        <td><form:input path="fechaNacimiento" disabled="true"/></td>
             <td><form:label path="telefono">Telefono:</form:label></td>
        <td><form:input path="telefono" disabled="true"/></td>
        <td><form:label path="mail">E-Mail:</form:label></td>
        <td><form:input path="mail" disabled="true"/></td>
    </tr>
    <tr>
    	
    	<td><form:label path="provincia">Provincia:</form:label></td>
    	<td>
    	<form:select path="provincia" style="width:100%; margin-bottom:0px" disabled="true">
			<form:option value="NONE" label="Seleccione Provincia ..."/>
			<form:options items="${provinciaList}"  />			    
		</form:select>
		</td>
        <td><form:label path="domicilio">Domicilio:</form:label></td>
        <td colspan=""><form:textarea path="domicilio" cssStyle="width:100%" disabled="true"/></td>
        <td><form:label path="liberado">Liberado:</form:label></td>
        <td><form:input path="liberado" disabled="true"/></td>
    </tr>
    
    <tr>
         <td colspan="6"><h5>Obra Social</h5></td>         
    </tr>
    <tr>
        <td colspan="6">
           <div>   

			<div class="inputProf">
			<form:select path="obrasocial.nombre" style="width:70%; margin-bottom:0px" disabled="true">
				   <form:option value="NONE" label="Seleccione Obra Social ..."/>
				   <form:options items="${obrasocialList}" itemLabel="nombre" itemValue="obrasocialId" />			    
				</form:select>
			<INPUT type="button" value="Agregar" onclick="addRow('tb_paciente_obrasocial')" class="btn btn-primary" disabled="disabled"/>
	    	<INPUT type="button" value="Eliminar" onclick="deleteRow('tb_paciente_obrasocial')" class="btn" disabled="disabled"/>
				
			</div>	
			</div>
    <div>  
    
	    <TABLE id="tb_paciente_obrasocial" class="table table-striped custab" style="width: 100%; margin: 2% 0">
	        <TR>
	        	<TD></TD>
	            <TD>Id</TD>
	            <TD>Obra Social</TD>        
	            <TD>Nro Credencial</TD>
	            <TD>Original/Provisoria</TD>
	        </TR>
	        
	        <% int index = 0;%>
	        <c:forEach items="${paciente.obrasocialList}" var="po" varStatus="loop" >
	    	<tr>
		    	<td><input type="checkbox" name = "chkbox[]" disabled="disabled"/></td>
		        <td>${po.obrasocialId}<input type="hidden" name = "obrasocialListEdit[<%=index%>].obrasocialId" value = "${po.obrasocialId}" disabled="disabled"/> </td>
		        <td>${po.nombre}</td>        
		        <td><input type="text" value="${po.credencial}" name = "obrasocialListEdit[<%=index%>].credencial" disabled="disabled"/></td>
		        <td> <input type="checkbox" name="obrasocialListEdit[<%=index%>].original" ${po.original} disabled="disabled"/></td>
		        <%index++;%>
	    	</tr>
	</c:forEach>
	    </TABLE>
	   	
 	</div>
        
        </td>
    </tr>
      <tr>
         <td colspan="6"><h5>Adherentes</h5></td>         
    </tr>
    <tr>
	    <td colspan="6">
	    <div style="text-align: right;">
	    <INPUT type="button" value="Add Row" onclick="addRowAdherente('dataTableAdherente')" class="btn btn-primary" disabled="disabled"/>
		<INPUT type="button" value="Delete Row" onclick="deleteRowAdherente('dataTableAdherente')" class="btn" disabled="disabled"/>
		</div>
	     <TABLE id="dataTableAdherente" class="table table-striped custab" style="width: 100%; margin: 2% 0">
	        <TR>
	        	<TD></TD>
	            <TD>Id</TD>
	            <TD>DNI</TD>
	            <TD>Apellido</TD>        
	            <TD>Nombre</TD>
	            <TD>Credencial</TD>
	        </TR>
	    </TABLE> 
	    </td>
    </tr>
    
    <tr>
        <td>
            <input class="btn btn-lg btn-primary btn-block btn-signin" type="submit" value="Eliminar"/>
        </td>
        <td>
            <input type="button" value="Cancelar" onclick="location.href='/nuova/mainPaciente';" class="btn"/>
        </td>
        <td colspan="4">
        </td>
    </tr>
	</table>
	</div> 
</form:form>
 

</body>
</html>