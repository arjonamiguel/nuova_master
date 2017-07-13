package com.nuova.dto;

public class InformacionSesiones {
  private Integer validar;
  private Integer disponibles;
  private String nombrePractica;
  private String codigoPractica;
  private Integer cantSesiones;
  private Integer cantSesionesUsadas;
  private Integer validaPor;

  public InformacionSesiones() {

  }

  public InformacionSesiones(Integer validar, Integer disponibles, String nombrePractica,
      String codigoPractica, Integer cantSesiones, Integer cantSesionesUsadas, Integer validaPor) {
    this.validar = validar;
    this.disponibles = disponibles;
    this.nombrePractica = nombrePractica;
    this.codigoPractica = codigoPractica;
    this.cantSesiones = cantSesiones;
    this.cantSesionesUsadas = cantSesionesUsadas;
    this.validaPor = validaPor;
  }

  public Integer getValidar() {
    return validar;
  }

  public void setValidar(Integer validar) {
    this.validar = validar;
  }

  public Integer getDisponibles() {
    return disponibles;
  }

  public void setDisponibles(Integer disponibles) {
    this.disponibles = disponibles;
  }

  public String getNombrePractica() {
    return nombrePractica;
  }

  public void setNombrePractica(String nombrePractica) {
    this.nombrePractica = nombrePractica;
  }

  public String getCodigoPractica() {
    return codigoPractica;
  }

  public void setCodigoPractica(String codigoPractica) {
    this.codigoPractica = codigoPractica;
  }

  public Integer getCantSesiones() {
    return cantSesiones;
  }

  public void setCantSesiones(Integer cantSesiones) {
    this.cantSesiones = cantSesiones;
  }

  public Integer getCantSesionesUsadas() {
    return cantSesionesUsadas;
  }

  public void setCantSesionesUsadas(Integer cantSesionesUsadas) {
    this.cantSesionesUsadas = cantSesionesUsadas;
  }

  public Integer getValidaPor() {
    return validaPor;
  }

  public void setValidaPor(Integer validaPor) {
    this.validaPor = validaPor;
  }

}
