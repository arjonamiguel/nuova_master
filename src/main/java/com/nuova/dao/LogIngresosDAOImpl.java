package com.nuova.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nuova.model.LogIngresos;

@Repository
public class LogIngresosDAOImpl implements LogIngresosDAO{
	 @Autowired
	    private SessionFactory sessionFactory;
	 
	public void add(LogIngresos log) {
		this.sessionFactory.getCurrentSession().save(log);
		
	}

}
