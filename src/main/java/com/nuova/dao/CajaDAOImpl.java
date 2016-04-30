package com.nuova.dao;

import com.nuova.model.Caja;
import com.nuova.model.CajaCierre;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class CajaDAOImpl implements CajaDAO {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void add(Caja caja) {
    this.sessionFactory.getCurrentSession().save(caja);
  }

  @Override
  public Caja findCajaById(Integer cajaId) {
    return (Caja) this.sessionFactory.getCurrentSession().get(Caja.class, cajaId);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Caja> findAll() {
    return this.sessionFactory.getCurrentSession().createQuery("FROM Caja c ORDER BY c.fecha ASC")
        .list();
  }

  @Override
  public void delete(Integer cajaId) {
    this.sessionFactory.getCurrentSession()
        .createQuery(" DELETE FROM Caja c WHERE c.cajaId = :cajaId ").setInteger("cajaId", cajaId)
        .executeUpdate();
  }

  @Override
  public void edit(Caja caja) {
    this.sessionFactory.getCurrentSession().update(caja);
  }

  @Override
  public Page<Caja> findCajaByPageable(Pageable pageable) {
    Query query =
        this.sessionFactory.getCurrentSession().createQuery("FROM Caja c ORDER BY c.cajaId DESC");
    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    List<Caja> result = query.list();
    return new PageImpl<Caja>(result, pageable, result.size());
  }

  @Override
  public Page<Caja> findCajaBySearch(String search, Pageable pageable) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM Caja c " + " WHERE upper(c.concepto) LIKE '%" + search.toUpperCase()
            + "%' " + " ORDER BY c.fecha ASC");

    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    List<Caja> result = query.list();
    return new PageImpl<Caja>(result, pageable, result.size());
  }

  @Override
  public List<Caja> findAllByfecha(Date fecha) {
    Query query = this.sessionFactory.getCurrentSession().createQuery(
        "FROM Caja c " + " WHERE CAST(c.fecha as date) = :fecha" + " ORDER BY c.cajaId")

        .setDate("fecha", fecha);

    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    List<Caja> result = query.list();

    return result;
  }

  @Override
  public void addCajaCierre(CajaCierre cierre) {
    this.sessionFactory.getCurrentSession().save(cierre);
  }

  @Override
  public Page<CajaCierre> findCajaCierreByPageable(Pageable pageable) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM CajaCierre c ORDER BY c.fechaCierre DESC");
    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    List<CajaCierre> result = query.list();
    return new PageImpl<CajaCierre>(result, pageable, result.size());
  }

  @Override
  public Page<CajaCierre> findCajaCierreBySearch(String search, Pageable pageable) {
    Query query = this.sessionFactory.getCurrentSession().createQuery(
        "FROM CajaCierre c " + " WHERE c.fechaCierre=" + search + " ORDER BY c.fechaCierre ASC");

    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    List<CajaCierre> result = query.list();
    return new PageImpl<CajaCierre>(result, pageable, result.size());
  }

  @Override
  public CajaCierre findCajaCierreByFecha(Date fecha) {
    CajaCierre retorno = null;
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM CajaCierre c " + " WHERE c.fechaCierre= :fecha ")
        .setDate("fecha", fecha);

    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    List<CajaCierre> result = query.list();


    if (result != null && !result.isEmpty()) {
      retorno = result.get(0);
    }

    return retorno;
  }

}
