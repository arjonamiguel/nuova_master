package com.nuova.dto;

import java.util.Date;

public class OrdenWorkflowDTO {
    private Integer ordenWorkflowId;
    private OrdenDTO orden;
    private String userName;
    private String estado;
    private Date fecha;

    public OrdenWorkflowDTO(OrdenDTO orden, String userName, String estado, Date fecha) {
        this.orden = orden;
        this.userName = userName;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Integer getOrdenWorkflowId() {
        return ordenWorkflowId;
    }

    public void setOrdenWorkflowId(Integer ordenWorkflowId) {
        this.ordenWorkflowId = ordenWorkflowId;
    }

    public OrdenDTO getOrden() {
        return orden;
    }

    public void setOrden(OrdenDTO orden) {
        this.orden = orden;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
