package com.nuova.service;

import com.nuova.dto.GridOrdenPracticaDTO;
import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.CajaOrden;
import com.nuova.model.Orden;
import com.nuova.model.OrdenDocument;
import com.nuova.model.OrdenFueraCartilla;
import com.nuova.model.OrdenPractica;
import com.nuova.model.OrdenProfesional;
import com.nuova.model.OrdenTipo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrdenManager {
  public void add(Orden orden);

  public Orden findOrdenById(Integer ordenId);

  public List<Orden> findAll();

  public void delete(Integer ordenId);

  public void deleteOrdenPractica(Integer id);

  public void edit(Orden orden);

  public Page<GridOrdenPracticaDTO> findOrdenesByPageable(Pageable pageable,
      Integer codigoOrdenTipo);

  public Page<GridOrdenPracticaDTO> findOrdenesBySearch(Integer typeSearch, Integer codigoOrdenTipo,
      Integer ordenId, String paciente, Pageable pageable);

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

  public void add(OrdenFueraCartilla ofc);

  public void deleteOrdenPrestador(Integer ordenId);

  public CajaOrden findCajaOrdenByOrdenId(Orden orden);

  public OrdenFueraCartilla findOrdenFueraCartilla(Integer ordenId);

  public void deleteOrdenFueraCartilla(Integer id);

  public void editFueraCartilla(OrdenFueraCartilla ofc);

  public OrdenProfesional getOrdenProfesional(Integer ordenId);

  public OrdenFueraCartilla getOrdenFueraCartilla(Integer ordenId);

  public List<OrdenPractica> getAllOrdenPracticaByOrden(Integer ordenId, Integer nomencladorId);


}
