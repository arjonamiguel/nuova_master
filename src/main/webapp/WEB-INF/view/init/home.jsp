<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Nuova</title>
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap-responsive.css" rel="stylesheet"/>
		<script src="<%=request.getContextPath()%>/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
		<script src="<%=request.getContextPath()%>/resources/js/bootstrap/bootstrap.min.js" />"></script>
		<script src="<%=request.getContextPath()%>/resources/js/jquery/bootstrap-collapse.js" />"></script>
		<link href="<%=request.getContextPath()%>/resources/css/nuova.css" rel="stylesheet"/>
		<style>
		    .navbar-brand-centered {
        position: absolute;
        left: 50%;
        display: block;
        width: 160px;
        text-align: center;
        background-color: transparent;
    }
    .navbar>.container .navbar-brand-centered, 
    .navbar>.container-fluid .navbar-brand-centered {
        margin-left: -80px;
    }
		</style>
</head>
<body style="background-color:#eee;">
	<jsp:include page="../sec_menu.jsp"></jsp:include>
	<jsp:include page="../breadcrumb.jsp"></jsp:include>
</body>
</html>