package com.nuova.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.OrdenDAO;
import com.nuova.dto.GridOrdenPracticaDTO;
import com.nuova.dto.HistoriaClinicaDTO;
import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.dto.PracticasListDTO;
import com.nuova.model.CajaOrden;
import com.nuova.model.Orden;
import com.nuova.model.OrdenDocument;
import com.nuova.model.OrdenFueraCartilla;
import com.nuova.model.OrdenPractica;
import com.nuova.model.OrdenProfesional;
import com.nuova.model.OrdenTipo;

@Transactional
@Service
public class OrdenManagerImpl implements OrdenManager {
  @Autowired
  OrdenDAO ordenDAO;


  @Override
  public void add(Orden orden) {
    ordenDAO.add(orden);
  }


  @Override
  public Orden findOrdenById(Integer ordenId) {
    return ordenDAO.findOrdenById(ordenId);
  }

  @Override
  public List<Orden> findAll() {
    return ordenDAO.findAll();
  }


  @Override
  public void delete(Integer ordenId) {
    ordenDAO.delete(ordenId);
  }


  @Override
  public void edit(Orden orden) {
    ordenDAO.edit(orden);
  }


  @Override
  public void deleteOrdenPractica(Integer id) {
    ordenDAO.deleteOrdenPractica(id);
  }


  @Override
  public Page<GridOrdenPracticaDTO> findOrdenesByPageable(Pageable pageable,
      Integer codigoOrdenTipo) {
    return ordenDAO.findOrdenesByPageable(pageable, codigoOrdenTipo);
  }

  @Override
  public Page<GridOrdenPracticaDTO> findOrdenesBySearch(Integer typeSearch, Integer codigoOrdenTipo,
      Integer ordenId, String paciente, Pageable pageable) {
    return ordenDAO.findOrdenesBySearch(typeSearch, codigoOrdenTipo, ordenId, paciente, pageable);
  }

  @Override
  public List<OrdenAlarmaDTO> findAlarmaOrdenes() {
    return ordenDAO.findAlarmaOrdenes();
  }


  @Override
  public List<OrdenTipo> finAllOrdenTipo() {
    return ordenDAO.finAllOrdenTipo();
  }

  @Override
  public OrdenTipo findOrdenTipoByCodigo(Integer codigo) {
    return ordenDAO.findOrdenTipoByCodigo(codigo);
  }

  @Override
  public void deleteOrdenProfesional(Integer ordenId) {
    ordenDAO.deleteOrdenProfesional(ordenId);
  }


  @Override
  public OrdenTipo findOrdenTipoById(Integer id) {
    return ordenDAO.findOrdenTipoById(id);
  }


  @Override
  public void add(OrdenDocument document) {
    ordenDAO.add(document);
  }


  @Override
  public OrdenDocument findOrdenDocumentById(Integer documentId) {
    return ordenDAO.findOrdenDocumentById(documentId);
  }


  @Override
  public void deleteOrdenDocument(Integer docuementId) {
    ordenDAO.deleteOrdenDocument(docuementId);
  }


  @Override
  public List<OrdenDocument> finAllOrdenDocumentByOrdenId(Integer ordenId) {
    return ordenDAO.finAllOrdenDocumentByOrdenId(ordenId);
  }


  @Override
  public Page<GridOrdenPracticaDTO> findConsultasByPageableANDPaciente(Pageable pageable,
      Integer pacienteId, Integer tipo) {
    return ordenDAO.findConsultasByPageableANDPaciente(pageable, pacienteId, tipo);
  }


  @Override
  public Page<GridOrdenPracticaDTO> findPracticasByPageableANDPaciente(Pageable pageable,
      Integer pacienteId, Integer tipo) {
    return ordenDAO.findPracticasByPageableANDPaciente(pageable, pacienteId, tipo);
  }


  @Override
  public void add(OrdenFueraCartilla ofc) {
    ordenDAO.add(ofc);
  }


  @Override
  public void deleteOrdenPrestador(Integer ordenId) {
    ordenDAO.deleteOrdenPrestador(ordenId);
  }


  @Override
  public CajaOrden findCajaOrdenByOrdenId(Orden orden) {
    return ordenDAO.findCajaOrdenByOrdenId(orden);
  }

  @Override
  public OrdenFueraCartilla findOrdenFueraCartilla(Integer ordenId) {
    return ordenDAO.findOrdenFueraCartilla(ordenId);
  }

  @Override
  public void deleteOrdenFueraCartilla(Integer id) {
    ordenDAO.deleteOrdenFueraCartilla(id);
  }

  @Override
  public void editFueraCartilla(OrdenFueraCartilla ofc) {
    ordenDAO.editFueraCartilla(ofc);
  }


  @Override
  public OrdenProfesional getOrdenProfesional(Integer ordenId) {
    return ordenDAO.getOrdenProfesional(ordenId);
  }


  @Override
  public OrdenFueraCartilla getOrdenFueraCartilla(Integer ordenId) {
    return ordenDAO.getOrdenFueraCartilla(ordenId);
  }

  @Override
  public List<OrdenPractica> getAllOrdenPracticaByOrden(Integer ordenId, Integer nomencladorId) {
    return ordenDAO.getAllOrdenPracticaByOrden(ordenId, nomencladorId);
  }

  @Override
  public List<PracticasListDTO> getAllPracticasByOrden(Integer ordenId) {
    return ordenDAO.getAllPracticasByOrden(ordenId);
  }

  @Override
  public Page<HistoriaClinicaDTO> findHistoriaClinica(Pageable pageable, Integer pacienteId) {
    return ordenDAO.findHistoriaClinica(pageable, pacienteId);
  }


  @Override
  public List<OrdenPractica> getAllOrdenPracticaByOrden(Integer ordenId) {

    return ordenDAO.getAllOrdenPracticaByOrden(ordenId);
  }


  @Override
  public List<OrdenDocument> getAllOrdenDocumentByOrden(Integer ordenId) {
    return ordenDAO.getAllOrdenDocumentByOrden(ordenId);
  }

  public void updateOrdenEntregada(Integer ordenEntregada, Integer ordenId,
      Date fechaOrdenEntregada) {
    ordenDAO.updateOrdenEntregada(ordenEntregada, ordenId, fechaOrdenEntregada);
  }


  @Override
  public String validarSesion(Integer nomencladorId, Integer pacienteId) {
    return ordenDAO.validarSesion(nomencladorId, pacienteId);
  }
}
