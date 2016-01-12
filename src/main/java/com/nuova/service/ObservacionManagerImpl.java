package com.nuova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.ObservacionDAO;
import com.nuova.model.Observaciones;

@Service
public class ObservacionManagerImpl implements ObservacionManager {
    @Autowired
    ObservacionDAO observacionDAO;

    @Transactional
    public void add(Observaciones observacion) {
        observacionDAO.add(observacion);
    }

    @Transactional
    public Observaciones findObservacionesById(Integer observacionId) {
        return observacionDAO.findObservacionesById(observacionId);
    }

    @Transactional
    public List<Observaciones> findAll() {
        return observacionDAO.findAll();
    }

    @Transactional
    public void edit(Observaciones observacion) {
        observacionDAO.edit(observacion);
    }

    @Transactional
    public void deleteObservaciones(Integer observacionId) {
        observacionDAO.deleteObservaciones(observacionId);
    }

}
