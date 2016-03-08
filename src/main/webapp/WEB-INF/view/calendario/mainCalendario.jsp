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
	
	<link href='<%=request.getContextPath()%>/resources/fullcalendar-2.6.0/fullcalendar.css' rel='stylesheet' />
	<link href='<%=request.getContextPath()%>/resources/fullcalendar-2.6.0/fullcalendar.print.css' rel='stylesheet' media='print'/>
	<link href="<%=request.getContextPath()%>/resources/fullcalendar-2.6.0/scheduler.min.css" rel="stylesheet">
	<script src='<%=request.getContextPath()%>/resources/fullcalendar-2.6.0/lib/moment.min.js'></script>
	<script src='<%=request.getContextPath()%>/resources/fullcalendar-2.6.0/lib/jquery.min.js'></script>
	<script src='<%=request.getContextPath()%>/resources/fullcalendar-2.6.0/fullcalendar.min.js'></script>
	<script src="<%=request.getContextPath()%>/resources/fullcalendar-2.6.0/scheduler.min.js"></script>
	<script src='<%=request.getContextPath()%>/resources/fullcalendar-2.6.0/lang/es.js'></script>
	
	<script>
	var currentDate='2016-01-07';
	var fistTimeFlag=1;
	var eventosOut=[];
	var eventos=${eventos};
		
	function calendar_event_clicked(cal_event, js_event, view){	
		for (var i = 0; i < eventosOut.length; i++) {
    		if (eventosOut[i].id === cal_event.id) {
    			fistTimeFlag=0;
	 			eventosOut[i]=cal_event;
    		}
  		}
  		if(fistTimeFlag==1){
  			eventosOut.push(cal_event);
  		}
	}

	function simpleStringify (object){
		var container="["
	    var simpleObject = "";
	    var returnString="";
	    for (var prop in object ){
	        if (!object.hasOwnProperty(prop)){
	            continue;
	        }
	        if (typeof(object[prop]) == 'object'){
	        
	        	//{ id: '1', resourceId: 'b', start: '2016-01-07T02:00:00', end: '2016-01-07T07:00:00', title: 'Dr. Lizarraga' },
	        	simpleObject=simpleObject+"{ id:" + object[prop].id + ", resourceId: '" + object[prop].resourceId + "', start: '" + object[prop].start._d + "', end: '" + object[prop].end._d + " ', title: '" + object[prop].title + "'}";
	        	if(prop<object.length-1)
	        	{
	        		simpleObject= simpleObject + ",";
	        	}  
	            continue;
	        }
	    }
	    returnString = container + simpleObject + "]";
	    
		return returnString; 
	};
	
	function sendingInfo(){
		alert(simpleStringify(eventosOut));
	}
	
	var myVar = setInterval(function(){ myTimer() }, 1000);

	function myTimer() {
		$(".fc-license-message").hide();
	}
	
	var currentDate='';
	
		function getDate(){
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1; //January is 0!
		var yyyy = today.getFullYear();

		if(dd<10) {
		    dd='0'+dd
		} 
		
		if(mm<10) {
		    mm='0'+mm
		} 
		
		return yyyy +'-'+ mm+'-'+dd;
	}
	

		$(function() { // dom ready
			currentDate=getDate();

			$('#calendar').fullCalendar({
				resourceAreaWidth: 230,
				defaultDate: currentDate,
				editable: true,
				aspectRatio: 1.5,
				scrollTime: '00:00',
				header: {
					left: 'promptResource today prev,next',
					center: 'title',
					right: ''
				},

				defaultView: 'month',

				resourceLabelText: 'Consultorios',
				resources: [
					{ id: 'a', title: 'Consultorio A', eventColor: 'red' },
					{ id: 'b', title: 'Consultorio B', eventColor: 'green' },
					{ id: 'c', title: 'Consultorio C', eventColor: 'orange' },
					{ id: 'd', title: 'Consultorio D', children: [
						{ id: 'd1', title: 'Sala D1' },
						{ id: 'd2', title: 'Sala D2' }
					] },
					{ id: 'e', title: 'Consultorio E' },
					{ id: 'f', title: 'Consultorio F', eventColor: 'red' },
					{ id: 'g', title: 'Consultorio G' },
					{ id: 'h', title: 'Consultorio H' },
					{ id: 'i', title: 'Consultorio I' },
					{ id: 'j', title: 'Consultorio J' },
					{ id: 'k', title: 'Consultorio K' },
					{ id: 'l', title: 'Consultorio L' },
					{ id: 'm', title: 'Consultorio M' },
					{ id: 'n', title: 'Consultorio N' },
					{ id: 'o', title: 'Consultorio O' },
					{ id: 'p', title: 'Consultorio P' },
					{ id: 'q', title: 'Consultorio Q' },
					{ id: 'r', title: 'Consultorio R' },
					{ id: 's', title: 'Consultorio S' },
					{ id: 't', title: 'Consultorio T' },
					{ id: 'u', title: 'Consultorio U' },
					{ id: 'v', title: 'Consultorio V' },
					{ id: 'w', title: 'Consultorio W' },
					{ id: 'x', title: 'Consultorio X' },
					{ id: 'y', title: 'Consultorio Y' },
					{ id: 'z', title: 'Consultorio Z' }
				],
				events:eventos,
				eventAfterRender: function(cal_event, js_event, view){calendar_event_clicked(cal_event, js_event, view);}
			});

		});

		// readjust sizing after font load
		$(window).on('load', function() {
			$('#calendar').fullCalendar('render');
			$(".fc-license-message").hide();
		});

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
		    					<input id="txt_current_event" type="hidden" value="" />
		    					<div id="script-warning">
									This page should be running from a webserver, to allow fetching from the <code>json/</code> directory.
								</div>
								<div id="loading">loading...</div>
								<div id="calendar"></div>
		    				</div>
		    		</div>
		    			<div class="row-fluid">
		<div class="span8">
		</div>
		<div class="span4">
		<div style="float:right;"><input type="button" value="Cancelar" onclick="location.href='/nuova/mainProfesional';" class="btn"/></div>
			<div style="float:right;padding-right:2%;"><input type="submit" value="Guardar" class="btn btn-info" onclick="sendingInfo()"/></div> 
	 		
		</div>
	</div>
		    	</div>
	    	</div>
	    </div>
</div>
	
</div>	 
</body>
</html>
