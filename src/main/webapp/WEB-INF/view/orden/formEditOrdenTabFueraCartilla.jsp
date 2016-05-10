<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
												<div id="tb_fueradecartilla" class="tab-pane fade">
													<table class="table" style="width: 100%">
														
														<tr>														
															<td style="width: 10%">Fuera de Cartilla:</td>
															<td>
															<input type="checkbox" id="fueraCartilla"
																name="fueraCartilla" class="largerCheckbox"
																onchange="enabledFueraCartilla()" />
															
															</td>
														</tr>
														<tr>														
															<td style="width: 10%">Entidad de Procedencia:</td>
															<td>
															<form:input path="entidad" disabled="true"/>
															</td>
														</tr>
														<tr>														
															<td style="width: 10%">Observacion:</td>
															<td>
															<form:textarea path="observacionFueraCartilla" disabled="true"/>
															</td>
														</tr>
														
													</table>
												</div>
