package com.nuova.service.report;

import com.nuova.dao.ReportDAO;
import com.nuova.model.Paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ReportManagerImpl implements ReportManager {
  @Autowired
  private ReportDAO reportDAO;

  @Override
  public List<Paciente> getAfiliadosAtendidos(Date fechaDesde, Date fechaHasta,
      Integer especialidadId) {
    return reportDAO.getAfiliadosAtendidos(fechaDesde, fechaHasta, especialidadId);
  }

  @Override
  public List<Paciente> getPacientesRegistrados(Date fechaDesde, Date fechaHasta) {
    return reportDAO.getPacientesRegistrados(fechaDesde, fechaHasta);
  }

  @Override
  public List<Paciente> getAfiliadosSinCoseguro() {
    return reportDAO.getAfiliadosSinCoseguro();
  }

  @Override
  public List<Paciente> getAfiliadosSinCobertura() {
    return reportDAO.getAfiliadosSinCobertura();
  }

}
