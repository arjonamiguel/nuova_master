package com.nuova.dto;

public class CierreCajaDTO {
    private Integer cajaCierreId;
    private String fechaCierre;
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

}
