<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${paciente.parentescoVO == 0}">
	<div class="panel panel-info">

		<div class="panel-body">
			<div class="row-fluid">
				<div class="span12">
					<div style="text-align: right;">
						<INPUT type="button" value="Nuevo Adherente"
							onclick="nuevoAdherente()" class="btn btn-success" />
					</div>
					<TABLE id="dataTableAdherente" class="table"
						style="width: 100%; margin: 1% 0">
						<TR>

							<TD><b>DNI</b></TD>
							<TD><b>Apellido</b></TD>
							<TD><b>Nombre</b></TD>
							<TD><b>Credencial</b></TD>
							<TD><b>Parentesco</b></TD>
							<TD></TD>
						</TR>
						<% int index2 = 0;%>
						<c:forEach items="${paciente.adherentes}" var="adh"
							varStatus="loop">
							<tr>
								<td>${adh.dni}<input type="hidden"
									name="adherentesEditList[<%=index2%>].pacienteId"
									value="${adh.pacienteId}" /></td>
								<td>${adh.apellido}</td>
								<td>${adh.nombre}</td>
								<td>${adh.crdencial}</td>
								<td>${adh.parentescoDescription}</td>
								<td><a class="btn btn-info btn-xs"
									href="/nuova/formEditPaciente/${adh.pacienteId}"
									title="Editar Adherente"> <span class="icon icon-edit"
										title="Editar Adherente"></span></a> <a
									class="btn btn-danger btn-xs"
									href="/nuova/formDeletePaciente/${adh.pacienteId}"
									title="Eliminar Adherente"> <span class="icon icon-remove"
										title="Eliminar Adherente"></span></a></td>
								<%index2++;%>
							</tr>
						</c:forEach>
					</TABLE>
				</div>
			</div>
		</div>
	</div>
</c:if>
