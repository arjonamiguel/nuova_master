package com.nuova.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nuova.dto.ComboItemDTO;
import com.nuova.dto.ObraSocialDTO;
import com.nuova.dto.ObservacionesDTO;
import com.nuova.dto.OrdenDTO;
import com.nuova.dto.OrdenPracticaDTO;
import com.nuova.dto.OrdenWorkflowDTO;
import com.nuova.dto.PacienteDTO;
import com.nuova.model.Obrasocial;
import com.nuova.model.Observaciones;
import com.nuova.model.Orden;
import com.nuova.model.OrdenPractica;
import com.nuova.model.OrdenWorkflow;
import com.nuova.model.Paciente;
import com.nuova.model.PacienteObrasocial;
import com.nuova.model.Practica;
import com.nuova.service.ObraSocialManager;
import com.nuova.service.ObservacionManager;
import com.nuova.service.OrdenManager;
import com.nuova.service.PacienteManager;
import com.nuova.service.PracticaManager;
import com.nuova.utils.ConstantControllers;
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

    //
    // @RequestMapping(value = ConstantControllers.FORM_DELETE_ORDEN, method = RequestMethod.GET)
    // public String formDeleteObraSocial(ModelMap map,
    // @PathVariable("obrasocialId") Integer obrasocialId) {
    // if (obrasocialId != null) {
    // map.addAttribute("obrasocial", obrasocialManager.findObraSocialById(obrasocialId));
    // }
    //
    // return ConstantRedirect.VIEW_FORM_DELETE_ORDEN;
    // }
    //
    @RequestMapping(value = ConstantControllers.ADD_ORDEN, method = RequestMethod.POST)
    public String addOrden(@ModelAttribute(value = "ordenDto") OrdenDTO ordenDto,
            BindingResult result) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ordenDto.setEstado(Util.getEstadoInicial(ordenDto));
        Orden orden = transformDtoToOrden(ordenDto);
        orden.setFecha(new Date());
        orden.getOrdenWorkflows().add(new OrdenWorkflow(orden, user.getUsername()
                , orden.getEstado(), new Date()));
        if (!ordenDto.getObservacion().trim().equals("")) {
            orden.getObservacioneses().add(
                    new Observaciones(orden, ordenDto.getObservacion(), user.getUsername(), new Date()));
        }
        ordenManager.add(orden);
        return "redirect:" + ConstantControllers.MAIN_ORDEN;
    }

    //
    // return "redirect:" + ConstantControllers.MAIN_ORDEN;
    // }
    //
    // @RequestMapping(value = ConstantControllers.DELETE_ORDEN, method = RequestMethod.POST)
    // public String deleteObraSocial(@ModelAttribute(value = "obrasocial") ObraSocialDTO dto) {
    // obrasocialManager.delete(dto.getObrasocialId());
    // return "redirect:" + ConstantControllers.MAIN_ORDEN;
    // }
    //
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
        map.addAttribute("ordenList", ordenManager.findAll());
        return ConstantRedirect.VIEW_MAIN_ORDEN;
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
        // orden.setObservacioneses(getObservacionesFromDto(dto.getObservacioneses()));
        // orden.setOrdenWorkflows(getOrdenWorkflowFromDto(dto.getOrdenWorkflows()));

        return orden;
    }

    public Paciente transformDtoToPaciente(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setPacienteId(dto.getPacienteId());
        paciente.setDni(dto.getDni());
        paciente.setApellido(dto.getApellido());
        paciente.setNombre(dto.getNombre());
        paciente.setDomicilio(dto.getDomicilio());
        paciente.setFechaNacimiento(Util.parseToDate(dto.getFechaNacimiento()));
        paciente.setLiberado(dto.getLiberado() ? new Byte("1") : new Byte("0"));
        paciente.setMail(dto.getMail());
        paciente.setTelefono(dto.getTelefono());
        paciente.setProvincia(dto.getProvincia());
        paciente.setTitular(dto.isTitular() ? new Byte("1") : new Byte("0"));

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
        dto.setLiberado(p.getLiberado().intValue() == 1 ? true : false);
        dto.setCheckedLiberado(p.getLiberado().intValue() == 1 ? "checked" : "");
        dto.setMail(p.getMail());
        dto.setTelefono(p.getTelefono());
        dto.setProvincia(p.getProvincia());
        if (p.getPaciente() != null && p.getPaciente().getPacienteId() != null) {
            dto.setPacienteTitular(transformPacienteToDto(pacienteManager
                    .fin1dPacienteById(p.getPaciente().getPacienteId())));
        }

        if (p.getTitular() != null) {
            dto.setTitular(p.getTitular().intValue() == 1 ? true : false);
            dto.setCheckedTitular(p.getTitular().intValue() == 1 ? "checked" : "");

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
            dtoad.setLiberado(ad.getLiberado().intValue() == 1 ? true : false);
            dtoad.setCheckedLiberado(p.getLiberado().intValue() == 1 ? "checked" : "");
            dtoad.setMail(ad.getMail());
            dtoad.setTelefono(ad.getTelefono());
            dtoad.setDni(ad.getDni());
            if (ad.getTitular() != null) {
                dtoad.setTitular(ad.getTitular().equals(1) ? true : false);
            }
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

        // Paciente
        dto.setPaciente(transformPacienteToDto(orden.getPaciente()));

        // requisitos
        dto.setReqCredecial(orden.getReqCredecial().intValue() == 1 ? true : false);
        dto.setReqMonotributista(orden.getReqMonotributista().intValue() == 1 ? true : false);
        dto.setReqOrdenMedico(orden.getReqOrdenMedico().intValue() == 1 ? true : false);
        dto.setReqReciboSueldo(orden.getReqReciboSueldo().intValue() == 1 ? true : false);

        // Autorizacion
        dto.setEstado(orden.getEstado());
        dto.setPracticasListEdit(getPracticaDto(orden.getOrdenPracticas()));

        // Observaciones
        dto.setObservacioneses(getObservacionesDto(orden.getObservacioneses()));

        // Flujo de Estados
        dto.setOrdenWorkflows(getOrdenWorkflowToDto(orden.getOrdenWorkflows()));

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

}
