package com.nuova.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nuova.model.Paciente;
import com.nuova.model.PacienteObrasocial;

@Repository
public class PacienteDAOImpl implements PacienteDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(Paciente paciente) {
        this.sessionFactory.getCurrentSession().save(paciente);
        for (PacienteObrasocial po : paciente.getPacienteObrasocials()) {
            this.sessionFactory.getCurrentSession().save(po);
        }
    }

    public Paciente fin1dPacienteById(Integer pacienteId) {
        return (Paciente) this.sessionFactory.
                getCurrentSession().get(Paciente.class, pacienteId);
    }

    @SuppressWarnings("unchecked")
    public List<Paciente> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("FROM Paciente").list();
    }

    public void delete(Integer pacienteId) {
        this.sessionFactory.getCurrentSession().
                createQuery(" DELETE FROM Paciente p WHERE p.pacienteId = :pacienteId ").
                setInteger("pacienteId", pacienteId).
                executeUpdate();
    }

    public void edit(Paciente paciente) {
        for (PacienteObrasocial po : paciente.getPacienteObrasocials()) {
            this.sessionFactory.getCurrentSession().saveOrUpdate(po);
        }
        this.sessionFactory.getCurrentSession().saveOrUpdate(paciente);
    }

    public void deleteAdherente(Integer pacienteId) {
        this.sessionFactory
                .getCurrentSession().
                createQuery(" DELETE FROM PacienteObrasocial po "
                        + " WHERE po.paciente.pacienteId = :pacienteId ").
                setInteger("pacienteId", pacienteId).
                executeUpdate();
    }

    public void deletePacienteObrasocial(Integer pacienteId) {
        this.sessionFactory
                .getCurrentSession().
                createQuery(" DELETE FROM PacienteObrasocial po "
                        + " WHERE po.paciente.pacienteId = :pacienteId ").
                setInteger("pacienteId", pacienteId).
                executeUpdate();

    }

}
