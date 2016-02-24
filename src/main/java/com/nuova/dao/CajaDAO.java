package com.nuova.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nuova.model.Caja;

public interface CajaDAO {

    public void add(Caja caja);

    public Caja findCajaById(Integer cajaId);

    public List<Caja> findAll();

    public void delete(Integer cajaId);

    public void edit(Caja caja);

    public Page<Caja> findCajaByPageable(Pageable pageable);

    public Page<Caja> findCajaBySearch(String search, Pageable pageable);

    public List<Caja> findAllByfecha(Date fecha);

}
