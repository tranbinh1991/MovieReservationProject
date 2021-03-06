/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.services.SessionService;
import com.bh08.movieproject.services.UserService;
import com.bh08.movieproject.viewmodels.LoginFormData;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jbacso@gmail.com
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {
        if (sessionService.getUserId() != null) {
            return "successful_login.html";
        }
        model.addAttribute("loginFormData", new LoginFormData());
        return "login.html";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String submitLogin(@ModelAttribute("loginFormData") @Valid LoginFormData loginFormData,
            BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            if (!userService.findByEmail(loginFormData.getEmail()).isEmpty()) {
                if (BCrypt.checkpw(loginFormData.getPassword(), userService.findByEmail(loginFormData.getEmail()).get(0).getPassword())) {
                    sessionService.setUserId(userService.findByEmail(loginFormData.getEmail()).get(0).getId());                    
                    return "successful_login.html";
                } else {
                    bindingResult.rejectValue("password", "", "Hibás jelszó!");
                }
            } else {
                bindingResult.rejectValue("email", "", "Hibás email-cím!");
            }
        }
        return "login.html";
    }

}