/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.services;

import com.bh08.movieproject.daos.TicketRepository;
import com.bh08.movieproject.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zoli
 */
@Service
public class TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;
    
    private Ticket saveTicket(Ticket ticket) {
        return ticketRepository.saveAndFlush(ticket);
    }
}
