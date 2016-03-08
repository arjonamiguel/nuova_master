<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="es">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Nuova</title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/img/favicon/favicon.ico">
	
	<link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
	<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
	<script src="<%=request.getContextPath()%>/resources/js/jquery/bootstrap-collapse.js" />"></script>
	<link href="<%=request.getContextPath()%>/resources/css/nuova.css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/resources/css/panel.css" rel="stylesheet"/>
	<link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap-responsive.css" rel="stylesheet"/>
	
	
	<script>

	var eventos=${eventos};
		

		function drawTable(data) {
		    for (var i = 0; i < data.length; i++) {
		        drawRow(data[i]);
		    }
		}
		
		function drawRow(rowData) {
		    var row = $("<tr />")
		    $("#calendarios").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
		    row.append($("<td>" + rowData.id + "</td>"));
		    row.append($("<td>" + rowData.resourceId + "</td>"));
		    row.append($("<td>" + rowData.start + "</td>"));
		    row.append($("<td>" + rowData.end + "</td>"));
		    row.append($("<td>" + rowData.title + "</td>"));
		    row.append($("<td><a class='btn btn-danger btn-xs' href='/nuova/formDeleteCalendario/" + rowData.id + "'><span class='icon icon-remove'></span>eliminar</a></td>"));
		   
		}


	</script>
	<style>



	#script-warning {
		display: none;
		background: #eee;
		border-bottom: 1px solid #ddd;
		padding: 0 10px;
		line-height: 40px;
		text-align: center;
		font-weight: bold;
		font-size: 12px;
		color: red;
	}

	#loading {
		display: none;
		position: absolute;
		top: 10px;
		right: 10px;
	}

	#calendar {
		max-width: 100%;
		margin: 50px auto;
	}

</style>
</head>

<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<jsp:include page="../breadcrumb.jsp"></jsp:include>

<div class="mainContainer"> 	
	<div class="panelContainer">		
		<div class="panel panel-info">
			<div class="panel-heading">
          			<div class="panel-title">
	          			Calendario
	           			<a href="#" class="pull-right"><b></b>&nbsp;&nbsp;Refrescar</a>
          			</div>
    		</div>     
			<div  class="panel-body" >
				<div class="container-fluid" >
	  				<div class="row-fluid" >
		    				<div class="span12">
								<table id="calendarios" class="table">
							    <tr>
							        <th>Id</th>
							        <th>resouceId</th>
							        <th>start</th>
							        <th>end</th>
							        <th>title</th>
							    </tr>
							    
							</table>
		    				</div>
		    		</div>
		    			<div class="row-fluid">
		<div class="span8">
		</div>
		<div class="span4">
		
		</div>
	</div>
		    	</div>
	    	</div>
	    </div>
</div>
	
</div>	 
</body>
</html>
<script>
	drawTable(eventos);
</script>