<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="tab-content" style="height: 400px">
	<table class="table" style="width: 100%">
		<tr>
			<td style="width: 15%"><form:label path="especialidad">Especialidad</form:label></td>
			<td style="text-align: left" colspan="5">
				<input type="hidden"
				name="especialidadPrestador" id="especialidadPrestador"
				value="${ordenDto.especialidadPrestador}"> 
				
				<input disabled="disabled"
				data-provide="typeahead" 
				class="typeahead" 
				name="especialidadPrestadorString"
				id="especialidadPrestadorString"
				type="text" 
				placeholder="Ingrese Especialidad ..." 
				autocomplete="off"
				value="${ordenDto.especialidadPrestadorView}"> 
				
			</td>
			<td style="width: 15%"><form:label path="prestadorId">Prestador</form:label></td>
			<td style="text-align: left" colspan="5"><form:select disabled="true"
					path="prestadorId" style="width:80%; margin-bottom:0px">
					<form:option value="-1" label="Seleccione Profesional ..." />
					<form:options items="${prestadores}" itemLabel="value"
						itemValue="id" />
				</form:select></td>

		</tr>

	</table>
</div>