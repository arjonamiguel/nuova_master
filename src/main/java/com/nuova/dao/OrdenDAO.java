package com.nuova.dao;

import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Orden;
import com.nuova.model.OrdenDocument;
import com.nuova.model.OrdenTipo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

  public void deleteOrdenProfesional(Integer ordenId);

  public OrdenTipo findOrdenTipoById(Integer id);

  // Orden Document
  public void add(OrdenDocument document);

  public OrdenDocument findOrdenDocumentById(Integer documentId);

  public void deleteOrdenDocument(Integer docuementId);

  public List<OrdenDocument> finAllOrdenDocumentByOrdenId(Integer ordenId);

  public Page<Orden> findConsultasByPageableANDPaciente(Pageable pageable, Integer pacienteId);

  public Page<Orden> findPracticasByPageableANDPaciente(Pageable pageable, Integer pacienteId);

}
