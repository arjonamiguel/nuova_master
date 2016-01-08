package com.nuova.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nuova.model.Orden;

@Repository
public class OrdenDAOImpl implements OrdenDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(Orden orden) {
        this.sessionFactory.getCurrentSession().save(orden);
    }

    public Orden findOrdenById(Integer ordenId) {
        return (Orden) this.sessionFactory.
                getCurrentSession().get(Orden.class, ordenId);
    }

    public List<Orden> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("FROM Orden").list();
    }

    public void delete(Integer ordenId) {
        this.sessionFactory.getCurrentSession().
                createQuery(" DELETE FROM Orden o WHERE o.ordenId = :ordenId ").
                setInteger("ordenId", ordenId).
                executeUpdate();

    }

    public void edit(Orden orden) {
        this.sessionFactory.getCurrentSession().update(orden);
    }

}
