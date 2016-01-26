package com.nuova.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Especialidad;

public interface EspecialidadManager {
    public void add(Especialidad especalidad);

    public Especialidad findEspecialidadById(Integer id);

    public List<Especialidad> findAll();

    public void delete(Integer id);

    public void edit(Especialidad especialidad);

    public Page<Especialidad> findEspecialidadesByPageable(Pageable pageable);

    public Page<Especialidad> findEspecialidadesBySearch(String search, Pageable pageable);

    public OrdenAlarmaDTO countEspecialidades();
}
