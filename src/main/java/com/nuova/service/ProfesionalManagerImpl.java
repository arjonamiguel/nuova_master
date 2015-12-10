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

    public void add(Profesional profesional) {
        // TODO Auto-generated method stub

    }

    @Transactional
    public List<Profesional> findAll() {
        return profesionalDAO.findAll();
    }

    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

}
