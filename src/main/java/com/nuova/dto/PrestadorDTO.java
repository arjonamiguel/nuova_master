package com.nuova.dto;

public class PrestadorDTO {
    private Integer prestadorId;
    private String nombre;
    private String domicilio;
    private String telefono;

    private String acciones;

    private String provincia;

    public PrestadorDTO() {
    }

    public PrestadorDTO(String nombre, String domicilio, String telefono) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }

    public Integer getPrestadorId() {
        return prestadorId;
    }

    public void setPrestadorId(Integer prestadorId) {
        this.prestadorId = prestadorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getAcciones() {
        String botonEdit = "<a class='btn btn-info btn-xs' href='/nuova/formEditPrestador/" + getPrestadorId()
                + "'><span class='icon icon-edit'></span>Editar</a>";

        String botonDelete = "<a class='btn btn-danger btn-xs' href='/nuova/formDeletePrestador/" + getPrestadorId()
                + "'><span class='icon icon-remove'></span>Eliminar</a>";

        this.acciones = botonEdit + botonDelete;

        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

}
