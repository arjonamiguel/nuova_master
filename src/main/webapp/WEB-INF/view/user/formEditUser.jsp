<%@page import="com.nuova.dto.UsuarioDTO"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
UsuarioDTO dto = (UsuarioDTO) session.getAttribute("userSession");
String userName = dto != null ? dto.getUsername() : "";
Integer userId = dto != null ? dto.getUserId() : 0;
%>
<div class="modal fade" id="cambioPassword" style="visibility: hidden;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only"></span>
				</button>
				<h4 class="modal-title">Cambio de Password</h4>
			</div>

			<div class="modal-body">
				<div class="container-fluid">
					<div class="row-fluid">
						<!-- Reporte de Pacientes -->
						<div class="span15">
							<div class="formLabel">Usuario:</div>							
							<div class="formInput">
								<input type="hidden" id="user_id" value="<%=userId%>"/>						
								<input type="text" name="userName" id="userName" value="<%=userName%>" disabled="disabled"/>
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span15">
							<div class="formLabel">Nueva Paswword:</div>								 
							<div class="formInput">
								<input type="password" id="txt_nuava_password">
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span15">
							<div class="formLabel">Repita Paswword:</div>							
							<div class="formInput">
								<input type="password" id="txt_repite_password">
							</div>
						</div>
					</div>
				
				</div>



			</div>

			<div class="modal-footer">
				<button type="button" id="btnAGuardarPassword" class="btn btn-info">Guardar</button>
				<button type="button" id="btnCancelarPassword" class="btn btn-default"
					data-dismiss="modal">Cancelar</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
