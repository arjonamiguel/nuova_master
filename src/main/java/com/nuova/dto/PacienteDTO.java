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
    private String vencCarnet;
    private String domicilio="";
    private String telefono;
    private String mail;
    private Integer localidadId;
    private String localidadString;

    private ObraSocialDTO obrasocial = new ObraSocialDTO();
    private List<ObraSocialDTO> obrasocialList = new ArrayList<ObraSocialDTO>();
    private List<ObraSocialDTO> obrasocialListEdit = new ArrayList<ObraSocialDTO>();
    private String provincia;
    private List<String> provinciaList;
    private List<PacienteDTO> adherentes = new ArrayList<PacienteDTO>();
    private List<PacienteDTO> adherentesEditList = new ArrayList<PacienteDTO>();

    private String crdencial;
    private String credencialSufijo;
    private boolean original = false;

    private boolean titular = false;
    private String checkedTitular;
    private PacienteDTO pacienteTitular;

    private boolean coseguro;
    private String checkedLiberado;

    private String acciones;
    private int parentesco;
    private String parentescoDescription;

    private String zonaAfiliacion;
    private Integer eliminado;
    private String eliminadoView;

    private int trabajaEn;
    private String empresa;
    private Integer empresaId;

    private List<String> razonCoseguroList;
    private String razonCoseguro;

    private String credencialCompleta;
    private String tieneCoseguro;

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
        return apellido==null? "" :apellido.toUpperCase();
    }

    public void setApellido(String apellido) {
        this.apellido = apellido.toUpperCase();
    }

    public String getNombre() {
        return nombre == null ?  "" :nombre.toUpperCase();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getVencCarnet() {
        return vencCarnet;
    }

    public void setVencCarnet(String vencCarnet) {
        this.vencCarnet = vencCarnet;
    }

    public String getDomicilio() {
        return domicilio.toUpperCase();
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio.toUpperCase();
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

    public List<String> getRazonCoseguroList() {
        return razonCoseguroList;
    }

    public void setRazonCoseguroList(List<String> razonCoseguroList) {
        this.razonCoseguroList = razonCoseguroList;
    }

    public String getRazonCoseguro() {
        return razonCoseguro;
    }

    public void setRazonCoseguro(String razonCoseguro) {
        this.razonCoseguro = razonCoseguro;
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

        String botonTipoOrden = "<a class='btn btn-success btn-xs' href='tipoOrden/" + getPacienteId()
                + "'><span class='icon icon-plus-sign'></span>Orden</a>";

        String botonDelete = "<a class='btn btn-danger btn-xs' href='formDeletePaciente/"
                + getPacienteId() + "'><span class='icon icon-remove'></span>Desactivar</a>&nbsp;";

        String botonActivar = "<a class='btn btn-success btn-xs' href='#' onClick='activarPaciente("
                + getPacienteId()
                + ")'><span class='icon icon-ok'></span>Activar&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>&nbsp;";

        this.acciones = botonEdit + (getEliminado().intValue() == 0 ? botonDelete : botonActivar);

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

    public Integer getLocalidadId() {
        return localidadId;
    }

    public void setLocalidadId(Integer localidadId) {
        this.localidadId = localidadId;
    }

    public String getLocalidadString() {
        return localidadString;
    }

    public void setLocalidadString(String localidadString) {
        this.localidadString = localidadString;
    }

    public Integer getEliminado() {
        return eliminado;
    }

    public void setEliminado(Integer eliminado) {
        this.eliminado = eliminado;
    }

    public int getTrabajaEn() {
        return trabajaEn;
    }

    public void setTrabajaEn(int trabajaEn) {
        this.trabajaEn = trabajaEn;
    }

    public String getEliminadoView() {
        return eliminadoView;
    }

    public void setEliminadoView(String eliminadoView) {
        this.eliminadoView = eliminadoView;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public String getCredencialSufijo() {
        return credencialSufijo;
    }

    public void setCredencialSufijo(String credencialSufijo) {
        this.credencialSufijo = credencialSufijo;
    }

    public String getCredencialCompleta() {
        this.credencialCompleta = getCrdencial() + "-" + getCredencialSufijo();
        return credencialCompleta;
    }

    public void setCredencialCompleta(String credencialCompleta) {
        this.credencialCompleta = credencialCompleta;
    }

    public String getTieneCoseguro() {
        this.tieneCoseguro = getCoseguro() == true ? "Si" : "No";
        return tieneCoseguro;
    }

    public void setTieneCoseguro(String tieneCoseguro) {
        this.tieneCoseguro = tieneCoseguro;
    }

}
