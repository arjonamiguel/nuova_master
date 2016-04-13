package com.nuova.service;

import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Especialidad;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EspecialidadManager {
  public void add(Especialidad especalidad);

  public Especialidad findEspecialidadById(Integer id);

  public List<Especialidad> findAll();

  public void delete(Integer id);

  public void edit(Especialidad especialidad);

  public Page<Especialidad> findEspecialidadesByPageable(Pageable pageable);

  public Page<Especialidad> findEspecialidadesBySearch(String search, Pageable pageable);

  public OrdenAlarmaDTO countEspecialidades();

  public List<Especialidad> findEspecialidadByProfesionalId(Integer profesionalId);
}
