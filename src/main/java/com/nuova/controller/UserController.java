package com.nuova.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuova.dto.UsuarioDTO;
import com.nuova.model.UserDetails;
import com.nuova.service.UserManager;
import com.nuova.utils.ConstantControllers;

@Controller
public class UserController {
    @Autowired
    UserManager userManager;

    @RequestMapping(value = ConstantControllers.AJAX_POST_UPDATE_PASSWORD, method = RequestMethod.POST,
            headers = { "content-type=application/json" })
    public @ResponseBody String updatePassword(@RequestBody UsuarioDTO dto)
            throws Exception {
        UserDetails u = userManager.findUserByUserId(dto.getUserId());
        u.setPassword(dto.getNuevaPassword());
        userManager.edit(u);

        return "OK";
    }
}
