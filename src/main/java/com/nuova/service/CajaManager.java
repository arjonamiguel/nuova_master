package com.nuova.service;

import com.nuova.model.Caja;
import com.nuova.model.CajaCierre;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface CajaManager {

  public void add(Caja caja);

  public Caja findCajaById(Integer cajaId);

  public List<Caja> findAll();

  public void delete(Integer cajaId);

  public void edit(Caja caja);

  public Page<Caja> findCajaByPageable(Pageable pageable);

  public Page<Caja> findCajaBySearch(String search, Pageable pageable);

  public List<Caja> findAllByfecha(Date fecha);

  public void addCajaCierre(CajaCierre cierre);

  public Page<CajaCierre> findCajaCierreByPageable(Pageable pageable);

  public Page<CajaCierre> findCajaCierreBySearch(String search, Pageable pageable);

  public CajaCierre findCajaCierreByFecha(Date fecha);

}
