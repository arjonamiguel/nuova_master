package com.nuova.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Obrasocial;

public interface ObraSocialManager {

    public void add(Obrasocial obrasocial);

    public Obrasocial findObraSocialById(Integer obrasocialId);

    public List<Obrasocial> findAll();

    public void delete(Integer obrasocialId);

    public void edit(Obrasocial obrasocial);

    public Page<Obrasocial> findObrasocialesByPageable(Pageable pageable);

    public Page<Obrasocial> findObrasocialesBySearch(String search, Pageable pageable);

    public OrdenAlarmaDTO countObrasociales();

}
