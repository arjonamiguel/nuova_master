package com.nuova.dao;

import com.nuova.model.Nomenclador;
import com.nuova.model.NomencladorTipo;

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

  public void add(Nomenclador practica) {
    practica.setEliminado(0);
    this.sessionFactory.getCurrentSession().save(practica);
  }

  public Nomenclador findPracticaById(Integer practicaId) {
    return (Nomenclador) this.sessionFactory.getCurrentSession().get(Nomenclador.class, practicaId);
  }

  @SuppressWarnings("unchecked")
  public List<Nomenclador> findAll() {
    return this.sessionFactory.getCurrentSession().createQuery("FROM Nomenclador").list();
  }

  public void edit(Nomenclador practica) {
    this.sessionFactory.getCurrentSession().saveOrUpdate(practica);
  }

  public void deletePractica(Integer nomencladorId) {
    this.sessionFactory.getCurrentSession()
        .createQuery(" DELETE FROM Nomenclador p " + " WHERE p.nomencladorId = :nomencladorId ")
        .setInteger("nomencladorId", nomencladorId).executeUpdate();
  }

  public Page<Nomenclador> findPracticaByPageable(Pageable pageable) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM Nomenclador p where p.eliminado = 0 ORDER BY p.nomencladorId desc");
    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    query.setMaxResults(200);
    List<Nomenclador> result = query.list();
    return new PageImpl<Nomenclador>(result, pageable, result.size());
  }

  public Page<Nomenclador> findPracticaBySearch(String search, Pageable pageable) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM Nomenclador p " + " WHERE (upper(p.nombre) LIKE '%"
            + search.toUpperCase() + "%' " + " OR upper(p.codigo) LIKE '%" + search.toUpperCase()
            + "%' )" + "  and  p.eliminado = 0 ORDER BY p.codigo, p.nombre, p.tipo ");
    // query.setFirstResult(pageable.getOffset());
    // query.setMaxResults(pageable.getPageNumber());
    query.setMaxResults(200);
    List<Nomenclador> result = query.list();
    return new PageImpl<Nomenclador>(result, pageable, result.size());
  }

  public List<Nomenclador> findNomencladorAutocomplete(String search) {
    Query query = this.sessionFactory.getCurrentSession()
        .createQuery("FROM Nomenclador n " + " WHERE (n.codigo like '%" + search.toUpperCase()
            + "%' OR" + " upper(n.nombre) LIKE '%" + search.toUpperCase() + "%' )"
            + " AND n.tipo IN ('Practicas', 'Odontológico', 'Prótesis', 'Nom. Bioquimico Unic', 'Prácticas PMOe', 'Odontológico PMO', 'Codigos Fuera de PMO','MANUAL','Bioquímico PMOe')"
            + " and n.eliminado = 0 " + " ORDER BY n.codigo, n.nombre, n.tipo ASC");
    // query.setFirstResult(pageable.getOffset());
    query.setMaxResults(20);
    List<Nomenclador> result = query.list();
    return result;
  }

  public List<NomencladorTipo> findNomecladorTipo() {
    return this.sessionFactory.getCurrentSession().createQuery("FROM NomencladorTipo").list();
  }
}
