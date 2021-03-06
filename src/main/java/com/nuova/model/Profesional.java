package com.nuova.model;

// Generated Mar 14, 2016 12:41:49 AM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Profesional generated by hbm2java
 */
@Entity
@Table(name = "profesional", catalog = "nuova")
public class Profesional implements java.io.Serializable {

  private Integer profesionalId;
  private String apellido;
  private String nombre;
  private String telefono;
  private String matricula;
  private String registroNacional;
  private String tituloProfesional;
  private Byte habilitacionSiprosa;
  private Date fechaVencimientoHabilitacion;
  private Integer nroRegistro;
  private Date validoHasta;
  private Date fechaEmisionMatricula;
  private Integer nroLibro;
  private Integer nroFolio;
  private String nroPoliza;
  private Date vigenciaDesde;
  private Byte eliminado;
  private Date vigenciaHasta;
  private String tipoMatricula;
  private Set<ProfesionalEspecialidad> profesionalEspecialidads =
      new HashSet<ProfesionalEspecialidad>(0);
  private Set<OrdenProfesional> ordenProfesionals = new HashSet<OrdenProfesional>(0);

  public Profesional() {}

  public Profesional(String apellido, String nombre, String telefono, String matricula,
      String registroNacional, String tituloProfesional, Byte habilitacionSiprosa,
      Date fechaVencimientoHabilitacion, Integer nroRegistro, Date validoHasta,
      Date fechaEmisionMatricula, Integer nroLibro, Integer nroFolio, String nroPoliza,
      Date vigenciaDesde, Byte eliminado, Date vigenciaHasta, String tipoMatricula,
      Set<ProfesionalEspecialidad> profesionalEspecialidads,
      Set<OrdenProfesional> ordenProfesionals) {
    this.apellido = apellido;
    this.nombre = nombre;
    this.telefono = telefono;
    this.matricula = matricula;
    this.registroNacional = registroNacional;
    this.tituloProfesional = tituloProfesional;
    this.habilitacionSiprosa = habilitacionSiprosa;
    this.fechaVencimientoHabilitacion = fechaVencimientoHabilitacion;
    this.nroRegistro = nroRegistro;
    this.validoHasta = validoHasta;
    this.fechaEmisionMatricula = fechaEmisionMatricula;
    this.nroLibro = nroLibro;
    this.nroFolio = nroFolio;
    this.nroPoliza = nroPoliza;
    this.vigenciaDesde = vigenciaDesde;
    this.eliminado = eliminado;
    this.vigenciaHasta = vigenciaHasta;
    this.tipoMatricula = tipoMatricula;
    this.profesionalEspecialidads = profesionalEspecialidads;
    this.ordenProfesionals = ordenProfesionals;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "profesional_id", unique = true, nullable = false)
  public Integer getProfesionalId() {
    return this.profesionalId;
  }

  public void setProfesionalId(Integer profesionalId) {
    this.profesionalId = profesionalId;
  }

  @Column(name = "apellido", length = 256)
  public String getApellido() {
    return this.apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  @Column(name = "nombre", length = 256)
  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Column(name = "telefono", length = 56)
  public String getTelefono() {
    return this.telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  @Column(name = "matricula", length = 256)
  public String getMatricula() {
    return this.matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  @Column(name = "registro_nacional", length = 256)
  public String getRegistroNacional() {
    return this.registroNacional;
  }

  public void setRegistroNacional(String registroNacional) {
    this.registroNacional = registroNacional;
  }

  @Column(name = "titulo_profesional", length = 256)
  public String getTituloProfesional() {
    return this.tituloProfesional;
  }

  public void setTituloProfesional(String tituloProfesional) {
    this.tituloProfesional = tituloProfesional;
  }

  @Column(name = "habilitacion_siprosa")
  public Byte getHabilitacionSiprosa() {
    return this.habilitacionSiprosa;
  }

  public void setHabilitacionSiprosa(Byte habilitacionSiprosa) {
    this.habilitacionSiprosa = habilitacionSiprosa;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "fecha_vencimiento_habilitacion", length = 10)
  public Date getFechaVencimientoHabilitacion() {
    return this.fechaVencimientoHabilitacion;
  }

  public void setFechaVencimientoHabilitacion(Date fechaVencimientoHabilitacion) {
    this.fechaVencimientoHabilitacion = fechaVencimientoHabilitacion;
  }

  @Column(name = "nro_registro")
  public Integer getNroRegistro() {
    return this.nroRegistro;
  }

  public void setNroRegistro(Integer nroRegistro) {
    this.nroRegistro = nroRegistro;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "valido_hasta", length = 10)
  public Date getValidoHasta() {
    return this.validoHasta;
  }

  public void setValidoHasta(Date validoHasta) {
    this.validoHasta = validoHasta;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "fecha_emision_matricula", length = 10)
  public Date getFechaEmisionMatricula() {
    return this.fechaEmisionMatricula;
  }

  public void setFechaEmisionMatricula(Date fechaEmisionMatricula) {
    this.fechaEmisionMatricula = fechaEmisionMatricula;
  }

  @Column(name = "nro_libro")
  public Integer getNroLibro() {
    return this.nroLibro;
  }

  public void setNroLibro(Integer nroLibro) {
    this.nroLibro = nroLibro;
  }

  @Column(name = "nro_folio")
  public Integer getNroFolio() {
    return this.nroFolio;
  }

  public void setNroFolio(Integer nroFolio) {
    this.nroFolio = nroFolio;
  }

  @Column(name = "nro_poliza")
  public String getNroPoliza() {
    return this.nroPoliza;
  }

  public void setNroPoliza(String nroPoliza) {
    this.nroPoliza = nroPoliza;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "vigencia_desde", length = 10)
  public Date getVigenciaDesde() {
    return this.vigenciaDesde;
  }

  public void setVigenciaDesde(Date vigenciaDesde) {
    this.vigenciaDesde = vigenciaDesde;
  }

  @Column(name = "eliminado")
  public Byte getEliminado() {
    return this.eliminado;
  }

  public void setEliminado(Byte eliminado) {
    this.eliminado = eliminado;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "vigencia_hasta", length = 10)
  public Date getVigenciaHasta() {
    return this.vigenciaHasta;
  }

  public void setVigenciaHasta(Date vigenciaHasta) {
    this.vigenciaHasta = vigenciaHasta;
  }

  @Column(name = "tipo_matricula", length = 256)
  public String getTipoMatricula() {
    return this.tipoMatricula;
  }

  public void setTipoMatricula(String tipoMatricula) {
    this.tipoMatricula = tipoMatricula;
  }

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "profesional")
  public Set<ProfesionalEspecialidad> getProfesionalEspecialidads() {
    return this.profesionalEspecialidads;
  }

  public void setProfesionalEspecialidads(Set<ProfesionalEspecialidad> profesionalEspecialidads) {
    this.profesionalEspecialidads = profesionalEspecialidads;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "profesional")
  public Set<OrdenProfesional> getOrdenProfesionals() {
    return this.ordenProfesionals;
  }

  public void setOrdenProfesionals(Set<OrdenProfesional> ordenProfesionals) {
    this.ordenProfesionals = ordenProfesionals;
  }

}
