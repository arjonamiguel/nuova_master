package com.nuova.controller;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import com.nuova.dto.ComboItemDTO;
import com.nuova.dto.EspecialidadDTO;
import com.nuova.dto.ObraSocialDTO;
import com.nuova.dto.ObservacionesDTO;
import com.nuova.dto.OrdenDTO;
import com.nuova.dto.OrdenDocumentDTO;
import com.nuova.dto.OrdenPracticaDTO;
import com.nuova.dto.OrdenTipoDTO;
import com.nuova.dto.OrdenWorkflowDTO;
import com.nuova.dto.PacienteDTO;
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
import com.nuova.model.OrdenPractica;
import com.nuova.model.OrdenProfesional;
import com.nuova.model.OrdenTipo;
import com.nuova.model.OrdenWorkflow;
import com.nuova.model.Paciente;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

  private String iniciada = " <span  style='color:black;background:gold'>INICIADA</span>";
  private String autorizada = "<span style='color:white;background: green'>AUTORIZADA</span>";
  private String pendiente = "<span  style='color:white;background: sienna'>PENDIENTE</span>";
  private String en_progreso =
      " <span style='color:white;background: steelblue'>EN PROGRESO</span>";
  private String rechazada = "<span style='color:white;background: gray'>RECHAZADA</span>";
  private String cerrada = "<span style='color:white;background: black'>CERRADA</span>";
  private String anulada = "<span style='color:white;background: tomato'>ANULADA</span>";

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

    if (codigo == Util.ORDEN_CONSULTA) {
      OrdenTipoDTO otdto = transformOrdenTipoToDto(ot);
      List<Profesional> profesionales = profesionalManager.findAll();
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

  @RequestMapping(value = ConstantControllers.FORM_EDIT_ORDEN, method = RequestMethod.GET)
  public String formEditOrden(ModelMap map, @PathVariable("ordenId") Integer ordenId) {
    if (ordenId != null) {
      OrdenDTO ordenDto = transformOrdenToDto(ordenManager.findOrdenById(ordenId));
      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      int observacionCount = ordenDto.getObservacioneses().size();
      map.addAttribute("ordenDto", ordenDto);
      map.addAttribute("observacionCount", observacionCount);
      map.addAttribute("userNameLogged", user.getUsername());
      map.addAttribute("ordenEstadosList", Util.getOrdenEstadosList());
    }

    return ConstantRedirect.VIEW_FORM_EDIT_ORDEN;
  }

  @RequestMapping(value = ConstantControllers.FORM_EDIT_CONSULTA, method = RequestMethod.GET)
  public String formEditConsulta(ModelMap map, @PathVariable("ordenId") Integer ordenId) {
    if (ordenId != null) {
      OrdenDTO ordenDto = transformOrdenToDto(ordenManager.findOrdenById(ordenId));
      List<Profesional> profesionales = profesionalManager.findAll();
      map.addAttribute("profesionales", getProfesionalDTOList(profesionales));
      map.addAttribute("ordenDto", ordenDto);
    }

    return ConstantRedirect.VIEW_FORM_EDIT_CONSULTA;
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
  public @ResponseBody Page<OrdenDTO> getOrdenesPaginados(
      @PathVariable("codigoOrdenTipo") Integer codigoOrdenTipo,
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<Orden> ordenes = ordenManager.findOrdenesByPageable(pageable, codigoOrdenTipo);
    List<OrdenDTO> dtos = new ArrayList<OrdenDTO>();
    for (Orden o : ordenes) {
      OrdenDTO dto = transformOrdenToDto(o);
      dtos.add(dto);
    }

    return new PageImpl<OrdenDTO>(dtos, pageable, ordenes.getTotalElements());
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_CONSULTASBYPACIENTE_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<OrdenDTO> getConsultasByPacientePaginados(
      @PathVariable("pacienteId") Integer pacienteId,
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<Orden> ordenes = ordenManager.findConsultasByPageableANDPaciente(pageable, pacienteId);
    List<OrdenDTO> dtos = new ArrayList<OrdenDTO>();
    for (Orden o : ordenes) {
      OrdenDTO dto = transformOrdenToDto(o);
      dtos.add(dto);
    }

    return new PageImpl<OrdenDTO>(dtos, pageable, ordenes.getTotalElements());
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_PRACTICASBYPACIENTE_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<OrdenDTO> getPracticasByPacientePaginados(
      @PathVariable("pacienteId") Integer pacienteId,
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<Orden> ordenes = ordenManager.findPracticasByPageableANDPaciente(pageable, pacienteId);
    List<OrdenDTO> dtos = new ArrayList<OrdenDTO>();
    for (Orden o : ordenes) {
      OrdenDTO dto = transformOrdenToDto(o);
      dtos.add(dto);
    }

    return new PageImpl<OrdenDTO>(dtos, pageable, ordenes.getTotalElements());
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_SEARCH_ORDENES_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<OrdenDTO> getSearchOrdenesPaginados(
      @PathVariable("codigoOrdenTipo") Integer codigoOrdenTipo,
      @RequestParam(required = false, defaultValue = "") String search,
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<Orden> ordenes = ordenManager.findOrdenesBySearch(search, pageable, codigoOrdenTipo);
    List<OrdenDTO> dtos = new ArrayList<OrdenDTO>();
    for (Orden o : ordenes) {
      OrdenDTO dto = transformOrdenToDto(o);
      dtos.add(dto);
    }

    return new PageImpl<OrdenDTO>(dtos, pageable, ordenes.getTotalElements());
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_AUTOCOMPLETE_NOMENCLADOR,
      method = RequestMethod.POST)
  public @ResponseBody List<ComboItemDTO> getAutocompletePaciente(
      @RequestParam(required = false, defaultValue = "") String query) {
    List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
    for (Nomenclador n : practicaManager.findNomencladorAutocomplete(query)) {
      retorno.add(new ComboItemDTO(n.getNomencladorId() + "",
          "[" + n.getCodigo() + "] - " + "[" + n.getTipo() + "] - " + n.getNombre()));
    }

    return retorno;
  }

  @RequestMapping(value = ConstantControllers.ADD_ORDEN, method = RequestMethod.POST)
  public String addOrden(@ModelAttribute(value = "ordenDto") OrdenDTO ordenDto,
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

    if (ordenDto.getOrdenTipo().getOrdenTipoId().intValue() == 1
        || ordenDto.getOrdenTipo().getOrdenTipoId().intValue() == 2) {
      // Caja
      Caja caja = new Caja();
      if (ordenDto.getOrdenTipo().getOrdenTipoId().intValue() == 1) {
        caja.setConcepto(Util.CONCEPTO_INGRESO_ORDENCONSULTA);
      }

      if (ordenDto.getOrdenTipo().getOrdenTipoId().intValue() == 2) {
        caja.setConcepto(Util.CONCEPTO_INGRESO_ORDENCONSULTAODONTOLOGICA);
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

    // Orden
    ordenManager.add(orden);

    String redirect = "";
    if (orden.getOrdenTipo().getCodigo().intValue() == 100) {
      redirect = ConstantControllers.MAIN_CONSULTA;
    }
    if (orden.getOrdenTipo().getCodigo().intValue() == 101) {
      redirect = ConstantControllers.MAIN_CONSULTA_ODONTOLOGICA;
    }
    if (orden.getOrdenTipo().getCodigo().intValue() == 102) {
      redirect = ConstantControllers.MAIN_ORDEN_PRACTICA;
    }

    return "redirect:" + redirect;
  }

  @RequestMapping(value = ConstantControllers.EDIT_ORDEN, method = RequestMethod.POST)
  public String editOrden(ModelMap map, @ModelAttribute(value = "ordenDto") OrdenDTO dto) {
    int message = Util.MESSAGE_SUCCESS;
    Orden orden = ordenManager.findOrdenById(dto.getOrdenId());

    try {

      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      List<OrdenPracticaDTO> practicas = dto.getOrdenpracticaListEdit();

      // Persisto Observacion
      if (dto.getObservacion() != null && !dto.getObservacion().trim().equals("")) {
        // observacionManager.add(new Observaciones(orden, dto.getObservacion(), user.getUsername(),
        // new Date()));
        orden.getObservacioneses()
            .add(new Observaciones(orden, dto.getObservacion(), user.getUsername(), new Date()));
      }

      // Persiste Practicas
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

          persistOrdenPracticaList.add(op);
        }
      }
      orden.setOrdenPracticas(persistOrdenPracticaList);

      // Persiste Estado
      if (dto.getEstado() != null && !orden.getEstado().equals(dto.getEstado())) {
        OrdenWorkflow ow =
            new OrdenWorkflow(orden, user.getUsername(), dto.getEstado(), new Date());
        orden.getOrdenWorkflows().add(ow);
        orden.setEstado(dto.getEstado());
      }

      if (orden.getOrdenTipo().getCodigo().intValue() == 100) {
        Set<OrdenProfesional> ordenProfesionals = new HashSet<OrdenProfesional>();
        OrdenProfesional op = new OrdenProfesional();
        op.setProfesional(profesionalManager.findProfesionalById(dto.getProfesionalId()));
        op.setOrden(orden);
        ordenProfesionals.add(op);
        ordenManager.deleteOrdenProfesional(orden.getOrdenId());
        orden.setOrdenProfesionals(ordenProfesionals);
      }

      // requisitos
      orden.setReqCredecial(Util.getByteFlag(dto.isReqCredecial()));
      orden.setReqMonotributista(Util.getByteFlag(dto.isReqMonotributista()));
      orden.setReqOrdenMedico(Util.getByteFlag(dto.isReqOrdenMedico()));
      orden.setReqReciboSueldo(Util.getByteFlag(dto.isReqReciboSueldo()));

      // Actualizo Orden
      ordenManager.edit(orden);

      // Actualizo Orden Document
      // Historia Cinica - Archivos Adjuntos
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

      for (OrdenDocumentDTO hc : dto.getHistoriasclinicas()) {
        if (hc.getDocumentId() == null && hc.getFileData() != null && !hc.getFileData().isEmpty()) {
          OrdenDocument content = new OrdenDocument();
          content.setContent(hc.getFileData().getBytes());
          content.setFileName(hc.getFileData().getOriginalFilename());
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

    } catch (Exception ex) {
      message = Util.MESSAGE_ERROR;
    }

    String redirect = "";
    if (orden.getOrdenTipo().getCodigo().intValue() == 100) {
      redirect = ConstantControllers.MAIN_CONSULTA;
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
    // map.addAttribute("ordenList", ordenManager.findAll());
    return ConstantRedirect.VIEW_MAIN_ORDEN;
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

  public Paciente transformDtoToPaciente(PacienteDTO dto) {
    Paciente paciente = new Paciente();
    paciente.setPacienteId(dto.getPacienteId());
    paciente.setDni(dto.getDni());
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
    o.setCredencial(p.getNroCredencial());
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

  private OrdenDTO transformOrdenToDto(Orden orden) {
    OrdenDTO dto = new OrdenDTO();
    dto.setOrdenId(orden.getOrdenId());
    dto.setFecha(orden.getFecha() + "");
    dto.setEstado(orden.getEstado());

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
    for (OrdenProfesional op : orden.getOrdenProfesionals()) {
      if (op.getProfesional() != null) {
        dto.setProfesional(transformProfesionalToDto(op.getProfesional()));
      }
    }
    if (dto.getProfesional() != null) {
      dto.setApellidoNombreProfesional(dto.getProfesional().getApellidoNombre());
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
        + dto.getOrdenId() + "'><span class='icon icon-edit'></span></a>";

    String botonDelete = "<a class='btn btn-danger btn-xs' href='/nuova/formDeleteOrden/"
        + dto.getOrdenId() + "'><span class='icon icon-remove'></span></a>";

    String botonPrint =
        "<a class='btn btn-default btn-xs' data-toggle='modal' data-target='#myModal' onClick='showReport("
            + dto.getOrdenId() + ")'><span class='icon icon-print'></span></a>";
    // String botonPrint = "<button type='button' class='btn btn-info btn-lg' data-toggle='modal'
    // data-target='#myModal'>Open Modal</button>";

    dto.setAcciones(botonEdit + botonDelete + botonPrint);

    return dto;
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
      @Override
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
      @Override
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
      p.setNombre("[" + p.getCodigo() + "] - [" + p.getTipo() + "] - " + p.getNombre());
      Orden o = op.getOrden();
      // PracticaDTO dto = new PracticaDTO(p.getPracticaId(), "[" + p.getCodigo() + "]-" +
      // p.getNombre());
      OrdenPracticaDTO dto = new OrdenPracticaDTO();
      dto.setOrddenPracticaId(op.getOrddenPracticaId());
      dto.setOrdenId(o.getOrdenId());
      dto.setNombre(p.getNombre());
      dto.setPracticaId(p.getNomencladorId());
      dto.setEstado(op.getEstado());

      retorno.add(dto);
    }

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

    if (dto.getProfesionalId() != null) {
      Set<OrdenProfesional> ordenProfesionals = new HashSet<OrdenProfesional>();
      Profesional profesional = new Profesional();
      profesional.setProfesionalId(dto.getProfesionalId());
      OrdenProfesional op = new OrdenProfesional(orden, profesional);
      ordenProfesionals.add(op);
      orden.setOrdenProfesionals(ordenProfesionals);
    }

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

  private List<ComboItemDTO> getProfesionalDTOList(List<Profesional> list) {
    List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
    for (Profesional p : list) {
      retorno
          .add(new ComboItemDTO(p.getProfesionalId() + "", p.getApellido() + ", " + p.getNombre()));
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
}
