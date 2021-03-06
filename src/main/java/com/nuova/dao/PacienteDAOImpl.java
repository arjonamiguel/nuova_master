package com.nuova.dao;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.model.Empresas;
import com.nuova.model.Especialidad;
import com.nuova.model.Localidades;
import com.nuova.model.Paciente;

@Repository
public class PacienteDAOImpl implements PacienteDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Paciente paciente) {
        this.sessionFactory.getCurrentSession().save(paciente);
        // for (PacienteObrasocial po : paciente.getPacienteObrasocials()) {
        // this.sessionFactory.getCurrentSession().save(po);
        // }
    }

    @Override
    public Paciente fin1dPacienteById(Integer pacienteId) {
        return (Paciente) this.sessionFactory.getCurrentSession().get(Paciente.class, pacienteId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Paciente> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("FROM Paciente").list();
    }

    @Override
    public void delete(Integer pacienteId) {
        this.sessionFactory.getCurrentSession()
                .createQuery(" DELETE FROM Paciente p WHERE p.pacienteId = :pacienteId ")
                .setInteger("pacienteId", pacienteId).executeUpdate();
    }

    @Override
    public void edit(Paciente paciente) {
        // for (PacienteObrasocial po : paciente.getPacienteObrasocials()) {
        // this.sessionFactory.getCurrentSession().saveOrUpdate(po);
        // }
        this.sessionFactory.getCurrentSession().saveOrUpdate(paciente);
    }

    @Override
    public void deleteAdherente(Integer pacienteId) {
        this.sessionFactory.getCurrentSession()
                .createQuery(
                        " DELETE FROM PacienteObrasocial po " + " WHERE po.paciente.pacienteId = :pacienteId ")
                .setInteger("pacienteId", pacienteId).executeUpdate();
    }

    @Override
    public void deletePacienteObrasocial(Integer pacienteId) {
        this.sessionFactory.getCurrentSession()
                .createQuery(
                        " DELETE FROM PacienteObrasocial po " + " WHERE po.paciente.pacienteId = :pacienteId ")
                .setInteger("pacienteId", pacienteId).executeUpdate();

    }

    @Override
    public Page<Paciente> findPacientesByPageable(Pageable pageable) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Paciente p " + " " + " ORDER BY p.pacienteId DESC");
        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        query.setMaxResults(20);
        List<Paciente> result = query.list();
        return new PageImpl<Paciente>(result, pageable, result.size());
    }

    @Override
    public Page<Paciente> findPacientesBySearch(String search, Pageable pageable) {
        try {
            byte[] parameterByte = search.getBytes("ISO-8859-15");
            search = new String(parameterByte, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Paciente p " + " WHERE " + " upper(p.apellido) LIKE '%"
                        + search.toUpperCase() + "%' " + " OR upper(p.nombre) LIKE '%" + search.toUpperCase()
                        + "%' " + " OR p.dni like'%" + search + "%' ORDER BY p.apellido ASC");
        // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(pageable.getPageNumber());
        query.setMaxResults(20);
        List<Paciente> result = query.list();
        return new PageImpl<Paciente>(result, pageable, result.size());
    }

    @Override
    @SuppressWarnings("unchecked")
    public OrdenAlarmaDTO countPacientes() {
        return (OrdenAlarmaDTO) this.sessionFactory.getCurrentSession()
                .createQuery(" SELECT NEW com.nuova.dto.OrdenAlarmaDTO(COUNT(*)) " + " FROM Paciente p ")
                .list().get(0);
    }

    @Override
    public List<Paciente> findPacienteAutocomplete(String search) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Paciente p " + " WHERE upper(p.apellido) LIKE '%" + search.toUpperCase()
                        + "%' " + " OR p.dni LIKE '%" + search.toUpperCase() + "%' "
                        + " ORDER BY p.apellido, p.nroCredencial, p.nroCredencialSufijo ASC");
        // query.setFirstResult(pageable.getOffset());
        query.setMaxResults(20);
        List<Paciente> result = query.list();
        return result;
    }

    @Override
    public Paciente findPacienteByDni(Integer dni) {
        Paciente retorno = null;
        @SuppressWarnings("unchecked")
        List<Paciente> result = this.sessionFactory.getCurrentSession()
                .createQuery(" SELECT p " + " FROM Paciente p " + " WHERE p.dni = " + dni).list();

        if (result != null && !result.isEmpty()) {
            retorno = result.get(0);
        }

        return retorno;
    }

    @Override
    public List<Localidades> findLocalidadesAutocomplete(String search) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Localidades loc " + " WHERE upper(loc.nombre) LIKE '"
                        + search.toUpperCase() + "%'" + " ORDER BY loc.nombre ASC");
        // query.setFirstResult(pageable.getOffset());
        query.setMaxResults(20);
        List<Localidades> result = query.list();
        return result;
    }

    @Override
    public Localidades findLocalidadById(Integer localidadId) {
        return (Localidades) this.sessionFactory.getCurrentSession().get(Localidades.class,
                localidadId);
    }

    @Override
    public Paciente findPacienteByCredencial(String credencial) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(
                "FROM Paciente p " + " WHERE p.nroCredencial = '" + credencial + "' AND p.parentesco = 0 ");
        return (Paciente) ((query.list().isEmpty()) ? null : query.list().get(0));
    }

    @Override
    public List<Paciente> findAllAdherentes() {
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM Paciente p WHERE p.parenteso > 0").list();
    }

    @Override
    public List<Empresas> findAllEmpresas() {
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM Empresas e ORDER BY e.nombre ASC").list();
    }

    @Override
    public Empresas findEmpresaById(Integer empresaId) {
        return (Empresas) this.sessionFactory.getCurrentSession().get(Empresas.class, empresaId);
    }

    @Override
    public List<Especialidad> findEspecialidadesAutocomplete(String search, Integer tipo) {
        Query query = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Especialidad e " + " WHERE upper(e.nombre) LIKE '%"
                        + search.toUpperCase() + "%' and e.tipo=" + tipo + " ORDER BY e.nombre ASC");
        // query.setFirstResult(pageable.getOffset());
        query.setMaxResults(20);
        List<Especialidad> result = query.list();
        return result;
    }

    @Override
    public void add(Empresas empresa) {
        this.sessionFactory.getCurrentSession().save(empresa);
    }

    @Override
    public List<Paciente> findAllPacienteByCredencial(String credencial) {
        Query query = this.sessionFactory.getCurrentSession().createQuery("FROM Paciente p "
                + " WHERE p.parentesco > 0 and p.nroCredencial = '" + credencial.trim() + "' ");
        return (query.list().isEmpty()) ? null : query.list();
    }

    @Override
    public void addLocalidad(Localidades localidad) {
        this.sessionFactory.getCurrentSession().save(localidad);
    }

}
