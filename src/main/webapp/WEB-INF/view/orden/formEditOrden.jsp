<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Nuova</title>
    <link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
	<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
	
	<script type="text/javascript">
	
	  function Eliminar (i) {    	 
  	    document.getElementById("tb_practicas").deleteRow(i);
  	}
  	
      function addRow(tableID) {
      	var index = document.getElementById(tableID).getElementsByTagName('tr').length;
      	index ++;	
          var table = document.getElementById(tableID);

          var rowCount = table.rows.length;
          var row = table.insertRow(rowCount);

          var cell2 = row.insertCell(0);                      
          cell2.innerHTML = document.getElementById("practica").value+"<input type='hidden' name='practicasListEdit["+index+"].practicaId' value='"+document.getElementById("practica").value+"'>";

          var cell3 = row.insertCell(1);
          cell3.innerHTML = document.getElementById('practica').options[document.getElementById('practica').selectedIndex].text; 


          var cell1 = row.insertCell(2);
          row.valign = "BASELINE";
          cell1.innerHTML = "<button type='button' class='btn btn-link' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'>eliminar</button>"
           
          index ++;

       }

	</script>
	
	<style>
	.chkbox {
   padding-left: 10px;
   font-weight: bold;
 }
		.custab{
    border: 1px solid #ccc;
    padding: 5px;
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
<form:form method="post" action="/nuova/editOrden" commandName="ordenDto">
<form:hidden path="ordenId"/>
<div class="row col-md-6 col-md-offset-2 custyle">
	<ul class="nav nav-tabs">
	  <li class="active"><a data-toggle="tab" href="#tb_paciente">Paciente</a></li>
	  <li><a data-toggle="tab" href="#tb_requisitos">Requisitos</a></li>
	  <li><a data-toggle="tab" href="#tb_autorizacion">Autorizacion</a></li>
	  <li>
	  	<a data-toggle="tab" href="#tb_observacion">
	  	Observaciones 
	  	<c:if test="${observacionCount > 0}">
     		<span class="badge">${observacionCount}</span>
     	</c:if>
	  	</a>
	  </li>
	  <li><a data-toggle="tab" href="#tb_flujo">Flujo</a></li>
	</ul>

	<div class="tab-content" style="height: 350px">
  		<div id="tb_paciente" class="tab-pane fade in active">
  		<table class="table table-striped custab">
			
			<tr>
				<td><form:label path="paciente.dni">DNI</form:label></td>
				<td><form:input path="paciente.dni"/></td>
				<td><form:label path="paciente.apellido">Apellido</form:label></td>
				<td>
					<form:hidden path="paciente.pacienteId"/>
					<form:input path="paciente.apellido"/>
				</td>
				<td><form:label path="paciente.nombre">Nombre</form:label></td>
				<td><form:input path="paciente.nombre"/></td>
			</tr>
			
			<tr>
				<td><form:label path="paciente.obrasocial.nombre">Obra Social</form:label></td>
				<td><form:input path="paciente.obrasocial.nombre"/></td>	
				<td><form:label path="paciente.obrasocial.credencial">Credencial</form:label></td>			
				<td><form:input path="paciente.obrasocial.credencial"/></td>
				<td><form:label path="paciente.obrasocial.original">Original</form:label></td>			
				<td><input type="checkbox" /> </td>
			</tr>
			
		</table>  		   
 		</div>
  
  		<div id="tb_requisitos" class="tab-pane fade">
		<table class="table table-striped custab" style="width: 60%">
			
			<tr>
				
				<td colspan="4">
					<b>Presentó la orden original del médico solicitante?</b>
				</td>
				<td  style="text-align:left" colspan="2">			
				    <input type="checkbox" id="reqOrdenMedico" name = "reqOrdenMedico"  />
				</td>
			</tr>
			<tr>
				
				<td colspan="4">
					<b>Presentó fotocopia de la credencial de la prestadora OSPSIP?</b>
				</td>
				<td  style="text-align:left" colspan="2">			
				    <input type="checkbox" id="reqCredecial" name="reqCredecial"  />
				</td>
			</tr>

		</table>
		
		<table class="table table-striped custab" style="width: 60%">
			<tr>			
				<td colspan="4">
					<b>Presentó fotocopia del último recibo Monotributista?</b>
				</td>
				<td  style="text-align:left" colspan="2">			
				    <input type="checkbox" id="reqMonotributista" name="reqMonotributista" />
				</td>
			</tr>	
			<tr>
				
				<td colspan="4">
					<b>Presentó fotocopia del último recibo de sueldo?</b>
				</td>
				<td  style="text-align:left" colspan="2">			
				    <input type="checkbox" id="reqReciboSueldo" name="reqReciboSueldo" />
				</td>
			</tr>

		</table>  		   
		  		   
		
  		</div>
  		<div id="tb_autorizacion" class="tab-pane fade">
  		<table class="table table-striped custab"  style="width: 60%">			
			<tr>
				<td style="width: 10%">Estado</td>
        		<td>
        			<form:select path="estado" style="width:30%; margin-bottom:0px">
					   <form:option value="NONE" label="Seleccione Estado ..."/>
					   <form:options items="${estadosList}" itemLabel="value" itemValue="id" />			    
					</form:select>
				</td>
        	</tr>	
  		</table>
    	<table class="table table-striped custab"  style="width: 60%" height="78%">			
			<tr>
        	<td colspan="2">
           	<div>
				<div class="inputProf">
				<form:select path="practica" style="width:70%; margin-bottom:0px">
					   <form:option value="NONE" label="Seleccione Práctica ..."/>
					   <form:options items="${practicasList}" itemLabel="value" itemValue="id" />			    
					</form:select>
					<INPUT type="button" value="Agregar" onclick="addRow('tb_practicas')" class="btn btn-info"/>
						    	
					
				</div>	
			</div>
    		<div>		    
			    <TABLE id="tb_practicas" class="table table-striped custab scroll" style="width: 100%; margin: 2% 0;" >
			    <thead>
			        <TR>
			        	
			            <TD style="width: 10%">ID</TD>			                    
			            <TD style="width: 100%">Practica</TD>
			            <td></td>
			        </TR>
			    </thead>
			   
			    </TABLE>
	   	
 			</div>    
 			
        	</td>
    	</tr>
			
		</table>
  		</div>
  		
  		<div id="tb_observacion" class="tab-pane fade">  					
			<c:forEach items="${ordenDto.observacioneses}" var="obs" varStatus="loop" >
			<table class="table table-striped custab"  style="width: 60%">	
			<tr>
				<td style="width: 80%; padding: 1px 1px;border-left: 1px solid orange">
	    		<b>${obs.userName}</b> <span style="font-size: 12px">${obs.fecha}</span>
	    		</td>
	    		<td style="padding: 1px 1px; text-align: right;">
	    		<a class="btn btn-link" href="#">
					<i class="icon-trash"></i>
				</a>
				<a class="btn btn-link" href="#">
					<i class="icon-pencil"></i>
				</a>
				
	    	</tr>
	    	<tr style="border-left: 1px solid orange">	    		
		        <td align="justify" colspan="2">${obs.observacion}</td>		      
	    	</tr>
	
	    	</table>
	    	</c:forEach>	
			
    		<table class="table table-striped custab"  style="width: 60%">    					
			<tr>		
				<td style="width: 15%"><form:label path="observacion">Observacion</form:label></td>
				<td  style="text-align:left" colspan="5">			
				    <form:textarea path="observacion" cssStyle="width:100%"/>
				</td>
			</tr>		
			</table>
  		</div>
  		<div id="tb_flujo" class="tab-pane fade">  					
			<c:forEach items="${ordenDto.ordenWorkflows}" var="ow" varStatus="loop" >
			<table class="table table-striped custab"  style="width: 60%">	
	    	<tr style="border-left: 1px solid orange">
	    		<td>
	    		<b>${ow.userName}</b>
	    		</td>
		        <td align="justify">${ow.estado}</td>		      
	    	</tr>	
	    	</table>
	    	</c:forEach>   		
  		</div>
  		
	</div>
	
	<table>
 		<tr>
			<td style="width: 50%">
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