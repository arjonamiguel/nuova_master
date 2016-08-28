package com.nuova.dto;

import java.util.Date;

public class HistoriaClinicaDTO {
  private Integer hcId;
  private Date fecha;
  private String fechaFormateada;
  private String ordenes;
  private String observaciones;
  private String adjuntos;

  public HistoriaClinicaDTO() {

  }

  public HistoriaClinicaDTO(Integer hcId, Date fecha, String ordenes, String observaciones,
      String adjuntos) {
    super();
    this.hcId = hcId;
    this.fecha = fecha;
    this.ordenes = ordenes;
    this.observaciones = observaciones;
    this.adjuntos = adjuntos;
  }

  public Integer getHcId() {
    return hcId;
  }

  public void setHcId(Integer hcId) {
    this.hcId = hcId;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public String getOrdenes() {
    return ordenes;
  }

  public void setOrdenes(String ordenes) {
    this.ordenes = ordenes;
  }

  public String getObservaciones() {
    return observaciones;
  }

  public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
  }

  public String getAdjuntos() {
    return adjuntos;
  }

  public void setAdjuntos(String adjuntos) {
    this.adjuntos = adjuntos;
  }



}
