package com.nuova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nuova.model.Profesional;
import com.nuova.service.ProfesionalManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;

@Controller
public class ProfesionalController {
    @Autowired
    ProfesionalManager profesionalManager;

    @RequestMapping(value = ConstantControllers.ADD_PROFESIONAL, method = RequestMethod.POST)
    public String addProfesional(
            @ModelAttribute(value = "employee") Profesional profesional,
            BindingResult result) {
        profesionalManager.add(profesional);

        return "redirect:" + ConstantControllers.MAIN_PROFESIONAL;
    }

    @RequestMapping(value = ConstantControllers.MAIN_PROFESIONAL, method = RequestMethod.GET)
    public String mainProfesional(ModelMap map) {

        map.addAttribute("profesionalList", profesionalManager.findAll());

        return ConstantRedirect.VIEW_MAIN_PROFESIONAL;
    }
}
