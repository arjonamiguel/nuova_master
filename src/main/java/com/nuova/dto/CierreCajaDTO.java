package com.nuova.dto;

import java.text.DecimalFormat;

public class CierreCajaDTO {
    private Integer cajaCierreId;
    private String fechaCierreView;
    private String fechaCierre;
    private String montoView;
    private Double monto;

    DecimalFormat format = new DecimalFormat("#.00");

    public CierreCajaDTO() {

    }

    public CierreCajaDTO(Integer cajaCierreId, String fechaCierre, Double monto) {
        super();
        this.cajaCierreId = cajaCierreId;
        this.fechaCierre = fechaCierre;
        this.monto = monto;
    }

    public Integer getCajaCierreId() {
        return cajaCierreId;
    }

    public void setCajaCierreId(Integer cajaCierreId) {
        this.cajaCierreId = cajaCierreId;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getFechaCierreView() {
        return fechaCierreView;
    }

    public void setFechaCierreView(String fechaCierreView) {
        this.fechaCierreView = fechaCierreView;
    }

    public String getMontoView() {
        return "<div style='text-align:right  !important'>" + format.format(monto.doubleValue()) + "</div>";
    }

    public void setMontoView(String montoView) {
        this.montoView = montoView;
    }

}
