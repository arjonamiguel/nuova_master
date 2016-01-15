package com.nuova.dto;

public class OrdenPracticaDTO {
    private Integer orddenPracticaId;
    private Integer ordenId;
    private String nombre;
    private Integer practicaId;

    public OrdenPracticaDTO() {

    }

    public OrdenPracticaDTO(Integer ordenId, String nombre, Integer practicaId) {
        this.ordenId = ordenId;
        this.nombre = nombre;
        this.practicaId = practicaId;
    }

    public Integer getOrddenPracticaId() {
        return orddenPracticaId;
    }

    public void setOrddenPracticaId(Integer orddenPracticaId) {
        this.orddenPracticaId = orddenPracticaId;
    }

    public Integer getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Integer ordenId) {
        this.ordenId = ordenId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPracticaId() {
        return practicaId;
    }

    public void setPracticaId(Integer practicaId) {
        this.practicaId = practicaId;
    }

}
