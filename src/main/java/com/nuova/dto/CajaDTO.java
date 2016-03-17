package com.nuova.dto;

import java.util.Date;

public class CajaDTO {

    private Integer cajaId;
    private Double ingreso;
    private Double egreso;
    private Date fecha;
    private Integer numeroReferencia;
    private String conceptoDesc;

    // Solo para movimientos en caja
    private Integer tipoMovimiento;
    private Integer concepto;
    private String fechaMovimiento;
    private Double monto;

    public CajaDTO() {
    };

    public CajaDTO(Integer cajaId, Double ingreso, Double egreso, Date fecha, Integer numeroReferencia,
            String conceptoDesc, Integer tipoMovimiento, Integer concepto, String fechaMovimiento, Double monto) {
        super();
        this.cajaId = cajaId;
        this.ingreso = ingreso;
        this.egreso = egreso;
        this.fecha = fecha;
        this.numeroReferencia = numeroReferencia;
        this.conceptoDesc = conceptoDesc;
        this.tipoMovimiento = tipoMovimiento;
        this.concepto = concepto;
        this.fechaMovimiento = fechaMovimiento;
        this.monto = monto;
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

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Integer getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(Integer tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

}
