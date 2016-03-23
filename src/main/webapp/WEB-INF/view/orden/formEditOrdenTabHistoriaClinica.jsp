<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
<a href="#" class="pull-left" onclick="javascript:addRowHistoriaClinica('tabla_historiaclinica')">
<img src="<%=request.getContextPath()%>/resources/img/atach16x16.png" alt="Nuevo Archivo">
Adjuntar Nuevo Archivo</a>
</div>
<br><br>
<% int index = 0;%>

<table class="table"  style="width: 100%" height="100%" id="tabla_historiaclinica">			
<c:forEach items="${ordenDto.historiasclinicas}" var="hc" varStatus="loop" >
<tr>
<td style="width: 40%">	
	<input type="hidden" value="${hc.documentId}" name="historiasclinicas[<%=index%>].documentId"> 
	${hc.fileName}<br>
</td>
<td style="width: 10%">
<a href="#">
  <img src="<%=request.getContextPath()%>/resources/img/download16x16.png" alt="Descargar">
  Descargar
</a>
</td>
<td>
<a href="#" onClick='eliminarHC(this.parentNode.parentNode.rowIndex)'>Eliminar</a>
</td>
</tr>
<%index++;%>
</c:forEach>
</table>