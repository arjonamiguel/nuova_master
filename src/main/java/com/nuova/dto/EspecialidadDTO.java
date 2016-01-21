package com.nuova.dto;

public class EspecialidadDTO {
    private Integer id;
    private String nombre;

    private String acciones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAcciones() {
        String botonEdit = "<a class='btn btn-info btn-xs' href='formEditEspecialidad/" + getId()
                + "'><span class='icon icon-edit'></span>editar</a>&nbsp;";

        String botonDelete = "<a class='btn btn-danger btn-xs' href='formDeleteEspecialidad/" + getId()
                + "'><span class='icon icon-remove'></span>eliminar</a>";

        this.acciones = botonEdit + botonDelete;

        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

}
