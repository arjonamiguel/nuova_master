package com.nuova.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nuova.model.Especialidad;

@Repository
public class EspecialidadDAOImpl implements EspecialidadDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(Especialidad especialidad) {
        this.sessionFactory.getCurrentSession().save(especialidad);
    }

    @SuppressWarnings("unchecked")
    public List<Especialidad> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("FROM Especialidad").list();
    }

    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

    public Especialidad findEspecialidadById(Integer especialidadId) {
        return (Especialidad) this.sessionFactory.
                getCurrentSession().get(Especialidad.class, especialidadId);
    }

}
