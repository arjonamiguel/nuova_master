package com.nuova.dto;

import java.util.Date;

public class PracticasListDTO {
  private String codigo;
  private String nombre;
  private String estado;
  private Date autorizarAutomatico;
  private String fechaFormateada;

  public PracticasListDTO() {

  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }



  public Date getAutorizarAutomatico() {
    return autorizarAutomatico;
  }

  public void setAutorizarAutomatico(Date autorizarAutomatico) {
    this.autorizarAutomatico = autorizarAutomatico;
  }

  public String getFechaFormateada() {
    return fechaFormateada;
  }

  public void setFechaFormateada(String fechaFormateada) {
    this.fechaFormateada = fechaFormateada;
  }


}
