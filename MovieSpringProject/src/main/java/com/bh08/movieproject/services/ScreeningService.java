/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.services;

import com.bh08.movieproject.daos.ScreeningRepository;
import com.bh08.movieproject.models.Screening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zoli
 */
@Service
public class ScreeningService {
    
    @Autowired
    private ScreeningRepository screeningRepository;
    
    public Screening saveScreening(Screening screening) {
        return screeningRepository.saveAndFlush(screening);
    }
    
    public boolean isScreeningOccupied(Screening screening) {
        return (screening.getTicketList().size() == (screening.getRoom().getColumnCount()*screening.getRoom().getRowCount()));
    }
    
        public Screening findById(Long screeningId){
        return screeningRepository.findFirstById(screeningId);
    }
}
