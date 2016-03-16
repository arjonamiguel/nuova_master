package com.nuova.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nuova.model.Nomenclador;

public interface PracticaManager {
    public void add(Nomenclador practica);

    public Nomenclador findPracticaById(Integer practicaId);

    public List<Nomenclador> findAll();

    public void edit(Nomenclador practica);

    public void deletePractica(Integer practicaId);

    public Page<Nomenclador> findPracticaByPageable(Pageable pageable);

    public Page<Nomenclador> findPracticaBySearch(String search, Pageable pageable);
}
