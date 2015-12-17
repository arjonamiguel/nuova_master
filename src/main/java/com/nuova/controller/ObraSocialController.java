package com.nuova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nuova.dto.ObraSocialDTO;
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
        map.addAttribute("obrasocialList", obrasocialManager.findAll());
        return ConstantRedirect.VIEW_MAIN_OBRASOCIAL;
    }

}
