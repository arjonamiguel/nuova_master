package com.nuova.service;

import com.nuova.dao.ReintegroDAO;
import com.nuova.model.Reintegro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ReintegroManagerImpl implements ReintegroManager {
  @Autowired
  ReintegroDAO reintegroDAO;

  @Override
  public void add(Reintegro reintegro) {
    reintegroDAO.add(reintegro);

  }

  @Override
  public Reintegro findReintegroById(Integer reintegroId) {
    // TODO Auto-generated method stub
    return reintegroDAO.findReintegroById(reintegroId);
  }

  @Override
  public void delete(Integer reintegroId) {
    reintegroDAO.delete(reintegroId);

  }

  @Override
  public void edit(Reintegro reintegro) {
    // TODO Auto-generated method stub
    reintegroDAO.edit(reintegro);
  }

  @Override
  public Page<Reintegro> findReintegrosByPageable(Pageable pageable) {
    // TODO Auto-generated method stub
    return reintegroDAO.findReintegrosByPageable(pageable);
  }

  @Override
  public Page<Reintegro> findReintegrosBySearch(String search, Pageable pageable) {
    // TODO Auto-generated method stub
    return reintegroDAO.findReintegrosBySearch(search, pageable);
  }

  @Override
  public Page<Reintegro> findReintegrosByPacientePageable(Integer pacienteId, Pageable pageable) {
    // TODO Auto-generated method stub
    return reintegroDAO.findReintegrosByPacientePageable(pacienteId, pageable);
  }

}
