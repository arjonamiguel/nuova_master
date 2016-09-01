package com.nuova.model;

// Generated Aug 28, 2016 2:45:54 PM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * HistoriaClinicaObservaciones generated by hbm2java
 */
@Entity
@Table(name = "historia_clinica_observaciones"
        , catalog = "nuova")
public class HistoriaClinicaObservaciones implements java.io.Serializable {

    private Integer id;
    private String observacion;
    private Integer historiaClinicaId;
    private Date fecha;

    public HistoriaClinicaObservaciones() {
    }

    public HistoriaClinicaObservaciones(String observacion, Integer historiaClinicaId, Date fecha) {
        this.observacion = observacion;
        this.historiaClinicaId = historiaClinicaId;
        this.fecha = fecha;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "observacion", length = 65535)
    public String getObservacion() {
        return this.observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Column(name = "historia_clinica_id")
    public Integer getHistoriaClinicaId() {
        return this.historiaClinicaId;
    }

    public void setHistoriaClinicaId(Integer historiaClinicaId) {
        this.historiaClinicaId = historiaClinicaId;
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