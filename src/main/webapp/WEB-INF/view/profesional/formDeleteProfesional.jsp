<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>   
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Nuova</title>
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap-responsive.css" rel="stylesheet"/>
		<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
		<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
		<script src="<%=request.getContextPath()%>/resources/js/jquery/bootstrap-collapse.js" />"></script>
		<link href="<%=request.getContextPath()%>/resources/css/nuova.css" rel="stylesheet"/>
		<link href="<%=request.getContextPath()%>/resources/css/panel.css" rel="stylesheet"/>

		<style>
		
		
		.btn-info {
    color: #fff;
    background-color: #5bc0de;
    border-color: #46b8da;
}
/* Hiding the checkbox, but allowing it to be focused */
.badgebox
{
    opacity: 0;
}

.badgebox + .badge
{
    /* Move the check mark away when unchecked */
    text-indent: -999999px;
    /* Makes the badge's width stay the same checked and unchecked */
	width: 27px;
}

.badgebox:focus + .badge
{
    /* Set something to make the badge looks focused */
    /* This really depends on the application, in my case it was: */
    
    /* Adding a light border */
    box-shadow: inset 0px 0px 5px;
    /* Taking the difference out of the padding */
}

.badgebox:checked + .badge
{
    /* Move the check mark back when checked */
	text-indent: 0;
}
.label, .badge{
	background-color:#E0F8F7;
	color:black;
}
.badge{
	box-shadow: inset 0px 0px 5px;
}

fieldset {
-webkit-border-radius: 8px;
-moz-border-radius: 8px;
border-radius: 8px;
}
		
		</style>
        <SCRIPT language="javascript">
        var siprosa=0;

        
        function addRow(tableID) {
 
            var table = document.getElementById(tableID);
 
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);
 
            var cell1 = row.insertCell(0);
            var element1 = document.createElement("input");
            element1.type = "checkbox";
            element1.name="chkbox[]";
            cell1.appendChild(element1);
 
            var cell2 = row.insertCell(1);                      
            cell2.innerHTML = document.getElementById("especialidad").value+"<input type='hidden' name='especialidadList' value='"+document.getElementById("especialidad").value+"'>";
            var cell3 = row.insertCell(2);
            cell3.innerHTML = document.getElementById('especialidad').options[document.getElementById('especialidad').selectedIndex].text; 
 
        }
 
        function deleteRow(tableID) {
            try {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
 
            for(var i=0; i<rowCount; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                if(null != chkbox && true == chkbox.checked) {
                    table.deleteRow(i);
                    rowCount--;
                    i--;
                }
 
 
            }
            }catch(e) {
                alert(e);
            }
        }

		function updateSiprosa(){
			if(siprosa==0){
				siprosa=1;
				document.getElementById("habilitacionSiprosa").value=1;
				document.getElementById("labelDate").style.visibility='visible';
				document.getElementById("calendar").style.visibility='visible';
			}else{
				siprosa=0;
				document.getElementById("habilitacionSiprosa").value=0;
				document.getElementById("labelDate").style.visibility='hidden';
				document.getElementById("calendar").style.visibility='hidden';
			}
		}
		function updateDate(){
			document.getElementById("fechaVencimientoHabilitacion").value=document.getElementById("registration-date").value;
		}
    </SCRIPT>
</head>
<body style="background-color:#eee;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<jsp:include page="../breadcrumb.jsp"></jsp:include>
<div class="mainContainer"> 
<div class="textTitle">  
<h3>Eliminar Profesional</h3>
</div>	
<div style="padding-left:2%;">
<form:form method="post" action="/nuova/deleteProfesional" commandName="profesional">
<div class="panel panel-info" style="width:40%;">
	<div class="panel-heading">
          <div class="panel-title">Datos del Profesional</div>
    </div>     
	<div style="padding-top:30px" class="panel-body" >
    	<div><form:hidden path="profesionalId" /></div>
     		<div>
        	<div class="formLabel"><form:label path="apellido">Apellido:</form:label></div>
        	<div class="formInput"><form:input path="apellido" placeholder="Apellido" disabled="true"/></div>
			</div>
			<div>
        	<div class="formLabel"><form:label path="nombre">Nombre:</form:label></div>
        	<div class="formInput"><form:input path="nombre" placeholder="Nombre" disabled="true"/></div>
      		</div>
      		<div>
	        <div class="formLabel"><form:label path="telefono">Telefono:</form:label></div>
	        <div class="formInput"><form:input path="telefono" placeholder="Telefono" disabled="true"/></div>
      		</div>
  			<div>
	        <div class="formLabel"><form:label path="matricula">Matricula:</form:label></div>
	        <div class="formInput"><form:input path="matricula" placeholder="Matricula" disabled="true"/></div>
      		</div>
   			<div>
	        <div class="formLabel"><form:label path="registroNacional">Registro Nacional:</form:label></div>
	        <div class="formInput"><form:input path="registroNacional" placeholder="Registro Nacional" disabled="true"/></div>
      		</div>
      		<div>
	        <div class="formLabel"><form:label path="tituloProfesional">Titulo Profesional:</form:label></div>
	        <div class="formInput"><form:input path="tituloProfesional" placeholder="Titulo Profesional" disabled="true"/></div>
        	</div>
        
        	<div class="siprosaDiv">
			        <div style="visibility:hidden;height:0px;"><form:label path="habilitacionSiprosa">Habilitacion del Siprosa:</form:label></div>
			        <div style="visibility:hidden;height:0px;"><form:input path="habilitacionSiprosa" /></div>
			        <label for="info">SIPROSA <input type="checkbox" id="info" class="badgebox" onchange="javascript:updateSiprosa();"><span class="badge">&check;</span></label>  
		        </div>
	        
		        <div id="labelDate" class="labelDate"><form:label path="fechaVencimientoHabilitacion">Fecha Vencimiento Habilitacion:</form:label></div>
		        <div style="visibility:hidden;height:0px;"><form:input class="date" path="fechaVencimientoHabilitacion" /></div>
		        <div id="calendar" style="visibility:hidden;">
		            <div class="input-group registration-date-time" style="padding-top:0%;">
	            		
	            		<input class="form-control" name="registration_date" id="registration-date" type="date"  onchange="javascript:updateDate();">
	            	</div>
	            </div>
        
     </div>   
    
        
</div>   

<div class="panel panel-info" style="width:40%;pointer-events:none;">
	<div class="panel-heading">
	          <div class="panel-title">Agregar Especialidades</div>
	</div>  
<div style="padding-top:30px" class="panel-body" >
    <div style="background-color:#f9f9f9;">   
    	<div style="visible:hidden;height:0px;"><form:label path="especialidad">Especialidad:</form:label></div>
    	<div>
	    	<form:select path="especialidad">
			   <form:option value="NONE" label="Seleccione Especialidad ..."/>
			   <form:options items="${especialidadList}" itemLabel="nombre" itemValue="especialidadId" />			    
			</form:select>
		</div>	
		<div class="inputProf">
			<INPUT type="button" value="Add Row" onclick="addRow('dataTable')" class="btn btn-info"/>
		</div>	
	</div>
    <div>  
	    <TABLE id="dataTable" class="table">
	        <TR>
	        	<TD></TD>
	            <TD>Id</TD>
	            <TD>Especialidad</TD>        
	        </TR>
	                <c:forEach items="${especialidadListEdit}" var="esp">
	    <tr>
	    	<td><input type="checkbox" name="chkbox[]"><input type="hidden" name="especialidadList" value="${esp.key}"></td>
	        <td>${esp.key} </td>
	        <td>${esp.value}</td>        
	    </tr>
	</c:forEach>
	    </TABLE>
	   	<div style="padding-left:79%;">
	    	<INPUT type="button" value="Delete Row" onclick="deleteRow('dataTable')" class="btn"/>
	    </div>
 	</div>
		        

</div>
</div>   

<div style="float:left;padding-left:27%;width:45%;padding-bottom:2%;">
	 <div style="float:left;"><input type="submit" value="Eliminar" class="btn btn-info"/></div> 
	 <div style="float:left;"><input type="button" value="Cancelar" onclick="location.href='/nuova/mainProfesional';" class="btn"/></div>
</div>

</form:form>
</div>
</div>
</div>
</body>
</html>

<script language="javascript">
       
        var isSiprosa=$("#habilitacionSiprosa").val();
        if(isSiprosa==1){
        	document.getElementById("registration-date").value=document.getElementById("fechaVencimientoHabilitacion").value;
			$(".badge").click();
			$("#calendar").css("pointer-events","none");
        }
</script>