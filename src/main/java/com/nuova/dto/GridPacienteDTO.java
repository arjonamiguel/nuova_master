package com.nuova.dto;

public class GridPacienteDTO {
  private Integer pacienteId;
  private String dni;
  private String apellido;
  private String nombre;
  private String eliminadoView;
  private String acciones;
  private Byte eliminado;

  public GridPacienteDTO() {
    super();
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

  public String getEliminadoView() {
    return eliminadoView;
  }

  public void setEliminadoView(String eliminadoView) {
    this.eliminadoView = eliminadoView;
  }

  public String getAcciones() {
    String botonEdit = "<a class='btn btn-info btn-xs' href='formEditPaciente/" + getPacienteId()
        + "'><span class='icon icon-edit'></span>Editar</a>&nbsp;";

    String botonTipoOrden = "<a class='btn btn-success btn-xs' href='tipoOrden/" + getPacienteId()
        + "'><span class='icon icon-plus-sign'></span>Orden</a>";

    String botonDelete = "<a class='btn btn-danger btn-xs' href='formDeletePaciente/"
        + getPacienteId() + "'><span class='icon icon-remove'></span>Desactivar</a>&nbsp;";

    String botonActivar = "<a class='btn btn-success btn-xs' href='#' onClick='activarPaciente("
        + getPacienteId()
        + ")'><span class='icon icon-ok'></span>Activar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>&nbsp;";

    this.acciones = botonEdit + (getEliminado().intValue() == 0 ? botonDelete : botonActivar);

    return acciones;
  }

  public void setAcciones(String acciones) {
    this.acciones = acciones;
  }

  public Byte getEliminado() {
    return eliminado;
  }

  public void setEliminado(Byte eliminado) {
    this.eliminado = eliminado;
  }



}
