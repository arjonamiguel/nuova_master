package com.nuova.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nuova.model.Obrasocial;

@Repository
public class ObraSocialDAOImpl implements ObraSocialDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(Obrasocial obrasocial) {
        this.sessionFactory.getCurrentSession().save(obrasocial);
    }

    public Obrasocial findObraSocialById(Integer obrasocialId) {
        return (Obrasocial) this.sessionFactory.
                getCurrentSession().get(Obrasocial.class, obrasocialId);
    }

    @SuppressWarnings("unchecked")
    public List<Obrasocial> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("FROM Obrasocial").list();
    }

    public void delete(Integer obrasocialId) {
        this.sessionFactory.getCurrentSession().
                createQuery(" DELETE FROM Obrasocial o WHERE o.obrasocialId = :obrasocialId ").
                setInteger("obrasocialId", obrasocialId).
                executeUpdate();
    }

    public void edit(Obrasocial obrasocial) {
        this.sessionFactory.getCurrentSession().update(obrasocial);
    }

}
