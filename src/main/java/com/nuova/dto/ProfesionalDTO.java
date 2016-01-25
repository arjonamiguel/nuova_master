package com.nuova.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfesionalDTO {

    private Integer profesionalId;
    private String apellido;
    private String nombre;
    private String telefono;
    private String matricula;
    private String registroNacional;
    private String tituloProfesional;
    private String habilitacionSiprosa;
    private String fechaVencimientoHabilitacion;
    private String especialidad;
    private List<Integer> especialidadList = new ArrayList<Integer>();
    private Map<Integer, String> especialidadListEdit = new HashMap<Integer, String>();
    private List<ProfesionalEspecialidadDTO> especialidadListOld = new ArrayList<ProfesionalEspecialidadDTO>();

    private String acciones;

    public Integer getProfesionalId() {
        return profesionalId;
    }

    public void setProfesionalId(Integer profesionalId) {
        this.profesionalId = profesionalId;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getRegistroNacional() {
        return registroNacional;
    }

    public void setRegistroNacional(String registroNacional) {
        this.registroNacional = registroNacional;
    }

    public String getTituloProfesional() {
        return tituloProfesional;
    }

    public void setTituloProfesional(String tituloProfesional) {
        this.tituloProfesional = tituloProfesional;
    }

    public String getHabilitacionSiprosa() {
        return habilitacionSiprosa;
    }

    public void setHabilitacionSiprosa(String habilitacionSiprosa) {
        this.habilitacionSiprosa = habilitacionSiprosa;
    }

    public String getFechaVencimientoHabilitacion() {
        return fechaVencimientoHabilitacion;
    }

    public void setFechaVencimientoHabilitacion(String fechaVencimientoHabilitacion) {
        this.fechaVencimientoHabilitacion = fechaVencimientoHabilitacion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Integer> getEspecialidadList() {
        return especialidadList;
    }

    public void setEspecialidadList(List<Integer> especialidadList) {
        this.especialidadList = especialidadList;
    }

    public Map<Integer, String> getEspecialidadListEdit() {
        return especialidadListEdit;
    }

    public void setEspecialidadListEdit(Map<Integer, String> especialidadListEdit) {
        this.especialidadListEdit = especialidadListEdit;
    }

    public List<ProfesionalEspecialidadDTO> getEspecialidadListOld() {
        return especialidadListOld;
    }

    public void setEspecialidadListOld(List<ProfesionalEspecialidadDTO> especialidadListOld) {
        this.especialidadListOld = especialidadListOld;
    }

    public String getAcciones() {
        String botonEdit = "<a class='btn btn-info btn-xs' href='formEditProfesional/" + getProfesionalId()
                + "'><span class='icon icon-edit'></span>editar</a>&nbsp;";

        String botonDelete = "<a class='btn btn-danger btn-xs' href='formDeleteProfesional/" + getProfesionalId()
                + "'><span class='icon icon-remove'></span>eliminar</a>";

        this.acciones = botonEdit + botonDelete;

        return acciones;

    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

}
