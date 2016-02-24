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

@Repository
public class PrestadoresDAOImpl implements PrestadoresDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(Prestadores preador) {
        this.sessionFactory.getCurrentSession().save(preador);
    }

    public Prestadores findPrestadorById(Integer prestadorId) {
        return (Prestadores) this.sessionFactory.
                getCurrentSession().get(Prestadores.class, prestadorId);
    }

    @SuppressWarnings("unchecked")
    public List<Prestadores> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("FROM Prestadores p ORDER BY p.nombre ASC").list();
    }

    public void delete(Integer prestadorId) {
        this.sessionFactory.getCurrentSession().
                createQuery(" DELETE FROM Prestadores p WHERE p.prestadorId = :prestadorId ").
                setInteger("prestadorId", prestadorId).
                executeUpdate();
    }

    public void edit(Prestadores prestador) {
        this.sessionFactory.getCurrentSession().update(prestador);
    }

    @SuppressWarnings("unchecked")
    public Page<Prestadores> findPrestadoresByPageable(Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(
                "FROM Prestadores p ORDER BY p.prestadorId DESC");
        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Prestadores> result = query.list();
        return new PageImpl<Prestadores>(result, pageable, result.size());
    }

    @SuppressWarnings("unchecked")
    public Page<Prestadores> findPrestadoresBySearch(String search, Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Prestadores p "
                        + " WHERE upper(p.nombre) LIKE '%" + search.toUpperCase() + "%' "
                        + " ORDER BY p.nombre ASC");

        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Prestadores> result = query.list();
        return new PageImpl<Prestadores>(result, pageable, result.size());
    }

}
