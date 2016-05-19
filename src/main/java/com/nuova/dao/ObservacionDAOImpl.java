package com.nuova.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.nuova.model.Observaciones;
import com.nuova.model.Reintegro;

@Repository
public class ObservacionDAOImpl implements ObservacionDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(Observaciones observacion) {
        this.sessionFactory.getCurrentSession().save(observacion);

    }

    public Observaciones findObservacionesById(Integer observacionId) {
        return (Observaciones) this.sessionFactory.
                getCurrentSession().get(Observaciones.class, observacionId);
    }

    @SuppressWarnings("unchecked")
    public List<Observaciones> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("FROM Observaciones").list();
    }

    public void edit(Observaciones observacion) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(observacion);
    }

    public void deleteObservaciones(Integer observacionId) {
        this.sessionFactory
                .getCurrentSession().
                createQuery(" DELETE FROM Observaciones ob "
                        + " WHERE ob.observacionId = :observacionId ").
                setInteger("observacionId", observacionId).
                executeUpdate();
    }

}
