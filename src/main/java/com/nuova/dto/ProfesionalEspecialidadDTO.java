package com.nuova.dto;

public class ProfesionalEspecialidadDTO {
    private Integer id;
    private ProfesionalDTO profesionalDto;
    private EspecialidadDTO especialidadDto;

    public ProfesionalEspecialidadDTO(Integer id, ProfesionalDTO profesionalDto, EspecialidadDTO especialidadDto) {
        this.id = id;
        this.profesionalDto = profesionalDto;
        this.especialidadDto = especialidadDto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProfesionalDTO getProfesionalDto() {
        return profesionalDto;
    }

    public void setProfesionalDto(ProfesionalDTO profesionalDto) {
        this.profesionalDto = profesionalDto;
    }

    public EspecialidadDTO getEspecialidadDto() {
        return especialidadDto;
    }

    public void setEspecialidadDto(EspecialidadDTO especialidadDto) {
        this.especialidadDto = especialidadDto;
    }

}
