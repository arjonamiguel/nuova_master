<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
var nuevoNomActive = false;
function showNuevoNomenclador() {
	if (!nuevoNomActive) {
		document.getElementById("nuevoNomenclador").style.display = '';
		nuevoNomActive = true;
	} else {
		document.getElementById("nuevoNomenclador").style.display = 'none';
		nuevoNomActive = false;
	}
}

function nuevoNomencladorOk() {
	var codigo = document.getElementById("nomenclador_codigo").value;
	var nombre = document.getElementById("nomenclador_nombre").value;
	
	if(codigo == "" && nombre == "") {
		alert("Los campos Codigo y Nombre del Nomenclador, no pueden ser vacios.");
		return;
	}
	
	var nomencladorId = callSaveCodigoNomenclador(getJsonNomenclador(codigo, nombre));
	
	if (nomencladorId != "-1") {
		addRowNuevoNom('tb_practicas', nomencladorId);
		document.getElementById("nuevoNomenclador").style.display = 'none';
	}else {
		alert("ERROR: No se pudo agregar verifique los datos ingresados.");	
	}
	
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

function addRowNuevoNom(tableID, nomencladorId) {
	var codigo = document.getElementById("nomenclador_codigo").value;
	var nombre = document.getElementById("nomenclador_nombre").value;
	
	if(codigo == "" && nombre == "") {
		alert("Los campos Codigo y Nombre del Nomenclador, no pueden ser vacios.");
		return;
	}
  
	var index = document.getElementById(tableID).getElementsByTagName('tr').length;
	index ++;	
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    row.style.background= '#f5f5f5';
          
    var cell0 = row.insertCell(0);
    cell0.innerHTML = "["+codigo+"] - [MANUAL] - "+nombre+" " + 
    " <input type='hidden' name='ordenpracticaListEdit[" + index + "].orddenPracticaId'> " +
    " <input type='hidden' name='ordenpracticaListEdit[" + index + "].practicaId' value='" + nomencladorId + "'>"; 

    var cell1 = row.insertCell(1);
    cell1.innerHTML = "<input type='text' name='ordenpracticaListEdit[" + index + "].valor' value='0.00'>"; 
    
    var cell2 = row.insertCell(2);
    cell2.innerHTML = createSelectEstados("ordenpracticaListEdit[" + index + "].estado");
    
    var cell3 = row.insertCell(3);
    cell3.innerHTML = createDatePicker(index); 

    var cell4 = row.insertCell(4);
    row.valign = "BASELINE";
    cell4.innerHTML = "<button type='button' class='btn btn-link' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'>Eliminar</button>";
    
   	var cell5 = row.insertCell(5);    
   	cell5.innerHTML = "";
   	
    index ++;
    document.getElementById("nomenclador_codigo").value = "";
    document.getElementById("nomenclador_nombre").value = "";
    document.getElementById("ContainerGeneralOverWrite_ContainerGeneral_sq").focus();
   }
   
function checkAll(chk) {
    var chks = document.getElementById("tb_practicas").getElementsByTagName("input");
    for(var i=0; i<chks.length; i++) {
        if(chks[i].type == "checkbox") 
        	chks[i].checked= chk.checked;
    }
}
function updateDate(i){	
	document.getElementById("ordenpracticaListEdit["+i+"].autorizarAutomatico").value=document.getElementById("autorizar_automatico_"+i).value;
}

</script>
<div>
	<!-- Autocompletar Nomenclador de codigos -->
	<div>										
	<input type="hidden" name="nomencladorId" id="nomencladorId" value="">										
		<input
			data-provide="typeahead" 
			class="typeahead"
			name="nomencladorString"
			type="text"
			id="nomencladorString"
			placeholder="Ingrese aqui, Codigo o Nombre del Nomenclador ..."
			autocomplete="off"
			style="height: 20px; width: 40%;margin-bottom:0px">
	
		<input type="button" value="Agregar" onclick="addRow('tb_practicas')" class="btn btn-info"/>

		<input type="button" value="..." onclick="javascript:showNuevoNomenclador()" class="btn"/>
		<span style ="float:right;display: none;" id="nuevoNomenclador">
			<input type="number" id="nomenclador_codigo"  
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
<div style = "float:right;padding-right: 20px">
<a href="#">
<img alt="Imprimir Orden" src="/nuova/resources/img/print_16x16.png" />
Imprimir</a> 
</div>
<br>

<div class="tab-content" style="height: 400px">	
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
			<td style="width: 20%"><b>Automatico</b></td>
			<td></td>
			<td>
				<input type="checkbox" name="selectImprimirAll" 
				onclick="checkAll(this)">				
			</td>
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
			
			<td align="left">				
					<div style="visibility:hidden;height:0px;">
						<input type="text" 
							name = "ordenpracticaListEdit[<%=index%>].autorizarAutomatico"  
							id = "ordenpracticaListEdit[<%=index%>].autorizarAutomatico"
							value = "${pa.autorizarAutomatico}" 
							class="date" />						
					</div>
					
					<div id="calendar">
					<div class="input-group registration-date-time" style="padding-top:0%;">
						<input class="form-control" 
							name="autorizar_automatico_<%=index%>" 
							id="autorizar_automatico_<%=index%>" 
							type="date"
							value = "${pa.autorizarAutomatico}"  
							onchange="javascript:updateDate(<%=index%>);">
		            </div>
		            </div>
	           
			</td>
			<td><button type='button' class='btn btn-link' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'>Eliminar</button></td>
			<td><input type="checkbox" name="ordenpracticaListEdit[<%=index%>].imprimir"></td>
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