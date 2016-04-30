package com.nuova.controller;

import com.nuova.dto.EspecialidadDTO;
import com.nuova.dto.ProfesionalDTO;
import com.nuova.dto.ProfesionalEspecialidadDTO;
import com.nuova.model.Especialidad;
import com.nuova.model.Profesional;
import com.nuova.model.ProfesionalEspecialidad;
import com.nuova.service.EspecialidadManager;
import com.nuova.service.ProfesionalManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ProfesionalController {
  @Autowired
  ProfesionalManager profesionalManager;
  @Autowired
  EspecialidadManager especialidadManager;

  @RequestMapping(value = ConstantControllers.FORM_ADD_PROFESIONAL, method = RequestMethod.GET)
  public String formAddEspecialidad(ModelMap map) {
    List<Especialidad> especialidadList = especialidadManager.findAll();
    map.addAttribute("especialidadList", especialidadList);
    map.addAttribute("profesional", new ProfesionalDTO());
    return ConstantRedirect.VIEW_FORM_ADD_PROFESIONAL;
  }

  @RequestMapping(value = ConstantControllers.FORM_EDIT_PROFESIONAL, method = RequestMethod.GET)
  public String formEditEspecialidad(ModelMap map,
      @PathVariable("profesionalId") Integer profesionalId) {
    if (profesionalId != null) {
      List<Especialidad> especialidadList = especialidadManager.findAll();
      ProfesionalDTO dto =
          transformProfesionalToDto(profesionalManager.findProfesionalById(profesionalId));

      map.addAttribute("especialidadList", especialidadList);
      map.addAttribute("especialidadListEdit", dto.getEspecialidadListEdit());
      map.addAttribute("profesional", dto);
    }
    return ConstantRedirect.VIEW_FORM_EDIT_PROFESIONAL;
  }

  @RequestMapping(value = ConstantControllers.FORM_DELETE_PROFESIONAL, method = RequestMethod.GET)
  public String formDeleteEspecialidad(ModelMap map,
      @PathVariable("profesionalId") Integer profesionalId) {
    if (profesionalId != null) {
      ProfesionalDTO dto =
          transformProfesionalToDto(profesionalManager.findProfesionalById(profesionalId));

      map.addAttribute("especialidadListEdit", dto.getEspecialidadListEdit());
      map.addAttribute("profesional", dto);
    }
    return ConstantRedirect.VIEW_FORM_DELETE_PROFESIONAL;
  }

  @RequestMapping(value = ConstantControllers.ADD_PROFESIONAL, method = RequestMethod.POST)
  public String addProfesional(@ModelAttribute(value = "employee") ProfesionalDTO profesionalDto,
      BindingResult result) {
    Profesional profesional = transformDtoToProfesional(profesionalDto);
    profesional.setEliminado(new Byte("0"));
    profesionalManager.add(profesional);
    return "redirect:" + ConstantControllers.MAIN_PROFESIONAL;
  }

  @RequestMapping(value = ConstantControllers.DELETE_PROFESIONAL, method = RequestMethod.POST)
  public String deleteEspecialidad(@ModelAttribute(value = "profesional") ProfesionalDTO dto) {
    Profesional profesionalOld = profesionalManager.findProfesionalById(dto.getProfesionalId());
    Profesional profesional = transformDtoToProfesional(dto);
    for (ProfesionalEspecialidad pe : profesionalOld.getProfesionalEspecialidads()) {
      profesionalManager.deleteProfesionalEspecialidad(pe.getProfesional().getProfesionalId());
    }
    profesional.setEliminado(new Byte("1"));
    profesionalManager.edit(profesional);
    return "redirect:" + ConstantControllers.MAIN_PROFESIONAL;
  }

  @RequestMapping(value = ConstantControllers.EDIT_PROFESIONAL, method = RequestMethod.POST)
  public String editEspecialidad(@ModelAttribute(value = "profesional") ProfesionalDTO dto) {
    Profesional profesionalOld = profesionalManager.findProfesionalById(dto.getProfesionalId());
    Profesional profesional = transformDtoToProfesional(dto);
    for (ProfesionalEspecialidad pe : profesionalOld.getProfesionalEspecialidads()) {
      profesionalManager.deleteProfesionalEspecialidad(pe.getProfesional().getProfesionalId());
    }
    profesional.setEliminado(profesionalOld.getEliminado());
    profesionalManager.edit(profesional);
    return "redirect:" + ConstantControllers.MAIN_PROFESIONAL;
  }

  @RequestMapping(value = ConstantControllers.MAIN_PROFESIONAL, method = RequestMethod.GET)
  public String mainProfesional(ModelMap map) {

    map.addAttribute("profesionalList", profesionalManager.findAll());

    return ConstantRedirect.VIEW_MAIN_PROFESIONAL;
  }

  // Ajax --------------------------------------------
  @RequestMapping(value = ConstantControllers.AJAX_GET_PROFESIONALES_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<ProfesionalDTO> getProfesionalesPaginados(
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<Profesional> profesionales = profesionalManager.findProfesionalesByPageable(pageable);
    List<ProfesionalDTO> dtos = new ArrayList<ProfesionalDTO>();
    for (Profesional p : profesionales) {
      ProfesionalDTO dto = transformProfesionalToDto(p);
      dtos.add(dto);
    }

    return new PageImpl<ProfesionalDTO>(dtos, pageable, profesionales.getTotalElements());
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_SEARCH_PROFESIONALES_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<ProfesionalDTO> getSearchProfesionalesPaginados(
      @RequestParam(required = false, defaultValue = "") String search,
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<Profesional> profesionales =
        profesionalManager.findProfesionalesBySearch(search, pageable);
    List<ProfesionalDTO> dtos = new ArrayList<ProfesionalDTO>();
    for (Profesional p : profesionales) {
      ProfesionalDTO dto = transformProfesionalToDto(p);
      dtos.add(dto);
    }

    return new PageImpl<ProfesionalDTO>(dtos, pageable, profesionales.getTotalElements());
  }

  private Profesional transformDtoToProfesional(ProfesionalDTO p) {
    Set<ProfesionalEspecialidad> profesionalEspecialidades = new HashSet<ProfesionalEspecialidad>();
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date fechaHabilitacion = null;
    Date validoHasta = null;
    // Date fechaMatricula = null;
    Date vigenciaDesde = null;
    Date vigenciaHasta = null;
    try {
      // fechaHabilitacion = formatter.parse(p.getFechaVencimientoHabilitacion());
      if (!p.getValidoHasta().equals("")) {
        validoHasta = formatter.parse(p.getValidoHasta());
      }
      // fechaMatricula = formatter.parse(p.getFechaEmisionMatricula());
      if (!p.getVigenciaDesde().equals("")) {
        vigenciaDesde = formatter.parse(p.getVigenciaDesde());
      }
      if (!p.getVigenciaHasta().equals("")) {
        vigenciaHasta = formatter.parse(p.getVigenciaHasta());
      }
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // Profesional profesional =
    // new Profesional(p.getApellido(), p.getNombre(), p.getTelefono(), p.getMatricula(),
    // p.getRegistroNacional(), p.getTituloProfesional(),
    // new Byte((p.getHabilitacionSiprosa().equals("")) ? "0" : p.getHabilitacionSiprosa()),
    // fechaHabilitacion, p.getNroRegistro(),
    // validoHasta, null, null, null, p.getNroPoliza(), vigenciaDesde, vigenciaHasta, null,
    // null, null, null, null, null, null);

    Byte hs = null;
    if (p.getHabilitacionSiprosa() != null) {
      hs = new Byte((p.getHabilitacionSiprosa().equals("")) ? "0" : p.getHabilitacionSiprosa());
    }
    Profesional profesional = new Profesional(p.getApellido(), p.getNombre(), p.getTelefono(),
        p.getMatricula(), p.getRegistroNacional(), p.getTituloProfesional(), hs, fechaHabilitacion,
        p.getNroRegistro(), validoHasta, null, null, null, p.getNroPoliza(), vigenciaDesde, null,
        vigenciaHasta, null, null, null);
    profesional.setProfesionalId(p.getProfesionalId());

    for (Integer id : p.getEspecialidadList()) {
      Especialidad especialidad = especialidadManager.findEspecialidadById(id);
      profesionalEspecialidades.add(new ProfesionalEspecialidad(profesional, especialidad));
    }

    profesional.setProfesionalEspecialidads(profesionalEspecialidades);

    return profesional;
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

    // ----
    dto.setNroRegistro(p.getNroRegistro());
    dto.setValidoHasta(p.getValidoHasta() + "");

    dto.setFechaEmisionMatricula(p.getFechaEmisionMatricula() + "");
    dto.setNroFolio(p.getNroFolio());
    dto.setNroLibro(p.getNroLibro());

    dto.setNroPoliza(p.getNroPoliza());
    dto.setVigenciaDesde(p.getVigenciaDesde() + "");
    dto.setVigenciaHasta(p.getVigenciaHasta() + "");

    return dto;
  }
}
