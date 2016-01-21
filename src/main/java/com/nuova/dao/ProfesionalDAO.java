package com.nuova.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nuova.model.Profesional;

public interface ProfesionalDAO {
    public void add(Profesional profesional);

    public Profesional findProfesionalById(Integer profesionalId);

    public List<Profesional> findAll();

    public void delete(Integer id);

    public void edit(Profesional profesional);

    public void deleteProfesionalEspecialidad(Integer profesionalId);

    public Page<Profesional> findProfesionalesByPageable(Pageable pageable);

    public Page<Profesional> findProfesionalesBySearch(String search, Pageable pageable);

}
