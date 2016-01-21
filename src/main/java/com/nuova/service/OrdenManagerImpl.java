package com.nuova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.OrdenDAO;
import com.nuova.model.Orden;

@Service
public class OrdenManagerImpl implements OrdenManager {
    @Autowired
    OrdenDAO ordenDAO;

    @Transactional
    public void add(Orden orden) {
        ordenDAO.add(orden);
    }

    @Transactional
    public Orden findOrdenById(Integer ordenId) {
        return ordenDAO.findOrdenById(ordenId);
    }

    @Transactional
    public List<Orden> findAll() {
        return ordenDAO.findAll();
    }

    @Transactional
    public void delete(Integer ordenId) {
        ordenDAO.delete(ordenId);
    }

    @Transactional
    public void edit(Orden orden) {
        ordenDAO.edit(orden);
    }

    @Transactional
    public void deleteOrdenPractica(Integer id) {
        ordenDAO.deleteOrdenPractica(id);
    }

    @Transactional
    public Page<Orden> findOrdenesByPageable(Pageable pageable) {
        return ordenDAO.findOrdenesByPageable(pageable);
    }

    @Transactional
    public Page<Orden> findOrdenesBySearch(String search, Pageable pageable) {
        return ordenDAO.findOrdenesBySearch(search, pageable);
    }

}
