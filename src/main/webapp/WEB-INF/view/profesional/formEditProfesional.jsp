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
		<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
		<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
		<script src="<%=request.getContextPath()%>/resources/js/jquery/bootstrap-collapse.js" />"></script>
		<link href="<%=request.getContextPath()%>/resources/css/nuova.css" rel="stylesheet"/>
		<link href="<%=request.getContextPath()%>/resources/css/panel.css" rel="stylesheet"/>
		<link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap-responsive.css" rel="stylesheet"/>

		<style>
		
		
		.btn-info {
    color: #fff;
    background-color: #5bc0de;
    border-color: #46b8da;
}
		
		</style>
        <SCRIPT language="javascript">
        var siprosa=0;
        
        function Eliminar (i) {    	 
    	    document.getElementById("dataTable").deleteRow(i);
    	}
        
       function addRow(tableID) {
 
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
            var row = table.insertRow(rowCount);

            var cell2 = row.insertCell(0);                      
            cell2.innerHTML = document.getElementById("especialidad").value+"<input type='hidden' name='especialidadList' value='"+document.getElementById("especialidad").value+"'>";
            var cell3 = row.insertCell(1);
            cell3.innerHTML = document.getElementById('especialidad').options[document.getElementById('especialidad').selectedIndex].text;
            
           	var cell4 = row.insertCell(2);
            cell4.innerHTML=  "<button type='button' class='btn btn-danger btn-xs' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'><span class='icon icon-remove' title='Eliminar'></span></button>";
 
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
<div class="panelContainer">
<form:form method="post" action="/nuova/editProfesional" commandName="profesional">
<div class="panel panel-info">
	<div class="panel-heading">
          <div class="panel-title">Editar Datos del Profesional</div>
    </div>     
	<div style="padding-top:30px" class="panel-body" >
	
<div class="container-fluid">
  <div class="row-fluid">
	    <div class="span4">
	      <div><form:hidden path="profesionalId" /></div>
        	<div class="formLabel"><form:label path="apellido">Apellido:</form:label></div>
        	<div class="formInput"><form:input path="apellido" placeholder="Apellido" /></div>
	    </div>
	    <div class="span4">
			<div class="formLabel"><form:label path="nombre">Nombre:</form:label></div>
        	<div class="formInput"><form:input path="nombre" placeholder="Nombre"/></div>
	    </div>
	    <div class="span4">
			<div class="formLabel"><form:label path="telefono">Telefono:</form:label></div>
	        <div class="formInput"><form:input path="telefono" placeholder="Telefono"/></div>
	    </div>
  </div>
    <div class="row-fluid">
	    <div class="span4">
			<div class="formLabel"><form:label path="matricula">Matricula:</form:label></div>
	        <div class="formInput"><form:input path="matricula" placeholder="Matricula"/></div>
	    </div>
	    <div class="span4">
	       <div class="formLabel"><form:label path="registroNacional">Registro Nacional:</form:label></div>
	       <div class="formInput"><form:input path="registroNacional" placeholder="Registro Nacional"/></div>
	    </div>
	    <div class="span4">
	     	<div class="formLabel"><form:label path="tituloProfesional">Titulo Profesional:</form:label></div>
	        <div class="formInput"><form:input path="tituloProfesional" placeholder="Titulo Profesional"/></div>
	    </div>
  </div>
    <div class="row-fluid">
	    <div class="span4"">
	    	<div style="visibility:hidden;height:0px;"><form:label path="habilitacionSiprosa">Habilitacion del Siprosa:</form:label></div>
			<div style="visibility:hidden;height:0px;"><form:input path="habilitacionSiprosa" /></div>
			<div>
				<div class="formLabel"><form:label path="fechaVencimientoHabilitacion">SIPROSA:</form:label></div>
				<div style="visibility:hidden;height:0px;"><form:input class="date" path="fechaVencimientoHabilitacion" /></div>
				<div class="formInput">
				<div id="calendar">
					<div class="input-group registration-date-time" style="padding-top:0%;">
						<input class="form-control" name="registration_date" id="registration-date" type="date"  onchange="javascript:updateDate();">
	            	</div>
	        	</div>  
	        	</div>
			</div>	
	    </div>
	    <div class="span4">
			
	    </div>
	    <div class="span4">

	    </div>
  </div>
</div>        
        
     </div>   
    
        
</div>   

<div class="panel panel-info">
	<div class="panel-heading">
	          <div class="panel-title">Editar Especialidades</div>
	</div>  
<div style="padding-top:30px" class="panel-body" >

	<div class="row-fluid">
			<div class="span9">
			</div>
			<div class="span2">
			 	<form:select path="especialidad">
				   <form:option value="NONE" label="Seleccione Especialidad ..."/>
				   <form:options items="${especialidadList}" itemLabel="nombre" itemValue="especialidadId" />			    
				</form:select>
			</div>
    		<div class="span1">
    			<div style="float:left;"><INPUT type="button" value="Agregar" onclick="addRow('dataTable')" class="btn btn-info"/></div>
    		</div>
    </div>
    <div class="tableContainer"> 

	    <TABLE id="dataTable" class="table"  style="margin-top:0px;">
	        <TR>
	        <TR>      	
	            <TD>Id</TD>
	            <TD>Especialidad</TD> 
	            <TD></TD>       
	        </TR>       
	        </TR>
	       	<c:forEach items="${especialidadListEdit}" var="esp">
		    <tr>

		        <td>${esp.key} </td>
		        <td>${esp.value}</td>       
		        <td>
		        <button type='button' class='btn btn-danger btn-xs' onClick='Eliminar(this.parentNode.parentNode.rowIndex)'><span class='icon icon-remove' title='Eliminar'></span></button>
		        </td> 
		    </tr>
	</c:forEach>
	    </TABLE>
	   	
 	</div>
	<div class="row-fluid">
		<div class="span8">
		</div>
		<div class="span4">
			<div style="float:right;padding-right:2%;"><input type="submit" value="Guardar" class="btn btn-info"/></div> 
	 		<div style="float:right;"><input type="button" value="Cancelar" onclick="location.href='/nuova/mainProfesional';" class="btn"/></div>
		</div>
	</div>	        

</div>
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
        }
</script>