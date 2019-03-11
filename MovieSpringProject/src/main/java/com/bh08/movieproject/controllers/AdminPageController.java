/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.services.MovieCategoryService;
import com.bh08.movieproject.services.SessionService;
import com.bh08.movieproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Zoli
 */
@Controller
public class AdminPageController {
    
    @Autowired
    private MovieCategoryService movieCategoryService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;
    
    
    @RequestMapping(value = "adminpage", method = RequestMethod.GET)
    public String showAdminPage() {
        if (sessionService.getCurrentUserId() == null || !userService.findById(sessionService.getCurrentUserId()).isCinemaAdmin()) {
            return "adminerror.html";
        }
        return "adminpage.html";
    }
    
    @RequestMapping(value = "createCategories")
    public String createCategoriesInDb(Model model) {
        movieCategoryService.saveAllCategories();
        return "successfulcreation.html";
    }
}
