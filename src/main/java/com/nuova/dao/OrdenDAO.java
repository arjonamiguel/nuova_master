package com.nuova.dao;

import java.util.List;

import com.nuova.model.Orden;

public interface OrdenDAO {
    public void add(Orden orden);

    public Orden findOrdenById(Integer ordenId);

    public List<Orden> findAll();

    public void delete(Integer ordenId);

    public void deleteOrdenPractica(Integer ordenId);

    public void edit(Orden orden);
}
