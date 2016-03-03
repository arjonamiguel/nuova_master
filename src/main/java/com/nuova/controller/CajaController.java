package com.nuova.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nuova.dto.CajaDTO;
import com.nuova.model.Caja;
import com.nuova.service.CajaManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;
import com.nuova.utils.Util;

@Controller
public class CajaController {
    @Autowired
    CajaManager cajaManager;

    @RequestMapping(value = ConstantControllers.MAIN_CAJA, method = RequestMethod.GET)
    public String mainCaja(ModelMap map,
            @PathVariable("fechaCaja") String fechaCaja) {
        CajaDTO cajaDTO = new CajaDTO();
        Date fecha = null;
        if (!fechaCaja.equals("null")) {
            fecha = Util.parseToDate(fechaCaja);
        } else {
            fecha = new Date(); // fecha hoy
        }

        List<Caja> cajas = cajaManager.findAllByfecha(fecha);
        map.addAttribute("caja", cajaDTO);
        map.addAttribute("cajaList", getCajasList(cajas));
        map.addAttribute("total", getTotalCaja(cajas));
        return ConstantRedirect.VIEW_MAIN_CAJA;
    }

    private CajaDTO transformCajaToDto(Caja c) {
        CajaDTO retorno = new CajaDTO();
        retorno.setCajaId(c.getCajaId());
        retorno.setConcepto(c.getConcepto());
        retorno.setIngreso(c.getIngreso());
        retorno.setEgreso(c.getEgreso());
        retorno.setFecha(c.getFecha());
        retorno.setConceptoDesc(Util.CAJA_CONCEPTOS.get(c.getConcepto()));

        return retorno;
    }

    private List<CajaDTO> getCajasList(List<Caja> list) {
        List<CajaDTO> retorno = new ArrayList<CajaDTO>();
        for (Caja c : list) {
            retorno.add(transformCajaToDto(c));
        }
        return retorno;
    }

    private Double getTotalCaja(List<Caja> list) {
        double ingreso = 0.0;
        double egreso = 0.0;
        for (Caja c : list) {
            ingreso = ingreso + (c.getIngreso() == null ? 0.0 : c.getIngreso().doubleValue());
            egreso = egreso + (c.getEgreso() == null ? 0.0 : c.getEgreso().doubleValue());
        }
        return new Double(ingreso - egreso);
    }
}
