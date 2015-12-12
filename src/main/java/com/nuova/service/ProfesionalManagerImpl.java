package com.nuova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.ProfesionalDAO;
import com.nuova.model.Profesional;

@Service
public class ProfesionalManagerImpl implements ProfesionalManager {
    @Autowired
    ProfesionalDAO profesionalDAO;

    @Transactional
    public void add(Profesional profesional) {
        this.profesionalDAO.add(profesional);
    }

    @Transactional
    public List<Profesional> findAll() {
        return profesionalDAO.findAll();
    }

    @Transactional
    public void delete(Integer id) {
        this.profesionalDAO.delete(id);
    }

    @Transactional
    public Profesional findProfesionalById(Integer profesionalId) {
        return profesionalDAO.findProfesionalById(profesionalId);
    }

    @Transactional
    public void edit(Profesional profesional) {
        this.profesionalDAO.edit(profesional);
    }

}
