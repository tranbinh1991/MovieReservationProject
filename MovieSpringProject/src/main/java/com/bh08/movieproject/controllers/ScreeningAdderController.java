/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.models.Language;
import com.bh08.movieproject.models.Movie;
import com.bh08.movieproject.models.Room;
import com.bh08.movieproject.services.MovieService;
import com.bh08.movieproject.services.RoomService;
import com.bh08.movieproject.services.ScreeningService;
import com.bh08.movieproject.viewmodels.ScreeningCreationFormData;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Zoli
 */
@Controller
public class ScreeningAdderController {
    
    @Autowired
    private ScreeningService screeningService;
    
    @Autowired
    private MovieService movieService;
    
    @Autowired
    private RoomService roomService;
    
    @RequestMapping(value = "screeningadder", method = RequestMethod.GET)
    private String showScreeningAdder(Model model) {
        Language[] languages = Language.values();
        List<Language> languageList = new ArrayList<>();
        for (Language language : languages) {
            languageList.add(language);
        }
        List<Movie> movies = movieService.findAll();
        List<Room> rooms = roomService.findAll();
        model.addAttribute("movies", movies);
        model.addAttribute("rooms", rooms);
        model.addAttribute("languages", languages);
        model.addAttribute("screeningCreationFormData", new ScreeningCreationFormData());
        return "screeningadder.html";
    }
}
