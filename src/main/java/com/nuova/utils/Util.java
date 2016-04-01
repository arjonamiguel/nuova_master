package com.nuova.utils;

import com.nuova.dto.CalendarDTO;
import com.nuova.dto.ComboItemDTO;
import com.nuova.dto.ObraSocialDTO;
import com.nuova.dto.OrdenDTO;
import com.nuova.model.Calendario;
import com.nuova.model.Nomenclador;
import com.nuova.model.Obrasocial;
import com.nuova.model.Paciente;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {

  static public int MESSAGE_SUCCESS = 1;
  static public int MESSAGE_WARNING = 2;
  static public int MESSAGE_INFORMATION = 3;
  static public int MESSAGE_ERROR = 4;

  static public String DOCUMENT_TYPE = "HISTORIA_CLINICA";

  static public int CONCEPTO_INGRESO_INICIOCAJA = 0;
  static public int CONCEPTO_INGRESO_ORDENCONSULTA = 1;
  static public int CONCEPTO_INGRESO_ORDENCONSULTAODONTOLOGICA = 2;
  static public int CONCEPTO_EGRESO_ARTICULOLIBRERIA = 100;
  static public int CONCEPTO_EGRESO_ARTICULOLIMIEZA = 101;
  static public int CONCEPTO_EGRESO_SERVIMOTO = 102;
  static public int CONCEPTO_EGRESO_GENERAL = 150;

  @SuppressWarnings("unused")
  public static final Map<Integer, String> CAJA_CONCEPTOS = prepareMap();

  private static Map<Integer, String> prepareMap() {
    Map<Integer, String> hashMap = new HashMap<Integer, String>();
    hashMap.put(CONCEPTO_INGRESO_INICIOCAJA, "Inicio de Caja");
    hashMap.put(CONCEPTO_INGRESO_ORDENCONSULTA, "Orden de Consulta");
    hashMap.put(CONCEPTO_INGRESO_ORDENCONSULTAODONTOLOGICA, "Orden de Consulta Odontologica");
    hashMap.put(CONCEPTO_EGRESO_ARTICULOLIBRERIA, "Articulo de Libreria");
    hashMap.put(CONCEPTO_EGRESO_ARTICULOLIMIEZA, "Articulo de Limpieza");
    hashMap.put(CONCEPTO_EGRESO_SERVIMOTO, "Servi Moto");
    hashMap.put(CONCEPTO_EGRESO_GENERAL, "Egreso General");

    return hashMap;
  }

  static public int ORDEN_CONSULTA = 100;
  static public int ORDEN_ODONTOLOGICA = 101;
  static public int ORDEN_PRACTICA = 102;

  static public String PARENTESCO_TITULAR = "[00]-Titular";
  static public String PARENTESCO_CONYUGE = "[01]-Esposo/a";
  static public String PARENTESCO_CONCUBINO = "[02]-Concubino/a";
  static public String PARENTESCO_HIJOSOLTEROMENOR = "[03]-Hijo soltero menor de 21 años";
  static public String PARENTESCO_HIJOSOLTEROESTUDIANTE =
      "[04]-Hijo soltero de 21 a 25 años cursando estudios regulares";
  static public String PARENTESCO_HIJOCONYUGESOLTEROMENOR =
      "[05]-Hijo de cónyuge soltero menor de 21 años";
  static public String PARENTESCO_HIJOCONYUGESOLTEROESTUDIANTE =
      "[06]-Hijo de cónyuge soltero de 21 a 25 años cursando estudios regulares";
  static public String PARENTESCO_MENORBAJOTUTELA = "[07]-Menor bajo guarda o tutela";
  static public String PARENTESCO_HIJODISCAPACITADO = "[08]-Hijo discapacitado";

  static public List<ComboItemDTO> getParentescos() {
    List<ComboItemDTO> parentescos = new ArrayList<ComboItemDTO>();
    parentescos.add(new ComboItemDTO("0", PARENTESCO_TITULAR));
    parentescos.add(new ComboItemDTO("1", PARENTESCO_CONYUGE));
    parentescos.add(new ComboItemDTO("2", PARENTESCO_CONCUBINO));
    parentescos.add(new ComboItemDTO("3", PARENTESCO_HIJOSOLTEROMENOR));
    parentescos.add(new ComboItemDTO("4", PARENTESCO_HIJOSOLTEROESTUDIANTE));
    parentescos.add(new ComboItemDTO("5", PARENTESCO_HIJOCONYUGESOLTEROMENOR));
    parentescos.add(new ComboItemDTO("6", PARENTESCO_HIJOCONYUGESOLTEROESTUDIANTE));
    parentescos.add(new ComboItemDTO("7", PARENTESCO_MENORBAJOTUTELA));
    parentescos.add(new ComboItemDTO("8", PARENTESCO_HIJODISCAPACITADO));

    return parentescos;
  }

  static public List<String> getProvincias() {
    List<String> provincias = new ArrayList<String>();
    provincias.add("Tucumán");
    provincias.add("Buenos Aires");
    provincias.add("Catamarca");
    provincias.add("Chaco");
    provincias.add("Chubut");
    provincias.add("Córdoba");
    provincias.add("Corrientes");
    provincias.add("Entre Ríos");
    provincias.add("Formosa");
    provincias.add("Jujuy");
    provincias.add("La Pampa");
    provincias.add("La Rioja");
    provincias.add("Mendoza");
    provincias.add("Misiones");
    provincias.add("Neuquén");
    provincias.add("Río Negro");
    provincias.add("Salta");
    provincias.add("San Juan");
    provincias.add("San Luis");
    provincias.add("Santa Cruz");
    provincias.add("Santa Fe");
    provincias.add("Santiago del Estero");
    provincias.add("Tierra del Fuego");
    return provincias;
  }

  static public Obrasocial transformDtoToObraSocial(ObraSocialDTO dto) {
    Obrasocial obrasocial = new Obrasocial();
    obrasocial.setObrasocialId(dto.getObrasocialId());
    obrasocial.setNombre(dto.getNombre());
    obrasocial.setCuit(dto.getCuit());
    obrasocial.setDireccion(dto.getDireccion());
    obrasocial.setTelefono(dto.getTelefono());
    return obrasocial;
  }

  static public Calendario transformDtoToCalendario(CalendarDTO dto) {
    Calendario calendario = new Calendario();
    calendario.setCalendarioId(dto.getId());
    calendario.setResource(dto.getResourceId());
    calendario.setStart(parseToDate(dto.getStart()));
    calendario.setEnd(parseToDate(dto.getEnd()));
    calendario.setTitle(dto.getTitle());
    return calendario;
  }

  static public Date parseToDate(String date) {
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date fechaHabilitacion = null;
    if (date != null) {
      try {
        fechaHabilitacion = formatter.parse(date);
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return fechaHabilitacion;
  }

  static public List<ComboItemDTO> getComboItems(List<?> list) {
    List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
    for (Object obj : list) {
      if (obj instanceof Paciente) {
        Paciente o = (Paciente) obj;
        retorno
            .add(new ComboItemDTO(o.getPacienteId() + "", o.getApellido() + " " + o.getNombre()));
      } else if (obj instanceof Nomenclador) {
        Nomenclador o = (Nomenclador) obj;
        retorno.add(new ComboItemDTO(o.getNomencladorId() + "",
            "[" + o.getCodigo() + "]-" + o.getNombre()));
      }
    }
    return retorno;
  }

  static public Byte getByteFlag(boolean flag) {
    return flag ? new Byte("1") : new Byte("0");
  }

  static public String getEstadoInicial(OrdenDTO dto) {
    String retorno = ConstantOrdenEstado.ORDEN_INICIADA;
    // if (dto.isReqCredecial() && dto.isReqOrdenMedico() && (dto.isReqMonotributista() ||
    // dto.isReqReciboSueldo()))
    // {
    // retorno = ConstantOrdenEstado.PENDIENTE;
    // } else {
    // retorno = ConstantOrdenEstado.INCOMPLETA;
    // }

    return retorno;
  }

  static public List<ComboItemDTO> getEstadosList() {
    List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
    retorno.add(new ComboItemDTO(ConstantOrdenEstado.AUTORIZACION_DIRECTA,
        ConstantOrdenEstado.AUTORIZACION_DIRECTA));
    retorno.add(new ComboItemDTO(ConstantOrdenEstado.PENDIENTE_AFILIACIONES,
        ConstantOrdenEstado.PENDIENTE_AFILIACIONES));
    retorno.add(new ComboItemDTO(ConstantOrdenEstado.AUTORIZADA_POR_AFILIACIONES,
        ConstantOrdenEstado.AUTORIZADA_POR_AFILIACIONES));
    retorno.add(new ComboItemDTO(ConstantOrdenEstado.RECHAZADA_POR_AFILIACIONES,
        ConstantOrdenEstado.RECHAZADA_POR_AFILIACIONES));
    retorno.add(new ComboItemDTO(ConstantOrdenEstado.PENDIENTE_AUDITORIA,
        ConstantOrdenEstado.PENDIENTE_AUDITORIA));
    retorno.add(new ComboItemDTO(ConstantOrdenEstado.AUTORIZADA_POR_AUDITORIA,
        ConstantOrdenEstado.AUTORIZADA_POR_AUDITORIA));
    retorno.add(new ComboItemDTO(ConstantOrdenEstado.RECHAZADA_POR_AUDITORIA,
        ConstantOrdenEstado.RECHAZADA_POR_AUDITORIA));
    retorno.add(new ComboItemDTO(ConstantOrdenEstado.RECHAZADA, ConstantOrdenEstado.RECHAZADA));
    retorno.add(new ComboItemDTO(ConstantOrdenEstado.ANULADO, ConstantOrdenEstado.ANULADO));
    return retorno;
  }

  static public List<ComboItemDTO> getOrdenEstadosList() {
    List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
    retorno.add(
        new ComboItemDTO(ConstantOrdenEstado.ORDEN_INICIADA, ConstantOrdenEstado.ORDEN_INICIADA));
    retorno.add(new ComboItemDTO(ConstantOrdenEstado.ORDEN_EN_PROGRESO,
        ConstantOrdenEstado.ORDEN_EN_PROGRESO));
    retorno.add(
        new ComboItemDTO(ConstantOrdenEstado.ORDEN_PENDIENTE, ConstantOrdenEstado.ORDEN_PENDIENTE));
    retorno.add(new ComboItemDTO(ConstantOrdenEstado.ORDEN_AUTORIZADA,
        ConstantOrdenEstado.ORDEN_AUTORIZADA));
    retorno.add(
        new ComboItemDTO(ConstantOrdenEstado.ORDEN_RECHAZADA, ConstantOrdenEstado.ORDEN_RECHAZADA));
    retorno.add(
        new ComboItemDTO(ConstantOrdenEstado.ORDEN_ANULADA, ConstantOrdenEstado.ORDEN_ANULADA));
    retorno.add(
        new ComboItemDTO(ConstantOrdenEstado.ORDEN_CERRADA, ConstantOrdenEstado.ORDEN_CERRADA));

    return retorno;
  }


}
