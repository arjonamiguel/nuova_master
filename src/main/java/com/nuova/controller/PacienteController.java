package com.nuova.controller;

import com.nuova.dto.ComboItemDTO;
import com.nuova.dto.ObraSocialDTO;
import com.nuova.dto.OrdenTipoDTO;
import com.nuova.dto.PacienteDTO;
import com.nuova.model.Empresas;
import com.nuova.model.Localidades;
import com.nuova.model.Obrasocial;
import com.nuova.model.OrdenTipo;
import com.nuova.model.Paciente;
import com.nuova.service.ObraSocialManager;
import com.nuova.service.OrdenManager;
import com.nuova.service.PacienteManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;
import com.nuova.utils.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PacienteController {
  @Autowired
  PacienteManager pacienteManager;
  @Autowired
  ObraSocialManager obrasocialManager;
  @Autowired
  OrdenManager ordenManager;

  String flag = "";

  @RequestMapping(value = ConstantControllers.TIPO_ORDEN, method = RequestMethod.GET)
  public String tipoOrden(ModelMap map, @PathVariable("pacienteId") Integer pacienteId) {
    List<OrdenTipo> ordenestipo = ordenManager.finAllOrdenTipo();
    Paciente paciente = pacienteManager.fin1dPacienteById(pacienteId);
    OrdenTipoDTO ordenTipo = new OrdenTipoDTO();
    ordenTipo.setPacienteId(paciente.getPacienteId());

    map.addAttribute("ordenestipo", getOrdenesTipoDTO(ordenestipo));
    map.addAttribute("paciente", transformPacienteToDto(paciente));
    map.addAttribute("ordenTipo", ordenTipo);
    return ConstantRedirect.VIEW_FORM_TIPO_ORDEN;
  }

  @RequestMapping(value = ConstantControllers.FORM_BUSCAR_PACIENTE, method = RequestMethod.GET)
  public String formBuscarPaciente(ModelMap map) {

    return ConstantRedirect.VIEW_FORM_BUSCAR_PACIENTE;
  }

  @RequestMapping(value = ConstantControllers.FORM_INFO_PACIENTE, method = RequestMethod.GET)
  public String formInfoPaciente(ModelMap map, @PathVariable("pacienteId") Integer pacienteId) {
    if (pacienteId != null) {
      Paciente p = pacienteManager.fin1dPacienteById(pacienteId);
      PacienteDTO dto = transformPacienteToDto(p);
      dto.setFechaNacimiento(Util.parseToStringDate(p.getFechaNacimiento()));
      map.addAttribute("paciente", dto);
    }
    return ConstantRedirect.VIEW_FORM_INFO_PACIENTE;
  }

  @RequestMapping(value = ConstantControllers.FORM_ADD_PACIENTE, method = RequestMethod.GET)
  public String formAddPaciente(ModelMap map) {
    List<Obrasocial> obrasocialList = obrasocialManager.findAll();
    List<Empresas> empresas = pacienteManager.findAllEmpresas();
    map.addAttribute("provinciaList", Util.getProvincias());
    map.addAttribute("parentescosList", Util.getParentescos());
    map.addAttribute("trabajaEnList", Util.getTrabajaEn());
    map.addAttribute("obrasocialList", obrasocialList);
    map.addAttribute("obrasocialDTOList", new ArrayList<ObraSocialDTO>());
    PacienteDTO pacienteDto = new PacienteDTO();
    map.addAttribute("paciente", pacienteDto);
    map.addAttribute("empresas", empresas);
    map.addAttribute("razonCoseguroList", Util.getRazonCoseguro());
    map.addAttribute("dniRepetido", flag);

    return ConstantRedirect.VIEW_FORM_ADD_PACIENTE;
  }

  @RequestMapping(value = ConstantControllers.FORM_EDIT_PACIENTE, method = RequestMethod.GET)
  public String formEditPaciente(ModelMap map, @PathVariable("pacienteId") Integer pacienteId) {
    if (pacienteId != null) {
      PacienteDTO dto = transformPacienteToDto(pacienteManager.fin1dPacienteById(pacienteId));
      List<Empresas> empresas = pacienteManager.findAllEmpresas();

      map.addAttribute("provinciaList", Util.getProvincias());
      map.addAttribute("parentescosList", Util.getParentescos());
      map.addAttribute("trabajaEnList", Util.getTrabajaEn());
      map.addAttribute("obrasocialList", obrasocialManager.findAll());
      map.addAttribute("paciente", dto);
      map.addAttribute("isTitular", dto.isTitular());
      map.addAttribute("empresas", empresas);
      map.addAttribute("razonCoseguro", dto.getRazonCoseguro());
      map.addAttribute("razonCoseguroList", Util.getRazonCoseguro());
    }
    return ConstantRedirect.VIEW_FORM_EDIT_PACIENTE;
  }

  @RequestMapping(value = ConstantControllers.FORM_DELETE_PACIENTE, method = RequestMethod.GET)
  public String formDeletePaciente(ModelMap map, @PathVariable("pacienteId") Integer pacienteId) {
    if (pacienteId != null) {
      PacienteDTO dto = transformPacienteToDto(pacienteManager.fin1dPacienteById(pacienteId));
      List<Empresas> empresas = pacienteManager.findAllEmpresas();

      map.addAttribute("provinciaList", Util.getProvincias());
      map.addAttribute("parentescosList", Util.getParentescos());
      map.addAttribute("trabajaEnList", Util.getTrabajaEn());
      map.addAttribute("obrasocialList", obrasocialManager.findAll());
      map.addAttribute("paciente", dto);
      map.addAttribute("isTitular", dto.isTitular());
      map.addAttribute("empresas", empresas);

    }
    return ConstantRedirect.VIEW_FORM_DELETE_PACIENTE;
  }

  @RequestMapping(value = ConstantControllers.ADD_PACIENTE, method = RequestMethod.POST)
  public String addPaciente(@ModelAttribute(value = "paciente") PacienteDTO dto,
      BindingResult result) {
    Paciente p = pacienteManager.findPacienteByDni(dto.getDni());
    if (p != null) {
      flag = "repetido";
      return "redirect:" + ConstantControllers.FORM_ADD_PACIENTE;
    }


    Paciente paciente = transformDtoToPaciente(dto);
    paciente.setEliminado(new Byte("0"));
    paciente.setFechaAlta(new Date());

    if (dto.getParentesco() != 0) {
      Paciente t = getTitularByCredencial(paciente);
      if (t != null) {
        paciente.setPaciente(t);
      }
    }

    pacienteManager.add(paciente);

    if (dto.getParentesco() == 0) {
      List<Paciente> adherentes =
          pacienteManager.findAllPacienteByCredencial(paciente.getNroCredencial());

      if (adherentes != null) {
        for (Paciente pas : adherentes) {
          if (pas.getPaciente() == null) {
            pas.setPaciente(paciente);
            pacienteManager.edit(pas);
          }
        }
      }

    }


    return "redirect:" + ConstantControllers.MAIN_PACIENTE;
  }

  @RequestMapping(value = ConstantControllers.DELETE_PACIENTE, method = RequestMethod.POST)
  public String deletePaciente(@ModelAttribute(value = "paciente") PacienteDTO dto) {
    Paciente paciente = pacienteManager.fin1dPacienteById(dto.getPacienteId());
    paciente.setEliminado(new Byte("1"));
    pacienteManager.edit(paciente);
    return "redirect:" + ConstantControllers.MAIN_PACIENTE;
  }

  @RequestMapping(value = ConstantControllers.AJAX_POST_ACTIVAR_PACIENTE,
      method = RequestMethod.POST, headers = {"content-type=application/json"})
  public @ResponseBody String activarPaciente(@PathVariable("pacienteId") Integer pacienteId)
      throws Exception {
    String resp = "1";
    Paciente paciente = pacienteManager.fin1dPacienteById(pacienteId);
    paciente.setEliminado(new Byte("0"));
    try {
      pacienteManager.edit(paciente);
    } catch (Exception e) {
      resp = "0";
    }
    return resp;
  }

  @RequestMapping(value = ConstantControllers.EDIT_PACIENTE, method = RequestMethod.POST)
  public String editPaciente(@ModelAttribute(value = "paciente") PacienteDTO dto) {
    Paciente pacienteOld = pacienteManager.fin1dPacienteById(dto.getPacienteId());
    Paciente paciente = transformDtoToPaciente(dto);
    paciente.setPaciente(pacienteOld.getPaciente());
    paciente.setEliminado(pacienteOld.getEliminado());
    paciente.setFechaAlta(pacienteOld.getFechaAlta());
    if (!pacienteOld.getNroCredencial().equals(paciente.getNroCredencial())) {
      if (paciente.getParentesco().intValue() != 0) {
        Paciente t = getTitularByCredencial(paciente);
        paciente.setPaciente(t);
      }
    }

    pacienteManager.edit(paciente);
    return "redirect:" + ConstantControllers.MAIN_PACIENTE;
  }

  @RequestMapping(value = ConstantControllers.MAIN_PACIENTE, method = RequestMethod.GET)
  public String mainPaciente(ModelMap map) {
    return ConstantRedirect.VIEW_MAIN_PACIENTE;
  }

  // Ajax --------------------------------------------

  @RequestMapping(value = ConstantControllers.AJAX_GET_PACIENTES_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<PacienteDTO> getPacientesPaginados(
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<Paciente> pacientes = pacienteManager.findPacientesByPageable(pageable);
    // Page<Paciente> batches = new Pa
    // enrollImportBatchService.getBatches(pageable);
    List<PacienteDTO> dtos = new ArrayList<PacienteDTO>();
    for (Paciente p : pacientes) {
      PacienteDTO dto = transformPacienteToDto(p);
      dtos.add(dto);
    }

    return new PageImpl<PacienteDTO>(dtos, pageable, pacientes.getTotalElements());
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_SEARCH_PACIENTES_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<PacienteDTO> getSearchPacientesPaginados(
      @RequestParam(required = false, defaultValue = "") String search,
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {
    String query = "";
    try {
      query = new String(search.getBytes("ISO-8859-1"), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<Paciente> pacientes = pacienteManager.findPacientesBySearch(query, pageable);
    // Page<Paciente> batches = new Pa
    // enrollImportBatchService.getBatches(pageable);
    List<PacienteDTO> dtos = new ArrayList<PacienteDTO>();
    for (Paciente p : pacientes) {
      PacienteDTO dto = transformPacienteToDto(p);
      dtos.add(dto);
    }

    return new PageImpl<PacienteDTO>(dtos, pageable, pacientes.getTotalElements());
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_AUTOCOMPLETE_PACIENTES,
      method = RequestMethod.POST)
  public @ResponseBody List<ComboItemDTO> getAutocompletePaciente(
      @RequestParam(required = false, defaultValue = "") String query) {
    List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
    for (Paciente p : pacienteManager.findPacienteAutocomplete(query)) {
      retorno.add(new ComboItemDTO(p.getPacienteId() + "",
          "[DNI: " + p.getDni() + "] [Cred: " + p.getNroCredencial() + "-"
              + p.getNroCredencialSufijo() + "] " + p.getApellido() + ", " + p.getNombre()));
    }

    return retorno;
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_AUTOCOMPLETE_LOCALIDADES,
      method = RequestMethod.POST)
  public @ResponseBody List<ComboItemDTO> getAutocompleteLocalidades(
      @RequestParam(required = false, defaultValue = "") String query) {
    List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
    for (Localidades loc : pacienteManager.findLocalidadesAutocomplete(query)) {
      retorno.add(new ComboItemDTO(loc.getLocalidadId() + "", loc.getNombre()));
    }

    return retorno;
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_EXIST_DNI, method = RequestMethod.GET)
  public @ResponseBody Boolean existDni(
      @RequestParam(required = false, defaultValue = "") String dni) {
    Paciente p = pacienteManager.findPacienteByDni(Integer.valueOf(dni));
    if (p != null) {
      return true;
    } else {
      return false;
    }
  }

  @RequestMapping(value = ConstantControllers.AJAX_POST_NUEVAEMPRESA, method = RequestMethod.POST,
      headers = {"content-type=application/json"})
  public @ResponseBody String saveCodigoNomenclador(@RequestBody Empresas empresa)
      throws Exception {
    empresa.setNombre(empresa.getNombre().toUpperCase());
    pacienteManager.add(empresa);

    return empresa.getEmpresaId() != null ? empresa.getEmpresaId() + "" : "-1";
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_EMPRESAS, method = RequestMethod.GET)
  public @ResponseBody List<ComboItemDTO> getEmpresas() {
    List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
    List<Empresas> empresas = pacienteManager.findAllEmpresas();

    for (Empresas p : empresas) {
      retorno.add(new ComboItemDTO(p.getEmpresaId() + "", p.getNombre()));
    }
    return retorno;
  }

  // Adherentes --------------------------------------------
  @RequestMapping(value = ConstantControllers.FORM_ADD_ADHERENTE, method = RequestMethod.GET)
  public String formAddAdherente(ModelMap map, @PathVariable("titularId") Integer titularId) {
    Paciente titular = pacienteManager.fin1dPacienteById(titularId);
    PacienteDTO dto = new PacienteDTO();
    dto.setApellido(titular.getApellido());
    dto.setProvincia(titular.getProvincia());
    dto.setTelefono(titular.getTelefono());
    dto.setMail(titular.getMail());
    dto.setDomicilio(titular.getDomicilio());
    dto.setTitularId(titular.getPacienteId());
    dto.setParentesco(titular.getParentesco().intValue());
    ObraSocialDTO os = new ObraSocialDTO();
    os.setObrasocialId(titular.getObrasocialId());
    os.setCredencial(titular.getNroCredencial());
    dto.setObrasocial(os);

    List<ObraSocialDTO> obrasociales = new ArrayList<ObraSocialDTO>();
    dto.setObrasocialList(obrasociales);

    List<Obrasocial> obrasocialList = obrasocialManager.findAll();
    map.addAttribute("datosTitular", titular.getApellido() + " " + titular.getNombre());
    map.addAttribute("parentescosList", Util.getParentescosAdherente());
    map.addAttribute("provinciaList", Util.getProvincias());
    map.addAttribute("obrasocialList", obrasocialList);
    map.addAttribute("obrasocialDTOList", new ArrayList<ObraSocialDTO>());
    map.addAttribute("trabajaEnList", Util.getTrabajaEn());
    map.addAttribute("paciente", dto);
    map.addAttribute("isTitular", dto.isTitular());
    map.addAttribute("razonCoseguro", Util.getRazonCoseguro());

    return ConstantRedirect.VIEW_FORM_ADD_ADHERENTE;
  }

  @RequestMapping(value = ConstantControllers.ADD_ADHERENTE, method = RequestMethod.POST)
  public String addAdherente(@ModelAttribute(value = "paciente") PacienteDTO dto,
      BindingResult result) {
    Paciente titular = pacienteManager.fin1dPacienteById(dto.getTitularId());
    Paciente paciente = transformDtoToPaciente(dto);
    paciente.setPaciente(titular);
    paciente.setEliminado(new Byte("0"));
    paciente.setFechaAlta(new Date());
    pacienteManager.add(paciente);
    return "redirect:" + ConstantControllers.MAIN_PACIENTE;
  }

  // -------------------------------------------------------

  public PacienteDTO transformPacienteToDto(Paciente p) {
    PacienteDTO dto = new PacienteDTO();
    dto.setPacienteId(p.getPacienteId());
    dto.setEliminado(p.getEliminado().intValue());
    dto.setDni(Integer.valueOf(p.getDni()));
    dto.setApellido(p.getApellido());
    dto.setNombre(p.getNombre());
    dto.setDomicilio(p.getDomicilio());
    dto.setFechaNacimiento(p.getFechaNacimiento() + "");
    dto.setCoseguro(p.getCoseguro().intValue() == 1 ? true : false);
    dto.setCheckedLiberado(p.getCoseguro().intValue() == 1 ? "checked" : "");
    dto.setMail(p.getMail());
    dto.setTelefono(p.getTelefono());
    dto.setProvincia(p.getProvincia());
    dto.setRazonCoseguro(p.getRazonCoseguro());
    dto.setZonaAfiliacion(p.getZonaAfiliacion());
    dto.setEliminadoView(p.getEliminado().intValue() == 0 ? "ACTIVO" : "INACTIVO");

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
      dtoad.setDni(Integer.valueOf(ad.getDni()));
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

  public List<PacienteDTO> transformPacientesToDtoList(List<Paciente> listPacientes) {
    List<PacienteDTO> retorno = new ArrayList<PacienteDTO>();
    for (Paciente p : listPacientes) {
      PacienteDTO dto = new PacienteDTO();
      dto.setPacienteId(p.getPacienteId());
      dto.setDni(Integer.valueOf(p.getDni()));
      dto.setApellido(p.getApellido());
      dto.setNombre(p.getNombre());
      dto.setDomicilio(p.getDomicilio());
      dto.setFechaNacimiento(Util.parseToStringDate(p.getFechaNacimiento()));
      dto.setCoseguro(p.getCoseguro().intValue() == 1 ? true : false);
      dto.setCheckedLiberado(p.getCoseguro().intValue() == 1 ? "checked" : "");
      dto.setMail(p.getMail());
      dto.setTelefono(p.getTelefono());
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
        dto.getAdherentes().add(dtoad);
      }
      retorno.add(dto);
    }

    return retorno;
  }

  public Paciente transformDtoToPaciente(PacienteDTO dto) {
    Paciente paciente = new Paciente();
    paciente.setPacienteId(dto.getPacienteId());
    paciente.setDni(dto.getDni().toString());
    paciente.setApellido(dto.getApellido());
    paciente.setNombre(dto.getNombre());
    paciente.setDomicilio(dto.getDomicilio());
    paciente.setFechaNacimiento(Util.parseToDate(dto.getFechaNacimiento()));
    paciente.setCoseguro(dto.getCoseguro() ? new Byte("1") : new Byte("0"));
    paciente.setMail(dto.getMail());
    paciente.setTelefono(dto.getTelefono());
    paciente.setProvincia(dto.getProvincia());
    paciente.setRazonCoseguro(dto.getRazonCoseguro());
    paciente.setParentesco(new Byte(dto.getParentesco() + ""));
    paciente.setZonaAfiliacion(dto.getZonaAfiliacion());
    paciente.setLocalidadId(dto.getLocalidadId());
    paciente.setTrabajaEn(dto.getTrabajaEn());
    paciente.setEmpresa(dto.getEmpresa());
    paciente.setEmpresaId(dto.getEmpresaId());

    // obra social
    paciente.setObrasocialId(dto.getObrasocial().getObrasocialId());
    paciente.setNroCredencial(dto.getObrasocial().getCredencial());
    paciente.setNroCredencialSufijo(dto.getObrasocial().getCredencialSufijo());

    return paciente;

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

  private List<OrdenTipoDTO> getOrdenesTipoDTO(List<OrdenTipo> list) {
    List<OrdenTipoDTO> retorno = new ArrayList<OrdenTipoDTO>();
    for (OrdenTipo ot : list) {
      retorno.add(transformOrdenTipoToDto(ot));
    }

    return retorno;
  }

  private Paciente getTitularByCredencial(Paciente paciente) {
    Paciente titular = null;
    if (paciente.getParentesco().intValue() != 0) {
      titular = pacienteManager.findPacienteByCredencial(paciente.getNroCredencial().trim());

    }

    return titular;
  }



}
