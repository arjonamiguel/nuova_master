package com.nuova.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nuova.dto.ComboItemDTO;
import com.nuova.dto.ObraSocialDTO;
import com.nuova.dto.OrdenDTO;
import com.nuova.model.Obrasocial;
import com.nuova.model.Paciente;
import com.nuova.model.Practica;

public class Util {

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
        return obrasocial;
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
                retorno.add(new ComboItemDTO(o.getPacienteId() + "", o.getApellido() + " "
                        + o.getNombre()));
            } else if (obj instanceof Practica) {
                Practica o = (Practica) obj;
                retorno.add(new ComboItemDTO(o.getPracticaId() + "", "[" + o.getCodigo() + "]-"
                        + o.getNombre()));
            }
        }
        return retorno;
    }

    static public Byte getByteFlag(boolean flag) {
        return flag ? new Byte("1") : new Byte("0");
    }

    static public String getEstadoInicial(OrdenDTO dto) {
        String retorno = "";
        if (dto.isReqCredecial() && dto.isReqReciboSueldo() && (dto.isReqMonotributista() || dto.isReqReciboSueldo())) {
            retorno = ConstantOrdenEstado.PENDIENTE;
        } else {
            retorno = ConstantOrdenEstado.INCOMPLETA;
        }

        return retorno;
    }

    static public List<ComboItemDTO> getEstadosList() {
        List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
        retorno.add(new ComboItemDTO(ConstantOrdenEstado.PENDIENTE, ConstantOrdenEstado.PENDIENTE));
        retorno.add(new ComboItemDTO(ConstantOrdenEstado.INCOMPLETA, ConstantOrdenEstado.INCOMPLETA));
        retorno.add(new ComboItemDTO(ConstantOrdenEstado.AUTORIZADA, ConstantOrdenEstado.AUTORIZADA));
        retorno.add(new ComboItemDTO(ConstantOrdenEstado.EN_OBSERVACION, ConstantOrdenEstado.EN_OBSERVACION));
        retorno.add(new ComboItemDTO(ConstantOrdenEstado.CERRADA, ConstantOrdenEstado.CERRADA));
        retorno.add(new ComboItemDTO(ConstantOrdenEstado.RECHAZADA, ConstantOrdenEstado.RECHAZADA));
        return retorno;
    }

}
