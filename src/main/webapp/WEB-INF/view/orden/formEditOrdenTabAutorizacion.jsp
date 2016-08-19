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
	var tipo = document.getElementById("nomenclador_tipo").value;
	var codigo = document.getElementById("nomenclador_codigo").value;
	var nombre = document.getElementById("nomenclador_nombre").value;
	
	if(tipo == "NONE" && codigo == "" && nombre == "") {
		alert("Los campos Tipo, Codigo y Nombre del Nomenclador, no pueden ser vacios.");
		return;
	}
	
	var nomencladorId = callSaveCodigoNomenclador(getJsonNomenclador(tipo, codigo, nombre));
	
	if (nomencladorId != "-1") {
		// addRowNuevoNom('tb_practicas', nomencladorId);
		// document.getElementById("nuevoNomenclador").style.display = 'none';
	}else {
		alert("ERROR: No se pudo agregar verifique los datos ingresados.");	
	}
	
	document.getElementById("nomenclador_tipo").value="NONE";
	document.getElementById("nomenclador_codigo").value="";
	document.getElementById("nomenclador_nombre").value="";

	
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

function getJsonNomenclador(tipo, codigo, nombre){
	return '{"tipo":"'+tipo+'", "codigo":"'+codigo+'", "nombre":"'+nombre+'"}';
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
		var str1 = nombre;
		var str2 = "ODON";
		
		if(str1.indexOf(str2) != -1){
			cell1.innerHTML = "<input type='text' name='ordenpracticaListEdit[" + index + "].piezaDental' placeholder='pieza dental'>";
		}

    var cell2 = row.insertCell(2);
    cell2.innerHTML = "<input type='hidden' name='ordenpracticaListEdit[" + index + "].valor' value='0.00'>"; 
    
    var cell3 = row.insertCell(3);
    cell3.innerHTML = createSelectEstados("ordenpracticaListEdit[" + index + "].estado");
    
    var cell4 = row.insertCell(4);
    cell4.innerHTML = createDatePicker(index); 

    var cell5 = row.insertCell(5);
    row.valign = "BASELINE";
    cell5.innerHTML = "<button type='button' class='btn btn-link' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'>Eliminar</button>";
    
   	var cell6 = row.insertCell(6);    
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

function marcarAll(){
	
	if(document.getElementById("authAll").checked==true){
		$("#tb_practicas").find("select").each(function() {
			$this = $(this);
			$this.val("AUTORIZACION DIRECTA");
		});
	}else{
		$("#tb_practicas").find("select").each(function() {
			$this = $(this);
			$this.val("NONE");
		});
	}		
}

function automaticoTodos() {
	var dateSelected = document.getElementById("auto_todos").value;
	for(i=0;i<100; i++) {
		datepicker = document.getElementById("autorizar_automatico_"+i);		
		if (datepicker != null){
			document.getElementById("autorizar_automatico_"+i).value=dateSelected;
			updateDate(i);
		}	
	}
}

</script>
<div>
	<!-- Autocompletar Nomenclador de codigos -->
	<div>
		<input type="hidden" name="nomencladorId" id="nomencladorId" value="">
		<input data-provide="typeahead" class="typeahead"
			name="nomencladorString" type="text" id="nomencladorString"
			placeholder="Ingrese aqui, Codigo o Nombre del Nomenclador ..."
			autocomplete="off"
			style="height: 20px; width: 40%; margin-bottom: 0px"> <input
			type="button" value="Agregar" onclick="addRow('tb_practicas')"
			class="btn btn-info" /> 
			<a href="#" title="Nueva Codigo" id="btnNuevoCodigo">
								<img src="/nuova/resources/img/list_add_16x16.png">
							</a> 
			
	</div>
	<!-- Fin Autocompletar Nomenclador de codigos -->
</div>
<br>

<table class="table" style="background: rgb(245, 245, 245); width: 100%">
	<tr>
		<td style="width: 80%;"><div style="float: right;">
				<b>Autorizar todos:</b>
			</div></td>
		<td><div style="float: right;">
				<input id="authAll" type="checkbox" class="largerCheckbox"
					onchange="javascript:marcarAll()">
			</div></td>
		<td style="width: 30%"><div style="float: right;">
				<b>Automatizar todos:</b>
			</div></td>
		<td>
			<div id="calendar">
								<div class="input-group registration-date-time" style="padding-top:0%;">
									<input class="form-control" 
									name="auto_todos" id="auto_todos" 
									type="date"  
									onchange="javascript:automaticoTodos();">
	            				</div>
	            			</div>
		</td>
	</tr>
</table>

<div class="tab-content" style="height: 400px">
	<table class="scroll" style="width: 100%">
		<tr>
			<td colspan="2">
				<div>
					<table id="tb_practicas" class="table"
						style="width: 100%; margin: 2% 0;">
						<thead>
							<tr>
								<td style="width: 40%"><b>Nomenclador</b></td>
								<td style="width: 10%"><b>Pieza Dental</b></td>
								<td style="width: 10%"></td>
								<td style="width: 31%"><b>Estados</b></td>
								<td style="width: 20%"><b>Automatico</b></td>
								<td></td>
								<td><input type="checkbox" name="selectImprimirAll" disabled="disabled"
									onclick="checkAll(this)"></td>
							</tr>
							<%
							  int index = 0;
							%>
							<c:forEach items="${ordenDto.practicasListEdit}" var="pa"
								varStatus="loop">
								<tr>

									<td><input type="hidden"
										name="ordenpracticaListEdit[<%=index%>].orddenPracticaId"
										value="${pa.orddenPracticaId}" /> <input type="hidden"
										name="ordenpracticaListEdit[<%=index%>].practicaId"
										value="${pa.practicaId}" /> ${pa.nombre}</td>
									<td><input type="text" style="text-align: center"
										name="ordenpracticaListEdit[<%=index%>].piezaDental"
										value="${pa.piezaDental}" /></td>
									<td><input type="hidden"
										name="ordenpracticaListEdit[<%=index%>].valor"
										value="${pa.valor}" /></td>
									<td><select
										name="ordenpracticaListEdit[<%=index%>].estado"
										id="ordenpracticaListEdit[<%=index%>].estado"
										style="width: 70%; margin-bottom: 0px">
											<option value="NONE">Seleccione Estado ...</option>
											<option value="AUTORIZACION DIRECTA">AUTORIZACION
												DIRECTA</option>
											<option value="AUTORIZADA POR AFILIACIONES">AUTORIZADA
												POR AFILIACIONES</option>	
											<option value="AUTORIZADA POR AUDITORIA">AUTORIZADA
												POR AUDITORIA</option>
											
											<option value="PENDIENTE AFILIACIONES">PENDIENTE
												AFILIACIONES</option>
											<option value="PENDIENTE AUDITORIA">PENDIENTE
												AUDITORIA</option>
												
											<option value="RECHAZADA">RECHAZADA</option>
											<option value="RECHAZADA POR AFILIACIONES">RECHAZADA
												POR AFILIACIONES</option>											
											<option value="RECHAZADA POR AUDITORIA">RECHAZADA
												POR AUDITORIA</option>
											
											<option value="ANULADO">ANULADO</option>
									</select> <script>
				document.getElementById('ordenpracticaListEdit[<%=index%>].estado').value ='${pa.estado}'; 
				</script></td>

									<td align="left">
										<div style="visibility: hidden; height: 0px;">
											<input type="text"
												name="ordenpracticaListEdit[<%=index%>].autorizarAutomatico"
												id="ordenpracticaListEdit[<%=index%>].autorizarAutomatico"
												value="${pa.autorizarAutomatico}" class="date" />
										</div>

										<div id="calendar">
											<div class="input-group registration-date-time"
												style="padding-top: 0%;">
												<input class="form-control"
													name="autorizar_automatico_<%=index%>"
													id="autorizar_automatico_<%=index%>" type="date"
													value="${pa.autorizarAutomatico}"
													onchange="javascript:updateDate(<%=index%>);">
											</div>
										</div>

									</td>
									<td><button type='button' class='btn btn-link'
											onClick='Eliminar(this.parentNode.parentNode.rowIndex)'>Eliminar</button></td>
									<td><input type="checkbox" disabled="disabled"
										name="ordenpracticaListEdit[<%=index%>].imprimir"></td>
									<%
									  index++;
									%>
								</tr>
							</c:forEach>
						</thead>
					</table>
				</div>
			</td>
		</tr>
	</table>
</div>


<div class="modal fade" id="nuevoCodigo">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span><span class="sr-only"></span>
				</button>
				<h4 class="modal-title">Nuevo Código Nomenclador</h4>
			</div>
			<div class="modal-body">
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span9">
							<div class="formLabel">
								<form:label path="">Tipo:</form:label>
							</div>
							<div class="formInput">
								<form:select path="" id="nomenclador_tipo"
									style="width:83%; margin-bottom:0px">
									<form:option value="NONE" label="Seleccione Tipo ..." />
									<form:options items="${listNomencladorTipo}" itemLabel="nombre"
										itemValue="nombre" />
								</form:select>
							</div>
						</div>
					</div>
					<br>
					<div class="row-fluid">
						<div class="span9">
							<div class="formLabel">
								<form:label path="">C&oacute;digo:</form:label>
							</div>

							<div class="formInput">
								<input type="number" id="nomenclador_codigo"
									style="height: 20px; width: 40%; margin-top: 2px"
									placeholder="C&oacute;digo">
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span9">
							<div class="formLabel">
								<form:label path="">Nombre:</form:label>
							</div>

							<div class="formInput">
								<input type="text" id="nomenclador_nombre"
									style="height: 20px; width: 100%; margin-top: 2px"
									placeholder="Nombre del Nomenclador">
							</div>
						</div>
					</div>

				</div>
				</div>
		</div>
		
		<div class="modal-footer">
			<button type="button" id="btnSaveCodigo" class="btn btn-info">Guardar</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->

<script>
    $("#tb_practicas tr:gt(0)").each(function () {
	var isOdon;
        var this_row = $(this);
        var str1 = $.trim(this_row.find('td:eq(0)').html());//td:eq(0) means first td of this row
        var str2="ODON";
	if(str1.toUpperCase().indexOf(str2) != -1){
		isOdon=true;
	}
	if(isOdon!=true){
		this_row.find('td:eq(1)').html('');
	}
    });

	$(function() {
		  $('#btnNuevoCodigo').click(function() {
			 // document.getElementById("localidadNombre").value="";
		    $('#nuevoCodigo').modal('show');
		  });
		  
		  $('#btnSaveCodigo').click(function() {
		   // var localidad = document.getElementById("localidadNombre").value;
		   // var resp = callNuevaLocalidad(getJsonLocalidad(localidad));
		   nuevoNomencladorOk();
		    $('#nuevoCodigo').modal('hide');
		  });
		});
    
</script>