package com.nuova.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.nuova.dto.EspecialidadDTO;
import com.nuova.dto.ProfesionalDTO;
import com.nuova.dto.ProfesionalEspecialidadDTO;
import com.nuova.model.Especialidad;
import com.nuova.model.Profesional;
import com.nuova.model.ProfesionalEspecialidad;
import com.nuova.service.EspecialidadManager;
import com.nuova.service.ProfesionalManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;

@Controller
public class ProfesionalController {
    @Autowired
    ProfesionalManager profesionalManager;
    @Autowired
    EspecialidadManager especialidadManager;

    @RequestMapping(value = ConstantControllers.FORM_ADD_PROFESIONAL, method = RequestMethod.GET)
    public String formAddEspecialidad(ModelMap map) {
        List<Especialidad> especialidadList = especialidadManager.findAll();
        map.addAttribute("especialidadList", especialidadList);
        map.addAttribute("profesional", new ProfesionalDTO());
        return ConstantRedirect.VIEW_FORM_ADD_PROFESIONAL;
    }

    @RequestMapping(value = ConstantControllers.FORM_EDIT_PROFESIONAL, method = RequestMethod.GET)
    public String formEditEspecialidad(ModelMap map,
            @PathVariable("profesionalId") Integer profesionalId) {
        if (profesionalId != null) {
            List<Especialidad> especialidadList = especialidadManager.findAll();
            ProfesionalDTO dto = transformProfesionalToDto(profesionalManager.findProfesionalById(profesionalId));

            map.addAttribute("especialidadList", especialidadList);
            map.addAttribute("especialidadListEdit", dto.getEspecialidadListEdit());
            map.addAttribute("profesional", dto);
        }
        return ConstantRedirect.VIEW_FORM_EDIT_PROFESIONAL;
    }

    @RequestMapping(value = ConstantControllers.FORM_DELETE_PROFESIONAL, method = RequestMethod.GET)
    public String formDeleteEspecialidad(ModelMap map,
            @PathVariable("profesionalId") Integer profesionalId) {
        if (profesionalId != null) {
            map.addAttribute("profesional", profesionalManager.findProfesionalById(profesionalId));
        }
        return ConstantRedirect.VIEW_FORM_DELETE_PROFESIONAL;
    }

    @RequestMapping(value = ConstantControllers.ADD_PROFESIONAL, method = RequestMethod.POST)
    public String addProfesional(
            @ModelAttribute(value = "employee") ProfesionalDTO profesionalDto,
            BindingResult result) {
        Profesional profesional = transformDtoToProfesional(profesionalDto);
        profesionalManager.add(profesional);
        return "redirect:" + ConstantControllers.MAIN_PROFESIONAL;
    }

    @RequestMapping(value = ConstantControllers.DELETE_PROFESIONAL, method = RequestMethod.POST)
    public String deleteEspecialidad(@ModelAttribute(value = "profesional") Profesional profesional) {
        profesionalManager.delete(profesional.getProfesionalId());
        return "redirect:" + ConstantControllers.MAIN_PROFESIONAL;
    }

    @RequestMapping(value = ConstantControllers.EDIT_PROFESIONAL, method = RequestMethod.POST)
    public String editEspecialidad(@ModelAttribute(value = "profesional") Profesional profesional) {
        profesionalManager.edit(profesional);
        return "redirect:" + ConstantControllers.MAIN_PROFESIONAL;
    }

    @RequestMapping(value = ConstantControllers.MAIN_PROFESIONAL, method = RequestMethod.GET)
    public String mainProfesional(ModelMap map) {

        map.addAttribute("profesionalList", profesionalManager.findAll());

        return ConstantRedirect.VIEW_MAIN_PROFESIONAL;
    }

    private Profesional transformDtoToProfesional(ProfesionalDTO p) {
        List<ProfesionalEspecialidad> profesionalEspecialidades = new ArrayList<ProfesionalEspecialidad>();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaHabilitacion = null;
        try {
            fechaHabilitacion = formatter.parse(p.getFechaVencimientoHabilitacion());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Profesional profesional = new Profesional(p.getApellido(), p.getNombre(), p.getTelefono(), p.getMatricula(),
                p.getRegistroNacional(), p.getTituloProfesional(), new Byte(p.getHabilitacionSiprosa()),
                fechaHabilitacion, null);

        for (Integer id : p.getEspecialidadList()) {
            Especialidad especialidad = especialidadManager.findEspecialidadById(id);
            profesionalEspecialidades.add(new ProfesionalEspecialidad(profesional, especialidad));
        }

        profesional.setProfesionalEspecialidads(profesionalEspecialidades);

        return profesional;
    }

    private ProfesionalDTO transformProfesionalToDto(Profesional p) {
        ProfesionalDTO dto = new ProfesionalDTO();
        dto.setApellido(p.getApellido());
        dto.setNombre(p.getNombre());
        dto.setTelefono(p.getTelefono());
        dto.setTituloProfesional(p.getTituloProfesional());
        dto.setRegistroNacional(p.getRegistroNacional());
        dto.setProfesionalId(p.getProfesionalId());
        dto.setHabilitacionSiprosa(p.getHabilitacionSiprosa().toString());
        dto.setMatricula(p.getMatricula());
        dto.setFechaVencimientoHabilitacion(p.getFechaVencimientoHabilitacion() + "");

        List<ProfesionalEspecialidad> listPE = p.getProfesionalEspecialidads();
        for (ProfesionalEspecialidad pe : listPE) {
            Especialidad especialidad = especialidadManager.findEspecialidadById(pe.getEspecialidad()
                    .getEspecialidadId());

            ProfesionalDTO profesionalDto = new ProfesionalDTO();
            profesionalDto.setProfesionalId(pe.getProfesional().getProfesionalId());
            EspecialidadDTO especialidadDto = new EspecialidadDTO();
            especialidadDto.setId(pe.getEspecialidad().getEspecialidadId());

            dto.getEspecialidadListOld().add(
                    new ProfesionalEspecialidadDTO(pe.getId(), profesionalDto, especialidadDto));
            dto.getEspecialidadListEdit().put(especialidad.getEspecialidadId(),
                    especialidad.getNombre());
        }

        return dto;
    }
}
