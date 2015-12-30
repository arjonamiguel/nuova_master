package com.nuova.dao;

import java.util.List;

import com.nuova.model.Paciente;

public interface PacienteDAO {
    public void add(Paciente paciente);

    public Paciente fin1dPacienteById(Integer pacienteId);

    public List<Paciente> findAll();

    public void delete(Integer pacienteId);

    public void edit(Paciente paciente);

    public void deleteAdherente(Integer pacienteId);

    public void deletePacienteObrasocial(Integer pacienteId);

}
