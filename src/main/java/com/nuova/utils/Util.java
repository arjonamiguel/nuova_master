package com.nuova.utils;

import com.nuova.dto.ObraSocialDTO;
import com.nuova.model.Obrasocial;

public class Util {

    static public Obrasocial transformDtoToObraSocial(ObraSocialDTO dto) {
        Obrasocial obrasocial = new Obrasocial();
        obrasocial.setObrasocialId(dto.getObrasocialId());
        obrasocial.setNombre(dto.getNombre());
        return obrasocial;
    }

}
