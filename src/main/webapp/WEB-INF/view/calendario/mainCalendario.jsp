<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	<link href="<%=request.getContextPath()%>/resources/fullcalendar-2.6.0//scheduler.min.css" rel="stylesheet">
	<script src='<%=request.getContextPath()%>/resources/fullcalendar-2.6.0//lib/moment.min.js'></script>
	<script src='<%=request.getContextPath()%>/resources/fullcalendar-2.6.0//lib/jquery.min.js'></script>
	<script src='<%=request.getContextPath()%>/resources/fullcalendar-2.6.0//fullcalendar.min.js'></script>
	<script src="<%=request.getContextPath()%>/resources/fullcalendar-2.6.0//scheduler.min.js"></script>
	
	<script>

		$(function() { // dom ready

			$('#calendar').fullCalendar({
				resourceAreaWidth: 230,
				defaultDate: '2016-01-07',
				editable: true,
				aspectRatio: 1.5,
				scrollTime: '00:00',
				header: {
					left: 'promptResource today prev,next',
					center: 'title',
					right: 'timelineDay,timelineThreeDays,agendaWeek,month'
				},
				customButtons: {
					promptResource: {
						text: '+ room',
						click: function() {
							var title = prompt('Room name');
							if (title) {
								$('#calendar').fullCalendar(
									'addResource',
									{ title: title },
									true // scroll to the new resource?
								);
							}
						}
					}
				},
				defaultView: 'timelineDay',
				views: {
					timelineThreeDays: {
						type: 'timeline',
						duration: { days: 3 }
					}
				},
				resourceLabelText: 'Consultorios',
				resources: [
					{ id: 'a', title: 'Consultorio A' },
					{ id: 'b', title: 'Consultorio B', eventColor: 'green' },
					{ id: 'c', title: 'Consultorio C', eventColor: 'orange' },
					{ id: 'd', title: 'Consultorio D', children: [
						{ id: 'd1', title: 'Room D1' },
						{ id: 'd2', title: 'Room D2' }
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
				events: [
					{ id: '1', resourceId: 'b', start: '2016-01-07T02:00:00', end: '2016-01-07T07:00:00', title: 'Dr. Lizarraga' },
					{ id: '2', resourceId: 'c', start: '2016-01-07T05:00:00', end: '2016-01-07T22:00:00', title: 'Dr. Gordillo' },
					{ id: '3', resourceId: 'd', start: '2016-01-06', end: '2016-01-08', title: 'Dr Sotelo' },
					{ id: '4', resourceId: 'e', start: '2016-01-07T03:00:00', end: '2016-01-07T08:00:00', title: 'Dr Roldan' },
					{ id: '5', resourceId: 'f', start: '2016-01-07T00:30:00', end: '2016-01-07T02:30:00', title: 'Dr Brandan' }
				]
			});

		});

		// readjust sizing after font load
		$(window).on('load', function() {
			$('#calendar').fullCalendar('render');
			$(".fc-license-message").hide();
		});

	</script>
	<style>

	body {
		margin: 0;
		padding: 0;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		font-size: 14px;
	}

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
		max-width: 900px;
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
		    					<div id="script-warning">
									This page should be running from a webserver, to allow fetching from the <code>json/</code> directory.
								</div>
								<div id="loading">loading...</div>
								<div id="calendar"></div>
		    				</div>
		    		</div>
		    	</div>
	    	</div>
	    </div>
</div>
	
</div>	 
</body>
</html>