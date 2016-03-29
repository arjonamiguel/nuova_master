package com.nuova.controller;

import com.nuova.dto.CajaDTO;
import com.nuova.dto.CierreCajaDTO;
import com.nuova.dto.ComboItemDTO;
import com.nuova.model.Caja;
import com.nuova.model.CajaCierre;
import com.nuova.service.CajaManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;
import com.nuova.utils.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CajaController {
  @Autowired
  CajaManager cajaManager;

  @RequestMapping(value = ConstantControllers.FORM_CIERRE_CAJA, method = RequestMethod.GET)
  public String formCierreCaja(ModelMap map,
      @RequestParam(required = false, defaultValue = "01-01-9999") String fechaCierre) {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    Date fecha = Util.parseToDate(fechaCierre);
    Double monto = getTotalCaja(cajaManager.findAllByfecha(fecha));
    CierreCajaDTO dto = new CierreCajaDTO();
    dto.setFechaCierreView(format.format(fecha));
    dto.setFechaCierre(fechaCierre);
    // dto.setMontoView(monto);
    dto.setMonto(monto);
    map.addAttribute("cajaCierre", dto);
    return ConstantRedirect.VIEW_FORM_CIERRE_CAJA;
  }

  @RequestMapping(value = ConstantControllers.FORM_UPDATE_CAJA, method = RequestMethod.GET)
  public String formUpdateCaja(ModelMap map) {
    CajaDTO dto = new CajaDTO();
    map.addAttribute("caja", dto);

    return ConstantRedirect.VIEW_FORM_UPDATE_CAJA;
  }

  @RequestMapping(value = ConstantControllers.CIERRE_CAJA, method = RequestMethod.POST)
  public String cierreCaja(@ModelAttribute(value = "cajaCierre") CierreCajaDTO dto,
      BindingResult result) {
    CajaCierre cierre = new CajaCierre();
    cierre.setFechaCierre(Util.parseToDate(dto.getFechaCierre()));
    cierre.setMonto(dto.getMonto());

    cajaManager.addCajaCierre(cierre);
    return "redirect: /mainCaja/null";
  }

  @RequestMapping(value = ConstantControllers.UPDATE_CAJA, method = RequestMethod.POST)
  public String updateCaja(@ModelAttribute(value = "caja") CajaDTO dto) {
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
  public String mainCaja(ModelMap map, @PathVariable("fechaCaja") String fechaCaja) {
    CajaDTO cajaDTO = new CajaDTO();
    Date fecha = null;
    if (!fechaCaja.equals("null")) {
      fecha = Util.parseToDate(fechaCaja);
    } else {
      fecha = new Date(); // fecha hoy
    }

    SimpleDateFormat formatbot = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    List<Caja> cajas = cajaManager.findAllByfecha(fecha);
    map.addAttribute("caja", cajaDTO);
    map.addAttribute("cajaList", getCajasList(cajas));
    map.addAttribute("total", getTotalCaja(cajas));
    map.addAttribute("fechaBoton", "Cerrar Caja " + formatbot.format(fecha));
    map.addAttribute("fecha", format.format(fecha));
    map.addAttribute("hasCierre", hasCierre(fecha));
    return ConstantRedirect.VIEW_MAIN_CAJA;
  }

  private boolean hasCierre(Date fecha) {
    if (cajaManager.findCajaCierreByFecha(fecha) != null) {
      return false;
    } else {
      return true;
    }
  }

  @RequestMapping(value = ConstantControllers.MAIN_CIERRE_CAJA, method = RequestMethod.GET)
  public String mainCaja(ModelMap map) {
    return ConstantRedirect.VIEW_MAIN_CIEREE_CAJA;
  }

  // Ajax --------------------------------------------
  @RequestMapping(value = ConstantControllers.AJAX_GET_CAJACIERRE_PAGINADOS,
      method = RequestMethod.GET)
  public @ResponseBody Page<CierreCajaDTO> getProfesionalesPaginados(
      @RequestParam(required = false, defaultValue = "0") Integer start,
      @RequestParam(required = false, defaultValue = "50") Integer limit) {

    // Sort and Pagination
    // Sort sort = new Sort(Sort.Direction.DESC, "creationDate");
    Pageable pageable = new PageRequest(start, limit);

    Page<CajaCierre> cajacierres = cajaManager.findCajaCierreByPageable(pageable);
    List<CierreCajaDTO> dtos = new ArrayList<CierreCajaDTO>();
    for (CajaCierre c : cajacierres) {
      CierreCajaDTO dto = new CierreCajaDTO();
      dto.setCajaCierreId(c.getCajaCierreId());
      dto.setFechaCierre(c.getFechaCierre() + "");
      dto.setMonto(c.getMonto());
      dtos.add(dto);
    }

    return new PageImpl<CierreCajaDTO>(dtos, pageable, cajacierres.getTotalElements());
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
