/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.models.User;
import com.bh08.movieproject.services.UserService;
import com.bh08.movieproject.services.SessionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author Zoli
 */
@Controller
public class AdminAdderController {
        
    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;
    
    @RequestMapping(value = "adminadder", method = RequestMethod.GET)
    public String showAdminAdderPage(Model model) {
        List<User> admins = userService.findByCinemaAdmin(true);
        if (!admins.isEmpty()) {
            admins.remove(userService.findById(sessionService.getCurrentUserId()));
        }
        
        List<User> customers = userService.findByCinemaAdmin(false);
        model.addAttribute("admins", admins);
        model.addAttribute("customers", customers);
        return "adminadder.html";
    }
    
    @RequestMapping(value = "adminadder/{user.id}")
    public String changeStatus(Model model, @PathVariable("user.id") Long id) {
        User user = userService.findById(id);
        user.setCinemaAdmin(!user.isCinemaAdmin());
        userService.saveUserWithoutPasswordEncryption(user);
        return showAdminAdderPage(model);
    }
}
