<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
				<div>
				<div class="">
				
				<div class="">													
														
				<input type="hidden" name="nomencladorId" id="nomencladorId" value="">										
					<input
						data-provide="typeahead" 
						class="typeahead"
						name="ctl00$ContainerGeneralOverWrite$ContainerGeneral$sq"
						type="text"
						id="ContainerGeneralOverWrite_ContainerGeneral_sq"
						placeholder="Búsque en el Nomenclador por Codigo o Nombre..."
						autocomplete="off"
						style="height: 20px; width: 40%;margin-bottom:0px">
		
		<INPUT type="button" value="Agregar" onclick="addRow('tb_practicas')" class="btn btn-info"/>
			</div>
				
					    	
				
			</div>	
		</div>
			
		 	<table class="scroll"  style="width: 100%" height="100%">			
		<tr>
		     	<td colspan="2">										           	
		 		<div>		    
		    <TABLE id="tb_practicas" class="table" style="width: 100%; margin: 2% 0;" >
		<thead>
		    <TR>													        	
		        <TD style="width: 50%"><b>Nomenclador</b></TD>
		<TD style="width: 40%"><b>Estados</b></TD>
		    <td></td>
		</TR>
		<% int index = 0;%>
		<c:forEach items="${ordenDto.practicasListEdit}" var="pa" varStatus="loop" >
		<tr>
		    
		    <td>
		    <input type="hidden" name = "ordenpracticaListEdit[<%=index%>].orddenPracticaId" value = "${pa.orddenPracticaId}" />
		<input type="hidden" name = "ordenpracticaListEdit[<%=index%>].practicaId" value = "${pa.practicaId}" />
		${pa.nombre}
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
		      <td><button type='button' class='btn btn-link' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'>eliminar</button></td>
		<%index++;%>
		</tr>
		</c:forEach>
			    </thead>
			   
			    </TABLE>
		  	
				</div>    
				
		      	</td>
		  	</tr>
			
		</table>