<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="tab-content" style="height: 400px">
	<table class="table" style="width: 100%">
		<tr>
			<td style="width: 15%"><form:label path="especialidad">Especialidad</form:label></td>
			<td style="text-align: left" colspan="5"><input type="hidden"
				name="especialidad" id="especialidad"
				value="${ordenDto.especialidad}"> <input
				data-provide="typeahead" class="typeahead" name="especialidadString"
				id="especialidadString" type="text"
				placeholder="Ingrese Especialidad ..." autocomplete="off"
				value="${especialidadView}"> <a
				href="/nuova/formAddEspecialidad" title="Nueva Especialidad"
				target="_blank"> <img
					src="/nuova/resources/img/list_add_16x16.png">
			</a></td>
			<td style="width: 15%"><form:label path="profesionalId">Profesional</form:label></td>
			<td style="text-align: left" colspan="5"><form:select
					path="profesionalId" style="width:80%; margin-bottom:0px">
					<form:option value="-1" label="Seleccione Profesional ..." />
					<form:options items="${profesionales}" itemLabel="value"
						itemValue="id" />
				</form:select></td>

		</tr>

	</table>


	<table class="table" style="width: 100%">

		<tr>
			<td style="width: 10%">Fuera de Cartilla:</td>
			<td><form:checkbox path="fueraCartilla" id="fueraCartilla" cssClass="largerCheckbox" 
				onchange="enabledFueraCartilla()" /></td>
		</tr>
		<tr>
			<td style="width: 10%">Entidad de Procedencia:</td>
			<td><form:input path="entidad" disabled="true" /></td>
		</tr>
		<tr>
			<td style="width: 10%">Observacion:</td>
			<td><form:textarea path="observacionFueraCartilla"
					disabled="true" /></td>
		</tr>

	</table>
<script>
enabledFueraCartilla();
</script>
</div>