package com.nuova.service;

import java.util.List;

import com.nuova.model.Profesional;

public interface ProfesionalManager {
    public void add(Profesional profesional);

    public List<Profesional> findAll();

    public void delete(Integer id);
}
