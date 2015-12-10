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

    @Transactional
    public void add(Especialidad especialidad) {
        especialidadDAO.add(especialidad);
    }

    @Transactional
    public List<Especialidad> findAll() {
        return especialidadDAO.findAll();
    }

    @Transactional
    public void delete(Integer id) {
        especialidadDAO.delete(id);

    }

    @Transactional
    public Especialidad findEspecialidadById(Integer id) {
        return especialidadDAO.findEspecialidadById(id);
    }

    @Transactional
    public void edit(Especialidad especialidad) {
        this.especialidadDAO.edit(especialidad);

    }

}
