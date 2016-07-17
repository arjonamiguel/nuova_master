package com.nuova.model;

import static javax.persistence.GenerationType.IDENTITY;

// Generated May 12, 2016 5:53:31 PM by Hibernate Tools 4.3.1
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Prestadores generated by hbm2java
 */
@Entity
@Table(name = "prestadores", catalog = "nuova")
public class Prestadores implements java.io.Serializable {

  private Integer prestadorId;
  private String nombre;
  private String domicilio;
  private String telefono;
  private String provincia;
  private Set<PrestadoresEspecialidad> prestadoresEspecialidads =
      new HashSet<PrestadoresEspecialidad>(0);
  private Integer eliminado;

  public Prestadores() {}

  public Prestadores(String nombre, String domicilio, String telefono, String provincia,
      Set<PrestadoresEspecialidad> prestadoresEspecialidads) {
    this.nombre = nombre;
    this.domicilio = domicilio;
    this.telefono = telefono;
    this.provincia = provincia;
    this.prestadoresEspecialidads = prestadoresEspecialidads;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "prestador_id", unique = true, nullable = false)
  public Integer getPrestadorId() {
    return this.prestadorId;
  }

  public void setPrestadorId(Integer prestadorId) {
    this.prestadorId = prestadorId;
  }

  @Column(name = "nombre", length = 256)
  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Column(name = "domicilio", length = 512)
  public String getDomicilio() {
    return this.domicilio;
  }

  public void setDomicilio(String domicilio) {
    this.domicilio = domicilio;
  }

  @Column(name = "telefono", length = 15)
  public String getTelefono() {
    return this.telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  @Column(name = "provincia", length = 256)
  public String getProvincia() {
    return this.provincia;
  }

  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "prestadores")
  public Set<PrestadoresEspecialidad> getPrestadoresEspecialidads() {
    return this.prestadoresEspecialidads;
  }

  public void setPrestadoresEspecialidads(Set<PrestadoresEspecialidad> prestadoresEspecialidads) {
    this.prestadoresEspecialidads = prestadoresEspecialidads;
  }

  @Column(name = "eliminado")
  public Integer getEliminado() {
    return eliminado;
  }

  public void setEliminado(Integer eliminado) {
    this.eliminado = eliminado;
  }

}
