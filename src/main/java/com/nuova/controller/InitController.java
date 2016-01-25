package com.nuova.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.dto.UsuarioDTO;
import com.nuova.service.OrdenManager;
import com.nuova.service.PacienteManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;

@Controller
public class InitController {
    @Autowired
    OrdenManager ordenManager;
    @Autowired
    PacienteManager pacienteManager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultPage(ModelMap map) {
        return "redirect:" + ConstantControllers.HOME;
    }

    @RequestMapping(value = ConstantControllers.HOME, method = RequestMethod.GET)
    public String init(ModelMap map) {
        // Datos del Usuario
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUsername(user.getUsername());

        // Alarmas de practicas
        List<OrdenAlarmaDTO> alarmas = ordenManager.findAlarmaOrdenes();

        map.put("usuario", usuario);
        map.put("alarmas", alarmas);
        return ConstantRedirect.VIEW_INIT_HOME;
    }
}
