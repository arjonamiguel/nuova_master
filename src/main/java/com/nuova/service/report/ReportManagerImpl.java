package com.nuova.service.report;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.ReportDAO;
import com.nuova.dto.report.ReporteDetalleOrdenConsultaDto;
import com.nuova.model.Paciente;

@Service
@Transactional
public class ReportManagerImpl implements ReportManager {
  @Autowired
  private ReportDAO reportDAO;

  @Override
  public List<Paciente> getAfiliadosAtendidos(Date fechaDesde, Date fechaHasta,
      Integer especialidadId, Integer profesionalId) {
    return reportDAO.getAfiliadosAtendidos(fechaDesde, fechaHasta, especialidadId, profesionalId);
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

  @Override
  public List<Paciente> getFiltroAfiliado(Date fechaDesdeAfiliado, Date fechaHastaAfiliado,
      Date fechaNacimiento, Integer localidadId, String zonaAfiliacion) {
    return reportDAO.getFiltroAfiliado(fechaDesdeAfiliado, fechaHastaAfiliado, fechaNacimiento,
        localidadId, zonaAfiliacion);
  }

  @Override
  public List<ReporteDetalleOrdenConsultaDto> getOrdenesPorTipoYFecha(Integer tipoOrden,
      Date fechaDesde, Date fechaHasta) {
    return reportDAO.getOrdenesPorTipoYFecha(tipoOrden, fechaDesde, fechaHasta);
  }

}
