package com.nuova.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nuova.dao.PacienteDAO;
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

@Service
@Transactional
public class PacienteManagerImpl implements PacienteManager {
    @Autowired
    PacienteDAO pacienteDAO;


    @Override
    public void add(Paciente paciente) {
        pacienteDAO.add(paciente);
    }


    @Override
    public Paciente fin1dPacienteById(Integer pacienteId) {
        return pacienteDAO.fin1dPacienteById(pacienteId);
    }


    @Override
    public List<Paciente> findAll() {
        return pacienteDAO.findAll();
    }


    @Override
    public List<Paciente> findAllActive() {
        return pacienteDAO.findAllActive();
    }


    @Override
    public void delete(Integer pacienteId) {
        pacienteDAO.delete(pacienteId);
    }


    @Override
    public void edit(Paciente paciente) {
        pacienteDAO.edit(paciente);
    }


    @Override
    public void deleteAdherente(Integer pacienteId) {
        pacienteDAO.deleteAdherente(pacienteId);
    }


    @Override
    public void deletePacienteObrasocial(Integer pacienteId) {
        pacienteDAO.deletePacienteObrasocial(pacienteId);
    }


    @Override
    public Page<GridPacienteDTO> findPacientesByPageable(Pageable pageable) {
        return pacienteDAO.findPacientesByPageable(pageable);
    }


    @Override
    public Page<GridPacienteDTO> findPacientesBySearch(String search, Pageable pageable) {
        return pacienteDAO.findPacientesBySearch(search, pageable);
    }


    @Override
    public OrdenAlarmaDTO countPacientes() {
        return pacienteDAO.countPacientes();
    }


    @Override
    public List<PacienteAutocompleteDTO> findPacienteAutocomplete(String search) {
        return pacienteDAO.findPacienteAutocomplete(search);
    }


    @Override
    public Paciente findPacienteByDni(Integer dni) {
        return pacienteDAO.findPacienteByDni(dni);
    }


    @Override
    public Paciente findPacienteByCredencialSufijo(String nroCredencial,
            String nroCredencialSufijo) {
        return pacienteDAO.findPacienteByCredencialSufijo(nroCredencial, nroCredencialSufijo);
    }


    @Override
    public List<Localidades> findLocalidadesAutocomplete(String search) {
        return pacienteDAO.findLocalidadesAutocomplete(search);
    }


    @Override
    public Localidades findLocalidadById(Integer localidadId) {
        return pacienteDAO.findLocalidadById(localidadId);
    }


    @Override
    public Paciente findPacienteByCredencial(String credencial) {
        return pacienteDAO.findPacienteByCredencial(credencial);
    }


    @Override
    public List<Empresas> findAllEmpresas() {
        return pacienteDAO.findAllEmpresas();
    }


    @Override
    public Empresas findEmpresaById(Integer empresaId) {
        return pacienteDAO.findEmpresaById(empresaId);
    }


    @Override
    public List<Especialidad> findEspecialidadesAutocomplete(String search, Integer tipo) {
        return pacienteDAO.findEspecialidadesAutocomplete(search, tipo);
    }


    @Override
    public void add(Empresas empresa) {
        pacienteDAO.add(empresa);
    }


    @Override
    public List<Paciente> findAllPacienteByCredencial(String credencial) {
        return pacienteDAO.findAllPacienteByCredencial(credencial);
    }


    @Override
    public void addLocalidad(Localidades localidad) {
        pacienteDAO.addLocalidad(localidad);
    }

    @Override
    public PacienteInfoDTO findPacientesInfo(Integer pacienteId) {
        return pacienteDAO.findPacientesInfo(pacienteId);
    }


    @Override
    public List<PacienteDTO> getAdherentes(Integer titular) {
        return pacienteDAO.getAdherentes(titular);
    }


    @Override
    public void addHistoriaClinica(HistoriaClinica hc) {
        pacienteDAO.addHistoriaClinica(hc);

    }


    @Override
    public void addHistoriaClinicaObservaciones(HistoriaClinicaObservaciones hco) {
        pacienteDAO.addHistoriaClinicaObservaciones(hco);

    }


    @Override
    public void addHistoriaClinicaAdjuntos(HistoriaClinicaAdjuntos hca) {
        pacienteDAO.addHistoriaClinicaAdjuntos(hca);

    }


    @Override
    public HistoriaClinica findHistoriaClinicaByFecha(Date fecha, Integer pacienteId) {
        return pacienteDAO.findHistoriaClinicaByFecha(fecha, pacienteId);
    }


    @Override
    public HistoriaClinicaAdjuntos findAdjuntoById(Integer adjuntoId) {
        return pacienteDAO.findAdjuntoById(adjuntoId);
    }


    @Override
    public Paciente findPacienteByOrdenId(Integer ordenId) {
        return pacienteDAO.findPacienteByOrdenId(ordenId);
    }

}
