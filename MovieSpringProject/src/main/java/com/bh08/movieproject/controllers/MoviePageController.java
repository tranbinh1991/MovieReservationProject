/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.models.Movie;
import com.bh08.movieproject.services.MovieService;
import com.bh08.movieproject.services.RoomService;
import com.bh08.movieproject.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Binh
 */
@Controller
public class MoviePageController {

    @Autowired
    private MovieService movieService;
    
    @Autowired
    private SessionService service;

    @RequestMapping(value = "/moviepage", method = RequestMethod.GET)
    public String showMoviePage(Model model, @RequestParam(value="id", required=false) Long movieId) {
        service.getSeatReservationDtos().clear();
        Movie movie = movieService.findById(movieId);
        
        model.addAttribute("movie", movie);

        return "moviepage.html";
    }
}
