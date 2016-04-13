<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
function showNuevoNomenclador() {
	document.getElementById("nuevoNomenclador").style.display = '';
}

function nuevoNomencladorOk() {
	var codigo = document.getElementById("nomenclador_codigo").value;
	var nombre = document.getElementById("nomenclador_nombre").value;
	
	if(codigo == "" && nombre == "") {
		alert("Los campos Codigo y Nombre del Nomenclador, no pueden ser vacios.");
	}
	
	var result = callSaveCodigoNomenclador(getJsonNomenclador(codigo, nombre));
	document.getElementById("nuevoNomenclador").style.display = 'none';
}

function callSaveCodigoNomenclador(jsonString) {
	var retorno;
	$.ajax({
		url : "/nuova/ajaxPostSaveCodigoNomenclador",
		type : "POST",
		contentType : "application/json; charset=utf-8",
		data: jsonString, //Stringified Json Object
		async : false, //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
		cache : false, //This will force requested pages not to be cached by the browser          
		processData : false, //To avoid making query String instead of JSON
		success : function(result) {
			retorno = result;
		}
	});

	return retorno;
}

function getJsonNomenclador(codigo, nombre){
	return '{"codigo":"'+codigo+'", "nombre":"'+nombre+'"}';
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
			placeholder="C&oacute;digo"	> 
			<input type="text" id="nomenclador_nombre"  
			style="height: 20px; width: 60%;margin-top:2px"
			placeholder="Nombre del Nomenclador">
			<input type="button" 
			value="OK" 
			id="nuevo_nomenclador_ok" 
			onclick="javascript:nuevoNomencladorOk()"
			class="btn btn-info"
			style="margin-bottom: 8px; height: 30px"/>
			
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
			<td style="width: 40%"><b>Nomenclador</b></td>
			<td style="width: 10%"><b>Valor</b></td>
			<td style="width: 31%"><b>Estados</b></td>			
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