package com.nuova.dao;

import com.nuova.model.Paciente;

import java.util.Date;
import java.util.List;

public interface ReportDAO {

  public List<Paciente> getAfiliadosAtendidos(Date fechaDesde, Date fechaHasta,
      Integer especialidadId);

  public List<Paciente> getPacientesRegistrados(Date fechaDesde, Date fechaHasta);

  public List<Paciente> getAfiliadosSinCoseguro();

  public List<Paciente> getAfiliadosSinCobertura();
}
