package com.nuova.service;

import java.util.List;

import com.nuova.model.Profesional;

public interface ProfesionalManager {
    public void add(Profesional profesional);

    public Profesional findProfesionalById(Integer profesionalId);

    public List<Profesional> findAll();

    public void delete(Integer id);

    public void edit(Profesional profesional);

    public void deleteProfesionalEspecialidad(Integer profesionalId);
}
