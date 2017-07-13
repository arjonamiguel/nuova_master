package com.nuova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.PracticaDAO;
import com.nuova.dto.InformacionSesiones;
import com.nuova.model.Nomenclador;
import com.nuova.model.NomencladorTipo;

@Service
public class PracticaManagerImpl implements PracticaManager {
  @Autowired
  PracticaDAO practicaDAO;

  @Transactional
  public void add(Nomenclador practica) {
    practicaDAO.add(practica);
  }

  @Transactional
  public Nomenclador findPracticaById(Integer practicaId) {
    return practicaDAO.findPracticaById(practicaId);
  }

  @Transactional
  public List<Nomenclador> findAll() {
    return practicaDAO.findAll();
  }

  @Transactional
  public void edit(Nomenclador practica) {
    practicaDAO.edit(practica);
  }

  @Transactional
  public void deletePractica(Integer practicaId) {
    practicaDAO.deletePractica(practicaId);
  }

  @Transactional
  public Page<Nomenclador> findPracticaByPageable(Pageable pageable) {
    return practicaDAO.findPracticaByPageable(pageable);
  }

  @Transactional
  public Page<Nomenclador> findPracticaBySearch(String search, Pageable pageable) {
    return practicaDAO.findPracticaBySearch(search, pageable);
  }

  @Transactional
  public List<Nomenclador> findNomencladorAutocomplete(String search) {
    return practicaDAO.findNomencladorAutocomplete(search);
  }

  @Transactional
  public List<NomencladorTipo> findNomecladorTipo() {
    return practicaDAO.findNomecladorTipo();
  }

  @Transactional
  public List<Nomenclador> findNomencladorWithSesiones() {
    return practicaDAO.findNomencladorWithSesiones();
  }

  @Transactional
  public InformacionSesiones getInfoSesiones(Integer nomencladorId, Integer pacienteId) {
    return practicaDAO.getInfoSesiones(nomencladorId, pacienteId);
  }
}
