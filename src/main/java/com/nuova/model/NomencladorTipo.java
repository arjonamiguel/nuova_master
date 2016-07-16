package com.nuova.model;

// Generated Jul 15, 2016 8:37:43 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NomencladorTipo generated by hbm2java
 */
@Entity
@Table(name = "nomenclador_tipo"
        , catalog = "nuova")
public class NomencladorTipo implements java.io.Serializable {

    private Integer id;
    private String nombre;

    public NomencladorTipo() {
    }

    public NomencladorTipo(String nombre) {
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "nombre", length = 512)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
