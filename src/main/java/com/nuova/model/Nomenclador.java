package com.nuova.model;

// Generated Mar 16, 2016 12:05:57 PM by Hibernate Tools 4.3.1

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Nomenclador generated by hbm2java
 */
@Entity
@Table(name = "nomenclador", catalog = "nuova")
public class Nomenclador implements java.io.Serializable {

  private Integer nomencladorId;
  private String codigo;
  private String nombre;
  private String tipo;
  private String nomEsp;
  private String nomAyu;
  private String nomAne;
  private String nomGts;
  private String nomCntayu;
  private String nomClave;
  private String nomPrecio;
  private String nomGrupo;
  private String estado;
  private Integer eliminado;

  public Nomenclador() {}

  public Nomenclador(String codigo, String nombre, String tipo, String nomEsp, String nomAyu,
      String nomAne, String nomGts, String nomCntayu, String nomClave, String nomPrecio,
      String nomGrupo, String estado) {
    this.codigo = codigo;
    this.nombre = nombre;
    this.tipo = tipo;
    this.nomEsp = nomEsp;
    this.nomAyu = nomAyu;
    this.nomAne = nomAne;
    this.nomGts = nomGts;
    this.nomCntayu = nomCntayu;
    this.nomClave = nomClave;
    this.nomPrecio = nomPrecio;
    this.nomGrupo = nomGrupo;
    this.estado = estado;
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "nomenclador_id", unique = true, nullable = false)
  public Integer getNomencladorId() {
    return this.nomencladorId;
  }

  public void setNomencladorId(Integer nomencladorId) {
    this.nomencladorId = nomencladorId;
  }

  @Column(name = "codigo", length = 50)
  public String getCodigo() {
    return this.codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  @Column(name = "nombre", length = 164)
  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Column(name = "tipo", length = 164)
  public String getTipo() {
    return this.tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  @Column(name = "nom_esp", length = 164)
  public String getNomEsp() {
    return this.nomEsp;
  }

  public void setNomEsp(String nomEsp) {
    this.nomEsp = nomEsp;
  }

  @Column(name = "nom_ayu", length = 164)
  public String getNomAyu() {
    return this.nomAyu;
  }

  public void setNomAyu(String nomAyu) {
    this.nomAyu = nomAyu;
  }

  @Column(name = "nom_ane", length = 164)
  public String getNomAne() {
    return this.nomAne;
  }

  public void setNomAne(String nomAne) {
    this.nomAne = nomAne;
  }

  @Column(name = "nom_gts", length = 164)
  public String getNomGts() {
    return this.nomGts;
  }

  public void setNomGts(String nomGts) {
    this.nomGts = nomGts;
  }

  @Column(name = "nom_cntayu", length = 164)
  public String getNomCntayu() {
    return this.nomCntayu;
  }

  public void setNomCntayu(String nomCntayu) {
    this.nomCntayu = nomCntayu;
  }

  @Column(name = "nom_clave", length = 164)
  public String getNomClave() {
    return this.nomClave;
  }

  public void setNomClave(String nomClave) {
    this.nomClave = nomClave;
  }

  @Column(name = "nom_precio", length = 164)
  public String getNomPrecio() {
    return this.nomPrecio;
  }

  public void setNomPrecio(String nomPrecio) {
    this.nomPrecio = nomPrecio;
  }

  @Column(name = "nom_grupo", length = 164)
  public String getNomGrupo() {
    return this.nomGrupo;
  }

  public void setNomGrupo(String nomGrupo) {
    this.nomGrupo = nomGrupo;
  }

  @Column(name = "estado", length = 164)
  public String getEstado() {
    return this.estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  @Column(name = "eliminado")
  public Integer getEliminado() {
    return eliminado;
  }

  public void setEliminado(Integer eliminado) {
    this.eliminado = eliminado;
  }

}
