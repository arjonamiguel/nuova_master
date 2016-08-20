package com.nuova.dto;

import com.nuova.utils.Util;

import java.util.Date;
import java.util.List;

public class PacienteInfoDTO {

  private Integer pacienteId;
  private Integer titularId;
  private String dni;
  private String apellido;
  private String nombre;
  private Date fechaNacimiento;
  private String domicilio = "";
  private String observaciones = "";
  private String telefono;
  private Byte eliminado;
  private String telfono;
  private String credencial;
  private String obrasocial;
  private String fechaNacimientoFormateada;
  private Byte parentesco;
  private int parentescoVO;
  private String parentescoDescripcion;
  private List<PacienteDTO> adherentes;
  private String infoTitular;

  public PacienteInfoDTO() {
    // TODO Auto-generated constructor stub
  }

  public Integer getPacienteId() {
    return pacienteId;
  }

  public void setPacienteId(Integer pacienteId) {
    this.pacienteId = pacienteId;
  }

  public Integer getTitularId() {
    return titularId;
  }

  public void setTitularId(Integer titularId) {
    this.titularId = titularId;
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

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getDomicilio() {
    return domicilio;
  }

  public void setDomicilio(String domicilio) {
    this.domicilio = domicilio;
  }

  public String getObservaciones() {
    return observaciones;
  }

  public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public Integer getEliminado() {
    return eliminado.intValue();
  }

  public void setEliminado(Byte eliminado) {
    this.eliminado = eliminado;
  }

  public String getTelfono() {
    return telfono;
  }

  public void setTelfono(String telfono) {
    this.telfono = telfono;
  }

  public String getCredencial() {
    return credencial;
  }

  public void setCredencial(String credencial) {
    this.credencial = credencial;
  }

  public String getObrasocial() {
    return obrasocial;
  }

  public void setObrasocial(String obrasocial) {
    this.obrasocial = obrasocial;
  }

  public String getFechaNacimientoFormateada() {
    return fechaNacimientoFormateada;
  }

  public void setFechaNacimientoFormateada(String fechaNacimientoFormateada) {
    this.fechaNacimientoFormateada = fechaNacimientoFormateada;
  }

  public Byte getParentesco() {
    return parentesco;
  }

  public void setParentesco(Byte parentesco) {
    this.parentesco = parentesco;
    setParentescoVO(getParentesco().intValue());
    for (ComboItemDTO item : Util.getParentescos()) {
      if (getParentescoVO() == Integer.valueOf(item.getId()).intValue())
        setParentescoDescripcion(item.getValue());
    }
  }

  public int getParentescoVO() {
    return getParentesco().intValue();
  }

  public void setParentescoVO(int parentescoVO) {
    this.parentescoVO = parentescoVO;
  }

  public String getParentescoDescripcion() {
    for (ComboItemDTO item : Util.getParentescos()) {
      if (getParentesco().intValue() == Integer.valueOf(item.getId()).intValue())
        setParentescoDescripcion(item.getValue());
    }

    return parentescoDescripcion;
  }

  public void setParentescoDescripcion(String parentescoDescripcion) {
    this.parentescoDescripcion = parentescoDescripcion;
  }

  public List<PacienteDTO> getAdherentes() {
    return adherentes;
  }

  public void setAdherentes(List<PacienteDTO> adherentes) {
    this.adherentes = adherentes;
  }

  public String getInfoTitular() {
    return infoTitular;
  }

  public void setInfoTitular(String infoTitular) {
    this.infoTitular = infoTitular;
  }


}
