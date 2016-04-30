package com.nuova.dao;

import com.nuova.model.Reintegro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReintegroDAO {

  public void add(Reintegro reintegro);

  public Reintegro findReintegroById(Integer reintegroId);

  public void delete(Integer reintegroId);

  public void edit(Reintegro reintegro);

  public Page<Reintegro> findReintegrosByPageable(Pageable pageable);

  public Page<Reintegro> findReintegrosByPacientePageable(Integer pacienteId, Pageable pageable);

  public Page<Reintegro> findReintegrosBySearch(String search, Pageable pageable);

}
