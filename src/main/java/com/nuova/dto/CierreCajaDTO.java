package com.nuova.dto;

public class CierreCajaDTO {
    private Integer cajaCierreId;
    private String fechaCierreView;
    private String fechaCierre;
    private Double montoView;
    private Double monto;

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

    public Double getMontoView() {
        return montoView;
    }

    public void setMontoView(Double montoView) {
        this.montoView = montoView;
    }

}
