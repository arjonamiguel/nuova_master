package com.nuova.dto;

public class OrdenWorkflowDTO {
    private Integer ordenWorkflowId;
    private OrdenDTO orden;
    private String userName;
    private String estado;

    public OrdenWorkflowDTO(OrdenDTO orden, String userName, String estado) {
        this.orden = orden;
        this.userName = userName;
        this.estado = estado;
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

}
