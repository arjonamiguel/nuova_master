package com.nuova.dto;

public class ProfesionalAjaxDto {
  private String apellido;
  private Integer tipo;
  private Integer profesionalId;
  private Integer especialidadId;

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public Integer getTipo() {
    return tipo;
  }

  public void setTipo(Integer tipo) {
    this.tipo = tipo;
  }

  public Integer getProfesionalId() {
    return profesionalId;
  }

  public void setProfesionalId(Integer profesionalId) {
    this.profesionalId = profesionalId;
  }

  public Integer getEspecialidadId() {
    return especialidadId;
  }

  public void setEspecialidadId(Integer especialidadId) {
    this.especialidadId = especialidadId;
  }

}
