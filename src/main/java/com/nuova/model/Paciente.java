package com.nuova.model;

// Generated Mar 14, 2016 12:41:49 AM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Paciente generated by hbm2java
 */
@Entity
@Table(name = "paciente", catalog = "nuova")
public class Paciente implements java.io.Serializable {

    private Integer pacienteId;
    private Paciente paciente;
    private String apellido;
    private String nombre;
    private Date fechaNacimiento;
    private Date vencCarnet;
    private String domicilio;
    private String telefono;
    private String mail;
    private Byte coseguro;
    private String dni;
    private String provincia;
    private String razonCoseguro;
    private Byte parentesco;
    private String zonaAfiliacion;
    private Integer obrasocialId;
    private String nroCredencial;
    private Byte eliminado;
    private Set<Orden> ordens = new HashSet<Orden>(0);
    private Set<Paciente> pacientes = new HashSet<Paciente>(0);
    private Integer localidadId;
    private Integer trabajaEn;
    private String empresa;
    private Integer empresaId;
    private String nroCredencialSufijo;
    private String search;

    private Date fechaAlta;

    private Set<PacienteObservaciones> pacienteObservacioneses = new HashSet<PacienteObservaciones>(0);

    public Paciente() {
    }

    public Paciente(Paciente paciente, String apellido, String nombre, Date fechaNacimiento, Date vencCarnet,
            String domicilio, String telefono, String mail, Byte coseguro, Integer dni, String provincia,
            String razonCoseguro, Byte parentesco, String zonaAfiliacion, Integer obrasocialId,
            String nroCredencial, Byte eliminado, Set<Orden> ordens, Set<Paciente> pacientes) {
        this.paciente = paciente;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.vencCarnet = vencCarnet;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.mail = mail;
        this.coseguro = coseguro;
        this.dni = dni.toString();
        this.provincia = provincia;
        this.razonCoseguro = razonCoseguro;
        this.parentesco = parentesco;
        this.zonaAfiliacion = zonaAfiliacion;
        this.obrasocialId = obrasocialId;
        this.nroCredencial = nroCredencial;
        this.eliminado = eliminado;
        this.ordens = ordens;
        this.pacientes = pacientes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paciente_id", unique = true, nullable = false)
    public Integer getPacienteId() {
        return this.pacienteId;
    }

    public void setPacienteId(Integer pacienteId) {
        this.pacienteId = pacienteId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adherente_id")
    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Column(name = "apellido", length = 256)
    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Column(name = "nombre", length = 256)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento", length = 10)
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "venc_carnet", length = 10)
    public Date getVencCarnet() {
        return this.vencCarnet;
    }

    public void setVencCarnet(Date vencCarnet) {
        this.vencCarnet = vencCarnet;
    }

    @Column(name = "domicilio", length = 512)
    public String getDomicilio() {
        return this.domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @Column(name = "telefono", length = 56)
    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Column(name = "mail", length = 128)
    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Column(name = "coseguro")
    public Byte getCoseguro() {
        return this.coseguro;
    }

    public void setCoseguro(Byte coseguro) {
        this.coseguro = coseguro;
    }

    @Column(name = "dni")
    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Column(name = "provincia", length = 156)
    public String getProvincia() {
        return this.provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Column(name = "razon_coseguro", length = 156)
    public String getRazonCoseguro() {
        return this.razonCoseguro;
    }

    public void setRazonCoseguro(String razonCoseguro) {
        this.razonCoseguro = razonCoseguro;
    }

    @Column(name = "parentesco")
    public Byte getParentesco() {
        return this.parentesco;
    }

    public void setParentesco(Byte parentesco) {
        this.parentesco = parentesco;
    }

    @Column(name = "zona_afiliacion", length = 156)
    public String getZonaAfiliacion() {
        return this.zonaAfiliacion;
    }

    public void setZonaAfiliacion(String zonaAfiliacion) {
        this.zonaAfiliacion = zonaAfiliacion;
    }

    @Column(name = "obrasocial_id")
    public Integer getObrasocialId() {
        return this.obrasocialId;
    }

    public void setObrasocialId(Integer obrasocialId) {
        this.obrasocialId = obrasocialId;
    }

    @Column(name = "nro_credencial", length = 64)
    public String getNroCredencial() {
        return this.nroCredencial;
    }

    public void setNroCredencial(String nroCredencial) {
        this.nroCredencial = nroCredencial;
    }

    @Column(name = "eliminado")
    public Byte getEliminado() {
        return this.eliminado;
    }

    public void setEliminado(Byte eliminado) {
        this.eliminado = eliminado;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "paciente")
    public Set<Orden> getOrdens() {
        return this.ordens;
    }

    public void setOrdens(Set<Orden> ordens) {
        this.ordens = ordens;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "paciente")
    public Set<Paciente> getPacientes() {
        return this.pacientes;
    }

    public void setPacientes(Set<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @Column(name = "localidad_id")
    public Integer getLocalidadId() {
        return localidadId;
    }

    public void setLocalidadId(Integer localidadId) {
        this.localidadId = localidadId;
    }

    @Column(name = "trabaja_en")
    public Integer getTrabajaEn() {
        return trabajaEn;
    }

    public void setTrabajaEn(Integer trabajaEn) {
        this.trabajaEn = trabajaEn;
    }

    @Column(name = "empresa")
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Column(name = "empresa_id")
    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    @Column(name = "nro_credencial_sufijo")
    public String getNroCredencialSufijo() {
        return nroCredencialSufijo;
    }

    public void setNroCredencialSufijo(String nroCredencialSufijo) {
        this.nroCredencialSufijo = nroCredencialSufijo;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_alta", length = 10)
    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "paciente")
    public Set<PacienteObservaciones> getPacienteObservacioneses() {
        return this.pacienteObservacioneses;
    }

    public void setPacienteObservacioneses(Set<PacienteObservaciones> pacienteObservacioneses) {
        this.pacienteObservacioneses = pacienteObservacioneses;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

}
