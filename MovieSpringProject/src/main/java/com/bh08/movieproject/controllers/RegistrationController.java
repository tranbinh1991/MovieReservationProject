/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.models.User;
import com.bh08.movieproject.services.UserService;
import com.bh08.movieproject.viewmodels.RegistrationFormData;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Zoli
 */
@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    /*@RequestMapping(value = "register", method = RequestMethod.POST)
    public @ResponseBody
    User createUser(@RequestBody User user) {
        return userService.saveUser(user);

    }*/
    @RequestMapping(value = "registerUser", method = RequestMethod.POST)
    public @ResponseBody
    User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    
    @RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("registrationFormData", new RegistrationFormData());
		return "register.html";
	}

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String submitRegistration(@ModelAttribute("registrationFormData") @Valid RegistrationFormData registrationFormData,
            BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            if (userService.findByEmail(registrationFormData.getEmail()).isEmpty()
                    && registrationFormData.getPassword().equals(registrationFormData.getPasswordAgain())) {
                return "main_page.html";
            }
        }
        return "register.html";
    }

    /*
    @RequestMapping(value = "login", method = RequestMethod.POST)
	public String submitLogin(@ModelAttribute("loginFormData") @Valid LoginFormData loginFormData, BindingResult bindingResult, Model model) {
		System.out.println(loginFormData);
		if (!bindingResult.hasErrors() && "Ubul".equals(loginFormData.getUserName()) && "1234".equals(loginFormData.getPassword())) {
			sessionService.setCurrentUserName(loginFormData.getUserName());
			model.addAttribute("loggedInUserName", sessionService.getCurrentUserName());
			return "status.html";
		}
		return "login.html";
	}
     */
}
