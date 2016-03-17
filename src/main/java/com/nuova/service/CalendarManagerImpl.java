package com.nuova.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.CalendarDAO;
import com.nuova.model.Calendario;

@Service
@Transactional
public class CalendarManagerImpl implements CalendarManager {

	@Autowired
	CalendarDAO calendarDAO;
	public List<Calendario> getCalendar() {

		return calendarDAO.getCalendar();
	}
	public void add(Calendario calendario) {
		calendarDAO.add(calendario);
		
	}
	public Calendario findCalendarioById(Integer calendarioId) {
		return calendarDAO.findCalendarioById(calendarioId);
	}
	public void delete(Integer id) {
		calendarDAO.delete(id);
		
	}	
}
