package com.nuova.dto;

import java.math.BigDecimal;

public class OrdenPracticaDTO {
  private Integer orddenPracticaId;
  private Integer ordenId;
  private String nombre;
  private Integer practicaId;
  private String estado;
  private BigDecimal valor;
  private boolean imprimir;
  private String autorizarAutomatico;
  private String piezaDental;
  private Integer cantidad;

  public OrdenPracticaDTO() {

  }

  public OrdenPracticaDTO(Integer orddenPracticaId, Integer ordenId, String nombre,
      Integer practicaId, String estado, String piezaDental) {
    super();
    this.orddenPracticaId = orddenPracticaId;
    this.ordenId = ordenId;
    this.nombre = nombre;
    this.practicaId = practicaId;
    this.estado = estado;
    this.piezaDental = piezaDental;
  }

  public Integer getOrddenPracticaId() {
    return orddenPracticaId;
  }

  public void setOrddenPracticaId(Integer orddenPracticaId) {
    this.orddenPracticaId = orddenPracticaId;
  }

  public Integer getOrdenId() {
    return ordenId;
  }

  public void setOrdenId(Integer ordenId) {
    this.ordenId = ordenId;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Integer getPracticaId() {
    return practicaId;
  }

  public void setPracticaId(Integer practicaId) {
    this.practicaId = practicaId;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public boolean isImprimir() {
    return imprimir;
  }

  public void setImprimir(boolean imprimir) {
    this.imprimir = imprimir;
  }

  public String getAutorizarAutomatico() {
    return autorizarAutomatico;
  }

  public void setAutorizarAutomatico(String autorizarAutomatico) {
    this.autorizarAutomatico = autorizarAutomatico;
  }

  public String getPiezaDental() {
    return piezaDental;
  }

  public void setPiezaDental(String piezaDental) {
    this.piezaDental = piezaDental;
  }

  public Integer getCantidad() {
    return cantidad == null ? 1 : cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad == null ? 1 : cantidad;
  }



}
