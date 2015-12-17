package com.nuova.dao;

import java.util.List;

import com.nuova.model.Obrasocial;

public interface ObraSocialDAO {

    public void add(Obrasocial obrasocial);

    public Obrasocial findObraSocialById(Integer obrasocialId);

    public List<Obrasocial> findAll();

    public void delete(Integer obrasocialId);

    public void edit(Obrasocial obrasocial);

}
