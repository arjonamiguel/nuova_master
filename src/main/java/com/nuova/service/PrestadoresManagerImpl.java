package com.nuova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.PrestadoresDAO;
import com.nuova.model.Prestadores;

@Transactional
@Service
public class PrestadoresManagerImpl implements PrestadoresManager {
    @Autowired
    PrestadoresDAO prestadorDAO;

    public void add(Prestadores preador) {
        prestadorDAO.add(preador);
    }

    public Prestadores findPrestadorById(Integer prestadorId) {
        return prestadorDAO.findPrestadorById(prestadorId);
    }

    public List<Prestadores> findAll() {
        return prestadorDAO.findAll();
    }

    public void delete(Integer prestadorId) {
        prestadorDAO.delete(prestadorId);
    }

    public void edit(Prestadores prestador) {
        prestadorDAO.edit(prestador);
    }

    public Page<Prestadores> findPrestadoresByPageable(Pageable pageable) {
        return prestadorDAO.findPrestadoresByPageable(pageable);
    }

    public Page<Prestadores> findPrestadoresBySearch(String search, Pageable pageable) {
        return prestadorDAO.findPrestadoresBySearch(search, pageable);
    }

}
