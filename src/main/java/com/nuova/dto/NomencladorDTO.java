package com.nuova.dto;

public class NomencladorDTO {
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

  public NomencladorDTO() {

  }

  public NomencladorDTO(Integer nomencladorId, String codigo, String nombre, String tipo,
      String nomEsp, String nomAyu, String nomAne, String nomGts, String nomCntayu, String nomClave,
      String nomPrecio, String nomGrupo, String estado) {
    this.nomencladorId = nomencladorId;
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

  public Integer getNomencladorId() {
    return nomencladorId;
  }

  public void setNomencladorId(Integer nomencladorId) {
    this.nomencladorId = nomencladorId;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getNomEsp() {
    return nomEsp;
  }

  public void setNomEsp(String nomEsp) {
    this.nomEsp = nomEsp;
  }

  public String getNomAyu() {
    return nomAyu;
  }

  public void setNomAyu(String nomAyu) {
    this.nomAyu = nomAyu;
  }

  public String getNomAne() {
    return nomAne;
  }

  public void setNomAne(String nomAne) {
    this.nomAne = nomAne;
  }

  public String getNomGts() {
    return nomGts;
  }

  public void setNomGts(String nomGts) {
    this.nomGts = nomGts;
  }

  public String getNomCntayu() {
    return nomCntayu;
  }

  public void setNomCntayu(String nomCntayu) {
    this.nomCntayu = nomCntayu;
  }

  public String getNomClave() {
    return nomClave;
  }

  public void setNomClave(String nomClave) {
    this.nomClave = nomClave;
  }

  public String getNomPrecio() {
    return nomPrecio;
  }

  public void setNomPrecio(String nomPrecio) {
    this.nomPrecio = nomPrecio;
  }

  public String getNomGrupo() {
    return nomGrupo;
  }

  public void setNomGrupo(String nomGrupo) {
    this.nomGrupo = nomGrupo;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }


}
