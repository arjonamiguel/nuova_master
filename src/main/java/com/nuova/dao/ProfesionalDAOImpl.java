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
        return this.sessionFactory.getCurrentSession().createQuery("FROM Profesional p ORDER BY p.apellido ASC")
                .list();
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

    public Page<Profesional> findProfesionalesByPageable(Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(
                "FROM Profesional p ORDER BY p.profesionalId DESC");
        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Profesional> result = query.list();
        return new PageImpl<Profesional>(result, pageable, result.size());
    }

    public Page<Profesional> findProfesionalesBySearch(String search, Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Profesional p "
                        + " WHERE upper(p.apellido) LIKE '%" + search.toUpperCase() + "%' "
                        + " OR upper(p.nombre) LIKE '%" + search.toUpperCase() + "%' "
                        + " ORDER BY p.apellido ");
        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Profesional> result = query.list();
        return new PageImpl<Profesional>(result, pageable, result.size());
    }

    @SuppressWarnings("unchecked")
    public OrdenAlarmaDTO countProfesionales() {
        return (OrdenAlarmaDTO) this.sessionFactory.getCurrentSession()
                .createQuery(" SELECT NEW com.nuova.dto.OrdenAlarmaDTO(COUNT(*)) "
                        + " FROM Profesional p ").list().get(0);
    }
}
