package com.nuova.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nuova.model.Practica;

public interface PracticaManager {
    public void add(Practica practica);

    public Practica findPracticaById(Integer practicaId);

    public List<Practica> findAll();

    public void edit(Practica practica);

    public void deletePractica(Integer practicaId);

    public Page<Practica> findPracticaByPageable(Pageable pageable);

    public Page<Practica> findPracticaBySearch(String search, Pageable pageable);
}
