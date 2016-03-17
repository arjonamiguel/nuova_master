package com.nuova.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nuova.dto.CalendarDTO;
import com.nuova.dto.ComboItemDTO;
import com.nuova.dto.ObraSocialDTO;
import com.nuova.model.Calendario;
import com.nuova.model.Especialidad;
import com.nuova.model.Profesional;
import com.nuova.service.CalendarManager;
import com.nuova.service.ProfesionalManager;
import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;
import com.nuova.utils.Util;

@Controller
public class CalendarioController {

	@Autowired
	CalendarManager calendarManager;
	
	@Autowired
	ProfesionalManager profesionalManager;
	
    @RequestMapping(value = ConstantControllers.MAIN_CALENDARIO, method = RequestMethod.GET)
    public String formMainCalendario(ModelMap map) {
         map.addAttribute("eventos", getCalendar());
   
        return ConstantRedirect.VIEW_MAIN_CALENDARIO;
    }
    
    @RequestMapping(value = ConstantControllers.ADMIN_CALENDARIO, method = RequestMethod.GET)
    public String formAdminCalendario(ModelMap map) {
         map.addAttribute("eventos", getCalendar());
   
        return ConstantRedirect.VIEW_ADMIN_CALENDARIO;
    }
    
    @RequestMapping(value = ConstantControllers.FORM_ADD_CALENDARIO, method = RequestMethod.GET)
    public String formAddCalendario(ModelMap map) {
    	map.addAttribute("calendario", new CalendarDTO());
    	List<Profesional> profesionales = profesionalManager.findAll();
    	map.addAttribute("profesionales", getProfesionalDTOList(profesionales));
    	map.addAttribute("consultorios", getConsultorios());
        return ConstantRedirect.VIEW_FORM_ADD_CALENDARIO;
    }
    
    @RequestMapping(value = ConstantControllers.ADD_CALENDARIO, method = RequestMethod.POST)
    public String addCalendario(
            @ModelAttribute(value = "calendario") CalendarDTO dto,
            BindingResult result) {

        if (dto != null) {
            calendarManager.add(Util.transformDtoToCalendario(dto));
        }

        return "redirect:" + ConstantControllers.MAIN_CALENDARIO;
    }
    
    @RequestMapping(value = ConstantControllers.FORM_DELETE_CALENDARIO, method = RequestMethod.GET)
    public String formDeleteCalendario(ModelMap map,
            @PathVariable("calendarioId") Integer calendarioId) {
        if (calendarioId != null) {
        	
     	   SimpleDateFormat formatter;
 		   formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        	
        	Calendario calendarioEntity= calendarManager.findCalendarioById(calendarioId);
        	CalendarDTO calendarDTO =		new CalendarDTO();
        	calendarDTO.setId(calendarioEntity.getCalendarioId());
        	calendarDTO.setResourceId(calendarioEntity.getResource());
        	calendarDTO.setStart(formatter.format(calendarioEntity.getStart())); 
        	calendarDTO.setEnd(formatter.format(calendarioEntity.getEnd())); 
        	calendarDTO.setTitle(calendarioEntity.getTitle());
        	
            map.addAttribute("calendario", calendarDTO);
            map.addAttribute("calendarioEntity", calendarioEntity);
        }

        return ConstantRedirect.VIEW_FORM_DELETE_CALENDARIO;
    }
    
    
    @RequestMapping(value = ConstantControllers.DELETE_CALENDARIO, method = RequestMethod.POST)
    public String deleteEspecialidad(@ModelAttribute(value = "calendarioEntity") Calendario calendario) {
    	
    	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//    	calendario.setStart(formatter.parse(calendario.getStart()));
   
    	calendarManager.delete(calendario.getCalendarioId());
        return "redirect:" + ConstantControllers.ADMIN_CALENDARIO;
    }

    
    private String getCalendar(){
    	
    	List<Calendario> calendarList = calendarManager.getCalendar();
    	
    	for (Calendario calendario : calendarList) {
			System.out.println(calendario.getTitle());
		}
    	Gson gson = new Gson();

    	// convert java object to JSON format,
    	// and returned as JSON formatted string
    	String json = gson.toJson(getCalendarDTO(calendarList));
    	System.out.println(json);
    	return json;
    }

    private List<CalendarDTO> getCalendarDTO(List<Calendario> listCalendario){
    	List<CalendarDTO> retorno = new ArrayList<CalendarDTO>();
    	   SimpleDateFormat formatter;
		   formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    	for (Calendario calendario : listCalendario) {
				CalendarDTO dto = new CalendarDTO();
				dto.setId(calendario.getCalendarioId());
				dto.setResourceId(calendario.getResource());
				dto.setTitle(calendario.getTitle());
				dto.setStart(formatter.format(calendario.getStart())); 
				dto.setEnd(formatter.format(calendario.getEnd())); 
				retorno.add(dto);
			}
    	
    	return retorno;
    }
    
    private List<ComboItemDTO> getProfesionalDTOList(List<Profesional> list) {
        List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
        for (Profesional p : list) {
        	
        	String name=  p.getApellido() + ", " + p.getNombre();
            retorno.add(new ComboItemDTO(name + "", name));
        }
        return retorno;
    }
    
    private List<ComboItemDTO> getConsultorios()
    {
    	List<ComboItemDTO> retorno = new ArrayList<ComboItemDTO>();
    	retorno.add(new ComboItemDTO("a","a"));
    	retorno.add(new ComboItemDTO("b","b"));
    	retorno.add(new ComboItemDTO("c","c"));
    	retorno.add(new ComboItemDTO("d","d"));
    	retorno.add(new ComboItemDTO("e","e"));
    	
    	return retorno;
    }
    
    private Calendario tranformDtoToCalendario(CalendarDTO dto){
    	
    	
    	
    	
    	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	Calendario calendario = new Calendario();
    	
    	try {
    		calendario.setCalendarioId(dto.getId());
        	calendario.setResource(dto.getResourceId());
        	calendario.setStart(formatter.parse(dto.getStart()));
        	calendario.setEnd(Util.parseToDate(dto.getEnd()));
        	calendario.setTitle(dto.getTitle());
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	
    	
    	return calendario;	
    }
}
