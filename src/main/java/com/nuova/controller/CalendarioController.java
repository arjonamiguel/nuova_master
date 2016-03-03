package com.nuova.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nuova.utils.ConstantControllers;
import com.nuova.utils.ConstantRedirect;

@Controller
public class CalendarioController {

    @RequestMapping(value = ConstantControllers.MAIN_CALENDARIO, method = RequestMethod.GET)
    public String formAddObraSocial(ModelMap map) {
         map.addAttribute("eventos", getFakeCalendar());
        return ConstantRedirect.VIEW_MAIN_CALENDARIO;
    }
    
    private String getFakeCalendar(){
    	return "["
    			+ "{ id: '1', resourceId: 'b', start: '2016-01-07T02:00:00', end: '2016-01-07T07:00:00', title: 'Dr. Lizarraga' },"
    			+ "{ id: '2', resourceId: 'c', start: '2016-01-07T05:00:00', end: '2016-01-07T22:00:00', title: 'Dr. Gordillo' },"
    			+ "{ id: '3', resourceId: 'd', start: '2016-01-06', end: '2016-01-08', title: 'Dr Sotelo' },"
    			+ "{ id: '4', resourceId: 'e', start: '2016-01-07T03:00:00', end: '2016-01-07T08:00:00', title: 'Dr Roldan' },"
    			+ "{ id: '5', resourceId: 'f', start: '2016-01-07T00:30:00', end: '2016-01-07T02:30:00', title: 'Dr Brandan' }"
    			+ "];";
    	
    	
    }
    
    private String getCalendar(){
    	
    	return "";
    }

}
