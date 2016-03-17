package com.nuova.dto;

import java.util.ArrayList;
import java.util.List;

import com.nuova.model.Paciente;

public class PacienteDTO {

    private Integer pacienteId;
    private Integer titularId;
    private Integer dni;
    private Paciente paciente;
    private String apellido;
    private String nombre;
    private String fechaNacimiento;
    private String domicilio;
    private String telefono;
    private String mail;

    private ObraSocialDTO obrasocial = new ObraSocialDTO();
    private List<ObraSocialDTO> obrasocialList = new ArrayList<ObraSocialDTO>();
    private List<ObraSocialDTO> obrasocialListEdit = new ArrayList<ObraSocialDTO>();
    private String provincia;
    private List<String> provinciaList;
    private List<PacienteDTO> adherentes = new ArrayList<PacienteDTO>();
    private List<PacienteDTO> adherentesEditList = new ArrayList<PacienteDTO>();

    private String crdencial;
    private boolean original = false;

    private boolean titular = false;
    private String checkedTitular;
    private PacienteDTO pacienteTitular;

    private boolean coseguro = false;
    private String checkedLiberado;

    private String acciones;
    private int parentesco;
    private String parentescoDescription;

    private String zonaAfiliacion;

    // private List<PacienteObrasocial> pacienteObrasocials = new ArrayList<PacienteObrasocial>(0);

    public Integer getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Integer pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean getCoseguro() {
        return coseguro;
    }

    public void setCoseguro(boolean coseguro) {
        this.coseguro = coseguro;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public ObraSocialDTO getObrasocial() {
        return obrasocial;
    }

    public void setObrasocial(ObraSocialDTO obrasocial) {
        this.obrasocial = obrasocial;
    }

    public List<ObraSocialDTO> getObrasocialList() {
        return obrasocialList;
    }

    public void setObrasocialList(List<ObraSocialDTO> obrasocialList) {
        this.obrasocialList = obrasocialList;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public List<String> getProvinciaList() {
        return provinciaList;
    }

    public void setProvinciaList(List<String> provinciaList) {
        this.provinciaList = provinciaList;
    }

    public List<ObraSocialDTO> getObrasocialListEdit() {
        return obrasocialListEdit;
    }

    public void setObrasocialListEdit(List<ObraSocialDTO> obrasocialListEdit) {
        this.obrasocialListEdit = obrasocialListEdit;
    }

    public Integer getTitularId() {
        return titularId;
    }

    public void setTitularId(Integer titularId) {
        this.titularId = titularId;
    }

    public List<PacienteDTO> getAdherentes() {
        return adherentes;
    }

    public void setAdherentes(List<PacienteDTO> adherentes) {
        this.adherentes = adherentes;
    }

    public List<PacienteDTO> getAdherentesEditList() {
        return adherentesEditList;
    }

    public void setAdherentesEditList(List<PacienteDTO> adherentesEditList) {
        this.adherentesEditList = adherentesEditList;
    }

    public String getCrdencial() {
        return crdencial;
    }

    public void setCrdencial(String crdencial) {
        this.crdencial = crdencial;
    }

    public boolean isTitular() {
        return titular;
    }

    public void setTitular(boolean titular) {
        this.titular = titular;
    }

    public String getCheckedTitular() {
        return checkedTitular;
    }

    public void setCheckedTitular(String checkedTitular) {
        this.checkedTitular = checkedTitular;
    }

    public PacienteDTO getPacienteTitular() {
        return pacienteTitular;
    }

    public void setPacienteTitular(PacienteDTO pacienteTitular) {
        this.pacienteTitular = pacienteTitular;
    }

    public String getCheckedLiberado() {
        return checkedLiberado;
    }

    public void setCheckedLiberado(String checkedLiberado) {
        this.checkedLiberado = checkedLiberado;
    }

    public boolean isOriginal() {
        return original;
    }

    public void setOriginal(boolean original) {
        this.original = original;
    }

    public String getAcciones() {
        String botonEdit = "<a class='btn btn-info btn-xs' href='formEditPaciente/" + getPacienteId()
                + "'><span class='icon icon-edit'></span>Editar</a>&nbsp;";

        String botonTipoOrden = "<a class='btn btn-success btn-xs' href='tipoOrden/"
                + getPacienteId()
                + "'><span class='icon icon-plus-sign'></span>Orden</a>&nbsp;";

        String botonDelete = "<a class='btn btn-danger btn-xs' href='formDeletePaciente/" + getPacienteId()
                + "'><span class='icon icon-remove'></span>Eliminar</a>";

        this.acciones = botonEdit + botonTipoOrden + botonDelete;

        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    public int getParentesco() {
        return parentesco;
    }

    public void setParentesco(int parentesco) {
        this.parentesco = parentesco;
    }

    public String getParentescoDescription() {
        return parentescoDescription;
    }

    public void setParentescoDescription(String parentescoDescription) {
        this.parentescoDescription = parentescoDescription;
    }

    public String getZonaAfiliacion() {
        return zonaAfiliacion;
    }

    public void setZonaAfiliacion(String zonaAfiliacion) {
        this.zonaAfiliacion = zonaAfiliacion;
    }

}
