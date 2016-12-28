package com.nuova.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.io.ByteSource;
import com.nuova.dto.ComboItemDTO;
import com.nuova.dto.EspecialidadDTO;
import com.nuova.dto.ObraSocialDTO;
import com.nuova.dto.ObservacionesDTO;
import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.dto.OrdenDTO;
import com.nuova.dto.OrdenPracticaDTO;
import com.nuova.dto.OrdenTipoDTO;
import com.nuova.dto.OrdenWorkflowDTO;
import com.nuova.dto.PacienteDTO;
import com.nuova.dto.ProfesionalDTO;
import com.nuova.dto.ProfesionalEspecialidadDTO;
import com.nuova.model.Especialidad;
import com.nuova.model.Localidades;
import com.nuova.model.Nomenclador;
import com.nuova.model.Obrasocial;
import com.nuova.model.Observaciones;
import com.nuova.model.Orden;
import com.nuova.model.OrdenPractica;
import com.nuova.model.OrdenTipo;
import com.nuova.model.OrdenWorkflow;
import com.nuova.model.Paciente;
import com.nuova.model.Profesional;
import com.nuova.model.ProfesionalEspecialidad;
import com.nuova.service.EspecialidadManager;
import com.nuova.service.ObraSocialManager;
import com.nuova.service.OrdenManager;
import com.nuova.service.PacienteManager;
import com.nuova.service.ProfesionalManager;
import com.nuova.service.report.ReportManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;
import com.nuova.utils.Util;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class ReportController {
  // TODO : para insertar una imagen
  // InputStream imgStream = getClass().getResourceAsStream(KEY_IMG_PATH);
  // Renderable img = RenderableUtil.getInstance(null).getRenderable(imgStream,
  // OnErrorTypeEnum.BLANK);
  // parameters.put("img", img);

  private static final String ORDEN_EMITIDA_REPORT_JRXML = "reports/reporteOrdenEmitida.jrxml";
  private static final String PROFESIONALES_REPORT_JRXML = "reports/reporteProfesionales.jrxml";
  private static final String ESPECIALIDADES_REPORT_JRXML = "reports/reporteEspecialidades.jrxml";
  private static final String PACIENTES_REPORT_JRXML = "reports/reportePacientes.jrxml";
  private static final String ESPECIALIDADES_SUBREPORT_JRXML =
      "reports/reporteEspecialidades_Profesionales.jrxml";

  // Monitor de Reportes
  private static final String REPORT_AFILIADOS_ATENDIDOS = "reports/reportAfiliadosAtendidos.jrxml";
  private static final String REPORT_PACIENTES_REGISTRADOS =
      "reports/reportPacientesRegistrados.jrxml";
  private static final String REPORT_AFILIADOS_SIN_COSEGURO =
      "reports/reportAfiliadosSinCoseguro.jrxml";
  private static final String REPORT_AFILIADOS_SIN_COBERTURA =
      "reports/reportAfiliadosSinCobertura.jrxml";
  private static final String REPORT_FILTRO_AFILIADOS = "reports/reportFiltroAfiliados.jrxml";

  @Autowired
  ReportManager reportManager;
  @Autowired
  ServletContext context;
  @Autowired
  OrdenManager ordenManager;
  @Autowired
  ProfesionalManager profesionalManager;
  @Autowired
  EspecialidadManager especialidadManager;
  @Autowired
  PacienteManager pacienteManager;
  @Autowired
  ObraSocialManager obrasocialManager;

  public static String cleanString(String texto) {
    texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
    texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    return texto;
  }

  // ------------------------------------------------------------------------------------------------
  // Ajax Reports
  @RequestMapping(value = ConstantControllers.AJAX_GET_FILTRO_AFILIADO, method = RequestMethod.GET)
  public @ResponseBody String reportFiltroAfiliados(HttpServletResponse response,
      @RequestParam(required = false, defaultValue = "") String fechaDesdeAfiliado,
      @RequestParam(required = false, defaultValue = "") String fechaHastaAfiliado,
      @RequestParam(required = false, defaultValue = "") String fechaNacimiento,
      @RequestParam(required = false, defaultValue = "") String localidadId,
      @RequestParam(required = false, defaultValue = "") String zonaAfiliacion) throws IOException {

    //
    Date fd = Util.parseToDate(fechaDesdeAfiliado);
    Date fh = Util.parseToDate(fechaHastaAfiliado);
    Date fn = Util.parseToDate(fechaNacimiento);
    int loc = Integer.valueOf(!localidadId.equals("") ? localidadId : "0");
    String za = !zonaAfiliacion.equals("NONE") ? zonaAfiliacion : "";

    // Afiliados atendidos
    List<Paciente> afiliadosAtendidos = reportManager.getFiltroAfiliado(fd, fh, fn, loc, za);

    String filtroDesc = "";
    filtroDesc =
        filtroDesc + (fd != null ? " [Fecha Desde: " + Util.parseToStringDate(fd) + "]" : "");
    filtroDesc =
        filtroDesc + (fh != null ? " [Fecha Hasta: " + Util.parseToStringDate(fh) + "]" : "");
    filtroDesc = filtroDesc
        + (fn != null ? " [Fecha Nacimiento Mayores a: " + Util.parseToStringDate(fn) + "]" : "");
    String localidadString = "";
    if (loc > 0) {
      Localidades l = pacienteManager.findLocalidadById(loc);
      localidadString = l.getNombre();
    }
    filtroDesc = filtroDesc + (loc > 0 ? " [Localidad: " + localidadString + "]" : "");
    filtroDesc = filtroDesc + (!za.equals("") ? " [Zona de Afiliacion: " + za + "]" : "");

    List<PacienteDTO> listAA = getPacientesDto(afiliadosAtendidos);
    JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listAA);

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("totalPacientes", listAA.size());
    parameters.put("filtroDesc", filtroDesc);
    ByteSource source = ByteSource
        .wrap(createReport(REPORT_FILTRO_AFILIADOS, parameters, beanCollectionDataSource));
    source.copyTo(response.getOutputStream());

    response.setContentType("application/pdf");
    response.getOutputStream().write(source.read());
    response.getOutputStream().flush();
    response.getOutputStream().close();

    // return ConstantRedirect.VIEWER_REPORTE;
    return "OK";
  }



  @RequestMapping(value = ConstantControllers.AJAX_GET_AFILIADOS_ATENDIDOS,
      method = RequestMethod.GET)
  public @ResponseBody String reportAfiliadosAtendidos(HttpServletResponse response,
      @RequestParam(required = false, defaultValue = "") String fd,
      @RequestParam(required = false, defaultValue = "") String fh,
      @RequestParam(required = false, defaultValue = "") Integer esp) throws IOException {

    String especialidad = "";
    if (esp.intValue() == 0) {
      especialidad = "Todas las Especialidades";
    } else {
      Especialidad e = especialidadManager.findEspecialidadById(esp);
      especialidad = e != null ? e.getNombre() : "Especialidad Descono";
    }

    // Cantidad de pacientes
    OrdenAlarmaDTO cantPaciente = pacienteManager.countPacientes();

    // Afiliados atendidos
    List<Paciente> afiliadosAtendidos =
        reportManager.getAfiliadosAtendidos(Util.parseToDate(fd), Util.parseToDate(fh), esp);

    /*
     * canttotal --- 100% cantatendi -- x %
     */
    Double porcentaje = new Double(0);
    DecimalFormat df = new DecimalFormat("#,###,##0.00");
    if (cantPaciente.getCantidad() > 0) {
      porcentaje = new Double((afiliadosAtendidos.size() * 100) / cantPaciente.getCantidad());
    }

    List<PacienteDTO> listAA = getPacientesDto(afiliadosAtendidos);
    JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listAA);

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("fechaDesde", Util.parseToStringDate(Util.parseToDate(fd)));
    parameters.put("fechaHasta", Util.parseToStringDate(Util.parseToDate(fh)));
    parameters.put("especialidad", especialidad);
    parameters.put("totalPacientes", cantPaciente.getCantidad());
    parameters.put("totalAfiliadosAtendidos", afiliadosAtendidos.size());
    parameters.put("porcentajeAfiliados", df.format(porcentaje));

    ByteSource source = ByteSource
        .wrap(createReport(REPORT_AFILIADOS_ATENDIDOS, parameters, beanCollectionDataSource));
    source.copyTo(response.getOutputStream());

    response.setContentType("application/pdf");
    response.getOutputStream().write(source.read());
    response.getOutputStream().flush();
    response.getOutputStream().close();

    // return ConstantRedirect.VIEWER_REPORTE;
    return "OK";
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_PACIENTES_REGISTRADOS,
      method = RequestMethod.GET)
  public @ResponseBody String reportPacientesRegistrados(HttpServletResponse response,
      @RequestParam(required = false, defaultValue = "") String fd,
      @RequestParam(required = false, defaultValue = "") String fh) throws IOException {

    // Afiliados atendidos
    List<Paciente> pacientesregistrados =
        reportManager.getPacientesRegistrados(Util.parseToDate(fd), Util.parseToDate(fh));


    List<PacienteDTO> listPR = getPacientesDto(pacientesregistrados);
    JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listPR);

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("fechaDesde", Util.parseToStringDate(Util.parseToDate(fd)));
    parameters.put("fechaHasta", Util.parseToStringDate(Util.parseToDate(fh)));
    parameters.put("totalPacientes", listPR.size());

    ByteSource source = ByteSource
        .wrap(createReport(REPORT_PACIENTES_REGISTRADOS, parameters, beanCollectionDataSource));
    source.copyTo(response.getOutputStream());

    response.setContentType("application/pdf");
    response.getOutputStream().write(source.read());
    response.getOutputStream().flush();
    response.getOutputStream().close();

    return "OK";
  }


  @RequestMapping(value = ConstantControllers.AJAX_GET_AFILIADOS_SIN_COSEGURO,
      method = RequestMethod.GET)
  public @ResponseBody String reportAfiliadosSinCoseguro(HttpServletResponse response)
      throws IOException {

    // Afiliados atendidos
    List<Paciente> afiliadossincoseguro = reportManager.getAfiliadosSinCoseguro();

    List<PacienteDTO> listASC = getPacientesDto(afiliadossincoseguro);
    JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listASC);

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("totalPacientes", listASC.size());

    ByteSource source = ByteSource
        .wrap(createReport(REPORT_AFILIADOS_SIN_COSEGURO, parameters, beanCollectionDataSource));
    source.copyTo(response.getOutputStream());

    response.setContentType("application/pdf");
    response.getOutputStream().write(source.read());
    response.getOutputStream().flush();
    response.getOutputStream().close();

    return "OK";
  }


  @RequestMapping(value = ConstantControllers.AJAX_GET_AFILIADOS_SIN_COBERTURA,
      method = RequestMethod.GET)
  public @ResponseBody String reportAfiliadosSinCobertura(HttpServletResponse response)
      throws IOException {

    // Afiliados atendidos
    List<Paciente> afiliadossincobertura = reportManager.getAfiliadosSinCobertura();

    List<PacienteDTO> listASC = getPacientesDto(afiliadossincobertura);


    JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listASC);

    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("totalPacientes", listASC.size());

    ByteSource source = ByteSource
        .wrap(createReport(REPORT_AFILIADOS_SIN_COBERTURA, parameters, beanCollectionDataSource));
    source.copyTo(response.getOutputStream());

    response.setContentType("application/pdf");
    response.getOutputStream().write(source.read());
    response.getOutputStream().flush();
    response.getOutputStream().close();

    return "OK";
  }

  // ------------------------------------------------------------------------------------------------
  // Viewer Reports
  @RequestMapping(value = ConstantControllers.SHOW_REPORT_PROFESIONALES, method = RequestMethod.GET)
  public String showReportProfesionales(ModelMap map) {
    map.addAttribute("titulo", "Reporte de Profesionales registrados en Nuova");
    map.addAttribute("URL_ACTION", ConstantControllers.REPORT_PROFESIONALES);
    return ConstantRedirect.VIEWER_REPORTE;
  }

  @RequestMapping(value = ConstantControllers.SHOW_REPORT_ESPECIALIDADES,
      method = RequestMethod.GET)
  public String showReportEspecialidades(ModelMap map) {
    map.addAttribute("titulo", "Reporte de Especialidades registradas en Nuova");
    map.addAttribute("URL_ACTION", ConstantControllers.REPORT_ESPECIALIDADES);
    return ConstantRedirect.VIEWER_REPORTE;
  }

  @RequestMapping(value = ConstantControllers.SHOW_REPORT_PACIENTES, method = RequestMethod.GET)
  public String showReportPacientes(ModelMap map) {
    map.addAttribute("titulo", "Reporte de Pacientes registrados en Nuova");
    map.addAttribute("URL_ACTION", ConstantControllers.REPORT_PACIENTES);
    return ConstantRedirect.VIEWER_REPORTE;
  }

  @RequestMapping(value = ConstantControllers.SHOW_REPORT_ORDEN_EMITIDA, method = RequestMethod.GET)
  public String showReportOrdenEmitida(ModelMap map, @PathVariable("ordenId") Integer ordenId) {
    map.addAttribute("ordenId", ordenId);
    return ConstantRedirect.VIEWER_REPORTE_ORDEN_EMITIDA;
  }

  // Reports
  @RequestMapping(value = ConstantControllers.REPORT_ORDEN_EMITIDA, method = RequestMethod.GET)
  public String reportOrdenEmitida(ModelMap map, @PathVariable("ordenId") Integer ordenId,
      HttpServletResponse response) throws IOException {
    Orden orden = ordenManager.findOrdenById(ordenId);

    Map<String, Object> parameters = new HashMap<String, Object>();
    OrdenDTO dto = transformOrdenToDto(orden);
    parameters.put("nro_orden", dto.getNroOrden());
    parameters.put("dni", dto.getPaciente().getDni() + "");
    parameters.put("apellido_nombre", dto.getPaciente().getApellido().toUpperCase() + " "
        + dto.getPaciente().getNombre().toUpperCase());

    Localidades loc = pacienteManager.findLocalidadById(dto.getPaciente().getLocalidadId());
    parameters.put("localidad", dto.getPaciente().getProvincia() + " - " + loc.getNombre());
    parameters.put("domicilio", dto.getPaciente().getDomicilio().toUpperCase());
    parameters.put("nombre_obrasocial",
        dto.getPaciente().getObrasocial().getNombre().toUpperCase());
    parameters.put("credencial_obrasocial", dto.getPaciente().getObrasocial().getCredencial() + "-"
        + dto.getPaciente().getObrasocial().getCredencialSufijo());
    parameters.put("fecha", dto.getFecha());

    parameters.put("codigo_n_n", "");
    parameters.put("establecimiento", "");
    parameters.put("orden_internacion", "");
    parameters.put("trabaja_en", Util.getTrbajaEnValue(dto.getPaciente().getTrabajaEn() + ""));

    // Empresas e = pacienteManager.findEmpresaById(dto.getPaciente().getEmpresaId());
    // parameters.put("empresa", e == null ? "" : e.getNombre());
    parameters.put("coseguro", "");
    if (dto.getOrdenTipo().getCodigo().intValue() == 100) {
      parameters.put("tipo_orden", "CONSULTA");

    } else {
      parameters.put("tipo_orden", "PRÁCTICA");

    }

    ByteSource source = ByteSource.wrap(createReport(ORDEN_EMITIDA_REPORT_JRXML, parameters, null));
    source.copyTo(response.getOutputStream());
    response.setContentType("application/pdf");
    response.getOutputStream().write(source.read());
    response.getOutputStream().flush();
    response.getOutputStream().close();

    return ConstantRedirect.VIEWER_REPORTE_ORDEN_EMITIDA;
  }

  @RequestMapping(value = ConstantControllers.REPORT_PROFESIONALES, method = RequestMethod.GET)
  public String reportProfesionales(ModelMap map, HttpServletResponse response) throws IOException {
    List<ProfesionalDTO> profesionales = getProfesionalesDto(profesionalManager.findAll());
    JRBeanCollectionDataSource beanCollectionDataSource =
        new JRBeanCollectionDataSource(profesionales);

    Map<String, Object> parameters = new HashMap<String, Object>();

    ByteSource source = ByteSource
        .wrap(createReport(PROFESIONALES_REPORT_JRXML, parameters, beanCollectionDataSource));
    source.copyTo(response.getOutputStream());
    response.setContentType("application/pdf");
    response.getOutputStream().write(source.read());
    response.getOutputStream().flush();
    response.getOutputStream().close();

    return ConstantRedirect.VIEWER_REPORTE;
  }

  @RequestMapping(value = ConstantControllers.REPORT_ESPECIALIDADES, method = RequestMethod.GET)
  public String reportEspecialidades(ModelMap map, HttpServletResponse response)
      throws IOException {
    List<EspecialidadDTO> especialidades = getEspecialidadesDto(especialidadManager.findAll());
    JRBeanCollectionDataSource beanCollectionDataSource =
        new JRBeanCollectionDataSource(especialidades);
    String SUBREPORT_DIR = context.getRealPath(ESPECIALIDADES_SUBREPORT_JRXML);
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("SUBREPORT_DIR", SUBREPORT_DIR);

    ByteSource source = ByteSource
        .wrap(createReport(ESPECIALIDADES_REPORT_JRXML, parameters, beanCollectionDataSource));
    source.copyTo(response.getOutputStream());
    response.setContentType("application/pdf");
    response.getOutputStream().write(source.read());
    response.getOutputStream().flush();
    response.getOutputStream().close();

    return ConstantRedirect.VIEWER_REPORTE;
  }

  @RequestMapping(value = ConstantControllers.REPORT_PACIENTES, method = RequestMethod.GET)
  public String reportPacientes(ModelMap map, HttpServletResponse response) throws IOException {
    List<PacienteDTO> pacientes = getPacientesDto(pacienteManager.findAllActive());
    JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(pacientes);

    Map<String, Object> parameters = new HashMap<String, Object>();

    ByteSource source =
        ByteSource.wrap(createReport(PACIENTES_REPORT_JRXML, parameters, beanCollectionDataSource));
    source.copyTo(response.getOutputStream());
    response.setContentType("application/pdf");
    response.getOutputStream().write(source.read());
    response.getOutputStream().flush();
    response.getOutputStream().close();

    return ConstantRedirect.VIEWER_REPORTE;
  }

  @RequestMapping(value = ConstantControllers.REPORT_MONITOR, method = RequestMethod.GET)
  public String reportMonitor(ModelMap map) throws IOException {

    map.addAttribute("provinciaList", Util.getProvincias());
    map.addAttribute("provincia", new String());
    map.addAttribute("localidadId", new String());
    map.addAttribute("localidadString", new String());

    map.addAttribute("zonaAfiliacion", new String());

    return ConstantRedirect.VIEW_REPORT_MONITOR;
  }

  // Config Jasper Report
  private byte[] createReport(String template, Map<String, Object> parameters,
      JRBeanCollectionDataSource beanCollectionDataSource) {
    byte[] pdf = {};
    try {
      InputStream inputStream = getClass().getClassLoader().getResourceAsStream(template);
      JasperReport report = JasperCompileManager.compileReport(inputStream);

      JasperPrint response = JasperFillManager.fillReport(report, parameters,
          beanCollectionDataSource == null ? new JREmptyDataSource() : beanCollectionDataSource);

      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      JasperExportManager.exportReportToPdfStream(response, outputStream);
      pdf = outputStream.toByteArray();

    } catch (JRException ex) {
      throw new RuntimeException(ex);
    }
    return pdf;
  }

  // Trasform model to DTO
  // -------------------------------------

  private OrdenDTO transformOrdenToDto(Orden orden) {
    OrdenDTO dto = new OrdenDTO();
    dto.setOrdenId(orden.getOrdenId());
    dto.setFecha(orden.getFecha() + "");
    dto.setEstado(orden.getEstado());

    // Paciente
    Paciente p = pacienteManager.findPacienteByOrdenId(orden.getOrdenId());
    dto.setPaciente(transformPacienteToDto(p));

    // requisitos
    dto.setReqCredecial(orden.getReqCredecial().intValue() == 1 ? true : false);
    dto.setReqMonotributista(orden.getReqMonotributista().intValue() == 1 ? true : false);
    dto.setReqOrdenMedico(orden.getReqOrdenMedico().intValue() == 1 ? true : false);
    dto.setReqReciboSueldo(orden.getReqReciboSueldo().intValue() == 1 ? true : false);

    // Autorizacion
    // dto.setPracticasListEdit(getPracticaDto(orden.getOrdenPracticas()));

    // Observaciones
    // dto.setObservacioneses(getObservacionesDto(orden.getObservacioneses()));

    // Flujo de Estados
    // dto.setOrdenWorkflows(getOrdenWorkflowToDto(orden.getOrdenWorkflows()));

    // Profesional
    // for (OrdenProfesional op : orden.getOrdenProfesionals()) {
    // if (op.getProfesional() != null) {
    // dto.setProfesional(transformProfesionalToDto(op.getProfesional()));
    // }
    // }

    // Historia Clinica
    // List<OrdenDocument> documents = ordenManager.finAllOrdenDocumentByOrdenId(dto.getOrdenId());
    // for (OrdenDocument od : documents) {
    // OrdenDocumentDTO oddto = new OrdenDocumentDTO(od.getDocumentId(), od.getType(),
    // od.getFileName(), od.getFileType(), od.getOrdenId(), null);
    //
    // dto.getHistoriasclinicas().add(oddto);
    // }

    // boton paciente
    // String botonpaciente =
    // dto.getPaciente().getApellido().toUpperCase() + ", " + dto.getPaciente().getNombre();
    // dto.setBotonpaciente(botonpaciente);
    //
    dto.setOrdenTipo(transformOrdenTipoToDto(orden.getOrdenTipo()));
    // dto.setOrdenTipoDesc(dto.getOrdenTipo().getNombre());
    //
    // // botones de acciones
    // String action = "";
    // if (dto.getOrdenTipo().getCodigo().intValue() == 100) {
    // action = "formEditConsulta";
    // }
    //
    // if (dto.getOrdenTipo().getCodigo().intValue() == 101) {
    // action = "formEditOrden";
    // }
    //
    // if (dto.getOrdenTipo().getCodigo().intValue() == 102) {
    // action = "formEditOrden";
    // }
    //
    // String botonEdit = "<a class='btn btn-info btn-xs' href='/nuova/" + action + "/"
    // + dto.getOrdenId() + "'><span class='icon icon-edit'></span></a>";
    //
    // String botonDelete = "<a class='btn btn-danger btn-xs' href='/nuova/formDeleteOrden/"
    // + dto.getOrdenId() + "'><span class='icon icon-remove'></span></a>";
    //
    // String botonPrint =
    // "<a class='btn btn-default btn-xs' data-toggle='modal' data-target='#myModal'
    // onClick='showReport("
    // + dto.getOrdenId() + ")'><span class='icon icon-print'></span></a>";
    // // String botonPrint = "<button type='button' class='btn btn-info btn-lg' data-toggle='modal'
    // // data-target='#myModal'>Open Modal</button>";
    //
    // dto.setAcciones(botonEdit + botonDelete + botonPrint);

    return dto;
  }

  private OrdenTipoDTO transformOrdenTipoToDto(OrdenTipo ot) {
    OrdenTipoDTO retorno = new OrdenTipoDTO();
    retorno.setOrdenTipoId(ot.getOrdenTipoId());
    retorno.setNombre(ot.getNombre());
    retorno.setMonto1(ot.getMonto1());
    retorno.setMonto2(ot.getMonto2());
    retorno.setMonto3(ot.getMonto3());
    retorno.setCodigo(ot.getCodigo());

    return retorno;
  }


  public PacienteDTO transformPacienteToDto(Paciente p) {
    PacienteDTO dto = new PacienteDTO();
    dto.setPacienteId(p.getPacienteId());
    dto.setEliminado(p.getEliminado().intValue());
    dto.setDni(p.getDni());
    dto.setApellido(p.getApellido());
    dto.setNombre(p.getNombre() == null ? "" : p.getNombre());
    dto.setDomicilio(p.getDomicilio());
    dto.setFechaNacimiento(
        p.getFechaNacimiento() == null ? "" : Util.parseToStringDate(p.getFechaNacimiento()));
    dto.setCoseguro(p.getCoseguro().intValue() == 1 ? true : false);
    dto.setCheckedLiberado(p.getCoseguro().intValue() == 1 ? "checked" : "");
    dto.setMail(p.getMail());
    dto.setTelefono(p.getTelefono());
    dto.setProvincia(p.getProvincia());
    dto.setRazonCoseguro(p.getRazonCoseguro() == null ? "" : p.getRazonCoseguro());
    dto.setZonaAfiliacion(p.getZonaAfiliacion());
    dto.setEliminadoView(p.getEliminado().intValue() == 0 ? "ACTIVO" : "INACTIVO");
    dto.setCrdencial(p.getNroCredencial());
    dto.setCredencialSufijo(p.getNroCredencialSufijo());

    if (p.getLocalidadId() != null) {
      Localidades loc = pacienteManager.findLocalidadById(p.getLocalidadId());
      dto.setLocalidadId(loc.getLocalidadId());
      dto.setLocalidadString(loc.getNombre());
    }

    Obrasocial o = obrasocialManager.findObraSocialById(p.getObrasocialId());
    ObraSocialDTO osdto = new ObraSocialDTO();
    osdto.setObrasocialId(p.getObrasocialId());
    osdto.setNombre(o.getNombre());
    osdto.setCredencial(p.getNroCredencial());
    osdto.setCredencialSufijo(p.getNroCredencialSufijo());
    dto.setObrasocial(osdto);
    if (p.getPaciente() != null && p.getPaciente().getPacienteId() != null) {
      dto.setPacienteTitular(transformPacienteToDto(
          pacienteManager.fin1dPacienteById(p.getPaciente().getPacienteId())));
    }

    dto.setParentesco(p.getParentesco().intValue());
    for (ComboItemDTO item : Util.getParentescos()) {
      if (dto.getParentesco() == Integer.valueOf(item.getId()).intValue())
        dto.setParentescoDescription(item.getValue());
    }

    dto.setTrabajaEn(p.getTrabajaEn());
    dto.setEmpresa(p.getEmpresa());
    dto.setEmpresaId(p.getEmpresaId());

    for (Paciente ad : p.getPacientes()) {
      PacienteDTO dtoad = new PacienteDTO();
      dtoad.setPacienteId(ad.getPacienteId());
      dtoad.setApellido(ad.getApellido());
      dtoad.setNombre(ad.getNombre());
      dtoad.setDomicilio(ad.getDomicilio());
      dtoad.setFechaNacimiento(Util.parseToStringDate(ad.getFechaNacimiento()));
      dtoad.setCoseguro(ad.getCoseguro().intValue() == 1 ? true : false);
      dtoad.setCheckedLiberado(p.getCoseguro().intValue() == 1 ? "checked" : "");
      dtoad.setMail(ad.getMail());
      dtoad.setTelefono(ad.getTelefono());
      dtoad.setDni(ad.getDni());
      dtoad.setZonaAfiliacion(p.getZonaAfiliacion());
      dtoad.setParentesco(ad.getParentesco().intValue());
      dtoad.setCrdencial(ad.getNroCredencial() + "-" + ad.getNroCredencialSufijo());
      dtoad.setEliminado(p.getEliminado().intValue());
      dtoad.setEmpresa(ad.getEmpresa());

      for (ComboItemDTO item : Util.getParentescos()) {
        if (dtoad.getParentesco() == Integer.valueOf(item.getId()).intValue())
          dtoad.setParentescoDescription(item.getValue());
      }

      dto.getAdherentes().add(dtoad);
    }

    return dto;
  }

  private List<OrdenPracticaDTO> getPracticaDto(Set<OrdenPractica> list) {
    List<OrdenPracticaDTO> retorno = new ArrayList<OrdenPracticaDTO>(0);
    for (OrdenPractica op : list) {
      Nomenclador p = op.getNomenclador();
      p.setNombre("[" + p.getCodigo() + "]-" + p.getNombre());
      Orden o = op.getOrden();
      // PracticaDTO dto = new PracticaDTO(p.getPracticaId(), "[" + p.getCodigo() + "]-" +
      // p.getNombre());
      OrdenPracticaDTO dto = new OrdenPracticaDTO(op.getOrddenPracticaId(), o.getOrdenId(),
          p.getNombre(), p.getNomencladorId(), op.getEstado(), op.getPiezaDental());
      // dto.setOrddenPracticaId(op.getOrddenPracticaId());
      retorno.add(dto);
    }

    return retorno;
  }

  private List<ObservacionesDTO> getObservacionesDto(Set<Observaciones> list) {
    List<ObservacionesDTO> retorno = new ArrayList<ObservacionesDTO>();
    for (Observaciones o : list) {
      OrdenDTO odto = new OrdenDTO();
      odto.setOrdenId(o.getOrden().getOrdenId());
      ObservacionesDTO dto =
          new ObservacionesDTO(odto, o.getObservacion(), o.getUserName(), o.getFecha());
      retorno.add(dto);
    }

    Collections.sort(retorno, new Comparator<ObservacionesDTO>() {
      public int compare(ObservacionesDTO a1, ObservacionesDTO a2) {
        return a2.getFecha().compareTo(a1.getFecha());
      }
    });
    return retorno;
  }

  private List<OrdenWorkflowDTO> getOrdenWorkflowToDto(Set<OrdenWorkflow> list) {
    List<OrdenWorkflowDTO> retorno = new ArrayList<OrdenWorkflowDTO>();
    for (OrdenWorkflow o : list) {
      OrdenDTO odto = new OrdenDTO();
      odto.setOrdenId(o.getOrden().getOrdenId());
      OrdenWorkflowDTO dto =
          new OrdenWorkflowDTO(odto, o.getUserName(), o.getEstado(), o.getFecha());
      retorno.add(dto);
    }

    Collections.sort(retorno, new Comparator<OrdenWorkflowDTO>() {
      public int compare(OrdenWorkflowDTO a1, OrdenWorkflowDTO a2) {
        return a2.getFecha().compareTo(a1.getFecha());
      }
    });

    return retorno;
  }

  private List<ProfesionalDTO> getProfesionalesDto(List<Profesional> list) {
    List<ProfesionalDTO> retorno = new ArrayList<ProfesionalDTO>();
    for (Profesional p : list) {
      retorno.add(transformProfesionalToDto(p));
    }
    return retorno;
  }

  private ProfesionalDTO transformProfesionalToDto(Profesional p) {
    ProfesionalDTO dto = new ProfesionalDTO();
    dto.setApellido(p.getApellido());
    dto.setNombre(p.getNombre());
    dto.setTelefono(p.getTelefono());
    dto.setTituloProfesional(p.getTituloProfesional());
    dto.setRegistroNacional(p.getRegistroNacional());
    dto.setProfesionalId(p.getProfesionalId());
    dto.setHabilitacionSiprosa(p.getHabilitacionSiprosa().toString());
    dto.setMatricula(p.getMatricula());
    dto.setFechaVencimientoHabilitacion(p.getFechaVencimientoHabilitacion() + "");

    Set<ProfesionalEspecialidad> listPE = p.getProfesionalEspecialidads();
    for (ProfesionalEspecialidad pe : listPE) {
      Especialidad especialidad =
          especialidadManager.findEspecialidadById(pe.getEspecialidad().getEspecialidadId());

      ProfesionalDTO profesionalDto = new ProfesionalDTO();
      profesionalDto.setProfesionalId(pe.getProfesional().getProfesionalId());
      EspecialidadDTO especialidadDto = new EspecialidadDTO();
      especialidadDto.setId(pe.getEspecialidad().getEspecialidadId());

      dto.getEspecialidadListOld()
          .add(new ProfesionalEspecialidadDTO(pe.getId(), profesionalDto, especialidadDto));
      dto.getEspecialidadListEdit().put(especialidad.getEspecialidadId(), especialidad.getNombre());
    }

    return dto;
  }

  private EspecialidadDTO transformEspecialidadToDTO(Especialidad e) {
    EspecialidadDTO retorno = new EspecialidadDTO();
    retorno.setId(e.getEspecialidadId());
    retorno.setNombre(e.getNombre());
    for (ProfesionalEspecialidad pe : e.getProfesionalEspecialidads()) {
      Profesional p =
          profesionalManager.findProfesionalById(pe.getProfesional().getProfesionalId());
      retorno.getProfesionales().add(transformProfesionalToDto(p));
    }
    return retorno;
  }

  private List<EspecialidadDTO> getEspecialidadesDto(List<Especialidad> list) {
    List<EspecialidadDTO> retorno = new ArrayList<EspecialidadDTO>();
    for (Especialidad e : list) {
      retorno.add(transformEspecialidadToDTO(e));
    }

    return retorno;
  }

  private List<PacienteDTO> getPacientesDto(List<Paciente> list) {
    List<PacienteDTO> retorno = new ArrayList<PacienteDTO>();
    for (Paciente p : list) {
      retorno.add(transformPacienteToDto(p));
    }

    return retorno;
  }
}
