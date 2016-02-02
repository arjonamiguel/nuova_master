package com.nuova.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.nuova.model.Practica;

@Repository
public class PracticaDAOImpl implements PracticaDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(Practica practica) {
        this.sessionFactory.getCurrentSession().save(practica);
    }

    public Practica findPracticaById(Integer practicaId) {
        return (Practica) this.sessionFactory.
                getCurrentSession().get(Practica.class, practicaId);
    }

    @SuppressWarnings("unchecked")
    public List<Practica> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("FROM Practica").list();
    }

    public void edit(Practica practica) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(practica);
    }

    public void deletePractica(Integer practicaId) {
        this.sessionFactory.getCurrentSession().
                createQuery(" DELETE FROM Practica p "
                        + " WHERE p.practicaId = :practicaId ").
                setInteger("practicaId", practicaId).
                executeUpdate();
    }

    public Page<Practica> findPracticaByPageable(Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(
                "FROM Practica p ORDER BY p.practicaId DESC");
        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Practica> result = query.list();
        return new PageImpl<Practica>(result, pageable, result.size());
    }

    public Page<Practica> findPracticaBySearch(String search, Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Practica p "
                        + " WHERE upper(p.nombre) LIKE '%" + search.toUpperCase() + "%' "
                        + " OR upper(p.codigo) LIKE '%" + search.toUpperCase() + "%' "
                        + " ORDER BY p.codigo ");
        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Practica> result = query.list();
        return new PageImpl<Practica>(result, pageable, result.size());
    }

}
