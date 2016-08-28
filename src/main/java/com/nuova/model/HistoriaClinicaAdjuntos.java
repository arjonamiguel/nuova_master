package com.nuova.model;

import static javax.persistence.GenerationType.IDENTITY;

// Generated Aug 28, 2016 2:45:54 PM by Hibernate Tools 4.3.1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * HistoriaClinicaAdjuntos generated by hbm2java
 */
@Entity
@Table(name = "historia_clinica_adjuntos", catalog = "nuova")
public class HistoriaClinicaAdjuntos implements java.io.Serializable {

  private Integer id;
  private byte[] adjunto;
  private Integer historiaClinicaId;
  private Date fecha;
  private String nombreArchivo;
  private String fileType;

  public HistoriaClinicaAdjuntos() {}

  public HistoriaClinicaAdjuntos(byte[] adjunto, Integer historiaClinicaId, Date fecha,
      String nombreArchivo) {
    this.adjunto = adjunto;
    this.historiaClinicaId = historiaClinicaId;
    this.fecha = fecha;
    this.nombreArchivo = nombreArchivo;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "adjunto")
  public byte[] getAdjunto() {
    return this.adjunto;
  }

  public void setAdjunto(byte[] adjunto) {
    this.adjunto = adjunto;
  }

  @Column(name = "historia_clinica_id")
  public Integer getHistoriaClinicaId() {
    return this.historiaClinicaId;
  }

  public void setHistoriaClinicaId(Integer historiaClinicaId) {
    this.historiaClinicaId = historiaClinicaId;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "fecha", length = 10)
  public Date getFecha() {
    return this.fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  @Column(name = "nombre_archivo", length = 512)
  public String getNombreArchivo() {
    return this.nombreArchivo;
  }

  public void setNombreArchivo(String nombreArchivo) {
    this.nombreArchivo = nombreArchivo;
  }

  @Column(name = "file_type", length = 64)
  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }



}
