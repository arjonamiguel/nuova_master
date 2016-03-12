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

import com.nuova.dto.ObraSocialDTO;
import com.nuova.model.Obrasocial;
import com.nuova.service.ObraSocialManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;
import com.nuova.utils.Util;

@Controller
public class ObraSocialController {
    @Autowired
    ObraSocialManager obrasocialManager;

    @RequestMapping(value = ConstantControllers.FORM_ADD_OBRASOCIAL, method = RequestMethod.GET)
    public String formAddObraSocial(ModelMap map) {
        map.addAttribute("obrasocial", new ObraSocialDTO());
        return ConstantRedirect.VIEW_FORM_ADD_OBRASOCIAL;
    }

    @RequestMapping(value = ConstantControllers.FORM_EDIT_OBRASOCIAL, method = RequestMethod.GET)
    public String formEditObraSocial(ModelMap map,
            @PathVariable("obrasocialId") Integer obrasocialId) {
        if (obrasocialId != null) {
            map.addAttribute("obrasocial", obrasocialManager.findObraSocialById(obrasocialId));
        }

        return ConstantRedirect.VIEW_FORM_EDIT_OBRASOCIAL;
    }

    @RequestMapping(value = ConstantControllers.FORM_DELETE_OBRASOCIAL, method = RequestMethod.GET)
    public String formDeleteObraSocial(ModelMap map,
            @PathVariable("obrasocialId") Integer obrasocialId) {
        if (obrasocialId != null) {
            map.addAttribute("obrasocial", obrasocialManager.findObraSocialById(obrasocialId));
        }

        return ConstantRedirect.VIEW_FORM_DELETE_OBRASOCIAL;
    }

    @RequestMapping(value = ConstantControllers.ADD_OBRASOCIAL, method = RequestMethod.POST)
    public String addObraSocial(
            @ModelAttribute(value = "obrasocial") ObraSocialDTO dto,
            BindingResult result) {

        if (dto != null) {
            obrasocialManager.add(Util.transformDtoToObraSocial(dto));
        }

        return "redirect:" + ConstantControllers.MAIN_OBRASOCIAL;
    }

    @RequestMapping(value = ConstantControllers.DELETE_OBRASOCIAL, method = RequestMethod.POST)
    public String deleteObraSocial(@ModelAttribute(value = "obrasocial") ObraSocialDTO dto) {
        obrasocialManager.delete(dto.getObrasocialId());
        return "redirect:" + ConstantControllers.MAIN_OBRASOCIAL;
    }

    @RequestMapping(value = ConstantControllers.EDIT_OBRASOCIAL, method = RequestMethod.POST)
    public String editObraSocial(@ModelAttribute(value = "obrasocial") ObraSocialDTO dto) {
        obrasocialManager.edit(Util.transformDtoToObraSocial(dto));
        return "redirect:" + ConstantControllers.MAIN_OBRASOCIAL;
    }

    @RequestMapping(value = ConstantControllers.MAIN_OBRASOCIAL, method = RequestMethod.GET)
    public String mainObraSocial(ModelMap map) {
        // map.addAttribute("obrasocialList", obrasocialManager.findAll());
        return ConstantRedirect.VIEW_MAIN_OBRASOCIAL;
    }

    // Ajax --------------------------------------------
    @RequestMapping(value = ConstantControllers.AJAX_GET_OBRASOCIALES_PAGINADOS, method = RequestMethod.GET)
    public @ResponseBody Page<ObraSocialDTO> getProfesionalesPaginados(
            @RequestParam(required = false, defaultValue = "0") Integer start,
            @RequestParam(required = false, defaultValue = "50") Integer limit) {

        // Sort and Pagination
        // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
        Pageable pageable = new PageRequest(start, limit);

        Page<Obrasocial> obrassociales = obrasocialManager.findObrasocialesByPageable(pageable);
        List<ObraSocialDTO> dtos = new ArrayList<ObraSocialDTO>();
        for (Obrasocial o : obrassociales) {
            ObraSocialDTO dto = new ObraSocialDTO();
            dto.setObrasocialId(o.getObrasocialId());
            dto.setNombre(o.getNombre());
            dto.setCuit(o.getCuit());
            dto.setDireccion(o.getDireccion());
            dto.setTelefono(o.getTelefono());
            dtos.add(dto);
        }

        return new PageImpl<ObraSocialDTO>(dtos, pageable, obrassociales.getTotalElements());
    }

    @RequestMapping(value = ConstantControllers.AJAX_GET_SEARCH_OBRASOCIALES_PAGINADOS, method = RequestMethod.GET)
    public @ResponseBody Page<ObraSocialDTO> getSearchProfesionalesPaginados(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "0") Integer start,
            @RequestParam(required = false, defaultValue = "50") Integer limit) {

        // Sort and Pagination
        // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
        Pageable pageable = new PageRequest(start, limit);

        Page<Obrasocial> obrasociales = obrasocialManager.findObrasocialesBySearch(search, pageable);
        List<ObraSocialDTO> dtos = new ArrayList<ObraSocialDTO>();
        for (Obrasocial o : obrasociales) {
            ObraSocialDTO dto = new ObraSocialDTO();
            dto.setObrasocialId(o.getObrasocialId());
            dto.setNombre(o.getNombre());
            dto.setCuit(o.getCuit());
            dto.setDireccion(o.getDireccion());
            dto.setTelefono(o.getTelefono());
            dtos.add(dto);
        }

        return new PageImpl<ObraSocialDTO>(dtos, pageable, obrasociales.getTotalElements());
    }

}
