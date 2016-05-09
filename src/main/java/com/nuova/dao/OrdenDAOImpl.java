package com.nuova.dao;

import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Orden;
import com.nuova.model.OrdenDocument;
import com.nuova.model.OrdenFueraCartilla;
import com.nuova.model.OrdenTipo;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdenDAOImpl implements OrdenDAO {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void add(Orden orden) {
    this.sessionFactory.getCurrentSession().save(orden);
  }

  @Override
  public Orden findOrdenById(Integer ordenId) {
    return (Orden) this.sessionFactory.getCurrentSession().get(Orden.class, ordenId);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Orden> findAll() {
    return this.sessionFactory.getCurrentSession().createQuery("FROM Orden").list();
  }

  @Override
  public void delete(Integer ordenId) {
    this.sessionFactory.getCurrentSession()
        .createQuery(" DELETE FROM Orden o WHERE o.ordenId = :ordenId ")
        .setInteger("ordenId", ordenId).executeUpdate();

  }

  @Override
  public void edit(Orden orden) {
    this.sessionFactory.getCurrentSession().saveOrUpdate(orden);
  }

  @Override
  public void deleteOrdenPractica(Integer ordenId) {
    this.sessionFactory.getCurrentSession()
        .createQuery(" DELETE FROM OrdenPractica o WHERE o.orden.ordenId = :ordenId ")
        .setInteger("ordenId", ordenId).executeUpdate();

  }

  @Override
  public Page<Orden> findOrdenesByPageable(Pageable pageable, Integer codigoOrdenTipo) {
    Query query = this.sessionFactory.getCurrentSession().createQuery("FROM Orden o "
        + " WHERE o.ordenTipo.codigo=" + codigoOrdenTipo + " ORDER BY o.ordenId DESC");
    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    query.setMaxResults(20);
    List<Orden> result = query.list();
    return new PageImpl<Orden>(result, pageable, result.size());
  }

  @Override
  public Page<Orden> findOrdenesBySearch(String search, Pageable pageable,
      Integer codigoOrdenTipo) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM Orden o " + " WHERE o.ordenTipo.codigo=" + codigoOrdenTipo
            + " AND CAST(o.ordenId AS string) LIKE '%" + search.toUpperCase() + "%' "
            + " ORDER BY o.ordenId ASC");

    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    query.setMaxResults(20);
    List<Orden> result = query.list();
    return new PageImpl<Orden>(result, pageable, result.size());
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<OrdenAlarmaDTO> findAlarmaOrdenes() {
    return this.sessionFactory.getCurrentSession()
        .createQuery(" SELECT NEW com.nuova.dto.OrdenAlarmaDTO(COUNT(o.estado) , o.estado) "
            + " FROM Orden o " + " WHERE o.ordenTipo.codigo in (101,102)" + " GROUP BY o.estado")
        .list();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<OrdenTipo> finAllOrdenTipo() {
    return this.sessionFactory.getCurrentSession().createQuery("FROM OrdenTipo").list();
  }

  @Override
  public OrdenTipo findOrdenTipoByCodigo(Integer codigo) {
    return (OrdenTipo) this.sessionFactory.getCurrentSession()
        .createQuery(" SELECT ot " + " FROM OrdenTipo ot " + " WHERE ot.codigo = " + codigo).list()
        .get(0);
  }

  @Override
  public void deleteOrdenProfesional(Integer ordenId) {
    this.sessionFactory.getCurrentSession()
        .createQuery(" DELETE FROM OrdenProfesional o WHERE o.orden.ordenId = :ordenId ")
        .setInteger("ordenId", ordenId).executeUpdate();
  }

  @Override
  public OrdenTipo findOrdenTipoById(Integer id) {
    return (OrdenTipo) this.sessionFactory.getCurrentSession().get(OrdenTipo.class, id);
  }

  // Orden Document
  @Override
  public void add(OrdenDocument document) {
    this.sessionFactory.getCurrentSession().save(document);
  }

  @Override
  public OrdenDocument findOrdenDocumentById(Integer documentId) {
    return (OrdenDocument) this.sessionFactory.getCurrentSession().get(OrdenDocument.class,
        documentId);
  }

  @Override
  public void deleteOrdenDocument(Integer documentId) {
    this.sessionFactory.getCurrentSession()
        .createQuery(" DELETE FROM OrdenDocument o WHERE o.documentId = :documentId ")
        .setInteger("documentId", documentId).executeUpdate();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<OrdenDocument> finAllOrdenDocumentByOrdenId(Integer ordenId) {
    return this.sessionFactory.getCurrentSession()
        .createQuery("FROM OrdenDocument od " + " WHERE od.ordenId = " + ordenId).list();
  }

  @Override
  public Page<Orden> findConsultasByPageableANDPaciente(Pageable pageable, Integer pacienteId) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM Orden o WHERE o.ordenTipo.codigo= 100 AND o.paciente.pacienteId = "
            + pacienteId + " ORDER BY o.ordenId DESC");
    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    List<Orden> result = query.list();
    return new PageImpl<Orden>(result, pageable, result.size());

  }

  @Override
  public Page<Orden> findPracticasByPageableANDPaciente(Pageable pageable, Integer pacienteId) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM Orden o WHERE o.ordenTipo.codigo=102  AND o.paciente.pacienteId = "
            + pacienteId + " ORDER BY o.ordenId DESC");
    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    List<Orden> result = query.list();
    return new PageImpl<Orden>(result, pageable, result.size());
  }

  @Override
  public void add(OrdenFueraCartilla ofc) {
    this.sessionFactory.getCurrentSession().save(ofc);

  }

}
