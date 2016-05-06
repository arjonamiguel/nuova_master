
<div class="modal fade" id="pacienteregistrado">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only"></span>
				</button>
				<h4 class="modal-title">Reporte Pacientes Registrados</h4>
			</div>

			<div class="modal-body">
				<div class="container-fluid">
					<div class="row-fluid">
						<!-- Reporte de Pacientes -->
						<div class="span6">
							<div class="formLabel">Desde:</div>
							<div style="visibility: hidden; height: 0px;">
								<input type="text" class="date" id="fechaDesdePR" />
							</div>
							<div class="formInput">
								<div id="calendar">
									<div class="input-group registration-date-time"
										style="padding-top: 0%;">
										<input class="form-control" name="fecha_desde_pr"
											id="fecha_desde_pr" type="date"
											onchange="javascript:updatefechaDesdePR();" />
									</div>
								</div>
							</div>
						</div>

						<div class="span6">
							<div class="formLabel">Hasta:</div>
							<div style="visibility: hidden; height: 0px;">
								<input type="text" class="date" id="fechaHastaPR" />
							</div>
							<div class="formInput">
								<div id="calendar">
									<div class="input-group registration-date-time"
										style="padding-top: 0%;">
										<input class="form-control" name="fecha_hasta_pr"
											id="fecha_hasta_pr" type="date"
											onchange="javascript:updatefechaHastaPR();" />
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>

			</div>
			<div class="alert alert-danger" id="msj_pacienteregistrado" style="visibility: hidden;">														
  			<strong>Importante!</strong> Usted debe seleccionar un rango de fechas.<br>
  			Seleccione Fecha Desde y Fecha Hasta.
  			</div>

			<div class="modal-footer">
				<button type="button" id="btnSavePR" class="btn btn-info">Aceptar</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
