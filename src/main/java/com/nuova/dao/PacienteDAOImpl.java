package com.nuova.dao;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.nuova.dto.GridPacienteDTO;
import com.nuova.dto.OrdenAlarmaDTO;
import com.nuova.dto.PacienteAutocompleteDTO;
import com.nuova.dto.PacienteDTO;
import com.nuova.dto.PacienteInfoDTO;
import com.nuova.model.Empresas;
import com.nuova.model.Especialidad;
import com.nuova.model.HistoriaClinica;
import com.nuova.model.HistoriaClinicaAdjuntos;
import com.nuova.model.HistoriaClinicaObservaciones;
import com.nuova.model.Localidades;
import com.nuova.model.Paciente;

@Repository
public class PacienteDAOImpl implements PacienteDAO {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void add(Paciente paciente) {
        String search = paciente.getDni().trim() + paciente.getApellido().trim()
                + paciente.getNombre().trim();
        paciente.setSearch(search.replace(" ", ""));
        this.sessionFactory.getCurrentSession().save(paciente);
        // for (PacienteObrasocial po : paciente.getPacienteObrasocials()) {
        // this.sessionFactory.getCurrentSession().save(po);
        // }
    }


    @Override
    public Paciente fin1dPacienteById(Integer pacienteId) {
        // return (Paciente) this.sessionFactory.getCurrentSession().get(Paciente.class,
        // pacienteId);
        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery("CALL zp_getPacienteById(:pacienteId);")
                .setInteger("pacienteId", pacienteId)
                .setResultTransformer(Transformers.aliasToBean(Paciente.class));
        List<Paciente> result = query.list();
        return result.isEmpty() ? null : result.get(0);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Paciente> findAll() {
        return this.sessionFactory.getCurrentSession().createQuery("FROM Paciente").list();
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Paciente> findAllActive() {
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM Paciente p WHERE p.eliminado = 0").list();
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
        String search = paciente.getDni().trim() + paciente.getApellido().trim()
                + paciente.getNombre().trim();
        paciente.setSearch(search.replace(" ", ""));
        this.sessionFactory.getCurrentSession().saveOrUpdate(paciente);
    }


    @Override
    public void deleteAdherente(Integer pacienteId) {
        this.sessionFactory.getCurrentSession()
                .createQuery(" DELETE FROM PacienteObrasocial po "
                        + " WHERE po.paciente.pacienteId = :pacienteId ")
                .setInteger("pacienteId", pacienteId).executeUpdate();
    }


    @Override
    public void deletePacienteObrasocial(Integer pacienteId) {
        this.sessionFactory.getCurrentSession()
                .createQuery(" DELETE FROM PacienteObrasocial po "
                        + " WHERE po.paciente.pacienteId = :pacienteId ")
                .setInteger("pacienteId", pacienteId).executeUpdate();

    }


    @Override
    public Page<GridPacienteDTO> findPacientesByPageable(Pageable pageable) {
        // Query query = this.sessionFactory.getCurrentSession()
        // .createQuery("FROM Paciente p " + " " + " ORDER BY p.pacienteId DESC");
        // // query.setFirstResult(pageable.getOffset());
        // // query.setMaxResults(pageable.getPageNumber());
        // query.setMaxResults(20);
        // List<Paciente> result = query.list();
        Query query =
                sessionFactory.getCurrentSession().createSQLQuery("CALL zp_getGridAllPacientes();")
                        .setResultTransformer(Transformers.aliasToBean(GridPacienteDTO.class));
        List<GridPacienteDTO> result = query.list();

        return new PageImpl<GridPacienteDTO>(result, pageable, result.size());
    }


    @Override
    public Page<GridPacienteDTO> findPacientesBySearch(String search, Pageable pageable) {
        try {
            byte[] parameterByte = search.getBytes("ISO-8859-15");
            search = new String(parameterByte, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery("CALL zp_getGridAllPacientesBySearch(:search);")
                .setString("search", search)
                .setResultTransformer(Transformers.aliasToBean(GridPacienteDTO.class));
        List<GridPacienteDTO> result = query.list();

        return new PageImpl<GridPacienteDTO>(result, pageable, result.size());
    }


    @Override
    @SuppressWarnings("unchecked")
    public OrdenAlarmaDTO countPacientes() {
        return (OrdenAlarmaDTO) this.sessionFactory.getCurrentSession()
                .createQuery(
                        " SELECT NEW com.nuova.dto.OrdenAlarmaDTO(COUNT(*)) " + " FROM Paciente p ")
                .list().get(0);
    }


    @Override
    public List<PacienteAutocompleteDTO> findPacienteAutocomplete(String search) {
        // Query query = this.sessionFactory.getCurrentSession()
        // .createQuery("FROM Paciente p " + " WHERE upper(p.apellido) LIKE '%" +
        // search.toUpperCase()
        // + "%' " + " OR p.dni LIKE '%" + search.toUpperCase() + "%' "
        // + " ORDER BY p.apellido, p.nroCredencial, p.nroCredencialSufijo ASC");
        // // query.setFirstResult(pageable.getOffset());
        // query.setMaxResults(20);
        // List<Paciente> result = query.list();
        // return result;

        String sql = " SELECT new com.nuova.dto.PacienteAutocompleteDTO("
                + " p.pacienteId, p.dni,p.apellido, "
                + " p.nombre, concat(p.nroCredencial,'-',p.nroCredencialSufijo))"
                + " FROM Paciente p" + " WHERE upper(p.search) LIKE '%"
                + search.trim().toUpperCase().replace(" ", "") + "%'"
                + " ORDER BY p.apellido, p.nroCredencial, p.nroCredencialSufijo ASC";
        // query.setFirstResult(pageable.getOffset());

        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
        query.setMaxResults(20);
        List<PacienteAutocompleteDTO> result = query.list();

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
    public Paciente findPacienteByCredencialSufijo(String nroCredencial,
            String nroCredencialSufijo) {
        Paciente retorno = null;
        @SuppressWarnings("unchecked")
        List<Paciente> result = this.sessionFactory.getCurrentSession()
                .createQuery(" SELECT p FROM Paciente p WHERE p.nroCredencial = :nroCredencial "
                        + " AND p.nroCredencialSufijo = :nroCredencialSufijo")
                .setString("nroCredencial", nroCredencial)
                .setString("nroCredencialSufijo", nroCredencialSufijo).list();

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
        Query query = this.sessionFactory.getCurrentSession().createQuery("FROM Paciente p "
                + " WHERE p.nroCredencial = '" + credencial + "' AND p.parentesco = 0 ");
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
        Query query = this.sessionFactory.getCurrentSession().createQuery(
                "FROM Especialidad e " + " WHERE e.eliminado=0 and upper(e.nombre) LIKE '%"
                        + search.toUpperCase() + "%' ORDER BY e.nombre ASC");
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

    @Override
    public PacienteInfoDTO findPacientesInfo(Integer pacienteId) {

        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery("CALL zp_getInfoPaciente(:pacienteId);")
                .setInteger("pacienteId", pacienteId)
                .setResultTransformer(Transformers.aliasToBean(PacienteInfoDTO.class));
        PacienteInfoDTO result = (PacienteInfoDTO) query.list().get(0);
        return result;
    }


    @Override
    public List<PacienteDTO> getAdherentes(Integer titular) {
        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery("CALL zp_getAdherentesByTitular(:titular);")
                .setInteger("titular", titular)
                .setResultTransformer(Transformers.aliasToBean(PacienteDTO.class));

        return query.list();
    }


    @Override
    public void addHistoriaClinica(HistoriaClinica hc) {
        this.sessionFactory.getCurrentSession().save(hc);

    }


    @Override
    public void addHistoriaClinicaObservaciones(HistoriaClinicaObservaciones hco) {
        this.sessionFactory.getCurrentSession().save(hco);

    }


    @Override
    public void addHistoriaClinicaAdjuntos(HistoriaClinicaAdjuntos hca) {
        this.sessionFactory.getCurrentSession().save(hca);

    }


    @Override
    public HistoriaClinica findHistoriaClinicaByFecha(Date fecha, Integer pacienteId) {
        HistoriaClinica retorno = null;
        @SuppressWarnings("unchecked")
        List<HistoriaClinica> result = this.sessionFactory.getCurrentSession()
                .createQuery(
                        " SELECT hc FROM HistoriaClinica hc WHERE hc.fecha = :fecha and hc.pacienteId = :pacienteId")
                .setDate("fecha", fecha).setInteger("pacienteId", pacienteId).list();

        if (result != null && !result.isEmpty()) {
            retorno = result.get(0);
        }

        return retorno;
    }


    @Override
    public HistoriaClinicaAdjuntos findAdjuntoById(Integer adjuntoId) {
        return (HistoriaClinicaAdjuntos) this.sessionFactory.getCurrentSession()
                .get(HistoriaClinicaAdjuntos.class, adjuntoId);
    }


    @Override
    public Paciente findPacienteByOrdenId(Integer ordenId) {
        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery("CALL zp_getPacienteByOrdenId(:ordenId);")
                .setInteger("ordenId", ordenId)
                .setResultTransformer(Transformers.aliasToBean(Paciente.class));
        List<Paciente> result = query.list();
        return result.isEmpty() ? null : result.get(0);
    }

}
