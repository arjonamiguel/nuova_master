<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>   
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Nuova</title>
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
		<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
		<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
		<style>
		.rowContainerProf{
			float:left;
			width:50%;
			height:10%;
			padding-left:0px;
		}
		.labelProf{
		float:left;
		padding-right:2%;
		padding-left:5%;
		}
		.inputProf{
		float:right;
		padding-top:0%;
		}
		fieldset.scheduler-border {
		    border: 1px groove #ddd !important;
		    padding: 0 1.4em 1.4em 1.4em !important;
		    margin: 0 0 1.5em 0 !important;
		    -webkit-box-shadow:  0px 0px 0px 0px #000;
		            box-shadow:  0px 0px 0px 0px #000;
		}

		legend.scheduler-border {
		    font-size: 1.2em !important;
		    font-weight: bold !important;
		    text-align: left !important;
		    width:inherit; /* Or auto */
    		padding:0 10px; /* To give a bit of padding on the left and right */
    		border-bottom:none;
		}
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
<body>
<jsp:include page="../sec_menu.jsp"></jsp:include>
<h3>Nuevo Profesional</h3>

<form:form method="post" action="/nuova/addProfesional" commandName="profesional">
<div class="row" style="padding-left:5%;padding-right:5%;width:45%;float:left;padding-top:3%;">
 <fieldset class="scheduler-border">
 <legend class="scheduler-border">Datos del Profesional</legend>
    	<div><form:hidden path="profesionalId" /></div>
        <div class="rowContainerProf">
        	<div class="labelProf"><form:label path="apellido">Apellido:</form:label></div>
		    <div class="inputProf"><form:input path="apellido" style="width:90%;float:left;" /></div>
        </div>
        <div class="rowContainerProf">
        	<div class="labelProf"><form:label path="nombre">Nombre:</form:label></div>
        	<div class="inputProf"><form:input path="nombre" style="width:90%;float:left;"/></div>
        </div>
        <div class="rowContainerProf">
	        <div class="labelProf"><form:label path="telefono">Telefono:</form:label></div>
	        <div class="inputProf"><form:input path="telefono" style="width:90%;float:left;"/></div>
        </div>
        <div class="rowContainerProf">
	        <div class="labelProf"><form:label path="matricula">Matricula:</form:label></div>
	        <div class="inputProf"><form:input path="matricula" style="width:90%;float:left;"/></div>
        </div>
        <div class="rowContainerProf">
	        <div class="labelProf"><form:label path="registroNacional">Registro Nacional:</form:label></div>
	        <div class="inputProf"><form:input path="registroNacional" style="width:90%;float:left;"/></div>
        </div>
        <div class="rowContainerProf">
	        <div class="labelProf"><form:label path="tituloProfesional">Titulo Profesional:</form:label></div>
	        <div class="inputProf"><form:input path="tituloProfesional" style="width:90%;float:left;"/></div>
        </div>
        <div style="padding-top:5%;float:left;padding-left:2%;width:100%;">
	        <div class="rowContainerProf" style="width:30%;padding-top:1%;">
		        <div style="visibility:hidden;height:0px;"><form:label path="habilitacionSiprosa">Habilitacion del Siprosa:</form:label></div>
		        <div style="visibility:hidden;height:0px;"><form:input path="habilitacionSiprosa" /></div>
		        <label for="info" class="btn btn-info">SIPROSA <input type="checkbox" id="info" class="badgebox" onchange="javascript:updateSiprosa();"><span class="badge">&check;</span></label>  
	        </div>
        
	        <div id="labelDate" style="padding-top:2%;float:left;visibility:hidden;"><form:label path="fechaVencimientoHabilitacion">Fecha Vencimiento Habilitacion:</form:label></div>
	        <div style="visibility:hidden;height:0px;"><form:input class="date" path="fechaVencimientoHabilitacion" /></div>
	        <div id="calendar" style="float:right;visibility:hidden;" class="btn btn-info">
	            <div class="input-group registration-date-time" style="padding-top:5%;">
            		<span class="input-group-addon" id="basic-addon1"><span class="icon icon-calendar" aria-hidden="true"></span></span>
            		<input class="form-control" name="registration_date" id="registration-date" type="date" style="width:82%;" onchange="javascript:updateDate();">
            	</div>
            </div>
        </div>
        
        
    
        
        
        </fieldset>
</div>
<div class="row" style="float:none;padding-left:5%;padding-right:15%;padding-top:3%;width:45%;">
<fieldset class="scheduler-border">
<legend class="scheduler-border">Agregar Especialidades</legend>
    <div>   
    	<div style="visible:hidden;height:0px;"><form:label path="especialidad">Especialidad:</form:label></div>
    	<div>
	    	<form:select path="especialidad">
			   <form:option value="NONE" label="Seleccione Especialidad ..."/>
			   <form:options items="${especialidadList}" itemLabel="nombre" itemValue="especialidadId" />			    
			</form:select>
		</div>	
		<div class="inputProf">
			<INPUT type="button" value="Add Row" onclick="addRow('dataTable')" class="btn btn-primary"/>
		</div>	
	</div>
    <div>  
	    <TABLE id="dataTable" class="table table-striped custab">
	        <TR>
	        	<TD></TD>
	            <TD>Id</TD>
	            <TD>Especialidad</TD>        
	        </TR>
	    </TABLE>
	   	<div class="inputProf">
	    	<INPUT type="button" value="Delete Row" onclick="deleteRow('dataTable')" class="btn"/>
	    </div>
 	</div>
</fieldset>
<div style="float:right;">
	 <input type="submit" value="Guardar" class="btn btn-primary"/> 
	 <input type="button" value="Cancelar" onclick="location.href='/nuova/mainProfesional';" class="btn"/>
</div>
</div>
</form:form>


</body>
</html>

<script>
        document.getElementById("habilitacionSiprosa").value=0;
        document.getElementById("fechaVencimientoHabilitacion").value='';
</script>