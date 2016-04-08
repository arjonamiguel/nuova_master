package com.nuova.dao;

import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Profesional;
import com.nuova.model.ProfesionalEspecialidad;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfesionalDAOImpl implements ProfesionalDAO {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void add(Profesional profesional) {
    this.sessionFactory.getCurrentSession().save(profesional);
    for (ProfesionalEspecialidad pe : profesional.getProfesionalEspecialidads()) {
      this.sessionFactory.getCurrentSession().save(pe);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Profesional> findAll() {
    return this.sessionFactory.getCurrentSession()
        .createQuery("FROM Profesional p " + " WHERE p.eliminado = 0 " + " ORDER BY p.apellido ASC")
        .list();
  }

  @Override
  public void delete(Integer profesionalId) {
    this.sessionFactory.getCurrentSession()
        .createQuery(" DELETE FROM Profesional p " + " WHERE p.profesionalId = :profesionalId ")
        .setInteger("profesionalId", profesionalId).executeUpdate();
  }

  @Override
  public Profesional findProfesionalById(Integer profesionalId) {
    return (Profesional) this.sessionFactory.getCurrentSession().get(Profesional.class,
        profesionalId);
  }

  @Override
  public void edit(Profesional profesional) {

    for (ProfesionalEspecialidad pe : profesional.getProfesionalEspecialidads()) {
      this.sessionFactory.getCurrentSession().saveOrUpdate(pe);
    }
    this.sessionFactory.getCurrentSession().saveOrUpdate(profesional);

  }

  @Override
  public void deleteProfesionalEspecialidad(Integer profesionalId) {
    this.sessionFactory.getCurrentSession()
        .createQuery(" DELETE FROM ProfesionalEspecialidad pe "
            + " WHERE pe.profesional.profesionalId = :profesionalId ")
        .setInteger("profesionalId", profesionalId).executeUpdate();

  }

  @Override
  public Page<Profesional> findProfesionalesByPageable(Pageable pageable) {
    Query query = this.sessionFactory.getCurrentSession().createQuery(
        " FROM Profesional p " + " WHERE p.eliminado = 0" + " ORDER BY p.profesionalId DESC");
    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    List<Profesional> result = query.list();
    return new PageImpl<Profesional>(result, pageable, result.size());
  }

  @Override
  public Page<Profesional> findProfesionalesBySearch(String search, Pageable pageable) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM Profesional p " + " WHERE " + " p.eliminado = 0 AND"
            + " upper(p.apellido) LIKE '%" + search.toUpperCase() + "%' "
            + " OR upper(p.nombre) LIKE '%" + search.toUpperCase() + "%' "
            + " ORDER BY p.apellido ");
    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    List<Profesional> result = query.list();
    return new PageImpl<Profesional>(result, pageable, result.size());
  }

  @Override
  @SuppressWarnings("unchecked")
  public OrdenAlarmaDTO countProfesionales() {
    return (OrdenAlarmaDTO) this.sessionFactory.getCurrentSession()
        .createQuery(" SELECT NEW com.nuova.dto.OrdenAlarmaDTO(COUNT(*)) " + " FROM Profesional p ")
        .list().get(0);
  }
}
