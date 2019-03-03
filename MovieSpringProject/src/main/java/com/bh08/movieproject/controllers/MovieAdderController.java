/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.models.MovieCategory;
import com.bh08.movieproject.services.MovieCategoryService;
import com.bh08.movieproject.services.MovieService;
import com.bh08.movieproject.viewmodels.MovieCreationFormData;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Zoli
 */
@Controller
public class MovieAdderController {
    
    @Autowired
    private MovieService movieService;
    
    @Autowired
    private MovieCategoryService movieCategoryService;
    
    @RequestMapping(value = "movieadder", method = RequestMethod.GET)
    public String showMovieAdderPage(Model model) {
//        List<MovieCategory> movieCategories = movieCategoryService.findAllOrderByCategory();
//        model.addAttribute("movieCategories", movieCategories);
        model.addAttribute("movieCreationFormData", new MovieCreationFormData());
        return "movieadder.html";
    }
    
    @RequestMapping(value = "createMovie", method = RequestMethod.POST)
    public String createMovie(Model model, BindingResult bindingResult, 
            @Valid @ModelAttribute("movieCreationFormData") MovieCreationFormData movieCreationFormData) {
        if (!bindingResult.hasErrors()) {
            
        }
        return "movieadder.html";
    }
}
