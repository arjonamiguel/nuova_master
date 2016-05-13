package com.nuova.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrestadorDTO {
  private Integer prestadorId;
  private String nombre;
  private String domicilio;
  private String telefono;

  private String acciones;

  private String provincia;
  private Integer tipo;

  private String especialidad;

  private List<Integer> especialidadList = new ArrayList<Integer>();
  private Map<Integer, String> especialidadListEdit = new HashMap<Integer, String>();

  public PrestadorDTO() {}

  public PrestadorDTO(String nombre, String domicilio, String telefono) {
    this.nombre = nombre;
    this.domicilio = domicilio;
    this.telefono = telefono;
  }

  public Integer getPrestadorId() {
    return prestadorId;
  }

  public void setPrestadorId(Integer prestadorId) {
    this.prestadorId = prestadorId;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDomicilio() {
    return domicilio;
  }

  public void setDomicilio(String domicilio) {
    this.domicilio = domicilio;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getProvincia() {
    return provincia;
  }

  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }

  public String getAcciones() {
    String botonEdit = "<a class='btn btn-info btn-xs' href='/nuova/formEditPrestador/"
        + getPrestadorId() + "'><span class='icon icon-edit'></span>Editar</a>";

    String botonDelete = "<a class='btn btn-danger btn-xs' href='/nuova/formDeletePrestador/"
        + getPrestadorId() + "'><span class='icon icon-remove'></span>Eliminar</a>";

    this.acciones = botonEdit + botonDelete;

    return acciones;
  }

  public void setAcciones(String acciones) {
    this.acciones = acciones;
  }

  public Integer getTipo() {
    return tipo;
  }

  public void setTipo(Integer tipo) {
    this.tipo = tipo;
  }

  public List<Integer> getEspecialidadList() {
    return especialidadList;
  }

  public void setEspecialidadList(List<Integer> especialidadList) {
    this.especialidadList = especialidadList;
  }

  public String getEspecialidad() {
    return especialidad;
  }

  public void setEspecialidad(String especialidad) {
    this.especialidad = especialidad;
  }

  public Map<Integer, String> getEspecialidadListEdit() {
    return especialidadListEdit;
  }

  public void setEspecialidadListEdit(Map<Integer, String> especialidadListEdit) {
    this.especialidadListEdit = especialidadListEdit;
  }


}
