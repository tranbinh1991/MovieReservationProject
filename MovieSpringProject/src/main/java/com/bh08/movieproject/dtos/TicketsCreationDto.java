/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.dtos;

import com.bh08.movieproject.models.Ticket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Binh
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketsCreationDto {
    private List<Ticket> ticketList = new ArrayList<>();
    
    public void addTicket(Ticket ticket){
        this.ticketList.add(ticket);
    }
    
}
