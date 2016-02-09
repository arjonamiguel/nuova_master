<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<meta charset="utf-8">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Nuova</title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/img/favicon/favicon.ico">

<link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
<script src="<c:url value="/resources/js/jquery/jquery-2.0.3.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap/bootstrap.min.js" />"></script>
<style>
.card-container.card {
    max-width: 350px;
    padding: 40px 40px;
    
}

/*
 * Card
 */
.card {
    background-color: white;
    /* just in case there no content*/
    padding: 20px 25px 30px;
    margin: 0 auto 25px;
    margin-top: 280px;
    /* shadows and rounded borders */
    -moz-border-radius: 2px;
    -webkit-border-radius: 2px;
    border-radius: 2px;
    -moz-box-shadow: 0px 2px 14px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: 0px 14px 12px rgba(0, 0, 0, 0.3);
    box-shadow: 0px 2px 14px rgba(0, 0, 0, 0.3);
}

.form-signin #inputEmail,
.form-signin #inputPassword {
    direction: ltr;
    height: 44px;
    font-size: 16px;
}

.form-signin input[type=email],
.form-signin input[type=password],
.form-signin input[type=text],
.form-signin button {
    width: 100%;
    display: block;
    margin-bottom: 10px;
    z-index: 1;
    position: relative;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}

.form-signin .form-control:focus {
    border-color: rgb(104, 145, 162);
    outline: 0;
    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgb(104, 145, 162);
    box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgb(104, 145, 162);
}
</style>

</head>
	<body style="background-color: #F7F7F7">
	
		<div class="container">
		<div style="float:left;padding-top:10%;;padding-left:22%;">
		<center>
		<img src="<%=request.getContextPath()%>/resources/img/others/nuova3.png" height="20%" width="20%">
		</center>
		</div>
		<div style="float:left;padding-top:1%;padding-left:38%;">
		<h3>Sign in to NUOVA</h3>
		</div>
		
		
		<div class="card card-container">
		<form class="form-signin" name="f" action="<c:url value='j_spring_security_check'/>"
					method="POST">
					<input id="inputEmail" class="form-control" placeholder="email"  type="email" name='j_username' required autofocus>
					<input  id="inputPassword" class="form-control" type='password' name='j_password'  placeholder="password" require>
					<input class="btn btn-lg btn-success btn-block btn-signin" name="submit" type="submit" style="height:40px;">	
		</form>
		</div>
		</div>
	</body>
</html>