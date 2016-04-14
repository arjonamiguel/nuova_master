<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="tab-content" style="height: 400px">
<table class="table" style="width: 100%">
	<tr>
		<td style="width: 15%"><form:label path="profesionalId">Profesional</form:label></td>
		<td style="text-align: left" colspan="5"><form:select
				path="profesionalId" style="width:80%; margin-bottom:0px"
				onchange="findEspecialidades(this);">
				<form:option value="-1" label="Seleccione Profesional ..." />
				<form:options items="${profesionales}" itemLabel="value"
					itemValue="id" />
			</form:select></td>
		<td><form:label path="especialidad">Especialidad</form:label></td>
		<td><form:select path="especialidad"
				style="width:60%; margin-bottom:0px">
				<form:option value="-1" label="Seleccione Especialidad ..." />
				<form:options items="${especialidades}" itemLabel="value"
					itemValue="id" />
			</form:select></td>
	</tr>
</table>
</div>