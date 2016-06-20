package com.nuova.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.nuova.model.Prestadores;
import com.nuova.model.PrestadoresEspecialidad;

@Repository
public class PrestadoresDAOImpl implements PrestadoresDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Prestadores prestador) {
        this.sessionFactory.getCurrentSession().save(prestador);
        for (PrestadoresEspecialidad pe : prestador.getPrestadoresEspecialidads()) {
            this.sessionFactory.getCurrentSession().save(pe);
        }
    }

    @Override
    public Prestadores findPrestadorById(Integer prestadorId) {
        return (Prestadores) this.sessionFactory.getCurrentSession().get(Prestadores.class,
                prestadorId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Prestadores> findAll() {
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM Prestadores p ORDER BY p.nombre ASC").list();
    }

    @Override
    public void delete(Integer prestadorId) {
        this.sessionFactory.getCurrentSession()
                .createQuery(" DELETE FROM Prestadores p WHERE p.prestadorId = :prestadorId ")
                .setInteger("prestadorId", prestadorId).executeUpdate();
    }

    @Override
    public void edit(Prestadores prestador) {
        for (PrestadoresEspecialidad pe : prestador.getPrestadoresEspecialidads()) {
            this.sessionFactory.getCurrentSession().saveOrUpdate(pe);
        }
        this.sessionFactory.getCurrentSession().saveOrUpdate(prestador);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page<Prestadores> findPrestadoresByPageable(Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Prestadores p WHERE p.eliminado = 0 ORDER BY p.prestadorId DESC");
        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Prestadores> result = query.list();
        return new PageImpl<Prestadores>(result, pageable, result.size());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page<Prestadores> findPrestadoresBySearch(String search, Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Prestadores p " + " WHERE upper(p.nombre) LIKE '%" + search.toUpperCase()
                        + "%' " + " AND p.eliminado=0 ORDER BY p.nombre ASC");

        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Prestadores> result = query.list();
        return new PageImpl<Prestadores>(result, pageable, result.size());
    }

    @Override
    public void deletePrestadorEspecialidad(Integer prestadorId) {
        this.sessionFactory.getCurrentSession()
                .createQuery(" DELETE FROM PrestadoresEspecialidad pe "
                        + " WHERE pe.prestadores.prestadorId = :prestadorId ")
                .setInteger("prestadorId", prestadorId).executeUpdate();

    }

}
