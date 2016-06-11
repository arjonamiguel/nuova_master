package com.nuova.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.nuova.dto.PrestadorDTO;
import com.nuova.model.Especialidad;
import com.nuova.model.Prestadores;
import com.nuova.model.PrestadoresEspecialidad;
import com.nuova.service.EspecialidadManager;
import com.nuova.service.PrestadoresManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;
import com.nuova.utils.Util;

@Controller
public class PrestadorController {
    @Autowired
    PrestadoresManager prestadorManager;
    @Autowired
    EspecialidadManager especialidadManager;

    @RequestMapping(value = ConstantControllers.FORM_ADD_PRESTADOR, method = RequestMethod.GET)
    public String formAddPrestador(ModelMap map) {
        Integer tipoPrestador = new Integer(2);
        List<Especialidad> especialidadList = especialidadManager.findAllByTipo(tipoPrestador);
        map.addAttribute("especialidadList", especialidadList);
        map.addAttribute("prestador", new PrestadorDTO());
        map.addAttribute("provinciasList", Util.getProvincias());
        return ConstantRedirect.VIEW_FORM_ADD_PRESTADOR;
    }

    @RequestMapping(value = ConstantControllers.FORM_EDIT_PRESTADOR, method = RequestMethod.GET)
    public String formEditPrestador(ModelMap map, @PathVariable("prestadorId") Integer prestadorId) {
        map.addAttribute("provinciasList", Util.getProvincias());

        if (prestadorId != null) {
            Integer tipoPrestador = new Integer(2);
            List<Especialidad> especialidadList = especialidadManager.findAllByTipo(tipoPrestador);
            PrestadorDTO dto = transformPrestadoresToDto(prestadorManager.findPrestadorById(prestadorId));

            map.addAttribute("especialidadList", especialidadList);
            map.addAttribute("especialidadListEdit", dto.getEspecialidadListEdit());
            map.addAttribute("prestador", dto);
        }

        return ConstantRedirect.VIEW_FORM_EDIT_PRESTADOR;
    }

    @RequestMapping(value = ConstantControllers.FORM_DELETE_PRESTADOR, method = RequestMethod.GET)
    public String formDeletePrestador(ModelMap map,
            @PathVariable("prestadorId") Integer prestadorId) {
        map.addAttribute("provinciasList", Util.getProvincias());
        if (prestadorId != null) {
            PrestadorDTO dto = transformPrestadoresToDto(prestadorManager.findPrestadorById(prestadorId));
            map.addAttribute("especialidadListEdit", dto.getEspecialidadListEdit());
            map.addAttribute("prestador", dto);
        }

        return ConstantRedirect.VIEW_FORM_DELETE_PRESTADOR;
    }

    @RequestMapping(value = ConstantControllers.ADD_PRESTADOR, method = RequestMethod.POST)
    public String addPrestador(@ModelAttribute(value = "prestador") PrestadorDTO dto,
            BindingResult result) {

        if (dto != null) {
        	Prestadores p  = transformDtoToPrestadores(dto);
            p.setEliminado(0); // 0=activo / 1=eliminado logico
        	prestadorManager.add(p);
        }
        // Especialidades
        for (Integer id : dto.getEspecialidadList()) {
            Especialidad especialidad = especialidadManager.findEspecialidadById(id);
            // profesionalEspecialidades.add(new ProfesionalEspecialidad(profesional, especialidad));
        }

        return "redirect:" + ConstantControllers.MAIN_PRESTADOR;
    }

    @RequestMapping(value = ConstantControllers.DELETE_PRESTADOR, method = RequestMethod.POST)
    public String deletePrestador(@ModelAttribute(value = "prestador") PrestadorDTO dto) {
    	Prestadores prestador = prestadorManager.findPrestadorById(dto.getPrestadorId());
        prestador.setEliminado(1);
    	// prestadorManager.delete(dto.getPrestadorId());
        prestadorManager.edit(prestador);
    	return "redirect:" + ConstantControllers.MAIN_PRESTADOR;
    }

    @RequestMapping(value = ConstantControllers.EDIT_PRESTADOR, method = RequestMethod.POST)
    public String editPrestador(@ModelAttribute(value = "prestador") PrestadorDTO dto) {
        Prestadores prestadorOld = prestadorManager.findPrestadorById(dto.getPrestadorId());
        for (PrestadoresEspecialidad pe : prestadorOld.getPrestadoresEspecialidads()) {
            prestadorManager.deletePrestadorEspecialidad(dto.getPrestadorId());
            break;
        }
        prestadorManager.edit(transformDtoToPrestadores(dto));
        return "redirect:" + ConstantControllers.MAIN_PRESTADOR;
    }

    @RequestMapping(value = ConstantControllers.MAIN_PRESTADOR, method = RequestMethod.GET)
    public String mainPrestador(ModelMap map) {
        // map.addAttribute("obrasocialList", obrasocialManager.findAll());
        return ConstantRedirect.VIEW_MAIN_PRESTADOR;
    }

    // Ajax --------------------------------------------
    @RequestMapping(value = ConstantControllers.AJAX_GET_PRESTADORES_PAGINADOS,
            method = RequestMethod.GET)
    public @ResponseBody Page<PrestadorDTO> getProfesionalesPaginados(
            @RequestParam(required = false, defaultValue = "0") Integer start,
            @RequestParam(required = false, defaultValue = "50") Integer limit) {

        // Sort and Pagination
        // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
        Pageable pageable = new PageRequest(start, limit);

        Page<Prestadores> prestadores = prestadorManager.findPrestadoresByPageable(pageable);
        List<PrestadorDTO> dtos = new ArrayList<PrestadorDTO>();
        for (Prestadores p : prestadores) {
            dtos.add(transformPrestadoresToDto(p));
        }

        return new PageImpl<PrestadorDTO>(dtos, pageable, prestadores.getTotalElements());
    }

    @RequestMapping(value = ConstantControllers.AJAX_GET_SEARCH_PRESTADORES_PAGINADOS,
            method = RequestMethod.GET)
    public @ResponseBody Page<PrestadorDTO> getSearchPrestadoresPaginados(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "0") Integer start,
            @RequestParam(required = false, defaultValue = "50") Integer limit) {

        // Sort and Pagination
        // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
        Pageable pageable = new PageRequest(start, limit);

        Page<Prestadores> prestadores = prestadorManager.findPrestadoresBySearch(search, pageable);
        List<PrestadorDTO> dtos = new ArrayList<PrestadorDTO>();
        for (Prestadores p : prestadores) {
            dtos.add(transformPrestadoresToDto(p));
        }

        return new PageImpl<PrestadorDTO>(dtos, pageable, prestadores.getTotalElements());
    }

    private Prestadores transformDtoToPrestadores(PrestadorDTO dto) {
        Prestadores retorno = new Prestadores();
        Set<PrestadoresEspecialidad> prestadorEspecialidades = new HashSet<PrestadoresEspecialidad>();

        retorno.setPrestadorId(dto.getPrestadorId());
        retorno.setNombre(dto.getNombre());
        retorno.setDomicilio(dto.getDomicilio());
        retorno.setTelefono(dto.getTelefono());
        retorno.setProvincia(dto.getProvincia());

        // Especialidades
        for (Integer id : dto.getEspecialidadList()) {
            Especialidad especialidad = especialidadManager.findEspecialidadById(id);
            prestadorEspecialidades.add(new PrestadoresEspecialidad(especialidad, retorno));
        }
        retorno.setPrestadoresEspecialidads(prestadorEspecialidades);

        return retorno;
    }

    private PrestadorDTO transformPrestadoresToDto(Prestadores p) {
        PrestadorDTO retorno = new PrestadorDTO();
        retorno.setPrestadorId(p.getPrestadorId());
        retorno.setNombre(p.getNombre());
        retorno.setDomicilio(p.getDomicilio());
        retorno.setTelefono(p.getTelefono());
        retorno.setProvincia(p.getProvincia());

        for (PrestadoresEspecialidad pe : p.getPrestadoresEspecialidads()) {
            retorno.getEspecialidadListEdit().put(pe.getEspecialidad().getEspecialidadId(),
                    pe.getEspecialidad().getNombre());
        }

        return retorno;
    }

}
