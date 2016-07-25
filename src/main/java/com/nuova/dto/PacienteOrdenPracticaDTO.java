package com.nuova.dto;

import java.util.Formatter;

public class PacienteOrdenPracticaDTO {
    private Integer id;
    private String nroOrden;
    private String fecha;
    private String solicitante;
    private String especialidad;
    private String practica;
    private String estado;
    private String acciones;
    private String fueraCartilla;

    public PacienteOrdenPracticaDTO() {

    }

    public String getNroOrden() {
        Formatter fmt = new Formatter();
        fmt.format("%08d", getId());
        nroOrden = fmt.toString();
        return nroOrden;
    }

    public void setNroOrden(String nroOrden) {
        this.nroOrden = nroOrden;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getPractica() {
        return practica;
    }

    public void setPractica(String practica) {
        this.practica = practica;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAcciones() {
        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getFueraCartilla() {
		return fueraCartilla;
	}

	public void setFueraCartilla(Integer fueraCartilla) {
		//this.fueraCartilla = fueraCartilla;
		if(fueraCartilla==1){
			this.fueraCartilla="SI";
		}else{
			this.fueraCartilla= "NO";
		}
	}
    
    

}
