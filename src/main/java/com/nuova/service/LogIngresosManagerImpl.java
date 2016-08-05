package com.nuova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.LogIngresosDAO;
import com.nuova.model.LogIngresos;

@Service
@Transactional
public class LogIngresosManagerImpl implements LogIngresosManager {

	@Autowired
	LogIngresosDAO logIngresosDAO;
	
	public void add(LogIngresos log) {
		logIngresosDAO.add(log);
		
	}

}
