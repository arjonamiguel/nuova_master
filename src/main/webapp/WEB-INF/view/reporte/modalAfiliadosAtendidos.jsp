
<div class="modal fade" id="afiliadosAtendidos"
	style="visibility: hidden;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only"></span>
				</button>
				<h4 class="modal-title">Reporte Afiliados Atendidos Por Especialidad y Profesional</h4>
			</div>

			<div class="modal-body" style="height: 300px;">
				<div class="container-fluid">
					<div class="row-fluid">
						<!-- Reporte de Pacientes -->
						<div class="span6">
							<div class="formLabel">Desde:</div>
							<div style="visibility: hidden; height: 0px;">
								<input type="text" class="date" id="fechaDesdeAA" />
							</div>
							<div class="formInput">
								<div id="calendar">
									<div class="input-group registration-date-time"
										style="padding-top: 0%;">
										<input class="form-control" name="fecha_desde_aa"
											id="fecha_desde_aa" type="date"
											onchange="javascript:updatefechaDesdeAA();" />
									</div>
								</div>
							</div>
						</div>

						<div class="span6">
							<div class="formLabel">Hasta:</div>
							<div style="visibility: hidden; height: 0px;">
								<input type="text" class="date" id="fechaHastaAA" />
							</div>
							<div class="formInput">
								<div id="calendar">
									<div class="input-group registration-date-time"
										style="padding-top: 0%;">
										<input class="form-control" name="fecha_hasta_aa"
											id="fecha_hasta_aa" type="date"
											onchange="javascript:updatefechaHastaAA();" />
									</div>
								</div>
							</div>
						</div>
					</div>
					<br>
					<div class="row-fluid">
						<!-- Reporte de Pacientes -->
						<input type="hidden" name="especialidad" id="especialidad">

						<input data-provide="typeahead" class="typeahead"
							name="especialidadString" id="especialidadString" type="text"
							placeholder="Ingrese Especialidad ..." autocomplete="off"
							style="width: 92%">

						
						<br><br>
						<label>Profesionales</label>
						<select id="profesionalId" style="width: 95%;">
								<option value="-1" label="Seleccione Profesional ..." />

						</select>

					</div>

				</div>

			</div>
			<div class="alert alert-danger" id="msj_afiliadoatendido"
				style="visibility: hidden;">
				<strong>Importante!</strong> Usted debe seleccionar un rango de
				fechas.<br> Seleccione Fecha Desde y Fecha Hasta, el campo
				especialidad no es requerido.
			</div>

			<div class="modal-footer">
				<button type="button" id="btnSave" class="btn btn-info">Aceptar</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
