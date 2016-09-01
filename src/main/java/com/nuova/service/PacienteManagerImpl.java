package com.nuova.service;

import com.nuova.dao.PacienteDAO;
import com.nuova.dto.GridPacienteDTO;
import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.dto.PacienteAutocompleteDTO;
import com.nuova.dto.PacienteDTO;
import com.nuova.dto.PacienteInfoDTO;
import com.nuova.model.Empresas;
import com.nuova.model.Especialidad;
import com.nuova.model.HistoriaClinica;
import com.nuova.model.HistoriaClinicaAdjuntos;
import com.nuova.model.HistoriaClinicaObservaciones;
import com.nuova.model.Localidades;
import com.nuova.model.Paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PacienteManagerImpl implements PacienteManager {
  @Autowired
  PacienteDAO pacienteDAO;


  public void add(Paciente paciente) {
    pacienteDAO.add(paciente);
  }


  public Paciente fin1dPacienteById(Integer pacienteId) {
    return pacienteDAO.fin1dPacienteById(pacienteId);
  }


  public List<Paciente> findAll() {
    return pacienteDAO.findAll();
  }


  public List<Paciente> findAllActive() {
    return pacienteDAO.findAllActive();
  }


  public void delete(Integer pacienteId) {
    pacienteDAO.delete(pacienteId);
  }


  public void edit(Paciente paciente) {
    pacienteDAO.edit(paciente);
  }


  public void deleteAdherente(Integer pacienteId) {
    pacienteDAO.deleteAdherente(pacienteId);
  }


  public void deletePacienteObrasocial(Integer pacienteId) {
    pacienteDAO.deletePacienteObrasocial(pacienteId);
  }


  public Page<GridPacienteDTO> findPacientesByPageable(Pageable pageable) {
    return pacienteDAO.findPacientesByPageable(pageable);
  }


  public Page<Paciente> findPacientesBySearch(String search, Pageable pageable) {
    return pacienteDAO.findPacientesBySearch(search, pageable);
  }


  public OrdenAlarmaDTO countPacientes() {
    return pacienteDAO.countPacientes();
  }


  public List<PacienteAutocompleteDTO> findPacienteAutocomplete(String search) {
    return pacienteDAO.findPacienteAutocomplete(search);
  }


  public Paciente findPacienteByDni(Integer dni) {
    return pacienteDAO.findPacienteByDni(dni);
  }


  public Paciente findPacienteByCredencialSufijo(String nroCredencial, String nroCredencialSufijo) {
    return pacienteDAO.findPacienteByCredencialSufijo(nroCredencial, nroCredencialSufijo);
  }


  public List<Localidades> findLocalidadesAutocomplete(String search) {
    return pacienteDAO.findLocalidadesAutocomplete(search);
  }


  public Localidades findLocalidadById(Integer localidadId) {
    return pacienteDAO.findLocalidadById(localidadId);
  }


  public Paciente findPacienteByCredencial(String credencial) {
    return pacienteDAO.findPacienteByCredencial(credencial);
  }


  public List<Empresas> findAllEmpresas() {
    return pacienteDAO.findAllEmpresas();
  }


  public Empresas findEmpresaById(Integer empresaId) {
    return pacienteDAO.findEmpresaById(empresaId);
  }


  public List<Especialidad> findEspecialidadesAutocomplete(String search, Integer tipo) {
    return pacienteDAO.findEspecialidadesAutocomplete(search, tipo);
  }


  public void add(Empresas empresa) {
    pacienteDAO.add(empresa);
  }


  public List<Paciente> findAllPacienteByCredencial(String credencial) {
    return pacienteDAO.findAllPacienteByCredencial(credencial);
  }


  public void addLocalidad(Localidades localidad) {
    pacienteDAO.addLocalidad(localidad);
  }

  public PacienteInfoDTO findPacientesInfo(Integer pacienteId) {
    return pacienteDAO.findPacientesInfo(pacienteId);
  }


  public List<PacienteDTO> getAdherentes(Integer titular) {
    return pacienteDAO.getAdherentes(titular);
  }


  public void addHistoriaClinica(HistoriaClinica hc) {
    pacienteDAO.addHistoriaClinica(hc);

  }


  public void addHistoriaClinicaObservaciones(HistoriaClinicaObservaciones hco) {
    pacienteDAO.addHistoriaClinicaObservaciones(hco);

  }


  public void addHistoriaClinicaAdjuntos(HistoriaClinicaAdjuntos hca) {
    pacienteDAO.addHistoriaClinicaAdjuntos(hca);

  }


  public HistoriaClinica findHistoriaClinicaByFecha(Date fecha, Integer pacienteId) {
    return pacienteDAO.findHistoriaClinicaByFecha(fecha, pacienteId);
  }


  public HistoriaClinicaAdjuntos findAdjuntoById(Integer adjuntoId) {
    return pacienteDAO.findAdjuntoById(adjuntoId);
  }


  public Paciente findPacienteByOrdenId(Integer ordenId) {
    return pacienteDAO.findPacienteByOrdenId(ordenId);
  }

}
