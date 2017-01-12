package com.nuova.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.nuova.dto.CalendarDTO;
import com.nuova.dto.ComboItemDTO;
import com.nuova.dto.ObraSocialDTO;
import com.nuova.dto.OrdenDTO;
import com.nuova.model.Calendario;
import com.nuova.model.Nomenclador;
import com.nuova.model.Obrasocial;
import com.nuova.model.Paciente;

public class Util {

  static public int ESPECIALIDAD_PROFESIONAL = 0;
  static public int ESPECIALIDAD_PRESTADOR = 2;

  static public String ESTADO_REINTEGRO_ENPROCESO = "EN PROCESO";
  static public String ESTADO_REINTEGRO_PROCESADO = "REINTEGRADO";
  static public String ESTADO_REINTEGRO_ANULADO = "ANULADO";

  static public int MESSAGE_SUCCESS = 1;
  static public int MESSAGE_WARNING = 2;
  static public int MESSAGE_INFORMATION = 3;
  static public int MESSAGE_ERROR = 4;

  static public String DOCUMENT_TYPE = "HISTORIA_CLINICA";

  static public int CONCEPTO_INGRESO_INICIOCAJA = 0;
  static public int CONCEPTO_INGRESO_ORDENCONSULTA = 1;
  static public int CONCEPTO_INGRESO_ORDENCONSULTAODONTOLOGICA = 2;
  static public int CONCEPTO_INGRESO_ORDENPRACTICA = 3;
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
    hashMap.put(CONCEPTO_INGRESO_ORDENPRACTICA, "Orden de Práctica");
    hashMap.put(CONCEPTO_EGRESO_ARTICULOLIBRERIA, "Artículos de Libreria");
    hashMap.put(CONCEPTO_EGRESO_ARTICULOLIMIEZA, "Artículos de Limpieza");
    hashMap.put(CONCEPTO_EGRESO_SERVIMOTO, "Servicio de Cadeteria");
    hashMap.put(CONCEPTO_EGRESO_GENERAL, "Egresos Generales");

    return hashMap;
  }

  static public int ORDEN_CONSULTA = 100;
  static public int ORDEN_ODONTOLOGICA = 101;
  static public int ORDEN_PRACTICA = 102;

  static public String PARENTESCO_TITULAR = "Titular";
  static public String PARENTESCO_CONYUGE = "Esposo/a";
  static public String PARENTESCO_CONCUBINO = "Concubino/a";
  static public String PARENTESCO_HIJOSOLTEROMENOR = "Hijo soltero menor de 21 años";
  static public String PARENTESCO_HIJOSOLTEROESTUDIANTE =
      "Hijo soltero de 21 a 25 años cursando estudios regulares";
  static public String PARENTESCO_HIJOCONYUGESOLTEROMENOR =
      "Hijo de cónyuge soltero menor de 21 años";
  static public String PARENTESCO_HIJOCONYUGESOLTEROESTUDIANTE =
      "Hijo de cónyuge soltero de 21 a 25 años cursando estudios regulares";
  static public String PARENTESCO_MENORBAJOTUTELA = "Menor bajo guarda o tutela";
  static public String PARENTESCO_HIJODISCAPACITADO = "Hijo discapacitado";

  static public String EMPLEADO_SEGURIDAD = "Empleado de Seguridad";
  static public String MONOTRIBUTISTA = "Monotributista";
  static public String EMPLEADA_DOMESTICA = "Empleada Domestica";

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

  static public List<ComboItemDTO> getParentescosAdherente() {
    List<ComboItemDTO> parentescos = new ArrayList<ComboItemDTO>();
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

  static public List<ComboItemDTO> getTrabajaEn() {
    List<ComboItemDTO> trabaEn = new ArrayList<ComboItemDTO>();
    trabaEn.add(new ComboItemDTO("0", EMPLEADO_SEGURIDAD));
    trabaEn.add(new ComboItemDTO("1", MONOTRIBUTISTA));
    trabaEn.add(new ComboItemDTO("2", EMPLEADA_DOMESTICA));

    return trabaEn;
  }

  static public List<ComboItemDTO> getEspecialidadTipos() {
    List<ComboItemDTO> espTipo = new ArrayList<ComboItemDTO>();
    espTipo.add(new ComboItemDTO(ESPECIALIDAD_PROFESIONAL + "", "PROFESIONAL"));
    espTipo.add(new ComboItemDTO(ESPECIALIDAD_PRESTADOR + "", "PRESTADOR"));

    return espTipo;
  }

  static public String getTrbajaEnValue(String id) {
    String retorno = "";
    for (ComboItemDTO c : getTrabajaEn()) {
      if (c.getId().equals(id)) {
        retorno = c.getValue();
      }
    }

    return retorno;
  }

  static public List<String> getProvincias() {
    List<String> provincias = new ArrayList<String>();
    provincias.add("TUCUMAN");
    provincias.add("BUENOS AIRES");
    provincias.add("CATAMARCA");
    provincias.add("CHACO");
    provincias.add("CHUBUT");
    provincias.add("CORDOBA");
    provincias.add("CORRIENTES");
    provincias.add("ENTRE RIOS");
    provincias.add("FORMOSA");
    provincias.add("JUJUY");
    provincias.add("LA PAMPA");
    provincias.add("LA RIOJA");
    provincias.add("MENDOZA");
    provincias.add("MISIONES");
    provincias.add("NEUQUEN");
    provincias.add("RIO NEGRO");
    provincias.add("SALTA");
    provincias.add("SAN JUAN");
    provincias.add("SAN LUIS");
    provincias.add("SANTA CRUZ");
    provincias.add("SANTA FE");
    provincias.add("SANTIAGO DEL ESTERO");
    provincias.add("TIERRA DEL FUEGO");
    return provincias;
  }

  static public List<String> getRazonCoseguro() {
    List<String> razonCoseguro = new ArrayList<String>();
    razonCoseguro.add("Discapacitado");
    razonCoseguro.add("PMI");
    razonCoseguro.add("Embarazo");
    razonCoseguro.add("Oncológico");
    razonCoseguro.add("Otro");
    return razonCoseguro;
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
    Date retorno = null;
    if (!Objects.equals(date, null) && !date.equals("null")) {
      try {
        if (!date.equals("")) {
          Date currentDate = new Date();
          retorno = formatter.parse(date);
          retorno.setHours(currentDate.getHours());
          retorno.setMinutes(currentDate.getMinutes());
          retorno.setSeconds(currentDate.getSeconds());
        }
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return retorno;
  }

  static public String parseToStringDate(Date date) {
    String retorno = "";
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    if (date != null) {
      try {
        retorno = formatter.format(date);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return retorno;
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
    retorno.add(new ComboItemDTO(ConstantOrdenEstado.VENCIDA, ConstantOrdenEstado.VENCIDA));
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

  static public String getEtiquetaEstadoReintegro(String estado) {
    String retorno = "";

    if (Util.ESTADO_REINTEGRO_ENPROCESO.equals(estado)) {
      retorno = " <span  style='color:black;background:gold'>" + estado + "</span>";
    }

    if (Util.ESTADO_REINTEGRO_PROCESADO.equals(estado)) {
      retorno = "<span style='color:white;background: green'>" + estado + "</span>";
    }

    if (Util.ESTADO_REINTEGRO_ANULADO.equals(estado)) {
      retorno = "<span style='color:white;background: tomato'>" + estado + "</span>";;
    }

    return retorno;
  }

  static public List<String> getEstadosReintegro() {
    List<String> retorno = new ArrayList<String>();
    retorno.add(ESTADO_REINTEGRO_ENPROCESO);
    retorno.add(ESTADO_REINTEGRO_PROCESADO);
    retorno.add(ESTADO_REINTEGRO_ANULADO);

    return retorno;
  }

  static public boolean isNumber(String value) {
    boolean retorno = true;
    try {
      Integer.parseInt(value);
    } catch (NumberFormatException e) {
      retorno = false;
    }

    return retorno;
  }

}
