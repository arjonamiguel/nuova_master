package com.nuova.dao;


import java.util.List;

import com.nuova.model.Calendario;

public interface CalendarDAO {

	public List<Calendario> getCalendar();
	public void add(Calendario calendario);
	public Calendario findCalendarioById(Integer calendarioId);
	public void delete(Integer calendarioId);
}
