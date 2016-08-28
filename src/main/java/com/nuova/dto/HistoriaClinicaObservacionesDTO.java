package com.nuova.dto;

public class HistoriaClinicaObservacionesDTO {

  private Integer id;
  private String observacion;
  private Integer historiaClinicaId;
  private String fecha;
  private Integer pacienteId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getObservacion() {
    return observacion;
  }

  public void setObservacion(String observacion) {
    this.observacion = observacion;
  }

  public Integer getHistoriaClinicaId() {
    return historiaClinicaId;
  }

  public void setHistoriaClinicaId(Integer historiaClinicaId) {
    this.historiaClinicaId = historiaClinicaId;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public Integer getPacienteId() {
    return pacienteId;
  }

  public void setPacienteId(Integer pacienteId) {
    this.pacienteId = pacienteId;
  }



}
