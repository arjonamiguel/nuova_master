package com.nuova.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nuova.dto.InformacionSesiones;
import com.nuova.model.Nomenclador;
import com.nuova.model.NomencladorTipo;

public interface PracticaManager {
  public void add(Nomenclador practica);

  public Nomenclador findPracticaById(Integer practicaId);

  public List<Nomenclador> findAll();

  public void edit(Nomenclador practica);

  public void deletePractica(Integer practicaId);

  public Page<Nomenclador> findPracticaByPageable(Pageable pageable);

  public Page<Nomenclador> findPracticaBySearch(String search, Pageable pageable);

  public List<Nomenclador> findNomencladorAutocomplete(String search);

  public List<NomencladorTipo> findNomecladorTipo();

  public List<Nomenclador> findNomencladorWithSesiones();

  public InformacionSesiones getInfoSesiones(Integer nomencladorId, Integer pacienteId);
}
