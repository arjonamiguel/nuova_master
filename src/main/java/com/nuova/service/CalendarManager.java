package com.nuova.service;

import java.util.Calendar;
import java.util.List;

import com.nuova.model.Calendario;


public interface CalendarManager {
	
	public List<Calendario> getCalendar();
	public void add(Calendario calendario);
	public Calendario findCalendarioById(Integer calendarioId);
	public void delete(Integer id);
}
