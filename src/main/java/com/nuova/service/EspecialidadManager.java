package com.nuova.service;

import java.util.List;

import com.nuova.model.Especialidad;

public interface EspecialidadManager {
    public void add(Especialidad especalidad);

    public Especialidad findEspecialidadById(Integer id);

    public List<Especialidad> findAll();

    public void delete(Integer id);

    public void edit(Especialidad especialidad);
}
