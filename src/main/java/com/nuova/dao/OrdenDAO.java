package com.nuova.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Orden;
import com.nuova.model.OrdenTipo;

public interface OrdenDAO {
    public void add(Orden orden);

    public Orden findOrdenById(Integer ordenId);

    public List<Orden> findAll();

    public void delete(Integer ordenId);

    public void deleteOrdenPractica(Integer ordenId);

    public void edit(Orden orden);

    public Page<Orden> findOrdenesByPageable(Pageable pageable, Integer codigoOrdenTipo);

    public Page<Orden> findOrdenesBySearch(String search, Pageable pageable, Integer codigoOrdenTipo);

    public List<OrdenAlarmaDTO> findAlarmaOrdenes();

    public List<OrdenTipo> finAllOrdenTipo();

    public OrdenTipo findOrdenTipoByCodigo(Integer codigo);

}
