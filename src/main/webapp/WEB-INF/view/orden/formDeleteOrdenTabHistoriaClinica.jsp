<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div></div>
<br>
<br>

<div class="tab-content" style="height: 400px">
	<%
	  int index = 0;
	%>

	<table class="table" style="width: 100%" id="tabla_historiaclinica">
		<c:forEach items="${ordenDto.historiasclinicas}" var="hc"
			varStatus="loop">
			<tr>
				<td style="width: 40%"><input type="hidden"
					value="${hc.documentId}"
					name="historiasclinicas[<%=index%>].documentId">
					${hc.fileName}<br></td>
				<td style="width: 10%"><a
					href="javascript:window.open('/nuova/download-document/${hc.documentId}','NUOVA - ${hc.fileName}','width=500,height=150')">
						<img
						src="<%=request.getContextPath()%>/resources/img/download16x16.png"
						alt="Descargar"> Descargar
				</a></td>
				<td>
				</td>
			</tr>
			<%index++;%>
		</c:forEach>
	</table>
</div>