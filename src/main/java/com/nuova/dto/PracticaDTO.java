package com.nuova.dto;

public class PracticaDTO {
    private Integer practicaId;
    private String nombre;
    private String codigo;
    private String tipo;

    private String acciones;

    public PracticaDTO() {

    }

    public PracticaDTO(Integer practicaId, String nombre) {
        this.practicaId = practicaId;
        this.nombre = nombre;
    }

    public Integer getPracticaId() {
        return practicaId;
    }

    public void setPracticaId(Integer practicaId) {
        this.practicaId = practicaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAcciones() {
        String botonEdit = "<a class='btn btn-info btn-xs' href='/nuova/formEditPractica/" + getPracticaId()
                + "'><span class='icon icon-edit'></span>Editar</a>";

        String botonDelete = "<a class='btn btn-danger btn-xs' href='/nuova/formDeletePractica/" + getPracticaId()
                + "'><span class='icon icon-remove'></span>Eliminar</a>";

        this.acciones = botonEdit + botonDelete;

        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
