<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="tab-content" style="height: 400px">	
<table class="table">						
	<tr>
		<td><form:label path="paciente.dni">DNI</form:label></td>
		<td><form:input path="paciente.dni" disabled="true"/></td>
		<td><form:label path="paciente.apellido">Apellido</form:label></td>
		<td>
			<form:hidden path="paciente.pacienteId"/>
			<form:input path="paciente.apellido" disabled="true"/>
		</td>
		<td><form:label path="paciente.nombre">Nombre</form:label></td>
		<td><form:input path="paciente.nombre" disabled="true"/></td>
	</tr>
						
	<tr>
		<td><form:label path="paciente.obrasocial.nombre">Obra Social</form:label></td>
		<td><form:input path="paciente.obrasocial.nombre" disabled="true"/></td>	
		<td><form:label path="paciente.obrasocial.credencial">Credencial</form:label></td>			
		<td><form:input path="paciente.obrasocial.credencial" disabled="true"/></td>
		<td></td>			
		<td></td>
	</tr>
</table>  		 
</div> 