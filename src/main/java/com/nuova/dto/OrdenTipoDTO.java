package com.nuova.dto;

public class OrdenTipoDTO {
    private Integer ordenTipoId;
    private String nombre;
    private Double monto1;
    private Double monto2;
    private Double monto3;
    private Integer codigo;

    private Integer pacienteId;

    public OrdenTipoDTO() {

    }

    public OrdenTipoDTO(String nombre, Double monto1, Double monto2, Double monto3) {
        super();
        this.nombre = nombre;
        this.monto1 = monto1;
        this.monto2 = monto2;
        this.monto3 = monto3;
    }

    public Integer getOrdenTipoId() {
        return ordenTipoId;
    }

    public void setOrdenTipoId(Integer ordenTipoId) {
        this.ordenTipoId = ordenTipoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getMonto1() {
        return monto1;
    }

    public void setMonto1(Double monto1) {
        this.monto1 = monto1;
    }

    public Double getMonto2() {
        return monto2;
    }

    public void setMonto2(Double monto2) {
        this.monto2 = monto2;
    }

    public Double getMonto3() {
        return monto3;
    }

    public void setMonto3(Double monto3) {
        this.monto3 = monto3;
    }

    public Integer getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Integer pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

}
