package com.nuova.service;

import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Profesional;
import com.nuova.model.ProfesionalEspecialidad;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfesionalManager {
  public void add(Profesional profesional);

  public Profesional findProfesionalById(Integer profesionalId);

  public List<Profesional> findAll();

  public void delete(Integer id);

  public void edit(Profesional profesional);

  public void deleteProfesionalEspecialidad(Integer profesionalId);

  public Page<Profesional> findProfesionalesByPageable(Pageable pageable);

  public Page<Profesional> findProfesionalesBySearch(String search, Pageable pageable);

  public OrdenAlarmaDTO countProfesionales();

  public List<Profesional> findAutocompleteProfesional(String query);

  public void addProfesionalEspecialidad(ProfesionalEspecialidad pe);
}
