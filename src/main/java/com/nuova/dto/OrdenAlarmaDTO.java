package com.nuova.dto;

import com.nuova.utils.ConstantOrdenEstado;

public class OrdenAlarmaDTO {
    private Long cantidad = 0L;
    private String descripcion;

    private String rechazada = "<span class='label-important' style='color:white;'>RECHAZADA</span>";
    private String incompleta = "<span class='label-warning' style='color:white;'>INCOMPLETA</span>";
    private String autorizada = " <span class='label-success' style='color:white;'>AUTORIZADA</span>";
    private String pendiente = "<span class='label-warning' style='color:white;'>PENDIETE</span>";
    private String cerrada = "<span class='label-warning' style='color:white;'>CERRADA</span>";
    private String enobservacion = "<span class='label-warning' style='color:white;'>EN OBSERVACION</span>";

    public OrdenAlarmaDTO() {

    }

    public OrdenAlarmaDTO(Long cantidad) {
        this.cantidad = cantidad;
    }

    public OrdenAlarmaDTO(Long cantidad, String descripcion) {
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        if (descripcion.equals(ConstantOrdenEstado.AUTORIZADA)) {
            return autorizada;
        } else if (descripcion.equals(ConstantOrdenEstado.CERRADA)) {
            return cerrada;
        } else if (descripcion.equals(ConstantOrdenEstado.EN_OBSERVACION)) {
            return enobservacion;
        } else if (descripcion.equals(ConstantOrdenEstado.INCOMPLETA)) {
            return incompleta;
        } else if (descripcion.equals(ConstantOrdenEstado.PENDIENTE)) {
            return pendiente;
        } else if (descripcion.equals(ConstantOrdenEstado.RECHAZADA)) {
            return rechazada;
        } else {
            return "...";
        }

    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
