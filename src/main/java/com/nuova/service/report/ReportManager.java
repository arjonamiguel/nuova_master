package com.nuova.service.report;

import java.util.Date;
import java.util.List;

import com.nuova.dto.report.ReporteDetalleOrdenConsultaDto;
import com.nuova.model.Paciente;

public interface ReportManager {

  public List<Paciente> getAfiliadosAtendidos(Date fechaDesde, Date fechaHasta,
      Integer especialidadId, Integer profesionalId);

  public List<Paciente> getPacientesRegistrados(Date fechaDesde, Date fechaHasta);

  public List<Paciente> getAfiliadosSinCoseguro();

  public List<Paciente> getAfiliadosSinCobertura();

  public List<Paciente> getFiltroAfiliado(Date fechaDesdeAfiliado, Date fechaHastaAfiliado,
      Date fechaNacimiento, Integer localidadId, String zonaAfiliacion);

  public List<ReporteDetalleOrdenConsultaDto> getOrdenesPorTipoYFecha(Integer tipoOrden,
      Date fechaDesde, Date fechaHasta);

}
