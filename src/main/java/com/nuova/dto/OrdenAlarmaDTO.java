package com.nuova.dto;

import com.nuova.utils.ConstantOrdenEstado;

public class OrdenAlarmaDTO {
    private Long cantidad = 0L;
    private String descripcion;

    private String iniciada = " <span class='label-success' style='color:white;'>INICIADA</span>";

    private String autorizacion_directa = "<span class='label-important' style='color:white;'>AUTORIZACION DIRECTA</span>";
    private String pendiente_afiliaciones = "<span class='label-warning' style='color:white;'>PENDIENTE AFILIACIONES</span>";
    private String autorizada_por_afiliaciones = " <span class='label-success' style='color:white;'>AUTORIZADA POR AFILIACIONES</span>";
    private String rechazada_por_afiliaciones = "<span class='label-warning' style='color:white;'>RECHAZADA POR AFILIACIONES</span>";
    private String pendiente_auditoria = "<span class='label-warning' style='color:white;'>PENDIENTE AUDITORIA</span>";
    private String autorizada_por_auditoria = "<span class='label-warning' style='color:white;'>AUTORIZADA POR AUDITORIA</span>";
    private String rechazada_por_auditoria = "<span class='label-warning' style='color:white;'>RECHAZADA POR AUDITORIA</span>";
    private String rechazada = "<span class='label-warning' style='color:white;'>RECHAZADA</span>";
    private String anulado = "<span class='label-warning' style='color:white;'>ANULADO</span>";

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
        if (descripcion != null && descripcion.equals(ConstantOrdenEstado.INICIADA)) {
            return iniciada;
        }
        if (descripcion != null && descripcion.equals(ConstantOrdenEstado.ANULADO)) {
            return anulado;
        }
        if (descripcion != null && descripcion.equals(ConstantOrdenEstado.AUTORIZACION_DIRECTA)) {
            return autorizacion_directa;
        }
        if (descripcion != null && descripcion.equals(ConstantOrdenEstado.AUTORIZADA_POR_AFILIACIONES)) {
            return autorizada_por_afiliaciones;
        }
        if (descripcion != null && descripcion.equals(ConstantOrdenEstado.AUTORIZADA_POR_AUDITORIA)) {
            return autorizada_por_auditoria;
        }
        if (descripcion != null && descripcion.equals(ConstantOrdenEstado.PENDIENTE_AFILIACIONES)) {
            return pendiente_afiliaciones;
        }
        if (descripcion != null && descripcion.equals(ConstantOrdenEstado.PENDIENTE_AUDITORIA)) {
            return pendiente_auditoria;
        }
        if (descripcion != null && descripcion.equals(ConstantOrdenEstado.RECHAZADA)) {
            return rechazada;
        }
        if (descripcion != null && descripcion.equals(ConstantOrdenEstado.RECHAZADA_POR_AFILIACIONES)) {
            return rechazada_por_afiliaciones;
        }
        if (descripcion != null && descripcion.equals(ConstantOrdenEstado.RECHAZADA_POR_AUDITORIA)) {
            return rechazada_por_auditoria;
        }

        return "";
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
