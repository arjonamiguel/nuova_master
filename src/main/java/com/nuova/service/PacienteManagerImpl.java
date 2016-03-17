package com.nuova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.PacienteDAO;
import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Paciente;

@Service
public class PacienteManagerImpl implements PacienteManager {
    @Autowired
    PacienteDAO pacienteDAO;

    @Transactional
    public void add(Paciente paciente) {
        pacienteDAO.add(paciente);
    }

    @Transactional
    public Paciente fin1dPacienteById(Integer pacienteId) {
        return pacienteDAO.fin1dPacienteById(pacienteId);
    }

    @Transactional
    public List<Paciente> findAll() {
        return pacienteDAO.findAll();
    }

    @Transactional
    public void delete(Integer pacienteId) {
        pacienteDAO.delete(pacienteId);
    }

    @Transactional
    public void edit(Paciente paciente) {
        pacienteDAO.edit(paciente);
    }

    @Transactional
    public void deleteAdherente(Integer pacienteId) {
        pacienteDAO.deleteAdherente(pacienteId);
    }

    @Transactional
    public void deletePacienteObrasocial(Integer pacienteId) {
        pacienteDAO.deletePacienteObrasocial(pacienteId);
    }

    @Transactional
    public Page<Paciente> findPacientesByPageable(Pageable pageable) {
        return pacienteDAO.findPacientesByPageable(pageable);
    }

    @Transactional
    public Page<Paciente> findPacientesBySearch(String search, Pageable pageable) {
        return pacienteDAO.findPacientesBySearch(search, pageable);
    }

    @Transactional
    public OrdenAlarmaDTO countPacientes() {
        return pacienteDAO.countPacientes();
    }

    @Transactional
    public List<Paciente> findPacienteAutocomplete(String search) {
        return pacienteDAO.findPacienteAutocomplete(search);
    }

    @Transactional
    public Paciente findPacienteByDni(Integer dni) {
        return pacienteDAO.findPacienteByDni(dni);
    }

}
