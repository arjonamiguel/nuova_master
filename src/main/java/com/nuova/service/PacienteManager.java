package com.nuova.service;

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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface PacienteManager {
  public void add(Paciente paciente);

  public Paciente fin1dPacienteById(Integer pacienteId);

  public List<Paciente> findAll();

  public List<Paciente> findAllActive();

  public void delete(Integer pacienteId);

  public void edit(Paciente paciente);

  public void deleteAdherente(Integer pacienteId);

  public void deletePacienteObrasocial(Integer pacienteId);

  public Page<GridPacienteDTO> findPacientesByPageable(Pageable pageable);

  public Page<Paciente> findPacientesBySearch(String search, Pageable pageable);

  public OrdenAlarmaDTO countPacientes();

  public List<PacienteAutocompleteDTO> findPacienteAutocomplete(String search);

  public Paciente findPacienteByDni(Integer dni);

  public Paciente findPacienteByCredencialSufijo(String nroCredencial, String nroCredencialSufijo);

  public List<Localidades> findLocalidadesAutocomplete(String search);

  public Localidades findLocalidadById(Integer localidadId);

  public Paciente findPacienteByCredencial(String credencial);

  public List<Paciente> findAllPacienteByCredencial(String credencial);

  public List<Especialidad> findEspecialidadesAutocomplete(String search, Integer tipo);

  public List<Empresas> findAllEmpresas();

  public Empresas findEmpresaById(Integer empresaId);

  public void add(Empresas empresa);

  public void addLocalidad(Localidades localidad);

  public PacienteInfoDTO findPacientesInfo(Integer pacienteId);

  public List<PacienteDTO> getAdherentes(Integer titular);

  public void addHistoriaClinica(HistoriaClinica hc);

  public void addHistoriaClinicaObservaciones(HistoriaClinicaObservaciones hco);

  public void addHistoriaClinicaAdjuntos(HistoriaClinicaAdjuntos hca);

  public HistoriaClinica findHistoriaClinicaByFecha(Date fecha, Integer pacienteId);

  public HistoriaClinicaAdjuntos findAdjuntoById(Integer adjuntoId);

}
