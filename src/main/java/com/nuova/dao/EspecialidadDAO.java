package com.nuova.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nuova.model.Especialidad;

public interface EspecialidadDAO {
    public void add(Especialidad especialidad);

    public Especialidad findEspecialidadById(Integer especialidadId);

    public List<Especialidad> findAll();

    public void delete(Integer especialidadId);

    public void edit(Especialidad especialidad);

    public Page<Especialidad> findEspecialidadesByPageable(Pageable pageable);

    public Page<Especialidad> findEspecialidadesBySearch(String search, Pageable pageable);

}
