package com.nuova.model;

// Generated Dec 7, 2015 4:39:53 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ProfesionalEspecialidad generated by hbm2java
 */
@Entity
@Table(name = "profesional_especialidad"
        , catalog = "nuova")
public class ProfesionalEspecialidad implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 523798534579190522L;
    private Integer id;
    private Profesional profesional;
    private Especialidad especialidad;

    public ProfesionalEspecialidad() {
    }

    public ProfesionalEspecialidad(Profesional profesional, Especialidad especialidad) {
        this.profesional = profesional;
        this.especialidad = especialidad;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesional_id")
    public Profesional getProfesional() {
        return this.profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidad_id")
    public Especialidad getEspecialidad() {
        return this.especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

}
