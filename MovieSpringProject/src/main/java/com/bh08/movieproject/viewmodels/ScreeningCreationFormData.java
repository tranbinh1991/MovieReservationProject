/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.viewmodels;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Zoli
 */
@Getter
@Setter
public class ScreeningCreationFormData {
    
    private String movie;
    private String room;
    private String startHour;
    private String startMinute;
    private String language;
}
