package com.nuova.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nuova.model.Profesional;

@Repository
public class ProfesionalDAOImpl implements ProfesionalDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(Profesional profesional) {
        // TODO Auto-generated method stub

    }

    @SuppressWarnings("unchecked")
    public List<Profesional> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("FROM Profesional").list();
    }

    public void delete(Integer id) {
        // TODO Auto-generated method stub

    }

}
