package com.nuova.dto;

public class ObraSocialDTO {

    private Integer obrasocialId;
    private String nombre;
    private String credencial;
    private String original = "1";

    private String acciones;

    public ObraSocialDTO() {
    }

    public ObraSocialDTO(Integer obrasocialId, String nombre, String credencial, String original) {
        this.obrasocialId = obrasocialId;
        this.nombre = nombre;
        this.credencial = credencial;
        this.original = original;
    }

    public Integer getObrasocialId() {
        return obrasocialId;
    }

    public void setObrasocialId(Integer obrasocialId) {
        this.obrasocialId = obrasocialId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCredencial() {
        return credencial;
    }

    public void setCredencial(String credencial) {
        this.credencial = credencial;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getAcciones() {
        String botonEdit = "<a class='btn btn-info btn-xs' href='formEditObraSocial/" + getObrasocialId()
                + "'><span class='icon icon-edit'></span>editar</a>&nbsp;";

        String botonDelete = "<a class='btn btn-danger btn-xs' href='formDeleteObraSocial/" + getObrasocialId()
                + "'><span class='icon icon-remove'></span>eliminar</a>";

        this.acciones = botonEdit + botonDelete;

        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

}
