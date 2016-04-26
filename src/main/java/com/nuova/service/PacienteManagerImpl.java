package com.nuova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.PacienteDAO;
import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Empresas;
import com.nuova.model.Localidades;
import com.nuova.model.Paciente;

@Service
public class PacienteManagerImpl implements PacienteManager {
    @Autowired
    PacienteDAO pacienteDAO;

    @Override
    @Transactional
    public void add(Paciente paciente) {
        pacienteDAO.add(paciente);
    }

    @Override
    @Transactional
    public Paciente fin1dPacienteById(Integer pacienteId) {
        return pacienteDAO.fin1dPacienteById(pacienteId);
    }

    @Override
    @Transactional
    public List<Paciente> findAll() {
        return pacienteDAO.findAll();
    }

    @Override
    @Transactional
    public void delete(Integer pacienteId) {
        pacienteDAO.delete(pacienteId);
    }

    @Override
    @Transactional
    public void edit(Paciente paciente) {
        pacienteDAO.edit(paciente);
    }

    @Override
    @Transactional
    public void deleteAdherente(Integer pacienteId) {
        pacienteDAO.deleteAdherente(pacienteId);
    }

    @Override
    @Transactional
    public void deletePacienteObrasocial(Integer pacienteId) {
        pacienteDAO.deletePacienteObrasocial(pacienteId);
    }

    @Override
    @Transactional
    public Page<Paciente> findPacientesByPageable(Pageable pageable) {
        return pacienteDAO.findPacientesByPageable(pageable);
    }

    @Override
    @Transactional
    public Page<Paciente> findPacientesBySearch(String search, Pageable pageable) {
        return pacienteDAO.findPacientesBySearch(search, pageable);
    }

    @Override
    @Transactional
    public OrdenAlarmaDTO countPacientes() {
        return pacienteDAO.countPacientes();
    }

    @Override
    @Transactional
    public List<Paciente> findPacienteAutocomplete(String search) {
        return pacienteDAO.findPacienteAutocomplete(search);
    }

    @Override
    @Transactional
    public Paciente findPacienteByDni(Integer dni) {
        return pacienteDAO.findPacienteByDni(dni);
    }

    @Override
    @Transactional
    public List<Localidades> findLocalidadesAutocomplete(String search) {
        return pacienteDAO.findLocalidadesAutocomplete(search);
    }

    @Override
    @Transactional
    public Localidades findLocalidadById(Integer localidadId) {
        return pacienteDAO.findLocalidadById(localidadId);
    }

    @Override
    @Transactional
    public Paciente findPacienteByCredencial(String credencial) {
        return pacienteDAO.findPacienteByCredencial(credencial);
    }

    @Override
    @Transactional
    public List<Empresas> findAllEmpresas() {
        return pacienteDAO.findAllEmpresas();
    }
}
