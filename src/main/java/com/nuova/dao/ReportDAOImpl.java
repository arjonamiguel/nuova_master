package com.nuova.dao;

import com.nuova.model.Paciente;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class ReportDAOImpl implements ReportDAO {
  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public List<Paciente> getAfiliadosAtendidos(Date fechaDesde, Date fechaHasta,
      Integer especialidadId) {
    Query query = sessionFactory.getCurrentSession()
        .createSQLQuery(
            "CALL zp_getAllAfiliadosAtendidos(:fechaDesde, :fechaHasta, :especialidadId);")
        .addEntity(Paciente.class).setParameter("fechaDesde", fechaDesde)
        .setParameter("fechaHasta", fechaHasta).setParameter("especialidadId", especialidadId);


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

}
