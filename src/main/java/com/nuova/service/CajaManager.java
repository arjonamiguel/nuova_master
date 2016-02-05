package com.nuova.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nuova.model.Caja;

public interface CajaManager {

    public void add(Caja caja);

    public Caja findCajaById(Integer cajaId);

    public List<Caja> findAll();

    public void delete(Integer cajaId);

    public void edit(Caja caja);

    public Page<Caja> findCajaByPageable(Pageable pageable);

    public Page<Caja> findCajaBySearch(String search, Pageable pageable);
}
