package com.nuova.dto;

import com.nuova.utils.ConstantOrdenEstado;

public class OrdenAlarmaDTO {
  private Long cantidad = 0L;
  private String descripcion;

  private String iniciada = " <span  style='color:black;background:gold'>INICIADA</span>";
  private String autorizada = "<span style='color:white;background: green'>AUTORIZADA</span>";
  private String pendiente = "<span  style='color:white;background: sienna'>PENDIENTE</span>";
  private String en_progreso =
      " <span style='color:white;background: steelblue'>EN PROGRESO</span>";
  private String rechazada = "<span style='color:white;background: gray'>RECHAZADA</span>";
  private String cerrada = "<span style='color:white;background: black'>CERRADA</span>";

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
    if (descripcion != null && descripcion.equals(ConstantOrdenEstado.ORDEN_INICIADA)) {
      return iniciada;
    }
    if (descripcion != null && descripcion.equals(ConstantOrdenEstado.ORDEN_AUTORIZADA)) {
      return autorizada;
    }
    if (descripcion != null && descripcion.equals(ConstantOrdenEstado.ORDEN_CERRADA)) {
      return cerrada;
    }
    if (descripcion != null && descripcion.equals(ConstantOrdenEstado.ORDEN_EN_PROGRESO)) {
      return en_progreso;
    }
    if (descripcion != null && descripcion.equals(ConstantOrdenEstado.ORDEN_PENDIENTE)) {
      return pendiente;
    }
    if (descripcion != null && descripcion.equals(ConstantOrdenEstado.ORDEN_RECHAZADA)) {
      return rechazada;
    }

    return "";
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

}
