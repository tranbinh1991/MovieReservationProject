/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import com.bh08.movieproject.dtos.SeatReservationDto;
import com.bh08.movieproject.dtos.TicketsCreationDto;
import com.bh08.movieproject.models.Movie;
import com.bh08.movieproject.models.Room;
import com.bh08.movieproject.models.Screening;
import com.bh08.movieproject.models.Ticket;
import com.bh08.movieproject.services.ChairService;
import com.bh08.movieproject.services.RoomService;
import com.bh08.movieproject.services.ScreeningService;
import com.bh08.movieproject.services.SessionService;
import com.bh08.movieproject.services.TicketService;
import com.bh08.movieproject.services.UserService;
import com.bh08.movieproject.viewmodels.RoomCreationFormData;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Binh
 */
@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;
    
    @Autowired
    private ScreeningService screeningService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ChairService chairService;
    
    @Autowired
    private SessionService sessionService;
    

    @RequestMapping(value = "/ticketpage", method = RequestMethod.GET)
    public String showReservationPage(Model model, @RequestParam(value = "id", required = false) String ticketBookingId) {
        
        String[] array = ticketBookingId.split("\\,");
        Screening screening= screeningService.findById(Long.parseLong(array[0]));
        TicketsCreationDto ticketsCreationDto = new TicketsCreationDto();
        for (int i = 0; i < (array.length-1); i++) {
            String[] splittedNumbers = array[i+1].split("\\-");
            
            Ticket ticket = new Ticket();
            SeatReservationDto reservationDto = new SeatReservationDto();
            ticket.setUser(userService.findById(494L));
            sessionService.setUserId(494L);
            
            int  numberOfRow = Integer.parseInt(splittedNumbers[0])+1;
            char actualCharOfChair = (char)(64+numberOfRow);
            int actualNumberOfChair = Integer.parseInt(splittedNumbers[1])+1;

            ticket.setChair(chairService.findByRowOfChairAndColumnOfChairAndRoom(actualCharOfChair, actualNumberOfChair, screening.getRoom()));
            reservationDto.setChairId(chairService.findByRowOfChairAndColumnOfChairAndRoom(actualCharOfChair, actualNumberOfChair, screening.getRoom()).getId());
            ticket.setScreening(screening);
            reservationDto.setScreeningId(screening.getId());
            
            
           
            //ticketService.saveTicket(ticket);
//            System.out.println(ticket);
            ticketsCreationDto.addTicket(ticket);
            sessionService.getSeatReservationDtos().add(reservationDto);
 
        }

        model.addAttribute("ticketsCreationDto", ticketsCreationDto);
        model.addAttribute("screening", screening);
        

        return "ticketpage.html";
    }
    
    @RequestMapping(value = "ticketpage", method = RequestMethod.POST)
    public String addNewTickets(@ModelAttribute("ticketsCreationDto") @Valid TicketsCreationDto ticketsCreationDto,
            BindingResult bindingResult, Model model) {
        
        for (int i = 0; i < sessionService.getSeatReservationDtos().size(); i++) {
            Ticket ticket = new Ticket();
            ticket.setChair(chairService.findById(sessionService.getSeatReservationDtos().get(i).getChairId()));
            ticket.setScreening(screeningService.findById(sessionService.getSeatReservationDtos().get(i).getScreeningId()));
            ticket.setUser(userService.findById(sessionService.getUserId()));
            ticketService.saveTicket(ticket);
        }
        
        
        

        return "successfulticketbooking.html";
    }
    
    
}
