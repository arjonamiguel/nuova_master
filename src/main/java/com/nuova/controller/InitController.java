package com.nuova.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;

@Controller
public class InitController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultPage(ModelMap map) {
        return "redirect:" + ConstantControllers.HOME;
    }

    @RequestMapping(value = ConstantControllers.HOME, method = RequestMethod.GET)
    public String init(ModelMap map) {
        return ConstantRedirect.VIEW_INIT_HOME;
    }

}
