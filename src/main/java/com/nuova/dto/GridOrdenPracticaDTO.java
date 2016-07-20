package com.nuova.dto;

import com.nuova.utils.Util;

import java.util.Date;
import java.util.Formatter;

public class GridOrdenPracticaDTO {
  Integer ordenId;
  String botonpaciente;
  String ordenTipoDesc;
  Date fechaOrden;
  String apellidoNombreProfesional;
  String especialidadView;
  String fecha;
  String nroOrden;
  String acciones;
  Integer ordenTipoCodigo;

  public GridOrdenPracticaDTO() {

  }

  public GridOrdenPracticaDTO(Integer ordenId, String paciente, String ordenTipoDesc,
      Date fechaOrden, String apellidoNombreProfesional, String especialidadView) {
    super();
    this.ordenId = ordenId;
    this.botonpaciente = paciente;
    this.ordenTipoDesc = ordenTipoDesc;
    this.fechaOrden = fechaOrden;
    this.apellidoNombreProfesional = apellidoNombreProfesional;
    this.especialidadView = especialidadView;
  }

  public Integer getOrdenId() {
    return ordenId;
  }

  public void setOrdenId(Integer ordenId) {
    this.ordenId = ordenId;
  }

  public String getBotonpaciente() {
    return botonpaciente;
  }

  public void setBotonpaciente(String botonpaciente) {
    this.botonpaciente = botonpaciente;
  }

  public String getOrdenTipoDesc() {
    return ordenTipoDesc;
  }

  public void setOrdenTipoDesc(String ordenTipoDesc) {
    this.ordenTipoDesc = ordenTipoDesc;
  }

  public Date getFechaOrden() {
    return fechaOrden;
  }

  public void setFechaOrden(Date fechaOrden) {
    this.fechaOrden = fechaOrden;
  }

  public String getApellidoNombreProfesional() {
    return apellidoNombreProfesional;
  }

  public void setApellidoNombreProfesional(String apellidoNombreProfesional) {
    this.apellidoNombreProfesional = apellidoNombreProfesional;
  }

  public String getEspecialidadView() {
    return especialidadView;
  }

  public void setEspecialidadView(String especialidadView) {
    this.especialidadView = especialidadView;
  }

  public String getFecha() {
    return Util.parseToStringDate(fechaOrden);
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public String getNroOrden() {
    Formatter fmt = new Formatter();
    fmt.format("%08d", getOrdenId());
    nroOrden = fmt.toString();
    return nroOrden;
  }

  public void setNroOrden(String nroOrden) {
    this.nroOrden = nroOrden;
  }

  public String getAcciones() {
    String action = "";
    if (getOrdenTipoCodigo().intValue() == 100) {
      action = "formEditConsulta";
    }

    if (getOrdenTipoCodigo().intValue() == 101) {
      action = "formEditOrden";
    }

    if (getOrdenTipoCodigo().intValue() == 102) {
      action = "formEditOrden";
    }

    String botonEdit = "<a class='btn btn-info btn-xs' href='/nuova/" + action + "/" + getOrdenId()
        + " ' target='_blank'><span class='icon icon-edit'></span></a>";

    // String botonDelete = "<a class='btn btn-danger btn-xs' href='/nuova/formDeleteOrden/"
    // + dto.getOrdenId() + "'><span class='icon icon-remove'></span></a>";

    String botonPrint = "";
    if (getOrdenTipoCodigo().intValue() != 100) {
      botonPrint =
          "<a class='btn btn-default btn-xs' data-toggle='modal' data-target='#myModal' onClick='showReport("
              + getOrdenId() + ")'><span class='icon icon-print'></span></a>";
    }
    // String botonPrint = "<button type='button' class='btn btn-info btn-lg' data-toggle='modal'
    // data-target='#myModal'>Open Modal</button>";

    acciones = botonEdit + botonPrint;

    return acciones;
  }

  public void setAcciones(String acciones) {
    this.acciones = acciones;
  }

  public Integer getOrdenTipoCodigo() {
    return ordenTipoCodigo;
  }

  public void setOrdenTipoCodigo(Integer ordenTipoCodigo) {
    this.ordenTipoCodigo = ordenTipoCodigo;
  }



}
