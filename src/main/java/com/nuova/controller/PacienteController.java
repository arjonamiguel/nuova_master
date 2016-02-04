package com.nuova.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.nuova.dto.ComboItemDTO;
import com.nuova.dto.ObraSocialDTO;
import com.nuova.dto.OrdenTipoDTO;
import com.nuova.dto.PacienteDTO;
import com.nuova.model.Obrasocial;
import com.nuova.model.OrdenTipo;
import com.nuova.model.Paciente;
import com.nuova.model.PacienteObrasocial;
import com.nuova.service.ObraSocialManager;
import com.nuova.service.OrdenManager;
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
    @Autowired
    OrdenManager ordenManager;

    @RequestMapping(value = ConstantControllers.TIPO_ORDEN, method = RequestMethod.GET)
    public String tipoOrden(ModelMap map,
            @PathVariable("pacienteId") Integer pacienteId) {
        List<OrdenTipo> ordenestipo = ordenManager.finAllOrdenTipo();
        Paciente paciente = pacienteManager.fin1dPacienteById(pacienteId);
        OrdenTipoDTO ordenTipo = new OrdenTipoDTO();
        ordenTipo.setPacienteId(paciente.getPacienteId());

        map.addAttribute("ordenestipo", getOrdenesTipoDTO(ordenestipo));
        map.addAttribute("paciente", transformPacienteToDto(paciente));
        map.addAttribute("ordenTipo", ordenTipo);
        return ConstantRedirect.VIEW_FORM_TIPO_ORDEN;
    }

    @RequestMapping(value = ConstantControllers.FORM_ADD_PACIENTE, method = RequestMethod.GET)
    public String formAddPaciente(ModelMap map) {
        List<Obrasocial> obrasocialList = obrasocialManager.findAll();
        map.addAttribute("provinciaList", Util.getProvincias());
        map.addAttribute("parentescosList", Util.getParentescos());
        map.addAttribute("obrasocialList", obrasocialList);
        map.addAttribute("obrasocialDTOList", new ArrayList<ObraSocialDTO>());
        map.addAttribute("paciente", new PacienteDTO());
        return ConstantRedirect.VIEW_FORM_ADD_PACIENTE;
    }

    @RequestMapping(value = ConstantControllers.FORM_EDIT_PACIENTE, method = RequestMethod.GET)
    public String formEditPaciente(ModelMap map,
            @PathVariable("pacienteId") Integer pacienteId) {
        if (pacienteId != null) {
            PacienteDTO dto = transformPacienteToDto(pacienteManager.fin1dPacienteById(pacienteId));
            map.addAttribute("provinciaList", Util.getProvincias());
            map.addAttribute("parentescosList", Util.getParentescos());
            map.addAttribute("obrasocialList", obrasocialManager.findAll());
            map.addAttribute("paciente", dto);
            map.addAttribute("isTitular", dto.isTitular());
        }
        return ConstantRedirect.VIEW_FORM_EDIT_PACIENTE;
    }

    @RequestMapping(value = ConstantControllers.FORM_DELETE_PACIENTE, method = RequestMethod.GET)
    public String formDeletePaciente(ModelMap map,
            @PathVariable("pacienteId") Integer pacienteId) {
        if (pacienteId != null) {
            PacienteDTO dto = transformPacienteToDto(pacienteManager.fin1dPacienteById(pacienteId));
            map.addAttribute("provinciaList", Util.getProvincias());
            map.addAttribute("parentescosList", Util.getParentescos());
            map.addAttribute("obrasocialList", obrasocialManager.findAll());
            map.addAttribute("paciente", dto);
            map.addAttribute("isTitular", dto.isTitular());

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
        Paciente paciente = pacienteManager.fin1dPacienteById(dto.getPacienteId());
        pacienteManager.deletePacienteObrasocial(paciente.getPacienteId());
        pacienteManager.delete(paciente.getPacienteId());
        return "redirect:" + ConstantControllers.MAIN_PACIENTE;
    }

    @RequestMapping(value = ConstantControllers.EDIT_PACIENTE, method = RequestMethod.POST)
    public String editPaciente(@ModelAttribute(value = "paciente") PacienteDTO dto) {
        Paciente pacienteOld = pacienteManager.fin1dPacienteById(dto.getPacienteId());
        Paciente paciente = transformDtoToPaciente(dto);
        for (PacienteObrasocial po : pacienteOld.getPacienteObrasocials()) {
            if (po.getPaciente() != null && po.getPaciente().getPacienteId() != null) {
                pacienteManager.deletePacienteObrasocial(po.getPaciente().getPacienteId());
            }
        }
        paciente.setPaciente(pacienteOld.getPaciente());
        pacienteManager.edit(paciente);
        return "redirect:" + ConstantControllers.MAIN_PACIENTE;
    }

    @RequestMapping(value = ConstantControllers.MAIN_PACIENTE, method = RequestMethod.GET)
    public String mainPaciente(ModelMap map) {

        // map.addAttribute("pacienteList", transformPacientesToDtoList(pacienteManager.findAll()));

        return ConstantRedirect.VIEW_MAIN_PACIENTE;
    }

    // Ajax --------------------------------------------

    @RequestMapping(value = ConstantControllers.AJAX_GET_PACIENTES_PAGINADOS, method = RequestMethod.GET)
    public @ResponseBody Page<PacienteDTO> getPacientesPaginados(
            @RequestParam(required = false, defaultValue = "0") Integer start,
            @RequestParam(required = false, defaultValue = "50") Integer limit) {

        // Sort and Pagination
        // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
        Pageable pageable = new PageRequest(start, limit);

        Page<Paciente> pacientes = pacienteManager.findPacientesByPageable(pageable);
        // Page<Paciente> batches = new Pa
        // enrollImportBatchService.getBatches(pageable);
        List<PacienteDTO> dtos = new ArrayList<PacienteDTO>();
        for (Paciente p : pacientes) {
            PacienteDTO dto = transformPacienteToDto(p);
            dtos.add(dto);
        }

        return new PageImpl<PacienteDTO>(dtos, pageable, pacientes.getTotalElements());
    }

    @RequestMapping(value = ConstantControllers.AJAX_GET_SEARCH_PACIENTES_PAGINADOS, method = RequestMethod.GET)
    public @ResponseBody Page<PacienteDTO> getSearchPacientesPaginados(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "0") Integer start,
            @RequestParam(required = false, defaultValue = "50") Integer limit) {

        // Sort and Pagination
        // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
        Pageable pageable = new PageRequest(start, limit);

        Page<Paciente> pacientes = pacienteManager.findPacientesBySearch(search, pageable);
        // Page<Paciente> batches = new Pa
        // enrollImportBatchService.getBatches(pageable);
        List<PacienteDTO> dtos = new ArrayList<PacienteDTO>();
        for (Paciente p : pacientes) {
            PacienteDTO dto = transformPacienteToDto(p);
            dtos.add(dto);
        }

        return new PageImpl<PacienteDTO>(dtos, pageable, pacientes.getTotalElements());
    }

    @RequestMapping(value = ConstantControllers.AJAX_GET_AUTOCOMPLETE_PACIENTES, method = RequestMethod.POST)
    public @ResponseBody List<ComboItemDTO> getAutocompletePaciente(
            @RequestParam(required = false, defaultValue = "") String query) {
        List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
        for (Paciente p : pacienteManager.findPacienteAutocomplete(query)) {
            retorno.add(new ComboItemDTO(p.getPacienteId() + "", p.getApellido() + ", " + p.getNombre()));
        }

        return retorno;
    }

    // Adherentes --------------------------------------------
    @RequestMapping(value = ConstantControllers.FORM_ADD_ADHERENTE, method = RequestMethod.GET)
    public String formAddAdherente(ModelMap map,
            @PathVariable("titularId") Integer titularId) {
        Paciente titular = pacienteManager.fin1dPacienteById(titularId);
        PacienteDTO dto = new PacienteDTO();
        dto.setApellido(titular.getApellido());
        dto.setProvincia(titular.getProvincia());
        dto.setTelefono(titular.getTelefono());
        dto.setMail(titular.getMail());
        dto.setDomicilio(titular.getDomicilio());
        dto.setTitularId(titular.getPacienteId());

        List<ObraSocialDTO> obrasociales = new ArrayList<ObraSocialDTO>();
        for (PacienteObrasocial po : titular.getPacienteObrasocials()) {
            ObraSocialDTO o = new ObraSocialDTO();
            o.setNombre(po.getObrasocial().getNombre());
            o.setObrasocialId(po.getObrasocial().getObrasocialId());
            obrasociales.add(o);
        }
        dto.setObrasocialList(obrasociales);

        List<Obrasocial> obrasocialList = obrasocialManager.findAll();
        map.addAttribute("datosTitular", titular.getApellido() + " " + titular.getNombre());
        map.addAttribute("parentescosList", Util.getParentescos());
        map.addAttribute("provinciaList", Util.getProvincias());
        map.addAttribute("obrasocialList", obrasocialList);
        map.addAttribute("obrasocialDTOList", new ArrayList<ObraSocialDTO>());
        map.addAttribute("paciente", dto);
        return ConstantRedirect.VIEW_FORM_ADD_ADHERENTE;
    }

    @RequestMapping(value = ConstantControllers.ADD_ADHERENTE, method = RequestMethod.POST)
    public String addAdherente(
            @ModelAttribute(value = "paciente") PacienteDTO dto,
            BindingResult result) {
        Paciente titular = pacienteManager.fin1dPacienteById(dto.getTitularId());
        Paciente paciente = transformDtoToPaciente(dto);
        paciente.setPaciente(titular);
        pacienteManager.add(paciente);
        return "redirect:" + ConstantControllers.MAIN_PACIENTE;
    }

    // -------------------------------------------------------

    public PacienteDTO transformPacienteToDto(Paciente p) {
        PacienteDTO dto = new PacienteDTO();
        dto.setPacienteId(p.getPacienteId());
        dto.setDni(p.getDni());
        dto.setApellido(p.getApellido());
        dto.setNombre(p.getNombre());
        dto.setDomicilio(p.getDomicilio());
        dto.setFechaNacimiento("" + p.getFechaNacimiento());
        dto.setCoseguro(p.getCoseguro().intValue() == 1 ? true : false);
        dto.setCheckedLiberado(p.getCoseguro().intValue() == 1 ? "checked" : "");
        dto.setMail(p.getMail());
        dto.setTelefono(p.getTelefono());
        dto.setProvincia(p.getProvincia());
        dto.setZonaAfiliacion(p.getZonaAfiliacion());

        if (p.getPaciente() != null && p.getPaciente().getPacienteId() != null) {
            dto.setPacienteTitular(transformPacienteToDto(pacienteManager
                    .fin1dPacienteById(p.getPaciente().getPacienteId())));
        }

        dto.setParentesco(p.getParentesco().intValue());
        for (ComboItemDTO item : Util.getParentescos()) {
            if (dto.getParentesco() == Integer.valueOf(item.getId()).intValue())
                dto.setParentescoDescription(item.getValue());
        }

        // if (p.getTitular() != null) {
        // dto.setTitular(p.getTitular().intValue() == 1 ? true : false);
        // dto.setCheckedTitular(p.getTitular().intValue() == 1 ? "checked" : "");
        //
        // }

        for (PacienteObrasocial po : p.getPacienteObrasocials()) {
            dto.getObrasocialList().add(
                    new ObraSocialDTO(po.getObrasocial().getObrasocialId(), po.getObrasocial().getNombre(),
                            po.getNroCredencial(), po.getProvisorio() == 1 ? "checked" : ""));
        }

        for (Paciente ad : p.getPacientes()) {
            PacienteDTO dtoad = new PacienteDTO();
            dtoad.setPacienteId(ad.getPacienteId());
            dtoad.setApellido(ad.getApellido());
            dtoad.setNombre(ad.getNombre());
            dtoad.setDomicilio(ad.getDomicilio());
            dtoad.setFechaNacimiento("" + ad.getFechaNacimiento());
            dtoad.setCoseguro(ad.getCoseguro().intValue() == 1 ? true : false);
            dtoad.setCheckedLiberado(p.getCoseguro().intValue() == 1 ? "checked" : "");
            dtoad.setMail(ad.getMail());
            dtoad.setTelefono(ad.getTelefono());
            dtoad.setDni(ad.getDni());
            dtoad.setZonaAfiliacion(p.getZonaAfiliacion());
            dtoad.setParentesco(ad.getParentesco().intValue());
            for (PacienteObrasocial poo : ad.getPacienteObrasocials()) {
                dtoad.setCrdencial(poo.getNroCredencial());
                break;
            }

            for (ComboItemDTO item : Util.getParentescos()) {
                if (dtoad.getParentesco() == Integer.valueOf(item.getId()).intValue())
                    dtoad.setParentescoDescription(item.getValue());
            }

            dto.getAdherentes().add(dtoad);
        }

        return dto;
    }

    public List<PacienteDTO> transformPacientesToDtoList(List<Paciente> listPacientes) {
        List<PacienteDTO> retorno = new ArrayList<PacienteDTO>();
        for (Paciente p : listPacientes) {
            PacienteDTO dto = new PacienteDTO();
            dto.setPacienteId(p.getPacienteId());
            dto.setDni(p.getDni());
            dto.setApellido(p.getApellido());
            dto.setNombre(p.getNombre());
            dto.setDomicilio(p.getDomicilio());
            dto.setFechaNacimiento("" + p.getFechaNacimiento());
            dto.setCoseguro(p.getCoseguro().intValue() == 1 ? true : false);
            dto.setCheckedLiberado(p.getCoseguro().intValue() == 1 ? "checked" : "");
            dto.setMail(p.getMail());
            dto.setTelefono(p.getTelefono());
            for (Paciente ad : p.getPacientes()) {
                PacienteDTO dtoad = new PacienteDTO();
                dtoad.setPacienteId(ad.getPacienteId());
                dtoad.setApellido(ad.getApellido());
                dtoad.setNombre(ad.getNombre());
                dtoad.setDomicilio(ad.getDomicilio());
                dtoad.setFechaNacimiento("" + ad.getFechaNacimiento());
                dtoad.setCoseguro(ad.getCoseguro().intValue() == 1 ? true : false);
                dtoad.setCheckedLiberado(p.getCoseguro().intValue() == 1 ? "checked" : "");
                dtoad.setMail(ad.getMail());
                dtoad.setTelefono(ad.getTelefono());
                dto.getAdherentes().add(dtoad);
            }
            retorno.add(dto);
        }

        return retorno;
    }

    public Paciente transformDtoToPaciente(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setPacienteId(dto.getPacienteId());
        paciente.setDni(dto.getDni());
        paciente.setApellido(dto.getApellido());
        paciente.setNombre(dto.getNombre());
        paciente.setDomicilio(dto.getDomicilio());
        paciente.setFechaNacimiento(Util.parseToDate(dto.getFechaNacimiento()));
        paciente.setCoseguro(dto.getCoseguro() ? new Byte("1") : new Byte("0"));
        paciente.setMail(dto.getMail());
        paciente.setTelefono(dto.getTelefono());
        paciente.setProvincia(dto.getProvincia());
        paciente.setParentesco(new Byte(dto.getParentesco() + ""));
        paciente.setZonaAfiliacion(dto.getZonaAfiliacion());

        for (ObraSocialDTO os : dto.getObrasocialListEdit()) {
            if (os.getObrasocialId() != null) {
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
        }

        return paciente;

    }

    private OrdenTipoDTO transformOrdenTipoToDto(OrdenTipo ot) {
        OrdenTipoDTO retorno = new OrdenTipoDTO();
        retorno.setOrdenTipoId(ot.getOrdenTipoId());
        retorno.setNombre(ot.getNombre());
        retorno.setMonto1(ot.getMonto1());
        retorno.setMonto2(ot.getMonto2());
        retorno.setMonto3(ot.getMonto3());
        retorno.setCodigo(ot.getCodigo());

        return retorno;
    }

    private List<OrdenTipoDTO> getOrdenesTipoDTO(List<OrdenTipo> list) {
        List<OrdenTipoDTO> retorno = new ArrayList<OrdenTipoDTO>();
        for (OrdenTipo ot : list) {
            retorno.add(transformOrdenTipoToDto(ot));
        }

        return retorno;
    }

}
