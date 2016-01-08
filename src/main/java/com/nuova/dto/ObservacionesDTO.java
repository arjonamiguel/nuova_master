package com.nuova.dto;

import java.util.Date;

public class ObservacionesDTO {
    private Integer observacionId;
    private OrdenDTO orden;
    private String observacion;
    private String userName;
    private Date fecha;

    public Integer getObservacionId() {
        return observacionId;
    }

    public void setObservacionId(Integer observacionId) {
        this.observacionId = observacionId;
    }

    public OrdenDTO getOrden() {
        return orden;
    }

    public void setOrden(OrdenDTO orden) {
        this.orden = orden;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
