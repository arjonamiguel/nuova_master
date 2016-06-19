package com.nuova.utils;

public class ConstantControllers {
    public final static String HOME = "/home";

    // Profesional
    public final static String MAIN_PROFESIONAL = "/mainProfesional";
    public final static String FORM_ADD_PROFESIONAL = "/formAddProfesional";
    public final static String FORM_EDIT_PROFESIONAL = "/formEditProfesional/{profesionalId}";
    public final static String FORM_DELETE_PROFESIONAL = "/formDeleteProfesional/{profesionalId}";
    public final static String ADD_PROFESIONAL = "/addProfesional";
    public final static String EDIT_PROFESIONAL = "/editProfesional";
    public final static String DELETE_PROFESIONAL = "/deleteProfesional";
    public final static String AJAX_GET_PROFESIONALES_PAGINADOS = "/ajaxGetProfesionalesPaginados";
    public final static String AJAX_GET_SEARCH_PROFESIONALES_PAGINADOS =
            "/ajaxGetSearchProfesionalesPaginados";

    // Especialidad
    public final static String MAIN_ESPECIALIDAD = "/mainEspecialidad";
    public final static String FORM_ADD_ESPECIALIDAD = "/formAddEspecialidad";
    public final static String FORM_EDIT_ESPECIALIDAD = "/formEditEspecialidad/{especialidadId}";
    public final static String FORM_DELETE_ESPECIALIDAD = "/formDeleteEspecialidad/{especialidadId}";
    public final static String ADD_ESPECIALIDAD = "/addEspecialidad";
    public final static String DELETE_ESPECIALIDAD = "/deleteEspecialidad";
    public final static String EDIT_ESPECIALIDAD = "/editEspecialidad";
    public final static String AJAX_GET_ESPECIALIDADES_PAGINADOS = "/ajaxGetEspecialidadesPaginados";
    public final static String AJAX_GET_SEARCH_ESPECIALIDADES_PAGINADOS =
            "/ajaxGetSearchEspecialidadesPaginados";

    // Obra Social
    public final static String MAIN_OBRASOCIAL = "/mainObraSocial";
    public final static String FORM_ADD_OBRASOCIAL = "/formAddObraSocial";
    public final static String FORM_EDIT_OBRASOCIAL = "/formEditObraSocial/{obrasocialId}";
    public final static String FORM_DELETE_OBRASOCIAL = "/formDeleteObraSocial/{obrasocialId}";
    public final static String ADD_OBRASOCIAL = "/addObraSocial";
    public final static String DELETE_OBRASOCIAL = "/deleteObraSocial";
    public final static String EDIT_OBRASOCIAL = "/editObraSocial";
    public final static String AJAX_GET_OBRASOCIALES_PAGINADOS = "/ajaxGetObrasSocialesPaginados";
    public final static String AJAX_GET_SEARCH_OBRASOCIALES_PAGINADOS =
            "/ajaxGetSearchObrasSocialesPaginados";

    // Paciente

    public final static String FORM_INFO_PACIENTE = "/formInfoPaciente/{pacienteId}";
    public final static String FORM_BUSCAR_PACIENTE = "/formBuscarPaciente";
    public final static String MAIN_PACIENTE = "/mainPaciente";
    public final static String FORM_ADD_PACIENTE = "/formAddPaciente";
    public final static String FORM_EDIT_PACIENTE = "/formEditPaciente/{pacienteId}";
    public final static String FORM_DELETE_PACIENTE = "/formDeletePaciente/{pacienteId}";
    public final static String ADD_PACIENTE = "/addPaciente";
    public final static String DELETE_PACIENTE = "/deletePaciente";
    public final static String EDIT_PACIENTE = "/editPaciente";
    public final static String AJAX_GET_PACIENTES_PAGINADOS = "/ajaxGetPacientesPaginados";
    public final static String AJAX_GET_SEARCH_PACIENTES_PAGINADOS =
            "/ajaxGetSearchPacientesPaginados";
    public final static String AJAX_GET_AUTOCOMPLETE_PACIENTES = "/ajaxGetAutoCompletePacientes";
    public final static String AJAX_GET_EXIST_DNI = "/ajaxGetExistDni";
    public final static String AJAX_GET_AUTOCOMPLETE_LOCALIDADES = "/ajaxGetAutoCompleteLocalidades";
    public final static String AJAX_GET_AUTOCOMPLETE_ESPECIALIDADES =
            "/ajaxGetAutoCompleteEspecialidades";
    public final static String AJAX_POST_ACTIVAR_PACIENTE = "/ajaxPostActivarPaciente/{pacienteId}";

    // Adherente
    public final static String FORM_ADD_ADHERENTE = "/formAddAdherente/{titularId}";
    public final static String FORM_EDIT_ADHERENTE = "/formEditAdherente/{pacienteId}";
    public final static String ADD_ADHERENTE = "/addAdherente";

    // Orden
    public final static String MAIN_ORDEN_PRACTICA = "/mainOrdenPractica";
    public final static String ORDEN_MESSAGE = "/ordenMessage";
    public final static String MAIN_CONSULTA = "/mainConsulta";
    public final static String MAIN_CONSULTA_ODONTOLOGICA = "/mainConsultaOdontologica";

    public final static String FORM_ADD_ORDEN = "/formAddOrden";
    public final static String FORM_ADD_ORDEN_BY_PACIENTE = "/formAddOrden/{pacienteId}";
    public final static String FORM_EDIT_ORDEN = "/formEditOrden/{ordenId}";
    public final static String FORM_EDIT_CONSULTA = "/formEditConsulta/{ordenId}";

    public final static String FORM_DELETE_ORDEN = "/formDeleteOrden/{ordenId}";
    public final static String ADD_ORDEN = "/addOrden";
    public final static String DELETE_ORDEN = "/deleteOrden";
    public final static String EDIT_ORDEN = "/editOrden";
    public final static String TIPO_ORDEN = "/tipoOrden/{pacienteId}";
    public final static String REDIRECT_ORDEN = "/redirectOrden";
    public final static String CREATE_ORDEN = "/createOrden/{ordenTipoId}/{pacienteId}";

    public final static String AJAX_GET_ORDENES_PAGINADOS =
            "/ajaxGetOrdenesPaginados/{codigoOrdenTipo}";
    public final static String AJAX_GET_SEARCH_ORDENES_PAGINADOS =
            "/ajaxGetSearchOrdenesPaginados/{codigoOrdenTipo}";
    public final static String AJAX_GET_CONSULTASBYPACIENTE_PAGINADOS =
            "/ajaxGetConsultasByPacientePaginados/{pacienteId}";
    public final static String AJAX_GET_PRACTICASBYPACIENTE_PAGINADOS =
            "/ajaxGetPracticasByPacientePaginados/{pacienteId}";
    public final static String DOWNLOAD = "/download-document/{documentId}";
    public final static String AJAX_POST_SAVECODIGONOMENCLADOR = "/ajaxPostSaveCodigoNomenclador";
    public final static String AJAX_POST_NUEVAEMPRESA = "/ajaxPostNuevaEmpresa";
    public final static String AJAX_GET_EMPRESAS = "/ajaxGetEmpresas";
    public final static String AJAX_GET_PROFESIONALESBYESPECIALIDAD =
            "/ajaxGetProfesionalByEspecialidad";
    public final static String AJAX_GET_PRESTADORESBYESPECIALIDAD =
            "/ajaxGetPrestadorByEspecialidad";
    public final static String AJAX_POST_NUEVALOCALIDAD = "/ajaxPostNuevaLocalidad";

    // Reportes
    public final static String SHOW_REPORT_ORDEN_EMITIDA = "/showReporteOrdenEmitida/{ordenId}";
    public final static String SHOW_REPORT_PROFESIONALES = "/showReporteProfesionales";
    public final static String SHOW_REPORT_ESPECIALIDADES = "/showReporteEspecialidades";
    public final static String SHOW_REPORT_OBRASSOCIALES = "/showReporteObrasSociales";
    public final static String SHOW_REPORT_PACIENTES = "/showReportePacientes";

    public final static String REPORT_ORDEN_EMITIDA = "/reporteOrdenEmitida/{ordenId}";
    public final static String REPORT_PROFESIONALES = "/reporteProfesionales";
    public final static String REPORT_ESPECIALIDADES = "/reporteEspecialidades";
    public final static String REPORT_OBRASSOCIALES = "/reporteObrasSociales";
    public final static String REPORT_PACIENTES = "/reportePacientes";

    public final static String REPORT_MONITOR = "/reportMonitor";
    public final static String AJAX_GET_AFILIADOS_ATENDIDOS = "/ajaxGetReportAfiliadosAtendidos";
    public final static String AJAX_GET_PACIENTES_REGISTRADOS = "/ajaxGetReportPacienteRegistrado";
    public final static String AJAX_GET_AFILIADOS_SIN_COSEGURO = "/ajaxGetReportAfiliadosSinCoseguro";
    public final static String AJAX_GET_AFILIADOS_SIN_COBERTURA =
            "/ajaxGetReportAfiliadosSinCobertura";
    public final static String AJAX_GET_FILTRO_AFILIADO = "/ajaxGetReportFiltroAfiliado";


    // Calendario
    public final static String MAIN_CALENDARIO = "/mainCalendario";
    public final static String FORM_ADD_CALENDARIO = "/formAddCalendario";
    public final static String ADD_CALENDARIO = "/addCalendario";
    public final static String FORM_DELETE_CALENDARIO = "/formDeleteCalendario//{calendarioId}";
    public final static String DELETE_CALENDARIO = "/deleteCalendario";
    public final static String ADMIN_CALENDARIO = "/adminCalendario";

    // Prestadores
    public final static String MAIN_PRESTADOR = "/mainPrestador";
    public final static String FORM_ADD_PRESTADOR = "/formAddPrestador";
    public final static String FORM_EDIT_PRESTADOR = "/formEditPrestador/{prestadorId}";
    public final static String FORM_DELETE_PRESTADOR = "/formDeletePrestador/{prestadorId}";
    public final static String ADD_PRESTADOR = "/addPrestador";
    public final static String DELETE_PRESTADOR = "/deletePrestador";
    public final static String EDIT_PRESTADOR = "/editPrestador";
    public final static String AJAX_GET_PRESTADORES_PAGINADOS = "/ajaxGetPrestadoresPaginados";
    public final static String AJAX_GET_SEARCH_PRESTADORES_PAGINADOS =
            "/ajaxGetSearchPrestadoresPaginados";

    // Practicas
    public final static String MAIN_PRACTICA = "/mainPractica";
    public final static String FORM_ADD_PRACTICA = "/formAddPractica";
    public final static String FORM_EDIT_PRACTICA = "/formEditPractica/{practicaId}";
    public final static String FORM_DELETE_PRACTICA = "/formDeletePractica/{practicaId}";
    public final static String ADD_PRACTICA = "/addPractica";
    public final static String DELETE_PRACTICA = "/deletePractica";
    public final static String EDIT_PRACTICA = "/editPractica";
    public final static String AJAX_GET_PRACTICAS_PAGINADOS = "/ajaxGetPracticasPaginados";
    public final static String AJAX_GET_SEARCH_PRACTICAS_PAGINADOS =
            "/ajaxGetSearchPracticasPaginados";
    public final static String AJAX_GET_AUTOCOMPLETE_NOMENCLADOR = "/ajaxGetAutoCompleteNomenclador";
    public final static String AJAX_GET_AUTOCOMPLETE_ESPECIALIADPRESTADOR = "/ajaxGetAutoCompleteEspecialidadesPrestador";

    // Caja
    public final static String MAIN_CAJA = "/mainCaja/{fechaCaja}";
    public final static String FORM_UPDATE_CAJA = "/formUpdateCaja";
    public final static String UPDATE_CAJA = "/updateCaja";
    public final static String AJAX_GET_MOVIMIENTOS_CAJA = "/ajaxGetMovimientoCaja";
    public final static String FORM_CIERRE_CAJA = "/formCierreCaja";
    public final static String CIERRE_CAJA = "/cierreCaja";
    public final static String MAIN_CIERRE_CAJA = "/mainCierreCaja";
    public final static String AJAX_GET_CAJACIERRE_PAGINADOS = "/ajaxGetCajaCierrePaginados";
    public final static String AJAX_GET_SEARCH_CAJACIERRE_PAGINADOS =
            "/ajaxGetSearchCajaCierrePaginados";

    // Reintegro
    public final static String MAIN_REINTEGRO = "/mainReintegro";
    public final static String FORM_ADD_REINTEGRO = "/formAddReintegro/{pacienteId}";
    public final static String FORM_EDIT_REINTEGRO = "/formEditReintegro/{reintegroId}";
    public final static String FORM_DELETE_REINTEGRO = "/formDeleteReintegro/{reintegroId}";
    public final static String ADD_REINTEGRO = "/addReintegro";
    public final static String DELETE_REINTEGRO = "/deleteReintegro";
    public final static String EDIT_REINTEGRO = "/editReintegro";
    public final static String AJAX_GET_REINTEGROS_PAGINADOS = "/ajaxGetReintegrosPaginados";
    public final static String AJAX_GET_SEARCH_REINTEGROS_PAGINADOS =
            "/ajaxGetSearchReintegrosPaginados";
    public final static String AJAX_GET_REINTEGROSBYPACIENTE_PAGINADOS =
            "/ajaxGetReintegrosByPacientePaginados/{pacienteId}";

}
