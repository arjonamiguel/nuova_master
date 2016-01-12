package com.nuova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.PracticaDAO;
import com.nuova.model.Practica;

@Service
public class PracticaManagerImpl implements PracticaManager {
    @Autowired
    PracticaDAO practicaDAO;

    @Transactional
    public void add(Practica practica) {
        practicaDAO.add(practica);
    }

    @Transactional
    public Practica findPracticaById(Integer practicaId) {
        return practicaDAO.findPracticaById(practicaId);
    }

    @Transactional
    public List<Practica> findAll() {
        return practicaDAO.findAll();
    }

    @Transactional
    public void edit(Practica practica) {
        practicaDAO.edit(practica);
    }

    @Transactional
    public void deletePractica(Integer practicaId) {
        practicaDAO.deletePractica(practicaId);
    }

}
