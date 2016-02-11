package com.nuova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.OrdenDAO;
import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Orden;
import com.nuova.model.OrdenTipo;

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
    public Page<Orden> findOrdenesByPageable(Pageable pageable, Integer codigoOrdenTipo) {
        return ordenDAO.findOrdenesByPageable(pageable, codigoOrdenTipo);
    }

    @Transactional
    public Page<Orden> findOrdenesBySearch(String search, Pageable pageable, Integer codigoOrdenTipo) {
        return ordenDAO.findOrdenesBySearch(search, pageable, codigoOrdenTipo);
    }

    @Transactional
    public List<OrdenAlarmaDTO> findAlarmaOrdenes() {
        return ordenDAO.findAlarmaOrdenes();
    }

    @Transactional
    public List<OrdenTipo> finAllOrdenTipo() {
        return ordenDAO.finAllOrdenTipo();
    }

    @Transactional
    public OrdenTipo findOrdenTipoByCodigo(Integer codigo) {
        return ordenDAO.findOrdenTipoByCodigo(codigo);
    }

    @Transactional
    public void deleteOrdenProfesional(Integer ordenId) {
        ordenDAO.deleteOrdenProfesional(ordenId);
    }

}
