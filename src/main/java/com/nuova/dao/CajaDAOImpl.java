package com.nuova.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.nuova.model.Caja;

@Repository
public class CajaDAOImpl implements CajaDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(Caja caja) {
        this.sessionFactory.getCurrentSession().save(caja);
    }

    public Caja findCajaById(Integer cajaId) {
        return (Caja) this.sessionFactory.
                getCurrentSession().get(Caja.class, cajaId);
    }

    public List<Caja> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("FROM Caja c ORDER BY c.fecha ASC").list();
    }

    public void delete(Integer cajaId) {
        this.sessionFactory.getCurrentSession().
                createQuery(" DELETE FROM Caja c WHERE c.cajaId = :cajaId ").
                setInteger("cajaId", cajaId).
                executeUpdate();
    }

    public void edit(Caja caja) {
        this.sessionFactory.getCurrentSession().update(caja);
    }

    public Page<Caja> findCajaByPageable(Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(
                "FROM Caja c ORDER BY c.cajaId DESC");
        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Caja> result = query.list();
        return new PageImpl<Caja>(result, pageable, result.size());
    }

    public Page<Caja> findCajaBySearch(String search, Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Caja c "
                        + " WHERE upper(c.concepto) LIKE '%" + search.toUpperCase() + "%' "
                        + " ORDER BY c.fecha ASC");

        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Caja> result = query.list();
        return new PageImpl<Caja>(result, pageable, result.size());
    }

    public List<Caja> findAllByfecha(Date fecha) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Caja c "
                        + " WHERE CAST(c.fecha as date) = :fecha"
                        + " ORDER BY c.cajaId")

                .setDate("fecha", fecha);

        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Caja> result = query.list();

        return result;
    }

}
