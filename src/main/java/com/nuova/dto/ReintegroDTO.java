package com.nuova.dto;

import java.math.BigDecimal;

public class ReintegroDTO {

  private Integer reintegroId;
  private Integer pacienteId;
  private String fechaDesde;
  private String fechaReintegro;
  private String profesional;
  private String observaciones;
  private BigDecimal monto;

  private String estado;
  private String estadoView;
  private String acciones;

  private String apellidoNombrePaciente;

  public Integer getReintegroId() {
    return reintegroId;
  }

  public void setReintegroId(Integer reintegroId) {
    this.reintegroId = reintegroId;
  }

  public Integer getPacienteId() {
    return pacienteId;
  }

  public void setPacienteId(Integer pacienteId) {
    this.pacienteId = pacienteId;
  }

  public String getFechaDesde() {
    return fechaDesde;
  }

  public void setFechaDesde(String fechaDesde) {
    this.fechaDesde = fechaDesde;
  }

  public String getFechaReintegro() {
    return fechaReintegro;
  }

  public void setFechaReintegro(String fechaReintegro) {
    this.fechaReintegro = fechaReintegro;
  }

  public String getProfesional() {
    return profesional;
  }

  public void setProfesional(String profesional) {
    this.profesional = profesional;
  }

  public String getObservaciones() {
	return observaciones;
  }

  public void setObservaciones(String observaciones) {
	this.observaciones = observaciones;
  }

  public BigDecimal getMonto() {
    return monto;
  }

  public void setMonto(BigDecimal monto) {
    this.monto = monto;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getAcciones() {
    return acciones;
  }

  public void setAcciones(String acciones) {
    this.acciones = acciones;
  }

  public String getApellidoNombrePaciente() {
    return apellidoNombrePaciente;
  }

  public void setApellidoNombrePaciente(String apellidoNombrePaciente) {
    this.apellidoNombrePaciente = apellidoNombrePaciente;
  }

  public String getEstadoView() {
    return estadoView;
  }

  public void setEstadoView(String estadoView) {
    this.estadoView = estadoView;
  }


}
