package com.nuova.dao;

import java.util.List;

import com.nuova.model.Profesional;

public interface ProfesionalDAO {
    public void add(Profesional profesional);

    public List<Profesional> findAll();

    public void delete(Integer id);
}
