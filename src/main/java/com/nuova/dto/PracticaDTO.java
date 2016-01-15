package com.nuova.dto;

public class PracticaDTO {
    private Integer practicaId;
    private String nombre;

    public PracticaDTO(Integer practicaId, String nombre) {
        this.practicaId = practicaId;
        this.nombre = nombre;
    }

    public Integer getPracticaId() {
        return practicaId;
    }

    public void setPracticaId(Integer practicaId) {
        this.practicaId = practicaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
