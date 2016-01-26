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
        return this.sessionFactory.getCurrentSession().createQuery("FROM Obrasocial o ORDER BY o.nombre ASC").list();
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

    public Page<Obrasocial> findObrasocialesByPageable(Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(
                "FROM Obrasocial o ORDER BY o.obrasocialId DESC");
        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Obrasocial> result = query.list();
        return new PageImpl<Obrasocial>(result, pageable, result.size());
    }

    public Page<Obrasocial> findObrasocialesBySearch(String search, Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Obrasocial o "
                        + " WHERE upper(o.nombre) LIKE '%" + search.toUpperCase() + "%' "
                        + " ORDER BY o.nombre ASC");

        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Obrasocial> result = query.list();
        return new PageImpl<Obrasocial>(result, pageable, result.size());
    }

    @SuppressWarnings("unchecked")
    public OrdenAlarmaDTO countObrasociales() {
        return (OrdenAlarmaDTO) this.sessionFactory.getCurrentSession()
                .createQuery(" SELECT NEW com.nuova.dto.OrdenAlarmaDTO(COUNT(*)) "
                        + " FROM Obrasocial o ").list().get(0);
    }

}
