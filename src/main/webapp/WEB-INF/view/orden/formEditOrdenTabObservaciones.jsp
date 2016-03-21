<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
		<c:forEach items="${ordenDto.observacioneses}" var="obs" varStatus="loop" >
		<table class="table"  style="width: 100%;">	
		<tr style="background-color:#f5f5f5;">
			<td></td><td></td><td></td><td></td>
		</tr>
		<tr>
			<td style="background-color:#f5f5f5;border-top:none;"></td>
		<td style="width: 80%; padding: 1px 1px;border-left: 2px solid orange;">
		<b>${obs.userName}</b> <span style="font-size: 12px">${obs.fecha}</span>
		</td>
		<td style="padding: 1px 1px; text-align: right;">
		  		<a class="btn btn-link" href="#">
				<i class="icon-trash"></i>
			</a>
			<a class="btn btn-link" href="#">
				<i class="icon-pencil"></i>
			</a>
		</td>
		<td style="background-color:#f5f5f5;border-top:none;"></td>
		
		</tr>
		<tr>	
			<td style="background-color:#f5f5f5;border-top:none;"></td>    		
		<td align="justify" colspan="2" style="border-left: 2px solid orange;">${obs.observacion}</td>		  
		<td style="background-color:#f5f5f5;border-top:none;"></td>    
		</tr>
		<tr style="background-color:#f5f5f5;">
		<td style="border-top:none;"></td><td></td><td></td><td style="border-top:none;"></td>
		</tr>
		
		  	</table>
		  	</c:forEach>	