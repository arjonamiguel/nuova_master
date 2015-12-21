package com.nuova.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nuova.dto.ObraSocialDTO;
import com.nuova.dto.PacienteDTO;
import com.nuova.model.Obrasocial;
import com.nuova.model.Paciente;
import com.nuova.model.PacienteObrasocial;
import com.nuova.service.ObraSocialManager;
import com.nuova.service.PacienteManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;
import com.nuova.utils.Util;

@Controller
public class PacienteController {
    @Autowired
    PacienteManager pacienteManager;
    @Autowired
    ObraSocialManager obrasocialManager;

    @RequestMapping(value = ConstantControllers.FORM_ADD_PACIENTE, method = RequestMethod.GET)
    public String formAddPaciente(ModelMap map) {
        List<Obrasocial> obrasocialList = obrasocialManager.findAll();
        map.addAttribute("provinciaList", Util.getProvincias());
        map.addAttribute("obrasocialList", obrasocialList);
        map.addAttribute("paciente", new PacienteDTO());
        return ConstantRedirect.VIEW_FORM_ADD_PACIENTE;
    }

    @RequestMapping(value = ConstantControllers.FORM_EDIT_PACIENTE, method = RequestMethod.GET)
    public String formEditPaciente(ModelMap map,
            @PathVariable("pacienteId") Integer pacienteId) {
        if (pacienteId != null) {
            List<Paciente> pacienteList = pacienteManager.findAll();
            PacienteDTO dto = transformPacienteToDto(pacienteManager.fin1dPacienteById(pacienteId));

            map.addAttribute("pacienteList", pacienteList);
            // map.addAttribute("pacienteListEdit", dto.getEspecialidadListEdit());
            map.addAttribute("paciente", dto);
        }
        return ConstantRedirect.VIEW_FORM_EDIT_PACIENTE;
    }

    @RequestMapping(value = ConstantControllers.FORM_DELETE_PACIENTE, method = RequestMethod.GET)
    public String formDeletePaciente(ModelMap map,
            @PathVariable("pacienteId") Integer pacienteId) {
        if (pacienteId != null) {
            PacienteDTO dto = transformPacienteToDto(pacienteManager.fin1dPacienteById(pacienteId));

            // map.addAttribute("pacienteListEdit", dto.getEspecialidadListEdit());
            map.addAttribute("pacienteId", dto);
        }
        return ConstantRedirect.VIEW_FORM_DELETE_PACIENTE;
    }

    @RequestMapping(value = ConstantControllers.ADD_PACIENTE, method = RequestMethod.POST)
    public String addPaciente(
            @ModelAttribute(value = "paciente") PacienteDTO dto,
            BindingResult result) {
        Paciente paciente = transformDtoToPaciente(dto);
        pacienteManager.add(paciente);
        return "redirect:" + ConstantControllers.MAIN_PACIENTE;
    }

    @RequestMapping(value = ConstantControllers.DELETE_PACIENTE, method = RequestMethod.POST)
    public String deletePaciente(@ModelAttribute(value = "paciente") PacienteDTO dto) {
        // Paciente profesionalOld = pacienteManager.findProfesionalById(dto.get);
        // for (ProfesionalPaciente pe : profesionalOld.getProfesionalEspecialidads()) {
        // profesionalManager.deleteProfesionalEspecialidad(pe.getProfesional().getProfesionalId());
        // }
        // profesionalManager.delete(profesionalOld.getProfesionalId());
        return "redirect:" + ConstantControllers.MAIN_PACIENTE;
    }

    @RequestMapping(value = ConstantControllers.EDIT_PACIENTE, method = RequestMethod.POST)
    public String editPaciente(@ModelAttribute(value = "paciente") PacienteDTO dto) {
        // Profesional profesionalOld = profesionalManager.findProfesionalById(dto.getProfesionalId());
        // Profesional profesional = transformDtoToProfesional(dto);
        // for (ProfesionalEspecialidad pe : profesionalOld.getProfesionalEspecialidads()) {
        // profesionalManager.deleteProfesionalEspecialidad(pe.getProfesional().getProfesionalId());
        // }
        // profesionalManager.edit(profesional);
        return "redirect:" + ConstantControllers.MAIN_PACIENTE;
    }

    @RequestMapping(value = ConstantControllers.MAIN_PACIENTE, method = RequestMethod.GET)
    public String mainPaciente(ModelMap map) {

        map.addAttribute("pacienteList", transformPacientesToDtoList(pacienteManager.findAll()));

        return ConstantRedirect.VIEW_MAIN_PACIENTE;
    }

    private Paciente transformDtoToPaciente(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setPacienteId(dto.getPacienteId());
        paciente.setDni(dto.getDni());
        paciente.setApellido(dto.getApellido());
        paciente.setNombre(dto.getNombre());
        paciente.setDomicilio(dto.getDomicilio());
        paciente.setFechaNacimiento(Util.parseToDate(dto.getFechaNacimiento()));
        paciente.setLiberado(dto.getLiberado());
        paciente.setMail(dto.getMail());
        paciente.setTelefono(dto.getTelefono());

        for (ObraSocialDTO os : dto.getObrasocialList()) {
            Obrasocial obrasocial = obrasocialManager.findObraSocialById(os.getObrasocialId());
            PacienteObrasocial po = new PacienteObrasocial();
            po.setObrasocial(obrasocial);
            po.setFecha(new Date());
            po.setNroCredencial(os.getCredencial());
            Byte isOriginalCredential = os.getOriginal().equals("on") ? new Byte("1") : new Byte("0");
            po.setProvisorio(isOriginalCredential);
            po.setPaciente(paciente);

            paciente.getPacienteObrasocials().add(po);
        }

        return paciente;

    }

    private PacienteDTO transformPacienteToDto(Paciente p) {
        return null;
    }

    private List<PacienteDTO> transformPacientesToDtoList(List<Paciente> listPacientes) {
        List<PacienteDTO> retorno = new ArrayList<PacienteDTO>();
        for (Paciente p : listPacientes) {
            PacienteDTO dto = new PacienteDTO();
            dto.setPacienteId(p.getPacienteId());
            dto.setDni(p.getDni());
            dto.setApellido(p.getApellido());
            dto.setNombre(p.getNombre());
            dto.setDomicilio(p.getDomicilio());
            dto.setFechaNacimiento("" + p.getFechaNacimiento());
            dto.setLiberado(p.getLiberado());
            dto.setMail(p.getMail());
            dto.setTelefono(p.getTelefono());
            for (Paciente ad : p.getPacientes()) {
                PacienteDTO dtoad = new PacienteDTO();
                dtoad.setPacienteId(p.getPacienteId());
                dtoad.setApellido(p.getApellido());
                dtoad.setNombre(p.getNombre());
                dtoad.setDomicilio(p.getDomicilio());
                dtoad.setFechaNacimiento("" + p.getFechaNacimiento());
                dtoad.setLiberado(p.getLiberado());
                dtoad.setMail(p.getMail());
                dtoad.setTelefono(p.getTelefono());
                dto.getPacientes().add(dtoad);
            }
            retorno.add(dto);
        }

        return retorno;
    }

}
