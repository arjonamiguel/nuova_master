package com.nuova.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nuova.dto.report.ReporteDetalleOrdenConsultaDto;
import com.nuova.model.Paciente;

@Repository
public class ReportDAOImpl implements ReportDAO {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public List<Paciente> getAfiliadosAtendidos(Date fechaDesde, Date fechaHasta,
      Integer especialidadId, Integer profesionalId) {
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery(
            "CALL zp_getAllAfiliadosAtendidos(:fechaDesde, :fechaHasta, :especialidadId, :profesionalId);")
        .addEntity(Paciente.class).setParameter("fechaDesde", fechaDesde)
        .setParameter("fechaHasta", fechaHasta).setParameter("especialidadId", especialidadId)
        .setParameter("profesionalId", profesionalId);


    return query.list();
  }

  @Override
  public List<Paciente> getPacientesRegistrados(Date fechaDesde, Date fechaHasta) {
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery("CALL zp_getAllPacientesRegistrados(:fechaDesde, :fechaHasta);")
        .addEntity(Paciente.class).setParameter("fechaDesde", fechaDesde)
        .setParameter("fechaHasta", fechaHasta);

    return query.list();
  }

  @Override
  public List<Paciente> getAfiliadosSinCoseguro() {
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery("CALL zp_getAllAfiliadosSinCoseguro();").addEntity(Paciente.class);

    return query.list();
  }

  @Override
  public List<Paciente> getAfiliadosSinCobertura() {
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery("CALL zp_getAllAfiliadosSinCobertura();").addEntity(Paciente.class);

    return query.list();
  }

  @Override
  public List<Paciente> getFiltroAfiliado(Date fechaDesdeAfiliado, Date fechaHastaAfiliado,
      Date fechaNacimiento, Integer localidadId, String zonaAfiliacion) {
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery(
            "CALL zp_getFiltroAfiliado(:fechaDesdeAfiliado, :fechaHastaAfiliado, :fechaNacimiento, :localidadId, :zonaAfiliacion);")
        .addEntity(Paciente.class).setDate("fechaDesdeAfiliado", fechaDesdeAfiliado)
        .setDate("fechaHastaAfiliado", fechaHastaAfiliado)
        .setDate("fechaNacimiento", fechaNacimiento).setInteger("localidadId", localidadId)
        .setString("zonaAfiliacion", zonaAfiliacion);

    return query.list();
  }

  @Override
  public List<ReporteDetalleOrdenConsultaDto> getOrdenesPorTipoYFecha(Integer tipoOrden,
      Date fechaDesde, Date fechaHasta) {
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery("CALL zp_getAllOrdenesPorTipoYFecha(:tipoOrden, :fechaDesde, :fechaHasta);")
        .setInteger("tipoOrden", tipoOrden).setDate("fechaDesde", fechaDesde)
        .setDate("fechaHasta", fechaHasta)
        .setResultTransformer(Transformers.aliasToBean(ReporteDetalleOrdenConsultaDto.class));;

    return query.list();
  }

}
