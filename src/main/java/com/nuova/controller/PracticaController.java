package com.nuova.controller;

import com.nuova.dto.PracticaDTO;
import com.nuova.model.Nomenclador;
import com.nuova.model.OrdenPractica;
import com.nuova.service.OrdenManager;
import com.nuova.service.PracticaManager;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class PracticaController {
  @Autowired
  PracticaManager practicaManager;

  @Autowired
  OrdenManager ordenManager;

  @RequestMapping(value = ConstantControllers.FORM_ADD_PRACTICA, method = RequestMethod.GET)
  public String formAddPractica(ModelMap map) {
    map.addAttribute("practica", new PracticaDTO());
    map.addAttribute("listNomencladorTipo", practicaManager.findNomecladorTipo());
    return ConstantRedirect.VIEW_FORM_ADD_PRACTICA;
  }

  @RequestMapping(value = ConstantControllers.FORM_EDIT_PRACTICA, method = RequestMethod.GET)
  public String formEditPractica(ModelMap map, @PathVariable("practicaId") Integer practicaId) {
    if (practicaId != null) {
      map.addAttribute("practica", practicaManager.findPracticaById(practicaId));
      map.addAttribute("listNomencladorTipo", practicaManager.findNomecladorTipo());
    }

    return ConstantRedirect.VIEW_FORM_EDIT_PRACTICA;
  }

  @RequestMapping(value = ConstantControllers.FORM_DELETE_PRACTICA, method = RequestMethod.GET)
  public String formDeletePractica(ModelMap map, @PathVariable("practicaId") Integer practicaId) {
    if (practicaId != null) {
      map.addAttribute("practica", practicaManager.findPracticaById(practicaId));
      map.addAttribute("listNomencladorTipo", practicaManager.findNomecladorTipo());

    }

    return ConstantRedirect.VIEW_FORM_DELETE_PRACTICA;
  }

  @RequestMapping(value = ConstantControllers.ADD_PRACTICA, method = RequestMethod.POST)
  public String addPractica(@ModelAttribute(value = "practica") PracticaDTO dto,
      BindingResult result) {

    if (dto != null) {
      practicaManager.add(transformDtoToPrestadores(dto));
    }

    return "redirect:" + ConstantControllers.MAIN_PRACTICA;
  }

  @RequestMapping(value = ConstantControllers.DELETE_PRACTICA, method = RequestMethod.POST)
  public String deletePractica(@ModelAttribute(value = "practica") PracticaDTO dto) {
    // practicaManager.deletePractica(dto.getNomencladorId());
    Nomenclador n = practicaManager.findPracticaById(dto.getNomencladorId());
    n.setEliminado(1);
    practicaManager.edit(n);
    return "redirect:" + ConstantControllers.MAIN_PRACTICA;
  }

  @RequestMapping(value = ConstantControllers.EDIT_PRACTICA, method = RequestMethod.POST)
  public String editPractica(@ModelAttribute(value = "practica") PracticaDTO dto) {
    Nomenclador n = practicaManager.findPracticaById(dto.getNomencladorId());
    Nomenclador nedit = transformDtoToPrestadores(dto);
    nedit.setEliminado(n.getEliminado());
    practicaManager.edit(nedit);
    return "redirect:" + ConstantControllers.MAIN_PRACTICA;
  }

  @RequestMapping(value = ConstantControllers.MAIN_PRACTICA, method = RequestMethod.GET)
  public String mainPractica(ModelMap map) {
    // map.addAttribute("obrasocialList", obrasocialManager.findAll());
    return ConstantRedirect.VIEW_MAIN_PRACTICA;
  }

  // Ajax --------------------------------------------
  @RequestMapping(value = ConstantControllers.AJAX_GET_PRACTICAS_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<PracticaDTO> getPracticasPaginados(
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<Nomenclador> prestadores = practicaManager.findPracticaByPageable(pageable);
    List<PracticaDTO> dtos = new ArrayList<PracticaDTO>();
    for (Nomenclador p : prestadores) {
      dtos.add(transformPrestadoresToDto(p));
    }

    return new PageImpl<PracticaDTO>(dtos, pageable, prestadores.getTotalElements());
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_SEARCH_PRACTICAS_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<PracticaDTO> getSearchPracticasPaginados(
      @RequestParam(required = false, defaultValue = "") String search,
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<Nomenclador> prestadores = practicaManager.findPracticaBySearch(search, pageable);
    List<PracticaDTO> dtos = new ArrayList<PracticaDTO>();
    for (Nomenclador p : prestadores) {
      dtos.add(transformPrestadoresToDto(p));
    }

    return new PageImpl<PracticaDTO>(dtos, pageable, prestadores.getTotalElements());
  }

  @RequestMapping(value = ConstantControllers.AJAX_GET_CANTIDAD_SESIONES,
      method = RequestMethod.GET)
  public @ResponseBody String getCantidadSesiones(
      @RequestParam(required = false, defaultValue = "0") Integer nomencladorId,
      @RequestParam(required = false, defaultValue = "0") Integer ordenId) {
    String retorno = "";
    Nomenclador n = practicaManager.findPracticaById(nomencladorId);
    Integer cantidadSesion = (n.getCantidadSesion() == null ? 0 : n.getCantidadSesion());
    List<OrdenPractica> practicas = ordenManager.getAllOrdenPracticaByOrden(ordenId, nomencladorId);
    int sesionesUsadas = 0;
    for (OrdenPractica op : practicas) {
      sesionesUsadas =
          sesionesUsadas + (op.getCantidad() == null ? 1 : op.getCantidad().intValue());
    }
    Integer cantidadSesionUsadas = sesionesUsadas;

    if (cantidadSesion.intValue() == 0) {
      return "0";
    }

    Integer cantidadSesionDisponible = cantidadSesion.intValue() - cantidadSesionUsadas.intValue();
    retorno = "Codigo: " + n.getCodigo() + "\nPractica: " + n.getNombre() + "\nSesiones: "
        + n.getCantidadSesion() + "\nUsadas: " + cantidadSesionUsadas + "\nDisponibles: "
        + cantidadSesionDisponible;
    return retorno;
  }

  private PracticaDTO transformPrestadoresToDto(Nomenclador p) {
    PracticaDTO retorno = new PracticaDTO();
    retorno.setNombre(p.getNombre());
    retorno.setNomencladorId(p.getNomencladorId());
    retorno.setCodigo(p.getCodigo());
    retorno.setTipo(p.getTipo());
    retorno.setCantidadSesion(p.getCantidadSesion());
    retorno.setValidaCada(p.getValidaCada());
    retorno.setMesAno(p.getMesAno());
    return retorno;
  }

  private Nomenclador transformDtoToPrestadores(PracticaDTO dto) {
    Nomenclador retorno = new Nomenclador();
    retorno.setNombre(dto.getNombre());
    retorno.setNomencladorId(dto.getNomencladorId());
    retorno.setCodigo(dto.getCodigo());
    retorno.setTipo(dto.getTipo());
    retorno.setCantidadSesion(dto.getCantidadSesion());
    retorno.setValidaCada(dto.getValidaCada());
    retorno.setMesAno(dto.getMesAno());
    return retorno;
  }

}
