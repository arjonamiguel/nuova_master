package com.nuova.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.dto.UsuarioDTO;
import com.nuova.model.UserDetails;
import com.nuova.service.EspecialidadManager;
import com.nuova.service.ObraSocialManager;
import com.nuova.service.OrdenManager;
import com.nuova.service.PacienteManager;
import com.nuova.service.ProfesionalManager;
import com.nuova.service.UserManager;
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
    @Autowired
    UserManager userManager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultPage(ModelMap map) {
        return "redirect:" + ConstantControllers.HOME;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
    public String loginerror(ModelMap model) {
        model.addAttribute("error", "true");
        return "denied";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model) {
        return "logout";
    }

    @RequestMapping(value = ConstantControllers.HOME, method = RequestMethod.GET)
    public String init(ModelMap map, HttpSession session) {
        // Datos del Usuario
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Obtengo informacion del usuario en session
        UserDetails userSession = userManager.findUserByUserName(user.getUsername());
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUserId(userSession.getId());
        usuario.setUsername(userSession.getUsername());
        session.setAttribute("userSession", usuario);        

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
