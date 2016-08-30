package com.nuova.controller;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import com.nuova.dto.ComboItemDTO;
import com.nuova.dto.EspecialidadDTO;
import com.nuova.dto.GridOrdenPracticaDTO;
import com.nuova.dto.HistoriaClinicaDTO;
import com.nuova.dto.NomencladorDTO;
import com.nuova.dto.ObraSocialDTO;
import com.nuova.dto.ObservacionesDTO;
import com.nuova.dto.OrdenDTO;
import com.nuova.dto.OrdenDocumentDTO;
import com.nuova.dto.OrdenPracticaDTO;
import com.nuova.dto.OrdenTipoDTO;
import com.nuova.dto.OrdenWorkflowDTO;
import com.nuova.dto.PacienteDTO;
import com.nuova.dto.PacienteOrdenPracticaDTO;
import com.nuova.dto.PracticasListDTO;
import com.nuova.dto.ProfesionalDTO;
import com.nuova.dto.ProfesionalEspecialidadDTO;
import com.nuova.model.Caja;
import com.nuova.model.CajaOrden;
import com.nuova.model.Especialidad;
import com.nuova.model.Nomenclador;
import com.nuova.model.Obrasocial;
import com.nuova.model.Observaciones;
import com.nuova.model.Orden;
import com.nuova.model.OrdenDocument;
import com.nuova.model.OrdenFueraCartilla;
import com.nuova.model.OrdenPractica;
import com.nuova.model.OrdenPrestador;
import com.nuova.model.OrdenProfesional;
import com.nuova.model.OrdenTipo;
import com.nuova.model.OrdenWorkflow;
import com.nuova.model.Paciente;
import com.nuova.model.Prestadores;
import com.nuova.model.Profesional;
import com.nuova.model.ProfesionalEspecialidad;
import com.nuova.service.CajaManager;
import com.nuova.service.EspecialidadManager;
import com.nuova.service.ObraSocialManager;
import com.nuova.service.ObservacionManager;
import com.nuova.service.OrdenManager;
import com.nuova.service.PacienteManager;
import com.nuova.service.PracticaManager;
import com.nuova.service.ProfesionalManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantOrdenEstado;
import com.nuova.utils.ConstantRedirect;
import com.nuova.utils.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

@Controller
public class OrdenController {
  @Autowired
  OrdenManager ordenManager;
  @Autowired
  PacienteManager pacienteManager;
  @Autowired
  ObraSocialManager obrasocialManager;
  @Autowired
  PracticaManager practicaManager;
  @Autowired
  ObservacionManager observacionManager;
  @Autowired
  ProfesionalManager profesionalManager;
  @Autowired
  EspecialidadManager especialidadManager;
  @Autowired
  CajaManager cajaManager;

  boolean isFormDelete = false;

  private String iniciada = " <span  style='color:black;background:gold'>INICIADA</span>";
  private String autorizada = "<span style='color:white;background: green'>AUTORIZADA</span>";
  private String pendiente = "<span  style='color:white;background: sienna'>PENDIENTE</span>";
  private String en_progreso =
      " <span style='color:white;background: steelblue'>EN PROGRESO</span>";
  private String rechazada = "<span style='color:white;background: gray'>RECHAZADA</span>";
  private String cerrada = "<span style='color:white;background: black'>CERRADA</span>";
  private String anulada = "<span style='color:white;background: tomato'>ANULADA</span>";

  private int lastOrdenId;

  @RequestMapping(value = ConstantControllers.REDIRECT_ORDEN, method = RequestMethod.POST)
  public String redirectOrden(ModelMap map,
      @ModelAttribute(value = "ordenTipo") OrdenTipoDTO ordenTipo) {
    String redirect = "";
    int codigo = ordenTipo.getCodigo().intValue();
    Paciente paciente = pacienteManager.fin1dPacienteById(ordenTipo.getPacienteId());
    OrdenDTO ordenDto = new OrdenDTO();
    ordenDto.setPaciente(transformPacienteToDto(paciente));
    OrdenTipo ot = ordenManager.findOrdenTipoByCodigo(codigo);
    ordenDto.setOrdenTipo(transformOrdenTipoToDto(ot));

    List<Profesional> profesionales = profesionalManager.findAll();

    if (codigo == Util.ORDEN_CONSULTA) {
      OrdenTipoDTO otdto = transformOrdenTipoToDto(ot);
      map.addAttribute("profesionales", getProfesionalDTOList(profesionales));
      map.addAttribute("ordenTipoDto", otdto);
      map.addAttribute("montosorden", getMontosOrden(otdto));
      redirect = ConstantRedirect.VIEW_FORM_CONSULTA_BY_PACIENTE;

    } else if (codigo == Util.ORDEN_ODONTOLOGICA) {
      OrdenTipoDTO otdto = transformOrdenTipoToDto(ot);
      map.addAttribute("montosorden", getMontosOrden(otdto));
      map.addAttribute("ordenTipoDto", otdto);
      map.addAttribute("montosorden", getMontosOrden(otdto));
      redirect = ConstantRedirect.VIEW_FORM_ODONTOLOGIA_BY_PACIENTE;

    } else if (codigo == Util.ORDEN_PRACTICA) {
      map.addAttribute("profesionales", getProfesionalDTOList(profesionales));
      redirect = ConstantRedirect.VIEW_FORM_ADD_ORDEN_BY_PACIENTE;
    }

    map.addAttribute("ordenDto", ordenDto);
    return redirect;
  }

  @RequestMapping(value = ConstantControllers.CREATE_ORDEN, method = RequestMethod.GET)
  public String createOrden(ModelMap map, @PathVariable("ordenTipoId") Integer ordenTipoId,
      @PathVariable("pacienteId") Integer pacienteId) {
    OrdenTipo otp = ordenManager.findOrdenTipoById(ordenTipoId);
    OrdenTipoDTO ordenTipo = new OrdenTipoDTO();
    ordenTipo.setCodigo(otp.getCodigo());
    ordenTipo.setOrdenTipoId(otp.getOrdenTipoId());
    ordenTipo.setPacienteId(pacienteId);

    return redirectOrden(map, ordenTipo);
  }

  @RequestMapping(value = ConstantControllers.FORM_ADD_ORDEN, method = RequestMethod.GET)
  public String formAddOrden(ModelMap map) {
    List<Paciente> pacientes = pacienteManager.findAll();

    map.addAttribute("pacienteList", Util.getComboItems(pacientes));

    map.addAttribute("paciente", new OrdenDTO());
    return ConstantRedirect.VIEW_FORM_ADD_ORDEN;
  }

  @RequestMapping(value = ConstantControllers.FORM_ADD_ORDEN_BY_PACIENTE,
      method = RequestMethod.GET)
  public String formAddOrdenByPaciente(ModelMap map,
      @PathVariable("pacienteId") Integer pacienteId) {
    Paciente paciente = pacienteManager.fin1dPacienteById(pacienteId);
    OrdenDTO ordenDto = new OrdenDTO();
    ordenDto.setPaciente(transformPacienteToDto(paciente));

    List<ComboItemDTO> practicaList = new ArrayList<ComboItemDTO>();
    map.addAttribute("ordenDto", ordenDto);
    map.addAttribute("practicaList", practicaList);
    return ConstantRedirect.VIEW_FORM_ADD_ORDEN_BY_PACIENTE;
  }

  @RequestMapping(value = ConstantControllers.FORM_DELETE_ORDEN, method = RequestMethod.GET)
  public String formDelteOrden(ModelMap map, @PathVariable("ordenId") Integer ordenId) {
    this.isFormDelete = true;
    return formEditOrden(map, ordenId);
  }

  @RequestMapping(value = ConstantControllers.FORM_DELETE_ORDEN_CONSULTA,
      method = RequestMethod.GET)
  public String formDeleteConsulta(ModelMap map, @PathVariable("ordenId") Integer ordenId) {
    this.isFormDelete = true;
    return formEditConsulta(map, ordenId);
  }

  @RequestMapping(value = ConstantControllers.FORM_EDIT_ORDEN, method = RequestMethod.GET)
  public String formEditOrden(ModelMap map, @PathVariable("ordenId") Integer ordenId) {
    if (ordenId != null) {
      OrdenDTO ordenDto = transformOrdenToDto(ordenManager.findOrdenById(ordenId));


      Especialidad e = null;
      if (ordenDto.getEspecialidad() != null) {
        e = especialidadManager.findEspecialidadById(ordenDto.getEspecialidad());
      }

      List<Profesional> profesionales =
          especialidadManager.findProfesionalByEspecialidadId(ordenDto.getEspecialidad());

      List<Prestadores> prestadores =
          especialidadManager.findPrestadorByEspecialidadId(ordenDto.getEspecialidadPrestador());

      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      int observacionCount = ordenDto.getObservacioneses().size();
      map.addAttribute("ordenDto", ordenDto);
      map.addAttribute("observacionCount", observacionCount);
      map.addAttribute("userNameLogged", user.getUsername());
      map.addAttribute("ordenEstadosList", Util.getOrdenEstadosList());
      map.addAttribute("profesionales", getProfesionalDTOList(profesionales));
      map.addAttribute("prestadores", getPrestadorDTOList(prestadores));
      map.addAttribute("especialidadView", e == null ? null : e.getNombre());
      map.addAttribute("listNomencladorTipo", practicaManager.findNomecladorTipo());
    }

    if (!isFormDelete) {
      return ConstantRedirect.VIEW_FORM_EDIT_ORDEN;
    } else {
      this.isFormDelete = false;
      return ConstantRedirect.VIEW_FORM_DELETE_ORDEN;
    }

  }

  @RequestMapping(value = ConstantControllers.FORM_EDIT_CONSULTA, method = RequestMethod.GET)
  public String formEditConsulta(ModelMap map, @PathVariable("ordenId") Integer ordenId) {
    if (ordenId != null) {
      List<ComboItemDTO> items = new ArrayList<ComboItemDTO>();
      String nombre = "";
      OrdenDTO ordenDto = transformOrdenToDto(ordenManager.findOrdenById(ordenId));
      if (ordenDto.getEspecialidad() != null) {
        if (ordenDto.getEspecialidad() != null) {
          Especialidad e = especialidadManager.findEspecialidadById(ordenDto.getEspecialidad());
          nombre = (e != null) ? e.getNombre() : "";
        }
        List<Profesional> profesionales =
            especialidadManager.findProfesionalByEspecialidadId(ordenDto.getEspecialidad());
        items = getProfesionalDTOList(profesionales);
      } else {
        items = getProfesionalDTOList(profesionalManager.findAll());
      }
      map.addAttribute("profesionales", items);
      map.addAttribute("ordenDto", ordenDto);
      map.addAttribute("especialidadView", nombre);

    }

    if (!isFormDelete) {
      return ConstantRedirect.VIEW_FORM_EDIT_CONSULTA;
    } else {
      this.isFormDelete = false;
      return ConstantRedirect.VIEW_FORM_DELETE_CONSULTA;
    }

  }

  @RequestMapping(value = ConstantControllers.DOWNLOAD, method = RequestMethod.GET)
  public void downloadDocument(HttpServletResponse response, ModelMap map,
      @PathVariable("documentId") Integer documentId) throws IOException {

    OrdenDocument doc = ordenManager.findOrdenDocumentById(documentId);
    String typeFile = "." + doc.getFileType().split("/")[1];

    byte[] content = doc.getContent();
    ByteSource source = ByteSource.wrap(content);
    source.copyTo(response.getOutputStream());

    response.setContentType(doc.getFileType());
    String contentDispositionValue = getContentDispositionValue(doc.getFileName(), typeFile);
    response.setHeader(com.google.common.net.HttpHeaders.CONTENT_DISPOSITION,
        contentDispositionValue);
    response.setHeader(com.google.common.net.HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,
        com.google.common.net.HttpHeaders.CONTENT_DISPOSITION);
  }

  // Ajax --------------------------------------------
  @RequestMapping(value = ConstantControllers.AJAX_GET_ORDENES_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<GridOrdenPracticaDTO> getOrdenesPaginados(
      @PathVariable("codigoOrdenTipo") Integer codigoOrdenTipo,
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<GridOrdenPracticaDTO> ordenes =
        ordenManager.findOrdenesByPageable(pageable, codigoOrdenTipo);
    List<GridOrdenPracticaDTO> aux = new ArrayList<GridOrdenPracticaDTO>();

    for (GridOrdenPracticaDTO o : ordenes.getContent()) {
      // PacienteOrdenPracticaDTO dto = transformoOrdenToPacienteOrdenPracticaDTO(o);
      String practicas = "";
      List<PracticasListDTO> practicasList = ordenManager.getAllPracticasByOrden(o.getOrdenId());
      for (PracticasListDTO op : practicasList) {
        practicas = practicas + "<tr> <td style='width:20%'>" + op.getCodigo()
            + "</td> <td style='width:50%'>" + op.getNombre() + "</td> " + " <td>"
            + getPracticaEstado(op) + "</td></tr>";

      }

      String listpracticas = "<table>" + practicas + "</table>";
      o.setPracticas(listpracticas);
      aux.add(o);
    }


    return new PageImpl<GridOrdenPracticaDTO>(aux, pageable, ordenes.getTotalElements());
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_CONSULTASBYPACIENTE_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<GridOrdenPracticaDTO> getConsultasByPacientePaginados(
      @PathVariable("pacienteId") Integer pacienteId,
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<GridOrdenPracticaDTO> dtos =
        ordenManager.findConsultasByPageableANDPaciente(pageable, pacienteId, 100);

    return new PageImpl<GridOrdenPracticaDTO>(dtos.getContent(), pageable, dtos.getTotalElements());
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_PRACTICASBYPACIENTE_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<GridOrdenPracticaDTO> getPracticasByPacientePaginados(
      @PathVariable("pacienteId") Integer pacienteId,
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<GridOrdenPracticaDTO> ordenes =
        ordenManager.findPracticasByPageableANDPaciente(pageable, pacienteId, 102);
    List<GridOrdenPracticaDTO> aux = new ArrayList<GridOrdenPracticaDTO>();


    for (GridOrdenPracticaDTO o : ordenes.getContent()) {
      // PacienteOrdenPracticaDTO dto = transformoOrdenToPacienteOrdenPracticaDTO(o);
      String practicas = "";
      List<PracticasListDTO> practicasList = ordenManager.getAllPracticasByOrden(o.getOrdenId());
      for (PracticasListDTO op : practicasList) {
        practicas = practicas + "<tr> <td style='width:20%'>" + op.getCodigo()
            + "</td> <td style='width:50%'>" + op.getNombre() + "</td> " + " <td>"
            + getPracticaEstado(op) + "</td></tr>";

      }

      String listpracticas = "<table>" + practicas + "</table>";
      o.setPracticas(listpracticas);
      aux.add(o);
    }
    return new PageImpl<GridOrdenPracticaDTO>(aux, pageable, ordenes.getTotalElements());
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_HISTORIACLINICABYPACIENTE_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<HistoriaClinicaDTO> getHistoriaClinicaByPacientePaginados(
      @PathVariable("pacienteId") Integer pacienteId,
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<HistoriaClinicaDTO> ordenes = ordenManager.findHistoriaClinica(pageable, pacienteId);
    return new PageImpl<HistoriaClinicaDTO>(ordenes.getContent(), pageable,
        ordenes.getTotalElements());
  }


  @RequestMapping(value = ConstantControllers.AJAX_GET_SEARCH_ORDENES_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<GridOrdenPracticaDTO> getSearchOrdenesPaginados(
      @PathVariable("codigoOrdenTipo") Integer codigoOrdenTipo,
      @RequestParam(required = false, defaultValue = "") String search,
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    search = search.trim().toUpperCase().replace(" ", "");
    boolean isNumber = Util.isNumber(search);
    String paciente = (isNumber ? "" : search);
    Integer ordenId = (isNumber ? Integer.parseInt(search) : 0);
    Integer typeSearch = (isNumber ? 1 : 2);



    Pageable pageable = new PageRequest(start, limit);

    Page<GridOrdenPracticaDTO> ordenes =
        ordenManager.findOrdenesBySearch(typeSearch, codigoOrdenTipo, ordenId, paciente, pageable);

    List<GridOrdenPracticaDTO> aux = new ArrayList<GridOrdenPracticaDTO>();

    for (GridOrdenPracticaDTO o : ordenes.getContent()) {
      // PacienteOrdenPracticaDTO dto = transformoOrdenToPacienteOrdenPracticaDTO(o);
      String practicas = "";
      List<PracticasListDTO> practicasList = ordenManager.getAllPracticasByOrden(o.getOrdenId());
      for (PracticasListDTO op : practicasList) {
        practicas = practicas + "<tr> <td style='width:20%'>" + op.getCodigo()
            + "</td> <td style='width:50%'>" + op.getNombre() + "</td> " + " <td>"
            + getPracticaEstado(op) + "</td></tr>";
      }

      String listpracticas = "<table>" + practicas + "</table>";
      o.setPracticas(listpracticas);
      aux.add(o);
    }
    return new PageImpl<GridOrdenPracticaDTO>(aux, pageable, ordenes.getTotalElements());
  }

  @RequestMapping(value = ConstantControllers.AJAX_POST_REMOVE_OBSERVACION,
      method = RequestMethod.POST)
  public @ResponseBody String removeObservacion(@PathVariable("observacionId") Integer id) {
    String retorno = "ERROR";
    try {
      observacionManager.deleteObservaciones(Integer.valueOf(id));
      retorno = "OK";
    } catch (Exception e) {

    }
    return retorno;
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_AUTOCOMPLETE_NOMENCLADOR,
      method = RequestMethod.POST)
  public @ResponseBody List<ComboItemDTO> getAutocompleteNomenclador(
      @RequestParam(required = false, defaultValue = "") String query) {
    List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
    for (Nomenclador n : practicaManager.findNomencladorAutocomplete(query)) {
      retorno.add(new ComboItemDTO(n.getNomencladorId() + "",
          "[" + n.getCodigo() + "] - " + "[" + n.getTipo() + "] - " + n.getNombre()));
    }

    return retorno;
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_AUTOCOMPLETE_ESPECIALIADPRESTADOR,
      method = RequestMethod.POST)
  public @ResponseBody List<ComboItemDTO> getAutocompleteEspecialidadPrestador(
      @RequestParam(required = false, defaultValue = "") String query) {
    List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
    Integer especialidadPrestador = new Integer(2);
    for (Especialidad e : pacienteManager.findEspecialidadesAutocomplete(query,
        especialidadPrestador)) {
      retorno.add(new ComboItemDTO(e.getEspecialidadId() + "", e.getNombre()));
    }

    return retorno;
  }

  @RequestMapping(value = ConstantControllers.AJAX_POST_SAVECODIGONOMENCLADOR,
      method = RequestMethod.POST, headers = {"content-type=application/json"})
  public @ResponseBody String saveCodigoNomenclador(@RequestBody NomencladorDTO dto)
      throws Exception {

    Nomenclador nom = new Nomenclador();
    nom.setCodigo(dto.getCodigo());
    nom.setNombre(dto.getNombre());
    nom.setTipo(dto.getTipo());
    nom.setEliminado(0);

    practicaManager.add(nom);
    return nom.getNomencladorId() != null ? nom.getNomencladorId() + "" : "-1";
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_PROFESIONALESBYESPECIALIDAD,
      method = RequestMethod.GET)
  public @ResponseBody List<ComboItemDTO> getProfesionalesByEspecialidad(
      @RequestParam(required = false, defaultValue = "0") Integer especialidadId) {
    List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
    List<Profesional> profesionales =
        especialidadManager.findProfesionalByEspecialidadId(especialidadId);

    for (Profesional p : profesionales) {
      retorno
          .add(new ComboItemDTO(p.getProfesionalId() + "", p.getApellido() + " " + p.getNombre()));
    }
    return retorno;
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_PRESTADORESBYESPECIALIDAD,
      method = RequestMethod.GET)
  public @ResponseBody List<ComboItemDTO> getPrestadoresByEspecialidad(
      @RequestParam(required = false, defaultValue = "0") Integer especialidadId) {
    List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
    List<Prestadores> prestadores =
        especialidadManager.findPrestadorByEspecialidadId(especialidadId);

    for (Prestadores p : prestadores) {
      retorno.add(new ComboItemDTO(p.getPrestadorId() + "", p.getNombre()));
    }
    return retorno;
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_AUTOCOMPLETE_ESPECIALIDADES,
      method = RequestMethod.POST)
  public @ResponseBody List<ComboItemDTO> getAutocompleteEspecialidades(
      @RequestParam(required = false, defaultValue = "") String query) {
    List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
    Integer especialidadProfesional = new Integer(0);
    for (Especialidad e : pacienteManager.findEspecialidadesAutocomplete(query,
        especialidadProfesional)) {
      retorno.add(new ComboItemDTO(e.getEspecialidadId() + "", e.getNombre()));
    }

    return retorno;
  }

  @RequestMapping(value = ConstantControllers.ADD_ORDEN, method = RequestMethod.POST)
  public String addOrden(ModelMap map, @ModelAttribute(value = "ordenDto") OrdenDTO ordenDto,
      BindingResult result) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    ordenDto.setEstado(Util.getEstadoInicial(ordenDto));
    Orden orden = transformDtoToOrden(ordenDto);
    orden.setFecha(new Date());
    orden.getOrdenWorkflows()
        .add(new OrdenWorkflow(orden, user.getUsername(), orden.getEstado(), new Date()));
    if (ordenDto.getObservacion() != null && !ordenDto.getObservacion().trim().equals("")) {
      orden.getObservacioneses()
          .add(new Observaciones(orden, ordenDto.getObservacion(), user.getUsername(), new Date()));
    }

    if (ordenDto.getMonto() != null && ordenDto.getMonto() > 0) {
      if (ordenDto.getOrdenTipo().getOrdenTipoId().intValue() == 1
          || ordenDto.getOrdenTipo().getOrdenTipoId().intValue() == 3) {
        // Caja
        Caja caja = new Caja();
        if (ordenDto.getOrdenTipo().getOrdenTipoId().intValue() == 1) {
          caja.setConcepto(Util.CONCEPTO_INGRESO_ORDENCONSULTA);
        }

        if (ordenDto.getOrdenTipo().getOrdenTipoId().intValue() == 3) {
          caja.setConcepto(Util.CONCEPTO_INGRESO_ORDENPRACTICA);
        }

        caja.setIngreso(ordenDto.getMonto());
        caja.setEgreso(0.00);
        caja.setFecha(new Date());

        cajaManager.add(caja);
        CajaOrden cajaorden = new CajaOrden(caja, orden);
        Set<CajaOrden> cajaordenes = new HashSet<CajaOrden>();
        cajaordenes.add(cajaorden);
        orden.setCajaOrdens(cajaordenes);
      }
    }

    // Orden
    ordenManager.add(orden);
    // System.out.println("gustavito: " + orden.getOrdenId());
    lastOrdenId = orden.getOrdenId();

    // Fuera de Cartilla
    if (ordenDto.getFueraCartilla()) {
      OrdenFueraCartilla ofc = new OrdenFueraCartilla();
      ofc.setEntidad(ordenDto.getEntidad());
      ofc.setObservacion(ordenDto.getObservacionFueraCartilla());
      ofc.setOrdenId(orden.getOrdenId());
      ordenManager.add(ofc);
    }

    String redirect = "";
    if (orden.getOrdenTipo().getCodigo().intValue() == 100) {
      List<ComboItemDTO> items = new ArrayList<ComboItemDTO>();
      String nombre = "";
      OrdenDTO oDto = transformOrdenToDto(ordenManager.findOrdenById(orden.getOrdenId()));
      if (oDto.getEspecialidad() != null) {
        if (oDto.getEspecialidad() != null) {
          Especialidad e = especialidadManager.findEspecialidadById(oDto.getEspecialidad());
          nombre = (e != null) ? e.getNombre() : "";
        }
        List<Profesional> profesionales =
            especialidadManager.findProfesionalByEspecialidadId(oDto.getEspecialidad());
        items = getProfesionalDTOList(profesionales);
      } else {
        items = getProfesionalDTOList(profesionalManager.findAll());
      }
      map.addAttribute("profesionales", items);
      map.addAttribute("ordenDto", oDto);
      map.addAttribute("especialidadView", nombre);
      return ConstantRedirect.VIEW_FORM_EDIT_CONSULTA;

    }
    if (orden.getOrdenTipo().getCodigo().intValue() == 101) {
      redirect = ConstantControllers.MAIN_CONSULTA_ODONTOLOGICA;
    }
    if (orden.getOrdenTipo().getCodigo().intValue() == 102) {
      OrdenDTO ordenDt = transformOrdenToDto(ordenManager.findOrdenById(orden.getOrdenId()));
      Especialidad e = null;
      if (ordenDt.getEspecialidad() != null) {
        e = especialidadManager.findEspecialidadById(ordenDt.getEspecialidad());
      }

      List<Profesional> profesionales =
          especialidadManager.findProfesionalByEspecialidadId(ordenDt.getEspecialidad());

      List<Prestadores> prestadores =
          especialidadManager.findPrestadorByEspecialidadId(ordenDt.getEspecialidadPrestador());

      User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      int observacionCount = ordenDto.getObservacioneses().size();
      map.addAttribute("ordenDto", ordenDt);
      map.addAttribute("observacionCount", observacionCount);
      map.addAttribute("userNameLogged", u.getUsername());
      map.addAttribute("ordenEstadosList", Util.getOrdenEstadosList());
      map.addAttribute("profesionales", getProfesionalDTOList(profesionales));
      map.addAttribute("prestadores", getPrestadorDTOList(prestadores));
      map.addAttribute("especialidadView", e == null ? null : e.getNombre());
      map.addAttribute("listNomencladorTipo", practicaManager.findNomecladorTipo());

      return ConstantRedirect.VIEW_FORM_EDIT_ORDEN;
    }

    return "redirect:" + redirect;
  }

  @RequestMapping(value = ConstantControllers.DELETE_ORDEN, method = RequestMethod.POST)
  public String deleteOrden(ModelMap map, @ModelAttribute(value = "ordenDto") OrdenDTO dto) {
    int message = Util.MESSAGE_SUCCESS;
    String redirect = "";
    try {
      // Orden orden = ordenManager.findOrdenById(dto.getOrdenId();
      ordenManager.delete(dto.getOrdenId());
      if (dto.getOrdenTipo().getCodigo().intValue() == 100) {
        redirect = ConstantRedirect.VIEW_MAIN_CONSULTA;
      }
      if (dto.getOrdenTipo().getCodigo().intValue() == 101) {
        redirect = ConstantRedirect.VIEW_MAIN_CONSULTA;
      }
      if (dto.getOrdenTipo().getCodigo().intValue() == 102) {
        redirect = ConstantRedirect.VIEW_MAIN_ORDEN;
      }


    } catch (Exception ex) {
      ex.printStackTrace();
    }



    return redirect;
  }

  @RequestMapping(value = ConstantControllers.EDIT_ORDEN, method = RequestMethod.POST)
  public String editOrden(ModelMap map, @ModelAttribute(value = "ordenDto") OrdenDTO dto) {
    int message = Util.MESSAGE_SUCCESS;
    Orden orden = ordenManager.findOrdenById(dto.getOrdenId());
    boolean isRolAdmin = false;

    try {

      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      List<GrantedAuthority> ga = new ArrayList(user.getAuthorities());
      GrantedAuthority rol = ga.get(0);
      isRolAdmin = rol.getAuthority().equals("ROLE_ADMIN") ? true : false;

      List<OrdenPracticaDTO> practicas = dto.getOrdenpracticaListEdit();

      // Persisto Observacion
      if (dto.getObservacion() != null && !dto.getObservacion().trim().equals("")) {
        // observacionManager.add(new Observaciones(orden, dto.getObservacion(), user.getUsername(),
        // new Date()));
        orden.getObservacioneses()
            .add(new Observaciones(orden, dto.getObservacion(), user.getUsername(), new Date()));
      }

      // Persiste Practicas
      if (isRolAdmin) {
        for (OrdenPractica o : orden.getOrdenPracticas()) {
          ordenManager.deleteOrdenPractica(o.getOrden().getOrdenId());
        }

        Set<OrdenPractica> persistOrdenPracticaList = new HashSet<OrdenPractica>();
        for (OrdenPracticaDTO opdto : practicas) {
          if (opdto.getPracticaId() != null) {
            Nomenclador p = new Nomenclador();
            p.setNomencladorId(opdto.getPracticaId());
            OrdenPractica op = new OrdenPractica();
            op.setFecha(new Date());
            op.setOrden(orden);
            op.setNomenclador(p);
            op.setEstado(opdto.getEstado());
            op.setValor(opdto.getValor());
            op.setAutorizarAutomatico(Util.parseToDate(opdto.getAutorizarAutomatico()));
            op.setPiezaDental(opdto.getPiezaDental());

            persistOrdenPracticaList.add(op);
          }
        }
        orden.setOrdenPracticas(persistOrdenPracticaList);
      }

      // Persiste Estado
      if (dto.getEstado() != null && !orden.getEstado().equals(dto.getEstado())) {
        OrdenWorkflow ow =
            new OrdenWorkflow(orden, user.getUsername(), dto.getEstado(), new Date());
        orden.getOrdenWorkflows().add(ow);
        orden.setEstado(dto.getEstado());
      }

      if (isRolAdmin) {
        // Prestador
        if (dto.getEspecialidadPrestador() != null && dto.getPrestadorId() != null) {
          Set<OrdenPrestador> ordenPrestadores = new HashSet<OrdenPrestador>();
          OrdenPrestador opr = transformDtoToOrdenPrestador(dto);
          opr.setOrden(orden);
          ordenPrestadores.add(opr);
          ordenManager.deleteOrdenPrestador(orden.getOrdenId());
          orden.setOrdenPrestadors(ordenPrestadores);
        }
      }

      // requisitos
      orden.setReqCredecial(Util.getByteFlag(dto.isReqCredecial()));
      orden.setReqMonotributista(Util.getByteFlag(dto.isReqMonotributista()));
      orden.setReqOrdenMedico(Util.getByteFlag(dto.isReqOrdenMedico()));
      orden.setReqReciboSueldo(Util.getByteFlag(dto.isReqReciboSueldo()));

      // fuera de cartilla
      orden.setFueraCartilla(dto.getFueraCartilla() == true ? 1 : 0);
      OrdenFueraCartilla ofc = ordenManager.findOrdenFueraCartilla(orden.getOrdenId());
      if (dto.getFueraCartilla()) {
        ordenManager.deleteOrdenProfesional(dto.getOrdenId());
        orden.setOrdenProfesionals(null);
        if (ofc != null) {
          ofc.setEntidad(dto.getEntidad());
          ofc.setObservacion(dto.getObservacionFueraCartilla());
          ofc.setOrdenId(dto.getOrdenId());
          ordenManager.editFueraCartilla(ofc);

        } else {
          ofc = new OrdenFueraCartilla();
          ofc.setEntidad(dto.getEntidad());
          ofc.setObservacion(dto.getObservacionFueraCartilla());
          ofc.setOrdenId(dto.getOrdenId());
          ordenManager.add(ofc);
        }
      } else {
        if (ofc != null) {
          ordenManager.deleteOrdenFueraCartilla(ofc.getId());
        }

        // Profesional y la especialidad relacionada a la orden
        if (dto.getProfesionalId() != null) {
          Set<OrdenProfesional> ordenProfesionals = new HashSet<OrdenProfesional>();
          OrdenProfesional op = new OrdenProfesional();
          op.setProfesional(profesionalManager.findProfesionalById(dto.getProfesionalId()));
          op.setOrden(orden);
          op.setEspecialidadId(dto.getEspecialidad());
          ordenProfesionals.add(op);
          ordenManager.deleteOrdenProfesional(orden.getOrdenId());
          orden.setOrdenProfesionals(ordenProfesionals);
        }
      }

      // actualizo monto

      if (orden.getCajaOrdens().isEmpty()) {

        if (dto.getMonto() != null && dto.getMonto() > 0 && dto.getMonto()
            .doubleValue() != (orden.getMonto() == null ? 0 : orden.getMonto().doubleValue())) {

          // Caja
          Caja caja = new Caja();
          if (orden.getOrdenTipo().getOrdenTipoId().intValue() == 1) {
            caja.setConcepto(Util.CONCEPTO_INGRESO_ORDENCONSULTA);
          }

          if (orden.getOrdenTipo().getOrdenTipoId().intValue() == 3) {
            caja.setConcepto(Util.CONCEPTO_INGRESO_ORDENPRACTICA);
          }

          caja.setIngreso(dto.getMonto());
          caja.setEgreso(0.00);
          caja.setFecha(new Date());

          cajaManager.add(caja);
          CajaOrden cajaorden = new CajaOrden(caja, orden);
          Set<CajaOrden> cajaordenes = new HashSet<CajaOrden>();
          cajaordenes.add(cajaorden);
          orden.setCajaOrdens(cajaordenes);

        }

      } else {
        CajaOrden coo = null;
        for (CajaOrden co : orden.getCajaOrdens()) {
          co.getCaja().setIngreso(dto.getMonto());
          coo = co;
        }

        orden.getCajaOrdens().clear();
        Set<CajaOrden> cajaordenes = new HashSet<CajaOrden>();
        cajaordenes.add(coo);
        orden.setCajaOrdens(cajaordenes);
        coo.getCaja().setFecha(new Date());
        cajaManager.edit(coo.getCaja());

      }

      orden.setMonto(dto.getMonto());
      // Actualizo Orden
      ordenManager.edit(orden);

      // Actualizo Orden Document
      // Historia Cinica - Archivos Adjuntos
      if (isRolAdmin) {
        List<OrdenDocument> documents = new ArrayList<OrdenDocument>();
        List<OrdenDocument> documentsTotal = new ArrayList<OrdenDocument>();
        List<OrdenDocument> documentsOld =
            ordenManager.finAllOrdenDocumentByOrdenId(orden.getOrdenId());
        for (OrdenDocumentDTO hc : dto.getHistoriasclinicas()) {
          if (hc.getDocumentId() != null) {
            OrdenDocument d = ordenManager.findOrdenDocumentById(hc.getDocumentId());
            if (d != null) {
              documents.add(d);
            }
          }
        }

        Formatter fmt = new Formatter();
        fmt.format("%08d", orden.getOrdenId());
        String nroOrden = fmt.toString();

        for (OrdenDocumentDTO hc : dto.getHistoriasclinicas()) {
          if (hc.getDocumentId() == null && hc.getFileData() != null
              && !hc.getFileData().isEmpty()) {
            OrdenDocument content = new OrdenDocument();
            content.setContent(hc.getFileData().getBytes());
            content.setFileName("HC_" + nroOrden + "_" + orden.getPaciente().getApellido() + "_"
                + orden.getPaciente().getNombre() + "_" + hc.getFileData().getOriginalFilename());
            content.setType(Util.DOCUMENT_TYPE);
            content.setFileType(hc.getFileData().getContentType());
            content.setOrdenId(orden.getOrdenId());
            content.setSize(hc.getFileData().getSize());

            documentsTotal.add(content);
          }
        }

        for (OrdenDocument od : documentsOld) {
          ordenManager.deleteOrdenDocument(od.getDocumentId());
        }

        if (documents.size() > 0) {
          documentsTotal.addAll(documents);
        }

        for (OrdenDocument od : documentsTotal) {
          ordenManager.add(od);
        }
      }
    } catch (Exception ex) {
      message = Util.MESSAGE_ERROR;
    }

    String redirect = "";
    if (orden.getOrdenTipo().getCodigo().intValue() == 100) {
      redirect = "/formEditConsulta/" + orden.getOrdenId();

    }
    if (orden.getOrdenTipo().getCodigo().intValue() == 101) {
      redirect = ConstantControllers.MAIN_CONSULTA_ODONTOLOGICA;
    }
    if (orden.getOrdenTipo().getCodigo().intValue() == 102) {
      redirect = "/formEditOrden/" + orden.getOrdenId();
    }

    map.addAttribute("message", message);

    return "redirect:" + redirect;
  }

  @RequestMapping(value = ConstantControllers.MAIN_ORDEN_PRACTICA, method = RequestMethod.GET)
  public String mainOrden(ModelMap map) {
    // map.addAttribute("ordenList", ordenManager.findAll().get(1));
    return ConstantRedirect.VIEW_MAIN_ORDEN;
  }

  @RequestMapping(value = ConstantControllers.ORDEN_MESSAGE, method = RequestMethod.GET)
  public String ordenMessage(ModelMap map) {
    map.addAttribute("ordenId", lastOrdenId);
    return ConstantRedirect.VIEW_ORDEN_MESSAGE;
  }

  @RequestMapping(value = ConstantControllers.MAIN_CONSULTA, method = RequestMethod.GET)
  public String mainConsulta(ModelMap map) {
    // map.addAttribute("ordenList", ordenManager.findAll());
    return ConstantRedirect.VIEW_MAIN_CONSULTA;
  }

  @RequestMapping(value = ConstantControllers.MAIN_CONSULTA_ODONTOLOGICA,
      method = RequestMethod.GET)
  public String mainConsultaOdontologica(ModelMap map) {
    // map.addAttribute("ordenList", ordenManager.findAll());
    return ConstantRedirect.VIEW_MAIN_CONSULTA_ODONTOLOGICA;
  }

  private String getPracticaEstado(PracticasListDTO op) {
    String retorno = "";
    Date fechaActual = new Date();
    if (op.getAutorizarAutomatico() != null
        && fechaActual.compareTo(op.getAutorizarAutomatico()) < 0) {
      return "<span style='color:white;background: grey'>" + "PENDIENTE AL "
          + Util.parseToStringDate(op.getAutorizarAutomatico()) + "</span>";

    } else {

      if (op.getEstado() != null && op.getEstado().equals(ConstantOrdenEstado.ANULADO)) {
        return "<span style='color:white;background: tomato'>" + ConstantOrdenEstado.ANULADO
            + "</span>";
      }
      if (op.getEstado() != null
          && op.getEstado().equals(ConstantOrdenEstado.AUTORIZACION_DIRECTA)) {
        return "<span style='color:white;background: green'>"
            + ConstantOrdenEstado.AUTORIZACION_DIRECTA + "</span>";
      }
      if (op.getEstado() != null
          && op.getEstado().equals(ConstantOrdenEstado.AUTORIZADA_POR_AFILIACIONES)) {
        return "<span style='color:white;background: green'>"
            + ConstantOrdenEstado.AUTORIZADA_POR_AFILIACIONES + "</span>";
      }
      if (op.getEstado() != null
          && op.getEstado().equals(ConstantOrdenEstado.AUTORIZADA_POR_AUDITORIA)) {
        return "<span style='color:white;background: green'>"
            + ConstantOrdenEstado.AUTORIZADA_POR_AUDITORIA + "</span>";
      }
      if (op.getEstado() != null
          && op.getEstado().equals(ConstantOrdenEstado.PENDIENTE_AFILIACIONES)) {
        return "<span  style='color:white;background: sienna'>"
            + ConstantOrdenEstado.PENDIENTE_AFILIACIONES + "</span>";
      }
      if (op.getEstado() != null
          && op.getEstado().equals(ConstantOrdenEstado.PENDIENTE_AUDITORIA)) {
        return "<span  style='color:white;background: sienna'>"
            + ConstantOrdenEstado.PENDIENTE_AUDITORIA + "</span>";
      }
      if (op.getEstado() != null && op.getEstado().equals(ConstantOrdenEstado.RECHAZADA)) {
        return "<span style='color:white;background: gray'>" + ConstantOrdenEstado.RECHAZADA
            + "</span>";
      }
      if (op.getEstado() != null
          && op.getEstado().equals(ConstantOrdenEstado.RECHAZADA_POR_AFILIACIONES)) {
        return "<span style='color:white;background: gray'>"
            + ConstantOrdenEstado.RECHAZADA_POR_AFILIACIONES + "</span>";
      }
      if (op.getEstado() != null
          && op.getEstado().equals(ConstantOrdenEstado.RECHAZADA_POR_AUDITORIA)) {
        return "<span style='color:white;background: gray'>"
            + ConstantOrdenEstado.RECHAZADA_POR_AUDITORIA + "</span>";
      }
    }
    return retorno;
  }

  public Paciente transformDtoToPaciente(PacienteDTO dto) {
    Paciente paciente = new Paciente();
    paciente.setPacienteId(dto.getPacienteId());
    paciente.setDni((dto.getDni() == null) ? "" : dto.getDni().toString());
    paciente.setApellido(dto.getApellido());
    paciente.setNombre(dto.getNombre());
    paciente.setDomicilio(dto.getDomicilio());
    paciente.setFechaNacimiento(Util.parseToDate(dto.getFechaNacimiento()));
    paciente.setCoseguro(dto.getCoseguro() ? new Byte("1") : new Byte("0"));
    paciente.setMail(dto.getMail());
    paciente.setTelefono(dto.getTelefono());
    paciente.setProvincia(dto.getProvincia());

    // for (ObraSocialDTO os : dto.getObrasocialListEdit()) {
    // if (os.getObrasocialId() != null) {
    // Obrasocial obrasocial = obrasocialManager.findObraSocialById(os.getObrasocialId());
    // PacienteObrasocial po = new PacienteObrasocial();
    // po.setObrasocial(obrasocial);
    // po.setFecha(new Date());
    // po.setNroCredencial(os.getCredencial());
    // Byte isOriginalCredential = os.getOriginal().equals("on") ? new Byte("1") : new Byte("0");
    // po.setProvisorio(isOriginalCredential);
    // po.setPaciente(paciente);
    //
    // paciente.getPacienteObrasocials().add(po);
    // }
    // }

    return paciente;

  }

  public PacienteDTO transformPacienteToDto(Paciente p) {
    PacienteDTO dto = new PacienteDTO();
    dto.setPacienteId(p.getPacienteId());
    dto.setEliminado(p.getEliminado().intValue());
    dto.setDni(p.getDni());
    dto.setApellido(p.getApellido());
    dto.setNombre(p.getNombre());
    dto.setDomicilio(p.getDomicilio());
    dto.setFechaNacimiento("" + p.getFechaNacimiento());
    dto.setCoseguro(p.getCoseguro().intValue() == 1 ? true : false);
    dto.setCheckedLiberado(p.getCoseguro().intValue() == 1 ? "checked" : "");
    dto.setMail(p.getMail());
    dto.setTelefono(p.getTelefono());
    dto.setProvincia(p.getProvincia());
    dto.setEliminadoView(p.getEliminado().intValue() == 0 ? "ACTIVO" : "INACTIVO");

    if (p.getPaciente() != null && p.getPaciente().getPacienteId() != null) {
      dto.setPacienteTitular(transformPacienteToDto(
          pacienteManager.fin1dPacienteById(p.getPaciente().getPacienteId())));
    }

    Obrasocial os = obrasocialManager.findObraSocialById(p.getObrasocialId());
    // ObraSocialDTO o = new ObraSocialDTO(po.getObrasocial().getObrasocialId(),
    // po.getObrasocial().getNombre(),
    // po.getNroCredencial(), po.getProvisorio() == 1 ? "checked" : "");
    ObraSocialDTO o = new ObraSocialDTO();
    o.setNombre(os.getNombre());
    o.setCredencial(p.getNroCredencial() + "-" + p.getNroCredencialSufijo());
    dto.setObrasocial(o);

    // Obrasocial os = obrasocialManager.findObraSocialById(p.getObrasocialId());
    // dto.setCrdencial(p.getNroCredencial());
    // ObraSocialDTO o = new ObraSocialDTO();
    // o.setNombre(os.getNombre());
    // o.setObrasocialId(os.getObrasocialId());
    // dto.setObrasocial(o);
    // for (PacienteObrasocial po : p.getPacienteObrasocials()) {
    // ObraSocialDTO o = new ObraSocialDTO(po.getObrasocial().getObrasocialId(),
    // po.getObrasocial().getNombre(),
    // po.getNroCredencial(), po.getProvisorio() == 1 ? "checked" : "");
    // dto.getObrasocialList().add(o);
    // dto.setObrasocial(o);
    // dto.setOriginal(po.getProvisorio().intValue() == 1 ? true : false);
    // break;
    // }

    for (Paciente ad : p.getPacientes()) {
      PacienteDTO dtoad = new PacienteDTO();
      dtoad.setPacienteId(ad.getPacienteId());
      dtoad.setApellido(ad.getApellido());
      dtoad.setNombre(ad.getNombre());
      dtoad.setDomicilio(ad.getDomicilio());
      dtoad.setFechaNacimiento("" + ad.getFechaNacimiento());
      dtoad.setCoseguro(ad.getCoseguro().intValue() == 1 ? true : false);
      dtoad.setCheckedLiberado(p.getCoseguro().intValue() == 1 ? "checked" : "");
      dtoad.setMail(ad.getMail());
      dtoad.setTelefono(ad.getTelefono());
      dtoad.setDni(ad.getDni());
      dtoad.setEliminado(ad.getEliminado().intValue());

      // for (PacienteObrasocial poo : ad.getPacienteObrasocials()) {
      // dtoad.setCrdencial(poo.getNroCredencial());
      // break;
      // }

      dto.getAdherentes().add(dtoad);
    }

    dto.setEliminado(p.getEliminado().intValue());
    return dto;
  }

  public List<Observaciones> getObservacionesFromDto(List<ObservacionesDTO> list) {
    List<Observaciones> retorno = new ArrayList<Observaciones>();
    for (ObservacionesDTO dto : list) {
      Observaciones obs = new Observaciones();
      obs.setFecha(dto.getFecha());
      obs.setObservacion(dto.getObservacion());
      obs.setObservacionId(dto.getObservacionId());
      obs.setOrden(transformDtoToOrden(dto.getOrden()));
      obs.setUserName(dto.getUserName());
      retorno.add(obs);
    }

    return retorno;
  }

  public List<OrdenWorkflow> getOrdenWorkflowFromDto(List<OrdenWorkflowDTO> list) {
    List<OrdenWorkflow> retorno = new ArrayList<OrdenWorkflow>();
    for (OrdenWorkflowDTO dto : list) {
      OrdenWorkflow ow = new OrdenWorkflow();
      ow.setEstado(dto.getEstado());
      ow.setOrden(transformDtoToOrden(dto.getOrden()));
      ow.setOrdenWorkflowId(dto.getOrdenWorkflowId());
      ow.setUserName(dto.getUserName());

      retorno.add(ow);
    }
    return retorno;
  }

  private PacienteOrdenPracticaDTO transformoOrdenToPacienteOrdenPracticaDTO(Orden orden) {
    PacienteOrdenPracticaDTO dto = new PacienteOrdenPracticaDTO();
    dto.setId(orden.getOrdenId());

    Integer especialidadId = new Integer(0);
    for (OrdenProfesional op : orden.getOrdenProfesionals()) {
      if (op.getProfesional() != null) {
        especialidadId = op.getEspecialidadId();
        if (especialidadId != null) {
          Especialidad e = especialidadManager.findEspecialidadById(especialidadId);
          dto.setEspecialidad(e.getNombre());
        }

        dto.setSolicitante("<b>Profesional: </b>" + op.getProfesional().getApellido() + " "
            + op.getProfesional().getNombre());

      }
    }

    if (orden.getFueraCartilla().intValue() == 1) {
      OrdenFueraCartilla ofc = ordenManager.findOrdenFueraCartilla(orden.getOrdenId());
      dto.setSolicitante("<b>Entidad: </b>" + ofc.getEntidad());
    }

    dto.setFecha(orden.getFecha() + "");
    // botones de acciones
    String action = "";
    if (orden.getOrdenTipo().getCodigo().intValue() == 100) {
      action = "formEditConsulta";
    }

    if (orden.getOrdenTipo().getCodigo().intValue() == 101) {
      action = "formEditOrden";
    }

    if (orden.getOrdenTipo().getCodigo().intValue() == 102) {
      action = "formEditOrden";
    }

    String botonEdit = "<a target='_blank' class='btn btn-info btn-xs' href='/nuova/" + action + "/"
        + orden.getOrdenId() + "'><span class='icon icon-edit'></span></a>";

    // String botonDelete = "<a class='btn btn-danger btn-xs' href='/nuova/formDeleteOrden/"
    // + dto.getOrdenId() + "'><span class='icon icon-remove'></span></a>";

    String botonPrint =
        "<a class='btn btn-default btn-xs' data-toggle='modal' data-target='#myModal' onClick='showReport("
            + orden.getOrdenId() + ")'><span class='icon icon-print'></span></a>";
    // String botonPrint = "<button type='button' class='btn btn-info btn-lg' data-toggle='modal'
    // data-target='#myModal'>Open Modal</button>";

    dto.setAcciones(botonEdit + botonPrint);

    dto.setFueraCartilla(orden.getFueraCartilla());

    return dto;
  }

  private OrdenDTO transformOrdenToDto(Orden orden) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<GrantedAuthority> ga = new ArrayList(user.getAuthorities());
    GrantedAuthority rol = ga.get(0);
    boolean isRolAdmin = rol.getAuthority().equals("ROLE_ADMIN") ? true : false;

    OrdenDTO dto = new OrdenDTO();
    dto.setOrdenId(orden.getOrdenId());
    dto.setFecha(Util.parseToStringDate(orden.getFecha()));
    dto.setEstado(orden.getEstado());
    dto.setMonto(orden.getMonto());

    dto.setFueraCartilla(orden.getFueraCartilla().intValue() == 1 ? true : false);

    // Paciente
    dto.setPaciente(transformPacienteToDto(orden.getPaciente()));

    // requisitos
    dto.setReqCredecial(orden.getReqCredecial().intValue() == 1 ? true : false);
    dto.setReqMonotributista(orden.getReqMonotributista().intValue() == 1 ? true : false);
    dto.setReqOrdenMedico(orden.getReqOrdenMedico().intValue() == 1 ? true : false);
    dto.setReqReciboSueldo(orden.getReqReciboSueldo().intValue() == 1 ? true : false);

    // Autorizacion
    dto.setPracticasListEdit(getPracticaDto(orden.getOrdenPracticas()));

    // Observaciones
    dto.setObservacioneses(getObservacionesDto(orden.getObservacioneses()));

    // Flujo de Estados
    dto.setOrdenWorkflows(getOrdenWorkflowToDto(orden.getOrdenWorkflows()));

    // Profesional
    Integer especialidadId = new Integer(0);
    for (OrdenProfesional op : orden.getOrdenProfesionals()) {
      if (op.getProfesional() != null) {
        dto.setEspecialidad(op.getEspecialidadId());
        dto.setProfesional(transformProfesionalToDto(op.getProfesional()));
        especialidadId = op.getEspecialidadId();
      }
    }

    if (!dto.getFueraCartilla() && dto.getProfesional() != null) {
      dto.setApellidoNombreProfesional(dto.getProfesional().getApellidoNombre());
      if (especialidadId != null) {
        Especialidad e = especialidadManager.findEspecialidadById(especialidadId);
        if (e != null)
          dto.setEspecialidadView(e.getNombre());
      }
    } else {
      OrdenFueraCartilla ofc = ordenManager.findOrdenFueraCartilla(dto.getOrdenId());
      String entidad = ofc != null ? ofc.getEntidad() : "";
      dto.setApellidoNombreProfesional("<b>Entidad: </b><br>" + entidad);
    }

    // Set de Estados
    if (dto.getProfesional() != null) {
      dto.setProfesionalId(dto.getProfesional().getProfesionalId());
    }

    if (orden.getEstado() != null && orden.getEstado().equals(ConstantOrdenEstado.ORDEN_INICIADA)) {
      dto.setEtiqestado(iniciada);
    }
    if (orden.getEstado() != null
        && orden.getEstado().equals(ConstantOrdenEstado.ORDEN_AUTORIZADA)) {
      dto.setEtiqestado(autorizada);
    }
    if (orden.getEstado() != null && orden.getEstado().equals(ConstantOrdenEstado.ORDEN_CERRADA)) {
      dto.setEtiqestado(cerrada);
    }
    if (orden.getEstado() != null
        && orden.getEstado().equals(ConstantOrdenEstado.ORDEN_EN_PROGRESO)) {
      dto.setEtiqestado(en_progreso);
    }
    if (orden.getEstado() != null
        && orden.getEstado().equals(ConstantOrdenEstado.ORDEN_PENDIENTE)) {
      dto.setEtiqestado(pendiente);
    }
    if (orden.getEstado() != null
        && orden.getEstado().equals(ConstantOrdenEstado.ORDEN_RECHAZADA)) {
      dto.setEtiqestado(rechazada);
    }
    if (orden.getEstado() != null && orden.getEstado().equals(ConstantOrdenEstado.ORDEN_ANULADA)) {
      dto.setEtiqestado(anulada);
    }
    // Historia Clinica
    List<OrdenDocument> documents = ordenManager.finAllOrdenDocumentByOrdenId(dto.getOrdenId());
    for (OrdenDocument od : documents) {
      OrdenDocumentDTO oddto = new OrdenDocumentDTO(od.getDocumentId(), od.getType(),
          od.getFileName(), od.getFileType(), od.getOrdenId(), null);

      dto.getHistoriasclinicas().add(oddto);
    }

    // Prestador
    for (OrdenPrestador op : orden.getOrdenPrestadors()) {
      dto.setEspecialidadPrestador(op.getEspecialidadId());
      Especialidad esp = especialidadManager.findEspecialidadById(op.getEspecialidadId());
      if (esp != null) {
        dto.setPrestadorId(op.getPrestadores().getPrestadorId());
        dto.setEspecialidadPrestadorView(esp.getNombre());
      }
    }

    // boton paciente
    String botonpaciente =
        dto.getPaciente().getApellido().toUpperCase() + ", " + dto.getPaciente().getNombre();
    dto.setBotonpaciente(botonpaciente);

    dto.setOrdenTipo(transformOrdenTipoToDto(orden.getOrdenTipo()));
    dto.setOrdenTipoDesc(dto.getOrdenTipo().getNombre());

    // botones de acciones
    String action = "";
    if (dto.getOrdenTipo().getCodigo().intValue() == 100) {
      action = "formEditConsulta";
    }

    if (dto.getOrdenTipo().getCodigo().intValue() == 101) {
      action = "formEditOrden";
    }

    if (dto.getOrdenTipo().getCodigo().intValue() == 102) {
      action = "formEditOrden";
    }

    String botonEdit = "<a class='btn btn-info btn-xs' href='/nuova/" + action + "/"
        + dto.getOrdenId() + " ' target='_blank'><span class='icon icon-edit'></span></a>";

    // String botonDelete = "<a class='btn btn-danger btn-xs' href='/nuova/formDeleteOrden/"
    // + dto.getOrdenId() + "'><span class='icon icon-remove'></span></a>";

    String botonPrint = "";
    if (isRolAdmin) {
      botonPrint =
          "<a class='btn btn-default btn-xs' data-toggle='modal' data-target='#myModal' onClick='showReport("
              + dto.getOrdenId() + ")'><span class='icon icon-print'></span></a>";
    }
    // String botonPrint = "<button type='button' class='btn btn-info btn-lg' data-toggle='modal'
    // data-target='#myModal'>Open Modal</button>";

    dto.setAcciones(botonEdit + botonPrint);

    // Fuera de cartilla
    OrdenFueraCartilla ofc = ordenManager.findOrdenFueraCartilla(orden.getOrdenId());
    if (dto.getFueraCartilla()) {
      if (ofc != null) {
        dto.setEntidad(ofc.getEntidad());
        dto.setObservacionFueraCartilla(ofc.getObservacion());
      }
    }

    return dto;
  }

  private List<ObservacionesDTO> getObservacionesDto(Set<Observaciones> list) {
    List<ObservacionesDTO> retorno = new ArrayList<ObservacionesDTO>();
    for (Observaciones o : list) {
      OrdenDTO odto = new OrdenDTO();
      odto.setOrdenId(o.getOrden().getOrdenId());
      ObservacionesDTO dto =
          new ObservacionesDTO(odto, o.getObservacion(), o.getUserName(), o.getFecha());
      dto.setObservacionId(o.getObservacionId());
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

  private List<OrdenPracticaDTO> getPracticaDto(Set<OrdenPractica> list) {
    List<OrdenPracticaDTO> retorno = new ArrayList<OrdenPracticaDTO>(0);
    for (OrdenPractica op : list) {
      Nomenclador p = op.getNomenclador();
      // p.setNombre("[" + p.getCodigo() + "] - [" + p.getTipo() + "] - " + p.getNombre());
      String nombrePractica = "[" + p.getCodigo() + "] - [" + p.getTipo() + "] - " + p.getNombre();
      Orden o = op.getOrden();
      // PracticaDTO dto = new PracticaDTO(p.getPracticaId(), "[" + p.getCodigo() + "]-" +
      // p.getNombre());
      OrdenPracticaDTO dto = new OrdenPracticaDTO();
      dto.setOrddenPracticaId(op.getOrddenPracticaId());
      dto.setOrdenId(o.getOrdenId());
      dto.setNombre(nombrePractica);
      dto.setPracticaId(p.getNomencladorId());
      dto.setEstado(op.getEstado());
      dto.setValor(op.getValor());
      dto.setAutorizarAutomatico(op.getAutorizarAutomatico() + "");
      dto.setPiezaDental(op.getPiezaDental());
      retorno.add(dto);
    }

    Collections.sort(retorno, new Comparator<OrdenPracticaDTO>() {
      public int compare(OrdenPracticaDTO a1, OrdenPracticaDTO a2) {
        return a1.getPracticaId().compareTo(a2.getPracticaId());
      }
    });

    return retorno;
  }

  public Orden transformDtoToOrden(OrdenDTO dto) {
    Orden orden = new Orden();
    orden.setEstado(dto.getEstado());
    orden.setFecha(Util.parseToDate(dto.getFecha()));
    orden.setOrdenId(dto.getOrdenId());
    orden.setReqCredecial(Util.getByteFlag(dto.isReqCredecial()));
    orden.setReqMonotributista(Util.getByteFlag(dto.isReqMonotributista()));
    orden.setReqOrdenMedico(Util.getByteFlag(dto.isReqOrdenMedico()));
    orden.setReqReciboSueldo(Util.getByteFlag(dto.isReqReciboSueldo()));
    orden.setPaciente(transformDtoToPaciente(dto.getPaciente()));
    orden.setOrdenTipo(transformDtoToOrdenTipo(dto.getOrdenTipo()));
    orden.setMonto(dto.getMonto() == null ? 0.00 : dto.getMonto());

    if (dto.getProfesionalId() != null) {
      Set<OrdenProfesional> ordenProfesionals = new HashSet<OrdenProfesional>();
      Profesional profesional = new Profesional();
      profesional.setProfesionalId(dto.getProfesionalId());
      OrdenProfesional op = new OrdenProfesional(orden, profesional);
      op.setEspecialidadId(dto.getEspecialidad());
      ordenProfesionals.add(op);
      orden.setOrdenProfesionals(ordenProfesionals);
    }

    // Prestador
    if (dto.getEspecialidadPrestador() != null && dto.getPrestadorId() != null) {
      orden.getOrdenPrestadors().add(transformDtoToOrdenPrestador(dto));
    }

    // Fuera de Cartilla
    orden.setFueraCartilla(dto.getFueraCartilla() == true ? 1 : 0);

    return orden;
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

  private OrdenTipo transformDtoToOrdenTipo(OrdenTipoDTO dto) {
    OrdenTipo retorno = new OrdenTipo();
    if (dto != null) {
      retorno = ordenManager.findOrdenTipoById(dto.getOrdenTipoId());
    }
    return retorno;
  }

  private ProfesionalDTO transformProfesionalToDto(Profesional p) {
    ProfesionalDTO dto = new ProfesionalDTO();
    dto.setApellido(p.getApellido());
    dto.setNombre(p.getNombre());
    dto.setApellidoNombre(p.getApellido() + " " + p.getNombre());
    dto.setTelefono(p.getTelefono());
    dto.setTituloProfesional(p.getTituloProfesional());
    dto.setRegistroNacional(p.getRegistroNacional());
    dto.setProfesionalId(p.getProfesionalId());
    dto.setHabilitacionSiprosa(
        p.getHabilitacionSiprosa() != null ? p.getHabilitacionSiprosa().toString() : "");
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

  private List<ComboItemDTO> getProfesionalDTOList(List<Profesional> list) {
    List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
    for (Profesional p : list) {
      retorno
          .add(new ComboItemDTO(p.getProfesionalId() + "", p.getApellido() + ", " + p.getNombre()));
    }
    return retorno;
  }

  private List<ComboItemDTO> getPrestadorDTOList(List<Prestadores> list) {
    List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
    for (Prestadores p : list) {
      retorno.add(new ComboItemDTO(p.getPrestadorId() + "", p.getNombre()));
    }
    return retorno;
  }

  private List<ComboItemDTO> getEspecialidadDTOList(List<Especialidad> list) {
    List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
    for (Especialidad e : list) {
      retorno.add(new ComboItemDTO(e.getEspecialidadId() + "", e.getNombre()));
    }
    return retorno;
  }

  private List<Double> getMontosOrden(OrdenTipoDTO dto) {
    List<Double> retorno = new ArrayList<Double>();
    if (dto.getMonto1().doubleValue() > 0.00) {
      retorno.add(dto.getMonto1());
    }

    if (dto.getMonto2().doubleValue() > 0.00) {
      retorno.add(dto.getMonto2());
    }

    if (dto.getMonto3().doubleValue() > 0.00) {
      retorno.add(dto.getMonto3());
    }

    return retorno;
  }

  private String getContentDispositionValue(String fileName, String typeFile) {
    StringBuilder contentDisposition = new StringBuilder("attachment");
    contentDisposition.append("; filename=").append('"').append(fileName).append(typeFile)
        .append('"');
    CharsetEncoder enc = StandardCharsets.US_ASCII.newEncoder();
    boolean canEncode = enc.canEncode(fileName);
    if (!canEncode) {

      try {
        URI uri = new URI(null, null, fileName, null);
        // for browsers supporting utf-8 encoding
        contentDisposition.append("; filename*=").append(Charsets.UTF_8).append("''")
            .append(uri.toASCIIString()).append(typeFile);
      } catch (URISyntaxException e) {

      }

    }

    return contentDisposition.toString();
  }

  private OrdenPrestador transformDtoToOrdenPrestador(OrdenDTO dto) {
    OrdenPrestador op = new OrdenPrestador();
    op.setEspecialidadId(dto.getEspecialidadPrestador());
    Prestadores p = new Prestadores();
    p.setPrestadorId(dto.getPrestadorId());
    op.setPrestadores(p);
    return op;
  }
}
