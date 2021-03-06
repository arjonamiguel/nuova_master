package com.nuova.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Empresas;
import com.nuova.model.Especialidad;
import com.nuova.model.Localidades;
import com.nuova.model.Paciente;

public interface PacienteDAO {
    public void add(Paciente paciente);

    public Paciente fin1dPacienteById(Integer pacienteId);

    public List<Paciente> findAll();

    public void delete(Integer pacienteId);

    public void edit(Paciente paciente);

    public void deleteAdherente(Integer pacienteId);

    public void deletePacienteObrasocial(Integer pacienteId);

    public Page<Paciente> findPacientesByPageable(Pageable pageable);

    public Page<Paciente> findPacientesBySearch(String search, Pageable pageable);

    public OrdenAlarmaDTO countPacientes();

    public List<Paciente> findPacienteAutocomplete(String search);

    public Paciente findPacienteByDni(Integer dni);

    public List<Localidades> findLocalidadesAutocomplete(String search);

    public Localidades findLocalidadById(Integer localidadId);

    public Paciente findPacienteByCredencial(String credencial);

    public List<Paciente> findAllPacienteByCredencial(String credencial);

    public List<Paciente> findAllAdherentes();

    public List<Especialidad> findEspecialidadesAutocomplete(String search, Integer tipo);

    // Empresas
    public List<Empresas> findAllEmpresas();

    public Empresas findEmpresaById(Integer empresaId);

    public void add(Empresas empresa);

    public void addLocalidad(Localidades localidad);

}
