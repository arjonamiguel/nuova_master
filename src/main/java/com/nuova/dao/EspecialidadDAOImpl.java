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

    public void delete(Integer especialidadId) {
        this.sessionFactory.getCurrentSession().
                createQuery(" DELETE FROM Especialidad e WHERE e.especialidadId = :especialidadId ").
                setInteger("especialidadId", especialidadId).
                executeUpdate();

    }

    public Especialidad findEspecialidadById(Integer especialidadId) {
        return (Especialidad) this.sessionFactory.
                getCurrentSession().get(Especialidad.class, especialidadId);
    }

    public void edit(Especialidad especialidad) {
        this.sessionFactory.getCurrentSession().update(especialidad);

    }

    public Page<Especialidad> findEspecialidadesByPageable(Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession().createQuery("FROM Especialidad");
        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Especialidad> result = query.list();
        return new PageImpl<Especialidad>(result, pageable, result.size());
    }

    public Page<Especialidad> findEspecialidadesBySearch(String search, Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Especialidad e "
                        + " WHERE upper(e.nombre) LIKE '%" + search.toUpperCase() + "%' ");

        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Especialidad> result = query.list();
        return new PageImpl<Especialidad>(result, pageable, result.size());
    }

    @SuppressWarnings("unchecked")
    public OrdenAlarmaDTO countEspecialidades() {
        return (OrdenAlarmaDTO) this.sessionFactory.getCurrentSession()
                .createQuery(" SELECT NEW com.nuova.dto.OrdenAlarmaDTO(COUNT(*)) "
                        + " FROM Especialidad e ").list().get(0);
    }
}
