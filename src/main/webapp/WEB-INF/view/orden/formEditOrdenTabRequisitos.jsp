<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="tab-content" style="height: 250px">	
<table class="table" style="width: 100%">
<tr>
	<td colspan="4" style="width:60%">
		<b>Presentó la orden original del médico solicitante?</b>
	</td>
	<td  style="text-align:left" colspan="2">			    
		<form:checkbox path="reqOrdenMedico" class="checkbox"/>
	</td>
</tr>
<tr>
	<td colspan="4">
		<b>Presentó fotocopia de la credencial de la prestadora OSPSIP?</b>
	</td>
	<td  style="text-align:left" colspan="2">				    
		<form:checkbox path="reqCredecial" class="checkbox"/>
	</td>
</tr>
</table>

<table class="table" style="width: 100%">
<tr>			
	<td colspan="4" style="width:60%">
		<b>Presentó fotocopia de los 3 último recibos como Monotributista o Ama de Casa?</b>
	</td>
	<td  style="text-align:left" colspan="2">				    
		<form:checkbox path="reqMonotributista" class="checkbox"/>
	</td>
</tr>	
<tr>
	<td colspan="4">
		<b>Presentó fotocopia del último recibo de sueldo?</b>
	</td>
	<td  style="text-align:left" colspan="2">				    
		<form:checkbox path="reqReciboSueldo" class="checkbox"/>
	</td>
</tr>
</table>
</div>