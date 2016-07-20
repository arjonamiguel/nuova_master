package com.nuova.service;

import com.nuova.dao.OrdenDAO;
import com.nuova.dto.GridOrdenPracticaDTO;
import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.CajaOrden;
import com.nuova.model.Orden;
import com.nuova.model.OrdenDocument;
import com.nuova.model.OrdenFueraCartilla;
import com.nuova.model.OrdenProfesional;
import com.nuova.model.OrdenTipo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class OrdenManagerImpl implements OrdenManager {
  @Autowired
  OrdenDAO ordenDAO;


  public void add(Orden orden) {
    ordenDAO.add(orden);
  }


  public Orden findOrdenById(Integer ordenId) {
    return ordenDAO.findOrdenById(ordenId);
  }


  public List<Orden> findAll() {
    return ordenDAO.findAll();
  }


  public void delete(Integer ordenId) {
    ordenDAO.delete(ordenId);
  }


  public void edit(Orden orden) {
    ordenDAO.edit(orden);
  }


  public void deleteOrdenPractica(Integer id) {
    ordenDAO.deleteOrdenPractica(id);
  }


  public Page<GridOrdenPracticaDTO> findOrdenesByPageable(Pageable pageable,
      Integer codigoOrdenTipo) {
    return ordenDAO.findOrdenesByPageable(pageable, codigoOrdenTipo);
  }


  public Page<Orden> findOrdenesBySearch(String search, Pageable pageable,
      Integer codigoOrdenTipo) {
    return ordenDAO.findOrdenesBySearch(search, pageable, codigoOrdenTipo);
  }


  public List<OrdenAlarmaDTO> findAlarmaOrdenes() {
    return ordenDAO.findAlarmaOrdenes();
  }


  public List<OrdenTipo> finAllOrdenTipo() {
    return ordenDAO.finAllOrdenTipo();
  }


  public OrdenTipo findOrdenTipoByCodigo(Integer codigo) {
    return ordenDAO.findOrdenTipoByCodigo(codigo);
  }


  public void deleteOrdenProfesional(Integer ordenId) {
    ordenDAO.deleteOrdenProfesional(ordenId);
  }


  public OrdenTipo findOrdenTipoById(Integer id) {
    return ordenDAO.findOrdenTipoById(id);
  }


  public void add(OrdenDocument document) {
    ordenDAO.add(document);
  }


  public OrdenDocument findOrdenDocumentById(Integer documentId) {
    return ordenDAO.findOrdenDocumentById(documentId);
  }


  public void deleteOrdenDocument(Integer docuementId) {
    ordenDAO.deleteOrdenDocument(docuementId);
  }


  public List<OrdenDocument> finAllOrdenDocumentByOrdenId(Integer ordenId) {
    return ordenDAO.finAllOrdenDocumentByOrdenId(ordenId);
  }


  public Page<Orden> findConsultasByPageableANDPaciente(Pageable pageable, Integer pacienteId) {
    return ordenDAO.findConsultasByPageableANDPaciente(pageable, pacienteId);
  }


  public Page<Orden> findPracticasByPageableANDPaciente(Pageable pageable, Integer pacienteId) {
    return ordenDAO.findPracticasByPageableANDPaciente(pageable, pacienteId);
  }


  public void add(OrdenFueraCartilla ofc) {
    ordenDAO.add(ofc);
  }


  public void deleteOrdenPrestador(Integer ordenId) {
    ordenDAO.deleteOrdenPrestador(ordenId);
  }


  public CajaOrden findCajaOrdenByOrdenId(Orden orden) {
    return ordenDAO.findCajaOrdenByOrdenId(orden);
  }

  public OrdenFueraCartilla findOrdenFueraCartilla(Integer ordenId) {
    return ordenDAO.findOrdenFueraCartilla(ordenId);
  }

  public void deleteOrdenFueraCartilla(Integer id) {
    ordenDAO.deleteOrdenFueraCartilla(id);
  }

  public void editFueraCartilla(OrdenFueraCartilla ofc) {
    ordenDAO.editFueraCartilla(ofc);
  }


  public OrdenProfesional getOrdenProfesional(Integer ordenId) {
    return ordenDAO.getOrdenProfesional(ordenId);
  }


  public OrdenFueraCartilla getOrdenFueraCartilla(Integer ordenId) {
    return ordenDAO.getOrdenFueraCartilla(ordenId);
  }



}
