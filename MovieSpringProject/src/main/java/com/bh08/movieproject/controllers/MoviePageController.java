/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.models.Movie;
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
                
               Movie movie = movieService.findByTitle("Marvel");
               Movie movie2 = movieService.findById(2);
                
                model.addAttribute("movie", movie);
                model.addAttribute("movie2", movie2);
		return "moviepage.html";         
	}
    
       /* @RequestMapping(value = "moviepage", method = RequestMethod.POST)
	public String addTitle(Model model) {
              
                model.addAttribute(movieService.findByTitle("Marvel"));
		return "moviepage.html";
}*/
}