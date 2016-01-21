package com.nuova.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuova.dto.EspecialidadDTO;
import com.nuova.model.Especialidad;
import com.nuova.service.EspecialidadManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;

@Controller
public class EspecialidadController {
    @Autowired
    EspecialidadManager especialidadManager;

    @RequestMapping(value = ConstantControllers.FORM_ADD_ESPECIALIDAD, method = RequestMethod.GET)
    public String formAddEspecialidad(ModelMap map) {
        map.addAttribute("especialidad", new Especialidad());
        return ConstantRedirect.VIEW_FORM_ADD_ESPECIALIDAD;
    }

    @RequestMapping(value = ConstantControllers.FORM_EDIT_ESPECIALIDAD, method = RequestMethod.GET)
    public String formEditEspecialidad(ModelMap map,
            @PathVariable("especialidadId") Integer especialidadId) {
        if (especialidadId != null) {
            map.addAttribute("especialidad", especialidadManager.findEspecialidadById(especialidadId));
        }

        return ConstantRedirect.VIEW_FORM_EDIT_ESPECIALIDAD;
    }

    @RequestMapping(value = ConstantControllers.FORM_DELETE_ESPECIALIDAD, method = RequestMethod.GET)
    public String formDeleteEspecialidad(ModelMap map,
            @PathVariable("especialidadId") Integer especialidadId) {
        if (especialidadId != null) {
            map.addAttribute("especialidad", especialidadManager.findEspecialidadById(especialidadId));
        }

        return ConstantRedirect.VIEW_FORM_DELETE_ESPECIALIDAD;
    }

    @RequestMapping(value = ConstantControllers.ADD_ESPECIALIDAD, method = RequestMethod.POST)
    public String addEspecialidad(
            @ModelAttribute(value = "especialidad") Especialidad especialidad,
            BindingResult result) {

        if (especialidad != null) {
            especialidadManager.add(especialidad);
        }

        return "redirect:" + ConstantControllers.MAIN_ESPECIALIDAD;
    }

    @RequestMapping(value = ConstantControllers.DELETE_ESPECIALIDAD, method = RequestMethod.POST)
    public String deleteEspecialidad(@ModelAttribute(value = "especialidad") Especialidad especialidad) {
        especialidadManager.delete(especialidad.getEspecialidadId());
        return "redirect:" + ConstantControllers.MAIN_ESPECIALIDAD;
    }

    @RequestMapping(value = ConstantControllers.EDIT_ESPECIALIDAD, method = RequestMethod.POST)
    public String editEspecialidad(@ModelAttribute(value = "especialidad") Especialidad especialidad) {
        especialidadManager.edit(especialidad);
        return "redirect:" + ConstantControllers.MAIN_ESPECIALIDAD;
    }

    @RequestMapping(value = ConstantControllers.MAIN_ESPECIALIDAD, method = RequestMethod.GET)
    public String mainEspecialidad(ModelMap map) {

        // map.addAttribute("especialidadList", especialidadManager.findAll());

        return ConstantRedirect.VIEW_MAIN_ESPECIALIDAD;
    }

    // Ajax --------------------------------------------
    @RequestMapping(value = ConstantControllers.AJAX_GET_ESPECIALIDADES_PAGINADOS, method = RequestMethod.GET)
    public @ResponseBody Page<EspecialidadDTO> getProfesionalesPaginados(
            @RequestParam(required = false, defaultValue = "0") Integer start,
            @RequestParam(required = false, defaultValue = "50") Integer limit) {

        // Sort and Pagination
        // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
        Pageable pageable = new PageRequest(start, limit);

        Page<Especialidad> especialidades = especialidadManager.findEspecialidadesByPageable(pageable);
        List<EspecialidadDTO> dtos = new ArrayList<EspecialidadDTO>();
        for (Especialidad e : especialidades) {
            EspecialidadDTO dto = new EspecialidadDTO();
            dto.setId(e.getEspecialidadId());
            dto.setNombre(e.getNombre());
            dtos.add(dto);
        }

        return new PageImpl<EspecialidadDTO>(dtos, pageable, especialidades.getTotalElements());
    }

    @RequestMapping(value = ConstantControllers.AJAX_GET_SEARCH_ESPECIALIDADES_PAGINADOS, method = RequestMethod.GET)
    public @ResponseBody Page<EspecialidadDTO> getSearchProfesionalesPaginados(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "0") Integer start,
            @RequestParam(required = false, defaultValue = "50") Integer limit) {

        // Sort and Pagination
        // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
        Pageable pageable = new PageRequest(start, limit);

        Page<Especialidad> especialidades = especialidadManager.findEspecialidadesBySearch(search, pageable);
        List<EspecialidadDTO> dtos = new ArrayList<EspecialidadDTO>();
        for (Especialidad e : especialidades) {
            EspecialidadDTO dto = new EspecialidadDTO();
            dto.setId(e.getEspecialidadId());
            dto.setNombre(e.getNombre());
            dtos.add(dto);
        }

        return new PageImpl<EspecialidadDTO>(dtos, pageable, especialidades.getTotalElements());
    }

}
