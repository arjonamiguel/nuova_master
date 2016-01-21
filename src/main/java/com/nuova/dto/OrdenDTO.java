package com.nuova.dto;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class OrdenDTO {
    private Integer ordenId;
    private String nroOrden;
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

    // usados en el paginador
    private String botonpaciente;
    private String iconordenmedica;
    private String iconcredencial;
    private String iconmonotributista;
    private String iconrecibo;
    private String etiqestado;
    private String acciones;

    // render HTML
    private String iconOk = "<span class='icon-ok-sign'></span>";
    private String iconNotOk = "<span class='icon-remove-sign'></span>";

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

    public String getBotonpaciente() {
        botonpaciente = "<a class='btn btn-success btn-xs' href='formViewPaciente/'" + getOrdenId() + ">"
                + "<span class='icon icon-user'></span>Ver...</a>";
        return botonpaciente;
    }

    public void setBotonpaciente(String botonpaciente) {
        this.botonpaciente = botonpaciente;
    }

    public String getIconordenmedica() {
        iconordenmedica = reqOrdenMedico == true ? iconOk : iconNotOk;
        return iconordenmedica;
    }

    public void setIconordenmedica(String iconordenmedica) {
        this.iconordenmedica = iconordenmedica;
    }

    public String getIconcredencial() {
        iconcredencial = reqCredecial == true ? iconOk : iconNotOk;
        return iconcredencial;
    }

    public void setIconcredencial(String iconcredencial) {
        this.iconcredencial = iconcredencial;
    }

    public String getIconmonotributista() {
        iconmonotributista = reqMonotributista == true ? iconOk : iconNotOk;
        return iconmonotributista;
    }

    public void setIconmonotributista(String iconmonotributista) {
        this.iconmonotributista = iconmonotributista;
    }

    public String getIconrecibo() {
        iconrecibo = reqReciboSueldo == true ? iconOk : iconNotOk;
        return iconrecibo;
    }

    public void setIconrecibo(String iconrecibo) {
        this.iconrecibo = iconrecibo;
    }

    public String getEtiqestado() {
        return etiqestado;
    }

    public void setEtiqestado(String etiqestado) {
        this.etiqestado = etiqestado;
    }

    public String getAcciones() {
        String botonEdit = "<a class='btn btn-info btn-xs' href='formEditOrden/" + getOrdenId()
                + "'><span class='icon icon-edit'></span>editar</a>&nbsp;";

        String botonDelete = "<a class='btn btn-danger btn-xs' href='formDeleteOrden/" + getOrdenId()
                + "'><span class='icon icon-remove'></span>eliminar</a>";

        this.acciones = botonEdit + botonDelete;

        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    public String getNroOrden() {
        Formatter fmt = new Formatter();
        fmt.format("%08d", getOrdenId());
        nroOrden = fmt.toString();
        return nroOrden;
    }

    public void setNroOrden(String nroOrden) {
        this.nroOrden = nroOrden;
    }

}
