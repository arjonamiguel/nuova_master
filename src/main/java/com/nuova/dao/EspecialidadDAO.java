package com.nuova.dao;

import java.util.List;

import com.nuova.model.Especialidad;

public interface EspecialidadDAO {
    public void add(Especialidad especialidad);

    public List<Especialidad> findAll();

    public void delete(Integer id);
}
