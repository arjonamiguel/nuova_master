package com.nuova.dto;

public class PracticaDTO {
  private Integer nomencladorId;
  private String nombre;
  private String codigo;
  private String tipo;

  private String acciones;
  private Integer cantidadSesion;

  public PracticaDTO() {

  }

  public PracticaDTO(Integer nomencladorId, String nombre) {
    this.nomencladorId = nomencladorId;
    this.nombre = nombre;
  }

  public Integer getNomencladorId() {
    return nomencladorId;
  }

  public void setNomencladorId(Integer nomencladorId) {
    this.nomencladorId = nomencladorId;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getAcciones() {
    String botonEdit = "<a class='btn btn-info btn-xs' href='/nuova/formEditPractica/"
        + getNomencladorId() + "'><span class='icon icon-edit'></span>Editar</a>";

    String botonDelete = "<a class='btn btn-danger btn-xs' href='/nuova/formDeletePractica/"
        + getNomencladorId() + "'><span class='icon icon-remove'></span>Eliminar</a>";

    this.acciones = botonEdit + botonDelete;

    return acciones;
  }

  public void setAcciones(String acciones) {
    this.acciones = acciones;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public Integer getCantidadSesion() {
    return cantidadSesion;
  }

  public void setCantidadSesion(Integer cantidadSesion) {
    this.cantidadSesion = cantidadSesion;
  }



}
