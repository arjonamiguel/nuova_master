package com.nuova.model;

// Generated Jan 11, 2016 12:33:30 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OrdenPractica generated by hbm2java
 */
@Entity
@Table(name = "orden_practica"
        , catalog = "nuova")
public class OrdenPractica implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8742454053094013763L;
    private Integer orddenPracticaId;
    private Orden orden;
    private Practica practica;
    private Date fecha;

    public OrdenPractica() {
    }

    public OrdenPractica(Orden orden, Practica practica, Date fecha) {
        this.orden = orden;
        this.practica = practica;
        this.fecha = fecha;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordden_practica_id", unique = true, nullable = false)
    public Integer getOrddenPracticaId() {
        return this.orddenPracticaId;
    }

    public void setOrddenPracticaId(Integer orddenPracticaId) {
        this.orddenPracticaId = orddenPracticaId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orden_id")
    public Orden getOrden() {
        return this.orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "practica_id")
    public Practica getPractica() {
        return this.practica;
    }

    public void setPractica(Practica practica) {
        this.practica = practica;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", length = 10)
    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
