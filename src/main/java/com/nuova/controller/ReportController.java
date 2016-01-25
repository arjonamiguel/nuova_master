package com.nuova.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.io.ByteSource;
import com.nuova.dto.EspecialidadDTO;
import com.nuova.dto.ObraSocialDTO;
import com.nuova.dto.ObservacionesDTO;
import com.nuova.dto.OrdenDTO;
import com.nuova.dto.OrdenPracticaDTO;
import com.nuova.dto.OrdenWorkflowDTO;
import com.nuova.dto.PacienteDTO;
import com.nuova.dto.ProfesionalDTO;
import com.nuova.dto.ProfesionalEspecialidadDTO;
import com.nuova.model.Especialidad;
import com.nuova.model.Observaciones;
import com.nuova.model.Orden;
import com.nuova.model.OrdenPractica;
import com.nuova.model.OrdenWorkflow;
import com.nuova.model.Paciente;
import com.nuova.model.PacienteObrasocial;
import com.nuova.model.Practica;
import com.nuova.model.Profesional;
import com.nuova.model.ProfesionalEspecialidad;
import com.nuova.service.EspecialidadManager;
import com.nuova.service.OrdenManager;
import com.nuova.service.ProfesionalManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;

@Controller
public class ReportController {
    // TODO : para insertar una imagen
    // InputStream imgStream = getClass().getResourceAsStream(KEY_IMG_PATH);
    // Renderable img = RenderableUtil.getInstance(null).getRenderable(imgStream, OnErrorTypeEnum.BLANK);
    // parameters.put("img", img);

    private static final String FILE_TYPE = ".pdf";
    private static final String ORDEN_EMITIDA_REPORT_JRXML = "reports/reporteOrdenEmitida.jrxml";
    private static final String PROFESIONALES_REPORT_JRXML = "reports/reporteProfesionales.jrxml";
    private static final String ESPECIALIDADES_REPORT_JRXML = "reports/reporteEspecialidades.jrxml";

    @Autowired
    OrdenManager ordenManager;
    @Autowired
    ProfesionalManager profesionalManager;
    @Autowired
    EspecialidadManager especialidadManager;

    // Viewer Reports
    @RequestMapping(value = ConstantControllers.SHOW_REPORT_PROFESIONALES, method = RequestMethod.GET)
    public String showReportProfesionales(ModelMap map) {
        map.addAttribute("titulo", "Reporte de Profesionales registrados en Nuova");
        map.addAttribute("URL_ACTION", ConstantControllers.REPORT_PROFESIONALES);
        return ConstantRedirect.VIEWER_REPORTE;
    }

    @RequestMapping(value = ConstantControllers.SHOW_REPORT_ESPECIALIDADES, method = RequestMethod.GET)
    public String showReportEspecialidades(ModelMap map) {
        map.addAttribute("titulo", "Reporte de Especialidades registradas en Nuova");
        map.addAttribute("URL_ACTION", ConstantControllers.REPORT_ESPECIALIDADES);
        return ConstantRedirect.VIEWER_REPORTE;
    }

    @RequestMapping(value = ConstantControllers.SHOW_REPORT_ORDEN_EMITIDA, method = RequestMethod.GET)
    public String showReportOrdenEmitida(ModelMap map,
            @PathVariable("ordenId") Integer ordenId) {
        map.addAttribute("ordenId", ordenId);
        return ConstantRedirect.VIEWER_REPORTE_ORDEN_EMITIDA;
    }

    // Reports
    @RequestMapping(value = ConstantControllers.REPORT_ORDEN_EMITIDA, method = RequestMethod.GET)
    public String reportOrdenEmitida(ModelMap map,
            @PathVariable("ordenId") Integer ordenId,
            HttpServletResponse response) throws IOException {
        Orden orden = ordenManager.findOrdenById(ordenId);
        Map<String, Object> parameters = new HashMap<String, Object>();
        OrdenDTO dto = transformOrdenToDto(orden);
        parameters.put("nro_orden", dto.getNroOrden());
        parameters.put("dni", dto.getPaciente().getDni() + "");
        parameters.put("apellido", dto.getPaciente().getApellido());
        parameters.put("nombre", dto.getPaciente().getNombre());
        parameters.put("telefono", dto.getPaciente().getTelefono());
        parameters.put("nombre_obrasocial", dto.getPaciente().getObrasocial().getNombre());
        parameters.put("credencial_obrasocial", dto.getPaciente().getObrasocial().getCredencial());

        ByteSource source = ByteSource.wrap(createReport(ORDEN_EMITIDA_REPORT_JRXML, parameters, null));
        source.copyTo(response.getOutputStream());
        response.setContentType("application/pdf");
        response.getOutputStream().write(source.read());
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return ConstantRedirect.VIEWER_REPORTE_ORDEN_EMITIDA;
    }

    @RequestMapping(value = ConstantControllers.REPORT_PROFESIONALES, method = RequestMethod.GET)
    public String reportProfesionales(ModelMap map,
            HttpServletResponse response) throws IOException {
        List<ProfesionalDTO> profesionales = getProfesionalesDto(profesionalManager.findAll());
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(profesionales);

        Map<String, Object> parameters = new HashMap<String, Object>();

        ByteSource source = ByteSource.wrap(createReport(PROFESIONALES_REPORT_JRXML, parameters,
                beanCollectionDataSource));
        source.copyTo(response.getOutputStream());
        response.setContentType("application/pdf");
        response.getOutputStream().write(source.read());
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return ConstantRedirect.VIEWER_REPORTE;
    }

    @RequestMapping(value = ConstantControllers.REPORT_ESPECIALIDADES, method = RequestMethod.GET)
    public String reportEspecialidades(ModelMap map,
            HttpServletResponse response) throws IOException {
        List<EspecialidadDTO> especialidades = getEspecialidadesDto(especialidadManager.findAll());
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(especialidades);

        Map<String, Object> parameters = new HashMap<String, Object>();

        ByteSource source = ByteSource.wrap(createReport(ESPECIALIDADES_REPORT_JRXML, parameters,
                beanCollectionDataSource));
        source.copyTo(response.getOutputStream());
        response.setContentType("application/pdf");
        response.getOutputStream().write(source.read());
        response.getOutputStream().flush();
        response.getOutputStream().close();

        return ConstantRedirect.VIEWER_REPORTE;
    }

    // Config Jasper Report
    private byte[] createReport(String template, Map<String, Object> parameters,
            JRBeanCollectionDataSource beanCollectionDataSource) {
        byte[] pdf = {};
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(template);
            JasperReport report = JasperCompileManager.compileReport(inputStream);

            JasperPrint response = JasperFillManager.fillReport(report, parameters,
                    beanCollectionDataSource == null ? new JREmptyDataSource() : beanCollectionDataSource);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(response, outputStream);
            pdf = outputStream.toByteArray();

        } catch (JRException ex) {
            throw new RuntimeException(ex);
        }
        return pdf;
    }

    // Trasform model to DTO
    // -------------------------------------
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

        // if (orden.getEstado().equals(ConstantOrdenEstado.AUTORIZADA)) {
        // dto.setEtiqestado(autorizada);
        // } else if (orden.getEstado().equals(ConstantOrdenEstado.CERRADA)) {
        // dto.setEtiqestado(cerrada);
        // } else if (orden.getEstado().equals(ConstantOrdenEstado.EN_OBSERVACION)) {
        // dto.setEtiqestado(enobservacion);
        // } else if (orden.getEstado().equals(ConstantOrdenEstado.INCOMPLETA)) {
        // dto.setEtiqestado(incompleta);
        // } else if (orden.getEstado().equals(ConstantOrdenEstado.PENDIENTE)) {
        // dto.setEtiqestado(pendiente);
        // } else if (orden.getEstado().equals(ConstantOrdenEstado.RECHAZADA)) {
        // dto.setEtiqestado(rechazada);
        // }

        return dto;
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
        // if (p.getPaciente() != null && p.getPaciente().getPacienteId() != null) {
        // dto.setPacienteTitular(transformPacienteToDto(pacienteManager
        // .fin1dPacienteById(p.getPaciente().getPacienteId())));
        // }

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

    private List<ProfesionalDTO> getProfesionalesDto(List<Profesional> list) {
        List<ProfesionalDTO> retorno = new ArrayList<ProfesionalDTO>();
        for (Profesional p : list) {
            retorno.add(transformProfesionalToDto(p));
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

    private EspecialidadDTO transformEspecialidadToDTO(Especialidad e) {
        EspecialidadDTO retorno = new EspecialidadDTO();
        retorno.setId(e.getEspecialidadId());
        retorno.setNombre(e.getNombre());
        for (ProfesionalEspecialidad pe : e.getProfesionalEspecialidads()) {
            Profesional p = profesionalManager.findProfesionalById(pe.getProfesional().getProfesionalId());
            retorno.getProfesionales().add(transformProfesionalToDto(p));
        }
        return retorno;
    }

    private List<EspecialidadDTO> getEspecialidadesDto(List<Especialidad> list) {
        List<EspecialidadDTO> retorno = new ArrayList<EspecialidadDTO>();
        for (Especialidad e : list) {
            retorno.add(transformEspecialidadToDTO(e));
        }

        return retorno;
    }

}
