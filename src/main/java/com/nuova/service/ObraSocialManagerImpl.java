package com.nuova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.ObraSocialDAO;
import com.nuova.model.Obrasocial;

@Service
public class ObraSocialManagerImpl implements ObraSocialManager {
    @Autowired
    ObraSocialDAO obrasocialDAO;

    @Transactional
    public void add(Obrasocial obrasocial) {
        obrasocialDAO.add(obrasocial);
    }

    @Transactional
    public Obrasocial findObraSocialById(Integer obrasocialId) {
        return obrasocialDAO.findObraSocialById(obrasocialId);
    }

    @Transactional
    public List<Obrasocial> findAll() {
        return obrasocialDAO.findAll();
    }

    @Transactional
    public void delete(Integer obrasocialId) {
        obrasocialDAO.delete(obrasocialId);
    }

    @Transactional
    public void edit(Obrasocial obrasocial) {
        obrasocialDAO.edit(obrasocial);
    }

}
