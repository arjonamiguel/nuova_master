<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="tab-content" style="height: 400px">	
<table class="table" style="width: 100%">
<tr>
<td style="width:60%;"><div style="float:right;">Seleccionar todos:</div></td>
<td><div style="float:left;"><input id="selectAll" type="checkbox" class="largerCheckbox" onchange="javascript:marcarTodos()"></div></td>
</tr>
</table>
<table class="table" style="width: 100%">
<tr>
	<td colspan="4" style="width:60%">
		<b>Present� la orden de Pr�ctica del m�dico solicitante?</b>
	</td>
	<td  style="text-align:left" colspan="2">			    
		<form:checkbox path="reqOrdenMedico" type="checkbox" class="largerCheckbox"/>
	</td>
</tr>
<tr>
	<td colspan="4">
		<b>Present� la credencial de la prestadora OSPSIP?</b>
	</td>
	<td  style="text-align:left" colspan="2">				    
		<form:checkbox path="reqCredecial" type="checkbox" class="largerCheckbox"/>
	</td>
</tr>
</table>

<table class="table" style="width: 100%">
<tr>			
	<td colspan="4" style="width:60%">
		<b>Present� fotocopia de los 3 �ltimo recibos como Monotributista o Ama de Casa?</b>
	</td>
	<td  style="text-align:left" colspan="2">				    
		<form:checkbox path="reqMonotributista" type="checkbox" class="largerCheckbox"/>
	</td>
</tr>	
<tr>
	<td colspan="4">
		<b>Present� fotocopia del �ltimo recibo de sueldo?</b>
	</td>
	<td  style="text-align:left" colspan="2">				    
		<form:checkbox path="reqReciboSueldo" type="checkbox" class="largerCheckbox"/>
	</td>
</tr>
</table>
</div>