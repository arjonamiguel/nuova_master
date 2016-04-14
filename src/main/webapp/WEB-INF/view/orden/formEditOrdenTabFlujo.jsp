<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<table class="table"  style="width: 100%">
<tr style="border-left: 2px solid #31708f;background-color:#f5f5f5;">
	<td style="width: 40%">
		<b>${userNameLogged}</b>
	</td>
	<td align="left">
		<form:select path="estado" style="width:30%; margin-bottom:0px">
		   <form:option value="NONE" label="Seleccione Estado ..."/>
		   <form:options items="${ordenEstadosList}" itemLabel="value" itemValue="id" />			    
		</form:select>
	</td>
</tr>
</table>
<br>
<div class="tab-content" style="height: 400px">	
<c:forEach items="${ordenDto.ordenWorkflows}" var="ow" varStatus="loop" >			
	<table class="table"  style="width: 100%">	
	<tr style="border-left: 2px solid orange;">
		<td style="width: 40%">
			<b>${ow.userName}</b><br>
			<span style="font-size: 12px">${ow.fecha}</span>
		</td>
		<td align="left">
			<c:if test="${loop.index < 1}">
				<span style="color: orange; font-weight: bold">${ow.estado}</span>
			</c:if>
			<c:if test="${loop.index  > 0}">
				${ow.estado}
			</c:if>
		</td>
	</tr>
	</table>
</c:forEach>
</div>