package com.nuova.model;

// Generated Jan 6, 2016 12:19:29 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Orden generated by hbm2java
 */
@Entity
@Table(name = "orden"
        , catalog = "nuova")
public class Orden implements java.io.Serializable {

    private Integer ordenId;
    private Paciente paciente;
    private Date fecha;
    private Byte reqOrdenMedico;
    private Byte reqCredecial;
    private Byte reqReciboSueldo;
    private Byte reqMonotributista;
    private String estado;
    private Set<Observaciones> observacioneses = new HashSet<Observaciones>(0);
    private Set<OrdenWorkflow> ordenWorkflows = new HashSet<OrdenWorkflow>(0);

    public Orden() {
    }

    public Orden(Paciente paciente, Date fecha, Byte reqOrdenMedico, Byte reqCredecial, Byte reqReciboSueldo,
            Byte reqMonotributista, String estado, Set<Observaciones> observacioneses, Set<OrdenWorkflow> ordenWorkflows) {
        this.paciente = paciente;
        this.fecha = fecha;
        this.reqOrdenMedico = reqOrdenMedico;
        this.reqCredecial = reqCredecial;
        this.reqReciboSueldo = reqReciboSueldo;
        this.reqMonotributista = reqMonotributista;
        this.estado = estado;
        this.observacioneses = observacioneses;
        this.ordenWorkflows = ordenWorkflows;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orden_id", unique = true, nullable = false)
    public Integer getOrdenId() {
        return this.ordenId;
    }

    public void setOrdenId(Integer ordenId) {
        this.ordenId = ordenId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", length = 10)
    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Column(name = "req_orden_medico")
    public Byte getReqOrdenMedico() {
        return this.reqOrdenMedico;
    }

    public void setReqOrdenMedico(Byte reqOrdenMedico) {
        this.reqOrdenMedico = reqOrdenMedico;
    }

    @Column(name = "req_credecial")
    public Byte getReqCredecial() {
        return this.reqCredecial;
    }

    public void setReqCredecial(Byte reqCredecial) {
        this.reqCredecial = reqCredecial;
    }

    @Column(name = "req_recibo_sueldo")
    public Byte getReqReciboSueldo() {
        return this.reqReciboSueldo;
    }

    public void setReqReciboSueldo(Byte reqReciboSueldo) {
        this.reqReciboSueldo = reqReciboSueldo;
    }

    @Column(name = "req_monotributista")
    public Byte getReqMonotributista() {
        return this.reqMonotributista;
    }

    public void setReqMonotributista(Byte reqMonotributista) {
        this.reqMonotributista = reqMonotributista;
    }

    @Column(name = "estado", length = 160)
    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orden", cascade = CascadeType.ALL)
    public Set<Observaciones> getObservacioneses() {
        return this.observacioneses;
    }

    public void setObservacioneses(Set<Observaciones> observacioneses) {
        this.observacioneses = observacioneses;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orden", cascade = CascadeType.ALL)
    public Set<OrdenWorkflow> getOrdenWorkflows() {
        return this.ordenWorkflows;
    }

    public void setOrdenWorkflows(Set<OrdenWorkflow> ordenWorkflows) {
        this.ordenWorkflows = ordenWorkflows;
    }

}
