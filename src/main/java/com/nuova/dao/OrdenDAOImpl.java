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
import com.nuova.model.Orden;
import com.nuova.model.OrdenTipo;

@Repository
public class OrdenDAOImpl implements OrdenDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(Orden orden) {
        this.sessionFactory.getCurrentSession().save(orden);
    }

    public Orden findOrdenById(Integer ordenId) {
        return (Orden) this.sessionFactory.
                getCurrentSession().get(Orden.class, ordenId);
    }

    @SuppressWarnings("unchecked")
    public List<Orden> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("FROM Orden").list();
    }

    public void delete(Integer ordenId) {
        this.sessionFactory.getCurrentSession().
                createQuery(" DELETE FROM Orden o WHERE o.ordenId = :ordenId ").
                setInteger("ordenId", ordenId).
                executeUpdate();

    }

    public void edit(Orden orden) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(orden);
    }

    public void deleteOrdenPractica(Integer ordenId) {
        this.sessionFactory.getCurrentSession().
                createQuery(" DELETE FROM OrdenPractica o WHERE o.orden.ordenId = :ordenId ").
                setInteger("ordenId", ordenId).
                executeUpdate();

    }

    public Page<Orden> findOrdenesByPageable(Pageable pageable, Integer codigoOrdenTipo) {
        Query query = this.sessionFactory.getCurrentSession().createQuery("FROM Orden o "
                + " WHERE o.ordenTipo.codigo=" + codigoOrdenTipo
                + " ORDER BY o.ordenId DESC");
        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Orden> result = query.list();
        return new PageImpl<Orden>(result, pageable, result.size());
    }

    public Page<Orden> findOrdenesBySearch(String search, Pageable pageable, Integer codigoOrdenTipo) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Orden o "
                        + " WHERE o.ordenTipo.codigo=" + codigoOrdenTipo
                        + " AND CAST(o.ordenId AS string) LIKE '%" + search.toUpperCase() + "%' "
                        + " ORDER BY o.ordenId ASC");

        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Orden> result = query.list();
        return new PageImpl<Orden>(result, pageable, result.size());
    }

    @SuppressWarnings("unchecked")
    public List<OrdenAlarmaDTO> findAlarmaOrdenes() {
        return this.sessionFactory.getCurrentSession()
                .createQuery(" SELECT NEW com.nuova.dto.OrdenAlarmaDTO(COUNT(o.estado) , o.estado) "
                        + " FROM Orden o "
                        + " GROUP BY o.estado").list();
    }

    @SuppressWarnings("unchecked")
    public List<OrdenTipo> finAllOrdenTipo() {
        return this.sessionFactory.getCurrentSession().createQuery("FROM OrdenTipo").list();
    }

    public OrdenTipo findOrdenTipoByCodigo(Integer codigo) {
        return (OrdenTipo) this.sessionFactory.getCurrentSession()
                .createQuery(" SELECT ot "
                        + " FROM OrdenTipo ot "
                        + " WHERE ot.codigo = " + codigo
                ).list().get(0);
    }

}
