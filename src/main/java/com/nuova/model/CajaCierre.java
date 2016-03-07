package com.nuova.model;

// Generated Mar 5, 2016 3:21:43 PM by Hibernate Tools 4.3.1

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
 * CajaCierre generated by hbm2java
 */
@Entity
@Table(name = "caja_cierre"
        , catalog = "nuova")
public class CajaCierre implements java.io.Serializable {

    private Integer cajaCierreId;
    private Date fechaCierre;
    private Double monto;

    public CajaCierre() {
    }

    public CajaCierre(Date fechaCierre, Double monto) {
        this.fechaCierre = fechaCierre;
        this.monto = monto;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "caja_cierre_id", unique = true, nullable = false)
    public Integer getCajaCierreId() {
        return this.cajaCierreId;
    }

    public void setCajaCierreId(Integer cajaCierreId) {
        this.cajaCierreId = cajaCierreId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_cierre", length = 10)
    public Date getFechaCierre() {
        return this.fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    @Column(name = "monto", precision = 22, scale = 0)
    public Double getMonto() {
        return this.monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

}
