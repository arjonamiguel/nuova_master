package com.nuova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nuova.model.Especialidad;
import com.nuova.service.EspecialidadManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;

@Controller
public class EspecialidadController {
    @Autowired
    EspecialidadManager especialidadManager;

    @RequestMapping(value = ConstantControllers.ADD_ESPECIALIDAD, method = RequestMethod.POST)
    public String addProfesional(
            @ModelAttribute(value = "employee") Especialidad especialidad,
            BindingResult result) {
        especialidadManager.add(especialidad);

        return "redirect:" + ConstantControllers.MAIN_ESPECIALIDAD;
    }

    @RequestMapping(value = ConstantControllers.MAIN_ESPECIALIDAD, method = RequestMethod.GET)
    public String mainProfesional(ModelMap map) {

        map.addAttribute("especialidadList", especialidadManager.findAll());

        return ConstantRedirect.VIEW_MAIN_ESPECIALIDAD;
    }

}
