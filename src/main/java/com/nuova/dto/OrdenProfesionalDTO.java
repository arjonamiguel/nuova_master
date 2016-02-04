package com.nuova.dto;

public class OrdenProfesionalDTO {

    private Integer ordenProfesionalId;
    private ProfesionalDTO profesional;

    public OrdenProfesionalDTO() {

    }

    public OrdenProfesionalDTO(Integer ordenProfesionalId, ProfesionalDTO profesional) {
        this.ordenProfesionalId = ordenProfesionalId;
        this.profesional = profesional;
    }

    public Integer getOrdenProfesionalId() {
        return ordenProfesionalId;
    }

    public void setOrdenProfesionalId(Integer ordenProfesionalId) {
        this.ordenProfesionalId = ordenProfesionalId;
    }

    public ProfesionalDTO getProfesional() {
        return profesional;
    }

    public void setProfesional(ProfesionalDTO profesional) {
        this.profesional = profesional;
    }

}
