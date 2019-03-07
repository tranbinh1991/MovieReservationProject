/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.models.Movie;
import com.bh08.movieproject.services.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Binh
 */

@Controller
public class MainPageController {
    
    @Autowired
    private MovieService movieService;
    
    
    @GetMapping("/mainpage")
    public String showMainPage(Model model) {
        List<Movie> movieList = movieService.findAll();
        model.addAttribute("movielist", movieList); 
        
        return "mainpage.html";
    }
}
