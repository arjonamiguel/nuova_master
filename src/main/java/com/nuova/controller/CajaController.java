package com.nuova.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;

@Controller
public class CajaController {

    @RequestMapping(value = ConstantControllers.MAIN_CAJA, method = RequestMethod.GET)
    public String formAddObraSocial(ModelMap map) {
        // map.addAttribute("o", new ObraSocialDTO());
        return ConstantRedirect.VIEW_FORM_ADD_OBRASOCIAL;
    }

}
