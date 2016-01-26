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
import com.nuova.service.EspecialidadManager;
import com.nuova.service.ObraSocialManager;
import com.nuova.service.OrdenManager;
import com.nuova.service.PacienteManager;
import com.nuova.service.ProfesionalManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;

@Controller
public class InitController {
    @Autowired
    OrdenManager ordenManager;
    @Autowired
    PacienteManager pacienteManager;
    @Autowired
    EspecialidadManager especialidadManager;
    @Autowired
    ObraSocialManager obrasocialManager;
    @Autowired
    ProfesionalManager profesionalManager;

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

        // Cantidad
        OrdenAlarmaDTO cantPaciente = pacienteManager.countPacientes();
        OrdenAlarmaDTO cantProfesional = profesionalManager.countProfesionales();
        OrdenAlarmaDTO cantEspecialidad = especialidadManager.countEspecialidades();
        OrdenAlarmaDTO cantObrasocial = obrasocialManager.countObrasociales();

        map.addAttribute("usuario", usuario);
        map.addAttribute("alarmas", alarmas);
        map.addAttribute("cantPaciente", cantPaciente.getCantidad());
        map.addAttribute("cantProfesional", cantProfesional.getCantidad());
        map.addAttribute("cantEspecialidad", cantEspecialidad.getCantidad());
        map.addAttribute("cantObrasocial", cantObrasocial.getCantidad());
        return ConstantRedirect.VIEW_INIT_HOME;
    }
}
