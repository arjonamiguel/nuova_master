package com.nuova.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuova.dto.CajaDTO;
import com.nuova.dto.ComboItemDTO;
import com.nuova.model.Caja;
import com.nuova.service.CajaManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;
import com.nuova.utils.Util;

@Controller
public class CajaController {
    @Autowired
    CajaManager cajaManager;

    @RequestMapping(value = ConstantControllers.FORM_UPDATE_CAJA, method = RequestMethod.GET)
    public String formUpdateCaja(ModelMap map) {
        CajaDTO dto = new CajaDTO();
        map.addAttribute("caja", dto);

        return ConstantRedirect.VIEW_FORM_UPDATE_CAJA;
    }

    @RequestMapping(value = ConstantControllers.UPDATE_CAJA, method = RequestMethod.POST)
    public String updateCaja(
            @ModelAttribute(value = "caja") CajaDTO dto) {
        Caja caja = new Caja();
        caja.setConcepto(dto.getConcepto());
        caja.setFecha(Util.parseToDate(dto.getFechaMovimiento()));
        if (dto.getTipoMovimiento().intValue() == 1) {
            caja.setIngreso(dto.getMonto());
            caja.setEgreso(new Double(0.0));
        } else if (dto.getTipoMovimiento().intValue() == 2) {
            caja.setEgreso(dto.getMonto());
            caja.setIngreso(new Double(0.0));
        }

        cajaManager.add(caja);
        return "redirect: /mainCaja/null";
    }

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

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        List<Caja> cajas = cajaManager.findAllByfecha(fecha);
        map.addAttribute("caja", cajaDTO);
        map.addAttribute("cajaList", getCajasList(cajas));
        map.addAttribute("total", getTotalCaja(cajas));
        map.addAttribute("fechaBoton", "Cerrar Caja " + format.format(fecha));
        map.addAttribute("fecha", format.format(fecha));
        return ConstantRedirect.VIEW_MAIN_CAJA;
    }

    // Ajax --------------------------------------------
    @RequestMapping(value = ConstantControllers.AJAX_GET_MOVIMIENTOS_CAJA, method = RequestMethod.GET)
    public @ResponseBody List<ComboItemDTO> getProfesionalesPaginados(
            @RequestParam(required = false, defaultValue = "0") Integer tipoMovimiento) {
        List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();

        if (tipoMovimiento.intValue() == 1) { // Ingresos
            for (java.util.Map.Entry<Integer, String> entry : Util.CAJA_CONCEPTOS.entrySet()) {
                Integer key = entry.getKey();
                String value = entry.getValue();
                if (key.intValue() < 100 && key.intValue() != 1 && key.intValue() != 2) {
                    retorno.add(new ComboItemDTO(key + "", value));
                }
            }

        } else if (tipoMovimiento.intValue() == 2) { // Egresos
            for (java.util.Map.Entry<Integer, String> entry : Util.CAJA_CONCEPTOS.entrySet()) {
                Integer key = entry.getKey();
                String value = entry.getValue();
                if (key.intValue() >= 100) {
                    retorno.add(new ComboItemDTO(key + "", value));
                }
            }
        }

        return retorno;
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
