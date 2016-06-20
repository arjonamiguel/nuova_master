<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="modal fade" id="filtroafiliados">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only"></span>
				</button>
				<h4 class="modal-title">Reporte Filtro de Afiliados</h4>
			</div>

			<div class="modal-body">
				<div class="container-fluid">
					<div class="row-fluid">
						<!-- Reporte de Pacientes -->
						<div class="span10">
							<div class="formLabel">Desde:</div>
							<div style="visibility: hidden; height: 0px;">
								<input type="text" class="date" id="fechaDesdeAfiliado" />
							</div>
							<div class="formInput">
								<div id="calendar">
									<div class="input-group registration-date-time"
										style="padding-top: 0%;">
										<input class="form-control" name="fecha_desde_afiliado"
											id="fecha_desde_afiliado" type="date"
											onchange="javascript:updatefechaDesdeAfiliado();" />
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span10">
							<div class="formLabel">Hasta:</div>
							<div style="visibility: hidden; height: 0px;">
								<input type="text" class="date" id="fechaHastaAfiliado" />
							</div>
							<div class="formInput">
								<div id="calendar">
									<div class="input-group registration-date-time"
										style="padding-top: 0%;">
										<input class="form-control" name="fecha_hasta_afiliado"
											id="fecha_hasta_afiliado" type="date"
											onchange="javascript:updatefechaHastaAfiliado();" />
									</div>
								</div>
							</div>
						</div>
					</div>

					<div class="row-fluid">
						<div class="span10">
							<div class="formLabel">Nacimiento:</div>
							<div style="visibility: hidden; height: 0px;">
								<input type="text" class="date" id="fechaNacimiento" />
							</div>
							<div class="formInput">
								<div id="calendar">
									<div class="input-group registration-date-time"
										style="padding-top: 0%;">
										<input class="form-control" name="fecha_nacimiento"
											id="fecha_nacimiento" type="date"
											onchange="javascript:updatefechaNacimiento();" />
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row-fluid">
						<div class="span10">
							<div class="formLabel">Localidad:</div>
							<div class="formInput">
								<form:hidden path="localidadId" id="localidadId"/>
								<form:input path="localidadString" data-provide="typeahead"
									id = "localidadString"
									class="typeahead" type="text"
									placeholder="Ingrese Localidad ..." autocomplete="off" />
							</div>
						</div>
					</div>
					
					<div class="row-fluid">
					<div class="span10">
						<div class="formLabel">
							<form:label path="zonaAfiliacion">Zona Afiliación:</form:label>
						</div>
						<div class="formInput">
							<form:select path="zonaAfiliacion" id = "zonaAfiliacion"
								style="margin-bottom:0px" cssStyle="width:235px">
								<form:option value="NONE" label="Seleccione Zona Afiliación ..." />
								<form:options items="${provinciaList}" />
							</form:select>
						</div>
					</div>
					</div>
				</div>



			</div>

			<div class="modal-footer">
				<button type="button" id="btnAceptarFiltro" class="btn btn-info">Aceptar</button>
				<button type="button" id="btnCancelarFiltro" class="btn btn-default"
					data-dismiss="modal">Cancelar</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
