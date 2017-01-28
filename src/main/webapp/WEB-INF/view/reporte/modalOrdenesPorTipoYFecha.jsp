
<div class="modal fade" id="modal_ordenes_por_tipo_fecha"
	style="visibility: hidden;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only"></span>
				</button>
				<h4 class="modal-title">Reporte Cantidad de Ordenes</h4>
			</div>

			<div class="modal-body" style="height: 200px;">
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span6">
							<div class="formLabel">Ordenes:</div>
							<!-- Reporte de Pacientes -->
							<div class="formInput">
								<select id="tipo_orden">
									<option value="1">CONSULTAS</option>
									<option value="3">PRACTICAS</option>
								</select>
							</div>

						</div>
					</div>
				
				<div class="row-fluid">
					<!-- Reporte de Pacientes -->
					<div class="span6">
						<div class="formLabel">Desde:</div>
						<div style="visibility: hidden; height: 0px;">
							<input type="text" class="date" id="fechaDesdeOrden" />
						</div>
						<div class="formInput">
							<div id="calendar">
								<div class="input-group registration-date-time"
									style="padding-top: 0%;">
									<input class="form-control" name="fecha_desde_orden"
										id="fecha_desde_orden" type="date"
										onchange="javascript:updatefechaDesdeOrden();" />
								</div>
							</div>
						</div>
					</div>


				</div>
				<div class="row-fluid">
					<div class="span6">
						<div class="formLabel">Hasta:</div>
						<div style="visibility: hidden; height: 0px;">
							<input type="text" class="date" id="fechaHastaOrden" />
						</div>
						<div class="formInput">
							<div id="calendar">
								<div class="input-group registration-date-time"
									style="padding-top: 0%;">
									<input class="form-control" name="fecha_hasta_orden"
										id="fecha_hasta_orden" type="date"
										onchange="javascript:updatefechaHastaOrden();" />
								</div>
							</div>
						</div>
					</div>
				</div>
				</div>
			</div>
			<div class="alert alert-danger" id="msj_ordenfiltro"
				style="visibility: hidden;">
				<strong>Importante!</strong> Usted debe seleccionar un rango de
				fechas.<br> Seleccione Fecha Desde y Fecha Hasta, el campo
				Orden.
			</div>

			<div class="modal-footer">
				<button type="button" id="btn_modal_ordenes_por_tipo_fecha_aceptar"
					class="btn btn-info">Aceptar</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->