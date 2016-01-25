package com.nuova.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Orden;

public interface OrdenManager {
    public void add(Orden orden);

    public Orden findOrdenById(Integer ordenId);

    public List<Orden> findAll();

    public void delete(Integer ordenId);

    public void deleteOrdenPractica(Integer id);

    public void edit(Orden orden);

    public Page<Orden> findOrdenesByPageable(Pageable pageable);

    public Page<Orden> findOrdenesBySearch(String search, Pageable pageable);

    public List<OrdenAlarmaDTO> findAlarmaOrdenes();

}
