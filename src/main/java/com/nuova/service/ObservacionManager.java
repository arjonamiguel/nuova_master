package com.nuova.service;

import java.util.List;

import com.nuova.model.Observaciones;

public interface ObservacionManager {
    public void add(Observaciones observacion);

    public Observaciones findObservacionesById(Integer observacionId);

    public List<Observaciones> findAll();

    public void edit(Observaciones observacion);

    public void deleteObservaciones(Integer observacionId);
}
