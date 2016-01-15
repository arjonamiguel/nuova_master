package com.nuova.service;

import java.util.List;

import com.nuova.model.Orden;

public interface OrdenManager {
    public void add(Orden orden);

    public Orden findOrdenById(Integer ordenId);

    public List<Orden> findAll();

    public void delete(Integer ordenId);

    public void deleteOrdenPractica(Integer id);

    public void edit(Orden orden);
}
