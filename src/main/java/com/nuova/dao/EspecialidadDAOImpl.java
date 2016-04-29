package com.nuova.dao;

import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Especialidad;
import com.nuova.model.Profesional;
import com.nuova.model.ProfesionalEspecialidad;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EspecialidadDAOImpl implements EspecialidadDAO {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void add(Especialidad especialidad) {
    this.sessionFactory.getCurrentSession().save(especialidad);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Especialidad> findAll() {
    return this.sessionFactory.getCurrentSession()
        .createQuery("FROM Especialidad e ORDER BY e.nombre ASC").list();
  }

  @Override
  public void delete(Integer especialidadId) {
    this.sessionFactory.getCurrentSession()
        .createQuery(" DELETE FROM Especialidad e WHERE e.especialidadId = :especialidadId ")
        .setInteger("especialidadId", especialidadId).executeUpdate();

  }

  @Override
  public Especialidad findEspecialidadById(Integer especialidadId) {
    return (Especialidad) this.sessionFactory.getCurrentSession().get(Especialidad.class,
        especialidadId);
  }

  @Override
  public void edit(Especialidad especialidad) {
    this.sessionFactory.getCurrentSession().update(especialidad);

  }

  @Override
  public Page<Especialidad> findEspecialidadesByPageable(Pageable pageable) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM Especialidad e ORDER BY e.especialidadId DESC");
    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    List<Especialidad> result = query.list();
    return new PageImpl<Especialidad>(result, pageable, result.size());
  }

  @Override
  public Page<Especialidad> findEspecialidadesBySearch(String search, Pageable pageable) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM Especialidad e " + " WHERE upper(e.nombre) LIKE '%"
            + search.toUpperCase() + "%' " + " ORDER BY e.nombre ASC");

    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    List<Especialidad> result = query.list();
    return new PageImpl<Especialidad>(result, pageable, result.size());
  }

  @Override
  @SuppressWarnings("unchecked")
  public OrdenAlarmaDTO countEspecialidades() {
    return (OrdenAlarmaDTO) this.sessionFactory.getCurrentSession()
        .createQuery(
            " SELECT NEW com.nuova.dto.OrdenAlarmaDTO(COUNT(*)) " + " FROM Especialidad e ")
        .list().get(0);
  }

  @Override
  public List<Profesional> findProfesionalByEspecialidadId(Integer especialidadId) {
    Query query =
        this.sessionFactory.getCurrentSession().createQuery("FROM ProfesionalEspecialidad e "
            + " WHERE e.especialidad.especialidadId = " + especialidadId);
    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    List<ProfesionalEspecialidad> result = query.list();

    List<Profesional> profesionales = new ArrayList<Profesional>();
    for (ProfesionalEspecialidad pe : result) {
      Profesional p = new Profesional();
      p.setProfesionalId(pe.getProfesional().getProfesionalId());
      p.setApellido(pe.getProfesional().getApellido());
      p.setNombre(pe.getProfesional().getNombre());

      profesionales.add(p);
    }

    return profesionales;
  }
}
