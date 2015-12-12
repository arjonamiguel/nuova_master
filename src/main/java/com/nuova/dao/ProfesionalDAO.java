package com.nuova.dao;

import java.util.List;

import com.nuova.model.Profesional;

public interface ProfesionalDAO {
    public void add(Profesional profesional);

    public Profesional findProfesionalById(Integer profesionalId);

    public List<Profesional> findAll();

    public void delete(Integer id);

    public void edit(Profesional profesional);

}
