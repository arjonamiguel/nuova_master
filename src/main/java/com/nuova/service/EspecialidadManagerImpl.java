package com.nuova.service;

import com.nuova.dao.EspecialidadDAO;
import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Especialidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EspecialidadManagerImpl implements EspecialidadManager {
  @Autowired
  EspecialidadDAO especialidadDAO;

  @Override
  @Transactional
  public void add(Especialidad especialidad) {
    especialidadDAO.add(especialidad);
  }

  @Override
  @Transactional
  public List<Especialidad> findAll() {
    return especialidadDAO.findAll();
  }

  @Override
  @Transactional
  public void delete(Integer id) {
    especialidadDAO.delete(id);

  }

  @Override
  @Transactional
  public Especialidad findEspecialidadById(Integer id) {
    return especialidadDAO.findEspecialidadById(id);
  }

  @Override
  @Transactional
  public void edit(Especialidad especialidad) {
    this.especialidadDAO.edit(especialidad);
  }

  @Override
  @Transactional
  public Page<Especialidad> findEspecialidadesByPageable(Pageable pageable) {
    return especialidadDAO.findEspecialidadesByPageable(pageable);
  }

  @Override
  @Transactional
  public Page<Especialidad> findEspecialidadesBySearch(String search, Pageable pageable) {
    return especialidadDAO.findEspecialidadesBySearch(search, pageable);
  }

  @Override
  @Transactional
  public OrdenAlarmaDTO countEspecialidades() {
    return especialidadDAO.countEspecialidades();
  }

  @Override
  @Transactional
  public List<Especialidad> findEspecialidadByProfesionalId(Integer profesionalId) {
    return especialidadDAO.findEspecialidadByProfesionalId(profesionalId);
  }
}
