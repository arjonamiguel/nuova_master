package com.nuova.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
import com.nuova.dto.EspecialidadDTO;
import com.nuova.dto.ObraSocialDTO;
import com.nuova.dto.ObservacionesDTO;
import com.nuova.dto.OrdenDTO;
import com.nuova.dto.OrdenPracticaDTO;
import com.nuova.dto.OrdenTipoDTO;
import com.nuova.dto.OrdenWorkflowDTO;
import com.nuova.dto.PacienteDTO;
import com.nuova.dto.ProfesionalDTO;
import com.nuova.dto.ProfesionalEspecialidadDTO;
import com.nuova.model.Especialidad;
import com.nuova.model.Obrasocial;
import com.nuova.model.Observaciones;
import com.nuova.model.Orden;
import com.nuova.model.OrdenPractica;
import com.nuova.model.OrdenProfesional;
import com.nuova.model.OrdenTipo;
import com.nuova.model.OrdenWorkflow;
import com.nuova.model.Paciente;
import com.nuova.model.PacienteObrasocial;
import com.nuova.model.Practica;
import com.nuova.model.Profesional;
import com.nuova.model.ProfesionalEspecialidad;
import com.nuova.service.EspecialidadManager;
import com.nuova.service.ObraSocialManager;
import com.nuova.service.ObservacionManager;
import com.nuova.service.OrdenManager;
import com.nuova.service.PacienteManager;
import com.nuova.service.PracticaManager;
import com.nuova.service.ProfesionalManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantOrdenEstado;
import com.nuova.utils.ConstantRedirect;
import com.nuova.utils.Util;

@Controller
public class OrdenController {
    @Autowired
    OrdenManager ordenManager;
    @Autowired
    PacienteManager pacienteManager;
    @Autowired
    ObraSocialManager obrasocialManager;
    @Autowired
    PracticaManager practicaManager;
    @Autowired
    ObservacionManager observacionManager;
    @Autowired
    ProfesionalManager profesionalManager;
    @Autowired
    EspecialidadManager especialidadManager;

    private String rechazada = "<span class='label-important' style='color:white;'>RECHAZADA</span>";
    private String incompleta = "<span class='label-warning' style='color:white;'>INCOMPLETA</span>";
    private String autorizada = " <span class='label-success' style='color:white;'>AUTORIZADA</span>";
    private String pendiente = "<span class='label-warning' style='color:white;'>PENDIETE</span>";
    private String cerrada = "<span class='label-warning' style='color:white;'>CERRADA</span>";
    private String enobservacion = "<span class='label-warning' style='color:white;'>EN OBSERVACION</span>";

    @RequestMapping(value = ConstantControllers.REDIRECT_ORDEN, method = RequestMethod.POST)
    public String redirectOrden(ModelMap map,
            @ModelAttribute(value = "ordenTipo") OrdenTipoDTO ordenTipo) {
        String redirect = "";
        int codigo = ordenTipo.getCodigo().intValue();
        Paciente paciente = pacienteManager.fin1dPacienteById(ordenTipo.getPacienteId());
        OrdenDTO ordenDto = new OrdenDTO();
        ordenDto.setPaciente(transformPacienteToDto(paciente));
        OrdenTipo ot = ordenManager.findOrdenTipoByCodigo(codigo);
        ordenDto.setOrdenTipo(transformOrdenTipoToDto(ot));

        if (codigo == Util.ORDEN_CONSULTA) {
            OrdenTipoDTO otdto = transformOrdenTipoToDto(ot);
            List<Profesional> profesionales = profesionalManager.findAll();
            map.addAttribute("profesionales", getProfesionalDTOList(profesionales));
            map.addAttribute("ordenTipoDto", otdto);
            map.addAttribute("montosorden", getMontosOrden(otdto));
            redirect = ConstantRedirect.VIEW_FORM_CONSULTA_BY_PACIENTE;

        } else if (codigo == Util.ORDEN_ODONTOLOGICA) {
            OrdenTipoDTO otdto = transformOrdenTipoToDto(ot);
            map.addAttribute("montosorden", getMontosOrden(otdto));
            map.addAttribute("ordenTipoDto", otdto);
            map.addAttribute("montosorden", getMontosOrden(otdto));
            redirect = ConstantRedirect.VIEW_FORM_ODONTOLOGIA_BY_PACIENTE;

        } else if (codigo == Util.ORDEN_PRACTICA) {
            redirect = ConstantRedirect.VIEW_FORM_ADD_ORDEN_BY_PACIENTE;
        }

        map.addAttribute("ordenDto", ordenDto);
        return redirect;
    }

    @RequestMapping(value = ConstantControllers.FORM_ADD_ORDEN, method = RequestMethod.GET)
    public String formAddOrden(ModelMap map) {
        List<Paciente> pacientes = pacienteManager.findAll();

        map.addAttribute("pacienteList", Util.getComboItems(pacientes));

        map.addAttribute("paciente", new OrdenDTO());
        return ConstantRedirect.VIEW_FORM_ADD_ORDEN;
    }

    @RequestMapping(value = ConstantControllers.FORM_ADD_ORDEN_BY_PACIENTE, method = RequestMethod.GET)
    public String formAddOrdenByPaciente(ModelMap map,
            @PathVariable("pacienteId") Integer pacienteId) {
        Paciente paciente = pacienteManager.fin1dPacienteById(pacienteId);
        OrdenDTO ordenDto = new OrdenDTO();
        ordenDto.setPaciente(transformPacienteToDto(paciente));

        List<ComboItemDTO> practicaList = new ArrayList<ComboItemDTO>();
        map.addAttribute("ordenDto", ordenDto);
        map.addAttribute("practicaList", practicaList);
        return ConstantRedirect.VIEW_FORM_ADD_ORDEN_BY_PACIENTE;
    }

    @RequestMapping(value = ConstantControllers.FORM_EDIT_ORDEN, method = RequestMethod.GET)
    public String formEditOrden(ModelMap map,
            @PathVariable("ordenId") Integer ordenId) {
        if (ordenId != null) {
            OrdenDTO ordenDto = transformOrdenToDto(ordenManager.findOrdenById(ordenId));
            int observacionCount = ordenDto.getObservacioneses().size();
            map.addAttribute("ordenDto", ordenDto);
            map.addAttribute("observacionCount", observacionCount);
            map.addAttribute("practicasList", Util.getComboItems(practicaManager.findAll()));

            map.addAttribute("estadosList", Util.getEstadosList());
        }

        return ConstantRedirect.VIEW_FORM_EDIT_ORDEN;
    }

    // Ajax --------------------------------------------
    @RequestMapping(value = ConstantControllers.AJAX_GET_ORDENES_PAGINADOS, method = RequestMethod.GET)
    public @ResponseBody Page<OrdenDTO> getProfesionalesPaginados(
            @RequestParam(required = false, defaultValue = "0") Integer start,
            @RequestParam(required = false, defaultValue = "50") Integer limit) {

        // Sort and Pagination
        // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
        Pageable pageable = new PageRequest(start, limit);

        Page<Orden> ordenes = ordenManager.findOrdenesByPageable(pageable);
        List<OrdenDTO> dtos = new ArrayList<OrdenDTO>();
        for (Orden o : ordenes) {
            OrdenDTO dto = transformOrdenToDto(o);
            dtos.add(dto);
        }

        return new PageImpl<OrdenDTO>(dtos, pageable, ordenes.getTotalElements());
    }

    @RequestMapping(value = ConstantControllers.AJAX_GET_SEARCH_ORDENES_PAGINADOS, method = RequestMethod.GET)
    public @ResponseBody Page<OrdenDTO> getSearchProfesionalesPaginados(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "0") Integer start,
            @RequestParam(required = false, defaultValue = "50") Integer limit) {

        // Sort and Pagination
        // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
        Pageable pageable = new PageRequest(start, limit);

        Page<Orden> ordenes = ordenManager.findOrdenesBySearch(search, pageable);
        List<OrdenDTO> dtos = new ArrayList<OrdenDTO>();
        for (Orden o : ordenes) {
            OrdenDTO dto = transformOrdenToDto(o);
            dtos.add(dto);
        }

        return new PageImpl<OrdenDTO>(dtos, pageable, ordenes.getTotalElements());
    }

    @RequestMapping(value = ConstantControllers.ADD_ORDEN, method = RequestMethod.POST)
    public String addOrden(@ModelAttribute(value = "ordenDto") OrdenDTO ordenDto,
            BindingResult result) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ordenDto.setEstado(Util.getEstadoInicial(ordenDto));
        Orden orden = transformDtoToOrden(ordenDto);
        orden.setFecha(new Date());
        orden.getOrdenWorkflows().add(new OrdenWorkflow(orden, user.getUsername()
                , orden.getEstado(), new Date()));
        if (ordenDto.getObservacion() != null && !ordenDto.getObservacion().trim().equals("")) {
            orden.getObservacioneses().add(
                    new Observaciones(orden, ordenDto.getObservacion(), user.getUsername(), new Date()));
        }

        ordenManager.add(orden);
        return "redirect:" + ConstantControllers.MAIN_ORDEN;
    }

    @RequestMapping(value = ConstantControllers.EDIT_ORDEN, method = RequestMethod.POST)
    public String editOrden(@ModelAttribute(value = "ordenDto") OrdenDTO dto) {
        Orden orden = ordenManager.findOrdenById(dto.getOrdenId());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<OrdenPracticaDTO> practicas = dto.getOrdenpracticaListEdit();

        // Persisto Observacion
        if (!dto.getObservacion().trim().equals("")) {
            // observacionManager.add(new Observaciones(orden, dto.getObservacion(), user.getUsername(), new Date()));
            orden.getObservacioneses().add(
                    new Observaciones(orden, dto.getObservacion(), user.getUsername(), new Date()));
        }

        // Persiste Practicas
        for (OrdenPractica o : orden.getOrdenPracticas()) {
            ordenManager.deleteOrdenPractica(o.getOrden().getOrdenId());
        }

        Set<OrdenPractica> persistOrdenPracticaList = new HashSet<OrdenPractica>();
        for (OrdenPracticaDTO opdto : practicas) {
            if (opdto.getPracticaId() != null) {
                Practica p = new Practica();
                p.setPracticaId(opdto.getPracticaId());
                OrdenPractica op = new OrdenPractica();
                op.setFecha(new Date());
                op.setOrden(orden);
                op.setPractica(p);

                persistOrdenPracticaList.add(op);
            }
        }
        orden.setOrdenPracticas(persistOrdenPracticaList);

        // Persiste Estado
        if (!orden.getEstado().equals(dto.getEstado())) {
            OrdenWorkflow ow = new OrdenWorkflow(orden, user.getUsername()
                    , dto.getEstado(), new Date());
            orden.getOrdenWorkflows().add(ow);
            orden.setEstado(dto.getEstado());
        }

        ordenManager.edit(orden);
        return "redirect:" + ConstantControllers.MAIN_ORDEN;
    }

    @RequestMapping(value = ConstantControllers.MAIN_ORDEN, method = RequestMethod.GET)
    public String mainOrden(ModelMap map) {
        // map.addAttribute("ordenList", ordenManager.findAll());
        return ConstantRedirect.VIEW_MAIN_ORDEN;
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
        if (p.getPaciente() != null && p.getPaciente().getPacienteId() != null) {
            dto.setPacienteTitular(transformPacienteToDto(pacienteManager
                    .fin1dPacienteById(p.getPaciente().getPacienteId())));
        }

        for (PacienteObrasocial po : p.getPacienteObrasocials()) {
            ObraSocialDTO o = new ObraSocialDTO(po.getObrasocial().getObrasocialId(), po.getObrasocial().getNombre(),
                    po.getNroCredencial(), po.getProvisorio() == 1 ? "checked" : "");
            dto.getObrasocialList().add(o);
            dto.setObrasocial(o);
            dto.setOriginal(po.getProvisorio().intValue() == 1 ? true : false);
            break;
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

            for (PacienteObrasocial poo : ad.getPacienteObrasocials()) {
                dtoad.setCrdencial(poo.getNroCredencial());
                break;
            }

            dto.getAdherentes().add(dtoad);
        }

        return dto;
    }

    public List<Observaciones> getObservacionesFromDto(List<ObservacionesDTO> list) {
        List<Observaciones> retorno = new ArrayList<Observaciones>();
        for (ObservacionesDTO dto : list) {
            Observaciones obs = new Observaciones();
            obs.setFecha(dto.getFecha());
            obs.setObservacion(dto.getObservacion());
            obs.setObservacionId(dto.getObservacionId());
            obs.setOrden(transformDtoToOrden(dto.getOrden()));
            obs.setUserName(dto.getUserName());
            retorno.add(obs);
        }

        return retorno;
    }

    public List<OrdenWorkflow> getOrdenWorkflowFromDto(List<OrdenWorkflowDTO> list) {
        List<OrdenWorkflow> retorno = new ArrayList<OrdenWorkflow>();
        for (OrdenWorkflowDTO dto : list) {
            OrdenWorkflow ow = new OrdenWorkflow();
            ow.setEstado(dto.getEstado());
            ow.setOrden(transformDtoToOrden(dto.getOrden()));
            ow.setOrdenWorkflowId(dto.getOrdenWorkflowId());
            ow.setUserName(dto.getUserName());

            retorno.add(ow);
        }
        return retorno;
    }

    private OrdenDTO transformOrdenToDto(Orden orden) {
        OrdenDTO dto = new OrdenDTO();
        dto.setOrdenId(orden.getOrdenId());
        dto.setFecha(orden.getFecha() + "");
        dto.setEstado(orden.getEstado());

        // Paciente
        dto.setPaciente(transformPacienteToDto(orden.getPaciente()));

        // requisitos
        dto.setReqCredecial(orden.getReqCredecial().intValue() == 1 ? true : false);
        dto.setReqMonotributista(orden.getReqMonotributista().intValue() == 1 ? true : false);
        dto.setReqOrdenMedico(orden.getReqOrdenMedico().intValue() == 1 ? true : false);
        dto.setReqReciboSueldo(orden.getReqReciboSueldo().intValue() == 1 ? true : false);

        // Autorizacion
        dto.setPracticasListEdit(getPracticaDto(orden.getOrdenPracticas()));

        // Observaciones
        dto.setObservacioneses(getObservacionesDto(orden.getObservacioneses()));

        // Flujo de Estados
        dto.setOrdenWorkflows(getOrdenWorkflowToDto(orden.getOrdenWorkflows()));

        if (orden.getEstado().equals(ConstantOrdenEstado.AUTORIZADA)) {
            dto.setEtiqestado(autorizada);
        } else if (orden.getEstado().equals(ConstantOrdenEstado.CERRADA)) {
            dto.setEtiqestado(cerrada);
        } else if (orden.getEstado().equals(ConstantOrdenEstado.EN_OBSERVACION)) {
            dto.setEtiqestado(enobservacion);
        } else if (orden.getEstado().equals(ConstantOrdenEstado.INCOMPLETA)) {
            dto.setEtiqestado(incompleta);
        } else if (orden.getEstado().equals(ConstantOrdenEstado.PENDIENTE)) {
            dto.setEtiqestado(pendiente);
        } else if (orden.getEstado().equals(ConstantOrdenEstado.RECHAZADA)) {
            dto.setEtiqestado(rechazada);
        }

        // boton paciente
        String botonpaciente = "<a class='btn btn-success btn-xs' href='/nuova/formEditPaciente/"
                + dto.getPaciente().getPacienteId() + "' title='" +
                dto.getPaciente().getApellido().toUpperCase() + ", " + dto.getPaciente().getNombre()
                + "'>"
                + "<span class='icon icon-user'></span</a>";
        dto.setBotonpaciente(botonpaciente);

        return dto;
    }

    private List<ObservacionesDTO> getObservacionesDto(Set<Observaciones> list) {
        List<ObservacionesDTO> retorno = new ArrayList<ObservacionesDTO>();
        for (Observaciones o : list) {
            OrdenDTO odto = new OrdenDTO();
            odto.setOrdenId(o.getOrden().getOrdenId());
            ObservacionesDTO dto = new ObservacionesDTO(odto, o.getObservacion(),
                    o.getUserName(), o.getFecha());
            retorno.add(dto);
        }

        Collections.sort(retorno, new Comparator<ObservacionesDTO>() {
            public int compare(ObservacionesDTO a1, ObservacionesDTO a2) {
                return a2.getFecha().compareTo(a1.getFecha());
            }
        });
        return retorno;
    }

    private List<OrdenWorkflowDTO> getOrdenWorkflowToDto(Set<OrdenWorkflow> list) {
        List<OrdenWorkflowDTO> retorno = new ArrayList<OrdenWorkflowDTO>();
        for (OrdenWorkflow o : list) {
            OrdenDTO odto = new OrdenDTO();
            odto.setOrdenId(o.getOrden().getOrdenId());
            OrdenWorkflowDTO dto = new OrdenWorkflowDTO(odto,
                    o.getUserName(), o.getEstado(), o.getFecha());
            retorno.add(dto);
        }

        Collections.sort(retorno, new Comparator<OrdenWorkflowDTO>() {
            public int compare(OrdenWorkflowDTO a1, OrdenWorkflowDTO a2) {
                return a2.getFecha().compareTo(a1.getFecha());
            }
        });

        return retorno;
    }

    private List<OrdenPracticaDTO> getPracticaDto(Set<OrdenPractica> list) {
        List<OrdenPracticaDTO> retorno = new ArrayList<OrdenPracticaDTO>(0);
        for (OrdenPractica op : list) {
            Practica p = op.getPractica();
            p.setNombre("[" + p.getCodigo() + "]-" + p.getNombre());
            Orden o = op.getOrden();
            // PracticaDTO dto = new PracticaDTO(p.getPracticaId(), "[" + p.getCodigo() + "]-" + p.getNombre());
            OrdenPracticaDTO dto = new OrdenPracticaDTO(o.getOrdenId(), p.getNombre(), p.getPracticaId());
            dto.setOrddenPracticaId(op.getOrddenPracticaId());
            retorno.add(dto);
        }

        return retorno;
    }

    public Orden transformDtoToOrden(OrdenDTO dto) {
        Orden orden = new Orden();
        orden.setEstado(dto.getEstado());
        orden.setFecha(Util.parseToDate(dto.getFecha()));
        orden.setOrdenId(dto.getOrdenId());
        orden.setReqCredecial(Util.getByteFlag(dto.isReqCredecial()));
        orden.setReqMonotributista(Util.getByteFlag(dto.isReqMonotributista()));
        orden.setReqOrdenMedico(Util.getByteFlag(dto.isReqOrdenMedico()));
        orden.setReqReciboSueldo(Util.getByteFlag(dto.isReqReciboSueldo()));
        orden.setPaciente(transformDtoToPaciente(dto.getPaciente()));
        orden.setOrdenTipo(transformDtoToOrdenTipo(dto.getOrdenTipo()));

        if (dto.getProfesionalId() != null) {
            Set<OrdenProfesional> ordenProfesionals = new HashSet<OrdenProfesional>();
            Profesional profesional = new Profesional();
            profesional.setProfesionalId(dto.getProfesionalId());
            OrdenProfesional op = new OrdenProfesional(orden, profesional);
            ordenProfesionals.add(op);
            orden.setOrdenProfesionals(ordenProfesionals);
        }

        return orden;
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

    private OrdenTipo transformDtoToOrdenTipo(OrdenTipoDTO dto) {
        OrdenTipo retorno = new OrdenTipo();
        if (dto != null) {
            retorno.setOrdenTipoId(dto.getOrdenTipoId());
            retorno.setNombre(dto.getNombre());
            retorno.setMonto1(dto.getMonto1());
            retorno.setMonto2(dto.getMonto2());
            retorno.setMonto3(dto.getMonto3());
            retorno.setCodigo(dto.getCodigo());
        }
        return retorno;
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
            Especialidad especialidad = especialidadManager.findEspecialidadById(
                    pe.getEspecialidad().getEspecialidadId());

            ProfesionalDTO profesionalDto = new ProfesionalDTO();
            profesionalDto.setProfesionalId(pe.getProfesional().getProfesionalId());
            EspecialidadDTO especialidadDto = new EspecialidadDTO();
            especialidadDto.setId(pe.getEspecialidad().getEspecialidadId());

            dto.getEspecialidadListOld().add(new ProfesionalEspecialidadDTO(pe.getId(),
                    profesionalDto, especialidadDto));
            dto.getEspecialidadListEdit().put(especialidad.getEspecialidadId(),
                    especialidad.getNombre());
        }

        return dto;
    }

    private List<ComboItemDTO> getProfesionalDTOList(List<Profesional> list) {
        List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
        for (Profesional p : list) {
            retorno.add(new ComboItemDTO(p.getProfesionalId() + "", p.getApellido() + ", " + p.getNombre()));
        }
        return retorno;
    }

    private List<Double> getMontosOrden(OrdenTipoDTO dto) {
        List<Double> retorno = new ArrayList<Double>();
        if (!dto.getMonto1().equals(0.00)) {
            retorno.add(dto.getMonto1());
        } else if (!dto.getMonto2().equals(0.00)) {
            retorno.add(dto.getMonto2());
        } else if (!dto.getMonto3().equals(0.00)) {
            retorno.add(dto.getMonto3());
        }

        return retorno;
    }
}
