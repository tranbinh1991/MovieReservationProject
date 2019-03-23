/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.models.Movie;
import com.bh08.movieproject.models.Screening;
import com.bh08.movieproject.services.MovieService;

import com.bh08.movieproject.services.SessionService;

import com.bh08.movieproject.services.ScreeningService;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private SessionService sessionService;

    @Autowired
    private ScreeningService screeningService;


    @RequestMapping(value = "/moviepage", method = RequestMethod.GET)
    public String showMoviePage(Model model, @RequestParam(value="id", required=false) Long movieId) {
        sessionService.getSeatReservationDtos().clear();
        Movie movie = movieService.findById(movieId);
        List<Screening> screeningList = screeningService.findByMovieOrderByTime(movie);
        Iterator<Screening> screeningIterator = screeningList.iterator();
        while (screeningIterator.hasNext()) {
            Screening s = screeningIterator.next();
            if (s.getTime().isBefore(LocalDateTime.now()) ||
                    (s.getRoom().getColumnCount()*s.getRoom().getRowCount())==s.getTicketList().size()) {
                screeningIterator.remove();                
            }
        }
        
        model.addAttribute("movie", movie);
        model.addAttribute("screeningList", screeningList);

        return "moviepage.html";
    }
}
