package com.bh08.movieproject.controllers;

import com.bh08.movieproject.models.User;
import com.bh08.movieproject.services.UserService;
import com.bh08.movieproject.viewmodels.LoginFormData;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("loginFormData", new LoginFormData());
        return "login.html";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String submitLogin(@ModelAttribute("loginFormData") @Valid LoginFormData loginFormData,
            BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            if (!userService.findByEmail(loginFormData.getEmail()).isEmpty()) {
                if(userService.findByEmail(loginFormData.getEmail()).get(0).getPassword().equals(loginFormData.getPassword())) {
                    return "successful_login.html";
                }
                
            }
        }
        return "login.html";
    }

}
