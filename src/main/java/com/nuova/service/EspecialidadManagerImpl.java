package com.nuova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.EspecialidadDAO;
import com.nuova.model.Especialidad;

@Service
public class EspecialidadManagerImpl implements EspecialidadManager {
    @Autowired
    EspecialidadDAO especialidadDAO;

    public void add(Especialidad especalidad) {
        // TODO Auto-generated method stub

    }

    @Transactional
    public List<Especialidad> findAll() {
        return especialidadDAO.findAll();
    }

    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

}
