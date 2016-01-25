<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
	
    <title>Nuova</title>
            <link href="<%=request.getContextPath()%>/resources/css/pcan43e1.css" rel="stylesheet"/>
    
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/resources/css/bootstrap/bootstrap-responsive.css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/resources/css/panel.css" rel="stylesheet"/>
        
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
<body style="background-color:#e5e5e5;">
<jsp:include page="../sec_menu.jsp"></jsp:include>
<div class="mainContainer">   
	<div class="panelContainer">		
		<div class="panel panel-info">
		<div id="ContainerGeneralOverWrite_MainContainer" class="container">
                

    


    <div id="MainContainer" class="">

        <div class="home_ac_v2">

            <div class="bg_tooltips">
            </div>
            <div class="close_tooltips">
                <div class="container">
                    <div class="close">
                        <span>Cerrar</span>
                        <span class="icon_close">✕</span>
                    </div>
                </div>
            </div>

            <div class="block_cand_offers">
                <section class="user_info">
                    <div>
                        <div class="img">
                            <input type="file" name="ctl00$ContainerGeneralOverWrite$ContainerGeneral$uploadpic" id="ContainerGeneralOverWrite_ContainerGeneral_uploadpic" style="display: none">
                            <a href="#" id="uploadImage">
                                <div class="img_cand icon men" id="up-img">
                                    
                                    <span>
                                        <span class="icon camara"></span>
                                    </span>
                                </div>
                            </a>
                            <div class="cv_completed box_blue">
                                <div class="cv_100">
                                    <span class="cv_user_complete" style="width: 80px;"></span>
                                </div>
                                    <span class="txt">NUOVA</span>
                                
                            </div>
                        </div>
                        <div class="info">
                            <h1>${usuario.username}</h1>
                            <h2>Rol:</h2>
                            <a href="#">Visitanos en: www.nuova.com</a>
                            <a href="#" class="block_link">
                                <span class="icon clip"></span>
                                <span>Bienvenido a SYSNUOVA</span>
                            </a>
                        </div>
                    </div>
                </section>
                <div onkeypress="javascript:return WebForm_FireDefaultButton(event, 'ContainerGeneralOverWrite_ContainerGeneral_btnSearchOffers')">
	
                    <div class="buscador buscador_mvl">
                        <h2>Busqueda de Pacientes</h2>
                        <div class="input_text">
                            <span class="icon palabra_clave"></span>
                            <input name="ctl00$ContainerGeneralOverWrite$ContainerGeneral$sq" type="text" id="ContainerGeneralOverWrite_ContainerGeneral_sq" data-su="/ofertas-de-trabajo/" class="js-cargos-suggest ui-autocomplete-input" placeholder="Inicie la busqueda de pacientes ..." autocomplete="off">
                        </div>
                        
                        <input type="submit" name="ctl00$ContainerGeneralOverWrite$ContainerGeneral$btnSearchOffers" value="Buscar Paciente" id="ContainerGeneralOverWrite_ContainerGeneral_btnSearchOffers" class="submit_n">

                        <div class="box_tooltips ocultar" id="tooltip_search">
                            Busca ofertas directamente a través de este buscador
                            <span class="icon arrow"></span>
                        </div>
                    </div>
                
</div>
                <div class="offers_insc ">
                    <h3>Monitor de Practicas Realizadas</h3>
                    
                    <ul>
	                    <c:forEach items="${alarmas}" var="a">
					      <li class="offers_process"><b>(${a.cantidad})</b> - <span>${a.descripcion}</span></li>					      
					    </c:forEach>
                        <li class="offers_apl"><a href="/Candidate/Match/?st=1">Te has postulado en <span>3</span>  ofertas</a></li>
                        <li class="offers_see"><a href="/Candidate/Match/?st=2"><span>1</span> empresa ha visto tu curriculum</a></li>
                        <li class="offers_process"><span>Empresas que te están evaluando</span></li>
                        <li class="offers_fin"><span>Procesos de selección en  los que eres finalista</span></li>

                    </ul>
                    <a href="/nuova/mainOrden">Ir al Administrador de Practicas</a>
                    <div class="box_tooltips ocultar" id="tooltip_offers_insc">
                        Aquí podrás ver toda la información relacionada con tus postulaciones
                        <span class="icon arrow"></span>
                    </div>
                </div>

                <div class="offers_insc no_offers ocultar">
                    <h3>Resumen del estado de tus postulaciones</h3>
                    <span>En esta sección podrás ver toda la información relacionada con tus postulaciones</span>
                    <ul>
                        <li class="offers_apl"><span>Ofertas a las que te has postulado</span></li>
                        <li class="offers_see"><span>Empresas que han visto tu curriculum</span></li>
                        <li class="offers_process"><span>Empresas que te están evaluando</span></li>
                        <li class="offers_fin"><span>Procesos de selección en  los que eres finalista</span></li>
                    </ul>
                    <a href="/Candidate/Match/">Ver todas mis postulaciones</a>
                    <div class="box_tooltips ocultar" id="tooltip_offers_insc">
                        Aquí podrás ver toda la información relacionada con tus postulaciones
                        <span class="icon arrow"></span>
                    </div>
                </div>
                
                
                <div class="box_test_competencias devclick cp info  ocultar">
                    <div class="box ">
                            
                            <span class="icon_test start"></span>
                            <h3>Evalúa tus competencias</h3>
                            <p><span>Destaca</span> entre los candidatos</p>
                            <p><span>Descubre</span> tus puntos fuertes</p>
                            <p><span>Crece</span>  profesionalmente</p>
                            <a href="/Candidate/Competences/" class="submit_n boton">Empezar el test</a>
                                
                        </div>
                    </div>

                    <div class="box_test_competencias ocultar">
                        <h3>Test Comportamental</h3>
                        <ul class="list_results_test">
                            
                        </ul>
                        <a href="/Candidate/Competences/result.aspx">Ver resultados del test</a>
                    </div>
                
               
                
            </div>

            <div class="block_companies_courses">
                <div onkeypress="javascript:return WebForm_FireDefaultButton(event, 'ContainerGeneralOverWrite_ContainerGeneral_btnSearchOffers1')">
	
                    <div class="buscador">
                        <h2>Busqueda de Pacientes</h2>
                        <div class="input_text">
                            <span class="icon palabra_clave"></span>
                            <input name="ctl00$ContainerGeneralOverWrite$ContainerGeneral$sq1" type="text" id="ContainerGeneralOverWrite_ContainerGeneral_sq1" data-su="/ofertas-de-trabajo/" class="js-cargos-suggest ui-autocomplete-input" placeholder="Inicie la busqueda de pacientes ..." autocomplete="off">
                        </div>
                        <input type="submit" name="ctl00$ContainerGeneralOverWrite$ContainerGeneral$btnSearchOffers1" value="Buscar Paciente" id="ContainerGeneralOverWrite_ContainerGeneral_btnSearchOffers1" class="submit_n">

                        <div class="box_tooltips ocultar" id="tooltip_search">
                            Busca ofertas directamente a través de este buscador
                            <span class="icon arrow"></span>
                        </div>
                    </div>
                
</div>
                            </div>
        </div>
    </div>

            </div>
	    </div>
</div>
	
	
</div>
</body>
</html>

