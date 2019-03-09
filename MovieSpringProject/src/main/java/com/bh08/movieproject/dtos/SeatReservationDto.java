/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Binh
 */
@Getter
@Setter
public class SeatReservationDto {
    
    private Long seatId;
    private Long screeningId;
    private Long ChairId;
    
}
