package com.nuova.service;

import com.nuova.dao.PrestadoresDAO;
import com.nuova.model.Prestadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PrestadoresManagerImpl implements PrestadoresManager {
  @Autowired
  PrestadoresDAO prestadorDAO;

  @Override
  public void add(Prestadores preador) {
    prestadorDAO.add(preador);
  }

  @Override
  public Prestadores findPrestadorById(Integer prestadorId) {
    return prestadorDAO.findPrestadorById(prestadorId);
  }

  @Override
  public List<Prestadores> findAll() {
    return prestadorDAO.findAll();
  }

  @Override
  public void delete(Integer prestadorId) {
    prestadorDAO.delete(prestadorId);
  }

  @Override
  public void edit(Prestadores prestador) {
    prestadorDAO.edit(prestador);
  }

  @Override
  public Page<Prestadores> findPrestadoresByPageable(Pageable pageable) {
    return prestadorDAO.findPrestadoresByPageable(pageable);
  }

  @Override
  public Page<Prestadores> findPrestadoresBySearch(String search, Pageable pageable) {
    return prestadorDAO.findPrestadoresBySearch(search, pageable);
  }

  @Override
  public void deletePrestadorEspecialidad(Integer prestadorId) {
    prestadorDAO.deletePrestadorEspecialidad(prestadorId);
  }



}
