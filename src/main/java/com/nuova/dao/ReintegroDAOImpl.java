package com.nuova.dao;

import com.nuova.model.Reintegro;
import com.nuova.utils.Util;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ReintegroDAOImpl implements ReintegroDAO {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void add(Reintegro reintegro) {
    this.sessionFactory.getCurrentSession().save(reintegro);
  }

  @Override
  public Reintegro findReintegroById(Integer reintegroId) {
    return (Reintegro) this.sessionFactory.getCurrentSession().get(Reintegro.class, reintegroId);
  }

  @Override
  public void delete(Integer reintegroId) {
    this.sessionFactory.getCurrentSession()
        .createQuery(" DELETE FROM Reintegro e WHERE e.reintegroId = :reintegroId ")
        .setInteger("reintegroId", reintegroId).executeUpdate();

  }

  @Override
  public void edit(Reintegro reintegro) {
    this.sessionFactory.getCurrentSession().update(reintegro);
  }

  @Override
  public Page<Reintegro> findReintegrosByPageable(Pageable pageable) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM Reintegro e ORDER BY e.reintegroId DESC");
    // query.setFirstResult(pageable.getOffset());
    query.setMaxResults(20);
    List<Reintegro> result = query.list();
    return new PageImpl<Reintegro>(result, pageable, result.size());
  }

  @Override
  public Page<Reintegro> findReintegrosBySearch(String search, Pageable pageable) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM Reintegro r " + " WHERE r.fechaReintegro=" + Util.parseToDate(search));

    // query.setFirstResult(pageable.getOffset());
    query.setMaxResults(50);
    List<Reintegro> result = query.list();
    return new PageImpl<Reintegro>(result, pageable, result.size());

  }

  @Override
  public Page<Reintegro> findReintegrosByPacientePageable(Integer pacienteId, Pageable pageable) {
    Query query = this.sessionFactory.getCurrentSession().createQuery(
        "FROM Reintegro r " + " WHERE r.pacienteId =" + pacienteId + " ORDER BY r.reintegroId ASC");

    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    List<Reintegro> result = query.list();
    return new PageImpl<Reintegro>(result, pageable, result.size());
  }

}
