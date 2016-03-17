package com.nuova.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nuova.model.Calendario;
import com.nuova.model.Obrasocial;

@Repository
public class CalendarDAOImpl implements CalendarDAO {
    @Autowired
    private SessionFactory sessionFactory;

	public List<Calendario> getCalendar() {
	
		return this.sessionFactory.getCurrentSession().createQuery("FROM Calendario c ORDER BY c.calendarioId ASC").list();
	}

	public void add(Calendario calendario) {
		this.sessionFactory.getCurrentSession().save(calendario);
	}

	public Calendario findCalendarioById(Integer calendarioId) {
	       return (Calendario) this.sessionFactory.getCurrentSession().get(Calendario.class, calendarioId);
		
	}

	public void delete(Integer calendarioId) {
		this.sessionFactory.getCurrentSession().
        createQuery(" DELETE FROM Calendario c WHERE c.calendarioId = :calendarioId ").
        setInteger("calendarioId", calendarioId).
        executeUpdate();
		
	}
}
