<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<table class="table" style="width: 100%">
	<tr>
		<td style="width: 15%">Monto de Coseguro $:</td>
		<td style="width: 35%"><form:input path="monto"
				cssStyle="width: 40%" /></td>
		<td style="width: 10%">Sin Costo:</td>
		<td><input type="checkbox" id="coseguroSinCosto"
			name="coseguroSinCosto" class="largerCheckbox" onchange="sinCosto()" />
		</td>
		
		<td style="width: 20%; text-align: right;">Fecha Caja:</td>
		<td>
			<div style="visibility: hidden; height: 0px;">
				<form:input path="fechaCaja" class="date" />
			</div>
			<div id="calendar">
				<div class="input-group registration-date-time"
					style="padding-top: 0%;">
					<input class="form-control" name="fechaCajaName" id="fechaCajaId"
						type="date" onchange="javascript:updateDateCaja();">
				</div>
			</div>
		</td>
	</tr>

	<tr>
		<td colspan="6">
			<div class="alert alert-info">
				<strong>Importante!</strong> Usar "." (punto) en montos decimales<br>
				Ejemplos: 2.00 / 5.50 / 12.00 / 161.20 / 5100.58
			</div>
		</td>
	</tr>
</table>
