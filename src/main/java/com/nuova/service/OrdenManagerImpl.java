package com.nuova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.OrdenDAO;
import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.CajaOrden;
import com.nuova.model.Orden;
import com.nuova.model.OrdenDocument;
import com.nuova.model.OrdenFueraCartilla;
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
    public Page<Orden> findOrdenesByPageable(Pageable pageable, Integer codigoOrdenTipo) {
        return ordenDAO.findOrdenesByPageable(pageable, codigoOrdenTipo);
    }

    @Override
    public Page<Orden> findOrdenesBySearch(String search, Pageable pageable,
            Integer codigoOrdenTipo) {
        return ordenDAO.findOrdenesBySearch(search, pageable, codigoOrdenTipo);
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
    public Page<Orden> findConsultasByPageableANDPaciente(Pageable pageable, Integer pacienteId) {
        return ordenDAO.findConsultasByPageableANDPaciente(pageable, pacienteId);
    }

    @Override
    public Page<Orden> findPracticasByPageableANDPaciente(Pageable pageable, Integer pacienteId) {
        return ordenDAO.findPracticasByPageableANDPaciente(pageable, pacienteId);
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

}
