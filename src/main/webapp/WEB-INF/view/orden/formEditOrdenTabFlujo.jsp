<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:forEach items="${ordenDto.ordenWorkflows}" var="ow" varStatus="loop" >			
	<table class="table"  style="width: 100%">	
	<tr style="border-left: 2px solid orange;">
		<td style="width: 60%">
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