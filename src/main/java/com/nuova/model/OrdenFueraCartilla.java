package com.nuova.model;

// Generated May 6, 2016 4:31:37 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OrdenFueraCartilla generated by hbm2java
 */
@Entity
@Table(name = "orden_fuera_cartilla"
        , catalog = "nuova")
public class OrdenFueraCartilla implements java.io.Serializable {

    private Integer id;
    private int ordenId;
    private String entidad;
    private String observacion;

    public OrdenFueraCartilla() {
    }

    public OrdenFueraCartilla(int ordenId) {
        this.ordenId = ordenId;
    }

    public OrdenFueraCartilla(int ordenId, String entidad, String observacion) {
        this.ordenId = ordenId;
        this.entidad = entidad;
        this.observacion = observacion;
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

    @Column(name = "orden_id", nullable = false)
    public int getOrdenId() {
        return this.ordenId;
    }

    public void setOrdenId(int ordenId) {
        this.ordenId = ordenId;
    }

    @Column(name = "entidad", length = 512)
    public String getEntidad() {
        return this.entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    @Column(name = "observacion", length = 512)
    public String getObservacion() {
        return this.observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

}
