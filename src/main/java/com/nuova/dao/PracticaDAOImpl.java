package com.nuova.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.nuova.model.Nomenclador;

@Repository
public class PracticaDAOImpl implements PracticaDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(Nomenclador practica) {
        this.sessionFactory.getCurrentSession().save(practica);
    }

    public Nomenclador findPracticaById(Integer practicaId) {
        return (Nomenclador) this.sessionFactory.
                getCurrentSession().get(Nomenclador.class, practicaId);
    }

    @SuppressWarnings("unchecked")
    public List<Nomenclador> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("FROM Nomenclador").list();
    }

    public void edit(Nomenclador practica) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(practica);
    }

    public void deletePractica(Integer nomencladorId) {
        this.sessionFactory.getCurrentSession().
                createQuery(" DELETE FROM Nomenclador p "
                        + " WHERE p.nomencladorId = :nomencladorId ").
                setInteger("nomencladorId", nomencladorId).
                executeUpdate();
    }

    public Page<Nomenclador> findPracticaByPageable(Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(
                "FROM Nomenclador p ORDER BY p.nomencladorId DESC");
        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Nomenclador> result = query.list();
        return new PageImpl<Nomenclador>(result, pageable, result.size());
    }

    public Page<Nomenclador> findPracticaBySearch(String search, Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Nomenclador p "
                        + " WHERE upper(p.nombre) LIKE '%" + search.toUpperCase() + "%' "
                        + " OR upper(p.codigo) LIKE '%" + search.toUpperCase() + "%' "
                        + " ORDER BY p.codigo ");
        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        List<Nomenclador> result = query.list();
        return new PageImpl<Nomenclador>(result, pageable, result.size());
    }

}
