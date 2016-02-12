package com.nuova.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.CajaDAO;
import com.nuova.model.Caja;

@Service
@Transactional
public class CajaManagerImpl implements CajaManager {
    @Autowired
    private CajaDAO cajaDAO;

    public void add(Caja caja) {
        cajaDAO.add(caja);
    }

    public Caja findCajaById(Integer cajaId) {
        return cajaDAO.findCajaById(cajaId);
    }

    public List<Caja> findAll() {
        return cajaDAO.findAll();
    }

    public void delete(Integer cajaId) {
        cajaDAO.delete(cajaId);
    }

    public void edit(Caja caja) {
        cajaDAO.edit(caja);
    }

    public Page<Caja> findCajaByPageable(Pageable pageable) {
        return cajaDAO.findCajaByPageable(pageable);
    }

    public Page<Caja> findCajaBySearch(String search, Pageable pageable) {
        return cajaDAO.findCajaBySearch(search, pageable);
    }

    public List<Caja> findAllByfecha(Date fecha) {
        return cajaDAO.findAllByfecha(fecha);
    }

}
