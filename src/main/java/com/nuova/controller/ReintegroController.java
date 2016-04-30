package com.nuova.controller;

import com.nuova.dto.ReintegroDTO;
import com.nuova.model.Paciente;
import com.nuova.model.Reintegro;
import com.nuova.service.PacienteManager;
import com.nuova.service.ReintegroManager;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReintegroController {

  @Autowired
  ReintegroManager reintegroManager;
  @Autowired
  PacienteManager pacienteManager;

  // Form
  @RequestMapping(value = ConstantControllers.FORM_ADD_REINTEGRO, method = RequestMethod.GET)
  public String formAddReintegro(ModelMap map, @PathVariable("pacienteId") Integer pacienteId) {
    ReintegroDTO dto = new ReintegroDTO();
    dto.setPacienteId(pacienteId);
    map.addAttribute("reintegro", dto);
    return ConstantRedirect.VIEW_FORM_ADD_REINTEGRO;
  }

  @RequestMapping(value = ConstantControllers.FORM_EDIT_REINTEGRO, method = RequestMethod.GET)
  public String formEditReintegro(ModelMap map, @PathVariable("reintegroId") Integer reintegroId) {
    ReintegroDTO dto = transformReintegroToDto(reintegroManager.findReintegroById(reintegroId));
    map.addAttribute("estadosReintegrosList", Util.getEstadosReintegro());
    map.addAttribute("reintegro", dto);
    return ConstantRedirect.VIEW_FORM_EDIT_REINTEGRO;
  }

  // Actions
  @RequestMapping(value = ConstantControllers.ADD_REINTEGRO, method = RequestMethod.POST)
  public String addReintegro(@ModelAttribute(value = "especialidad") ReintegroDTO dto,
      BindingResult result) {

    Reintegro r = transformDtoToReintegro(dto);
    r.setEstado(Util.ESTADO_REINTEGRO_ENPROCESO);
    if (dto != null) {
      reintegroManager.add(r);
    }

    return "redirect:" + ConstantControllers.MAIN_REINTEGRO;
  }

  @RequestMapping(value = ConstantControllers.EDIT_REINTEGRO, method = RequestMethod.POST)
  public String editReintegro(@ModelAttribute(value = "especialidad") ReintegroDTO dto,
      BindingResult result) {

    Reintegro r = transformDtoToReintegro(dto);
    if (dto != null) {
      reintegroManager.edit(r);
    }

    return "redirect:" + ConstantControllers.MAIN_REINTEGRO;
  }

  @RequestMapping(value = ConstantControllers.MAIN_REINTEGRO, method = RequestMethod.GET)
  public String mainEspecialidad(ModelMap map) {
    return ConstantRedirect.VIEW_MAIN_REINTEGRO;
  }

  // Ajax --------------------------------------------
  @RequestMapping(value = ConstantControllers.AJAX_GET_REINTEGROS_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<ReintegroDTO> getReintegrosPaginados(
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<Reintegro> reintegros = reintegroManager.findReintegrosByPageable(pageable);
    List<ReintegroDTO> dtos = new ArrayList<ReintegroDTO>();
    for (Reintegro r : reintegros) {
      dtos.add(transformReintegroToDto(r));
    }

    return new PageImpl<ReintegroDTO>(dtos, pageable, reintegros.getTotalElements());
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_SEARCH_REINTEGROS_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<ReintegroDTO> getSearchReintegrosPaginados(
      @RequestParam(required = false, defaultValue = "") String search,
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<Reintegro> reintegros = reintegroManager.findReintegrosBySearch(search, pageable);
    List<ReintegroDTO> dtos = new ArrayList<ReintegroDTO>();
    for (Reintegro r : reintegros) {
      dtos.add(transformReintegroToDto(r));
    }

    return new PageImpl<ReintegroDTO>(dtos, pageable, reintegros.getTotalElements());
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_REINTEGROSBYPACIENTE_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<ReintegroDTO> getReintegrosByPacientePaginados(
      @PathVariable("pacienteId") Integer pacienteId,
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<Reintegro> reintegros = reintegroManager.findReintegrosByPageable(pageable);
    List<ReintegroDTO> dtos = new ArrayList<ReintegroDTO>();
    for (Reintegro r : reintegros) {
      dtos.add(transformReintegroToDto(r));
    }

    return new PageImpl<ReintegroDTO>(dtos, pageable, reintegros.getTotalElements());

  }

  private ReintegroDTO transformReintegroToDto(Reintegro r) {
    ReintegroDTO dto = new ReintegroDTO();
    dto.setFechaDesde(r.getFechaDesde() + "");
    dto.setFechaReintegro(r.getFechaReintegro() + "");
    dto.setMonto(r.getMonto());
    dto.setPacienteId(r.getPacienteId());
    dto.setProfesional(r.getProfesional());
    dto.setReintegroId(r.getReintegroId());

    // paciente
    Paciente p = pacienteManager.fin1dPacienteById(r.getPacienteId());
    if (p != null) {
      dto.setApellidoNombrePaciente(p.getApellido() + " " + p.getNombre());
    }

    // acciones
    String botonEdit = "<a class='btn btn-info btn-xs' href='/nuova/formEditReintegro/"
        + r.getReintegroId() + "'><span class='icon icon-edit'></span></a>";

    dto.setAcciones(botonEdit);

    // estado
    dto.setEstado(r.getEstado());
    dto.setEstadoView(Util.getEtiquetaEstadoReintegro(r.getEstado()));

    return dto;
  }

  private Reintegro transformDtoToReintegro(ReintegroDTO dto) {
    Reintegro r = new Reintegro();
    r.setReintegroId(dto.getReintegroId());
    r.setFechaDesde(Util.parseToDate(dto.getFechaDesde()));
    r.setFechaReintegro(Util.parseToDate(dto.getFechaReintegro()));
    r.setMonto(dto.getMonto());
    r.setPacienteId(dto.getPacienteId());
    r.setProfesional(dto.getProfesional());
    r.setEstado(dto.getEstado());
    return r;
  }

}
