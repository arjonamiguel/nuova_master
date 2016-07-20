package com.nuova.dao;

import com.nuova.dto.GridOrdenPracticaDTO;
import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.CajaOrden;
import com.nuova.model.Orden;
import com.nuova.model.OrdenDocument;
import com.nuova.model.OrdenFueraCartilla;
import com.nuova.model.OrdenProfesional;
import com.nuova.model.OrdenTipo;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
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


  public void add(Orden orden) {
    this.sessionFactory.getCurrentSession().save(orden);
  }


  public Orden findOrdenById(Integer ordenId) {
    return (Orden) this.sessionFactory.getCurrentSession().get(Orden.class, ordenId);
  }


  @SuppressWarnings("unchecked")
  public List<Orden> findAll() {
    return this.sessionFactory.getCurrentSession().createQuery("FROM Orden").list();
  }


  public void delete(Integer ordenId) {
    this.sessionFactory.getCurrentSession()
        .createQuery(" DELETE FROM Orden o WHERE o.ordenId = :ordenId ")
        .setInteger("ordenId", ordenId).executeUpdate();

  }


  public void edit(Orden orden) {
    this.sessionFactory.getCurrentSession().saveOrUpdate(orden);
  }


  public void deleteOrdenPractica(Integer ordenId) {
    this.sessionFactory.getCurrentSession()
        .createQuery(" DELETE FROM OrdenPractica o WHERE o.orden.ordenId = :ordenId ")
        .setInteger("ordenId", ordenId).executeUpdate();

  }


  public Page<GridOrdenPracticaDTO> findOrdenesByPageable(Pageable pageable,
      Integer codigoOrdenTipo) {


    // Query query = this.sessionFactory.getCurrentSession().createQuery("FROM Orden o "
    // + " WHERE o.ordenTipo.codigo=" + codigoOrdenTipo + " ORDER BY o.ordenId DESC");

    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());

    // Query query = this.sessionFactory.getCurrentSession().createQuery(
    // " SELECT new com.nuova.dto.OrdenDTO(o.ordenId, concat(p.apellido,' ',p.nombre), ot.nombre,
    // o.fecha, o.fueraCartilla, concat(op.profesional.apellido, ' ', op.profesional.nombre )) FROM
    // Orden o "
    // + " JOIN o.paciente p JOIN o.ordenTipo ot JOIN o.ordenProfesional op "
    // + " WHERE o.ordenTipo.codigo=" + codigoOrdenTipo + " ORDER BY o.ordenId DESC");
    // query.setMaxResults(20);
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery("CALL zp_getAllPracticas(:codigoOrdenTipo);")
        .setInteger("codigoOrdenTipo", codigoOrdenTipo)
        .setResultTransformer(Transformers.aliasToBean(GridOrdenPracticaDTO.class));
    List<GridOrdenPracticaDTO> result = query.list();

    return new PageImpl<GridOrdenPracticaDTO>(result, pageable, result.size());
  }


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


  @SuppressWarnings("unchecked")
  public List<OrdenAlarmaDTO> findAlarmaOrdenes() {
    return this.sessionFactory.getCurrentSession()
        .createQuery(" SELECT NEW com.nuova.dto.OrdenAlarmaDTO(COUNT(o.estado) , o.estado) "
            + " FROM Orden o " + " WHERE o.ordenTipo.codigo in (101,102)" + " GROUP BY o.estado")
        .list();
  }


  @SuppressWarnings("unchecked")
  public List<OrdenTipo> finAllOrdenTipo() {
    return this.sessionFactory.getCurrentSession().createQuery("FROM OrdenTipo").list();
  }


  public OrdenTipo findOrdenTipoByCodigo(Integer codigo) {
    return (OrdenTipo) this.sessionFactory.getCurrentSession()
        .createQuery(" SELECT ot " + " FROM OrdenTipo ot " + " WHERE ot.codigo = " + codigo).list()
        .get(0);
  }


  public void deleteOrdenProfesional(Integer ordenId) {
    this.sessionFactory.getCurrentSession()
        .createQuery(" DELETE FROM OrdenProfesional o WHERE o.orden.ordenId = :ordenId ")
        .setInteger("ordenId", ordenId).executeUpdate();
  }


  public OrdenTipo findOrdenTipoById(Integer id) {
    return (OrdenTipo) this.sessionFactory.getCurrentSession().get(OrdenTipo.class, id);
  }

  // Orden Document

  public void add(OrdenDocument document) {
    this.sessionFactory.getCurrentSession().save(document);
  }


  public OrdenDocument findOrdenDocumentById(Integer documentId) {
    return (OrdenDocument) this.sessionFactory.getCurrentSession().get(OrdenDocument.class,
        documentId);
  }


  public void deleteOrdenDocument(Integer documentId) {
    this.sessionFactory.getCurrentSession()
        .createQuery(" DELETE FROM OrdenDocument o WHERE o.documentId = :documentId ")
        .setInteger("documentId", documentId).executeUpdate();
  }

  @SuppressWarnings("unchecked")

  public List<OrdenDocument> finAllOrdenDocumentByOrdenId(Integer ordenId) {
    return this.sessionFactory.getCurrentSession()
        .createQuery("FROM OrdenDocument od " + " WHERE od.ordenId = " + ordenId).list();
  }


  public Page<Orden> findConsultasByPageableANDPaciente(Pageable pageable, Integer pacienteId) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM Orden o WHERE o.ordenTipo.codigo= 100 AND o.paciente.pacienteId = "
            + pacienteId + " ORDER BY o.ordenId DESC");
    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    List<Orden> result = query.list();
    return new PageImpl<Orden>(result, pageable, result.size());

  }


  public Page<Orden> findPracticasByPageableANDPaciente(Pageable pageable, Integer pacienteId) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM Orden o WHERE o.ordenTipo.codigo=102  AND o.paciente.pacienteId = "
            + pacienteId + " ORDER BY o.ordenId DESC");
    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    List<Orden> result = query.list();
    return new PageImpl<Orden>(result, pageable, result.size());
  }


  public void add(OrdenFueraCartilla ofc) {
    this.sessionFactory.getCurrentSession().save(ofc);

  }


  public void deleteOrdenPrestador(Integer ordenId) {
    this.sessionFactory.getCurrentSession()
        .createQuery(" DELETE FROM OrdenPrestador o WHERE o.orden.ordenId = :ordenId ")
        .setInteger("ordenId", ordenId).executeUpdate();

  }


  public CajaOrden findCajaOrdenByOrdenId(Orden orden) {
    return (CajaOrden) this.sessionFactory.getCurrentSession().get(CajaOrden.class, orden);
  }

  public OrdenFueraCartilla findOrdenFueraCartilla(Integer ordenId) {
    OrdenFueraCartilla retorno = null;
    @SuppressWarnings("unchecked")
    List<OrdenFueraCartilla> result = this.sessionFactory.getCurrentSession()
        .createQuery(" SELECT p " + " FROM OrdenFueraCartilla p " + " WHERE p.ordenId = " + ordenId)
        .list();

    if (result != null && !result.isEmpty()) {
      retorno = result.get(0);
    }

    return retorno;

  }

  public void deleteOrdenFueraCartilla(Integer id) {
    this.sessionFactory.getCurrentSession()
        .createQuery(" DELETE FROM OrdenFueraCartilla o WHERE o.id = :id ").setInteger("id", id)
        .executeUpdate();
  }

  public void editFueraCartilla(OrdenFueraCartilla ofc) {
    this.sessionFactory.getCurrentSession().saveOrUpdate(ofc);
  }


  public OrdenProfesional getOrdenProfesional(Integer ordenId) {
    // TODO Auto-generated method stub
    return (OrdenProfesional) this.sessionFactory.getCurrentSession().get(OrdenProfesional.class,
        ordenId);
  }


  public OrdenFueraCartilla getOrdenFueraCartilla(Integer ordenId) {
    // TODO Auto-generated method stub
    return (OrdenFueraCartilla) this.sessionFactory.getCurrentSession()
        .get(OrdenFueraCartilla.class, ordenId);
  }

}
