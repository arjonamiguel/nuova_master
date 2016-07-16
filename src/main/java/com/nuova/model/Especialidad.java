package com.nuova.model;

// Generated Dec 7, 2015 4:39:53 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Especialidad generated by hbm2java
 */
@Entity
@Table(name = "especialidad"
        , catalog = "nuova")
public class Especialidad implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer especialidadId;
    private String nombre;
    private Set<ProfesionalEspecialidad> profesionalEspecialidads = new HashSet<ProfesionalEspecialidad>(0);
    private Integer tipo;
    private Integer eliminado;

    public Especialidad() {
    }

    public Especialidad(String nombre, Set<ProfesionalEspecialidad> profesionalEspecialidads) {
        this.nombre = nombre;
        this.profesionalEspecialidads = profesionalEspecialidads;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "especialidad_id", unique = true, nullable = false)
    public Integer getEspecialidadId() {
        return this.especialidadId;
    }

    public void setEspecialidadId(Integer especialidadId) {
        this.especialidadId = especialidadId;
    }

    @Column(name = "nombre", length = 256)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "especialidad")
    public Set<ProfesionalEspecialidad> getProfesionalEspecialidads() {
        return this.profesionalEspecialidads;
    }

    public void setProfesionalEspecialidads(Set<ProfesionalEspecialidad> profesionalEspecialidads) {
        this.profesionalEspecialidads = profesionalEspecialidads;
    }

    @Column(name = "tipo")
    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    @Column(name = "eliminado")
    public Integer getEliminado() {
        return eliminado;
    }

    public void setEliminado(Integer eliminado) {
        this.eliminado = eliminado;
    }

}
