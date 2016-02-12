package com.nuova.dto;

import java.util.Date;

public class CajaDTO {

    private Integer cajaId;
    private Integer concepto;
    private Double ingreso;
    private Double egreso;
    private Date fecha;
    private Integer numeroReferencia;
    private String conceptoDesc;

    public CajaDTO() {
    };

    public CajaDTO(Integer concepto, Double ingreso, Double egreso, Date fecha, Integer numeroReferencia) {
        super();
        this.concepto = concepto;
        this.ingreso = ingreso;
        this.egreso = egreso;
        this.fecha = fecha;
        this.numeroReferencia = numeroReferencia;
    }

    public Integer getCajaId() {
        return cajaId;
    }

    public void setCajaId(Integer cajaId) {
        this.cajaId = cajaId;
    }

    public Integer getConcepto() {
        return concepto;
    }

    public void setConcepto(Integer concepto) {
        this.concepto = concepto;
    }

    public Double getIngreso() {
        return ingreso;
    }

    public void setIngreso(Double ingreso) {
        this.ingreso = ingreso;
    }

    public Double getEgreso() {
        return egreso;
    }

    public void setEgreso(Double egreso) {
        this.egreso = egreso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(Integer numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public String getConceptoDesc() {
        return conceptoDesc;
    }

    public void setConceptoDesc(String conceptoDesc) {
        this.conceptoDesc = conceptoDesc;
    }

}
