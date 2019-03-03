/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 *
 * @author Zoli
 */
@Controller
public class MovieAdderController {
    
    @Autowired
    private MovieService movieService;
    
    
    public String showMovieAdderPage(Model model) {
        
        return "movieadder.html";
    }
}
