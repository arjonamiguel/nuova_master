package com.nuova.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;

@Controller
public class CalendarioController {

    @RequestMapping(value = ConstantControllers.MAIN_CALENDARIO, method = RequestMethod.GET)
    public String formAddObraSocial(ModelMap map) {
        // map.addAttribute("obrasocial", new ObraSocialDTO());
        return ConstantRedirect.VIEW_MAIN_CALENDARIO;
    }

}
