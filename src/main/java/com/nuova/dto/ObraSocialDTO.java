package com.nuova.dto;

public class ObraSocialDTO {

    private Integer obrasocialId;
    private String nombre;
    private String credencial;
    private String original = "1";

    public ObraSocialDTO() {
    }

    public ObraSocialDTO(Integer obrasocialId, String nombre) {
        this.obrasocialId = obrasocialId;
        this.nombre = nombre;
    }

    public Integer getObrasocialId() {
        return obrasocialId;
    }

    public void setObrasocialId(Integer obrasocialId) {
        this.obrasocialId = obrasocialId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCredencial() {
        return credencial;
    }

    public void setCredencial(String credencial) {
        this.credencial = credencial;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

}
