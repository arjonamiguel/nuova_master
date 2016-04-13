<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
function showNuevoNomenclador() {
	document.getElementById("nuevoNomenclador").style.display = '';
}

function nuevoNomencladorOk() {
	document.getElementById("nuevoNomenclador").style.display = 'none';
}
</script>
<div>
	<!-- Autocompletar Nomenclador de codigos -->
	<div>										
	<input type="hidden" name="nomencladorId" id="nomencladorId" value="">										
		<input
			data-provide="typeahead" 
			class="typeahead"
			name="ctl00$ContainerGeneralOverWrite$ContainerGeneral$sq"
			type="text"
			id="ContainerGeneralOverWrite_ContainerGeneral_sq"
			placeholder="Ingrese aqui, Codigo o Nombre del Nomenclador ..."
			autocomplete="off"
			style="height: 20px; width: 40%;margin-bottom:0px">
	
		<input type="button" value="Agregar" onclick="addRow('tb_practicas')" class="btn btn-info"/>

		<input type="button" value="..." onclick="javascript:showNuevoNomenclador()" class="btn"/>
		<span style ="float:right;display: none;" id="nuevoNomenclador">
		<input type="text" id="nomenclador_codigo"  
		style="height: 20px; width: 20%;margin-top:2px"
		placeholder="Codigo"	> 
		<input type="text" id="nomenclador_nombre"  
		style="height: 20px; width: 60%;margin-top:2px"
		placeholder="Nombre del Nomenclador"	>
		<button id="nuevo_nomenclador_ok" 
		class="btn btn-info" 
		onclick="javascript:nuevoNomencladorOk()"
		style="margin-bottom: 8px; height: 30px">
		<i class="ico icon-ok">
		</i>
		</button>
		</span>
	</div>
	<!-- Fin Autocompletar Nomenclador de codigos -->
</div>
<br>
<div class="tab-content" style="height: 200px">	
<table class="scroll"  style="width: 100%">			
<tr>
	<td colspan="2">										           	
	<div>		    
		<table id="tb_practicas" class="table" style="width: 100%; margin: 2% 0;" >
		<thead>
		<tr>													        	
			<td style="width: 50%"><b>Nomenclador</b></td>
			<td style="width: 10%"><b>Valor</b></td>
			<td style="width: 40%"><b>Estados</b></td>
			<td></td>
		</tr>
		<% int index = 0;%>
		<c:forEach items="${ordenDto.practicasListEdit}" var="pa" varStatus="loop" >
			<tr>
			    
			<td>
			<input type="hidden" name = "ordenpracticaListEdit[<%=index%>].orddenPracticaId" value = "${pa.orddenPracticaId}" />
			<input type="hidden" name = "ordenpracticaListEdit[<%=index%>].practicaId" value = "${pa.practicaId}" />
			${pa.nombre}
			</td>
			<td>
			<input type="text" name = "ordenpracticaListEdit[<%=index%>].valor" value = "${pa.valor}" />
			</td>			
			<td>			
				<select name = "ordenpracticaListEdit[<%=index%>].estado" 
				id = "ordenpracticaListEdit[<%=index%>].estado" style="width:70%; margin-bottom:0px" >
					<option value="NONE">Seleccione Estado ...</option>
					<option value="AUTORIZACION DIRECTA">AUTORIZACION DIRECTA</option> 
					<option value="PENDIENTE AFILIACIONES">PENDIENTE AFILIACIONES</option>
					<option value="AUTORIZADA POR AFILIACIONES">AUTORIZADA POR AFILIACIONES</option>
					<option value="RECHAZADA POR AFILIACIONES">RECHAZADA POR AFILIACIONES</option>
					<option value="PENDIENTE AUDITORIA">PENDIENTE AUDITORIA</option>
					<option value="AUTORIZADA POR AUDITORIA">AUTORIZADA POR AUDITORIA</option>
					<option value="RECHAZADA POR AUDITORIA">RECHAZADA POR AUDITORIA</option>
					<option value="RECHAZADA">RECHAZADA</option><option value="ANULADO">ANULADO</option>			    
				</select>
				<script>
				document.getElementById('ordenpracticaListEdit[<%=index%>].estado').value ='${pa.estado}'; 
				</script>
			</td>
			<td><button type='button' class='btn btn-link' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'>Eliminar</button></td>
			<%index++;%>
			</tr>
		</c:forEach>
		</thead>	   
		</table>
	</div>		
	</td>
</tr>
</table>
</div>