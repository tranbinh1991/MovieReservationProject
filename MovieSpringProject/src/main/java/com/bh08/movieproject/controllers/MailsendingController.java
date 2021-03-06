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
import com.bh08.movieproject.services.MailSendingService;
import com.bh08.movieproject.services.RoomService;
import com.bh08.movieproject.services.ScreeningService;
import com.bh08.movieproject.services.SessionService;
import com.bh08.movieproject.services.TicketService;
import com.bh08.movieproject.services.UserService;
import com.bh08.movieproject.util.PdfGenaratorUtil;
import com.bh08.movieproject.viewmodels.RoomCreationFormData;
import com.bh08.movieproject.viewmodels.TicketCreationFormData;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
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
public class MailsendingController {
    
    @Autowired
    private MailSendingService mailSendingService;
    
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
    
    @Autowired
    private PdfGenaratorUtil util;
    
    @RequestMapping(value = "successfulticketbooking", method = RequestMethod.POST)
    public String addNewTickets(@ModelAttribute("ticketCreationFormData") @Valid TicketCreationFormData ticketCreationFormData,
            BindingResult bindingResult, Model model) {
        
        System.out.println(ticketCreationFormData.getTicketTypes());
        String[] chosenTicketTypes = ticketCreationFormData.getTicketTypes().split("\\,");
        
        String finalFilename;
        
        List<Ticket> ticketList = new ArrayList<>();
        
        for (int i = 0; i < sessionService.getSeatReservationDtos().size(); i++) {
            Ticket ticket = new Ticket();
            
            ticket.setChair(chairService.findById(sessionService.getSeatReservationDtos().get(i).getChairId()));
            ticket.setScreening(screeningService.findById(sessionService.getSeatReservationDtos().get(i).getScreeningId()));
            ticket.setUser(userService.findById(sessionService.getUserId()));
            if (chosenTicketTypes[i].equals("student")) {
                ticket.setStudent(true);
            } else {
                ticket.setStudent(false);
            }
            
            ticket.setTicketPrice();
            ticketService.saveTicket(ticket);
            ticketList.add(ticket);
        }
        
        String QRtitle = ticketList.get(0).getScreening().getMovie().getTitle().replaceAll(" ", "+");
        
        Map<Object, Object> data = new HashMap<>();
        data.put("tickets", ticketList);
        data.put("qrtitle", QRtitle);
        Screening screening = ticketList.get(0).getScreening();
        if (screeningService.isScreeningOccupied(ticketList.get(0).getScreening(), ticketList.size())) {            
            screening.setOccupied(true);
            screeningService.saveScreening(screening);
        }
        try {
            finalFilename = util.createPdf("listoftickets", data);
            mailSendingService.sendMail(userService.findById(sessionService.getUserId()), finalFilename);
        } catch (Exception ex) {
            Logger.getLogger(MailsendingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "successfulticketbooking.html";
    }
    
}
