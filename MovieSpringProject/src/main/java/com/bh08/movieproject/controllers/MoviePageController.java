/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.services.UserService;
import com.bh08.movieproject.viewmodels.RegistrationFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.bh08.movieproject.services.MovieService;
import com.bh08.movieproject.viewmodels.MoviePageFormData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Norbi
 */
@Controller
public class MoviePageController {
    
    @Autowired
    private MovieService movieService;
    
    
    @RequestMapping(value = "moviepage", method = RequestMethod.GET)
	public String moviepage(Model model) {
		model.addAttribute("moviePageFormData", new MoviePageFormData());
		return "moviepage.html";
	}
    
}
