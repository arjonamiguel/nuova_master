package com.nuova.dto;

public class PacienteAutocompleteDTO {
    private Integer pacienteId;
    private String dni;
    private String apellido;
    private String nombre;
    private String nroCredencial;

    public PacienteAutocompleteDTO(Integer pacienteId, String dni, String apellido, String nombre, String nroCredencial) {
        super();
        this.pacienteId = pacienteId;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.nroCredencial = nroCredencial;
    }

    public Integer getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Integer pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNroCredencial() {
        return nroCredencial;
    }

    public void setNroCredencial(String nroCredencial) {
        this.nroCredencial = nroCredencial;
    }

}
