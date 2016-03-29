package com.nuova.service;

import com.nuova.dao.CajaDAO;
import com.nuova.model.Caja;
import com.nuova.model.CajaCierre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CajaManagerImpl implements CajaManager {
  @Autowired
  private CajaDAO cajaDAO;

  @Override
  public void add(Caja caja) {
    cajaDAO.add(caja);
  }

  @Override
  public Caja findCajaById(Integer cajaId) {
    return cajaDAO.findCajaById(cajaId);
  }

  @Override
  public List<Caja> findAll() {
    return cajaDAO.findAll();
  }

  @Override
  public void delete(Integer cajaId) {
    cajaDAO.delete(cajaId);
  }

  @Override
  public void edit(Caja caja) {
    cajaDAO.edit(caja);
  }

  @Override
  public Page<Caja> findCajaByPageable(Pageable pageable) {
    return cajaDAO.findCajaByPageable(pageable);
  }

  @Override
  public Page<Caja> findCajaBySearch(String search, Pageable pageable) {
    return cajaDAO.findCajaBySearch(search, pageable);
  }

  @Override
  public List<Caja> findAllByfecha(Date fecha) {
    return cajaDAO.findAllByfecha(fecha);
  }

  @Override
  public void addCajaCierre(CajaCierre cierre) {
    cajaDAO.addCajaCierre(cierre);
  }

  @Override
  public Page<CajaCierre> findCajaCierreByPageable(Pageable pageable) {
    return cajaDAO.findCajaCierreByPageable(pageable);
  }

  @Override
  public Page<CajaCierre> findCajaCierreBySearch(String search, Pageable pageable) {
    return cajaDAO.findCajaCierreBySearch(search, pageable);
  }

  @Override
  public CajaCierre findCajaCierreByFecha(Date fecha) {
    return cajaDAO.findCajaCierreByFecha(fecha);
  }

}
