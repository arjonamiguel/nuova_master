package com.nuova.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Paciente;

@Repository
public class PacienteDAOImpl implements PacienteDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(Paciente paciente) {
        this.sessionFactory.getCurrentSession().save(paciente);
        // for (PacienteObrasocial po : paciente.getPacienteObrasocials()) {
        // this.sessionFactory.getCurrentSession().save(po);
        // }
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
        // for (PacienteObrasocial po : paciente.getPacienteObrasocials()) {
        // this.sessionFactory.getCurrentSession().saveOrUpdate(po);
        // }
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

    public Page<Paciente> findPacientesByPageable(Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession().createQuery("FROM Paciente p "
                + " WHERE p.eliminado = 0"
                + " ORDER BY p.pacienteId DESC");
        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Paciente> result = query.list();
        return new PageImpl<Paciente>(result, pageable, result.size());
    }

    public Page<Paciente> findPacientesBySearch(String search, Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Paciente p "
                        + " WHERE  p.eliminado = 0 AND "
                        + " upper(p.apellido) LIKE '%" + search.toUpperCase() + "%' "
                        + " OR upper(p.nombre) LIKE '%" + search.toUpperCase() + "%' "
                        + " ORDER BY p.apellido ASC");
        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Paciente> result = query.list();
        return new PageImpl<Paciente>(result, pageable, result.size());
    }

    @SuppressWarnings("unchecked")
    public OrdenAlarmaDTO countPacientes() {
        return (OrdenAlarmaDTO) this.sessionFactory.getCurrentSession()
                .createQuery(" SELECT NEW com.nuova.dto.OrdenAlarmaDTO(COUNT(*)) "
                        + " FROM Paciente p ").list().get(0);
    }

    public List<Paciente> findPacienteAutocomplete(String search) {
        Query query = this.sessionFactory.getCurrentSession().createQuery("FROM Paciente p "
                + " WHERE upper(p.apellido) LIKE '%" + search.toUpperCase() + "%'"
                + " ORDER BY p.apellido ASC");
        // query.setFirstResult(pageable.getOffset());
        query.setMaxResults(20);
        List<Paciente> result = query.list();
        return result;
    }

    public Paciente findPacienteByDni(Integer dni) {
        Paciente retorno = null;
        @SuppressWarnings("unchecked")
        List<Paciente> result = this.sessionFactory.getCurrentSession()
                .createQuery(" SELECT p "
                        + " FROM Paciente p "
                        + " WHERE p.dni = " + dni).list();

        if (result != null && !result.isEmpty()) {
            retorno = result.get(0);
        }

        return retorno;
    }

}
