package com.nuova.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Especialidad;
import com.nuova.model.Profesional;

public interface EspecialidadDAO {
    public void add(Especialidad especialidad);

    public Especialidad findEspecialidadById(Integer especialidadId);

    public List<Especialidad> findAll();

    public void delete(Integer especialidadId);

    public void edit(Especialidad especialidad);

    public Page<Especialidad> findEspecialidadesByPageable(Pageable pageable);

    public Page<Especialidad> findEspecialidadesBySearch(String search, Pageable pageable);

    public OrdenAlarmaDTO countEspecialidades();

    public List<Profesional> findProfesionalByEspecialidadId(Integer especialidadId);

    public List<Especialidad> findAllByTipo(Integer tipo);

}
