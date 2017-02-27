package com.nuova.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Formatter;

import com.nuova.utils.Util;

public class CajaDTO {

    private Integer cajaId;
    private BigDecimal ingreso;
    private BigDecimal egreso;
    private Date fecha;
    private Integer numeroReferencia;
    private String conceptoDesc;

    // Solo para movimientos en caja
    private Integer tipoMovimiento;
    private Integer concepto;
    private String fechaMovimiento;
    private Double monto;
    private Integer ordenTipoId;
    private Integer ordenId;

    public CajaDTO() {

    };

    public CajaDTO(Integer cajaId, BigDecimal ingreso, BigDecimal egreso, Date fecha,
            Integer numeroReferencia, String conceptoDesc, Integer tipoMovimiento, Integer concepto,
            String fechaMovimiento, Double monto) {
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

    public BigDecimal getIngreso() {
        return ingreso;
    }

    public void setIngreso(BigDecimal ingreso) {
        this.ingreso = ingreso;
    }

    public BigDecimal getEgreso() {
        return egreso;
    }

    public void setEgreso(BigDecimal egreso) {
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
        Formatter fmt = new Formatter();
        fmt.format("%08d", ordenId);

        String formRedirect = "";
        if (ordenTipoId != null) {
            if (ordenTipoId == 1) {
                formRedirect = "/nuova/formEditConsulta/";
            } else if (ordenTipoId == 3) {
                formRedirect = "/nuova/formEditOrden/";
            }
        }

        if (!formRedirect.isEmpty()) {
            this.setConceptoDesc(
                    Util.CAJA_CONCEPTOS.get(concepto) + " Nro. <a target='_blank' href='"
                            + formRedirect + ordenId + "'>" + fmt.toString() + " </a>");
        } else {
            this.setConceptoDesc(Util.CAJA_CONCEPTOS.get(concepto));
        }
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

    public Integer getOrdenTipoId() {
        return ordenTipoId;
    }

    public void setOrdenTipoId(Integer ordenTipoId) {
        this.ordenTipoId = ordenTipoId;
    }

    public Integer getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Integer ordenId) {
        this.ordenId = ordenId;
    }



}
