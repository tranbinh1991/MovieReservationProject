/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.models.Movie;
import com.bh08.movieproject.models.Screening;
import com.bh08.movieproject.services.RoomService;
import com.bh08.movieproject.services.ScreeningService;
import java.util.Arrays;
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
public class ReservationController {

    @Autowired
    private ScreeningService screeningService;

    @RequestMapping(value = "/reservationpage", method = RequestMethod.GET)

    public String showReservationPage(Model model, @RequestParam(value = "id", required = false) Long screeningId) {
        Screening screening = screeningService.findById(screeningId);
        int[] numbersColumnOfChair = new int[screening.getTicketList().size()];
        int[] numbersRowOfChair = new int[screening.getTicketList().size()];
        
        for (int i = 0; i < screening.getTicketList().size(); i++) {
            numbersColumnOfChair[i]=screening.getTicketList().get(i).getChair().getColumnOfChair();
            numbersRowOfChair[i]=screening.getTicketList().get(i).getChair().getRowOfChair()-64;
        }

        model.addAttribute("numbersColumnOfChair", numbersColumnOfChair);
        model.addAttribute("numbersRowOfChair", numbersRowOfChair);
        model.addAttribute("numberColumn", screening.getRoom().getColumnCount());
        model.addAttribute("numberRow", screening.getRoom().getRowCount());

        model.addAttribute("screening", screening);

        return "reservationpage.html";
    }
}
