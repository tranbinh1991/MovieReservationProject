/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.models.Language;
import com.bh08.movieproject.models.Movie;
import com.bh08.movieproject.models.Room;
import com.bh08.movieproject.models.Screening;
import com.bh08.movieproject.services.MovieService;
import com.bh08.movieproject.services.RoomService;
import com.bh08.movieproject.services.ScreeningService;
import com.bh08.movieproject.viewmodels.ScreeningCreationFormData;
import java.time.DateTimeException;
import java.time.LocalDateTime;
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
public class ScreeningAdderController {

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "screeningadder", method = RequestMethod.GET)
    public String showScreeningAdder(Model model) {
        Language[] languages = Language.values();
        List<Movie> movies = movieService.findAll();
        List<Room> rooms = roomService.findAll();
        model.addAttribute("movies", movies);
        model.addAttribute("rooms", rooms);
        model.addAttribute("languages", languages);
        model.addAttribute("screeningCreationFormData", new ScreeningCreationFormData());
        return "screeningadder.html";
    }

    @RequestMapping(value = "screeningadder", method = RequestMethod.POST)
    public String createScreening(@ModelAttribute("screeningCreationFormData") @Valid ScreeningCreationFormData screeningCreationFormData,
            BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            Screening screening = new Screening();
            Movie movie = movieService.findByTitle(screeningCreationFormData.getMovie()).get(0);
            screening.setMovie(movie);

            Room room = roomService.findByRoomNumber(Integer.parseInt(screeningCreationFormData.getRoom())).get(0);
            screening.setRoom(room);

            Language language = Language.valueOf(screeningCreationFormData.getLanguage());
            screening.setLanguage(language);

            try {
                LocalDateTime time = LocalDateTime.of(Integer.parseInt(screeningCreationFormData.getYear()),
                        Integer.parseInt(screeningCreationFormData.getMonth()),
                        Integer.parseInt(screeningCreationFormData.getDay()),
                        Integer.parseInt(screeningCreationFormData.getHour()),
                        Integer.parseInt(screeningCreationFormData.getMinute()));
                if (LocalDateTime.now().isAfter(time)) {
                    throw new DateTimeException("");
                }
                
                //TODO: ne lehessen egymással egyidőben ugyanott több vetítés
                screening.setTime(time);
                model.addAttribute("successMessage", "Sikeres mentés!");
                screeningService.saveScreening(screening);
            } catch(DateTimeException e) {
                //bindingResult.rejectValue("day", "", "Hibás dátum!");
                model.addAttribute("wrongDateMessage", "Hibás dátum!");
            }

        }
        return showScreeningAdder(model);
    }
}
