package com.nuova.service;

import com.nuova.dao.PacienteDAO;
import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Empresas;
import com.nuova.model.Especialidad;
import com.nuova.model.Localidades;
import com.nuova.model.Paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PacienteManagerImpl implements PacienteManager {
  @Autowired
  PacienteDAO pacienteDAO;

  @Override
  public void add(Paciente paciente) {
    pacienteDAO.add(paciente);
  }

  @Override
  public Paciente fin1dPacienteById(Integer pacienteId) {
    return pacienteDAO.fin1dPacienteById(pacienteId);
  }

  @Override
  public List<Paciente> findAll() {
    return pacienteDAO.findAll();
  }

  @Override
  public void delete(Integer pacienteId) {
    pacienteDAO.delete(pacienteId);
  }

  @Override
  public void edit(Paciente paciente) {
    pacienteDAO.edit(paciente);
  }

  @Override
  public void deleteAdherente(Integer pacienteId) {
    pacienteDAO.deleteAdherente(pacienteId);
  }

  @Override
  public void deletePacienteObrasocial(Integer pacienteId) {
    pacienteDAO.deletePacienteObrasocial(pacienteId);
  }

  @Override
  public Page<Paciente> findPacientesByPageable(Pageable pageable) {
    return pacienteDAO.findPacientesByPageable(pageable);
  }

  @Override
  public Page<Paciente> findPacientesBySearch(String search, Pageable pageable) {
    return pacienteDAO.findPacientesBySearch(search, pageable);
  }

  @Override
  public OrdenAlarmaDTO countPacientes() {
    return pacienteDAO.countPacientes();
  }

  @Override
  public List<Paciente> findPacienteAutocomplete(String search) {
    return pacienteDAO.findPacienteAutocomplete(search);
  }

  @Override
  public Paciente findPacienteByDni(Integer dni) {
    return pacienteDAO.findPacienteByDni(dni);
  }

  @Override
  public List<Localidades> findLocalidadesAutocomplete(String search) {
    return pacienteDAO.findLocalidadesAutocomplete(search);
  }

  @Override
  public Localidades findLocalidadById(Integer localidadId) {
    return pacienteDAO.findLocalidadById(localidadId);
  }

  @Override
  public Paciente findPacienteByCredencial(String credencial) {
    return pacienteDAO.findPacienteByCredencial(credencial);
  }

  @Override
  public List<Empresas> findAllEmpresas() {
    return pacienteDAO.findAllEmpresas();
  }

  @Override
  public Empresas findEmpresaById(Integer empresaId) {
    return pacienteDAO.findEmpresaById(empresaId);
  }

  @Override
  public List<Especialidad> findEspecialidadesAutocomplete(String search) {
    return pacienteDAO.findEspecialidadesAutocomplete(search);
  }

  @Override

  public void add(Empresas empresa) {
    pacienteDAO.add(empresa);
  }

  @Override
  public List<Paciente> findAllPacienteByCredencial(String credencial) {
    return pacienteDAO.findAllPacienteByCredencial(credencial);
  }

}
