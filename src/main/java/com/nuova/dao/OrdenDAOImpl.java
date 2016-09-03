package com.nuova.dao;

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
    Orden o = (Orden) this.sessionFactory.getCurrentSession()
        .createQuery(" SELECT o FROM Orden o WHERE o.ordenId = :ordenId")
        .setInteger("ordenId", ordenId).list().get(0);

    Orden o2 = null;
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery("CALL zp_getOrdenById(:ordenId);").setInteger("ordenId", ordenId)
        .setResultTransformer(Transformers.aliasToBean(Orden.class));
    List<Orden> result = query.list();

    o2 = result.isEmpty() ? null : result.get(0);

    o.setPacienteId(o2.getPacienteId());

    return o;
  }


  @SuppressWarnings("unchecked")
  public List<Orden> findAll() {
    return this.sessionFactory.getCurrentSession().createQuery("FROM Orden").list();
  }


  public void delete(Integer ordenId) {
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery("CALL zp_deleteOrden(:ordenId);").setInteger("ordenId", ordenId);
    query.executeUpdate();
    // this.sessionFactory.getCurrentSession()
    // .createQuery(" DELETE FROM Orden o WHERE o.ordenId = :ordenId ")
    // .setInteger("ordenId", ordenId).executeUpdate();

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
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery("CALL zp_getGridAllPracticas(:codigoOrdenTipo);")
        .setInteger("codigoOrdenTipo", codigoOrdenTipo)
        .setResultTransformer(Transformers.aliasToBean(GridOrdenPracticaDTO.class));
    List<GridOrdenPracticaDTO> result = query.list();

    return new PageImpl<GridOrdenPracticaDTO>(result, pageable, result.size());
  }


  public Page<GridOrdenPracticaDTO> findOrdenesBySearch(Integer typeSearch, Integer codigoOrdenTipo,
      Integer ordenId, String paciente, Pageable pageable) {
    // Query query = this.sessionFactory.getCurrentSession()
    // .createQuery("FROM Orden o " + " WHERE o.ordenTipo.codigo=" + codigoOrdenTipo
    // + " AND CAST(o.ordenId AS string) LIKE '%" + search.toUpperCase() + "%' "
    // + " ORDER BY o.ordenId ASC");

    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());

    // query.setMaxResults(20);
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery(
            "CALL zp_getGridAllPracticasBySearch(:typeSearch,:codigoOrdenTipo,:ordenId, :paciente);")
        .setInteger("typeSearch", typeSearch).setInteger("codigoOrdenTipo", codigoOrdenTipo)
        .setInteger("ordenId", ordenId).setString("paciente", paciente)
        .setResultTransformer(Transformers.aliasToBean(GridOrdenPracticaDTO.class));
    List<GridOrdenPracticaDTO> result = query.list();

    return new PageImpl<GridOrdenPracticaDTO>(result, pageable, result.size());

    // List<Orden> result = query.list();
    // return new PageImpl<Orden>(result, pageable, result.size());
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
    // return (OrdenTipo) this.sessionFactory.getCurrentSession().get(OrdenTipo.class, id);
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery("CALL zp_getOrdenTipoById(:ordenId);").setInteger("ordenId", id)
        .setResultTransformer(Transformers.aliasToBean(OrdenTipo.class));
    List<OrdenTipo> result = query.list();

    return result.isEmpty() ? null : result.get(0);
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


  public Page<GridOrdenPracticaDTO> findConsultasByPageableANDPaciente(Pageable pageable,
      Integer pacienteId, Integer tipo) {
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery("CALL zp_getGridAllConsultas(:pacienteId, :tipo);")
        .setInteger("pacienteId", pacienteId).setInteger("tipo", tipo)
        .setResultTransformer(Transformers.aliasToBean(GridOrdenPracticaDTO.class));
    List<GridOrdenPracticaDTO> result = query.list();

    return new PageImpl<GridOrdenPracticaDTO>(result, pageable, result.size());
  }


  public Page<GridOrdenPracticaDTO> findPracticasByPageableANDPaciente(Pageable pageable,
      Integer pacienteId, Integer tipo) {
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery("CALL zp_getGridAllConsultas(:pacienteId, :tipo);")
        .setInteger("pacienteId", pacienteId).setInteger("tipo", tipo)
        .setResultTransformer(Transformers.aliasToBean(GridOrdenPracticaDTO.class));
    List<GridOrdenPracticaDTO> result = query.list();

    return new PageImpl<GridOrdenPracticaDTO>(result, pageable, result.size());
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

  public List<OrdenPractica> getAllOrdenPracticaByOrden(Integer ordenId, Integer nomencladorId) {
    return this.sessionFactory.getCurrentSession()
        .createQuery("FROM OrdenPractica od " + " WHERE od.orden.ordenId = " + ordenId
            + " and od.nomenclador.nomencladorId=" + nomencladorId)
        .list();
  }


  public List<PracticasListDTO> getAllPracticasByOrden(Integer ordenId) {
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery("CALL zp_getPracticasByOrden(:ordenId);").setInteger("ordenId", ordenId)
        .setResultTransformer(Transformers.aliasToBean(PracticasListDTO.class));
    List<PracticasListDTO> result = query.list();

    return result;
  }


  public Page<HistoriaClinicaDTO> findHistoriaClinica(Pageable pageable, Integer pacienteId) {
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery("CALL zp_getGridAllHistoriaClinica(:pacienteId);")
        .setInteger("pacienteId", pacienteId)
        .setResultTransformer(Transformers.aliasToBean(HistoriaClinicaDTO.class));
    List<HistoriaClinicaDTO> result = query.list();

    return new PageImpl<HistoriaClinicaDTO>(result, pageable, result.size());
  }


  public List<OrdenPractica> getAllOrdenPracticaByOrden(Integer ordenId) {
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery("CALL zp_getAllOrdenPracticaByOrden(:ordenId);")
        .setInteger("ordenId", ordenId)
        .setResultTransformer(Transformers.aliasToBean(OrdenPractica.class));
    List<OrdenPractica> result = query.list();

    return result;
    // return this.sessionFactory.getCurrentSession()
    // .createQuery(" SELECT o FROM OrdenPractica o WHERE o.ordenId = :ordenId")
    // .setInteger("ordenId", ordenId).list();

  }


  public List<OrdenDocument> getAllOrdenDocumentByOrden(Integer ordenId) {
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery("CALL zp_getAllOrdenDocumentByOrden(:ordenId);")
        .setInteger("ordenId", ordenId)
        .setResultTransformer(Transformers.aliasToBean(OrdenDocument.class));
    List<OrdenDocument> result = query.list();

    return result;
  }


}
