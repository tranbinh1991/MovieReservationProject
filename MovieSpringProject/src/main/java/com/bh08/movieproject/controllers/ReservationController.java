/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Binh
 */
@Controller
public class ReservationController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/reservationpage")
    public String showReservationPgae(Model model) {
        
         model.addAttribute("numberColumn", roomService.getRoomColumnNumber(1L));
         model.addAttribute("numberRow", roomService.getRoomRowNumber(1L));
        return "reservationpage.html";
    }
}
