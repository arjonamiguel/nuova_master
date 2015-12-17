package com.nuova.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nuova.model.Profesional;
import com.nuova.model.ProfesionalEspecialidad;

@Repository
public class ProfesionalDAOImpl implements ProfesionalDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(Profesional profesional) {
        this.sessionFactory.getCurrentSession().save(profesional);
        for (ProfesionalEspecialidad pe : profesional.getProfesionalEspecialidads()) {
            this.sessionFactory.getCurrentSession().save(pe);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Profesional> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("FROM Profesional").list();
    }

    public void delete(Integer profesionalId) {
        this.sessionFactory.getCurrentSession().
                createQuery(" DELETE FROM Profesional p "
                        + " WHERE p.profesionalId = :profesionalId ").
                setInteger("profesionalId", profesionalId).
                executeUpdate();
    }

    public Profesional findProfesionalById(Integer profesionalId) {
        return (Profesional) this.sessionFactory.
                getCurrentSession().get(Profesional.class, profesionalId);
    }

    public void edit(Profesional profesional) {

        for (ProfesionalEspecialidad pe : profesional.getProfesionalEspecialidads()) {
            this.sessionFactory.getCurrentSession().saveOrUpdate(pe);
        }
        this.sessionFactory.getCurrentSession().saveOrUpdate(profesional);

    }

    public void deleteProfesionalEspecialidad(Integer profesionalId) {
        this.sessionFactory
                .getCurrentSession().
                createQuery(" DELETE FROM ProfesionalEspecialidad pe "
                        + " WHERE pe.profesional.profesionalId = :profesionalId ").
                setInteger("profesionalId", profesionalId).
                executeUpdate();

    }

}
