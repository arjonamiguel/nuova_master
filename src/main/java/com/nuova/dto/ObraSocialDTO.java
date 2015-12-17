package com.nuova.dto;

public class ObraSocialDTO {

    private Integer obrasocialId;
    private String nombre;

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

}
