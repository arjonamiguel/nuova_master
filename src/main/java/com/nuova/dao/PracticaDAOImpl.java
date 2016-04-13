package com.nuova.dao;

import com.nuova.model.Nomenclador;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PracticaDAOImpl implements PracticaDAO {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void add(Nomenclador practica) {
    this.sessionFactory.getCurrentSession().save(practica);
  }

  @Override
  public Nomenclador findPracticaById(Integer practicaId) {
    return (Nomenclador) this.sessionFactory.getCurrentSession().get(Nomenclador.class, practicaId);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Nomenclador> findAll() {
    return this.sessionFactory.getCurrentSession().createQuery("FROM Nomenclador").list();
  }

  @Override
  public void edit(Nomenclador practica) {
    this.sessionFactory.getCurrentSession().saveOrUpdate(practica);
  }

  @Override
  public void deletePractica(Integer nomencladorId) {
    this.sessionFactory.getCurrentSession()
        .createQuery(" DELETE FROM Nomenclador p " + " WHERE p.nomencladorId = :nomencladorId ")
        .setInteger("nomencladorId", nomencladorId).executeUpdate();
  }

  @Override
  public Page<Nomenclador> findPracticaByPageable(Pageable pageable) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM Nomenclador p ORDER BY p.codigo, p.nombre, p.tipo asc");
    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    query.setMaxResults(200);
    List<Nomenclador> result = query.list();
    return new PageImpl<Nomenclador>(result, pageable, result.size());
  }

  @Override
  public Page<Nomenclador> findPracticaBySearch(String search, Pageable pageable) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM Nomenclador p " + " WHERE upper(p.nombre) LIKE '%" + search.toUpperCase()
            + "%' " + " OR upper(p.codigo) LIKE '%" + search.toUpperCase() + "%' "
            + " AND p.tipo='Practicas'" + " ORDER BY p.codigo, p.nombre, p.tipo ");
    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    query.setMaxResults(200);
    List<Nomenclador> result = query.list();
    return new PageImpl<Nomenclador>(result, pageable, result.size());
  }

  @Override
  public List<Nomenclador> findNomencladorAutocomplete(String search) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM Nomenclador n " + " WHERE (n.codigo like '%" + search.toUpperCase()
            + "%' OR" + " upper(n.nombre) LIKE '%" + search.toUpperCase() + "%' )"
            + " AND n.tipo IN ('Practicas', 'Odontológico', 'Prótesis', 'Descartables', 'Prácticas PMOe', 'Odontológico PMO', 'Codigos Fuera de PMO','MANUAL')"
            + " ORDER BY n.codigo, n.nombre, n.tipo ASC");
    // query.setFirstResult(pageable.getOffset());
    query.setMaxResults(20);
    List<Nomenclador> result = query.list();
    return result;
  }
}
