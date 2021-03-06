package com.nuova.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.nuova.dto.PracticaDTO;
import com.nuova.model.Nomenclador;
import com.nuova.service.PracticaManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;

@Controller
public class PracticaController {
    @Autowired
    PracticaManager practicaManager;

    @RequestMapping(value = ConstantControllers.FORM_ADD_PRACTICA, method = RequestMethod.GET)
    public String formAddPractica(ModelMap map) {
        map.addAttribute("practica", new PracticaDTO());
        return ConstantRedirect.VIEW_FORM_ADD_PRACTICA;
    }

    @RequestMapping(value = ConstantControllers.FORM_EDIT_PRACTICA, method = RequestMethod.GET)
    public String formEditPractica(ModelMap map,
            @PathVariable("practicaId") Integer practicaId) {
        if (practicaId != null) {
            map.addAttribute("practica", practicaManager.findPracticaById(practicaId));
        }

        return ConstantRedirect.VIEW_FORM_EDIT_PRACTICA;
    }

    @RequestMapping(value = ConstantControllers.FORM_DELETE_PRACTICA, method = RequestMethod.GET)
    public String formDeletePractica(ModelMap map,
            @PathVariable("practicaId") Integer practicaId) {
        if (practicaId != null) {
            map.addAttribute("practica", practicaManager.findPracticaById(practicaId));
        }

        return ConstantRedirect.VIEW_FORM_DELETE_PRACTICA;
    }

    @RequestMapping(value = ConstantControllers.ADD_PRACTICA, method = RequestMethod.POST)
    public String addPractica(
            @ModelAttribute(value = "practica") PracticaDTO dto,
            BindingResult result) {

        if (dto != null) {
            practicaManager.add(transformDtoToPrestadores(dto));
        }

        return "redirect:" + ConstantControllers.MAIN_PRACTICA;
    }

    @RequestMapping(value = ConstantControllers.DELETE_PRACTICA, method = RequestMethod.POST)
    public String deletePractica(@ModelAttribute(value = "practica") PracticaDTO dto) {
        practicaManager.deletePractica(dto.getPracticaId());
        return "redirect:" + ConstantControllers.MAIN_PRACTICA;
    }

    @RequestMapping(value = ConstantControllers.EDIT_PRACTICA, method = RequestMethod.POST)
    public String editPractica(@ModelAttribute(value = "practica") PracticaDTO dto) {
        practicaManager.edit(transformDtoToPrestadores(dto));
        return "redirect:" + ConstantControllers.MAIN_PRACTICA;
    }

    @RequestMapping(value = ConstantControllers.MAIN_PRACTICA, method = RequestMethod.GET)
    public String mainPractica(ModelMap map) {
        // map.addAttribute("obrasocialList", obrasocialManager.findAll());
        return ConstantRedirect.VIEW_MAIN_PRACTICA;
    }

    // Ajax --------------------------------------------
    @RequestMapping(value = ConstantControllers.AJAX_GET_PRACTICAS_PAGINADOS, method = RequestMethod.GET)
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

    @RequestMapping(value = ConstantControllers.AJAX_GET_SEARCH_PRACTICAS_PAGINADOS, method = RequestMethod.GET)
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

    private PracticaDTO transformPrestadoresToDto(Nomenclador p) {
        PracticaDTO retorno = new PracticaDTO();
        retorno.setNombre(p.getNombre());
        retorno.setPracticaId(p.getNomencladorId());
        retorno.setCodigo(p.getCodigo());
        retorno.setTipo(p.getTipo());
        return retorno;
    }

    private Nomenclador transformDtoToPrestadores(PracticaDTO dto) {
        Nomenclador retorno = new Nomenclador();
        retorno.setNombre(dto.getNombre());
        retorno.setNomencladorId(dto.getPracticaId());
        retorno.setCodigo(dto.getCodigo());
        return retorno;
    }

}
