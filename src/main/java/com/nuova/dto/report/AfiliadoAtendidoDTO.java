package com.nuova.dto.report;

import javax.persistence.Entity;

@Entity
public class AfiliadoAtendidoDTO {
  private Integer pacienteId;
  private String dni;
  private String apellido;
  private String nombre;
  private String credencial;
  private String edad;
  private String coseguro;
  private String razCoseguro;

  public AfiliadoAtendidoDTO(Integer pacienteId, String dni, String apellido, String nombre,
      String credencial, String edad, String coseguro, String razCoseguro) {
    super();
    this.pacienteId = pacienteId;
    this.dni = dni;
    this.apellido = apellido;
    this.nombre = nombre;
    this.credencial = credencial;
    this.edad = edad;
    this.coseguro = coseguro;
    this.razCoseguro = razCoseguro;
  }

  public Integer getPacienteId() {
    return pacienteId;
  }

  public void setPacienteId(Integer pacienteId) {
    this.pacienteId = pacienteId;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCredencial() {
    return credencial;
  }

  public void setCredencial(String credencial) {
    this.credencial = credencial;
  }

  public String getEdad() {
    return edad;
  }

  public void setEdad(String edad) {
    this.edad = edad;
  }

  public String getCoseguro() {
    return coseguro;
  }

  public void setCoseguro(String coseguro) {
    this.coseguro = coseguro;
  }

  public String getRazCoseguro() {
    return razCoseguro;
  }

  public void setRazCoseguro(String razCoseguro) {
    this.razCoseguro = razCoseguro;
  }

}
