package com.nuova.dto;

import java.util.ArrayList;
import java.util.List;

public class OrdenDTO {
    private Integer ordenId;
    private PacienteDTO paciente;
    private String fecha;
    private boolean reqOrdenMedico = false;
    private boolean reqCredecial = false;
    private boolean reqReciboSueldo = false;
    private boolean reqMonotributista = false;
    private List<ObservacionesDTO> observacioneses = new ArrayList<ObservacionesDTO>();
    private List<OrdenWorkflowDTO> ordenWorkflows = new ArrayList<OrdenWorkflowDTO>();
    private String estado;
    private String observacion;

    private String pacienteSelected;
    private String practica;
    private List<OrdenPracticaDTO> practicasListEdit = new ArrayList<OrdenPracticaDTO>();
    private List<OrdenPracticaDTO> ordenpracticaListEdit = new ArrayList<OrdenPracticaDTO>();

    public Integer getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Integer ordenId) {
        this.ordenId = ordenId;
    }

    public PacienteDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isReqOrdenMedico() {
        return reqOrdenMedico;
    }

    public void setReqOrdenMedico(boolean reqOrdenMedico) {
        this.reqOrdenMedico = reqOrdenMedico;
    }

    public boolean isReqCredecial() {
        return reqCredecial;
    }

    public void setReqCredecial(boolean reqCredecial) {
        this.reqCredecial = reqCredecial;
    }

    public boolean isReqReciboSueldo() {
        return reqReciboSueldo;
    }

    public void setReqReciboSueldo(boolean reqReciboSueldo) {
        this.reqReciboSueldo = reqReciboSueldo;
    }

    public List<ObservacionesDTO> getObservacioneses() {
        return observacioneses;
    }

    public void setObservacioneses(List<ObservacionesDTO> observacioneses) {
        this.observacioneses = observacioneses;
    }

    public List<OrdenWorkflowDTO> getOrdenWorkflows() {
        return ordenWorkflows;
    }

    public void setOrdenWorkflows(List<OrdenWorkflowDTO> ordenWorkflows) {
        this.ordenWorkflows = ordenWorkflows;
    }

    public String getPacienteSelected() {
        return pacienteSelected;
    }

    public void setPacienteSelected(String pacienteSelected) {
        this.pacienteSelected = pacienteSelected;
    }

    public boolean isReqMonotributista() {
        return reqMonotributista;
    }

    public void setReqMonotributista(boolean reqMonotributista) {
        this.reqMonotributista = reqMonotributista;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getPractica() {
        return practica;
    }

    public void setPractica(String practica) {
        this.practica = practica;
    }

    public List<OrdenPracticaDTO> getPracticasListEdit() {
        return practicasListEdit;
    }

    public void setPracticasListEdit(List<OrdenPracticaDTO> practicasListEdit) {
        this.practicasListEdit = practicasListEdit;
    }

    public List<OrdenPracticaDTO> getOrdenpracticaListEdit() {
        return ordenpracticaListEdit;
    }

    public void setOrdenpracticaListEdit(List<OrdenPracticaDTO> ordenpracticaListEdit) {
        this.ordenpracticaListEdit = ordenpracticaListEdit;
    }

}
