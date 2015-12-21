package com.nuova.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nuova.dto.ObraSocialDTO;
import com.nuova.model.Obrasocial;

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
        try {
            fechaHabilitacion = formatter.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fechaHabilitacion;
    }

}
