package com.nuova.model;

// Generated Jan 5, 2016 11:22:38 AM by Hibernate Tools 3.4.0.CR1

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
 * OrdenWorkflow generated by hbm2java
 */
@Entity
@Table(name = "orden_workflow"
        , catalog = "nuova")
public class OrdenWorkflow implements java.io.Serializable {

    private Integer ordenWorkflowId;
    private Orden orden;
    private String userName;
    private String estado;

    public OrdenWorkflow() {
    }

    public OrdenWorkflow(Orden orden, String userName, String estado) {
        this.orden = orden;
        this.userName = userName;
        this.estado = estado;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orden_workflow_id", unique = true, nullable = false)
    public Integer getOrdenWorkflowId() {
        return this.ordenWorkflowId;
    }

    public void setOrdenWorkflowId(Integer ordenWorkflowId) {
        this.ordenWorkflowId = ordenWorkflowId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_id")
    public Orden getOrden() {
        return this.orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    @Column(name = "user_name", length = 164)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "estado", length = 164)
    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
