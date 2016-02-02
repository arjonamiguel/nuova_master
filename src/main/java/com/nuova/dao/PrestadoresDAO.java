package com.nuova.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nuova.model.Prestadores;

public interface PrestadoresDAO {
    public void add(Prestadores preador);

    public Prestadores findPrestadorById(Integer prestadorId);

    public List<Prestadores> findAll();

    public void delete(Integer prestadorId);

    public void edit(Prestadores prestador);

    public Page<Prestadores> findPrestadoresByPageable(Pageable pageable);

    public Page<Prestadores> findPrestadoresBySearch(String search, Pageable pageable);

}
